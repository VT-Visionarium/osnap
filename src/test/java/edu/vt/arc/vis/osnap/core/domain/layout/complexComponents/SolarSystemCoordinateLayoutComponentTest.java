package edu.vt.arc.vis.osnap.core.domain.layout.complexComponents;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.jutility.common.datatype.tree.PreorderTreeIterator;
import org.jutility.common.datatype.tree.Tree;
import org.jutility.common.datatype.tree.TreeNode;

import edu.vt.arc.vis.osnap.core.domain.graph.Edge;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.graph.algorithms.IWeight;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IEdge;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraph;
import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.BooleanMetadata;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.IMetadataContainer;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Metadata;
import edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.TieredOrbitalLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.TieredOrbitalLayout.ComponentNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;


/**
 * The {@link SolarSystemCoordinateLayoutComponentTest} class provides tests for
 * the {@link TieredOrbitalLayout} class.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
public class SolarSystemCoordinateLayoutComponentTest {

    private Universe                             universe;
    private IGraph                               graph;
    private INode                                rootNode;

    private INode                                A;
    private INode                                B;
    private INode                                C;
    private INode                                D;
    private INode                                E;
    private INode                                F;
    private INode                                G;
    private INode                                H;
    private INode                                I;
    private INode                                M;
    private INode                                N;
    private INode                                O;
    private INode                                Q;
    private INode                                Z;

    private Metadata                             metadata;

    private float                                minimalDistance;
    private float                                maximumNodeSize;
    private float                                depthModifier;

    private TieredOrbitalLayout smallSolarSystemLayout;
    private TieredOrbitalLayout smallSolarSystemLayoutWithMetadata;
    private TieredOrbitalLayout smallSolarSystemLayoutInverted;
    private TieredOrbitalLayout smallSolarSystemLayoutWithMetadataInverted;
    private TieredOrbitalLayout largeSolarSystemLayout;
    private TieredOrbitalLayout largeSolarSystemLayoutWithMetadata;
    private TieredOrbitalLayout largeSolarSystemLayoutInverted;
    private TieredOrbitalLayout largeSolarSystemLayoutWithMetadataInverted;

    /**
     * Sets up the testing harness.
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

        universe.createEdge("F->B", this.graph, F, B);
        universe.createEdge("F->O", this.graph, F, O);
        universe.createEdge("F->G", this.graph, F, G);
        universe.createEdge("B->A", this.graph, B, A);
        universe.createEdge("B->D", this.graph, B, D);
        universe.createEdge("A->M", this.graph, A, M);
        universe.createEdge("A->N", this.graph, A, N);
        universe.createEdge("M->Q", this.graph, M, Q);
        universe.createEdge("D->C", this.graph, D, C);
        universe.createEdge("D->E", this.graph, D, E);
        universe.createEdge("G->I", this.graph, G, I);
        universe.createEdge("I->H", this.graph, I, H);
        universe.createEdge("Q->F", this.graph, Q, F);


        Metadata criterion = new BooleanMetadata("isImportant", true);

        this.metadata = criterion;

        this.minimalDistance = 1f;
        this.maximumNodeSize = 1f;
        this.depthModifier = 3f;

        for (IEdge edge : universe.getEdges()) {
            System.out.println("Edge " + edge);
            if (edge instanceof IMetadataContainer) {
                IMetadataContainer container = (IMetadataContainer) edge;
                switch (edge.getId()) {
                    case "F->B":
                    case "F->O":
                    case "F->G":
                    case "B->A":
                    case "B->D":
                    case "A->M":
                    case "A->N":
                    case "M->Q":
                    case "D->C":
                    case "D->E":
                    case "G->I":
                    case "I->H":
                        container.getMetadataProperty().addMetadata(criterion);
                        break;
                    default:
                        // Nothing to be done.
                        break;
                }
            }
        }
        System.out.println("What the what?");
        this.smallSolarSystemLayout = new TieredOrbitalLayout(
                this.rootNode, null, this.minimalDistance,
                this.maximumNodeSize, this.depthModifier, false);
        this.smallSolarSystemLayout.layout(new Visualization(universe));

        this.smallSolarSystemLayoutWithMetadata = new TieredOrbitalLayout(
                this.rootNode, this.metadata, this.minimalDistance,
                this.maximumNodeSize, this.depthModifier, false);
        this.smallSolarSystemLayoutWithMetadata.layout(new Visualization(
                universe));

        this.smallSolarSystemLayoutInverted = new TieredOrbitalLayout(
                this.rootNode, null, this.minimalDistance,
                this.maximumNodeSize, this.depthModifier, true);
        this.smallSolarSystemLayoutInverted.layout(new Visualization(universe));

        this.smallSolarSystemLayoutWithMetadataInverted = new TieredOrbitalLayout(
                this.rootNode, this.metadata, this.minimalDistance,
                this.maximumNodeSize, this.depthModifier, true);
        this.smallSolarSystemLayoutWithMetadataInverted
                .layout(new Visualization(universe));


        universe.createEdge("H->Q", this.graph, H, Q);
        universe.createEdge("A->D", this.graph, A, D);
        universe.createEdge("M->G", this.graph, M, G);
        universe.createEdge("E->O", this.graph, E, O);
        universe.createEdge("B->I", this.graph, B, I);
        universe.createEdge("N->E", this.graph, N, E);
        Edge F_Z = universe.createEdge("F->Z", this.graph, F, Z);
        Edge H_F = universe.createEdge("H->F", this.graph, H, F);

        F_Z.getMetadataProperty().addMetadata(criterion);
        H_F.getMetadataProperty().addMetadata(criterion);

        this.largeSolarSystemLayout = new TieredOrbitalLayout(
                this.rootNode, null, this.minimalDistance,
                this.maximumNodeSize, this.depthModifier, false);
        this.largeSolarSystemLayout.layout(new Visualization(universe));

        this.largeSolarSystemLayoutWithMetadata = new TieredOrbitalLayout(
                this.rootNode, this.metadata, this.minimalDistance,
                this.maximumNodeSize, this.depthModifier, false);
        this.largeSolarSystemLayoutWithMetadata.layout(new Visualization(
                universe));

        this.largeSolarSystemLayoutInverted = new TieredOrbitalLayout(
                this.rootNode, null, this.minimalDistance,
                this.maximumNodeSize, this.depthModifier, true);
        this.largeSolarSystemLayoutInverted.layout(new Visualization(universe));

        this.largeSolarSystemLayoutWithMetadataInverted = new TieredOrbitalLayout(
                this.rootNode, this.metadata, this.minimalDistance,
                this.maximumNodeSize, this.depthModifier, true);
        this.largeSolarSystemLayoutWithMetadataInverted
                .layout(new Visualization(universe));

    }


    /**
     * Test method for
     * {@link edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.TieredOrbitalLayout#getMetadata()}
     * .
     */
    @Test
    public void testGetMetadata() {

        assertNull(smallSolarSystemLayout.getMetadata());
        assertEquals(this.metadata,
                smallSolarSystemLayoutWithMetadata.getMetadata());
        assertNull(smallSolarSystemLayoutInverted.getMetadata());
        assertEquals(this.metadata,
                smallSolarSystemLayoutWithMetadataInverted.getMetadata());

        assertNull(largeSolarSystemLayout.getMetadata());
        assertEquals(this.metadata,
                largeSolarSystemLayoutWithMetadata.getMetadata());
        assertNull(largeSolarSystemLayoutInverted.getMetadata());
        assertEquals(this.metadata,
                largeSolarSystemLayoutWithMetadataInverted.getMetadata());
    }

    /**
     * Test method for
     * {@link edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.TieredOrbitalLayout#getRootNode()}
     * .
     */
    @Test
    public void testGetRootNode() {

        assertEquals(this.rootNode, smallSolarSystemLayout.getRootNode());
        assertEquals(this.rootNode,
                smallSolarSystemLayoutWithMetadata.getRootNode());
        assertEquals(this.rootNode,
                smallSolarSystemLayoutInverted.getRootNode());
        assertEquals(this.rootNode,
                smallSolarSystemLayoutWithMetadataInverted.getRootNode());

        assertEquals(this.rootNode, largeSolarSystemLayout.getRootNode());
        assertEquals(this.rootNode,
                largeSolarSystemLayoutWithMetadata.getRootNode());
        assertEquals(this.rootNode,
                largeSolarSystemLayoutInverted.getRootNode());
        assertEquals(this.rootNode,
                largeSolarSystemLayoutWithMetadataInverted.getRootNode());
    }

    /**
     * Test method for
     * {@link edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.TieredOrbitalLayout#getWeight()}
     * .
     */
    @Test
    public void testGetShortestPath() {

        IWeight smallWeight = smallSolarSystemLayout.getWeight();
        IWeight smallWeightWithMetadata = smallSolarSystemLayoutWithMetadata
                .getWeight();
        IWeight smallWeightInverted = smallSolarSystemLayoutInverted
                .getWeight();
        IWeight smallWeightWithMetadataInverted = smallSolarSystemLayoutWithMetadataInverted
                .getWeight();
        IWeight largeWeight = largeSolarSystemLayout.getWeight();
        IWeight largeWeightWithMetadata = largeSolarSystemLayoutWithMetadata
                .getWeight();
        IWeight largeWeightInverted = largeSolarSystemLayoutInverted
                .getWeight();
        IWeight largeWeightWithMetadataInverted = largeSolarSystemLayoutWithMetadataInverted
                .getWeight();


        for (INode node : this.universe.getNodes()) {

            PreorderTreeIterator<INode> it = smallSolarSystemLayout
                    .getMinimumSpanningTree().preorderIterator();

            TreeNode<INode> smallTreeNode = null;
            while (it.hasNext()) {
                if (it.hasNext()) {
                    INode mstNode = it.next();
                    if (mstNode.equals(node)) {
                        smallTreeNode = it.getTreeNode();
                        break;
                    }
                }
            }
            it = smallSolarSystemLayoutWithMetadata.getMinimumSpanningTree()
                    .preorderIterator();

            TreeNode<INode> smallTreeNodeWithMetadata = null;
            while (it.hasNext()) {
                if (it.hasNext()) {
                    INode mstNode = it.next();
                    if (mstNode.equals(node)) {
                        smallTreeNodeWithMetadata = it.getTreeNode();
                        break;
                    }
                }
            }
            it = smallSolarSystemLayoutInverted.getMinimumSpanningTree()
                    .preorderIterator();

            TreeNode<INode> smallTreeNodeInverted = null;
            while (it.hasNext()) {
                if (it.hasNext()) {
                    INode mstNode = it.next();
                    if (mstNode.equals(node)) {
                        smallTreeNodeInverted = it.getTreeNode();
                        break;
                    }
                }
            }
            it = smallSolarSystemLayoutWithMetadataInverted
                    .getMinimumSpanningTree().preorderIterator();

            TreeNode<INode> smallTreeNodeWithMetadataInverted = null;
            while (it.hasNext()) {
                if (it.hasNext()) {
                    INode mstNode = it.next();
                    if (mstNode.equals(node)) {
                        smallTreeNodeWithMetadataInverted = it.getTreeNode();
                        break;
                    }
                }
            }


            it = largeSolarSystemLayout.getMinimumSpanningTree()
                    .preorderIterator();

            TreeNode<INode> largeTreeNode = null;
            while (it.hasNext()) {
                if (it.hasNext()) {
                    INode mstNode = it.next();
                    if (mstNode.equals(node)) {
                        largeTreeNode = it.getTreeNode();
                        break;
                    }
                }
            }
            it = largeSolarSystemLayoutWithMetadata.getMinimumSpanningTree()
                    .preorderIterator();

            TreeNode<INode> largeTreeNodeWithMetadata = null;
            while (it.hasNext()) {
                if (it.hasNext()) {
                    INode mstNode = it.next();
                    if (mstNode.equals(node)) {
                        largeTreeNodeWithMetadata = it.getTreeNode();
                        break;
                    }
                }
            }
            it = largeSolarSystemLayoutInverted.getMinimumSpanningTree()
                    .preorderIterator();

            TreeNode<INode> largeTreeNodeInverted = null;
            while (it.hasNext()) {
                if (it.hasNext()) {
                    INode mstNode = it.next();
                    if (mstNode.equals(node)) {
                        largeTreeNodeInverted = it.getTreeNode();
                        break;
                    }
                }
            }
            it = largeSolarSystemLayoutWithMetadataInverted
                    .getMinimumSpanningTree().preorderIterator();

            TreeNode<INode> largeTreeNodeWithMetadataInverted = null;
            while (it.hasNext()) {
                if (it.hasNext()) {
                    INode mstNode = it.next();
                    if (mstNode.equals(node)) {
                        largeTreeNodeWithMetadataInverted = it.getTreeNode();
                        break;
                    }
                }
            }

            switch (node.getId()) {

                case "A":
                    assertEquals(new Integer(2), smallWeight.getWeight(node));
                    assertEquals(new Integer(2),
                            new Integer(smallTreeNode.depth()));

                    assertEquals(new Integer(2),
                            smallWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(2), new Integer(
                            smallTreeNodeWithMetadata.depth()));

                    assertEquals(new Integer(3),
                            smallWeightInverted.getWeight(node));
                    assertEquals(new Integer(3), new Integer(
                            smallTreeNodeInverted.depth()));

                    assertNull(smallWeightWithMetadataInverted.getWeight(node));
                    assertNull(smallTreeNodeWithMetadataInverted);



                    assertEquals(new Integer(2), largeWeight.getWeight(node));
                    assertEquals(new Integer(2),
                            new Integer(largeTreeNode.depth()));

                    assertEquals(new Integer(2),
                            largeWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(2), new Integer(
                            largeTreeNodeWithMetadata.depth()));

                    assertEquals(new Integer(3),
                            largeWeightInverted.getWeight(node));
                    assertEquals(new Integer(3), new Integer(
                            largeTreeNodeInverted.depth()));

                    assertNull(largeWeightWithMetadataInverted.getWeight(node));
                    assertNull(largeTreeNodeWithMetadataInverted);
                    break;
                case "B":
                    assertEquals(new Integer(1), smallWeight.getWeight(node));
                    assertEquals(new Integer(1),
                            new Integer(smallTreeNode.depth()));

                    assertEquals(new Integer(1),
                            smallWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(1), new Integer(
                            smallTreeNodeWithMetadata.depth()));

                    assertEquals(new Integer(4),
                            smallWeightInverted.getWeight(node));
                    assertEquals(new Integer(4), new Integer(
                            smallTreeNodeInverted.depth()));

                    assertNull(smallWeightWithMetadataInverted.getWeight(node));
                    assertNull(smallTreeNodeWithMetadataInverted);



                    assertEquals(new Integer(1), largeWeight.getWeight(node));
                    assertEquals(new Integer(1),
                            new Integer(largeTreeNode.depth()));

                    assertEquals(new Integer(1),
                            largeWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(1), new Integer(
                            largeTreeNodeWithMetadata.depth()));

                    assertEquals(new Integer(3),
                            largeWeightInverted.getWeight(node));
                    assertEquals(new Integer(3), new Integer(
                            largeTreeNodeInverted.depth()));

                    assertNull(largeWeightWithMetadataInverted.getWeight(node));
                    assertNull(largeTreeNodeWithMetadataInverted);
                    break;
                case "C":
                    assertEquals(new Integer(3), smallWeight.getWeight(node));
                    assertEquals(new Integer(3),
                            new Integer(smallTreeNode.depth()));

                    assertEquals(new Integer(3),
                            smallWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(3), new Integer(
                            smallTreeNodeWithMetadata.depth()));

                    assertNull(smallWeightInverted.getWeight(node));
                    assertNull(smallTreeNodeInverted);

                    assertNull(smallWeightWithMetadataInverted.getWeight(node));
                    assertNull(smallTreeNodeWithMetadataInverted);



                    assertEquals(new Integer(3), largeWeight.getWeight(node));
                    assertEquals(new Integer(3),
                            new Integer(largeTreeNode.depth()));

                    assertEquals(new Integer(3),
                            largeWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(3), new Integer(
                            largeTreeNodeWithMetadata.depth()));

                    assertNull(largeWeightInverted.getWeight(node));
                    assertNull(largeTreeNodeInverted);

                    assertNull(largeWeightWithMetadataInverted.getWeight(node));
                    assertNull(largeTreeNodeWithMetadataInverted);
                    break;
                case "D":
                    assertEquals(new Integer(2), smallWeight.getWeight(node));
                    assertEquals(new Integer(2),
                            new Integer(smallTreeNode.depth()));

                    assertEquals(new Integer(2),
                            smallWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(2), new Integer(
                            smallTreeNodeWithMetadata.depth()));

                    assertNull(smallWeightInverted.getWeight(node));
                    assertNull(smallTreeNodeInverted);

                    assertNull(smallWeightWithMetadataInverted.getWeight(node));
                    assertNull(smallTreeNodeWithMetadataInverted);



                    assertEquals(new Integer(2), largeWeight.getWeight(node));
                    assertEquals(new Integer(2),
                            new Integer(largeTreeNode.depth()));

                    assertEquals(new Integer(2),
                            largeWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(2), new Integer(
                            largeTreeNodeWithMetadata.depth()));

                    assertNull(largeWeightInverted.getWeight(node));
                    assertNull(largeTreeNodeInverted);

                    assertNull(largeWeightWithMetadataInverted.getWeight(node));
                    assertNull(largeTreeNodeWithMetadataInverted);
                    break;
                case "E":
                    assertEquals(new Integer(3), smallWeight.getWeight(node));
                    assertEquals(new Integer(3),
                            new Integer(smallTreeNode.depth()));

                    assertEquals(new Integer(3),
                            smallWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(3), new Integer(
                            smallTreeNodeWithMetadata.depth()));

                    assertNull(smallWeightInverted.getWeight(node));
                    assertNull(smallTreeNodeInverted);

                    assertNull(smallWeightWithMetadataInverted.getWeight(node));
                    assertNull(smallTreeNodeWithMetadataInverted);



                    assertEquals(new Integer(3), largeWeight.getWeight(node));
                    assertEquals(new Integer(3),
                            new Integer(largeTreeNode.depth()));

                    assertEquals(new Integer(3),
                            largeWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(3), new Integer(
                            largeTreeNodeWithMetadata.depth()));

                    assertNull(largeWeightInverted.getWeight(node));
                    assertNull(largeTreeNodeInverted);

                    assertNull(largeWeightWithMetadataInverted.getWeight(node));
                    assertNull(largeTreeNodeWithMetadataInverted);
                    break;
                case "F":
                    assertEquals(new Integer(0), smallWeight.getWeight(node));
                    assertEquals(new Integer(0),
                            new Integer(smallTreeNode.depth()));

                    assertEquals(new Integer(0),
                            smallWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(0), new Integer(
                            smallTreeNodeWithMetadata.depth()));

                    assertEquals(new Integer(0),
                            smallWeightInverted.getWeight(node));
                    assertEquals(new Integer(0), new Integer(
                            smallTreeNodeInverted.depth()));

                    assertEquals(new Integer(0),
                            smallWeightWithMetadataInverted.getWeight(node));
                    assertEquals(new Integer(0), new Integer(
                            smallTreeNodeWithMetadataInverted.depth()));



                    assertEquals(new Integer(0), largeWeight.getWeight(node));
                    assertEquals(new Integer(0),
                            new Integer(largeTreeNode.depth()));

                    assertEquals(new Integer(0),
                            largeWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(0), new Integer(
                            largeTreeNodeWithMetadata.depth()));

                    assertEquals(new Integer(0),
                            largeWeightInverted.getWeight(node));
                    assertEquals(new Integer(0), new Integer(
                            largeTreeNodeInverted.depth()));

                    assertEquals(new Integer(0),
                            largeWeightWithMetadataInverted.getWeight(node));
                    assertEquals(new Integer(0), new Integer(
                            largeTreeNodeWithMetadataInverted.depth()));
                    break;
                case "G":
                    assertEquals(new Integer(1), smallWeight.getWeight(node));
                    assertEquals(new Integer(1),
                            new Integer(smallTreeNode.depth()));

                    assertEquals(new Integer(1),
                            smallWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(1), new Integer(
                            smallTreeNodeWithMetadata.depth()));

                    assertNull(smallWeightInverted.getWeight(node));
                    assertNull(smallTreeNodeInverted);

                    assertNull(smallWeightWithMetadataInverted.getWeight(node));
                    assertNull(smallTreeNodeWithMetadataInverted);



                    assertEquals(new Integer(1), largeWeight.getWeight(node));
                    assertEquals(new Integer(1),
                            new Integer(largeTreeNode.depth()));

                    assertEquals(new Integer(1),
                            largeWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(1), new Integer(
                            largeTreeNodeWithMetadata.depth()));

                    assertEquals(new Integer(3),
                            largeWeightInverted.getWeight(node));
                    assertEquals(new Integer(3), new Integer(
                            largeTreeNodeInverted.depth()));

                    assertEquals(new Integer(3),
                            largeWeightWithMetadataInverted.getWeight(node));
                    assertEquals(new Integer(3), new Integer(
                            largeTreeNodeWithMetadataInverted.depth()));
                    break;
                case "H":
                    assertEquals(new Integer(3), smallWeight.getWeight(node));
                    assertEquals(new Integer(3),
                            new Integer(smallTreeNode.depth()));

                    assertEquals(new Integer(3),
                            smallWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(3), new Integer(
                            smallTreeNodeWithMetadata.depth()));

                    assertNull(smallWeightInverted.getWeight(node));
                    assertNull(smallTreeNodeInverted);

                    assertNull(smallWeightWithMetadataInverted.getWeight(node));
                    assertNull(smallTreeNodeWithMetadataInverted);



                    assertEquals(new Integer(3), largeWeight.getWeight(node));
                    assertEquals(new Integer(3),
                            new Integer(largeTreeNode.depth()));

                    assertEquals(new Integer(3),
                            largeWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(3), new Integer(
                            largeTreeNodeWithMetadata.depth()));

                    assertEquals(new Integer(1),
                            largeWeightInverted.getWeight(node));
                    assertEquals(new Integer(1), new Integer(
                            largeTreeNodeInverted.depth()));

                    assertEquals(new Integer(1),
                            largeWeightWithMetadataInverted.getWeight(node));
                    assertEquals(new Integer(1), new Integer(
                            largeTreeNodeWithMetadataInverted.depth()));
                    break;
                case "I":
                    assertEquals(new Integer(2), smallWeight.getWeight(node));
                    assertEquals(new Integer(2),
                            new Integer(smallTreeNode.depth()));

                    assertEquals(new Integer(2),
                            smallWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(2), new Integer(
                            smallTreeNodeWithMetadata.depth()));

                    assertNull(smallWeightInverted.getWeight(node));
                    assertNull(smallTreeNodeInverted);

                    assertNull(smallWeightWithMetadataInverted.getWeight(node));
                    assertNull(smallTreeNodeWithMetadataInverted);



                    assertEquals(new Integer(2), largeWeight.getWeight(node));
                    assertEquals(new Integer(2),
                            new Integer(largeTreeNode.depth()));

                    assertEquals(new Integer(2),
                            largeWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(2), new Integer(
                            largeTreeNodeWithMetadata.depth()));

                    assertEquals(new Integer(2),
                            largeWeightInverted.getWeight(node));
                    assertEquals(new Integer(2), new Integer(
                            largeTreeNodeInverted.depth()));

                    assertEquals(new Integer(2),
                            largeWeightWithMetadataInverted.getWeight(node));
                    assertEquals(new Integer(2), new Integer(
                            largeTreeNodeWithMetadataInverted.depth()));
                    break;
                case "M":
                    assertEquals(new Integer(3), smallWeight.getWeight(node));
                    assertEquals(new Integer(3),
                            new Integer(smallTreeNode.depth()));

                    assertEquals(new Integer(3),
                            smallWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(3), new Integer(
                            smallTreeNodeWithMetadata.depth()));

                    assertEquals(new Integer(2),
                            smallWeightInverted.getWeight(node));
                    assertEquals(new Integer(2), new Integer(
                            smallTreeNodeInverted.depth()));

                    assertNull(smallWeightWithMetadataInverted.getWeight(node));
                    assertNull(smallTreeNodeWithMetadataInverted);



                    assertEquals(new Integer(3), largeWeight.getWeight(node));
                    assertEquals(new Integer(3),
                            new Integer(largeTreeNode.depth()));

                    assertEquals(new Integer(3),
                            largeWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(3), new Integer(
                            largeTreeNodeWithMetadata.depth()));

                    assertEquals(new Integer(2),
                            largeWeightInverted.getWeight(node));
                    assertEquals(new Integer(2), new Integer(
                            largeTreeNodeInverted.depth()));

                    assertNull(largeWeightWithMetadataInverted.getWeight(node));
                    assertNull(largeTreeNodeWithMetadataInverted);
                    break;
                case "N":
                    assertEquals(new Integer(3), smallWeight.getWeight(node));
                    assertEquals(new Integer(3),
                            new Integer(smallTreeNode.depth()));

                    assertEquals(new Integer(3),
                            smallWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(3), new Integer(
                            smallTreeNodeWithMetadata.depth()));

                    assertNull(smallWeightInverted.getWeight(node));
                    assertNull(smallTreeNodeInverted);

                    assertNull(smallWeightWithMetadataInverted.getWeight(node));
                    assertNull(smallTreeNodeWithMetadataInverted);



                    assertEquals(new Integer(3), largeWeight.getWeight(node));
                    assertEquals(new Integer(3),
                            new Integer(largeTreeNode.depth()));

                    assertEquals(new Integer(3),
                            largeWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(3), new Integer(
                            largeTreeNodeWithMetadata.depth()));

                    assertNull(largeWeightInverted.getWeight(node));
                    assertNull(largeTreeNodeInverted);

                    assertNull(largeWeightWithMetadataInverted.getWeight(node));
                    assertNull(largeTreeNodeWithMetadataInverted);
                    break;
                case "O":
                    assertEquals(new Integer(1), smallWeight.getWeight(node));
                    assertEquals(new Integer(1),
                            new Integer(smallTreeNode.depth()));

                    assertEquals(new Integer(1),
                            smallWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(1), new Integer(
                            smallTreeNodeWithMetadata.depth()));

                    assertNull(smallWeightInverted.getWeight(node));
                    assertNull(smallTreeNodeInverted);

                    assertNull(smallWeightWithMetadataInverted.getWeight(node));
                    assertNull(smallTreeNodeWithMetadataInverted);



                    assertEquals(new Integer(1), largeWeight.getWeight(node));
                    assertEquals(new Integer(1),
                            new Integer(largeTreeNode.depth()));

                    assertEquals(new Integer(1),
                            largeWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(1), new Integer(
                            largeTreeNodeWithMetadata.depth()));

                    assertNull(largeWeightInverted.getWeight(node));
                    assertNull(largeTreeNodeInverted);

                    assertNull(largeWeightWithMetadataInverted.getWeight(node));
                    assertNull(largeTreeNodeWithMetadataInverted);
                    break;
                case "Q":
                    assertEquals(new Integer(4), smallWeight.getWeight(node));
                    assertEquals(new Integer(4),
                            new Integer(smallTreeNode.depth()));

                    assertEquals(new Integer(4),
                            smallWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(4), new Integer(
                            smallTreeNodeWithMetadata.depth()));

                    assertEquals(new Integer(1),
                            smallWeightInverted.getWeight(node));
                    assertEquals(new Integer(1), new Integer(
                            smallTreeNodeInverted.depth()));

                    assertNull(smallWeightWithMetadataInverted.getWeight(node));
                    assertNull(smallTreeNodeWithMetadataInverted);



                    assertEquals(new Integer(4), largeWeight.getWeight(node));
                    assertEquals(new Integer(4),
                            new Integer(largeTreeNode.depth()));

                    assertEquals(new Integer(4),
                            largeWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(4), new Integer(
                            largeTreeNodeWithMetadata.depth()));

                    assertEquals(new Integer(1),
                            largeWeightInverted.getWeight(node));
                    assertEquals(new Integer(1), new Integer(
                            largeTreeNodeInverted.depth()));

                    assertNull(largeWeightWithMetadataInverted.getWeight(node));
                    assertNull(largeTreeNodeWithMetadataInverted);
                    break;
                case "Z":
                    assertNull(smallWeight.getWeight(node));
                    assertNull(smallTreeNode);

                    assertNull(smallWeightWithMetadata.getWeight(node));
                    assertNull(smallTreeNodeWithMetadata);

                    assertNull(smallWeightInverted.getWeight(node));
                    assertNull(smallTreeNodeInverted);

                    assertNull(smallWeightWithMetadataInverted.getWeight(node));
                    assertNull(smallTreeNodeWithMetadataInverted);



                    assertEquals(new Integer(1), largeWeight.getWeight(node));
                    assertEquals(new Integer(1),
                            new Integer(largeTreeNode.depth()));

                    assertEquals(new Integer(1),
                            largeWeightWithMetadata.getWeight(node));
                    assertEquals(new Integer(1), new Integer(
                            largeTreeNodeWithMetadata.depth()));

                    assertNull(largeWeightInverted.getWeight(node));
                    assertNull(largeTreeNodeInverted);

                    assertNull(largeWeightWithMetadataInverted.getWeight(node));
                    assertNull(largeTreeNodeWithMetadataInverted);
            }


        }
    }

    /**
     * Test method for
     * {@link edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.TieredOrbitalLayout#getMinimalDistance()}
     * .
     */
    @Test
    public void testGetMinimalDistance() {

        assertEquals(this.minimalDistance,
                smallSolarSystemLayout.getMinimalDistance(), 0.0001f);
        assertEquals(this.minimalDistance,
                smallSolarSystemLayoutWithMetadata.getMinimalDistance(),
                0.0001f);
        assertEquals(this.minimalDistance,
                smallSolarSystemLayoutInverted.getMinimalDistance(), 0.0001f);
        assertEquals(
                this.minimalDistance,
                smallSolarSystemLayoutWithMetadataInverted.getMinimalDistance(),
                0.0001f);

        assertEquals(this.minimalDistance,
                largeSolarSystemLayout.getMinimalDistance(), 0.0001f);
        assertEquals(this.minimalDistance,
                largeSolarSystemLayoutWithMetadata.getMinimalDistance(),
                0.0001f);
        assertEquals(this.minimalDistance,
                largeSolarSystemLayoutInverted.getMinimalDistance(), 0.0001f);
        assertEquals(
                this.minimalDistance,
                largeSolarSystemLayoutWithMetadataInverted.getMinimalDistance(),
                0.0001f);
    }

    /**
     * Test method for
     * {@link edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.TieredOrbitalLayout#getMaximumNodeRadius()}
     * .
     */
    @Test
    public void testGetMaximumNodeSize() {

        assertEquals(this.maximumNodeSize,
                smallSolarSystemLayout.getMaximumNodeRadius(), 0.0001f);
        assertEquals(this.maximumNodeSize,
                smallSolarSystemLayoutWithMetadata.getMaximumNodeRadius(),
                0.0001f);
        assertEquals(this.maximumNodeSize,
                smallSolarSystemLayoutInverted.getMaximumNodeRadius(), 0.0001f);
        assertEquals(
                this.maximumNodeSize,
                smallSolarSystemLayoutWithMetadataInverted.getMaximumNodeRadius(),
                0.0001f);


        assertEquals(this.maximumNodeSize,
                largeSolarSystemLayout.getMaximumNodeRadius(), 0.0001f);
        assertEquals(this.maximumNodeSize,
                largeSolarSystemLayoutWithMetadata.getMaximumNodeRadius(),
                0.0001f);
        assertEquals(this.maximumNodeSize,
                largeSolarSystemLayoutInverted.getMaximumNodeRadius(), 0.0001f);
        assertEquals(
                this.maximumNodeSize,
                largeSolarSystemLayoutWithMetadataInverted.getMaximumNodeRadius(),
                0.0001f);
    }

    /**
     * Test method for
     * {@link edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.TieredOrbitalLayout#getDepthModifier()}
     * .
     */
    @Test
    public void testGetDepthModifier() {

        assertEquals(this.depthModifier,
                smallSolarSystemLayout.getDepthModifier(), 0.0001f);
        assertEquals(this.depthModifier,
                smallSolarSystemLayoutWithMetadata.getDepthModifier(), 0.0001f);
        assertEquals(this.depthModifier,
                smallSolarSystemLayoutInverted.getDepthModifier(), 0.0001f);
        assertEquals(this.depthModifier,
                smallSolarSystemLayoutWithMetadataInverted.getDepthModifier(),
                0.0001f);

        assertEquals(this.depthModifier,
                largeSolarSystemLayout.getDepthModifier(), 0.0001f);
        assertEquals(this.depthModifier,
                largeSolarSystemLayoutWithMetadata.getDepthModifier(), 0.0001f);
        assertEquals(this.depthModifier,
                largeSolarSystemLayoutInverted.getDepthModifier(), 0.0001f);
        assertEquals(this.depthModifier,
                largeSolarSystemLayoutWithMetadataInverted.getDepthModifier(),
                0.0001f);
    }


    /**
     * Test method for
     * {@link edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.TieredOrbitalLayout#getMinimumSpanningTree()}
     * .
     */
    @Test
    public void testMinimumSpanningTree() {

        this.testMinimumSpanningTree(this.smallSolarSystemLayout);
        this.testMinimumSpanningTree(this.smallSolarSystemLayoutWithMetadata);
    }

    private void testMinimumSpanningTree(
            TieredOrbitalLayout layout) {

        float delta = layout.getMinimalDistance();
        float s = layout.getMaximumNodeRadius();


        Tree<INode> mst = layout.getMinimumSpanningTree();


        // for (INode node : mst) {
        // ComponentNode treeNode = layout.getComponentNodeMap().get(node);
        //
        // // System.out.println(treeNode);
        // // float c = treeNode.getNumberOfChildren();
        // // System.out.println("c: " + c);
        // float alpha = 0f;
        // if (c == 0f) {
        // alpha = (float) Math.PI;
        // }
        // else {
        // alpha = (float) Math.min(Math.PI, (360f / c * Math.PI / 180f));
        // }
        // float g = treeNode.getMaximumChildRadius();
        // float a = 0f;
        // if (c == 0f) {
        // a = 0f;
        // }
        // else {
        // a = 2 * g + delta;
        // }
        //
        // float r = (float) Math.sqrt((a * a / (2 * (1 - Math.cos(alpha)))))
        // + scale;
        //
        // float gg = scale + 0f;
        // float aa = 2f * gg + delta;
        // float rr = (float) Math
        // .sqrt((aa * aa / (2 * (1 - Math.cos(Math.PI))))) + scale;
        //
        // float ggg = gg + rr;
        // float aaa = 2f * ggg + delta;
        // float rrr = (float) Math.sqrt((aaa * aaa / (2 * (1 - Math
        // .cos(Math.PI))))) + scale;
        //
        // float gggg = ggg + rrr;
        // float aaaa = 2f * gggg + delta;
        // float rrrr = (float) Math.sqrt((aaaa * aaaa / (2 * (1 - Math
        // .cos(Math.PI))))) + scale;
        //
        // float ggggg = gggg + rrrr;
        //
        // // System.out.println("g: 0.0");
        // // System.out.println("a: 0.0");
        // // System.out.println("r: " + scale);
        // // System.out.println();
        // //
        // // System.out.println("gg = r + g: " + gg);
        // // System.out.println("aa = 2gg + delta: " + aa);
        // // System.out.println("rr: " + rr);
        // // System.out.println();
        // //
        // // System.out.println("ggg = rr + gg: " + ggg);
        // // System.out.println("aaa = 2ggg + delta: " + aaa);
        // // System.out.println("rrr: " + rrr);
        // // System.out.println();
        // //
        // // System.out.println("gggg = rrr + ggg: " + gggg);
        // // System.out.println("aaaa = 2gggg + delta: " + aaaa);
        // // System.out.println("rrrr: " + rrrr);
        // // System.out.println();
        // //
        // // System.out.println("ggggg = rrrr + gggg: " + ggggg);
        // // System.out.println();
        //
        //
        // switch (node.getID()) {
        // case "F": {
        // assertEquals(3f, c, 0.001f);
        // assertEquals(ggggg, g, 0.001f);
        // break;
        // }
        //
        // case "B": {
        // assertEquals(2f, c, 0.001f);
        // assertEquals(gggg, g, 0.001f);
        // break;
        // }
        //
        // case "A": {
        // assertEquals(2f, c, 0.001f);
        // assertEquals(ggg, g, 0.001f);
        // break;
        // }
        // case "G": {
        //
        // assertEquals(1f, c, 0.001f);
        // assertEquals(ggg, g, 0.001f);
        // break;
        // }
        //
        // case "M":
        // assertEquals(1f, c, 0.001f);
        // assertEquals(gg, g, 0.001f);
        // break;
        // case "D":
        // assertEquals(2f, c, 0.001f);
        // assertEquals(gg, g, 0.001f);
        // break;
        // case "I":
        // assertEquals(1f, c, 0.001f);
        // assertEquals(gg, g, 0.001f);
        // break;
        //
        // case "E":
        // assertEquals(0f, c, 0.001f);
        // assertEquals(0f, g, 0.001f);
        // break;
        // case "C":
        // assertEquals(0f, c, 0.001f);
        // assertEquals(0f, g, 0.001f);
        // break;
        // case "N":
        // assertEquals(0f, c, 0.001f);
        // assertEquals(0f, g, 0.001f);
        // break;
        // case "H":
        // assertEquals(0f, c, 0.001f);
        // assertEquals(0f, g, 0.001f);
        // break;
        // case "Q":
        // assertEquals(0f, c, 0.001f);
        // assertEquals(0f, g, 0.001f);
        // break;
        // case "O":
        // assertEquals(0f, c, 0.001f);
        // assertEquals(0f, g, 0.001f);
        // break;
        // }
        // assertEquals(alpha, treeNode.getAngle(), 0.001f);
        // assertEquals(a, treeNode.getDistanceBetweenChildren(), 0.001f);
        // assertEquals(r, treeNode.getRadius(), 0.001f);
        // System.out.println(node.getID() + ":");
        // System.out.println("  c: " + c);
        // System.out.println("  alpha: " + alpha);
        // System.out.println("  g: " + g);
        // System.out.println("  a: " + a);
        // System.out.println("  r: " + r);
        // }
    }


}
