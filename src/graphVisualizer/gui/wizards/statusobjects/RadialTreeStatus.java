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
import graphVisualizer.layout.prefuseComponents.RadialTreeCoordinateLayoutComponent;


/**
 * Status object to hold info for the layout matching its name
 *
 * @author Shawn P Neuman
 *
 */
public class RadialTreeStatus
        extends PrefuseTreeStatusObject {



    @Override
    public void setRootNode(INode node) {

        super.setRootNode(node);

        if (node != null) {
            RadialTreeCoordinateLayoutComponent layoutProvider = new RadialTreeCoordinateLayoutComponent(
                    node);
            super.setLayoutComponent(layoutProvider);

        }
        else {
            super.setLayoutComponent(null);
        }
    }

    /**
     * @return the radius
     */
    public Integer getRadius() {

        if (this.getLayoutComponent() != null) {
            return this.getLayoutComponent().getRadius();
        }
        return null;
    }

    /**
     * @param radius
     *            the radius to set
     */
    public void setRadius(int radius) {

        if (this.getLayoutComponent() != null) {
            this.getLayoutComponent().setRadius(radius);
            setChanged();
            notifyObservers(getRadius());
        }
    }

    @Override
    public RadialTreeCoordinateLayoutComponent getLayoutComponent() {

        if (super.getLayoutComponent() instanceof RadialTreeCoordinateLayoutComponent) {
            return (RadialTreeCoordinateLayoutComponent) super
                    .getLayoutComponent();
        }
        return null;
    }
}
