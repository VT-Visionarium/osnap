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


package graphVisualizer.gui.wizards.pages;


import graphVisualizer.gui.wizards.WizardWithStatus;
import graphVisualizer.gui.wizards.content.VisualPropertyCheckBoxVBox;
import graphVisualizer.visualization.VisualProperty;

import java.util.EnumSet;
import java.util.Set;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBoxBuilder;


/**
 * opening page of display property wizard
 * 
 * @author Shawn P Neuman
 * 
 */
public class VisualPropertyWizardPage
        extends WizardPage {

    private VisualPropertyCheckBoxVBox<VisualProperty> checkBox;
    private Set<VisualProperty>                        partialEnumSet;


    /**
     * Creates a new instance of the {@link VisualPropertyWizardPage} class.
     */
    public VisualPropertyWizardPage() {

        this(EnumSet.allOf(VisualProperty.class), 1);

    }


    /**
     * Creates a new instance of the {@link VisualPropertyWizardPage} class,
     * reducing the choices to the {@link VisualProperty VisualProperties}
     * provided.
     * 
     * @param partialEnumSet
     *            the reduced set of {@link VisualProperty} choices.
     */
    public VisualPropertyWizardPage(Set<VisualProperty> partialEnumSet) {

        this(partialEnumSet, partialEnumSet.size());
    }


    /**
     * Creates a new instance of the {@link VisualPropertyWizardPage} class,
     * reducing the choices to the {@link VisualProperty VisualProperties}
     * provided and limiting the number of selectable properties to the number
     * provided.
     * 
     * @param partialEnumSet
     *            the reduced set of {@link VisualProperty} choices.
     * @param limit
     *            the limit on the number of selectable properties.
     */
    public VisualPropertyWizardPage(Set<VisualProperty> partialEnumSet,
            int limit) {

        super("Visual Property Selector");

        this.partialEnumSet = partialEnumSet;
        // this.box.populate(this.partialEnumSet);
        this.checkBox.populateList(this.partialEnumSet);
        checkBox.setSelectionLimit(limit);

        this.setMinWidth(825.0);
        this.getFinishButton().setDisable(true);



    }


    @Override
    Parent getContent() {


        if (this.partialEnumSet == null) {
            this.partialEnumSet = EnumSet.allOf(VisualProperty.class);
        }



        checkBox = new VisualPropertyCheckBoxVBox<>(
                "Select Visual Property", this.partialEnumSet);

        return VBoxBuilder.create().spacing(5)
                .children(new Label(""), checkBox).build();
    }

    /**
     * decides which page to to to next, based on selection from combo box
     */
    @Override
    void nextPage() {

        this.getWizard().getStatusObject()
                .setVisualProperty(checkBox.getList());

        super.nextPage();
    }

    @Override
    public WizardWithStatus getWizard() {

        if (super.getWizard() instanceof WizardWithStatus) {

            return (WizardWithStatus) super.getWizard();
        }
        return null;
    }
}
