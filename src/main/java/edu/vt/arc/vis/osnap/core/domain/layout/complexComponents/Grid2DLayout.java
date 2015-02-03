package edu.vt.arc.vis.osnap.core.domain.layout.complexComponents;


//@formatter:off
/*
* _
* The Open Semantic Network Analysis Platform (OSNAP)
* _
* Copyright (C) 2012 - 2015 Visionarium at Virginia Tech
* _
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
*      http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
* _
*/
//@formatter:on


import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.core.domain.layout.common.Base2DCoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.CoordinateComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ICoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualGraph;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;


/**
 * "The {@code Grid2DLayout} provides a two-dimensional grid layout of all nodes
 * in no particular order. Nodes are somewhat evenly distributed across a
 * grid-like shape.
 * 
 * @author Shawn P Neuman
 * @version 1.2.0
 * @since 0.5.0
 */
@XmlType(name = "Grid2DLayout")
public class Grid2DLayout
        extends Base2DCoordinateLayout {


    public static String description() {

        final String description = "The " + Grid2DLayout.name()
                + " provides a two-dimensional grid layout of all nodes "
                + " in no particular order. "
                + " Nodes are somewhat evenly distributed across "
                + " a grid like shape.";

        return description;
    }

    public static String name() {

        return "2D Grid Layout";
    }

    public static Set<VisualProperty> capabilities() {

        return Base2DCoordinateLayout.capabilities();
    }

    /**
     * Returns the coordinate components (the set of {@link CoordinateComponent
     * CoordinateComponents} that can be provided) by this
     * {@link ICoordinateLayout}.
     * 
     * @return the components.
     */
    public static Set<CoordinateComponent> components() {

        return Base2DCoordinateLayout.components();
    }



    /**
     * Creates a new instance of the {@code Grid2DLayout} class.
     */
    public Grid2DLayout() {

        this(1, 1);
    }

    /**
     * Creates a new instance of the {@code Grid2DLayout} class with the
     * provided distance scaling factors.
     *
     * @param firstComponentScale
     *            the scale factor of the first {@link CoordinateComponent}.
     * @param secondComponentScale
     *            the scale factor of the second {@link CoordinateComponent}.
     */
    public Grid2DLayout(final float firstComponentScale,
            final float secondComponentScale) {

        super(Grid2DLayout.name(), Grid2DLayout.description(), true);

        this.setFirstComponentScale(firstComponentScale);
        this.setSecondComponentScale(secondComponentScale);
    }



    private void applyLayout(final Collection<VisualNode> nodes) {

        final int rows = (int) Math.ceil(Math.sqrt(nodes.size()));
        final Iterator<VisualNode> it = nodes.iterator();

        for (int y = 0; y < rows; y++) {

            for (int x = 0; x < rows; x++) {

                if (it.hasNext()) {

                    final VisualNode node = it.next();
                    final Float xpos = (float) (6 * x);
                    final Float ypos = (float) (6 * y);

                    this.setValue(node, CoordinateComponent.FIRST_COMPONENT,
                            xpos);
                    this.setValue(node, CoordinateComponent.SECOND_COMPONENT,
                            ypos);
                }
            }
        }
    }



    @Override
    public void layout(final Visualization visualization) {

        this.applyLayout(visualization.getVisualNodes());

    }

    @Override
    public void layout(final VisualGraph graph) {

        this.applyLayout(graph.getVisualNodes());
    }

    @Override
    public void layout(final VisualNode visualNode) {

        // not used


    }

    @Override
    public void layout(final VisualEdge visualEdge) {

        // not used

    }

    @Override
    public void layout(final VisualHyperEdge visualHyperEdge) {

        // not used

    }
}
