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
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.common.datatype.tree.PreorderTreeIterator;
import org.jutility.common.datatype.tree.Tree;
import org.jutility.common.datatype.tree.TreeNode;
import org.jutility.math.arithmetics.ArithmeticOperations;
import org.jutility.math.vectoralgebra.Point4;

import edu.vt.arc.vis.osnap.core.domain.graph.Edge;
import edu.vt.arc.vis.osnap.core.domain.graph.algorithms.IWeight;
import edu.vt.arc.vis.osnap.core.domain.graph.algorithms.SimpleDistanceWeight;
import edu.vt.arc.vis.osnap.core.domain.graph.algorithms.minimumSpanningTree.PrimsAlgorithm;
import edu.vt.arc.vis.osnap.core.domain.graph.base.NodeBase;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IEdge;
import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Metadata;
import edu.vt.arc.vis.osnap.core.domain.layout.common.Base3DCoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.CoordinateComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ICoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ITreeLayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualGraph;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;



/**
 * The {@code TieredOrbitalLayout} class provides a coordinate layout in the
 * shape of a solar system. The layout calculates a minimum spanning tree from a
 * provided root node. This can be based on a provided {@link Metadata}
 * criterion that filters the edges included to those with the criterion. It
 * lays out all nodes radially below their parent nodes in the spanning tree
 * with the root node in the origin. Provided parameters can invert the
 * direction of the spanning tree, specify the maximum size and minimum distance
 * of the nodes to prevent overlap, as well as scale the depth of the tree.
 * 
 * @author Peter J. Radics
 * @version 1.2.3
 * @since 0.1.0
 */
@XmlType(name = "TieredOrbitalLayout")
public class TieredOrbitalLayout
        extends Base3DCoordinateLayout
        implements ITreeLayout {

    private Collection<? extends INode> nodes;
    private Collection<? extends IEdge> edges;

    private Map<String, VisualNode>     visualNodes;
    private PrimsAlgorithm              minimumSpanningTree;

    private Map<INode, ComponentNode>   componentNodeMap;

    // Cannot use IDRef with interface due to bug in JAXB
    // @XmlIDREF
    @XmlElement(name = "RootNode", type = NodeBase.class)
    private INode                       rootNode;

    @XmlElement(name = "Metadata")
    private Metadata                    metadata;
    @XmlElement(name = "InvertPath")
    private boolean                     invertPathToRootNode;
    @XmlElement(name = "IgnoreEdgeDirection")
    private boolean                     ignoreEdgeDirection;

    @XmlElement(name = "MinimalDistance")
    private float                       minimalDistance;
    @XmlElement(name = "MaximumNodeRadius")
    private float                       maximumNodeRadius;
    @XmlElement(name = "DepthModifier")
    private float                       depthModifier;

    private SimpleDistanceWeight        simpleDistanceWeight;

    private boolean                     initialized;

    @XmlElement(name = "Anchor")
    private Point4<Float>               anchor;

    public static String description() {

        String description = "The " + TieredOrbitalLayout.name()
                + " provides a hierarchical layout that places nodes radially"
                + " below a root node using a minimum spanning tree.\n"
                + "Optionally, only edges containing a metadata provided"
                + " are included in the spanning tree.";

        return description;
    }

    public static String name() {

        return "Tiered Orbital Layout";
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

    @Override
    public INode getRootNode() {

        return this.rootNode;
    }

    /**
     * Returns the {@link Metadata} based on which this LayoutComponent filters
     * edges or {@code null}, if the LayoutComponent is unconstrained.
     * 
     * @return the {@link Metadata}.
     */
    public Metadata getMetadata() {

        return this.metadata;
    }


    /**
     * Sets the {@link Metadata} based on which this LayoutComponent filters
     * edges.
     * 
     * @param metadata
     *            the metadata.
     */
    public void setMetadata(Metadata metadata) {

        this.metadata = metadata;
    }


    /**
     * Sets the minimal distance between two VisualNodes.
     * 
     * @param minimalDistance
     *            the minimal distance.
     */
    public void setMinimalDistance(float minimalDistance) {

        this.minimalDistance = minimalDistance;
    }

    /**
     * Returns the minimal distance.
     * 
     * @return the minimal distance.
     */
    public float getMinimalDistance() {

        return this.minimalDistance;
    }

    /**
     * Sets the maximum radius of a VisualNode.
     * 
     * @param maximumNodeRadius
     *            the maximum radius.
     */
    public void setMaximumNodeRadius(float maximumNodeRadius) {

        this.maximumNodeRadius = maximumNodeRadius;
    }

    /**
     * Returns the maximum node size.
     * 
     * @return the maximum node size.
     */
    public float getMaximumNodeRadius() {

        return this.maximumNodeRadius;
    }

    /**
     * Sets the depth modifier (the distance between hierarchy levels).
     * 
     * @param depthModifier
     *            the depth modifier.
     */
    public void setDepthModifier(float depthModifier) {

        this.depthModifier = depthModifier;
    }

    /**
     * Returns the depth modifier.
     * 
     * @return the depth modifier.
     */
    public float getDepthModifier() {

        return this.depthModifier;
    }

    /**
     * Sets whether or not to invert the direction of edges constituting the
     * path to the root node in directed graphs.
     * 
     * @param invertPathToRootNode
     *            whether to invert direction of edges.
     */
    public void setInvertPathToRootNode(boolean invertPathToRootNode) {

        this.invertPathToRootNode = invertPathToRootNode;
    }

    /**
     * Returns whether or not to invert the direction of edges constituting the
     * path to the root node in directed graphs.
     * 
     * @return {@code true}, if edge direction is inverted; {@code false}
     *         otherwise.
     */
    public boolean isInvertPathToRootNode() {

        return this.invertPathToRootNode;
    }

    /**
     * Sets whether or not to ignore the direction of edges in creating paths.
     * 
     * @param ignoreEdgeDirection
     *            whether or not to ignore edge direction.
     */
    public void setIgnoreEdgeDirection(boolean ignoreEdgeDirection) {

        this.ignoreEdgeDirection = ignoreEdgeDirection;
    }


    /**
     * Returns whether or not to ignore the direction of edges in creating
     * paths.
     * 
     * @return {@code true}, if edge direction is to be ignored; {@code false}
     *         otherwise.
     */
    public boolean isIgnoreEdgeDirection() {

        return this.ignoreEdgeDirection;
    }



    /**
     * Returns the minimum spanning tree.
     * 
     * @return the minimum spanning tree.
     */
    protected Tree<INode> getMinimumSpanningTree() {

        if (this.minimumSpanningTree != null) {
            return this.minimumSpanningTree.getMinimumSpanningTree();
        }
        return null;
    }

    /**
     * Returns the weight of nodes and edges.
     * 
     * @return the weight.
     */
    protected IWeight getWeight() {

        return this.simpleDistanceWeight;
    }

    /**
     * Returns the component-node map.
     * 
     * @return the component-node map
     */
    protected Map<INode, ComponentNode> getComponentNodeMap() {

        return this.componentNodeMap;
    }

    /**
     * Creates a new instance of the {@link TieredOrbitalLayout} class.
     * (Serialization Constructor).
     */
    @SuppressWarnings("unused")
    private TieredOrbitalLayout() {

        this(null, null, 1f, 1f, 50f, false, true, true);
    }

    /**
     * Creates a new instance of the {@link TieredOrbitalLayout} class with the
     * provided root {@link INode Node}.
     * 
     * @param rootNode
     *            the root {@link INode Node}.
     */
    public TieredOrbitalLayout(INode rootNode) {

        this(rootNode, null);
    }


    /**
     * Creates a new {@link TieredOrbitalLayout} with the provided root
     * {@link INode Node}, limiting paths from the root node to edges that
     * contain the provided {@link Metadata}. Furthermore, the minimal distance
     * between nodes and the maximum distance of nodes is set to 1. The depth of
     * the resulting coordinate layout is not scaled. Finally, the path from the
     * root node to each individual node is inverted depending on the provided
     * parameter.
     * 
     * @param rootNode
     *            the root {@link INode Node}.
     * @param invertPathToRootNode
     *            if <code>true</code>, inverts the directions of the paths.
     */
    public TieredOrbitalLayout(INode rootNode, boolean invertPathToRootNode) {

        this(rootNode, null, 1f, 1f, 50f, invertPathToRootNode);
    }


    /**
     * Creates a new {@link TieredOrbitalLayout} with the provided root
     * {@link INode Node}, limiting paths from the root node to edges that
     * contain the provided {@link Metadata}. Furthermore, the minimal distance
     * between nodes and the maximum distance of nodes is set to 1. The depth of
     * the resulting coordinate layout is not scaled.
     * 
     * @param rootNode
     *            the root {@link INode Node}.
     * @param metadata
     *            the {@link Metadata}.
     */
    public TieredOrbitalLayout(INode rootNode, Metadata metadata) {

        this(rootNode, metadata, 1f, 1f, 50f);
    }


    /**
     * Creates a new {@link TieredOrbitalLayout} with the provided root
     * {@link INode Node}, limiting paths from the root node to edges that
     * contain the provided {@link Metadata}. Furthermore, the minimal distance
     * between nodes and the maximum distance of nodes is set to the provided
     * values. Furthermore, the depth of the resulting coordinate layout is
     * scaled by the depth modifier.
     * 
     * @param rootNode
     *            the root {@link INode Node}.
     * @param metadata
     *            the {@link Metadata}.
     * @param minimalDistance
     *            the minimal distance between nodes.
     * @param maximumNodeRadius
     *            the maximum size of a node.
     * @param depthModifier
     *            the scaling factor for the depth of the layout.
     */
    public TieredOrbitalLayout(INode rootNode, Metadata metadata,
            float minimalDistance, float maximumNodeRadius, float depthModifier) {

        this(rootNode, metadata, minimalDistance, maximumNodeRadius,
                depthModifier, false);
    }


    /**
     * Creates a new {@link TieredOrbitalLayout} with the provided root
     * {@link INode Node}, limiting paths from the root node to edges that
     * contain the provided {@link Metadata}. Furthermore, the minimal distance
     * between nodes and the maximum radius of nodes is set to the provided
     * values. Furthermore, the depth of the resulting coordinate layout is
     * scaled by the depth modifier. Finally, the path from the root node to
     * each individual node is inverted depending on the provided parameter.
     * 
     * @param rootNode
     *            the root {@link INode Node}.
     * @param metadata
     *            the {@link Metadata}.
     * @param minimalDistance
     *            the minimal distance between nodes.
     * @param maximumNodeRadius
     *            the maximum radius of a node.
     * @param depthModifier
     *            the scaling factor for the depth of the layout.
     * @param invertPathToRootNode
     *            if <code>true</code>, inverts the directions of the paths.
     */
    public TieredOrbitalLayout(INode rootNode, Metadata metadata,
            float minimalDistance, float maximumNodeRadius,
            float depthModifier, boolean invertPathToRootNode) {

        this(rootNode, metadata, minimalDistance, maximumNodeRadius,
                depthModifier, invertPathToRootNode, true);
    }

    /**
     * Creates a new {@link TieredOrbitalLayout} with the provided root
     * {@link INode Node}, limiting paths from the root node to edges that
     * contain the provided {@link Metadata}. Furthermore, the minimal distance
     * between nodes and the maximum radius of nodes is set to the provided
     * values. Furthermore, the depth of the resulting coordinate layout is
     * scaled by the depth modifier. Finally, the path from the root node to
     * each individual node is inverted depending on the provided parameter.
     * 
     * @param rootNode
     *            the root {@link INode Node}.
     * @param metadata
     *            the {@link Metadata}.
     * @param minimalDistance
     *            the minimal distance between nodes.
     * @param maximumNodeRadius
     *            the maximum radius of a node.
     * @param depthModifier
     *            the scaling factor for the depth of the layout.
     * @param invertPathToRootNode
     *            if <code>true</code>, inverts the directions of the paths.
     * @param ignoreEdgeDirection
     *            if {@code true}, direction of the nodes are ignored.
     */
    public TieredOrbitalLayout(INode rootNode, Metadata metadata,
            float minimalDistance, float maximumNodeRadius,
            float depthModifier, boolean invertPathToRootNode,
            boolean ignoreEdgeDirection) {

        this(rootNode, metadata, minimalDistance, maximumNodeRadius,
                depthModifier, invertPathToRootNode, ignoreEdgeDirection, false);
    }

    /**
     * Creates a new {@link TieredOrbitalLayout} with the provided root
     * {@link INode Node}, limiting paths from the root node to edges that
     * contain the provided {@link Metadata}. Furthermore, the minimal distance
     * between nodes and the maximum radius of nodes is set to the provided
     * values. Furthermore, the depth of the resulting coordinate layout is
     * scaled by the depth modifier. Finally, the path from the root node to
     * each individual node is inverted depending on the provided parameter.
     * 
     * @param rootNode
     *            the root {@link INode Node}.
     * @param metadata
     *            the {@link Metadata}.
     * @param minimalDistance
     *            the minimal distance between nodes.
     * @param maximumNodeRadius
     *            the maximum radius of a node.
     * @param depthModifier
     *            the scaling factor for the depth of the layout.
     * @param invertPathToRootNode
     *            if <code>true</code>, inverts the directions of the paths.
     * @param ignoreEdgeDirection
     *            if {@code true}, direction of the nodes are ignored.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    public TieredOrbitalLayout(INode rootNode, Metadata metadata,
            float minimalDistance, float maximumNodeRadius,
            float depthModifier, boolean invertPathToRootNode,
            boolean ignoreEdgeDirection, boolean serialization) {

        super(TieredOrbitalLayout.name(), TieredOrbitalLayout.description(),
                true, serialization);

        this.invertPathToRootNode = invertPathToRootNode;
        this.ignoreEdgeDirection = ignoreEdgeDirection;
        this.rootNode = rootNode;
        this.metadata = metadata;

        this.minimalDistance = minimalDistance;
        this.maximumNodeRadius = maximumNodeRadius;
        this.depthModifier = depthModifier;
        this.anchor = new Point4<>(0, 0, 0, Float.class);

        Random random = new Random();
        this.setName("Solar System Coordinate Provider" + "-"
                + random.nextLong());

        this.componentNodeMap = new LinkedHashMap<>();
        this.initialized = false;
    }


    @Override
    public String getDescription() {

        String description = super.getDescription();

        if (this.metadata != null) {
            description += "\n\nIn this instance oly edges containing the"
                    + " metadata " + this.metadata
                    + " are included in the spanning tree.";
        }


        return description;
    }


    /**
     * Calculates the visual properties of all nodes.
     */
    protected void calculateVisualProperties() {

        // System.out.println("In calculateVisualProperties");

        if (!initialized) {

            // Setting root node
            if (rootNode == null) {

                Iterator<? extends INode> it = nodes.iterator();
                if (it.hasNext()) {

                    this.rootNode = it.next();
                }
                else {

                    throw new IllegalArgumentException(
                            "Cannot create solar system layout without a root node!");
                }
            }

            // Create minimal spanning tree based on the shortest paths
            // from the root node.
            // System.out.println("Creating distances");
            this.simpleDistanceWeight = new SimpleDistanceWeight(rootNode,
                    nodes, this.filterEdges(), !this.ignoreEdgeDirection,
                    this.invertPathToRootNode);

            // System.out.println("Creating MST");
            this.minimumSpanningTree = new PrimsAlgorithm(nodes, edges,
                    simpleDistanceWeight, rootNode);

            PreorderTreeIterator<INode> it = this.getMinimumSpanningTree()
                    .preorderIterator();
            // System.out.println("Creating component node tree");
            while (it.hasNext()) {

                INode inode = it.next();

                // System.out.println("\tCreating component node for "
                // + inode.getID());

                ComponentNode componentNode = new ComponentNode(inode);

                this.componentNodeMap.put(inode, componentNode);

                TreeNode<INode> treeNode = it.getTreeNode();
                if (treeNode.getParent() != null) {

                    ComponentNode parentComponentNode = this.componentNodeMap
                            .get(treeNode.getParent().getElement());
                    parentComponentNode.getChildren().add(componentNode);
                    // System.out.println("\t\tAdding " + inode.getID()
                    // + " to parent " + parentComponentNode.getNode()
                    // + "'scale children");
                }
            }


            // System.out.println("Retrieving root node");
            ComponentNode rootComponentNode = this.componentNodeMap
                    .get(this.rootNode);

            rootComponentNode.setFirstComponent(this.anchor.getX());
            rootComponentNode.setSecondComponent(this.anchor.getY());
            rootComponentNode.setThirdComponent(this.anchor.getZ());

            rootComponentNode.setAngle(0f);
            rootComponentNode.setChildPositions();

        }
        // Sets the visual properties for the nodes contained in the minimal
        // spanning tree.
        this.setComponents();
        initialized = true;
    }

    private Collection<? extends IEdge> filterEdges() {

        if (this.metadata == null) {

            return this.edges;
        }

        List<IEdge> filteredEdges = new LinkedList<>();

        for (IEdge edge : this.edges) {
            if (edge instanceof Edge) {
                Edge realEdge = (Edge) edge;
                List<Metadata> metadataValues = realEdge.getMetadataProperty()
                        .getMetadata(this.metadata.getKey());
                if (metadataValues != null
                        && metadataValues.contains(this.metadata)) {

                    filteredEdges.add(edge);
                }
            }
        }

        return filteredEdges;
    }


    /**
     * Calculates the depth of the provided {@link INode Node}.
     * 
     * @param node
     *            the {@link INode Node}.
     * @return the depth of the provided {@link INode Node}.
     */
    protected Number calculateDepth(INode node) {

        Float theWeight = null;

        Integer weight = simpleDistanceWeight.getWeight(node);
        if (weight != null) {

            theWeight = weight * depthModifier;
        }

        return theWeight;
    }


    private void setComponents() {

        for (INode node : this.getMinimumSpanningTree()) {

            ComponentNode componentNode = this.componentNodeMap.get(node);
            VisualNode visualNode = this.getVisualNode(componentNode.getNode());

            this.setValue(visualNode, CoordinateComponent.FIRST_COMPONENT,
                    componentNode.getFirstComponent());
            this.setValue(
                    visualNode,
                    CoordinateComponent.SECOND_COMPONENT,
                    ArithmeticOperations.multiply(-1f,
                            componentNode.getThirdComponent()));
            this.setValue(visualNode, CoordinateComponent.THIRD_COMPONENT,
                    componentNode.getSecondComponent());
        }
    }

    @Override
    public void layout(Visualization visualization) {

        this.visualNodes = visualization.getVisualNodeMap();
        this.nodes = visualization.getUniverse().getNodes();
        this.edges = visualization.getUniverse().getEdges();


        this.calculateVisualProperties();
    }

    @Override
    public void layout(VisualGraph graph) {

        this.visualNodes = graph.getVisualNodeMap();
        this.nodes = graph.getGraph().getNodes();
        this.edges = graph.getGraph().getEdges();

        this.calculateVisualProperties();
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

    private VisualNode getVisualNode(INode node) {

        String visualNodeID = Visualization.createIdentifier(node, false);

        return this.visualNodes.get(visualNodeID);
    }


    /**
     * The {@link ComponentNode} class provides a container for information of
     * an {@link VisualNode} required to determine its layout properties.
     * 
     * @author Peter J. Radics
     * @version 0.1
     * 
     */
    protected class ComponentNode {

        private final INode               node;

        private final List<ComponentNode> children;
        private float                     maxChildRadius;
        private float                     radius;
        private float                     angle;

        private Point4<Float>             position;

        /**
         * Creates a new {@link ComponentNode} for the provided
         * {@link VisualNode}.
         * 
         * @param node
         *            the {@link VisualNode}.
         */
        public ComponentNode(INode node) {

            this.node = node;
            this.children = new LinkedList<>();
            this.radius = -1f;
            this.maxChildRadius = 0f;

            this.angle = 0f;
            this.position = new Point4<>(0, 0, 0, Float.class);
        }

        private void calculateRadius() {

            float circumference = (float) (2f * Math.PI * TieredOrbitalLayout.this
                    .getMaximumNodeRadius());


            this.maxChildRadius = 0f;

            // We need to put |children| x minimalDistance spacing in-between
            // the children.
            float childDiameters = TieredOrbitalLayout.this
                    .getMinimalDistance() * this.children.size();

            for (ComponentNode child : this.children) {

                float childRadius = child.getRadius();
                // Add the diameter of the child to the circumference.
                childDiameters += childRadius * 2f;

                if (childRadius > this.maxChildRadius) {

                    this.maxChildRadius = childRadius;
                }
            }

            circumference = Math.max(circumference, childDiameters);

            this.radius = circumference / (float) (2f * Math.PI)
                    + maxChildRadius;
            // System.out.println("Radius of " + this.node.getID() + " is "
            // + this.radius);
        }

        private float calculateAngleForChild(ComponentNode child,
                ComponentNode predecessor) {

            if (predecessor == null) {

                child.setAngle(0f);
                // System.out.println("Angle of " + child.getNode().getID()
                // + " is " + child.getAngle());
                return 0f;
            }
            float angle = predecessor.getAngle();
            float childRadius = child.getRadius();
            float grandChildRadius = child.getMaxChildRadius();
            float predecessorRadius = predecessor.getRadius();
            float predecessorGrandChildRadius = predecessor.getMaxChildRadius();

            float distance = childRadius + grandChildRadius + predecessorRadius
                    + predecessorGrandChildRadius
                    + TieredOrbitalLayout.this.getMinimalDistance();


            angle += (float) Math.acos((2f * this.getRadius()
                    * this.getRadius() - distance * distance)
                    / (2f * this.getRadius() * this.getRadius()));

            child.setAngle(angle);
            // System.out.println("Angle of " + child.getNode().getID() + " is "
            // + child.getAngle());
            return angle;
        }

        /**
         * Calculates and sets the position of a node'scale children.
         */
        public void setChildPositions() {

            // System.out.println("\n\nSetting positions of children of "
            // + this.getNode().getID());
            Point4<Float> anchor = this.position;
            float radius = this.getRadius();

            ComponentNode predecessor = null;

            for (ComponentNode child : this.getChildren()) {

                float angle = this.calculateAngleForChild(child, predecessor);

                float x = (float) Math.cos(angle) * radius;
                float y = (float) Math.sin(angle) * radius;

                child.setFirstComponent(x + anchor.getX());
                child.setSecondComponent(y + anchor.getY());
                child.setThirdComponent(TieredOrbitalLayout.this
                        .calculateDepth(child.getNode()));

                // System.out.println("Position of " + child.getNode().getID()
                // + " is " + child.position);
                child.setChildPositions();
                predecessor = child;
            }
            // System.out.println("\n");
        }

        /**
         * Returns the {@link INode} contained in the {@link VisualNode}.
         * 
         * @return the {@link INode}.
         */
        protected INode getNode() {

            return this.node;
        }


        /**
         * Returns the children of this {@link ComponentNode}.
         * 
         * @return the children of this {@link ComponentNode}.
         */
        public List<ComponentNode> getChildren() {

            return this.children;
        }


        /**
         * Returns the radius of the children of this {@link ComponentNode}.
         * 
         * @return the radius of the children of this {@link ComponentNode}.
         */
        public float getRadius() {

            if (-1f == this.radius) {

                // System.out.println("In here!");
                this.calculateRadius();
            }
            return this.radius;
        }


        /**
         * Returns the maximum child radius.
         * 
         * @return the maximum child radius.
         */
        public float getMaxChildRadius() {

            if (-1f == this.maxChildRadius) {

                this.calculateRadius();
            }
            return this.maxChildRadius;
        }


        /**
         * Returns the angle.
         * 
         * @return the angle.
         */
        public float getAngle() {

            return this.angle;
        }


        /**
         * Sets the angle.
         * 
         * @param angle
         *            the angle.
         */
        public void setAngle(float angle) {

            this.angle = angle;
        }

        /**
         * Returns the first coordinate component of this node.
         * 
         * @return the first component.
         */
        public Number getFirstComponent() {

            return this.position.getX();
        }

        /**
         * Sets the first coordinate component of this node
         * 
         * @param firstComponent
         *            the first coordinate.
         */
        public void setFirstComponent(Number firstComponent) {

            this.position = new Point4<>(firstComponent, this.position.getY(),
                    this.position.getZ(), Float.class);
        }

        /**
         * Returns the second coordinate component of this node.
         * 
         * @return the second component.
         */
        public Number getSecondComponent() {

            return this.position.getY();
        }

        /**
         * Sets the second coordinate component of this node
         * 
         * @param secondComponent
         *            the first coordinate.
         */
        public void setSecondComponent(Number secondComponent) {

            this.position = new Point4<>(this.position.getX(), secondComponent,
                    this.position.getZ(), Float.class);
        }


        /**
         * Returns the third coordinate component of this node.
         * 
         * @return the third component.
         */
        public Number getThirdComponent() {

            return TieredOrbitalLayout.this.calculateDepth(this.node);
        }

        /**
         * Sets the third component.
         * 
         * @param thirdComponent
         *            the third component.
         */
        public void setThirdComponent(Number thirdComponent) {

            this.position = new Point4<>(this.position.getX(),
                    this.position.getY(), thirdComponent, Float.class);
        }


        @Override
        public boolean equals(Object other) {

            if (other != null && other instanceof ComponentNode) {

                ComponentNode otherNode = (ComponentNode) other;

                return this.getNode().equals(otherNode.getNode());

            }

            return false;
        }

        @Override
        public int hashCode() {

            return this.getNode().hashCode();
        }

        @Override
        public String toString() {

            return this.getNode().getId().toString();
        }
    }
}
