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


package edu.vt.arc.vis.osnap.gui.wizards.statusobjects;


import edu.vt.arc.vis.osnap.graph.common.INode;
import edu.vt.arc.vis.osnap.graph.metadata.Metadata;
import edu.vt.arc.vis.osnap.layout.complexComponents.SolarSystemCoordinateLayoutComponent;


/**
 * Status object to hold info for the layout matching its name
 * 
 * @author Shawn P Neuman
 * 
 */
public class SolarSystemStatus
        extends BaseTreeStatus {


    @Override
    public void setRootNode(INode node) {

        super.setRootNode(node);

        if (node != null) {
            SolarSystemCoordinateLayoutComponent layoutProvider = new SolarSystemCoordinateLayoutComponent(
                    node);
            this.setLayoutComponent(layoutProvider);
            this.getLayoutComponent().setName(
                    SolarSystemCoordinateLayoutComponent.name());
            this.getLayoutComponent().setDescription(
                    SolarSystemCoordinateLayoutComponent.description());

        }
        else {
            this.setLayoutComponent(null);
        }
    }

    /**
     * @return the meta data
     */
    public Metadata getMetadata() {

        if (this.getLayoutComponent() != null) {

            return this.getLayoutComponent().getMetadata();
        }
        return null;
    }

    /**
     * @param metadata
     *            the metadata to set.
     */
    public void setMetadata(Metadata metadata) {

        if (this.getLayoutComponent() != null) {

            this.getLayoutComponent().setMetadata(metadata);
            setChanged();
            notifyObservers("" + getMetadata());
        }
    }

    /**
     * @return true if inverse path, false otherwise
     */
    public Boolean isIgnoreEdgeDirection() {

        if (this.getLayoutComponent() != null) {

            return this.getLayoutComponent().isIgnoreEdgeDirection();
        }
        return null;
    }

    /**
     * @param ignoreEdgeDirection
     *            whether or not to ignore edge direction
     */
    public void setIgnoreEdgeDirection(boolean ignoreEdgeDirection) {

        if (this.getLayoutComponent() != null) {

            this.getLayoutComponent().setIgnoreEdgeDirection(
                    ignoreEdgeDirection);
            setChanged();
            notifyObservers("" + isIgnoreEdgeDirection());
        }
    }

    /**
     * @return whether or not to invert the path to the root node.
     */
    public Boolean isInvertPathToRootNode() {

        if (this.getLayoutComponent() != null) {

            return this.getLayoutComponent().isInvertPathToRootNode();
        }
        return null;
    }

    /**
     * @param invertPathToRootNode
     *            whether or not to invert the path to the root node.
     */
    public void setInvertPathToRootNode(boolean invertPathToRootNode) {

        if (this.getLayoutComponent() != null) {

            this.getLayoutComponent().setInvertPathToRootNode(
                    invertPathToRootNode);
            setChanged();
            notifyObservers("" + isInvertPathToRootNode());
        }
    }

    /**
     * @return minimal distance between nodes
     */
    public Float getMinimalDistance() {


        if (this.getLayoutComponent() != null) {

            return this.getLayoutComponent().getMinimalDistance();
        }
        return null;
    }

    /**
     * @param minimalDistance
     *            the minimum distance between nodes
     */
    public void setMinimalDistance(float minimalDistance) {

        if (this.getLayoutComponent() != null) {

            this.getLayoutComponent().setMinimalDistance(minimalDistance);
            setChanged();
            notifyObservers("" + getMinimalDistance());
        }
    }

    /**
     * @return max node size
     */
    public Float getMaximumNodeSize() {

        if (this.getLayoutComponent() != null) {

            return this.getLayoutComponent().getMaximumNodeSize();
        }
        return null;
    }

    /**
     * @param maximumNodeSize
     *            the maximum node size.
     */
    public void setMaximumNodeSize(float maximumNodeSize) {

        if (this.getLayoutComponent() != null) {
            this.getLayoutComponent().setMaximumNodeSize(maximumNodeSize);
            setChanged();
            notifyObservers(getMaximumNodeSize());
        }
    }

    /**
     * @return the depth modifier
     */
    public Float getDepthModifier() {

        if (this.getLayoutComponent() != null) {

            return this.getLayoutComponent().getDepthModifier();
        }
        return null;
    }

    /**
     * @param depthModifier
     *            the depth modifier.
     */
    public void setDepthModifier(float depthModifier) {

        if (this.getLayoutComponent() != null) {
            this.getLayoutComponent().setDepthModifier(depthModifier);
            setChanged();
            notifyObservers(getDepthModifier());
        }
    }



    @Override
    public SolarSystemCoordinateLayoutComponent getLayoutComponent() {

        if (super.getLayoutComponent() instanceof SolarSystemCoordinateLayoutComponent) {

            return (SolarSystemCoordinateLayoutComponent) super
                    .getLayoutComponent();
        }
        return null;
    }

}
