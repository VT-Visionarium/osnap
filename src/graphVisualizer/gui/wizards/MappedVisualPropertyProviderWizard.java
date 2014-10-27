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
import graphVisualizer.gui.wizards.pages.DisplayPropertiesPage;
import graphVisualizer.gui.wizards.pages.MappingPage;
import graphVisualizer.gui.wizards.statusobjects.MappingStatus;
import graphVisualizer.gui.wizards.statuspanes.MappingStatusPane;
import javafx.stage.Stage;


/**
 * Wizard for selecting components for this layout type
 * 
 * @author Shawn P Neuman, Peter J. Radics
 * 
 */
public class MappedVisualPropertyProviderWizard
        extends BaseLayoutWizard {


    private MappingPage           mapPage;
    private DisplayPropertiesPage displayPropertyPage;


    /**
     * Wizard for providing certain mappings, whether one to one, set to one, or
     * identity such as mapping node size to node degree, nodes would be scaled
     * to match the number of edges connected to that node
     * 
     * @param universe
     */
    public MappedVisualPropertyProviderWizard(Universe universe) {

        this(null, universe);
    }

    /**
     * @param primary
     * @param universe
     */
    public MappedVisualPropertyProviderWizard(Stage primary, Universe universe) {

        super(primary, new MappingStatusPane(
                "Visual Property Selection MappingStatus"),
                new MappingStatus(), universe, new DisplayPropertiesPage(),
                new MappingPage());


        this.displayPropertyPage = (DisplayPropertiesPage) this.getPage(2);
        this.mapPage = (MappingPage) this.getPage(3);

        this.getStatusObject().addObserver(displayPropertyPage);
        this.getStatusObject().addObserver(this.mapPage);

    }

    @Override
    public MappingStatus getStatusObject() {

        if (super.getStatusObject() instanceof MappingStatus) {

            return (MappingStatus) super.getStatusObject();
        }
        return null;
    }


}
