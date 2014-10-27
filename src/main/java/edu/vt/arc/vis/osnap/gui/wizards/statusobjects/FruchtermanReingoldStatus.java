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


import java.util.List;

import edu.vt.arc.vis.osnap.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.layout.prefuseComponents.ForceDirectedCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.layout.prefuseComponents.FruchtermanReingoldCoordinateLayoutComponent;



/**
 * Status object to hold info for the layout matching its name
 *
 * @author Shawn P Neuman
 * @version 1.0
 */
public class FruchtermanReingoldStatus
        extends PrefuseStatusObject {

    /**
     * Returns the number of iterations.
     *
     * @return the number of iterations.
     */
    public Integer getMaxIterations() {

        if (this.getLayoutComponent() != null) {

            if (this.getLayoutComponent() instanceof FruchtermanReingoldCoordinateLayoutComponent) {
                return ((FruchtermanReingoldCoordinateLayoutComponent) this
                        .getLayoutComponent()).getMaximumIterations();
            }
            else if (this.getLayoutComponent() instanceof ForceDirectedCoordinateLayoutComponent) {

                return ((ForceDirectedCoordinateLayoutComponent) this
                        .getLayoutComponent()).getIterations();
            }
        }
        return null;
    }

    /**
     * Sets the number of iterations.
     *
     * @param iterations
     *            the number of iterations.
     */
    public void setMaxIterations(int iterations) {

        if (this.getLayoutComponent() != null) {

            if (this.getLayoutComponent() instanceof FruchtermanReingoldCoordinateLayoutComponent) {

                ((FruchtermanReingoldCoordinateLayoutComponent) this
                        .getLayoutComponent()).setMaximumIterations(iterations);
                setChanged();
                notifyObservers(getMaxIterations());
            }
            else if (this.getLayoutComponent() instanceof ForceDirectedCoordinateLayoutComponent) {

                ((ForceDirectedCoordinateLayoutComponent) this
                        .getLayoutComponent()).setIterations(iterations);
            }
        }
    }

    @Override
    public void setGraphObjectList(List<IGraphObject> graphObjectList) {

        super.setGraphObjectList(graphObjectList);

        super.setLayoutComponent(new FruchtermanReingoldCoordinateLayoutComponent());
    }
}
