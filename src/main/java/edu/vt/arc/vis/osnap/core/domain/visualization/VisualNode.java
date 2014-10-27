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


package edu.vt.arc.vis.osnap.core.domain.visualization;

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


import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.jutility.common.datatype.util.NumberComparator;

import edu.vt.arc.vis.osnap.core.domain.graph.Node;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;


/**
 * This class represents a node contained within a visualGraphs.
 * 
 * Nodes consist of a (required) unique identifier, are associated with a
 * visualGraphs (required), and can (optionally) have edges to other nodes
 * (including themselves), and/or contain metadata.
 * 
 * <p/>
 * Nodes can only be created from within a visualGraphs.
 * 
 * 
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @see edu.vt.arc.vis.osnap.core.domain.visualization.VisualGraph
 * @see edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge
 * @see edu.vt.arc.vis.osnap.core.domain.visualization.VisualHyperEdge
 * @see edu.vt.arc.vis.osnap.core.domain.graph.metadata.Schema
 */
@XmlType(name = "VisualNode")
@XmlAccessorType(XmlAccessType.NONE)
public class VisualNode
        extends VisualObject {


    @XmlIDREF
    @XmlAttribute
    @XmlSchemaType(name = "IDREF")
    private final Node                 node;


    @XmlElementWrapper(name = "VisualEdges")
    @XmlElement(name = "VisualEdge")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    private final Set<VisualEdge>      edges;

    @XmlElementWrapper(name = "VisualHyperEdges")
    @XmlElement(name = "VisualHyperEdge")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    private final Set<VisualHyperEdge> hyperedges;



    /**
     * Returns the {@link Node} contained in this VisualNode.
     * 
     * @return the node.
     */

    public Node getNode() {

        return this.node;
    }

    @Override
    public IGraphObject getGraphObject() {

        return this.getNode();
    }


    /**
     * Getter for the node'scale edges.
     * 
     * Returns the edges (both outgoing and incoming) of the node.
     * 
     * @return The node'scale edges.
     */
    public Set<VisualEdge> getVisualEdges() {

        return new LinkedHashSet<>(Collections.unmodifiableSet(this.edges));
    }


    /**
     * Getter for the node'scale hyperedges.
     * 
     * Returns the hyperedges of the node.
     * 
     * @return The node'scale hyperedges.
     */
    public Set<VisualHyperEdge> getVisualHyperEdges() {

        return new LinkedHashSet<>(Collections.unmodifiableSet(this.hyperedges));
    }

    @Override
    public boolean isVisible() {

        if (this.getColor() != null && this.getPosition() != null
                && this.getShape() != null && this.getScale() != null
                && !NumberComparator.equals(this.getScale().getScaleFactorX(), 0f)
                && !NumberComparator.equals(this.getScale().getScaleFactorY(), 0f)
                && !NumberComparator.equals(this.getScale().getScaleFactorZ(), 0f)) {
            this.setVisible(true);
        }
        else {
            this.setVisible(false);
        }

        return super.isVisible();
    }


    @SuppressWarnings("unused")
    private VisualNode() {

        this(null, null, false, null, true);
    }

    /**
     * Creates a new instance of the VisualNode class.
     * 
     * The created visual node only has an associated node and is associated
     * with a visual visualGraphs.
     * 
     * @param node
     *            The associated node.
     * @param visualGraph
     *            The visualGraph containing the node.
     * @param precision
     *            the precision of this visual node'scale numeric properties.
     */
    protected VisualNode(Node node, VisualGraph visualGraph,
            Class<? extends Number> precision) {

        this(node, visualGraph, false, precision);
    }

    /**
     * Creates a new instance of the VisualNode class.
     * 
     * The created visual node only has an associated node and is associated
     * with a visual visualGraphs.
     * 
     * @param node
     *            The associated node.
     * @param visualGraph
     *            The visualGraph containing the node.
     * @param isVisible
     *            whether or not the VisualNode should be visible.
     * @param precision
     *            the precision of this visual node'scale numeric properties.
     */
    protected VisualNode(Node node, VisualGraph visualGraph, boolean isVisible,
            Class<? extends Number> precision) {

        this(node, visualGraph, false, precision, false);
    }

    private VisualNode(Node node, VisualGraph visualGraph, boolean isVisible,
            Class<? extends Number> precision, boolean serialization) {

        super(Visualization.createIdentifier(node, serialization), visualGraph,
                isVisible, precision, serialization);
        if (node == null && !serialization) {
            throw new IllegalArgumentException("A visual node must have an "
                    + "associated node.");
        }


        this.node = node;
        this.edges = new LinkedHashSet<>();
        this.hyperedges = new LinkedHashSet<>();

    }

    /**
     * Adds an edge to the node.
     * 
     * @param edge
     *            The edge to add.
     */
    protected void addEdge(VisualEdge edge) {

        this.edges.add(edge);
    }

    /**
     * Removes an edge from the node.
     * 
     * @param edge
     *            The edge to remove.
     */
    protected void deleteEdge(VisualEdge edge) {

        this.edges.remove(edge);

    }

    /**
     * Removes all the edges from the node.
     */
    protected void clearVisualEdges() {

        this.edges.clear();
    }

    /**
     * Adds a hyperedge to the node.
     * 
     * @param hyperedge
     *            The edge to add.
     */
    protected void addVisualHyperEdge(VisualHyperEdge hyperedge) {

        this.hyperedges.add(hyperedge);
    }

    /**
     * Removes a hyperedge from the node.
     * 
     * @param hyperedge
     *            The hyperedge to remove.
     */
    protected void deleteHyperEdge(VisualHyperEdge hyperedge) {

        this.hyperedges.remove(hyperedge);

    }

    /**
     * Removes all the hyperedges from the node.
     */
    protected void clearVisualHyperEdges() {

        this.hyperedges.clear();
    }

    @Override
    public boolean isIdentical(VisualObject other) {

        if (other instanceof VisualNode) {

            return this.isIdentical((VisualNode) other, true);
        }
        return false;
    }

    /**
     * Determines whether or not this <code>VisualNode</code> is identical to
     * the one provided.
     * 
     * @param other
     *            the other <code>VisualNode</code>.
     * @param recurse
     *            whether or not to recurse into the connected
     *            {@link VisualObject VisualObjects}.
     * @return <code>true</code> if they are identical; <code>false</code>
     *         otherwise.
     */
    public boolean isIdentical(VisualNode other, boolean recurse) {

        if (super.isIdentical(other)) {

            boolean sameNode = this.getNode().isIdentical(other.getNode(),
                    recurse);
            boolean sameNumberOfEdges = this.getVisualEdges().size() == other
                    .getVisualEdges().size();
            boolean sameNumberOfHyperEdges = this.getVisualHyperEdges().size() == other
                    .getVisualHyperEdges().size();

            if (sameNode && sameNumberOfEdges && sameNumberOfHyperEdges) {

                if (!recurse) {

                    boolean edgesEqual = this.getVisualEdges().equals(
                            other.getVisualEdges());
                    boolean hyperedgesEqual = this.getVisualHyperEdges()
                            .equals(other.getVisualHyperEdges());

                    return edgesEqual && hyperedgesEqual;
                }

                for (VisualEdge edge : this.getVisualEdges()) {

                    boolean identical = false;
                    for (VisualEdge otherEdge : other.getVisualEdges()) {

                        identical = edge.isIdentical(otherEdge, false);
                        if (identical) {

                            break;
                        }
                    }
                    if (!identical) {

                        return false;
                    }

                }
                for (VisualHyperEdge hyperedge : this.getVisualHyperEdges()) {

                    boolean identical = false;
                    for (VisualHyperEdge otherHyperEdge : other
                            .getVisualHyperEdges()) {

                        identical = hyperedge
                                .isIdentical(otherHyperEdge, false);
                        if (identical) {

                            break;
                        }
                    }
                    if (!identical) {

                        return false;
                    }
                }

                return true;

            }
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {

        if (super.equals(obj) && obj instanceof VisualNode) {
            final VisualNode other = (VisualNode) obj;

            return this.getNode().equals(other.getNode())
                    && this.getVisualGraph().equals(other.getVisualGraph());
        }

        return false;
    }

    @Override
    public int hashCode() {

        int hash = super.hashCode();
        hash = 13 * hash
                + (this.getNode() == null ? 0 : this.getNode().hashCode());
        hash += 13
                * hash
                + (this.getVisualGraph() == null ? 0 : this.getVisualGraph()
                        .hashCode());
        return hash;
    }


}
