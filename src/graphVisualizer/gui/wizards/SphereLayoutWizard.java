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


/**
 * 
 */
package graphVisualizer.gui.wizards;


import graphVisualizer.graph.Universe;
import graphVisualizer.gui.wizards.pages.CoordinateScalePage;
import graphVisualizer.gui.wizards.pages.RoutingPage;
import graphVisualizer.gui.wizards.statusobjects.BaseStatusObject;
import graphVisualizer.gui.wizards.statusobjects.SphereLayoutStatus;
import graphVisualizer.gui.wizards.statuspanes.CoordinateStatusPane;
import graphVisualizer.layout.complexComponents.SphereCoordinateLayoutComponent;
import javafx.stage.Stage;


/**
 * @author Shawn P Neuman
 * 
 */
public class SphereLayoutWizard
        extends BaseLayoutWizard {

    /**
     * The wizard for creating a layout where node are laid out in a large
     * sphere. Layout is useful for providing depth on Z axis for otherwise 2d
     * layouts
     * 
     * @param universe
     */
    public SphereLayoutWizard(Universe universe) {

        this(null, universe);
    }

    /**
     * @param primary
     *            this stage
     * @param uni
     *            this universe
     */

    public SphereLayoutWizard(Stage primary, Universe uni) {

        super(primary, new CoordinateStatusPane("Spherical Coordinate Layout"),

        new SphereLayoutStatus(), SphereCoordinateLayoutComponent
                .capabilities(), uni, new CoordinateScalePage(),
                new RoutingPage());


        this.getStatusObject().setLayoutComponent(
                new SphereCoordinateLayoutComponent());

        this.getStatusObject().addObserver((RoutingPage) this.getPage(3));

        this.getStatusObject().addObserver(
                (CoordinateStatusPane) this.getStatusPane());
        this.getStatusObject().addObserver(this.getNamingPage());

    }



    /**
     * @return the current status object
     */

    @Override
    public SphereLayoutStatus getStatusObject() {

        if (super.getStatusObject() instanceof BaseStatusObject) {
            return (SphereLayoutStatus) super.getStatusObject();
        }
        return null;
    }

}
