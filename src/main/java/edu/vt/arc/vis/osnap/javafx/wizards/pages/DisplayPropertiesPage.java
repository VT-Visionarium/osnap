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


package edu.vt.arc.vis.osnap.javafx.wizards.pages;

/*
 * _
 * The Open Semantic Network Analysis Platform (OSNAP)
 * _
 * Copyright (C) 2012 - 2014 Visionarium at Virginia Tech
 * _
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * _
 */


import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObjectBasedValueTypeContainer;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.mappedComponents.VisualPropertyMappingFactory;
import edu.vt.arc.vis.osnap.core.domain.mappings.Mapping;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.javafx.wizards.MappedVisualPropertyProviderWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.content.DisplayPropertiesGridPane;
import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.IStatus;
import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.MappingStatus;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBoxBuilder;


/**
 * 
 * page for selecting components for this layout type
 * 
 * @author Shawn P Neuman
 * 
 */
public class DisplayPropertiesPage
        extends WizardPage
        implements Observer {

    private DisplayPropertiesGridPane dpvb;


    /**
     * constructor
     */
    public DisplayPropertiesPage() {

        super("Node Visual Property Selector");

        this.setMinWidth(825.0);
        this.getFinishButton().setDisable(true);
        this.getNextButton().setDisable(true);

    }

    @Override
    Parent getContent() {

        dpvb = new DisplayPropertiesGridPane();
        return VBoxBuilder.create().spacing(5)
                .children(new Label("Select Property Provider"), dpvb).build();
    }

    @Override
    void priorPage() {

        dpvb.clear();

        this.getWizard().getStatusObject().setProvider(null);
        this.getWizard().getStatusObject().setKey(null);
        this.getWizard().getStatusObject().setValuesList(null);

        super.priorPage();
    }

    @Override
    void nextPage() {


//        System.out.println("Getting combo box choice");
        String type = dpvb.getComboBoxChoice();

//        System.out.println("Getting schema or property");
        IGraphObjectBasedValueTypeContainer schemaEntryOrProperty = dpvb
                .getSchemaOrProperty();

//        System.out.println("Getting value choices");
        List<Object> values = dpvb.getValueChoices();

//        System.out.println("Setting map");
        setMap(schemaEntryOrProperty);

//        System.out.println("Setting provider type");
        this.getWizard().getStatusObject().setProvider(type);
//        System.out.println("Setting schema entry or property");
        this.getWizard().getStatusObject().setKey(schemaEntryOrProperty);

//        System.out.println("Setting value list");
        this.getWizard().getStatusObject().setValuesList(values);



        super.nextPage();
    }

    /**
     * @param enabled
     *            true if next button is to be shown (enabled false if it is to
     *            be disabled to prevent user stupidity
     */
    public void enableNextButton(boolean enabled) {

        if (enabled) {

            this.getNextButton().setDisable(false);
        }
        else {
            this.getNextButton().setDisable(true);
        }
    }


    /**
     * gets the list of meta data choices from the child class display
     * properties VBox
     * 
     * @return list of choices
     */
    public List<Object> getMetadataChoices() {


        return dpvb.getValueChoices();
    }

    /**
     * @param selectedGraphObjects
     *            list
     */
    public void setSelectedGraphObjects(
            Collection<IGraphObject> selectedGraphObjects) {

        dpvb.setSelectedGraphObjects(selectedGraphObjects);
    }

    // type can be either Metadata or Properties
    // key is the value from available meta data or available properties
    private void setMap(Object key) {

        MappingStatus mappingStatus = this.getWizard().getStatusObject();

        if (key instanceof IGraphObjectBasedValueTypeContainer) {

            if (mappingStatus.getVisualProperty() != null
                    && !mappingStatus.getVisualProperty().isEmpty()) {

                Mapping<?, ?, ?, VisualProperty> mapping = VisualPropertyMappingFactory
                        .createMappedVisualPropertyProvider(
                                (IGraphObjectBasedValueTypeContainer) key,
                                mappingStatus.getVisualProperty().iterator()
                                        .next());

                if (mapping instanceof ILayoutComponent) {

                    ILayoutComponent ivpp = (ILayoutComponent) mapping;

                    ivpp.setRestriction(new LinkedHashSet<>(
                            mappingStatus.getGraphObjectList()));
                }

                mappingStatus.setMapping(mapping);

            }
        }
    }

    @Override
    public MappedVisualPropertyProviderWizard getWizard() {

        if (super.getWizard() instanceof MappedVisualPropertyProviderWizard) {

            return (MappedVisualPropertyProviderWizard) super.getWizard();
        }
        return null;
    }

    @Override
    public void update(Observable observable, Object changedValue) {

//        System.out.println("In update in displayProperties page");
        if (observable instanceof IStatus) {

            IStatus status = (IStatus) observable;
            if (status.getGraphObjectList() != null
                    && changedValue instanceof List) {

//                System.out.println("Setting selected graph objects");
                this.setSelectedGraphObjects(status.getGraphObjectList());
            }
        }
    }
}
