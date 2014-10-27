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
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;
import edu.vt.arc.vis.osnap.core.domain.layout.common.Base2DCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.io.prefuse.Constants;
import prefuse.action.layout.graph.NodeLinkTreeLayout;


/**
 * The <code>NodeLinkTreeCoordinateLayoutComponent</code> class computes a tidy
 * layout of a node-link tree diagram. This algorithm lays out a rooted tree
 * such that each depth level of the tree is on a shared line. The orientation
 * of the tree can be set such that the tree goes left-to-right (default),
 * right-to-left, top-to-bottom, or bottom-to-top.
 * 
 * @see NodeLinkTreeLayout
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
@XmlType(name = "NodeLinkTreeCoordinateLayoutComponent")
public class NodeLinkTreeCoordinateLayoutComponent
        extends BasePrefuseTreeCoordinateLayoutComponent {



    private final NodeLinkTreeLayout layout;


    @XmlElement(name = "Orientation")
    private Orientation              orientation;
    @XmlElement(name = "DepthSpacing")
    private double                   depthSpacing;
    @XmlElement(name = "SpaceBetweenSiblings")
    private double                   spaceBetweenSiblings;
    @XmlElement(name = "SpaceBetweenNeighboringSubtrees")
    private double                   spaceBetweenNeighboringSubtrees;

    /**
     * Returns the name of this <code>ILayoutComponent</code>.
     * 
     * @return the name.
     */
    public static String name() {

        return "Node Link Tree Coordinate Layout Component (Prefuse)";
    }


    /**
     * Returns the description of this <code>ILayoutComponent</code>.
     * 
     * @return the description.
     */
    public static String description() {

        String description = "The "
                + NodeLinkTreeCoordinateLayoutComponent.name()
                + " computes a tidy layout of a node-link tree diagram. This"
                + " algorithm lays out a rooted tree such that each depth level"
                + " of the tree is on a shared line. The orientation of the"
                + " tree can be set such that the tree goes left-to-right"
                + " (default), right-to-left, top-to-bottom, or bottom-to-top.";

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
     * Returns the orientation.
     * 
     * @return the orientation.
     */
    public Orientation getOrientation() {

        return this.orientation;
    }



    /**
     * Setsthe orientation.
     * 
     * @param orientation
     *            the orientation.
     */
    public void setOrientation(Orientation orientation) {

        this.orientation = orientation;
    }



    /**
     * Returns the depth spacing
     * 
     * @return the depth spacing.
     */
    public double getDepthSpacing() {

        return this.depthSpacing;
    }



    /**
     * Sets the depth spacing.
     * 
     * @param depthSpacing
     *            the depth spacing.
     */
    public void setDepthSpacing(double depthSpacing) {

        this.depthSpacing = depthSpacing;
    }



    /**
     * Returns the space between sibling nodes.
     * 
     * @return the space between sibling nodes.
     */
    public double getSpaceBetweenSiblings() {

        return this.spaceBetweenSiblings;
    }



    /**
     * Sets the space between sibling nodes.
     * 
     * @param spaceBetweenSiblings
     *            the space between sibling nodes.
     */
    public void setSpaceBetweenSiblings(double spaceBetweenSiblings) {

        this.spaceBetweenSiblings = spaceBetweenSiblings;
    }



    /**
     * Returns the space between neighboring subtrees.
     * 
     * @return the space between neighboring subtrees.
     */
    public double getSpaceBetweenNeighboringSubtrees() {

        return this.spaceBetweenNeighboringSubtrees;
    }



    /**
     * Sets the space between neighboring subtrees.
     * 
     * @param spaceBetweenNeighboringSubtrees
     *            the space between neighboring subtrees.
     */
    public void setSpaceBetweenNeighboringSubtrees(
            double spaceBetweenNeighboringSubtrees) {

        this.spaceBetweenNeighboringSubtrees = spaceBetweenNeighboringSubtrees;
    }

    /**
     * Creates a new instance of the
     * {@link NodeLinkTreeCoordinateLayoutComponent} class. (Serialization
     * Constructor).
     */
    @SuppressWarnings("unused")
    private NodeLinkTreeCoordinateLayoutComponent() {

        this(null, Orientation.LEFT_TO_RIGHT, 50.0, 5.0, 25.0, true);
    }

    /**
     * Creates a new instance of the
     * {@link NodeLinkTreeCoordinateLayoutComponent} class with the provided
     * {@link INode Node} as root of the tree.
     * 
     * @param rootNode
     *            the root node.
     */
    public NodeLinkTreeCoordinateLayoutComponent(INode rootNode) {

        this(rootNode, Orientation.LEFT_TO_RIGHT, 50.0, 5.0, 25.0);
    }

    /**
     * Creates a new instance of the
     * {@link NodeLinkTreeCoordinateLayoutComponent} class with the provided
     * {@link INode Node} as root of the tree and the provided orientation,
     * depth spacing, space between siblings and neighboring subtrees.
     * 
     * @param rootNode
     *            the root node.
     * @param orientation
     *            the orientation of the layout.
     * @param depthSpacing
     *            the spacing between hierarchies of the tree.
     * @param spaceBetweenSiblings
     *            the spacing between siblings.
     * @param spaceBetweenNeighboringSubtrees
     *            the spacing between neighboring subtrees.
     */
    public NodeLinkTreeCoordinateLayoutComponent(INode rootNode,
            Orientation orientation, double depthSpacing,
            double spaceBetweenSiblings, double spaceBetweenNeighboringSubtrees) {

        this(rootNode, orientation, depthSpacing, spaceBetweenSiblings,
                spaceBetweenNeighboringSubtrees, false);
    }

    /**
     * Creates a new instance of the
     * {@link NodeLinkTreeCoordinateLayoutComponent} class with the provided
     * {@link INode Node} as root of the tree and the provided orientation,
     * depth spacing, space between siblings and neighboring subtrees.
     * 
     * @param rootNode
     *            the root node.
     * @param orientation
     *            the orientation of the layout.
     * @param depthSpacing
     *            the spacing between hierarchies of the tree.
     * @param spaceBetweenSiblings
     *            the spacing between siblings.
     * @param spaceBetweenNeighboringSubtrees
     *            the spacing between neighboring subtrees.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    public NodeLinkTreeCoordinateLayoutComponent(INode rootNode,
            Orientation orientation, double depthSpacing,
            double spaceBetweenSiblings,
            double spaceBetweenNeighboringSubtrees, boolean serialization) {

        super(NodeLinkTreeCoordinateLayoutComponent.name(),
                NodeLinkTreeCoordinateLayoutComponent.description(), true,
                rootNode, serialization);

        this.orientation = orientation;
        this.depthSpacing = depthSpacing;
        this.spaceBetweenSiblings = spaceBetweenSiblings;
        this.spaceBetweenNeighboringSubtrees = spaceBetweenNeighboringSubtrees;

        this.layout = new NodeLinkTreeLayout(Constants.PREFUSE_GROUP);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.IPrefuseLayoutComponent#getLayout
     * ()
     */
    @Override
    public NodeLinkTreeLayout getLayout() {

        return this.layout;
    }

    @Override
    public void applyParameters() {

        super.applyParameters();

        int orient = -1;

        switch (this.orientation) {
            case BOTTOM_TO_TOP:
                orient = prefuse.Constants.ORIENT_TOP_BOTTOM;
                break;
            case RIGHT_TO_LEFT:
                orient = prefuse.Constants.ORIENT_RIGHT_LEFT;
                break;
            default:
            case TOP_TO_BOTTOM:
                orient = prefuse.Constants.ORIENT_BOTTOM_TOP;
                break;
            case LEFT_TO_RIGHT:
                orient = prefuse.Constants.ORIENT_LEFT_RIGHT;
                break;

        }
        this.getLayout().setBreadthSpacing(this.spaceBetweenSiblings);
        this.getLayout().setDepthSpacing(this.depthSpacing);
        this.getLayout().setOrientation(orient);
        this.getLayout()
                .setSubtreeSpacing(this.spaceBetweenNeighboringSubtrees);
    }


    /**
     * Possible orientations of a tree layout.
     * 
     * @author Peter J. Radics
     * @version 0.1
     */
    @XmlEnum
    public enum Orientation {
        /**
         * Orients the tree left to right.
         */
        @XmlEnumValue(value = "LeftToRight")
        LEFT_TO_RIGHT,
        /**
         * Orients the tree right to left.
         */
        @XmlEnumValue(value = "RightToLeft")
        RIGHT_TO_LEFT,
        /**
         * Orients the tree top to bottom.
         */
        @XmlEnumValue(value = "TopToBottom")
        TOP_TO_BOTTOM,
        /**
         * Orients the tree bottom to top.
         */
        @XmlEnumValue(value = "BottomToTop")
        BOTTOM_TO_TOP;
    }

}
