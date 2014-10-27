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
import graphVisualizer.gui.wizards.pages.ScaleSelectionPage;
import graphVisualizer.gui.wizards.statusobjects.ScaleStatus;
import graphVisualizer.gui.wizards.statuspanes.ScaleStatusPane;
import graphVisualizer.layout.simpleComponents.SimpleScaleLayoutComponent;


/**
 * Wizard for selecting components for this layout type
 * 
 * @author Shawn P Neuman
 * 
 */
public class ScaleLayoutWizard
        extends BaseLayoutWizard {


    /**
     * The wizard for creating a layout where node or edge scale is modified for
     * better visualization
     * 
     * @param universe
     */
    public ScaleLayoutWizard(Universe universe) {

        this(null, universe);
    }

    /**
     * @param primary
     * @param uni
     */
    public ScaleLayoutWizard(Stage primary, Universe uni) {

        super(primary, new ScaleStatusPane("Scale Selection Wizard"),
                new ScaleStatus(), SimpleScaleLayoutComponent.capabilities(),
                uni, new ScaleSelectionPage());

        this.getStatusObject().setLayoutComponent(
                new SimpleScaleLayoutComponent());

    }

    @Override
    public ScaleStatus getStatusObject() {

        if (super.getStatusObject() instanceof ScaleStatus) {
            return (ScaleStatus) super.getStatusObject();
        }

        return null;
    }
}
