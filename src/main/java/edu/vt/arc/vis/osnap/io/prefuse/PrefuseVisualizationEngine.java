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


/**
 *
 */
package edu.vt.arc.vis.osnap.io.prefuse;


import java.util.Iterator;

import org.jutility.io.ConversionException;
import org.jutility.io.IConverter;

import edu.vt.arc.vis.osnap.core.domain.graph.Node;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.layout.common.BaseCoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.CoordinateComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.IPrefuseLayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import prefuse.Visualization;
import prefuse.data.Graph;
import prefuse.data.Tuple;
import prefuse.data.tuple.TupleSet;
import prefuse.visual.NodeItem;



/**
 * @author Peter J. Radics
 * @version 0.1
 */
public class PrefuseVisualizationEngine
        implements IConverter {

    private edu.vt.arc.vis.osnap.core.domain.visualization.Visualization visualization;
    private final IPrefuseLayout               layoutComponent;



    @Override
    public boolean supportsConversion(Class<?> sourceType, Class<?> targetType) {

        // lower bound required for source type.
        if (!Visualization.class.isAssignableFrom(sourceType)) {

            return false;
        }

        // upper bound required for target type.
        if (!targetType
                .isAssignableFrom(edu.vt.arc.vis.osnap.core.domain.visualization.Visualization.class)) {

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

        return returnType.cast(this.convert(Visualization.class
                .cast(documentToConvert)));

    }

    /**
     * Creates a new instance of the {@link PrefuseVisualizationEngine} class.
     *
     * @param layoutComponent
     *            the layout component to apply to the
     *            {@link edu.vt.arc.vis.osnap.core.domain.visualization.Visualization
     *            visualization}.
     */
    public PrefuseVisualizationEngine(IPrefuseLayout layoutComponent) {

        this(layoutComponent, null);
    }

    /**
     * Creates a new instance of the {@link PrefuseVisualizationEngine} class.
     *
     * @param layoutComponent
     *            the layout component to apply to the
     *            {@link edu.vt.arc.vis.osnap.core.domain.visualization.Visualization
     *            visualization}.
     * @param visualization
     *            the {@link edu.vt.arc.vis.osnap.core.domain.visualization.Visualization
     *            visualization} to modify.
     */
    public PrefuseVisualizationEngine(IPrefuseLayout layoutComponent,
            edu.vt.arc.vis.osnap.core.domain.visualization.Visualization visualization) {

        this.layoutComponent = layoutComponent;
        this.visualization = visualization;
    }


    private edu.vt.arc.vis.osnap.core.domain.visualization.Visualization convert(
            Visualization vis)
            throws ConversionException {

        if (this.visualization == null) {

            this.createVisualization(vis);
        }

        this.applyVisualization(vis);

        return this.visualization;
    }


    private void createVisualization(Visualization vis)
            throws ConversionException {

        TupleSet tuple = vis.getGroup("graph");

        if (tuple instanceof Graph) {
            Graph graph = (Graph) tuple;

            // TODO: this /will/ blow up with an UnsupportedOperationException,
            // since the conversion from Prefuse Graph to Universe is not yet
            // implemented!
            Universe universe = PrefuseFormatConverter.Instance().convert(
                    graph, Universe.class);

            this.visualization = new edu.vt.arc.vis.osnap.core.domain.visualization.Visualization(
                    universe);
        }
        else {
            throw new ConversionException(
                    "TupleSet contained in Prefuse Visualization is not a "
                            + "Prefuse Graph! IOManager can only convert "
                            + "Prefuse Graphs.");
        }
    }


    private void applyVisualization(Visualization vis)
            throws ConversionException {

//        System.out.println("\tApplying visualization values");
        Iterator<?> it = vis.items("graph");


        while (it.hasNext()) {

            Object item = it.next();

            if (item instanceof NodeItem) {

                NodeItem nodeItem = (NodeItem) item;

//                System.out.println("NodeItem: " + nodeItem);

                String nodeID = this.getID(nodeItem);
//                System.out.println("NodeID: " + nodeID);

//                System.out.println("Universe nodes: "
//                        + this.visualization.getUniverse().getNodes());
//                System.out.println("Universe nodes: "
//                        + this.visualization.getUniverse().getNodeMap());
//                System.out.println("Visualization nodes: " + this.visualization.getVisualNodes());

                Node iNode = this.visualization.getUniverse().getNode(nodeID);
//                System.out.println("Node: " + iNode);
                VisualNode visualNode = this.visualization.getVisualNode(iNode);


                if (visualNode == null) {
//                    System.out.println("Curious: just dies...");
                    throw new ConversionException("Visual Node with id "
                            + nodeID + " is not part of this visualization!");
                }
                Double x = nodeItem.getX();
                Double y = nodeItem.getY();
                float firstComponent = x.floatValue();
                float secondComponent = y.floatValue();

//                    System.out.println("\t\tTrying to set x, y to " + x + ", "
//                            + y + " for visual node " + visualNode.getID());
                BaseCoordinateLayout
                        .setValue(this.layoutComponent, visualNode,
                                CoordinateComponent.FIRST_COMPONENT,
                                firstComponent);
                BaseCoordinateLayout.setValue(
                        this.layoutComponent, visualNode,
                        CoordinateComponent.SECOND_COMPONENT,
                        secondComponent);

            }
        }

    }

    private String getID(Tuple tuple)
            throws ConversionException {

        if (tuple.canGetString(Constants.ID)) {
            return tuple.getString(Constants.ID);

        }
        throw new ConversionException(
                "Node does not contain edu.vt.arc.vis.osnap.core identifier! "
                        + "Cannot establish link between Prefuse "
                        + "graph and Universe.");
    }



}
