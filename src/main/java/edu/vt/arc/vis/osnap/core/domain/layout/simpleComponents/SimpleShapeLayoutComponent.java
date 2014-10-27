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


package edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents;


import edu.vt.arc.vis.osnap.core.domain.layout.common.BaseShapeLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.visualization.Shape;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The <code>SimpleShapeLayoutComponent</code> class provides a basic
 * implementation of the
 * {@link edu.vt.arc.vis.osnap.core.domain.layout.common.IShapeLayoutComponent} interface,
 * setting the {@link Shape} of graphObjects to the provided value.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlType(name = "SimpleShapeLayoutComponent")
public class SimpleShapeLayoutComponent
        extends BaseShapeLayoutComponent {

    @XmlElement(name = "Shape")
    private Shape shape;


    /**
     * Returns the name of this <code>ILayoutComponent</code>.
     * 
     * @return the name.
     */
    public static String name() {

        return "Simple Shape Layout Component";
    }


    /**
     * Returns the description of this <code>ILayoutComponent</code>.
     * 
     * @return the description.
     */
    public static String description() {

        return "The "
                + SimpleShapeLayoutComponent.name()
                + " provides a single shape for the selected graph objects (nodes or edges).";
    }


    /**
     * Returns the capabilities (the set of
     * {@link edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty VisualProperties}
     * that can be provided) of this <code>ILayoutComponent</code>.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return BaseShapeLayoutComponent.capabilities();
    }



    /**
     * Returns the shape.
     * 
     * @return the shape.
     */
    public Shape getShape() {

        return this.shape;
    }


    /**
     * Sets the shape.
     * 
     * @param shape
     *            the shape.
     */
    public void setShape(Shape shape) {

        this.shape = shape;
    }

    /**
     * Creates a new instance of the <code>SimpleShapeLayoutComponent</code>
     * class which sets the shape of graph objects to a sphere
     */
    public SimpleShapeLayoutComponent() {

        this(Shape.SPHERE);
    }


    /**
     * Creates a new instance of the <code>SimpleShapeLayoutComponent</code>
     * class.
     * 
     * @param shape
     *            the intended shape of the graphObjects.
     */
    public SimpleShapeLayoutComponent(final Shape shape) {

        super(SimpleShapeLayoutComponent.capabilities(),
                SimpleShapeLayoutComponent.name(), SimpleShapeLayoutComponent
                        .description(), false);

        this.shape = shape;

        this.setName(this.getName() + " (" + this.shape.toString() + ")");
        this.setDescription(this.getDescription() + "\n\tThe Shape is set to "
                + this.shape.toString() + ".");
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.domain.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap.core
     * .visualization.VisualNode)
     */
    @Override
    public void layout(VisualNode visualNode) {

        if (this.isEnabled(VisualProperty.NODE_SHAPE)) {

            visualNode.setShape(this.shape);
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.domain.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap.core
     * .visualization.VisualEdge)
     */
    @Override
    public void layout(VisualEdge visualEdge) {

        if (this.isEnabled(VisualProperty.EDGE_SHAPE)) {

            visualEdge.setShape(this.shape);
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.domain.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap.core
     * .visualization.VisualHyperEdge)
     */
    @Override
    public void layout(VisualHyperEdge visualHyperEdge) {

        if (this.isEnabled(VisualProperty.HYPEREDGE_SHAPE)) {

            visualHyperEdge.setShape(this.shape);
        }
    }
}
