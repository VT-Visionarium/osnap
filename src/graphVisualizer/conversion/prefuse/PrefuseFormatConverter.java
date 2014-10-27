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


import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jutility.io.ConversionException;
import org.jutility.io.IConverter;

import prefuse.data.Table;
import graphVisualizer.graph.Graph;
import graphVisualizer.graph.Universe;
import graphVisualizer.graph.common.IEdge;
import graphVisualizer.graph.common.IGraph;
import graphVisualizer.graph.common.IGraphObject;
import graphVisualizer.graph.common.INode;
import graphVisualizer.graph.common.IUniverse;
import graphVisualizer.graph.metadata.IMetadataContainer;
import graphVisualizer.graph.metadata.Metadata;
import graphVisualizer.graph.metadata.Schema;
import graphVisualizer.graph.metadata.SchemaEntry;


/**
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
public class PrefuseFormatConverter
        implements IConverter {


    private static PrefuseFormatConverter s_Instance;

    private Map<String, Long>             graphObjectIDToPrefuseIDMap;

    /**
     * The singleton instance of the IOManager.
     * 
     * @return the singleton instance.
     */
    public static PrefuseFormatConverter Instance() {

        if (s_Instance == null) {
            s_Instance = new PrefuseFormatConverter();
        }

        return s_Instance;
    }

    private PrefuseFormatConverter() {


    }


    @Override
    public boolean supportsConversion(Class<?> sourceType, Class<?> targetType) {

        // lower bound required for source type.
        if (!IUniverse.class.isAssignableFrom(sourceType)
                && !IGraph.class.isAssignableFrom(sourceType)) {

            return false;
        }

        // upper bound required for target type.
        if (!targetType.isAssignableFrom(prefuse.data.Graph.class)) {

            return false;
        }

        return true;
    }



    @Override
    public <T, S> S convert(T documentToConvert, Class<? extends S> returnType)
            throws ConversionException {

        Class<?> documentType = documentToConvert.getClass();
        if (!this.supportsConversion(documentType, returnType)) {

            throw new ConversionException("Conversion from " + documentType
                    + " to " + returnType + " is not supported by "
                    + this.getClass().toString() + "!");
        }

        if (IUniverse.class.isAssignableFrom(documentType)) {
            return returnType.cast(this.convert(IUniverse.class
                    .cast(documentToConvert)));
        }
        return returnType.cast(this.convert(IGraph.class
                .cast(documentToConvert)));
    }



    private prefuse.data.Graph convert(IUniverse universe)
            throws ConversionException {

        Table nodeTable = this.createNodeTable();
        Table edgeTable = this.createEdgeTable();


        if (universe instanceof Universe) {

            Universe realUniverse = (Universe) universe;

            this.populateTable(nodeTable, realUniverse.getNodeSchema());
            this.populateTable(edgeTable, realUniverse.getEdgeSchema());

        }


        this.graphObjectIDToPrefuseIDMap = new LinkedHashMap<>();

        prefuse.data.Graph prefuseGraph = this
                .createGraph(nodeTable, edgeTable);

        for (INode node : universe.getNodes()) {

            this.parseGraphObjects(nodeTable, node);
        }

        for (IEdge edge : universe.getEdges()) {

            this.parseGraphObjects(edgeTable, edge);
        }

        // TODO: undo changes
        // prefuseGraph = new prefuse.data.Graph();
        // Node nodea = prefuseGraph.addNode();
        // nodea.setString("graphViz:ID", "Tester");
        //
        // this.printTable(nodeTable);
        // System.out.println("\n\n)");
        // this.printTable(edgeTable);


        return prefuseGraph;
    }

    private prefuse.data.Graph convert(IGraph graph)
            throws ConversionException {

        Table nodeTable = this.createNodeTable();
        Table edgeTable = this.createEdgeTable();


        if (graph instanceof Graph) {

            Universe realUniverse = (Universe) graph.getUniverse();

            this.populateTable(nodeTable, realUniverse.getNodeSchema());
            this.populateTable(edgeTable, realUniverse.getEdgeSchema());

        }


        this.graphObjectIDToPrefuseIDMap = new LinkedHashMap<>();

        prefuse.data.Graph prefuseGraph = this
                .createGraph(nodeTable, edgeTable);

        for (INode node : graph.getNodes()) {

            this.parseGraphObjects(nodeTable, node);
        }

        for (IEdge edge : graph.getEdges()) {

            this.parseGraphObjects(edgeTable, edge);
        }

        // TODO: undo changes
        // prefuseGraph = new prefuse.data.Graph();
        // Node nodea = prefuseGraph.addNode();
        // nodea.setString("graphViz:ID", "Tester");
        //
        // this.printTable(nodeTable);
        // System.out.println("\n\n)");
        // this.printTable(edgeTable);


        return prefuseGraph;
    }

    private Table createNodeTable() {

        Table nodeTable = new Table();

        nodeTable.addColumn(Constants.PREFUSE_ID, long.class);
        nodeTable.addColumn(Constants.ID, String.class);
        nodeTable.addColumn("graphViz:graph", String.class);
        nodeTable.addColumn("graphViz:type", String.class);

        return nodeTable;
    }

    private Table createEdgeTable() {

        Table edgeTable = new Table();

        edgeTable.addColumn(Constants.PREFUSE_ID, long.class);
        edgeTable.addColumn(Constants.ID, String.class);
        edgeTable.addColumn(Constants.PREFUSE_SOURCE_ID, long.class);
        edgeTable.addColumn(Constants.PREFUSE_TARGET_ID, long.class);
        edgeTable.addColumn(Constants.SOURCE_ID, String.class);
        edgeTable.addColumn(Constants.TARGET_ID, String.class);
        edgeTable.addColumn("graphViz:graph", String.class);
        edgeTable.addColumn("graphViz:type", String.class);

        return edgeTable;
    }

    private prefuse.data.Graph createGraph(Table nodeTable, Table edgeTable) {

        return new prefuse.data.Graph(nodeTable, edgeTable, true,
                Constants.PREFUSE_ID, Constants.PREFUSE_SOURCE_ID,
                Constants.PREFUSE_TARGET_ID);
    }

    private void populateTable(Table table, Schema schema)
            throws ConversionException {


        for (SchemaEntry entry : schema.getEntries()) {

            // System.out.println("Adding schema entry " + entry);
            Object defaultValue = null;
            boolean hasDefaultValue = false;

            if (entry.getDefaultValue() != null) {

                defaultValue = entry.getDefaultValue().getValue();
                hasDefaultValue = true;
            }
            switch (entry.getType()) {
                case BOOLEAN:
                    if (entry.isUnique()) {
                        if (hasDefaultValue) {

                            table.addColumn(entry.getKey(), Boolean.class,
                                    defaultValue);
                        }
                        else {

                            table.addColumn(entry.getKey(), Boolean.class);
                        }
                    }
                    else {
                        if (hasDefaultValue) {

                            List<Boolean> list = new LinkedList<>();
                            list.add((Boolean) defaultValue);
                            table.addColumn(entry.getKey(), List.class, list);
                        }
                        else {

                            table.addColumn(entry.getKey(), List.class);
                        }
                    }
                    break;

                case DOUBLE:
                    if (entry.isUnique()) {
                        if (hasDefaultValue) {

                            table.addColumn(entry.getKey(), Double.class,
                                    defaultValue);
                        }
                        else {

                            table.addColumn(entry.getKey(), Double.class);
                        }
                    }
                    else {
                        if (hasDefaultValue) {

                            List<Double> list = new LinkedList<>();
                            list.add((Double) defaultValue);
                            table.addColumn(entry.getKey(), List.class, list);
                        }
                        else {

                            table.addColumn(entry.getKey(), List.class);
                        }
                    }
                    break;

                case FLOAT:
                    if (entry.isUnique()) {
                        if (hasDefaultValue) {

                            table.addColumn(entry.getKey(), Float.class,
                                    defaultValue);
                        }
                        else {

                            table.addColumn(entry.getKey(), Float.class);
                        }
                    }
                    else {
                        if (hasDefaultValue) {

                            List<Float> list = new LinkedList<>();
                            list.add((Float) defaultValue);
                            table.addColumn(entry.getKey(), List.class, list);
                        }
                        else {

                            table.addColumn(entry.getKey(), List.class);
                        }
                    }
                    break;

                case INTEGER:
                    if (entry.isUnique()) {
                        if (hasDefaultValue) {

                            table.addColumn(entry.getKey(), Integer.class,
                                    defaultValue);
                        }
                        else {

                            table.addColumn(entry.getKey(), Integer.class);
                        }
                    }
                    else {
                        if (hasDefaultValue) {

                            List<Integer> list = new LinkedList<>();
                            list.add((Integer) defaultValue);
                            table.addColumn(entry.getKey(), List.class, list);
                        }
                        else {

                            table.addColumn(entry.getKey(), List.class);
                        }
                    }
                    break;

                case LONG:
                    if (entry.isUnique()) {
                        if (hasDefaultValue) {

                            table.addColumn(entry.getKey(), Long.class,
                                    defaultValue);
                        }
                        else {

                            table.addColumn(entry.getKey(), Long.class);
                        }
                    }
                    else {
                        if (hasDefaultValue) {

                            List<Long> list = new LinkedList<>();
                            list.add((Long) defaultValue);
                            table.addColumn(entry.getKey(), List.class, list);
                        }
                        else {

                            table.addColumn(entry.getKey(), List.class);
                        }
                    }
                    break;

                case STRING:
                    if (entry.isUnique()) {
                        if (hasDefaultValue) {

                            table.addColumn(entry.getKey(), String.class,
                                    defaultValue);
                        }
                        else {

                            table.addColumn(entry.getKey(), String.class);
                        }
                    }
                    else {
                        if (hasDefaultValue) {

                            List<String> list = new LinkedList<>();
                            list.add((String) defaultValue);
                            table.addColumn(entry.getKey(), List.class, list);
                        }
                        else {

                            table.addColumn(entry.getKey(), List.class);
                        }
                    }
                    break;

                default:
                    throw new ConversionException("SchemaEntry "
                            + entry.getKey()
                            + " in Node Schema has invalid type "
                            + entry.getType());

            }
        }
    }


    private void parseGraphObjects(Table table, IGraphObject graphObject) {

        int row = table.addRow();
        // System.out.println("Adding object " + row + ": " +
        // graphObject.getID());
        table.set(row, Constants.ID, graphObject.getID());
        table.set(row, Constants.PREFUSE_ID, row);
        if (table.canGetLong(Constants.PREFUSE_ID)) {
            Long prefuseID = table.getLong(row, Constants.PREFUSE_ID);
            // System.out.println(Constants.PREFUSE_ID+ ": " + prefuseID);

            this.graphObjectIDToPrefuseIDMap
                    .put(graphObject.getID(), prefuseID);
        }


        if (graphObject instanceof IEdge) {

            IEdge edge = (IEdge) graphObject;
            table.set(row, "graphViz:graph", edge.getGraph().getID());
            table.set(row, "graphViz:type", "graphViz:edge");
            // System.out.println("\t\tgraphViz:Source: "
            // + edge.getSource().getID());
            // System.out.println("\t\tgraphViz:Target: "
            // + edge.getTarget().getID());

            table.set(row, Constants.SOURCE_ID, edge.getSource().getID());
            table.set(row, Constants.TARGET_ID, edge.getTarget().getID());

            // System.out.println("\t\tgraphViz:PrefuseSource: "
            // + this.graphObjectIDToPrefuseIDMap.get(edge.getSource()
            // .getID()));
            // System.out.println("\t\tgraphViz:PrefuseTarget: "
            // + this.graphObjectIDToPrefuseIDMap.get(edge.getTarget()
            // .getID()));

            table.set(row, Constants.PREFUSE_SOURCE_ID,
                    this.graphObjectIDToPrefuseIDMap.get(edge.getSource()
                            .getID()));
            table.set(row, Constants.PREFUSE_TARGET_ID,
                    this.graphObjectIDToPrefuseIDMap.get(edge.getTarget()
                            .getID()));
        }
        if (graphObject instanceof INode) {
            INode node = (INode) graphObject;

            table.set(row, "graphViz:graph", node.getGraph().getID());
            table.set(row, "graphViz:type", "graphViz:node");

        }

        if (graphObject instanceof IMetadataContainer) {
            IMetadataContainer container = (IMetadataContainer) graphObject;

            for (String key : container.getMetadataProperty().getKeys()) {

                // System.out.println("\tAdding metadata value for " + key);
                List<Metadata> metadata = container.getMetadataProperty()
                        .getMetadata(key);

                if (!metadata.isEmpty()) {
                    if (container.getMetadataProperty().getSchema()
                            .getEntry(key).isUnique()) {

                        // System.out.println("\t\tSingle entry");
                        // System.out.println("\t\t\tAdding value: "
                        // + metadata.get(0).getValue());
                        table.set(row, key, metadata.get(0).getValue());

                    }
                    else {
                        List<Object> list = new LinkedList<>();

                        // System.out.println("\t\tList entry");
                        for (Metadata metadataValue : metadata) {

                            // System.out.println("\t\t\tAdding value: "
                            // + metadataValue.getValue());
                            list.add(metadataValue.getValue());
                        }
                        table.set(row, key, list);
                    }
                }

            }
        }
    }

    @SuppressWarnings("unused")
    private void printTable(Table table) {

        for (int j = 0; j < table.getColumnCount(); j++) {
            if (j > 0) {
                System.out.print(", ");
            }
            System.out.print(table.getColumnName(j));
        }
        System.out.println();
        for (int i = 0; i < table.getRowCount(); i++) {

            for (int j = 0; j < table.getColumnCount(); j++) {

                if (j > 0) {
                    System.out.print(", ");
                }
                System.out.print(table.get(i, j));
            }
            System.out.println();

        }
    }


}
