package edu.vt.arc.vis.osnap.javafx;


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

import java.util.HashSet;
import java.util.Set;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;


/**
 * The {@code LayoutCapabilitiesTableView} class provides a {@link TableView}
 * that displays the {@link VisualProperty Capabilities} of a {@link ILayout}
 * and whether or not those capabilities are enabled.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 */
public class LayoutCapabilitiesTableView
        extends VBox {

    private final Text                          tableLabel;
    private final TableView<CapabilitiesObject> tableView;

    private final ObjectProperty<ILayout>       layoutProperty;

    /**
     * Returns the {@ILayout} property.
     * 
     * @return the {@ILayout} property.
     */
    public ObjectProperty<ILayout> layoutProperty() {

        return this.layoutProperty;
    }

    /**
     * Returns the value of the {@ILayout} property.
     * 
     * @return the value of the {@ILayout} property.
     */
    public ILayout getLayout() {

        return this.layoutProperty.get();
    }

    /**
     * Sets the value of the {@ILayout} property.
     * 
     * @param layout
     *            the value of the {@ILayout} property.
     */
    public void setLayout(final ILayout layout) {

        this.layoutProperty.set(layout);
    }


    /**
     * Creates a new instance of the {@code LayoutCapabilitiesTableView} class.
     * 
     * @param title
     *            the title of this TableView.
     */
    public LayoutCapabilitiesTableView(final String title) {

        this.setStyle("-fx-background-color: cornsilk");

        this.layoutProperty = new SimpleObjectProperty<>();

        this.tableLabel = new Text(title);
        this.tableLabel.setFont(Font.font("verdana", 16));
        this.tableView = new TableView<>();

        final TableColumn<CapabilitiesObject, VisualProperty> visualProperty = new TableColumn<>(
                "Visual Property");
        visualProperty.setMinWidth(125);
        final TableColumn<CapabilitiesObject, Boolean> enabled = new TableColumn<>(
                "Enabled");
        enabled.setMinWidth(125);

        visualProperty
                .setCellValueFactory(new PropertyValueFactory<CapabilitiesObject, VisualProperty>(
                        "visualProperty"));
        enabled.setCellValueFactory(new PropertyValueFactory<CapabilitiesObject, Boolean>(
                "enabled"));

        this.tableView.setItems(FXCollections.observableArrayList());



        this.tableView.getColumns().add(visualProperty);
        this.tableView.getColumns().add(enabled);
        this.getChildren().addAll(this.tableLabel, this.tableView);

        this.setupEventHandlers();
    }


    private void setupEventHandlers() {

        this.layoutProperty.addListener(observable -> {

            this.tableView.getItems().clear();

            if (this.getLayout() != null) {

                ILayout layout = this.getLayout();

                Set<VisualProperty> capabilities = layout
                        .providesCapabilities();

                Set<CapabilitiesObject> enabledCapabilities = new HashSet<>();
                for (VisualProperty property : capabilities) {

                    Boolean enabled = layout.isEnabled(property);
                    CapabilitiesObject capability = new CapabilitiesObject(
                            property, enabled);
                    enabledCapabilities.add(capability);
                }

                this.tableView.getItems().addAll(enabledCapabilities);
            }
        });
    }



    /**
     * The {@code CapabilitiesObject} class stores a {@link VisualProperty} and
     * boolean value for each capability of a {@link ILayout Layout} for easier
     * display of the enabled state of the {@link VisualProperty}.
     * 
     * @author Shawn P Neuman
     * @version 1.2.0
     * @since 0.1.0
     */
    public static class CapabilitiesObject {


        private VisualProperty visualProperty;
        private boolean        enabled;

        /**
         * Creates a new instance of the {@code CapabilitiesObject} class.
         * 
         * @param visualProperty
         *            the {@link VisualProperty}.
         * @param enabled
         *            whether or not the property is enabled.
         */
        public CapabilitiesObject(final VisualProperty visualProperty,
                final boolean enabled) {

            this.visualProperty = visualProperty;
            this.enabled = enabled;
        }

        /**
         * Returns the {@link VisualProperty}.
         * 
         * @return the {@link VisualProperty}.
         */
        public VisualProperty getVisualProperty() {

            return this.visualProperty;

        }

        /**
         * Sets the {@link VisualProperty}.
         * 
         * @param visualProperty
         *            the {@link VisualProperty}.
         */
        public void setVisualProperty(final VisualProperty visualProperty) {

            this.visualProperty = visualProperty;
        }

        /**
         * Returns whether or not the {@link VisualProperty} is enabled.
         * 
         * @return {@code true}, if the {@link VisualProperty} is enabled;
         *         {@code false} otherwise.
         */
        public Boolean isEnabled() {

            return this.enabled;
        }

        /**
         * Sets whether or not the {@link VisualProperty} is enabled.
         * 
         * @param enabled
         *            set to {@code true}, if the {@link VisualProperty} is
         *            enabled; set to {@code false} otherwise.
         */
        public void setEnabled(final boolean enabled) {

            this.enabled = enabled;
        }
    }
}
