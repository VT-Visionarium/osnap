
//=======
///*******************************************************************************
// * Copyright 2014 Virginia Tech Visionarium
// * 
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// * 
// *   http://www.apache.org/licenses/LICENSE-2.0
// * 
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// ******************************************************************************/


package edu.vt.arc.vis.osnap.core.domain.layout.complexComponents;


import edu.vt.arc.vis.osnap.core.domain.layout.common.Base3DCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.BaseCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.BaseLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.CoordinateComponent;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualGraph;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.xml.bind.annotation.XmlType;


/**
 * Simple layout to place all nodes in an evenly distributed sphere
 * 
 * @author Shawn P Neuman
 * 
 */
@XmlType(name = "SphereCoordinateLayoutComponent")
public class SphereCoordinateLayoutComponent
        extends Base3DCoordinateLayoutComponent {

    private int                              numRings;
    private double                           decrementDegree;
    private double                           sphereRadius;
    private final double                     pi     = Math.PI;
    private float                            xScale = 1;
    private float                            yScale = 1;
    private float                            zScale = 1;

    private ArrayList<ArrayList<VisualNode>> doubleList;


    public static String description() {

        String description = "The " + SphereCoordinateLayoutComponent.name()
                + " provides a spherical layout of all nodes "
                + " in no particular order. "
                + " nodes are somewhat evenly distributed across "
                + " increasing radii parallel rings starting at the top.";

        return description;
    }

    public static String name() {

        return "Spherical Layout Component";
    }


    /**
     * 
     */
    public SphereCoordinateLayoutComponent() {

        super(SphereCoordinateLayoutComponent.name(),
                SphereCoordinateLayoutComponent.description(), true);
    }

    public static Set<VisualProperty> capabilities() {

        return Base3DCoordinateLayoutComponent.capabilities();
    }

    /**
     * this method uses a double array list
     * 
     * @param nodes
     *            the collection of nodes being added to the list
     */
    public void init3(Collection<VisualNode> nodes) {


        Set<VisualNode> restrictedNodes = BaseLayoutComponent.calculateRestrictedSet(this, nodes); 

//        System.out.println("Restricted node list size: " + restrictedNodes.size());
        doubleList = new ArrayList<>();

        setNumRings(restrictedNodes.size());

        int rings = getNumRings();


        for (int i = 0; i < rings; i++) {
            doubleList.add(new ArrayList<VisualNode>());
        }

        int start = 0;
        int stop = rings;
        int stepCounter = 4;
        Iterator<VisualNode> it = restrictedNodes.iterator();

        int stopNode;
        if (rings < 4) {
            stopNode = 4;
        }
        else {
            stopNode = (int) Math.pow(2, rings / 2);
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
                    doubleList.get(i).add(it.next());
                }
                else {
                    break;
                }
            }
        }

        setDecrementDegree(rings);
        setSphereRadius(rings);

        setNodeCoordinates2();

    }

    /**
     * 
     */
    private void setNodeCoordinates2() {

        double theta = 0;
        double phi = 0;
        double r = this.getSphereRadius();

//        System.out.println("setting node Coords for Sphere");
//        System.out.println("x scale is: " + getxScale());
//        System.out.println("y scale is: " + getyScale());
//        System.out.println("z scale is: " + getzScale());


        for (int i = 0; i < doubleList.size(); i++) {
            for (int k = 0; k < doubleList.get(i).size(); k++) {
                phi = i * getDecrementDegree();
                int nodesToSet = doubleList.get(i).size();
                theta = Math.toRadians(360.0 / nodesToSet);
                float xpos = (float) (r * Math.cos((i * theta) + (k * theta)) * Math
                        .sin(phi));
                xpos *= getxScale();
                float ypos = (float) (r * Math.sin((i * theta) + (k * theta)) * Math
                        .sin(phi));
                ypos *= getyScale();
                float zpos = (float) (r * Math.cos(phi));
                zpos *= getzScale();

                BaseCoordinateLayoutComponent.setValue(this, this.doubleList
                        .get(i).get(k), CoordinateComponent.FIRST_COMPONENT,
                        xpos);
                BaseCoordinateLayoutComponent.setValue(this, this.doubleList
                        .get(i).get(k), CoordinateComponent.SECOND_COMPONENT,
                        ypos);
                BaseCoordinateLayoutComponent.setValue(this, this.doubleList
                        .get(i).get(k), CoordinateComponent.THIRD_COMPONENT,
                        zpos);
            }
        }
    }



    /**
     * @return the double array list
     */
    public ArrayList<ArrayList<VisualNode>> getDoubleList() {

        return this.doubleList;
    }



    // finds the total number of rings to place nodes on
    // includes the vertical axis 0 and 180
    private void setNumRings(int numNodes) {

//        System.out.println("num nodes = " + numNodes);
        if (numNodes < 3) {
            this.numRings = 2;
        }
        else if (numNodes <= 6) {
            this.numRings = 3;
        }
        else {
            this.numRings = ((int) Math.ceil(Math.log(numNodes + 6)
                    / Math.log(2)) - 3) * 2 + 2;
        }
//        System.out.println("num rings = " + numRings);


    }



    /**
     * @return the number of rings
     */
    public int getNumRings() {

        return this.numRings;
    }



    /**
     * @return the decrementDegree
     */
    public double getDecrementDegree() {

        return decrementDegree;
    }

    /**
     * @param numRings
     *            the number of rings.
     */
    public void setDecrementDegree(int numRings) {

        if (numRings > 1)
            this.decrementDegree = Math.toRadians(180.0 / (numRings - 1));
    }



    /**
     * @return the sphereRadius
     */
    public double getSphereRadius() {

        return sphereRadius;
    }

    /**
     * using the number of rings we can find the radius of the sphere assumes
     * node radius of 1;
     * 
     * @param numRings
     *            the number of rings between 0 and 90 degrees
     */
    public void setSphereRadius(int numRings) {

        if (numRings < 3) {
            this.sphereRadius = 0;
        }
        else if (numRings < 4) {
            this.sphereRadius = 12 / (2 * pi);
        }
        else {
            int nodesOnLastRing = (int) Math.pow(2, (numRings / 2));
            // should set arc length to node radius * 3 but for now, this will
            // do.
            double arcLength = 3;
            double circumference = arcLength * nodesOnLastRing;
            double ringRadius = circumference / (2 * pi);
            double halfDegree = (pi / 2)
                    - (((numRings / 2) - 1) * getDecrementDegree());
            double cosine = Math.cos(halfDegree);

            this.sphereRadius = ringRadius / cosine;
        }
    }



    @Override
    public void layout(Visualization visualization) {

        this.init3(visualization.getVisualNodes());

    }

    @Override
    public void layout(VisualGraph graph) {

        this.init3(graph.getVisualNodes());
    }

    @Override
    public void layout(VisualNode visualNode) {

        // not used.
    }

    @Override
    public void layout(VisualEdge visualEdge) {

        // not used.
    }

    @Override
    public void layout(VisualHyperEdge visualHyperEdge) {

        // not used.
    }

    /**
     * @return the xScale
     */
    public float getxScale() {

        return xScale;
    }

    /**
     * @param xScale
     *            the xScale to set
     */
    public void setxScale(float xScale) {

        this.xScale = xScale;
    }

    /**
     * @return the yScale
     */
    public float getyScale() {

        return yScale;
    }

    /**
     * @param yScale
     *            the yScale to set
     */
    public void setyScale(float yScale) {

        this.yScale = yScale;
    }

    /**
     * @return the zScale
     */
    public float getzScale() {

        return zScale;
    }

    /**
     * @param zScale
     *            the zScale to set
     */
    public void setzScale(float zScale) {

        this.zScale = zScale;
    }

}
