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


package edu.vt.arc.vis.osnap.javafx.wizards.statusobjects;

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


import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.NodeLinkTreeCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.NodeLinkTreeCoordinateLayoutComponent.Orientation;


/**
 * Status object to hold info for the layout matching its name
 * 
 * @author Shawn P Neuman
 * 
 */
public class NodeLinkTreeStatus
        extends PrefuseTreeStatusObject {


    @Override
    public void setRootNode(INode node) {

        super.setRootNode(node);

        if (node != null) {

            NodeLinkTreeCoordinateLayoutComponent layoutProvider = new NodeLinkTreeCoordinateLayoutComponent(
                    node);
            this.setLayoutComponent(layoutProvider);

        }
        else {
            this.setLayoutComponent(null);
        }
    }

    /**
     * @return current orientation
     */
    public Orientation getOrientation() {

        if (this.getLayoutComponent() != null) {

            return this.getLayoutComponent().getOrientation();
        }
        return null;
    }

    /**
     * @param orientation
     *            the orientation.
     */
    public void setOrientation(Orientation orientation) {

        if (this.getLayoutComponent() != null) {

            this.getLayoutComponent().setOrientation(orientation);
            setChanged();
            notifyObservers("" + getOrientation());
        }
    }

    /**
     * @return obvious
     */
    public Double getDepthSpacing() {

        if (this.getLayoutComponent() != null) {

            return this.getLayoutComponent().getDepthSpacing();
        }
        return null;
    }

    /**
     * @param depthSpacing
     *            the depth spacing.
     */
    public void setDepthSpacing(double depthSpacing) {

        if (this.getLayoutComponent() != null) {

            this.getLayoutComponent().setDepthSpacing(depthSpacing);
            setChanged();
            notifyObservers("" + getDepthSpacing());
        }
    }

    /**
     * @return obvious
     */
    public Double getSpaceBetweenSiblings() {

        if (this.getLayoutComponent() != null) {

            return this.getLayoutComponent().getSpaceBetweenSiblings();
        }

        return null;
    }

    /**
     * @param spaceBetweenSiblings
     *            the space between siblings.
     */
    public void setSpaceBetweenSiblings(double spaceBetweenSiblings) {

        if (this.getLayoutComponent() != null) {

            this.getLayoutComponent().setSpaceBetweenSiblings(
                    spaceBetweenSiblings);
            setChanged();
            notifyObservers("" + getSpaceBetweenSiblings());
        }
    }

    /**
     * @return obvious
     */
    public Double getSpaceBetweenNeighboringSubtrees() {

        if (this.getLayoutComponent() != null) {

            return this.getLayoutComponent()
                    .getSpaceBetweenNeighboringSubtrees();
        }
        return null;
    }

    /**
     * @param spaceBetweenNeighboringSubtrees
     *            the space between neighboring sub-trees.
     */
    public void setSpaceBetweenNeighboringSubtrees(
            double spaceBetweenNeighboringSubtrees) {

        if (this.getLayoutComponent() != null) {

            this.getLayoutComponent().setSpaceBetweenNeighboringSubtrees(
                    spaceBetweenNeighboringSubtrees);
            setChanged();
            notifyObservers("" + getSpaceBetweenNeighboringSubtrees());
        }
    }

    @Override
    public NodeLinkTreeCoordinateLayoutComponent getLayoutComponent() {

        if (super.getLayoutComponent() instanceof NodeLinkTreeCoordinateLayoutComponent) {
            return (NodeLinkTreeCoordinateLayoutComponent) super
                    .getLayoutComponent();
        }
        return null;
    }

}
