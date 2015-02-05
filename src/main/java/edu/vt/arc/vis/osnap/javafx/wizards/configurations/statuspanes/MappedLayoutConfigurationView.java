package edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes;


// @formatter:off
/*
 * _
 * The Open Semantic Network Analysis Platform (OSNAP)
 * _
 * Copyright (C) 2012 - 2015 Visionarium at Virginia Tech
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
// @formatter:on

import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import edu.vt.arc.vis.osnap.core.domain.graph.common.GraphObjectProperty;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObjectBasedValueTypeContainer;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Metadata;
import edu.vt.arc.vis.osnap.core.domain.layout.common.IMappedLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.IMappedLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.MappedLayoutConfiguration;


/**
 * Status pane to show user selections from wizard for mapping layout
 *
 * the type of the {@link IMappedLayoutConfiguration}.
 *
 * @author Shawn P Neuman
 *
 */
public class MappedLayoutConfigurationView
        extends
        LayoutConfigurationView<IMappedLayout<? extends IGraphObjectBasedValueTypeContainer, ?, ?>, IMappedLayoutConfiguration> {

    private final Label            typeLabel;
    private final Text             typeTF;
    private final Label            selectedValues;
    private final ListView<Object> propertyValuesList;


    /**
     * Creates a new instance of the {@code MappedLayoutConfigurationView}
     * class.
     *
     * @param title
     *            the title for this {@link IConfigurationView}.
     */
    public MappedLayoutConfigurationView(final String title) {

        super(title, new MappedLayoutConfiguration());


        this.typeLabel = new Label("Using");
        this.typeTF = new Text();
        this.typeTF.setStyle("-fx-font-weight: bold");

        this.selectedValues = new Label("Selected Values:");
        this.propertyValuesList = new ListView<>();

        this.add(this.typeLabel, 0, super.rowsUsed());
        this.add(this.typeTF, 1, super.rowsUsed());
        this.add(this.selectedValues, 0, super.rowsUsed() + 1);

        GridPane.setValignment(selectedValues, VPos.TOP);
        this.add(this.propertyValuesList, 1, super.rowsUsed() + 1);
    }

    @Override
    public void refreshView() {

        super.refreshView();

        this.typeLabel.setText("");
        this.propertyValuesList.getItems().clear();

        if (this.getConfiguration().getDomainKey() != null) {
            if (this.getConfiguration().getDomainKey() instanceof Metadata) {

                this.typeLabel.setText("Metadata");
            }
            else if (this.getConfiguration().getDomainKey() instanceof GraphObjectProperty) {

                this.typeLabel.setText("Property");
            }


            this.typeTF.setText(this.getConfiguration().getDomainKey()
                    .toString());

        }

        if (this.getConfiguration().getValueMappings().isEmpty()) {

            this.selectedValues.setText("Selected Values:");
            this.propertyValuesList.getItems().addAll(
                    this.getConfiguration().getDomainValues());
        }
        else {

            this.selectedValues.setText("Mappings:");
            this.propertyValuesList.getItems().addAll(
                    this.getConfiguration().getValueMappings());
        }

    }
}
