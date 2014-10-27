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


package graphVisualizer.gui.wizards.statusobjects;


import graphVisualizer.graph.common.INode;
import graphVisualizer.layout.prefuseComponents.BalloonTreeCoordinateLayoutComponent;


/**
 * Status object to hold balloon tree info
 *
 * @author Shawn P Neuman
 *
 */
public class BalloonTreeStatus
        extends PrefuseTreeStatusObject {



    @Override
    public void setRootNode(INode node) {

        super.setRootNode(node);

        if (node != null) {
            
            BalloonTreeCoordinateLayoutComponent layoutProvider = new BalloonTreeCoordinateLayoutComponent(
                    node);
            this.setLayoutComponent(layoutProvider);
            this.getLayoutComponent().setName(
                    BalloonTreeCoordinateLayoutComponent.name());
            this.getLayoutComponent().setDescription(
                    BalloonTreeCoordinateLayoutComponent.description());
        }
        else {
            
            this.setLayoutComponent(null);
        }
    }


    /**
     * Returns the minimum radius.
     *
     * @return the minimum radius.
     */
    public Integer getMinimumRadius() {

        if (this.getLayoutComponent() != null) {
            
            return this.getLayoutComponent().getMinimumRadius();
        }
        return null;
    }

    /**
     * Sets the minimum radius.
     *
     * @param minimumRadius
     *            the minimum radius.
     */
    public void setMinimumRadius(Integer minimumRadius) {

        if (this.getLayoutComponent() != null) {

            this.getLayoutComponent().setMinimumRadius(minimumRadius);
            setChanged();
            notifyObservers(getMinimumRadius());
        }
    }

    @Override
    public BalloonTreeCoordinateLayoutComponent getLayoutComponent() {

        if (super.getLayoutComponent() instanceof BalloonTreeCoordinateLayoutComponent) {
            return (BalloonTreeCoordinateLayoutComponent) super
                    .getLayoutComponent();
        }
        return null;
    }
    

}
