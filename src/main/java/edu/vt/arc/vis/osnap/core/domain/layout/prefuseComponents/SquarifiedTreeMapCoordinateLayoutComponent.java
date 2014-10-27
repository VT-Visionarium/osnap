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

import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;
import edu.vt.arc.vis.osnap.core.domain.layout.common.Base2DCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.io.prefuse.Constants;
import prefuse.action.layout.graph.SquarifiedTreeMapLayout;


/**
 * The <code>SquarifiedTreeMapCoordinateLayoutComponent</code> class computes a
 * TreeMap layout that optimizes for low aspect ratios of visualized tree nodes.
 * TreeMaps are a form of space-filling layout that represents nodes as boxes on
 * the display, with children nodes represented as boxes placed within their
 * parent'scale box.
 * 
 * @see SquarifiedTreeMapLayout
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
@XmlType(name = "SquarifiedTreeMapCoordinateLayoutComponent")
public class SquarifiedTreeMapCoordinateLayoutComponent
        extends BasePrefuseTreeCoordinateLayoutComponent {

    private final SquarifiedTreeMapLayout layout;

    private double                        frameWidth;

    /**
     * Returns the name of this <code>ILayoutComponent</code>.
     * 
     * @return the name.
     */
    public static String name() {

        return "Squarified Tree Map Coordinate Layout Component (Prefuse)";
    }


    /**
     * Returns the description of this <code>ILayoutComponent</code>.
     * 
     * @return the description.
     */
    public static String description() {

        String description = "The "
                + SquarifiedTreeMapCoordinateLayoutComponent.name()
                + " computes a TreeMap layout that optimizes for low aspect"
                + " ratios of visualized tree nodes. TreeMaps are a form of"
                + " space-filling layout that represents nodes as boxes on the"
                + " display, with children nodes represented as boxes placed"
                + " within their parent'scale box.";

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
     * Returns the frame width of the SquarifiedTreeMap.
     * 
     * @return the frame width.
     */
    public double getFrameWidth() {

        return this.frameWidth;
    }


    /**
     * Sets the frame width of the SquarifiedTreeMap.
     * 
     * @param frameWidth
     *            the frame width.
     */
    public void setFrameWidth(double frameWidth) {

        this.frameWidth = frameWidth;
    }


    /**
     * Creates a new instance of the
     * {@link SquarifiedTreeMapCoordinateLayoutComponent} class. (Serialization
     * Constructor)
     * 
     */
    public SquarifiedTreeMapCoordinateLayoutComponent() {

        this(null, 0, true);
    }

    /**
     * Creates a new instance of the
     * {@link SquarifiedTreeMapCoordinateLayoutComponent} class with the
     * provided {@link INode Node} as root of the tree.
     * 
     * @param rootNode
     *            the root node.
     */
    public SquarifiedTreeMapCoordinateLayoutComponent(INode rootNode) {

        this(rootNode, 0);
    }


    /**
     * Creates a new instance of the
     * {@link SquarifiedTreeMapCoordinateLayoutComponent} class with the
     * provided {@link INode Node} as root of the tree and the provided frame
     * width.
     * 
     * @param rootNode
     *            the root node.
     * @param frameWidth
     *            the frame width.
     */
    public SquarifiedTreeMapCoordinateLayoutComponent(INode rootNode,
            double frameWidth) {

        this(rootNode, frameWidth, false);
    }

    /**
     * Creates a new instance of the
     * {@link SquarifiedTreeMapCoordinateLayoutComponent} class with the
     * provided {@link INode Node} as root of the tree and the provided frame
     * width.
     * 
     * @param rootNode
     *            the root node.
     * @param frameWidth
     *            the frame width.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    public SquarifiedTreeMapCoordinateLayoutComponent(INode rootNode,
            double frameWidth, boolean serialization) {

        super(SquarifiedTreeMapCoordinateLayoutComponent.name(),
                SquarifiedTreeMapCoordinateLayoutComponent.description(), true,
                rootNode, serialization);


        this.setFrameWidth(frameWidth);
        this.layout = new SquarifiedTreeMapLayout(Constants.PREFUSE_GROUP);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.IPrefuseLayoutComponent#getLayout
     * ()
     */
    @Override
    public SquarifiedTreeMapLayout getLayout() {

        return this.layout;
    }

    @Override
    public void applyParameters() {

        super.applyParameters();

        this.getLayout().setFrameWidth(this.getFrameWidth());
    }
}
