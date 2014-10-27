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


package edu.vt.arc.vis.osnap.layout.prefuseComponents;


import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.graph.base.NodeBase;
import edu.vt.arc.vis.osnap.graph.common.INode;
import edu.vt.arc.vis.osnap.layout.common.Base2DCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.layout.common.ILayoutComponent;
import edu.vt.arc.vis.osnap.visualization.VisualProperty;


/**
 * The abstract {@link BasePrefuseTreeCoordinateLayoutComponent} class provides
 * common functionality of all @{link IPrefuseTreeLayoutComponent
 * IPrefuseTreeLayoutComponents} based on the {@link prefuse Prefuse} toolkit.
 * <p>
 * Notably, it provides the mechanics for setting the root {@link INode Node} of
 * the layout.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
@XmlType(name = "BasePrefuseTreeCoordinateLayoutComponent")
public abstract class BasePrefuseTreeCoordinateLayoutComponent
        extends BasePrefuseCoordinateLayoutComponent
        implements IPrefuseTreeLayoutComponent {

    @XmlElement(name = "RootNode", type = NodeBase.class)
    private final INode rootNode;


    /**
     * Returns the name of this {@link ILayoutComponent}.
     * 
     * @return the name.
     */
    public static String name() {

        return "Prefuse 2D Tree Coordinate Layout Component";
    }


    /**
     * Returns the description of this {@link ILayoutComponent}.
     * 
     * @return the description.
     */
    public static String description() {

        return "Provides coordinates for at most two of the X, Y, or Z "
                + "components of a node.";
    }


    /**
     * Returns the capabilities (the set of {@link VisualProperty
     * VisualProperties} that can be provided) of this {@link ILayoutComponent}.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return Base2DCoordinateLayoutComponent.capabilities();
    }

    /**
     * Creates a new instance of the
     * {@link BasePrefuseTreeCoordinateLayoutComponent} class. (Serialization
     * Constructor).
     */
    public BasePrefuseTreeCoordinateLayoutComponent() {

        this(null, null, false, null, true);
    }


    /**
     * Creates a new instance of the
     * {@link BasePrefuseTreeCoordinateLayoutComponent} class. It sets the
     * description and name to the provided values and optionally appends it
     * with a random number.
     * 
     * @param name
     *            the name of the layout component.
     * @param description
     *            the description of the layout component.
     * @param randomizeName
     *            whether or not to append a random number to the name.
     * @param rootNode
     *            the root node of this tree layout.
     */
    public BasePrefuseTreeCoordinateLayoutComponent(final String name,
            final String description, final boolean randomizeName,
            final INode rootNode) {

        this(name, description, randomizeName, rootNode, false);
    }

    /**
     * Creates a new instance of the
     * {@link BasePrefuseTreeCoordinateLayoutComponent} class. It sets the
     * description and name to the provided values and optionally appends it
     * with a random number.
     * 
     * @param name
     *            the name of the layout component.
     * @param description
     *            the description of the layout component.
     * @param randomizeName
     *            whether or not to append a random number to the name.
     * @param rootNode
     *            the root node of this tree layout.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    protected BasePrefuseTreeCoordinateLayoutComponent(final String name,
            final String description, final boolean randomizeName,
            final INode rootNode, final boolean serialization) {

        super(name, description, randomizeName, serialization);

        this.rootNode = rootNode;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.prefuseComponents.IPrefuseTreeLayoutComponent#
     * getRootNode()
     */
    @Override
    public INode getRootNode() {

        return this.rootNode;
    }
}
