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


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;
import java.util.Set;

import graphVisualizer.graph.Universe;
import graphVisualizer.gui.wizards.pages.GraphObjectSelectionPage;
import graphVisualizer.gui.wizards.pages.NamingPage;
import graphVisualizer.gui.wizards.pages.VisualPropertyWizardPage;
import graphVisualizer.gui.wizards.pages.WizardPage;
import graphVisualizer.gui.wizards.statusobjects.IStatus;
import graphVisualizer.visualization.VisualProperty;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * @author Shawn P Neuman
 * 
 */
public abstract class BaseLayoutWizard
        extends WizardWithStatus {

    private final GraphObjectSelectionPage graphObjectSelectionPage;
    private final NamingPage               namingPage;
    private final Universe                 universe;
    private final IStatus                  statusObject;



    /**
     * @return the graphObjectSelectionPage
     */
    public GraphObjectSelectionPage getGraphObjectSelectionPage() {

        return graphObjectSelectionPage;
    }


    /**
     * @return the namingPage
     */
    public NamingPage getNamingPage() {

        return namingPage;
    }


    /**
     * @return the universe
     */
    public Universe getUniverse() {

        return universe;
    }

    /**
     * @param primary
     * @param statusPane
     * @param statusObject
     * @param uni
     * @param pages
     */
    public <T extends Pane & Observer> BaseLayoutWizard(Stage primary,
            T statusPane, IStatus statusObject, Universe uni,
            WizardPage... pages) {

        this(primary, statusPane, statusObject, (Set<VisualProperty>) null, 0,
                uni, pages);
    }

    /**
     * @param primary
     * @param statusPane
     * @param statusObject
     * @param capabilities
     * @param uni
     */
    public <T extends Pane & Observer> BaseLayoutWizard(Stage primary,
            T statusPane, IStatus statusObject,
            Set<VisualProperty> capabilities, Universe uni) {

        this(primary, statusPane, statusObject, capabilities, uni,
                (WizardPage[]) null);
    }

    /**
     * @param primary
     * @param statusPane
     * @param statusObject
     * @param capabilities
     * @param uni
     * @param pages
     */
    public <T extends Pane & Observer> BaseLayoutWizard(Stage primary,
            T statusPane, IStatus statusObject,
            Set<VisualProperty> capabilities, Universe uni, WizardPage... pages) {

        this(primary, statusPane, statusObject, capabilities, capabilities
                .size(), uni, pages);

    }

    /**
     * @param primary
     *            this stage
     * @param statusPane
     * @param statusObject
     * @param capabilities
     * @param limit
     * @param uni
     *            this universe
     * @param pages
     */
    public <T extends Pane & Observer> BaseLayoutWizard(Stage primary,
            T statusPane, IStatus statusObject,
            Set<VisualProperty> capabilities, int limit, Universe uni,
            WizardPage... pages) {

        super(primary, statusPane, BaseLayoutWizard.createWizardPageArray(
                capabilities, limit, pages));

        this.statusObject = statusObject;

        this.graphObjectSelectionPage = (GraphObjectSelectionPage) this
                .getPage(1);
        this.namingPage = (NamingPage) this.getPage(this.numberOfPages() - 1);
        this.universe = uni;

        this.statusObject.addObserver(statusPane);
        this.statusObject.addObserver(graphObjectSelectionPage);
        this.statusObject.addObserver(namingPage);

    }



    /**
     * @return the current status object
     */

    @Override
    public IStatus getStatusObject() {

        return this.statusObject;
    }

    private static WizardPage[] createWizardPageArray(
            Set<VisualProperty> capabilities, int limit, WizardPage... pages) {

        List<WizardPage> wizardPages = new LinkedList<>();
        if (capabilities == null) {

            wizardPages.add(new VisualPropertyWizardPage());
        }
        else {

            wizardPages.add(new VisualPropertyWizardPage(capabilities, limit));
        }
        wizardPages.add(new GraphObjectSelectionPage());

        if (pages != null) {

            wizardPages.addAll(Arrays.asList(pages));
        }
        wizardPages.add(new NamingPage());

        return wizardPages.toArray(new WizardPage[0]);
    }
}
