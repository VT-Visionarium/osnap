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


package edu.vt.arc.vis.osnap.layout.simpleComponents;


import edu.vt.arc.vis.osnap.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.layout.common.BaseColorLayoutComponent;
import edu.vt.arc.vis.osnap.layout.common.IColorLayoutComponent;
import edu.vt.arc.vis.osnap.layout.common.ILayoutComponent;
import edu.vt.arc.vis.osnap.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.visualization.VisualNode;
import edu.vt.arc.vis.osnap.visualization.VisualProperty;
import javafx.scene.paint.Color;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The {@link SimpleColorLayoutComponent} class provides a basic implementation
 * of the {@link IColorLayoutComponent} interface, setting the {@link Color} of
 * {@link IGraphObject graph objects} to a single color.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlRootElement(name = "SimpleColorLayoutComponent")
@XmlType(name = "SimpleColorLayoutComponent")
@XmlAccessorType(XmlAccessType.NONE)
public class SimpleColorLayoutComponent
        extends BaseColorLayoutComponent {

    @XmlElement(name = "Color")
    private Color color;


    /**
     * Returns the name of this {@link ILayoutComponent}.
     * 
     * @return the name.
     */
    public static String name() {

        return "Simple Color Layout Component";
    }


    /**
     * Returns the description of this {@link ILayoutComponent}.
     * 
     * @return the description.
     */
    public static String description() {

        return "The " + SimpleColorLayoutComponent.name()
                + " provides a single color for graph objects(nodes or edges).";
    }


    /**
     * Returns the capabilities (the set of
     * {@link edu.vt.arc.vis.osnap.visualization.VisualProperty VisualProperties}
     * that can be provided) of this {@link ILayoutComponent}.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return BaseColorLayoutComponent.capabilities();
    }



    /**
     * Returns the color.
     * 
     * @return the color.
     */
    public Color getColor() {

        return this.color;
    }


    /**
     * Sets the color.
     * 
     * @param color
     *            the color.
     */
    public void setColor(Color color) {

        this.color = color;
    }


    /**
     * Creates a new instance of the {@link SimpleColorLayoutComponent} class
     * which sets the color of graph objects to black.
     */
    public SimpleColorLayoutComponent() {

        this(Color.WHITE);
    }


    /**
     * Creates a new instance of the {@link SimpleColorLayoutComponent} class
     * with the provided {@link Color}.
     * 
     * @param color
     *            the intended color for the
     *            {@link edu.vt.arc.vis.osnap.graph.common.IGraphObject
     *            IGraphObjects}.
     */
    public SimpleColorLayoutComponent(final Color color) {

        super(SimpleColorLayoutComponent.capabilities(),
                SimpleColorLayoutComponent.name(), SimpleColorLayoutComponent
                        .description(), false);

        this.color = color;

        this.setName(this.getName() + " (" + this.color.toString() + ")");
        this.setDescription(this.getDescription() + "\n\tThe color is set to "
                + this.color.toString() + ".");
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap
     * .visualization.VisualNode)
     */
    @Override
    public void layout(VisualNode visualNode) {

        if (this.isEnabled(VisualProperty.NODE_COLOR)) {

            visualNode.setColor(this.color);
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap
     * .visualization.VisualEdge)
     */
    @Override
    public void layout(VisualEdge visualEdge) {

        if (this.isEnabled(VisualProperty.EDGE_COLOR)) {

            visualEdge.setColor(this.color);
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap
     * .visualization.VisualHyperEdge)
     */
    @Override
    public void layout(VisualHyperEdge visualHyperEdge) {

        if (this.isEnabled(VisualProperty.HYPEREDGE_COLOR)) {

            visualHyperEdge.setColor(this.color);
        }
    }

    @Override
    public boolean equals(Object obj) {

        if (super.equals(obj) && obj instanceof SimpleColorLayoutComponent) {

            SimpleColorLayoutComponent other = (SimpleColorLayoutComponent) obj;

            boolean sameColor = this.getColor().equals(other.getColor());

            return sameColor;
        }
        return false;
    }

    @Override
    public int hashCode() {

        // TODO Auto-generated method stub
        return super.hashCode();
    }
}
