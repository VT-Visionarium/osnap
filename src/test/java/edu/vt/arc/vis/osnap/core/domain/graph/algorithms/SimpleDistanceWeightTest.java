package edu.vt.arc.vis.osnap.core.domain.graph.algorithms;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.graph.algorithms.SimpleDistanceWeight;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IEdge;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraph;
import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;


/**
 * The {@link SimpleDistanceWeightTest} class provides test cases for the
 * {@link SimpleDistanceWeight} class.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */

public class SimpleDistanceWeightTest {


    private Universe             universe;
    private IGraph               graph;
    private INode                rootNode;

    private INode                A;
    private INode                B;
    private INode                C;
    private INode                D;
    private INode                E;
    private INode                F;
    private INode                G;
    private INode                H;
    private INode                I;
    private INode                M;
    private INode                N;
    private INode                O;
    private INode                Q;
    private INode                Z;

    private IEdge                F_B;
    private IEdge                F_O;
    private IEdge                F_G;
    private IEdge                B_A;
    private IEdge                B_D;
    private IEdge                A_M;
    private IEdge                A_N;
    private IEdge                M_Q;
    private IEdge                Q_F;
    private IEdge                D_C;
    private IEdge                D_E;
    private IEdge                G_I;
    private IEdge                I_H;


    private IEdge                H_Q;
    private IEdge                A_D;
    private IEdge                M_G;
    private IEdge                E_O;
    private IEdge                B_I;
    private IEdge                N_E;
    private IEdge                F_Z;
    private IEdge                H_F;

    private SimpleDistanceWeight simpleDistanceWeight;
    private SimpleDistanceWeight simpleDistanceWeightDirected;
    private SimpleDistanceWeight simpleDistanceWeightDirectedInverted;
    private SimpleDistanceWeight otherDistanceWeight;
    private SimpleDistanceWeight otherDistanceWeightDirected;
    private SimpleDistanceWeight otherDistanceWeightDirectedInverted;


    /**
     * Creates all elements required for testing.
     */
    @Before
    public void setUp() {

        this.universe = new Universe();
        this.graph = universe.createGraph("the Graph");

        this.rootNode = universe.createNode("F", this.graph);
        F = this.rootNode;

        B = universe.createNode("B", this.graph);
        O = universe.createNode("O", this.graph);
        G = universe.createNode("G", this.graph);

        A = universe.createNode("A", this.graph);
        D = universe.createNode("D", this.graph);
        I = universe.createNode("I", this.graph);

        M = universe.createNode("M", this.graph);
        N = universe.createNode("N", this.graph);
        C = universe.createNode("C", this.graph);
        E = universe.createNode("E", this.graph);
        H = universe.createNode("H", this.graph);

        Q = universe.createNode("Q", this.graph);

        Z = universe.createNode("Z", this.graph);

        F_B = universe.createEdge("F->B", this.graph, F, B);
        F_O = universe.createEdge("F->O", this.graph, F, O);
        F_G = universe.createEdge("F->G", this.graph, F, G);
        B_A = universe.createEdge("B->A", this.graph, B, A);
        B_D = universe.createEdge("B->D", this.graph, B, D);
        A_M = universe.createEdge("A->M", this.graph, A, M);
        A_N = universe.createEdge("A->N", this.graph, A, N);
        M_Q = universe.createEdge("M->Q", this.graph, M, Q);
        D_C = universe.createEdge("D->C", this.graph, D, C);
        D_E = universe.createEdge("D->E", this.graph, D, E);
        G_I = universe.createEdge("G->I", this.graph, G, I);
        I_H = universe.createEdge("I->H", this.graph, I, H);
        Q_F = universe.createEdge("Q->F", this.graph, Q, F);

        System.out.println("Undirected small:");
        simpleDistanceWeight = new SimpleDistanceWeight(F, universe, false,
                false);
        simpleDistanceWeight.getWeight(rootNode);
        System.out.println("Directed small:");
        simpleDistanceWeightDirected = new SimpleDistanceWeight(F, universe,
                true, false);
        simpleDistanceWeightDirected.getWeight(rootNode);
        System.out.println("Directed reverse small:");
        simpleDistanceWeightDirectedInverted = new SimpleDistanceWeight(F,
                universe, true, true);
        simpleDistanceWeightDirectedInverted.getWeight(rootNode);

        H_Q = universe.createEdge("H->Q", this.graph, H, Q);
        A_D = universe.createEdge("A->D", this.graph, A, D);
        M_G = universe.createEdge("M->G", this.graph, M, G);
        E_O = universe.createEdge("E->O", this.graph, E, O);
        B_I = universe.createEdge("B->I", this.graph, B, I);
        N_E = universe.createEdge("N->E", this.graph, N, E);
        F_Z = universe.createEdge("F->Z", this.graph, F, Z);
        H_F = universe.createEdge("H->F", this.graph, H, F);

        System.out.println("Undirected large:");
        otherDistanceWeight = new SimpleDistanceWeight(F, universe, false,
                false);
        otherDistanceWeight.getWeight(rootNode);
        System.out.println("Directed large:");
        otherDistanceWeightDirected = new SimpleDistanceWeight(F, universe,
                true, false);
        otherDistanceWeightDirected.getWeight(rootNode);
        System.out.println("Directed reversed large:");
        otherDistanceWeightDirectedInverted = new SimpleDistanceWeight(F,
                universe, true, true);
        otherDistanceWeightDirectedInverted.getWeight(rootNode);
    }

    /**
     * Test method for
     * {@link edu.vt.arc.vis.osnap.core.domain.graph.algorithms.SimpleDistanceWeight#getWeight(edu.vt.arc.vis.osnap.core.domain.graph.common.IEdge)}
     * .
     */
    @Test
    public void testGetWeightIEdge() {

        for (IEdge edge : this.graph.getEdges()) {
            switch (edge.getId()) {
                case ("F->B"):
                    assertEquals(new Integer(1),
                            simpleDistanceWeight.getWeight(F_B));
                    assertEquals(new Integer(1),
                            simpleDistanceWeightDirected.getWeight(F_B));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(F_B));
                    assertEquals(new Integer(1),
                            otherDistanceWeight.getWeight(F_B));
                    assertEquals(new Integer(1),
                            otherDistanceWeightDirected.getWeight(F_B));
                    assertNull(otherDistanceWeightDirectedInverted
                            .getWeight(F_B));
                    break;
                case ("F->O"):
                    assertEquals(new Integer(1),
                            simpleDistanceWeight.getWeight(F_O));
                    assertEquals(new Integer(1),
                            simpleDistanceWeightDirected.getWeight(F_O));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(F_O));
                    assertEquals(new Integer(1),
                            otherDistanceWeight.getWeight(F_O));
                    assertEquals(new Integer(1),
                            otherDistanceWeightDirected.getWeight(F_O));
                    assertNull(otherDistanceWeightDirectedInverted
                            .getWeight(F_O));
                    break;
                case ("F->G"):
                    assertEquals(new Integer(1),
                            simpleDistanceWeight.getWeight(F_G));
                    assertEquals(new Integer(1),
                            simpleDistanceWeightDirected.getWeight(F_G));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(F_G));
                    assertEquals(new Integer(1),
                            otherDistanceWeight.getWeight(F_G));
                    assertEquals(new Integer(1),
                            otherDistanceWeightDirected.getWeight(F_G));
                    assertNull(otherDistanceWeightDirectedInverted
                            .getWeight(F_G));
                    break;
                case ("B->A"):
                    assertEquals(new Integer(2),
                            simpleDistanceWeight.getWeight(B_A));
                    assertEquals(new Integer(2),
                            simpleDistanceWeightDirected.getWeight(B_A));
                    assertEquals(new Integer(4),
                            simpleDistanceWeightDirectedInverted.getWeight(B_A));
                    assertEquals(new Integer(2),
                            otherDistanceWeight.getWeight(B_A));
                    assertEquals(new Integer(2),
                            otherDistanceWeightDirected.getWeight(B_A));
                    assertNull(otherDistanceWeightDirectedInverted
                            .getWeight(B_A));
                    break;
                case ("B->D"):
                    assertEquals(new Integer(2),
                            simpleDistanceWeight.getWeight(B_D));
                    assertEquals(new Integer(2),
                            simpleDistanceWeightDirected.getWeight(B_D));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(B_D));
                    assertEquals(new Integer(2),
                            otherDistanceWeight.getWeight(B_D));
                    assertEquals(new Integer(2),
                            otherDistanceWeightDirected.getWeight(B_D));
                    assertNull(otherDistanceWeightDirectedInverted
                            .getWeight(B_D));
                    break;
                case ("A->M"):
                    assertNull(simpleDistanceWeight.getWeight(A_M));
                    assertEquals(new Integer(3),
                            simpleDistanceWeightDirected.getWeight(A_M));
                    assertEquals(new Integer(3),
                            simpleDistanceWeightDirectedInverted.getWeight(A_M));
                    assertNull(otherDistanceWeight.getWeight(A_M));
                    assertEquals(new Integer(3),
                            otherDistanceWeightDirected.getWeight(A_M));
                    assertEquals(new Integer(3),
                            otherDistanceWeightDirectedInverted.getWeight(A_M));
                    break;
                case ("A->N"):
                    assertEquals(new Integer(3),
                            simpleDistanceWeight.getWeight(A_N));
                    assertEquals(new Integer(3),
                            simpleDistanceWeightDirected.getWeight(A_N));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(A_N));
                    assertEquals(new Integer(3),
                            otherDistanceWeight.getWeight(A_N));
                    assertEquals(new Integer(3),
                            otherDistanceWeightDirected.getWeight(A_N));
                    assertNull(otherDistanceWeightDirectedInverted
                            .getWeight(A_N));
                    break;
                case ("M->Q"):
                    assertEquals(new Integer(2),
                            simpleDistanceWeight.getWeight(M_Q));
                    assertEquals(new Integer(4),
                            simpleDistanceWeightDirected.getWeight(M_Q));
                    assertEquals(new Integer(2),
                            simpleDistanceWeightDirectedInverted.getWeight(M_Q));
                    assertNull(otherDistanceWeight.getWeight(M_Q));
                    assertEquals(new Integer(4),
                            otherDistanceWeightDirected.getWeight(M_Q));
                    assertEquals(new Integer(2),
                            otherDistanceWeightDirectedInverted.getWeight(M_Q));
                    break;
                case ("D->C"):
                    assertEquals(new Integer(3),
                            simpleDistanceWeight.getWeight(D_C));
                    assertEquals(new Integer(3),
                            simpleDistanceWeightDirected.getWeight(D_C));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(D_C));
                    assertEquals(new Integer(3),
                            otherDistanceWeight.getWeight(D_C));
                    assertEquals(new Integer(3),
                            otherDistanceWeightDirected.getWeight(D_C));
                    assertNull(otherDistanceWeightDirectedInverted
                            .getWeight(D_C));
                    break;
                case ("D->E"):
                    assertEquals(new Integer(3),
                            simpleDistanceWeight.getWeight(D_E));
                    assertEquals(new Integer(3),
                            simpleDistanceWeightDirected.getWeight(D_E));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(D_E));
                    assertNull(otherDistanceWeight.getWeight(D_E));
                    assertEquals(new Integer(3),
                            otherDistanceWeightDirected.getWeight(D_E));
                    assertNull(otherDistanceWeightDirectedInverted
                            .getWeight(D_E));
                    break;
                case ("G->I"):
                    assertEquals(new Integer(2),
                            simpleDistanceWeight.getWeight(G_I));
                    assertEquals(new Integer(2),
                            simpleDistanceWeightDirected.getWeight(G_I));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(G_I));
                    assertNull(otherDistanceWeight.getWeight(G_I));
                    assertNull(otherDistanceWeightDirected.getWeight(G_I));
                    assertEquals(new Integer(3),
                            otherDistanceWeightDirectedInverted.getWeight(G_I));
                    break;
                case ("I->H"):
                    assertEquals(new Integer(3),
                            simpleDistanceWeight.getWeight(I_H));
                    assertEquals(new Integer(3),
                            simpleDistanceWeightDirected.getWeight(I_H));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(I_H));
                    assertNull(otherDistanceWeight.getWeight(I_H));
                    assertEquals(new Integer(3),
                            otherDistanceWeightDirected.getWeight(I_H));
                    assertEquals(new Integer(2),
                            otherDistanceWeightDirectedInverted.getWeight(I_H));
                    break;
                case ("Q->F"):
                    assertEquals(new Integer(1),
                            simpleDistanceWeight.getWeight(Q_F));
                    assertNull(simpleDistanceWeightDirected.getWeight(Q_F));
                    assertEquals(new Integer(1),
                            simpleDistanceWeightDirectedInverted.getWeight(Q_F));
                    assertEquals(new Integer(1),
                            otherDistanceWeight.getWeight(Q_F));
                    assertNull(otherDistanceWeightDirected.getWeight(Q_F));
                    assertEquals(new Integer(1),
                            otherDistanceWeightDirectedInverted.getWeight(Q_F));
                    break;

                // bpp

                case ("H->Q"):
                    assertNull(simpleDistanceWeight.getWeight(H_Q));
                    assertNull(simpleDistanceWeightDirected.getWeight(H_Q));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(H_Q));
                    assertNull(otherDistanceWeight.getWeight(H_Q));
                    assertNull(otherDistanceWeightDirected.getWeight(H_Q));
                    assertNull(otherDistanceWeightDirectedInverted
                            .getWeight(H_Q));
                    break;
                case ("A->D"):
                    assertNull(simpleDistanceWeight.getWeight(A_D));
                    assertNull(simpleDistanceWeightDirected.getWeight(A_D));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(A_D));
                    assertNull(otherDistanceWeight.getWeight(A_D));
                    assertNull(otherDistanceWeightDirected.getWeight(A_D));
                    assertNull(otherDistanceWeightDirectedInverted
                            .getWeight(A_D));
                    break;
                case ("M->G"):
                    assertNull(simpleDistanceWeight.getWeight(M_G));
                    assertNull(simpleDistanceWeightDirected.getWeight(M_G));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(M_G));
                    assertEquals(new Integer(2),
                            otherDistanceWeight.getWeight(M_G));
                    assertNull(otherDistanceWeightDirected.getWeight(M_G));
                    assertNull(otherDistanceWeightDirectedInverted
                            .getWeight(M_G));
                    break;
                case ("E->O"):
                    assertNull(simpleDistanceWeight.getWeight(E_O));
                    assertNull(simpleDistanceWeightDirected.getWeight(E_O));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(E_O));
                    assertEquals(new Integer(2),
                            otherDistanceWeight.getWeight(E_O));
                    assertNull(otherDistanceWeightDirected.getWeight(E_O));
                    assertNull(otherDistanceWeightDirectedInverted
                            .getWeight(E_O));
                    break;
                case ("B->I"):
                    assertNull(simpleDistanceWeight.getWeight(B_I));
                    assertNull(simpleDistanceWeightDirected.getWeight(B_I));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(B_I));
                    assertEquals(new Integer(2),
                            otherDistanceWeight.getWeight(B_I));
                    assertEquals(new Integer(2),
                            otherDistanceWeightDirected.getWeight(B_I));
                    assertEquals(new Integer(3),
                            otherDistanceWeightDirectedInverted.getWeight(B_I));
                    break;
                case ("N->E"):
                    assertNull(simpleDistanceWeight.getWeight(N_E));
                    assertNull(simpleDistanceWeightDirected.getWeight(N_E));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(N_E));
                    assertNull(otherDistanceWeight.getWeight(N_E));
                    assertNull(otherDistanceWeightDirected.getWeight(N_E));
                    assertNull(otherDistanceWeightDirectedInverted
                            .getWeight(N_E));
                    break;
                case ("F->Z"):
                    assertNull(simpleDistanceWeight.getWeight(F_Z));
                    assertNull(simpleDistanceWeightDirected.getWeight(F_Z));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(F_Z));
                    assertEquals(new Integer(1),
                            otherDistanceWeight.getWeight(F_Z));
                    assertEquals(new Integer(1),
                            otherDistanceWeightDirected.getWeight(F_Z));
                    assertNull(otherDistanceWeightDirectedInverted
                            .getWeight(F_Z));
                    break;
                case ("H->F"):
                    assertNull(simpleDistanceWeight.getWeight(H_F));
                    assertNull(simpleDistanceWeightDirected.getWeight(H_F));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(H_F));
                    assertEquals(new Integer(1),
                            otherDistanceWeight.getWeight(H_F));
                    assertNull(otherDistanceWeightDirected.getWeight(H_F));
                    assertEquals(new Integer(1),
                            otherDistanceWeightDirectedInverted.getWeight(H_F));
                    break;

            }
        }
    }

    /**
     * Test method for
     * {@link edu.vt.arc.vis.osnap.core.domain.graph.algorithms.SimpleDistanceWeight#getWeight(edu.vt.arc.vis.osnap.core.domain.graph.common.INode)}
     * .
     */
    @Test
    public void testGetWeightINode() {


        for (INode node : this.graph.getNodes()) {
            switch (node.getId()) {

                case "A":
                    assertEquals(new Integer(2),
                            simpleDistanceWeight.getWeight(A));
                    assertEquals(new Integer(2),
                            simpleDistanceWeightDirected.getWeight(A));
                    assertEquals(new Integer(3),
                            simpleDistanceWeightDirectedInverted.getWeight(A));
                    assertEquals(new Integer(2),
                            otherDistanceWeight.getWeight(A));
                    assertEquals(new Integer(2),
                            otherDistanceWeightDirected.getWeight(A));
                    assertEquals(new Integer(3),
                            otherDistanceWeightDirectedInverted.getWeight(A));
                    break;
                case "B":
                    assertEquals(new Integer(1),
                            simpleDistanceWeight.getWeight(B));
                    assertEquals(new Integer(1),
                            simpleDistanceWeightDirected.getWeight(B));
                    assertEquals(new Integer(4),
                            simpleDistanceWeightDirectedInverted.getWeight(B));
                    assertEquals(new Integer(1),
                            otherDistanceWeight.getWeight(B));
                    assertEquals(new Integer(1),
                            otherDistanceWeightDirected.getWeight(B));
                    assertEquals(new Integer(3),
                            otherDistanceWeightDirectedInverted.getWeight(B));
                    break;
                case "C":
                    assertEquals(new Integer(3),
                            simpleDistanceWeight.getWeight(C));
                    assertEquals(new Integer(3),
                            simpleDistanceWeightDirected.getWeight(C));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(C));
                    assertEquals(new Integer(3),
                            otherDistanceWeight.getWeight(C));
                    assertEquals(new Integer(3),
                            otherDistanceWeightDirected.getWeight(C));
                    assertNull(otherDistanceWeightDirectedInverted.getWeight(C));
                    break;
                case "D":
                    assertEquals(new Integer(2),
                            simpleDistanceWeight.getWeight(D));
                    assertEquals(new Integer(2),
                            simpleDistanceWeightDirected.getWeight(D));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(D));
                    assertEquals(new Integer(2),
                            otherDistanceWeight.getWeight(D));
                    assertEquals(new Integer(2),
                            otherDistanceWeightDirected.getWeight(D));
                    assertNull(otherDistanceWeightDirectedInverted.getWeight(D));
                    break;
                case "E":
                    assertEquals(new Integer(3),
                            simpleDistanceWeight.getWeight(E));
                    assertEquals(new Integer(3),
                            simpleDistanceWeightDirected.getWeight(E));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(E));
                    assertEquals(new Integer(2),
                            otherDistanceWeight.getWeight(E));
                    assertEquals(new Integer(3),
                            otherDistanceWeightDirected.getWeight(E));
                    assertNull(otherDistanceWeightDirectedInverted.getWeight(E));
                    break;
                case "F":
                    assertEquals(new Integer(0),
                            simpleDistanceWeight.getWeight(F));
                    assertEquals(new Integer(0),
                            simpleDistanceWeightDirected.getWeight(F));
                    assertEquals(new Integer(0),
                            simpleDistanceWeightDirectedInverted.getWeight(F));
                    assertEquals(new Integer(0),
                            otherDistanceWeight.getWeight(F));
                    assertEquals(new Integer(0),
                            otherDistanceWeightDirected.getWeight(F));
                    assertEquals(new Integer(0),
                            otherDistanceWeightDirectedInverted.getWeight(F));
                    break;
                case "G":
                    assertEquals(new Integer(1),
                            simpleDistanceWeight.getWeight(G));
                    assertEquals(new Integer(1),
                            simpleDistanceWeightDirected.getWeight(G));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(G));
                    assertEquals(new Integer(1),
                            otherDistanceWeight.getWeight(G));
                    assertEquals(new Integer(1),
                            otherDistanceWeightDirected.getWeight(G));
                    assertEquals(new Integer(3),
                            otherDistanceWeightDirectedInverted.getWeight(G));
                    break;
                case "H":
                    assertEquals(new Integer(3),
                            simpleDistanceWeight.getWeight(H));
                    assertEquals(new Integer(3),
                            simpleDistanceWeightDirected.getWeight(H));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(H));
                    assertEquals(new Integer(1),
                            otherDistanceWeight.getWeight(H));
                    assertEquals(new Integer(3),
                            otherDistanceWeightDirected.getWeight(H));
                    assertEquals(new Integer(1),
                            otherDistanceWeightDirectedInverted.getWeight(H));
                    break;
                case "I":
                    assertEquals(new Integer(2),
                            simpleDistanceWeight.getWeight(I));
                    assertEquals(new Integer(2),
                            simpleDistanceWeightDirected.getWeight(I));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(I));
                    assertEquals(new Integer(2),
                            otherDistanceWeight.getWeight(I));
                    assertEquals(new Integer(2),
                            otherDistanceWeightDirected.getWeight(I));
                    assertEquals(new Integer(2),
                            otherDistanceWeightDirectedInverted.getWeight(I));
                    break;
                case "M":
                    assertEquals(new Integer(2),
                            simpleDistanceWeight.getWeight(M));
                    assertEquals(new Integer(3),
                            simpleDistanceWeightDirected.getWeight(M));
                    assertEquals(new Integer(2),
                            simpleDistanceWeightDirectedInverted.getWeight(M));
                    assertEquals(new Integer(2),
                            otherDistanceWeight.getWeight(M));
                    assertEquals(new Integer(3),
                            otherDistanceWeightDirected.getWeight(M));
                    assertEquals(new Integer(2),
                            otherDistanceWeightDirectedInverted.getWeight(M));
                    break;
                case "N":
                    assertEquals(new Integer(3),
                            simpleDistanceWeight.getWeight(N));
                    assertEquals(new Integer(3),
                            simpleDistanceWeightDirected.getWeight(N));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(N));
                    assertEquals(new Integer(3),
                            otherDistanceWeight.getWeight(N));
                    assertEquals(new Integer(3),
                            otherDistanceWeightDirected.getWeight(N));
                    assertNull(otherDistanceWeightDirectedInverted.getWeight(N));
                    break;
                case "O":
                    assertEquals(new Integer(1),
                            simpleDistanceWeight.getWeight(O));
                    assertEquals(new Integer(1),
                            simpleDistanceWeightDirected.getWeight(O));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(O));
                    assertEquals(new Integer(1),
                            otherDistanceWeight.getWeight(O));
                    assertEquals(new Integer(1),
                            otherDistanceWeightDirected.getWeight(O));
                    assertNull(otherDistanceWeightDirectedInverted.getWeight(O));
                    break;
                case "Q":
                    assertEquals(new Integer(1),
                            simpleDistanceWeight.getWeight(Q));
                    assertEquals(new Integer(4),
                            simpleDistanceWeightDirected.getWeight(Q));
                    assertEquals(new Integer(1),
                            simpleDistanceWeightDirectedInverted.getWeight(Q));
                    assertEquals(new Integer(1),
                            otherDistanceWeight.getWeight(Q));
                    assertEquals(new Integer(4),
                            otherDistanceWeightDirected.getWeight(Q));
                    assertEquals(new Integer(1),
                            otherDistanceWeightDirectedInverted.getWeight(Q));
                    break;
                case "Z":
                    assertNull(simpleDistanceWeight.getWeight(Z));
                    assertNull(simpleDistanceWeightDirected.getWeight(Z));
                    assertNull(simpleDistanceWeightDirectedInverted
                            .getWeight(Z));
                    assertEquals(new Integer(1),
                            otherDistanceWeight.getWeight(Z));
                    assertEquals(new Integer(1),
                            otherDistanceWeightDirected.getWeight(Z));
                    assertNull(otherDistanceWeightDirectedInverted.getWeight(Z));
            }
        }
    }

}
