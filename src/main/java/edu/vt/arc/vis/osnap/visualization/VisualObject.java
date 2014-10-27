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


package edu.vt.arc.vis.osnap.visualization;


import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.jutility.math.geometry.IRotation;
import org.jutility.math.geometry.IScale;
import org.jutility.math.geometry.Rotation;
import org.jutility.math.geometry.Scale;
import org.jutility.math.vectorAlgebra.IPoint4;
import org.jutility.math.vectorAlgebra.Point4;

import javafx.scene.paint.Color;


/**
 * The abstract {@link VisualObject} class provides the basic capabilities of
 * any object that is visualized.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlType(name = "VisualObject")
@XmlAccessorType(XmlAccessType.NONE)
public abstract class VisualObject
        implements IVisualGraphObject {

    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    @XmlAttribute
    private final String                  id;

    @XmlAttribute
    private final Class<? extends Number> precision;

    @XmlIDREF
    @XmlElement(name = "VisualGraph")  
    @XmlSchemaType(name = "IDREF")
    private final List<VisualGraph>       visualGraphs;

    @XmlAttribute
    private boolean                       visible;

    @XmlElement(name = "Label")
    private Label                         label;

    @XmlElement(name = "Shape")
    private Shape                         shape;
    @XmlElement(name = "Scale", type = Scale.class)
    private IScale<?>                     scale;
    @XmlElement(name = "Position", type = Point4.class)
    private IPoint4<?>                    position;
    @XmlElement(name = "Rotation", type = Rotation.class)
    private IRotation<?>                  rotation;

    @XmlElement(name = "Color")
    private Color                         color;

    @Override
    public String getID() {

        return this.id;
    }


    @Override
    public String getGraphObjectID() {

        return this.getGraphObject().getID();
    }

    /**
     * Returns the precision of this {@link VisualObject VisualObject'scale}
     * numerical properties.
     * 
     * @return the precision.
     */
    public Class<? extends Number> getPrecision() {

        return this.precision;
    }

    /**
     * The getter for the visual graph field.
     * 
     * @return The visual graph containing the visual edge.
     */
    public VisualGraph getVisualGraph() {

        if (!this.visualGraphs.isEmpty()) {

            return this.visualGraphs.get(0);
        }
        return null;
    }


    /**
     * Returns whether or not this VisualEdge is visible.
     * 
     * @return <code>true</code> if the VisualEdge is visible;
     *         <code>false</code> otherwise.
     */
    public boolean isVisible() {

        return this.visible;
    }


    /**
     * Sets whether or not this VisualEdge is visible.
     * 
     * @param visible
     *            the new visibility.
     */
    protected void setVisible(boolean visible) {

        this.visible = visible;
    }


    /**
     * Returns the label of this VisualEdge.
     * 
     * @return the label.
     */
    public Label getLabel() {

        return this.label;
    }


    /**
     * Sets the new label of this VisualEdge.
     * 
     * @param label
     *            the new label
     */
    public void setLabel(Label label) {

        this.label = label;
    }


    /**
     * Returns the shape of this VisualEdge.
     * 
     * @return the shape.
     */
    public Shape getShape() {

        return shape;
    }


    /**
     * Sets the shape of this VisualEdge.
     * 
     * @param shape
     *            the new shape.
     */
    public void setShape(Shape shape) {

        this.shape = shape;
    }


    /**
     * Returns the scale of this VisualEdge.
     * 
     * @return the scale.
     */
    public IScale<?> getScale() {

        return this.scale;
    }


    /**
     * Sets the new scale of this VisualEdge.
     * 
     * @param scale
     *            the new scale.
     */
    public void setScale(IScale<?> scale) {

        this.scale = scale;
    }


    /**
     * Returns the position of this VisualEdge.
     * 
     * @return the position.
     */
    public IPoint4<?> getPosition() {

        return this.position;
    }


    /**
     * Sets the new position of this VisualEdge.
     * 
     * @param position
     *            the new position.
     */
    public void setPosition(IPoint4<?> position) {

        this.position = position;
    }


    /**
     * Returns the rotation of this VisualEdge.
     * 
     * @return the rotation.
     */
    public IRotation<?> getRotation() {

        return this.rotation;
    }


    /**
     * Sets the new rotation of this VisualEdge.
     * 
     * @param rotation
     *            the new rotation.
     */
    public void setRotation(IRotation<?> rotation) {

        this.rotation = rotation;
    }


    /**
     * Returns the color of this VisualEdge.
     * 
     * @return the color.
     */
    public Color getColor() {

        return color;
    }


    /**
     * Sets the new color of this VisualEdge.
     * 
     * @param color
     *            the new color.
     */
    public void setColor(Color color) {

        this.color = color;
    }


    @SuppressWarnings("unused")
    private VisualObject() {

        this(null, null, false, null, true);
    }

    /**
     * Creates a new instance of the VisualObject class.
     * 
     * @param id
     *            the id of the visual object.
     * @param visualGraph
     *            the visual graph containing the visual object.
     * @param precision
     *            the precision of this visual object'scale numeric properties.
     */
    protected VisualObject(String id, VisualGraph visualGraph,
            Class<? extends Number> precision) {

        this(id, visualGraph, false, precision, false);
    }

    /**
     * Creates a new instance of the VisualObject class.
     * 
     * @param id
     *            the id of the visual object.
     * @param visualGraph
     *            the visual graph containing the visual object.
     * @param isVisible
     *            whether or not the visual object is visible.
     * @param precision
     *            the precision of this visual object'scale numeric properties.
     */
    protected VisualObject(String id, VisualGraph visualGraph,
            boolean isVisible, Class<? extends Number> precision) {

        this(id, visualGraph, isVisible, precision, false);
    }

    /**
     * Creates a new instance of the VisualObject class.
     * 
     * @param id
     *            the id of the visual object.
     * @param visualGraph
     *            the visual graph containing the visual object.
     * @param isVisible
     *            whether or not the visual object is visible.
     * @param precision
     *            the precision of this visual object'scale numeric properties.
     * @param serialization
     *            whether or not the constructor is called during serialization.
     */
    protected VisualObject(String id, VisualGraph visualGraph,
            boolean isVisible, Class<? extends Number> precision,
            boolean serialization) {

        if (id == null && !serialization) {
            throw new IllegalArgumentException(
                    "A visual object must have an id!");
        }

        if (visualGraph == null && !serialization) {
            throw new IllegalArgumentException(
                    "A visual object must be associated with a visual graph!");
        }

        this.id = id;

        this.precision = precision;

        this.visualGraphs = new LinkedList<>();
        this.visualGraphs.add(visualGraph);


        this.visible = isVisible;

        this.label = null;
        this.shape = null;
        this.scale = null;
        this.position = null;
        this.rotation = null;
        this.color = null;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder(this.getID());


        if (this.position != null) {
            builder.append("@" + this.position);
        }
        else {
            builder.append("@null");
        }


        if (this.label != null) {
            builder.append(", Label: " + this.label);
        }
        else {
            builder.append(", Label: no label");
        }


        if (this.shape != null) {
            builder.append(", Shape: " + this.shape);
        }
        else {
            builder.append(", Shape: no shape");
        }


        if (this.color != null) {
            builder.append(", Color: " + this.color);
        }
        else {
            builder.append(", Color: no color");
        }

        if (this.scale != null) {
            builder.append(", Scale: " + this.scale);
        }
        else {
            builder.append(", Scale: no scale");
        }

        return builder.toString();
    }

    /**
     * Determines whether or not this <code>VisualObject</code> is identical to
     * the one provided.
     * 
     * @param other
     *            the other <code>VisualObject</code>.
     * @return <code>true</code> if they are identical; <code>false</code>
     *         otherwise.
     */
    public boolean isIdentical(VisualObject other) {

        if (this.equals(other)) {

            boolean sameVisibility = this.isVisible() == other.isVisible();
            boolean sameLabel = ((this.getLabel() == null && other.getLabel() == null) || this
                    .getLabel() != null
                    && this.getLabel().isIdentical(other.getLabel()));
            boolean sameShape = this.getShape() == other.getShape();
            boolean sameScale = ((this.getScale() == null && other.getScale() == null) || this
                    .getScale() != null
                    && this.getScale().equals(other.getScale()));
            boolean samePosition = ((this.getPosition() == null && other
                    .getPosition() == null) || this.getPosition() != null
                    && this.getPosition().equals(other.getPosition()));
            boolean sameRotation = ((this.getRotation() == null && other
                    .getRotation() == null) || this.getRotation() != null
                    && this.getRotation().equals(other.getRotation()));
            boolean sameColor = ((this.getColor() == null && other.getColor() == null) || this
                    .getColor() != null
                    && this.getColor().equals(other.getColor()));

            return (sameVisibility && sameLabel && sameShape && sameScale
                    && samePosition && sameRotation && sameColor);
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj != null && obj instanceof VisualObject) {
            VisualObject other = (VisualObject) obj;

            boolean sameID = this.getID() == other.getID();

            if (!sameID && this.getID() != null) {

                sameID = this.getID().equals(other.getID());
            }

            return sameID;
        }

        return false;
    }

    @Override
    public int hashCode() {

        return this.getID().hashCode();
    }
}
