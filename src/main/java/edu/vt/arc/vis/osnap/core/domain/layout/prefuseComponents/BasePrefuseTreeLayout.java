package edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents;


//@formatter:off
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
//@formatter:on


import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.core.domain.graph.base.NodeBase;
import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;
import edu.vt.arc.vis.osnap.core.domain.layout.common.Base2DCoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.CoordinateComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ICoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;


/**
 * The abstract {@code BasePrefuseTreeLayout} class provides common
 * functionality of all @{link IPrefuseTreeLayout
 * IPrefuseTreeLayoutComponents} based on the {@link prefuse Prefuse} toolkit.
 * <p>
 * Notably, it provides the mechanics for setting the root {@link INode Node} of
 * the layout.
 * 
 * @author Peter J. Radics
 * @version 1.2.3
 * @since 0.1.0
 */
@XmlType(name = "BasePrefuseTreeLayout")
public abstract class BasePrefuseTreeLayout
        extends BasePrefuseLayout
        implements IPrefuseTreeLayout {

    @XmlElement(name = "RootNode", type = NodeBase.class)
    private final INode rootNode;


    /**
     * Returns the name of this {@link ILayout}.
     * 
     * @return the name.
     */
    public static String name() {

        return "Prefuse 2D Tree Layout";
    }


    /**
     * Returns the description of this {@link ILayout}.
     * 
     * @return the description.
     */
    public static String description() {

        return "Provides coordinates for at most two of the X, Y, or Z "
                + "components of a node.";
    }



    /**
     * Returns the capabilities (the set of {@link VisualProperty
     * VisualProperties} that can be provided) of this {@link ILayout}.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return Base2DCoordinateLayout.capabilities();
    }

    /**
     * Returns the coordinate components (the set of {@link CoordinateComponent
     * CoordinateComponents} that can be provided) by this
     * {@link ICoordinateLayout}.
     * 
     * @return the components.
     */
    public static Set<CoordinateComponent> components() {

        return Base2DCoordinateLayout.components();
    }



    @Override
    public INode getRootNode() {

        return this.rootNode;
    }



    /**
     * Creates a new instance of the {@code BasePrefuseTreeLayout} class.
     * (Serialization Constructor).
     */
    public BasePrefuseTreeLayout() {

        this(null, null, false, null, true);
    }


    /**
     * Creates a new instance of the {@code BasePrefuseTreeLayout} class. It
     * sets the description and name to the provided values and optionally
     * appends it with a random number.
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
    public BasePrefuseTreeLayout(final String name, final String description,
            final boolean randomizeName, final INode rootNode) {

        this(name, description, randomizeName, rootNode, false);
    }

    /**
     * Creates a new instance of the {@code BasePrefuseTreeLayout} class. It
     * sets the description and name to the provided values and optionally
     * appends it with a random number.
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
    protected BasePrefuseTreeLayout(final String name,
            final String description, final boolean randomizeName,
            final INode rootNode, final boolean serialization) {

        super(name, description, randomizeName, serialization);

        this.rootNode = rootNode;
    }
}
