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


package edu.vt.arc.vis.osnap.core.domain.layout.complexComponents;


import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.core.domain.layout.common.Base2DCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.CoordinateComponent;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualGraph;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;


/**
 * @author Shawn P Neuman
 * 
 */
@XmlType(name = "TwoDGridCoordinateLayoutComponent")
public class TwoDGridCoordinateLayoutComponent
        extends Base2DCoordinateLayoutComponent {


    private float xDistanceScale = 1;
    private float yDistanceScale = 1;
    private float zDistanceScale = 1;

    public static String description() {

        String description = "The " + TwoDGridCoordinateLayoutComponent.name()
                + " provides a twi dimensional grid layout of all nodes "
                + " in no particular order. "
                + " nodes are somewhat evenly distributed across "
                + " a grid like shape.";

        return description;
    }

    public static String name() {

        return "2D Grid Layout Component";
    }

    public static Set<VisualProperty> capabilities() {

        return Base2DCoordinateLayoutComponent.capabilities();
    }

    /**
     * constructor
     */
    public TwoDGridCoordinateLayoutComponent() {

        super(TwoDGridCoordinateLayoutComponent.name(),
                TwoDGridCoordinateLayoutComponent.description(), true);
    }


    /**
     * @param nodes
     *            the collection of nodes to visualize
     */
    public void initialize(Collection<VisualNode> nodes) {

        int rows = (int) Math.ceil(Math.sqrt(nodes.size()));
        Iterator<VisualNode> it = nodes.iterator();

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < rows; x++) {
                if (it.hasNext()) {
                    VisualNode node = it.next();
                    Float xpos = (float) (6 * x * getxDistanceScale());
                    Float ypos = (float) (6 * y * getyDistanceScale());

                    this.setValue(node, CoordinateComponent.FIRST_COMPONENT,
                            xpos);
                    this.setValue(node, CoordinateComponent.SECOND_COMPONENT,
                            ypos);


                }
            }
        }


    }


    @Override
    public void layout(Visualization visualization) {

        this.initialize(visualization.getVisualNodes());

    }

    @Override
    public void layout(VisualGraph graph) {

        this.initialize(graph.getVisualNodes());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.domain.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap.core
     * .visualization.VisualNode)
     */
    @Override
    public void layout(VisualNode visualNode) {

        // not used


    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.domain.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap.core
     * .visualization.VisualEdge)
     */
    @Override
    public void layout(VisualEdge visualEdge) {

        // not used

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.domain.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap.core
     * .visualization.VisualHyperEdge)
     */
    @Override
    public void layout(VisualHyperEdge visualHyperEdge) {

        // not used

    }

    /**
     * @return the xDistanceScale
     */
    public float getxDistanceScale() {

        return xDistanceScale;
    }

    /**
     * @param xDistanceScale the xDistanceScale to set
     */
    public void setxDistanceScale(float xDistanceScale) {

        this.xDistanceScale = xDistanceScale;
    }

    /**
     * @return the yDistanceScale
     */
    public float getyDistanceScale() {

        return yDistanceScale;
    }

    /**
     * @param yDistanceScale the yDistanceScale to set
     */
    public void setyDistanceScale(float yDistanceScale) {

        this.yDistanceScale = yDistanceScale;
    }

    /**
     * @return the zDistanceScale
     */
    public float getzDistanceScale() {

        return zDistanceScale;
    }

    /**
     * @param zDistanceScale the zDistanceScale to set
     */
    public void setzDistanceScale(float zDistanceScale) {

        this.zDistanceScale = zDistanceScale;
    }
}
