/*******************************************************************************
 * Copyright 2014 Virginia Tech Visionarium
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/


package graphVisualizer.conversion.prefuse;


import java.util.HashMap;
import java.util.List;
import org.graphdrawing.graphml.DefaultEdgeType;
import org.graphdrawing.graphml.Key;
import org.graphdrawing.graphml.KeyData;
import org.graphdrawing.graphml.GraphMLDocument;
import prefuse.data.Schema;
import prefuse.data.Table;


/**
 * This class contains conversion routines between different graph
 * representations.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public class GraphFormatConverter {

    /**
     * Converts a {@link GraphMLDocument} into a prefuse
     * {@link prefuse.data.Graph}.
     * 
     * @param graphMLDocument
     *            the {@link GraphMLDocument} to convert.
     * @return the prefuse {@link prefuse.data.Graph}.
     */
    public static prefuse.data.Graph graphMLtoPrefuse(
            GraphMLDocument graphMLDocument) {

        List<org.graphdrawing.graphml.Graph> graphs = graphMLDocument
                .getGraphs();

        org.graphdrawing.graphml.Graph theGraph = graphs.get(0);

        List<org.graphdrawing.graphml.Node> nodes = theGraph.getNodes();
        List<org.graphdrawing.graphml.Edge> edges = theGraph.getEdges();

        List<Key> keys = graphMLDocument.getKeys();

        prefuse.data.Schema nodeSchema = new Schema();
        nodeSchema.addColumn("id", String.class);

        prefuse.data.Schema edgeSchema = new Schema();
        edgeSchema.addColumn("id", String.class);
        edgeSchema.addColumn("source", int.class);
        edgeSchema.addColumn("target", int.class);

        for (Key key : keys) {

            Class<?> clazz = null;
            Object defaultValue = null;
            switch (key.getAttributeType()) {
                case BOOLEAN:
                    clazz = boolean.class;
                    if (key.getDefault() != null) {
                        defaultValue = Boolean.parseBoolean(key.getDefault()
                                .getContent());
                    }
                    break;
                case DOUBLE:
                    clazz = double.class;
                    if (key.getDefault() != null) {
                        defaultValue = Double.parseDouble(key.getDefault()
                                .getContent());
                    }
                    break;
                case FLOAT:
                    clazz = float.class;
                    if (key.getDefault() != null) {
                        defaultValue = Float.parseFloat(key.getDefault()
                                .getContent());
                    }
                    break;
                case INT:
                    clazz = int.class;
                    if (key.getDefault() != null) {
                        defaultValue = Integer.parseInt(key.getDefault()
                                .getContent());
                    }
                    break;
                case LONG:
                    clazz = long.class;
                    if (key.getDefault() != null) {
                        defaultValue = Long.parseLong(key.getDefault()
                                .getContent());
                    }
                    break;
                case STRING:
                    clazz = String.class;
                    if (key.getDefault() != null) {
                        defaultValue = key.getDefault().getContent();
                    }
                    break;
                default:
                    break;
            }
            switch (key.getKeyForType()) {
                case ALL:
                    // System.out.println("Adding key \"" + key.getId() +
                    // "\" to both nodes and edges.");
                    nodeSchema.addColumn(key.getId(), clazz);
                    edgeSchema.addColumn(key.getId(), clazz);

                    if (defaultValue != null) {
                        nodeSchema.setDefault(key.getId(), defaultValue);
                        edgeSchema.setDefault(key.getId(), defaultValue);
                    }

                    break;
                case EDGE:
                    // System.out.println("Adding key \"" + key.getId() +
                    // "\" to edges.");
                    edgeSchema.addColumn(key.getId(), clazz);

                    if (defaultValue != null) {
                        edgeSchema.setDefault(key.getId(), defaultValue);
                    }
                    break;
                case NODE:
                    // System.out.println("Adding key \"" + key.getId() +
                    // "\" to nodes.");
                    nodeSchema.addColumn(key.getId(), clazz);

                    if (defaultValue != null) {
                        nodeSchema.setDefault(key.getId(), defaultValue);
                    }
                    break;
                default:
                    break;
            }
        }

        prefuse.data.Table nodeTable = new Table();
        nodeTable.addColumns(nodeSchema);
        nodeTable.addRows(nodes.size());

        HashMap<String, Integer> nodeNameToID = new HashMap<>();

        int k = 0;
        Integer i;
        for (org.graphdrawing.graphml.Node aNode : nodes) {
            nodeTable.set(k, "id", aNode.getId());
            // System.out.println("Adding Node: " + aNode.getId());

            nodeNameToID.put(aNode.getId(), k);
            for (KeyData keyData : aNode.getKeyData()) {
                int columnIndex = nodeSchema.getColumnIndex(keyData.getKey());
                if (columnIndex >= 0) {
                    Class<?> clazz = nodeSchema.getColumnType(keyData.getKey());
                    // System.out.println("Adding data " + keyData.getContent()
                    // +
                    // " to key " + keyData.getKey());
                    // System.out.println(nodeSchema.getColumnType(columnIndex));
                    // System.out.println(nodeSchema.getColumnType(keyData.getKey()));
                    Object entry = null;
                    if (double.class.equals(clazz)) {
                        entry = Double.parseDouble(keyData.getContent());
                    }
                    else if (float.class.equals(clazz)) {
                        entry = Float.parseFloat(keyData.getContent());
                    }
                    else if (int.class.equals(clazz)) {
                        entry = Integer.parseInt(keyData.getContent());
                    }
                    else if (long.class.equals(clazz)) {
                        entry = Long.parseLong(keyData.getContent());
                    }
                    else if (String.class.equals(clazz)) {
                        entry = keyData.getContent();
                    }
                    nodeTable.set(k, keyData.getKey(), entry);

                }
            }

            k++;
        }

        // System.out.println("Done with Nodes");

        prefuse.data.Table edgeTable = new Table();
        edgeTable.addColumns(edgeSchema);
        edgeTable.addRows(edges.size());

        k = 0;
        i = 0;
        for (org.graphdrawing.graphml.Edge anEdge : edges) {
            if (anEdge.getId() != null) {
                edgeTable.set(k, "id", anEdge.getId());
                // System.out.println("Adding Edge: " + anEdge.getId());
            }
            else {
                edgeTable.set(k, "id", i.toString());
                // System.out.println("Adding Edge: " + i.toString());
                i++;
            }

            // System.out.print("Source: " + anEdge.getSource());
            // System.out.println(", Target: " + anEdge.getTarget());
            // System.out.print("SourceID: " +
            // nodeNameToID.get(anEdge.getSource()));
            // System.out.println(", TargetID: " +
            // nodeNameToID.get(anEdge.getTarget()));


            edgeTable.set(k, "source", nodeNameToID.get(anEdge.getSource()));
            edgeTable.set(k, "target", nodeNameToID.get(anEdge.getTarget()));


            for (KeyData keyData : anEdge.getKeyData()) {
                int columnIndex = edgeSchema.getColumnIndex(keyData.getKey());

                if (columnIndex >= 0) {
                    Class<?> clazz = edgeSchema.getColumnType(columnIndex);
                    Object entry = null;
                    if (clazz.equals(double.class)) {
                        entry = Double.parseDouble(keyData.getContent());
                    }
                    else if (clazz.equals(float.class)) {
                        entry = Float.parseFloat(keyData.getContent());
                    }
                    else if (clazz.equals(int.class)) {
                        entry = Integer.parseInt(keyData.getContent());
                    }
                    else if (clazz.equals(long.class)) {
                        entry = Long.parseLong(keyData.getContent());
                    }
                    else if (clazz.equals(String.class)) {
                        entry = keyData.getContent();
                    }
                    edgeTable.set(k, keyData.getKey(), entry);

                }
            }

            k++;
        }


        boolean directed = false;
        if (theGraph.getDefaultEdgeType() == DefaultEdgeType.DIRECTED) {
            directed = true;
        }

        prefuse.data.Graph graph = new prefuse.data.Graph(nodeTable, edgeTable,
                directed, "source", "target");


        return graph;
    }

}
