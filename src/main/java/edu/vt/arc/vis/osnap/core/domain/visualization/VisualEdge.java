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


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.math.geometry.GeometricOperations;
import org.jutility.math.geometry.IRotation;
import org.jutility.math.vectorAlgebra.IPoint4;
import org.jutility.math.vectorAlgebra.IVector4;
import org.jutility.math.vectorAlgebra.Vector4;
import org.jutility.math.vectorAlgebra.VectorAlgebraicOperations;

import edu.vt.arc.vis.osnap.core.domain.graph.Edge;


/**
 * This class represents the visual representation of an edge between two nodes.
 * 
 * The class instance contains the edge it is to represent.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @see edu.vt.arc.vis.osnap.core.domain.graph.Edge
 * @see edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode
 * @see edu.vt.arc.vis.osnap.core.domain.graph.metadata.Schema
 */
@XmlType(name = "VisualEdge")
@XmlAccessorType(XmlAccessType.NONE)
public class VisualEdge
        extends VisualObject {

    @XmlIDREF
    @XmlAttribute
    @XmlSchemaType(name = "IDREF")
    private final Edge       edge;

    @XmlIDREF
    @XmlAttribute
    @XmlSchemaType(name = "IDREF")
    private final VisualNode visualSourceNode;

    @XmlIDREF
    @XmlAttribute
    @XmlSchemaType(name = "IDREF")
    private final VisualNode visualTargetNode;


    /**
     * Returns the {@link Edge} contained in this VisualEdge.
     * 
     * @return the edge.
     */
    public Edge getEdge() {

        return this.edge;
    }

    @Override
    public Edge getGraphObject() {

        return this.getEdge();
    }


    /**
     * The getter for the visualSourceNode field.
     * 
     * @return The visualSourceNode node of the edge.
     */
    public VisualNode getVisualSourceNode() {

        return this.visualSourceNode;
    }


    /**
     * The getter for the visualTargetNode field.
     * 
     * @return The visualTargetNode node of the edge.
     */
    public VisualNode getVisualTargetNode() {

        return this.visualTargetNode;
    }


    @Override
    public boolean isVisible() {

        if (this.getColor() != null && this.getShape() != null
                && this.getScale() != null && this.visualSourceNode.isVisible()
                && this.visualTargetNode.isVisible()
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


    @Override
    public IPoint4<?> getPosition() {

        if (this.isVisible()) {
            IVector4<?> distanceVector = VectorAlgebraicOperations.subtract(
                    this.getVisualTargetNode().getPosition(), this
                            .getVisualSourceNode().getPosition(), this
                            .getPrecision());



            IVector4<?> halfDistance = VectorAlgebraicOperations.multiply(0.5f,
                    distanceVector, this.getPrecision());

            IPoint4<?> position = VectorAlgebraicOperations.add(this
                    .getVisualSourceNode().getPosition(), halfDistance, this
                    .getPrecision());

            this.setPosition(position);
        }
        return super.getPosition();
    }


    @Override
    public IRotation<?> getRotation() {

        if (this.isVisible()) {
            IVector4<?> distanceVector = VectorAlgebraicOperations.subtract(
                    this.getVisualTargetNode().getPosition(), this
                            .getVisualSourceNode().getPosition(), this
                            .getPrecision());

            IRotation<?> rotation = GeometricOperations
                    .getAngleAxisRotationBetweenVectors(
                            Vector4.J_UNIT_VECTOR(this.getPrecision()),
                            distanceVector, this.getPrecision());

            this.setRotation(rotation);
        }
        return super.getRotation();
    }



    @SuppressWarnings("unused")
    private VisualEdge() {

        this(null, null, null, null, null, false, true);
    }

    /**
     * Creates a new instance of the VisualEdge class.
     * 
     * This constructor creates a visual representation for a provided edge
     * within the given visual visualGraphs from the visualSourceNode to the
     * visualTargetNode node.
     * 
     * 
     * @param edge
     *            The edge data for this visual edge.
     * @param visualGraph
     *            The visualGraphs containing the edge.
     * @param visualSourceNode
     *            The visual source node of the edge.
     * @param visualTargetNode
     *            The visual target node of the edge.
     * @param precision
     *            the precision of this visual edge'scale numeric properties.
     */
    protected VisualEdge(Edge edge, VisualGraph visualGraph,
            VisualNode visualSourceNode, VisualNode visualTargetNode,
            Class<? extends Number> precision) {

        this(edge, visualGraph, visualSourceNode, visualTargetNode, precision,
                true);
    }


    /**
     * Creates a new instance of the VisualEdge class.
     * 
     * This constructor creates a visual representation for a provided edge
     * within the given visual visualGraphs from the visualSourceNode to the
     * visualTargetNode node.
     * 
     * 
     * @param edge
     *            The edge data for this visual edge.
     * @param visualGraph
     *            The visual graph containing the edge.
     * @param visualSourceNode
     *            The visual source node of the edge.
     * @param visualTargetNode
     *            The visual target node of the edge.
     * @param precision
     *            the precision of this visual edge'scale numeric properties.
     * @param isVisible
     *            Specifies whether the edge should be displayed.
     */
    protected VisualEdge(Edge edge, VisualGraph visualGraph,
            VisualNode visualSourceNode, VisualNode visualTargetNode,
            Class<? extends Number> precision, boolean isVisible) {

        this(edge, precision, visualGraph, visualSourceNode, visualTargetNode,
                isVisible, false);

    }

    private VisualEdge(Edge edge, Class<? extends Number> precision,
            VisualGraph visualGraph, VisualNode visualSourceNode,
            VisualNode visualTargetNode, boolean isVisible,
            boolean serialization) {

        super(Visualization.createIdentifier(edge, serialization), visualGraph,
                isVisible, precision, serialization);


        if (visualSourceNode == null && !serialization) {
            throw new IllegalArgumentException(
                    "A visual edge must have a visualSourceNode node.");
        }

        if (visualTargetNode == null && !serialization) {
            throw new IllegalArgumentException(
                    "A visual edge must have a visualTargetNode node.");
        }

        this.edge = edge;


        this.visualSourceNode = visualSourceNode;
        this.visualTargetNode = visualTargetNode;

    }



    @Override
    public boolean isIdentical(VisualObject other) {

        if (other instanceof VisualEdge) {

            return this.isIdentical((VisualEdge) other, true);
        }
        return false;
    }


    /**
     * Determines whether or not this <code>VisualEdge</code> is identical to
     * the one provided.
     * 
     * @param other
     *            the other <code>VisualEdge</code>.
     * @param recurse
     *            whether or not to recurse into the connected
     *            {@link VisualObject VisualObjects}.
     * @return <code>true</code> if they are identical; <code>false</code>
     *         otherwise.
     */
    public boolean isIdentical(VisualEdge other, boolean recurse) {

        if (super.isIdentical(other)) {

            boolean sameEdge = this.getEdge().isIdentical(other.getEdge(),
                    recurse);
            boolean sourcesIdentical = false;
            boolean targetsIdentical = false;

            if (recurse) {
                sourcesIdentical = this.getVisualSourceNode().isIdentical(
                        other.getVisualSourceNode(), false);

                targetsIdentical = this.getVisualTargetNode().isIdentical(
                        other.getVisualTargetNode(), false);
            }
            else {
                sourcesIdentical = this.getVisualSourceNode().equals(
                        other.getVisualSourceNode());

                targetsIdentical = this.getVisualTargetNode().equals(
                        other.getVisualTargetNode());
            }
            return sameEdge && sourcesIdentical && targetsIdentical;

        }

        return false;
    }



    /**
     * Determines whether the provided object is equal to the edge.
     * 
     * <p/>
     * <FONT COLOR="#cc6600">Equality is based solely on identifier and
     * visualGraphs containment!</FONT>
     * 
     * @param obj
     *            The object that the edge is compared to.
     * @return <CODE>true</CODE>, if the object provided is an edge, the edges'
     *         unique identifiers match, and both edges are contained in the
     *         same visualGraphs. Otherwise <CODE>false</CODE>.
     */
    @Override
    public boolean equals(Object obj) {

        if (super.equals(obj) && obj instanceof VisualEdge) {

            final VisualEdge other = (VisualEdge) obj;

            return this.getEdge().equals(other.getEdge())
                    && this.getVisualGraph().equals(other.getVisualGraph());
        }
        return false;
    }

    /**
     * Provides a hash code for the edge.
     * 
     * <p/>
     * <FONT COLOR="#cc6600">The hash code is based solely on identifier and
     * associated visualGraphs!</FONT>
     * 
     * @return A hash code for the edge based upon the unique identifier and the
     *         visualGraphs the edge is contained in.
     */
    @Override
    public int hashCode() {

        int hash = super.hashCode();
        hash = 83 * hash
                + (this.getEdge() != null ? this.getEdge().hashCode() : 0);
        hash = 83
                * hash
                + (this.getVisualGraph() != null ? this.getVisualGraph()
                        .hashCode() : 0);
        return hash;
    }

}
