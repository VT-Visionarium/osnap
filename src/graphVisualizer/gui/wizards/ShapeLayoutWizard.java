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


package graphVisualizer.gui.wizards;


import javafx.stage.Stage;
import graphVisualizer.graph.Universe;
import graphVisualizer.gui.wizards.pages.ShapeSelectionPage;
import graphVisualizer.gui.wizards.statusobjects.ShapeStatus;
import graphVisualizer.gui.wizards.statuspanes.ShapeStatusPane;
import graphVisualizer.layout.simpleComponents.SimpleShapeLayoutComponent;


/**
 * Wizard for selecting components for this layout type
 * 
 * @author Shawn P Neuman
 * 
 */
public class ShapeLayoutWizard
        extends BaseLayoutWizard {

    /**
     * The wizard for creating a layout where node or edge shape is modified for
     * better visualization
     * 
     * @param universe
     */
    public ShapeLayoutWizard(Universe universe) {

        this(null, universe);
    }

    /**
     * @param primary
     * @param uni
     */
    public ShapeLayoutWizard(Stage primary, Universe uni) {

        super(primary, new ShapeStatusPane("Shape StatusPane"),
                new ShapeStatus(), SimpleShapeLayoutComponent.capabilities(),
                1, uni, new ShapeSelectionPage());

        this.getStatusObject().setLayoutComponent(
                new SimpleShapeLayoutComponent());

    }


    @Override
    public ShapeStatus getStatusObject() {

        if (super.getStatusObject() instanceof ShapeStatus) {
            return (ShapeStatus) super.getStatusObject();
        }

        return null;
    }

}
