package edu.vt.arc.vis.osnap.core.domain.layout.complexComponents;


//@formatter:off
/*
* _
* The Open Semantic Network Analysis Platform (OSNAP)
* _
* Copyright (C) 2012 - 2014 Visionarium at Virginia Tech
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.core.domain.layout.common.Base3DCoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.BaseCoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.BaseLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.CoordinateComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ICoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualGraph;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;


/**
 * The {@code SphericalLayout} places all nodes in an evenly distributed sphere.
 *
 * @author Shawn P Neuman
 * @version 1.2.3
 * @since 0.5.0
 */
@XmlType(name = "SphericalLayout")
public class SphericalLayout
        extends Base3DCoordinateLayout {

    private int                              numberOfRings;
    private double                           decrementDegree;
    private double                           sphereRadius;

    private ArrayList<ArrayList<VisualNode>> doubleList;

    /**
     * Returns the double list.
     * 
     * @return the double list.
     */
    protected ArrayList<ArrayList<VisualNode>> getDoubleList() {

        return this.doubleList;
    }

    /**
     * Returns the decrement degree.
     * 
     * @return the decrement degree.
     */
    protected double getDecrementDegree() {

        return this.decrementDegree;
    }


    /**
     * Returns the sphere radius.
     * 
     * @return the sphere radius.
     */
    protected double getSphereRadius() {

        return this.sphereRadius;
    }


    /**
     * Returns the number of ring the number of rings.\
     * 
     * @return the numberOfRings.
     */
    protected int getNumberOfRings() {

        return this.numberOfRings;
    }


    public static String description() {

        final String description = "The " + SphericalLayout.name()
                + " provides a spherical layout of all nodes "
                + " in no particular order. "
                + " nodes are somewhat evenly distributed across "
                + " increasing radii parallel rings starting at the top.";

        return description;
    }

    public static String name() {

        return "Spherical Layout";
    }

    public static Set<VisualProperty> capabilities() {

        return Base3DCoordinateLayout.capabilities();
    }

    /**
     * Returns the coordinate components (the set of {@link CoordinateComponent
     * CoordinateComponents} that can be provided) by this
     * {@link ICoordinateLayout}.
     * 
     * @return the components.
     */
    public static Set<CoordinateComponent> components() {

        return Base3DCoordinateLayout.components();
    }



    /**
     * Creates a new instance of the {@code SphericalLayout} class.
     */
    public SphericalLayout() {

        this(1, 1, 1);
    }

    /**
     * Creates a new instance of the {@code SphericalLayout} class with the
     * provided scalings.
     * 
     * @param firstComponentScale
     *            the scale factor of the first {@link CoordinateComponent}.
     * @param secondComponentScale
     *            the scale factor of the second {@link CoordinateComponent}.
     * @param thirdComponentScale
     *            the scale factor of the third {@link CoordinateComponent}.
     */
    public SphericalLayout(final float firstComponentScale,
            final float secondComponentScale, final float thirdComponentScale) {

        super(SphericalLayout.name(), SphericalLayout.description(), true);

        this.setFirstComponentScale(firstComponentScale);
        this.setSecondComponentScale(secondComponentScale);
        this.setThirdComponentScale(thirdComponentScale);
    }



    private void applyLayout(final Collection<VisualNode> nodes) {


        final Set<VisualNode> restrictedNodes = BaseLayout
                .calculateRestrictedSet(this, nodes);

        // System.out.println("Restricted node list size: " +
        // restrictedNodes.size());
        this.doubleList = new ArrayList<>();

        this.calculateNumberOfRings(restrictedNodes.size());


        for (int i = 0; i < this.numberOfRings; i++) {

            this.doubleList.add(new ArrayList<VisualNode>());
        }

        int start = 0;
        int stop = this.numberOfRings;
        int stepCounter = 4;
        final Iterator<VisualNode> it = restrictedNodes.iterator();

        int stopNode;
        if (this.numberOfRings < 4) {

            stopNode = 4;
        }
        else {

            stopNode = (int) Math.pow(2, this.numberOfRings / 2);
        }


        for (int j = 0; j < stopNode; j++) {

            if (!it.hasNext()) {

                break;
            }
            if (j == 1) {

                start++;
                stop--;
            }
            else if (j == stepCounter) {

                start++;
                stop--;
                stepCounter = stepCounter * 2;

            }
            for (int i = start; i < stop; i++) {

                if (it.hasNext()) {

                    this.doubleList.get(i).add(it.next());
                }
                else {

                    break;
                }
            }
        }

        this.calculateDecrementDegree();
        this.calculateSphereRadius();

        this.setNodeCoordinates();
    }


    private void setNodeCoordinates() {

        double theta = 0;
        double phi = 0;
        final double r = this.sphereRadius;

        for (int i = 0; i < this.doubleList.size(); i++) {
            for (int k = 0; k < this.doubleList.get(i).size(); k++) {
                phi = i * this.decrementDegree;
                final int nodesToSet = this.doubleList.get(i).size();
                theta = Math.toRadians(360.0 / nodesToSet);
                float xpos = (float) (r * Math.cos((i * theta) + (k * theta)) * Math
                        .sin(phi));
                float ypos = (float) (r * Math.sin((i * theta) + (k * theta)) * Math
                        .sin(phi));
                float zpos = (float) (r * Math.cos(phi));

                BaseCoordinateLayout.setValue(this,
                        this.doubleList.get(i).get(k),
                        CoordinateComponent.FIRST_COMPONENT, xpos);
                BaseCoordinateLayout.setValue(this,
                        this.doubleList.get(i).get(k),
                        CoordinateComponent.SECOND_COMPONENT, ypos);
                BaseCoordinateLayout.setValue(this,
                        this.doubleList.get(i).get(k),
                        CoordinateComponent.THIRD_COMPONENT, zpos);
            }
        }
    }



    // finds the total number of rings to place nodes on
    // includes the vertical axis 0 and 180
    private void calculateNumberOfRings(final int numberOfNodes) {

        // System.out.println("num nodes = " + numNodes);
        if (numberOfNodes < 3) {

            this.numberOfRings = 2;
        }
        else if (numberOfNodes <= 6) {

            this.numberOfRings = 3;
        }
        else {

            this.numberOfRings = (((int) Math.ceil(Math.log(numberOfNodes + 6)
                    / Math.log(2)) - 3) * 2) + 2;
        }
        // System.out.println("num rings = " + numRings);
    }


    private void calculateDecrementDegree() {

        if (this.numberOfRings > 1) {

            this.decrementDegree = Math
                    .toRadians(180.0 / (this.numberOfRings - 1));
        }
    }


    private void calculateSphereRadius() {

        if (this.numberOfRings < 3) {

            this.sphereRadius = 0;
        }
        else if (this.numberOfRings < 4) {

            this.sphereRadius = 12 / (2 * Math.PI);
        }
        else {

            final int nodesOnLastRing = (int) Math.pow(2,
                    (this.numberOfRings / 2));
            // should set arc length to node radius * 3 but for now, this will
            // do.
            final double arcLength = 3;
            final double circumference = arcLength * nodesOnLastRing;
            final double ringRadius = circumference / (2 * Math.PI);
            final double halfDegree = (Math.PI / 2)
                    - (((this.numberOfRings / 2) - 1) * this.decrementDegree);
            final double cosine = Math.cos(halfDegree);

            this.sphereRadius = ringRadius / cosine;
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

        // not used.
    }

    @Override
    public void layout(final VisualEdge visualEdge) {

        // not used.
    }

    @Override
    public void layout(final VisualHyperEdge visualHyperEdge) {

        // not used.
    }
}
