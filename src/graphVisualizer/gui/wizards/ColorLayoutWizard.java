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
import graphVisualizer.gui.wizards.pages.ColorSelectionPage;
import graphVisualizer.gui.wizards.statusobjects.ColorStatus;
import graphVisualizer.gui.wizards.statuspanes.ColorStatusPane;
import graphVisualizer.layout.simpleComponents.SimpleColorLayoutComponent;


/**
 * Wizard for selecting components for this layout type
 * 
 * @author Shawn P Neuman
 * 
 */
public class ColorLayoutWizard
        extends BaseLayoutWizard {


    /**
     * The wizard for creating a layout where the color of nodes or edges is
     * modified for better visualization
     * 
     * @param universe
     *            the universe ontology
     */
    public ColorLayoutWizard(Universe universe) {

        this(null, universe);
    }

    /**
     * @param primary
     * @param uni
     */
    public ColorLayoutWizard(Stage primary, Universe uni) {

        super(primary, new ColorStatusPane("Color Selection Wizard"),
                new ColorStatus(), SimpleColorLayoutComponent.capabilities(),
                uni, new ColorSelectionPage());

        this.getStatusObject().setLayoutComponent(
                new SimpleColorLayoutComponent());


    }



    @Override
    public ColorStatus getStatusObject() {

        if (super.getStatusObject() instanceof ColorStatus) {
            return (ColorStatus) super.getStatusObject();
        }
        return null;
    }

}
