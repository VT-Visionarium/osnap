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


import edu.vt.arc.vis.osnap.core.domain.layout.common.BaseScaleLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.math.geometry.IScale;
import org.jutility.math.geometry.Scale;
import org.jutility.math.geometry.Scalef;


/**
 * The <code>SimpleScaleLayoutComponent</code> class provides a basic
 * implementation of the
 * {@link edu.vt.arc.vis.osnap.core.domain.layout.common.IScaleLayoutComponent} interface,
 * setting the {@link Scalef Scale} of nodes, edges, and hyperedges to (1, 1,
 * 1).
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlType(name = "SimpleScaleLayoutComponent")
public class SimpleScaleLayoutComponent
        extends BaseScaleLayoutComponent {


    @XmlElement(name = "Scale", type = Scale.class)
    private IScale<?> scale;


    /**
     * Returns the name of this <code>ILayoutComponent</code>.
     * 
     * @return the name.
     */
    public static String name() {

        return "Simple Scale Layout Component";
    }


    /**
     * Returns the description of this <code>ILayoutComponent</code>.
     * 
     * @return the description.
     */
    public static String description() {

        return "The " + SimpleScaleLayoutComponent.name()
                + "provides a single scale for graph objects(nodes or edges).";
    }


    /**
     * Returns the capabilities (the set of
     * {@link edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty VisualProperties}
     * that can be provided) of this <code>ILayoutComponent</code>.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return BaseScaleLayoutComponent.capabilities();
    }


    /**
     * Returns the scale.
     * 
     * @return the scale.
     */
    public IScale<?> getScale() {

        return this.scale;
    }


    /**
     * Sets the scale.
     * 
     * @param scale
     *            the sacle.
     */
    public void setScale(IScale<?> scale) {

        this.scale = scale;
    }



    /**
     * Creates a new instance of the <code>SimpleScaleLayoutComponent</code>
     * class which sets the scale of graph objects to (1, 1, 1).
     */
    public SimpleScaleLayoutComponent() {

        this(new Scalef());
    }

    /**
     * Creates a new instance of the <code>SimpleScaleLayoutComponent</code>
     * class with the provided {@link Scalef Scale}.
     * 
     * @param scale
     *            the intended scale for the graph objects.
     */
    public SimpleScaleLayoutComponent(final IScale<?> scale) {

        super(SimpleScaleLayoutComponent.capabilities(),
                SimpleScaleLayoutComponent.name(), SimpleScaleLayoutComponent
                        .description(), false);

        this.scale = scale;

        this.setName(this.getName() + " (" + this.scale.toString() + ")");
        this.setDescription(this.getDescription() + "\n\tThe scale is set to "
                + this.scale.toString() + ".");
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

        if (this.isEnabled(VisualProperty.NODE_SCALE)) {

            visualNode.setScale(this.scale);
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

        if (this.isEnabled(VisualProperty.EDGE_SCALE)) {

            visualEdge.setScale(this.scale);
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

        if (this.isEnabled(VisualProperty.HYPEREDGE_SCALE)) {

            visualHyperEdge.setScale(this.scale);
        }
    }
}
