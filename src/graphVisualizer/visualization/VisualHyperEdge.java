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


package graphVisualizer.visualization;


import graphVisualizer.graph.HyperEdge;
import graphVisualizer.graph.common.IGraphObject;

import java.util.Collections;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;


/**
 * This class represents a hyperedge between an arbitrary number of visualNodes.
 * 
 * Hyperedges consist of a (required) unique identifier, are associated with a
 * graph (required), require a set of visualNodes, and can (optionally) contain
 * metadata.
 * 
 * <p/>
 * Hyperedges can only be created from within a graph.
 * 
 * <p/>
 * <FONT COLOR="#cc6600">Not implemented!</FONT>
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @see graphVisualizer.visualization.VisualGraph
 * @see graphVisualizer.visualization.VisualNode
 * @see graphVisualizer.graph.metadata.Metadata
 */
@XmlType(name = "VisualHyperEdge")
@XmlAccessorType(XmlAccessType.NONE)
public class VisualHyperEdge
        extends VisualObject {


    @XmlIDREF
    @XmlAttribute
    private final HyperEdge       hyperEdge;


    @XmlIDREF
    @XmlElementWrapper(name = "VisualNodes")
    @XmlElement(name = "VisualNode")
    private final Set<VisualNode> visualNodes;


    /**
     * Returns the HyperEdge associated with this VisualHyperEdge.
     * 
     * @return the hyperedge.
     */
    public HyperEdge getHyperEdge() {

        return this.hyperEdge;
    }

    @Override
    public IGraphObject getGraphObject() {

        return this.getHyperEdge();
    }


    /**
     * Returns the visual visualNodes of the visual hyperEdge.
     * 
     * @return The visual visualNodes.
     */
    public Set<VisualNode> getVisualNodes() {

        return Collections.unmodifiableSet(visualNodes);
    }


    @SuppressWarnings("unused")
    private VisualHyperEdge() {

        this(null, null, null, false, null, true);
    }

    /**
     * Creates a new instance of the VisualHyperEdge class.
     * 
     * This constructor creates a visual hyperedge within the given visual graph
     * between the provided visual visualNodes with the provided hyperedge.
     * 
     * @param hyperEdge
     *            The hyperedge to visualize.
     * @param visualGraph
     *            The visual graph containing the visual hyperedge.
     * @param visualNodes
     *            The visual visualNodes of the hyperedge.
     * @param precision
     *            the precision of this visual hyperedge'scale numeric properties.
     */
    protected VisualHyperEdge(HyperEdge hyperEdge, VisualGraph visualGraph,
            Set<VisualNode> visualNodes, Class<? extends Number> precision) {

        this(hyperEdge, visualGraph, visualNodes, precision, false);
    }

    /**
     * Creates a new instance of the VisualHyperEdge class.
     * 
     * This constructor creates a visual hyperedge within the given visual graph
     * between the provided visual visualNodes with the provided hyperedge.
     * 
     * @param hyperEdge
     *            The hyperedge to visualize.
     * @param visualGraph
     *            The visual graph containing the visual hyperedge.
     * @param visualNodes
     *            The visual nodes of the hyperedge.
     * @param precision
     *            the precision of this visual hyperedge'scale numeric properties.
     * @param isVisible
     *            whether or not the visual hyperedge should be visible.
     */
    protected VisualHyperEdge(HyperEdge hyperEdge, VisualGraph visualGraph,
            Set<VisualNode> visualNodes, Class<? extends Number> precision,
            boolean isVisible) {

        this(hyperEdge, visualGraph, visualNodes, isVisible, precision, false);
    }

    /**
     * Creates a new instance of the VisualHyperEdge class.
     * 
     * This constructor creates a visual hyperedge within the given visual graph
     * between the provided visual visualNodes with the provided hyperedge.
     * 
     * @param hyperEdge
     *            The hyperedge to visualize.
     * @param visualGraph
     *            The visual graph containing the visual hyperedge.
     * @param visualNodes
     *            The visual nodes of the hyperedge.
     * @param precision
     *            the precision of this visual hyperedge'scale numeric properties.
     * @param isVisible
     *            whether or not the visual hyperedge should be visible.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    private VisualHyperEdge(HyperEdge hyperEdge, VisualGraph visualGraph,
            Set<VisualNode> visualNodes, boolean isVisible,
            Class<? extends Number> precision, boolean serialization) {

        super(Visualization.createIdentifier(hyperEdge, serialization),
                visualGraph, isVisible, precision, serialization);


        if ((visualNodes == null || visualNodes.size() < 2) && !serialization) {
            throw new IllegalArgumentException(
                    "A visual hyperedge must have at least two visual visualNodes.");
        }

        this.hyperEdge = hyperEdge;
        this.visualNodes = visualNodes;

    }

    /**
     * Removes the provided VisualNode from this VisualHyperEdge.
     * 
     * @param node
     *            the node to remove.
     */
    protected void removeVisualNode(VisualNode node) {

        if (this.visualNodes.size() < 3) {

            throw new IllegalArgumentException("Cannot delete either of the "
                    + "last two Nodes of a hyperedge.");
        }

        if (this.visualNodes.contains(node)) {

            this.visualNodes.remove(node);
        }
    }

    @Override
    public boolean isIdentical(VisualObject other) {

        if (other instanceof VisualHyperEdge) {

            return this.isIdentical((VisualHyperEdge) other, true);
        }
        return false;
    }

    /**
     * Determines whether or not this <code>VisualHyperEdge</code> is identical
     * to the one provided.
     * 
     * @param other
     *            the other <code>VisualHyperEdge</code>.
     * @param recurse
     *            whether or not to recurse into the connected
     *            {@link VisualObject VisualObjects}.
     * @return <code>true</code> if they are identical; <code>false</code>
     *         otherwise.
     */
    public boolean isIdentical(VisualHyperEdge other, boolean recurse) {

        if (super.isIdentical(other)) {

            boolean sameHyperEdge = this.getHyperEdge().isIdentical(
                    other.getHyperEdge(), recurse);

            if (sameHyperEdge) {
                for (VisualNode node : this.getVisualNodes()) {

                    boolean identical = false;
                    for (VisualNode otherNode : other.getVisualNodes()) {

                        if (recurse) {
                            identical = node.isIdentical(otherNode, false);
                        }
                        else {
                            identical = node.equals(otherNode);
                        }
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

    /**
     * Determines whether the provided object is equal to the hyperedge.
     * 
     * <p/>
     * <FONT COLOR="#cc6600">Equality is based solely on identifier and graph
     * containment!</FONT>
     * 
     * @param obj
     *            The object that the hyperedge is compared to.
     * @return <CODE>true</CODE>, if the object provided is an hyperedge, the
     *         hyperedges' unique identifiers match, and both edges are
     *         contained in the same graph. Otherwise <CODE>false</CODE>.
     */
    @Override
    public boolean equals(Object obj) {

        if (super.equals(obj) && obj instanceof VisualHyperEdge) {
            final VisualHyperEdge other = (VisualHyperEdge) obj;

            return this.getHyperEdge().equals(other.getHyperEdge())
                    && this.getVisualGraph().equals(other.getVisualGraph());
        }

        return false;
    }

    /**
     * Provides a hash code for the hyperedge.
     * 
     * <p/>
     * <FONT COLOR="#cc6600">The hash code is based solely on identifier and
     * associated graph!</FONT>
     * 
     * @return A hash code for the hyperedge based upon the unique identifier
     *         and the graph the hyperedge is contained in.
     */
    @Override
    public int hashCode() {

        int hash = super.hashCode();
        hash = 83
                * hash
                + (this.getHyperEdge() == null ? 0 : this.getHyperEdge()
                        .hashCode());
        hash = 83
                * hash
                + (this.getVisualGraph() == null ? 0 : this.getVisualGraph()
                        .hashCode());
        return hash;
    }
}
