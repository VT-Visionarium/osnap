/**
 * 
 */
package edu.vt.arc.vis.osnap.core.domain.layout.complexComponents;


/*
 * _ The Open Semantic Network Analysis Platform (OSNAP) _ Copyright (C) 2012 -
 * 2014 Visionarium at Virginia Tech _ Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License. _
 */



import static org.junit.Assert.*;

import java.util.ArrayList;

import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraph;
import edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.SphericalLayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;

import org.junit.Before;
import org.junit.Test;


/**
 * @author Shawn P Neuman
 * 
 */
public class SphereLayoutTest
        extends SphericalLayout {

    private ArrayList<ArrayList<VisualNode>> doubleList;

    /**
     * @throws Exception
     */
    @Before
    public void setUp()
            throws Exception {

    }


    private void initialize(int numberOfNodes) {

        Universe uni = new Universe();
        IGraph graph = uni.createGraph("MINE");



        for (int i = 0; i < numberOfNodes; i++) {
            uni.createNode("node " + i, graph);

        }

        Visualization viz = new Visualization(uni);

        this.layout(viz);
    }

    /**
     * Test method for {@link SphericalLayout#getDecrementDegree()}.
     */
    @Test
    public void testSphereLayout() {

        int randomNumber = 6;
        this.initialize(randomNumber);

        this.testGetDecrementDegree(randomNumber);
        this.testGetSphereRadius(randomNumber);

        this.doubleList = this.getDoubleList();
        // testDoubleList();

        testSphereCoordinates();



        System.out.println("\nSuccess!");
    }

    /**
     * 
     */
    private void testSphereCoordinates() {

        for (int i = 0; i < doubleList.size(); i++) {
            for (int k = 0; k < doubleList.get(i).size(); k++) {
                System.out.println("Size at " + i + " is: "
                        + doubleList.get(i).size());
                System.out.println("Coords are: "
                        + doubleList.get(i).get(k).getPosition());
            }
        }

    }


    // private void testDoubleList() {
    //
    // int size = 1;
    // for (int i = 0; i < doubleList.size(); i++) {
    //
    // if (i == 0) {
    // size = 1;
    // assertEquals(size, doubleList.get(i).size());
    // }
    // else if (i < sphere.getNumRings() / 2) {
    // size = (int) Math.pow(2, i + 1);
    // assertEquals(size, doubleList.get(i).size());
    // }
    // else if (i == sphere.getNumRings() / 2) {
    // assertEquals(4, doubleList.get(i).size());
    // }
    // else if (i == sphere.getNumRings() - 1) {
    // size = 1;
    // assertEquals(size, doubleList.get(i).size());
    // }
    // else {
    // size = size / 2;
    // assertEquals(size, doubleList.get(i).size());
    // }
    // }
    // }



    private void testGetDecrementDegree(int randomNumber) {

        double decrement = calculateDecrement(randomNumber);
        assertEquals(decrement, this.getDecrementDegree(), .0001);

    }


    private double calculateDecrement(int randomNumber) {

        if (randomNumber < 3) {
            return Math.toRadians(180);
        }
        else if (randomNumber < 7) {
            return Math.toRadians(90);
        }

        else {
            int numRings = ((int) Math.ceil(Math.log(randomNumber + 6)
                    / Math.log(2)) - 3) * 2 + 2;

            return Math.toRadians(180.0 / (numRings - 1));
        }

    }


    private void testGetSphereRadius(int randomNumber) {

        double radius = calculateRadius(randomNumber);
        assertEquals(radius, this.getSphereRadius(), .0001);

    }

    private double calculateRadius(int randomNumber) {

        if (randomNumber < 3) {
            return 0;
        }
        else if (randomNumber < 7) {
            return (12 / (2 * 3.14159));
        }

        else {
            int nodesOnLastRing = (int) Math.pow(2, (this.getNumberOfRings() / 2));
            // should set arc length to node radius * 3 but for now, this will
            // do.
            double arcLength = 3;
            double circumference = arcLength * nodesOnLastRing;
            double ringRadius = circumference / (2 * 3.1415926);
            double halfDegree = (3.1415926 / 2)
                    - (((this.getNumberOfRings() / 2) - 1) * this
                            .getDecrementDegree());
            double cosine = Math.cos(halfDegree);
            double radius = ringRadius / cosine;

            return radius;
        }
    }


    //
    // /**
    // * test the x, y and z coordinates for accuracy
    // */
    // @Test
    // public void testNodeCoordinates() {
    //
    // System.out.println("\n\nTesting Coordinates");
    // this.initialize(64);
    //
    // ArrayList<VisualNode> list = sphere.getList();
    // for (int i = 0; i < sphere.getList().size(); i++) {
    // System.out.println(list.get(i).getPosition());
    // }
    //
    // System.out.println("\nSuccess!");
    // }



}
