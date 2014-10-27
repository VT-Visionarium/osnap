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


package edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents;


import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;
import edu.vt.arc.vis.osnap.core.domain.layout.common.Base2DCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.io.prefuse.Constants;
import prefuse.action.layout.graph.RadialTreeLayout;


/**
 * The <code>RadialTreeCoordinateLayoutComponent</code> class computes a radial
 * layout, laying out subsequent depth levels of a tree on circles of
 * progressively increasing radiusIncrement.
 * 
 * @see RadialTreeLayout
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
@XmlType(name = "RadialTreeCoordinateLayoutComponent")
public class RadialTreeCoordinateLayoutComponent
        extends BasePrefuseTreeCoordinateLayoutComponent {

    private final RadialTreeLayout layout;

    @XmlElement(name = "RadiusIncrement")
    private int                    radiusIncrement;

    /**
     * Returns the name of this <code>ILayoutComponent</code>.
     * 
     * @return the name.
     */
    public static String name() {

        return "Radial Tree Coordinate Layout Component (Prefuse)";
    }


    /**
     * Returns the description of this <code>ILayoutComponent</code>.
     * 
     * @return the description.
     */
    public static String description() {

        String description = "The "
                + RadialTreeCoordinateLayoutComponent.name()
                + "  computes a radial layout, laying out subsequent depth"
                + " levels of a tree on circles of progressively increasing"
                + " radiusIncrement.";

        return description;
    }


    /**
     * Returns the capabilities (the set of
     * {@link edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty VisualProperties}
     * that can be provided) of this <code>ILayoutComponent</code>.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return Base2DCoordinateLayoutComponent.capabilities();
    }

    /**
     * Returns the radiusIncrement of the radial tree.
     * 
     * @return radiusIncrement the radiusIncrement.
     */
    public int getRadius() {

        return radiusIncrement;
    }


    /**
     * Sets the radiusIncrement of the radial tree.
     * 
     * @param radiusIncrement
     *            the radiusIncrement.
     */
    public void setRadius(int radiusIncrement) {

        this.radiusIncrement = radiusIncrement;
    }

    /**
     * Creates a new instance of the {@link RadialTreeCoordinateLayoutComponent}
     * class. (Serialization Constructor).
     */
    @SuppressWarnings("unused")
    private RadialTreeCoordinateLayoutComponent() {

        this(null, 50, true);
    }

    /**
     * Creates a new instance of the {@link RadialTreeCoordinateLayoutComponent}
     * class with the provided {@link INode Node} as root of the tree.
     * 
     * @param rootNode
     *            the root node.
     */
    public RadialTreeCoordinateLayoutComponent(INode rootNode) {

        this(rootNode, 50);
    }


    /**
     * Creates a new instance of the {@link RadialTreeCoordinateLayoutComponent}
     * class with the provided {@link INode Node} as root of the tree and the
     * provided radius increment.
     * 
     * @param rootNode
     *            the root node.
     * @param radiusIncrement
     *            the radiusIncrement.
     */
    public RadialTreeCoordinateLayoutComponent(INode rootNode,
            int radiusIncrement) {

        this(rootNode, radiusIncrement, false);
    }

    /**
     * Creates a new instance of the {@link RadialTreeCoordinateLayoutComponent}
     * class with the provided {@link INode Node} as root of the tree and the
     * provided radius increment.
     * 
     * @param rootNode
     *            the root node.
     * @param radiusIncrement
     *            the radius increment between layers of the graph.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    public RadialTreeCoordinateLayoutComponent(INode rootNode,
            int radiusIncrement, boolean serialization) {

        super(RadialTreeCoordinateLayoutComponent.name(),
                RadialTreeCoordinateLayoutComponent.description(), true,
                rootNode, serialization);

        this.radiusIncrement = radiusIncrement;
        this.layout = new RadialTreeLayout(Constants.PREFUSE_GROUP);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.IPrefuseLayoutComponent#getLayout
     * ()
     */
    @Override
    public RadialTreeLayout getLayout() {

        return this.layout;
    }

    @Override
    public void applyParameters() {

        super.applyParameters();

        this.getLayout().setRadiusIncrement(radiusIncrement);
    }
}
