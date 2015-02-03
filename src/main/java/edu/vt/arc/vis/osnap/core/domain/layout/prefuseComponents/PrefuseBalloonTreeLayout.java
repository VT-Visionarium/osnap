package edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents;


//@formatter:off
/*
* _
* The Open Semantic Network Analysis Platform (OSNAP)
* _
* Copyright (C) 2012 - 2015 Visionarium at Virginia Tech
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

import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;
import edu.vt.arc.vis.osnap.core.domain.layout.common.Base2DCoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.CoordinateComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ICoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.io.prefuse.Constants;
import prefuse.action.layout.graph.BalloonTreeLayout;


/**
 * The {@code PrefuseBalloonTreeLayout} class computes a circular "balloon-tree"
 * layout of a tree. This layout places children nodes radially around their
 * parents, and is equivalent to a top-down flattened view of a ConeTree.
 * 
 * @see BalloonTreeLayout
 * 
 * @author Peter J. Radics
 * @version 1.2.3
 * @since 0.1.0
 */
@XmlType(name = "PrefuseBalloonTreeLayout")
public class PrefuseBalloonTreeLayout
        extends BasePrefuseTreeLayout {

    private final BalloonTreeLayout layout;

    @XmlElement(name = "MinimumRadius")
    private int                     minimumRadius;

    /**
     * Returns the name of this {@link ILayout}.
     * 
     * @return the name.
     */
    public static String name() {

        return "Balloon Tree Layout (Prefuse)";
    }


    /**
     * Returns the description of this {@link ILayout}.
     * 
     * @return the description.
     */
    public static String description() {

        String description = "The "
                + PrefuseBalloonTreeLayout.name()
                + " computes a circular \"balloon-tree\" layout of a tree. This "
                + "layout places children nodes radially around their parents, "
                + "and is equivalent to a top-down flattened view of a ConeTree.";

        return description;
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

    /**
     * Returns the minimum radius of the BalloonTree.
     * 
     * @return the minimum radius.
     */
    public int getMinimumRadius() {

        return this.minimumRadius;
    }

    /**
     * Sets the minimum radius of the BalloonTree.
     * 
     * @param minimumRadius
     *            the minimum radius.
     */
    public void setMinimumRadius(int minimumRadius) {

        this.minimumRadius = minimumRadius;
    }

    /**
     * Creates a new instance of the {@link PrefuseBalloonTreeLayout} class.
     * (Serialization Constructor).
     */
    @SuppressWarnings("unused")
    private PrefuseBalloonTreeLayout() {

        this(null, 2, true);
    }

    /**
     * Creates a new instance of the {@link PrefuseBalloonTreeLayout} class with
     * the provided {@link INode Node} as root of the tree.
     * 
     * @param rootNode
     *            the root node.
     */
    public PrefuseBalloonTreeLayout(INode rootNode) {

        this(rootNode, 2);
    }


    /**
     * Creates a new instance of the {@link PrefuseBalloonTreeLayout} class with
     * the provided {@link INode Node} as root of the tree and the provided
     * minimum radius.
     * 
     * @param rootNode
     *            the root {@link INode node}.
     * @param minimumRadius
     *            the minimum radius.
     */
    public PrefuseBalloonTreeLayout(final INode rootNode,
            final int minimumRadius) {

        this(rootNode, minimumRadius, false);
    }

    /**
     * Creates a new instance of the {@link PrefuseBalloonTreeLayout} class with
     * the provided {@link INode Node} as root of the tree and the provided
     * minimum radius.
     * 
     * @param rootNode
     *            the root {@link INode node}.
     * @param minimumRadius
     *            the minimum radius.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    private PrefuseBalloonTreeLayout(final INode rootNode,
            final int minimumRadius, final boolean serialization) {

        super(PrefuseBalloonTreeLayout.name(), PrefuseBalloonTreeLayout
                .description(), true, rootNode, serialization);

        this.minimumRadius = minimumRadius;
        this.layout = new BalloonTreeLayout(Constants.PREFUSE_GROUP,
                minimumRadius);
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.
     * IPrefuseLayout#getLayout ()
     */
    @Override
    public BalloonTreeLayout getLayout() {

        return this.layout;
    }

    @Override
    public void applyParameters() {

        super.applyParameters();
        this.getLayout().setMinRadius(this.minimumRadius);
    }
}
