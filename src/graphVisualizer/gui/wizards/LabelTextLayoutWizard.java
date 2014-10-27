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
import graphVisualizer.gui.wizards.pages.LabelTextPage;
import graphVisualizer.gui.wizards.statusobjects.LabelStatus;
import graphVisualizer.gui.wizards.statuspanes.LabelStatusPane;
import graphVisualizer.layout.simpleComponents.SimpleLabelTextLayoutComponent;


/**
 * Wizard for selecting components for this layout type
 * 
 * @author Shawn P Neuman
 * 
 */
public class LabelTextLayoutWizard
        extends BaseLayoutWizard {


    /**
     * Wizard for creating a layout based on label of nodes or edges
     * @param universe
     */
    public LabelTextLayoutWizard(Universe universe) {

        this(null, universe);
    }

    /**
     * @param primary
     * @param uni
     */
    public LabelTextLayoutWizard(Stage primary, Universe uni) {

        super(primary, new LabelStatusPane("Label Selection Wizard"),
                new LabelStatus(),
                SimpleLabelTextLayoutComponent.capabilities(),
                uni,
                new LabelTextPage());
  
        this.getStatusObject()
                .setLayoutComponent(new SimpleLabelTextLayoutComponent());
      

    }


    @Override
    public LabelStatus getStatusObject() {

        if(super.getStatusObject() instanceof LabelStatus) {
            return (LabelStatus) super.getStatusObject();
        }
        return null;
    }

}
