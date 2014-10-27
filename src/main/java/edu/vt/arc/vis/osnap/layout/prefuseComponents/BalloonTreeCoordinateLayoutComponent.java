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

import edu.vt.arc.vis.osnap.conversion.prefuse.Constants;
import edu.vt.arc.vis.osnap.graph.common.INode;
import edu.vt.arc.vis.osnap.layout.common.Base2DCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.layout.common.ILayoutComponent;
import edu.vt.arc.vis.osnap.visualization.VisualProperty;
import prefuse.action.layout.graph.BalloonTreeLayout;


/**
 * The {@link BalloonTreeCoordinateLayoutComponent} class computes a circular
 * "balloon-tree" layout of a tree. This layout places children nodes radially
 * around their parents, and is equivalent to a top-down flattened view of a
 * ConeTree.
 * 
 * @see BalloonTreeLayout
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
@XmlType(name = "BalloonTreeCoordinateLayoutComponent")
public class BalloonTreeCoordinateLayoutComponent
        extends BasePrefuseTreeCoordinateLayoutComponent {

    private final BalloonTreeLayout layout;

    @XmlElement(name = "MinimumRadius")
    private int                     minimumRadius;

    /**
     * Returns the name of this {@link ILayoutComponent}.
     * 
     * @return the name.
     */
    public static String name() {

        return "Balloon Tree Coordinate Layout Component (Prefuse)";
    }


    /**
     * Returns the description of this {@link ILayoutComponent}.
     * 
     * @return the description.
     */
    public static String description() {

        String description = "The "
                + BalloonTreeCoordinateLayoutComponent.name()
                + " computes a circular \"balloon-tree\" layout of a tree. This "
                + "layout places children nodes radially around their parents, "
                + "and is equivalent to a top-down flattened view of a ConeTree.";

        return description;
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
     * Creates a new instance of the
     * {@link BalloonTreeCoordinateLayoutComponent} class. (Serialization
     * Constructor).
     */
    @SuppressWarnings("unused")
    private BalloonTreeCoordinateLayoutComponent() {

        this(null, 2, true);
    }

    /**
     * Creates a new instance of the
     * {@link BalloonTreeCoordinateLayoutComponent} class with the provided
     * {@link INode Node} as root of the tree.
     * 
     * @param rootNode
     *            the root node.
     */
    public BalloonTreeCoordinateLayoutComponent(INode rootNode) {

        this(rootNode, 2);
    }


    /**
     * Creates a new instance of the
     * {@link BalloonTreeCoordinateLayoutComponent} class with the provided
     * {@link INode Node} as root of the tree and the provided minimum radius.
     * 
     * @param rootNode
     *            the root {@link INode node}.
     * @param minimumRadius
     *            the minimum radius.
     */
    public BalloonTreeCoordinateLayoutComponent(final INode rootNode,
            final int minimumRadius) {

        this(rootNode, minimumRadius, false);
    }

    /**
     * Creates a new instance of the
     * {@link BalloonTreeCoordinateLayoutComponent} class with the provided
     * {@link INode Node} as root of the tree and the provided minimum radius.
     * 
     * @param rootNode
     *            the root {@link INode node}.
     * @param minimumRadius
     *            the minimum radius.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    private BalloonTreeCoordinateLayoutComponent(final INode rootNode,
            final int minimumRadius, final boolean serialization) {

        super(BalloonTreeCoordinateLayoutComponent.name(),
                BalloonTreeCoordinateLayoutComponent.description(), true,
                rootNode, serialization);

        this.minimumRadius = minimumRadius;
        this.layout = new BalloonTreeLayout(Constants.PREFUSE_GROUP,
                minimumRadius);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.prefuseComponents.IPrefuseLayoutComponent#getLayout
     * ()
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
