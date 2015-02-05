package edu.vt.arc.vis.osnap.javafx.wizards.content;


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

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;


/**
 * The {@code CapabilityGridPane} provides a view for selecting a number of
 * {@link VisualProperty Capabilities} of a {@link ILayout} for activation.
 * 
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class CapabilityGridPane
        extends GridPane {

    private final Logger                        LOG = LoggerFactory
                                                            .getLogger(CapabilityGridPane.class);

    private final Map<CheckBox, VisualProperty> properties;
    private final int                           selectionLimit;


    private final IntegerProperty               capabilitiesSelectedProperty;

    /**
     * Creates a new instance of the {@code CapabilityGridPane} class.
     * 
     * @param capabilities
     *            the available capabilities.
     */
    public CapabilityGridPane(final Collection<VisualProperty> capabilities) {

        this(capabilities, capabilities.size());
    }

    /**
     * Creates a new instance of the {@code CapabilityGridPane} class.
     * 
     * @param capabilities
     *            the available capabilities.
     */
    public CapabilityGridPane(final VisualProperty... capabilities) {

        this(Arrays.asList(capabilities));
    }


    /**
     * Creates a new instance of the {@code CapabilityGridPane} class.
     * 
     * @param capabilities
     *            the available capabilities.
     * @param selectionLimit
     *            the maximum amount of selectable capabilities.
     */
    public CapabilityGridPane(final Collection<VisualProperty> capabilities,
            int selectionLimit) {

        this.setVgap(15);
        this.setHgap(10);

        this.selectionLimit = selectionLimit;

        this.properties = new LinkedHashMap<>();
        this.capabilitiesSelectedProperty = new SimpleIntegerProperty(0);

        this.setupView(capabilities);
        this.setupEventHandlers();
    }

    private void setupView(final Collection<VisualProperty> capabilities) {

        this.getChildren()
                .add(new Text("Select at most " + this.selectionLimit
                        + " Value(s)"));

        VBox nodeProperties = new VBox(15);
        VBox edgeProperties = new VBox(15);
        VBox coordinateProperties = new VBox(15);


        for (VisualProperty capability : capabilities) {

            if (!capability.isHyperedgeProperty()) {

                CheckBox checkbox = new CheckBox(capability.getName());
                checkbox.setId(capability.getKey());
                checkbox.setFont(Font.font("verdana", 14));
                this.properties.put(checkbox, capability);

                checkbox.selectedProperty().addListener(
                        (observable, oldValue, newValue) -> {

                            if (newValue) {

                                this.capabilitiesSelectedProperty
                                        .set(this.capabilitiesSelectedProperty
                                                .get() + 1);
                            }
                            else {

                                this.capabilitiesSelectedProperty
                                        .set(this.capabilitiesSelectedProperty
                                                .get() - 1);
                            }
                        });

                if (capability.isNodeProperty() && !capability.isEdgeProperty()) {

                    if (capability.getValueType() != Float.class) {

                        nodeProperties.getChildren().add(checkbox);
                    }
                    else {

                        coordinateProperties.getChildren().add(checkbox);
                    }
                }
                else if (capability.isEdgeProperty()
                        && !capability.isNodeProperty()) {

                    edgeProperties.getChildren().add(checkbox);
                }
                else {

                    coordinateProperties.getChildren().add(checkbox);
                }
            }
        }

        int row = 1;
        int column = 0;

        if (!nodeProperties.getChildren().isEmpty()) {

            this.add(nodeProperties, 0, 1);
            row += 2;
            column = 1;
        }
        if (!edgeProperties.getChildren().isEmpty()) {

            this.add(edgeProperties, 0, row);
            column = 1;
        }
        if (!coordinateProperties.getChildren().isEmpty()) {

            this.add(coordinateProperties, column, 1);
        }
    }

    private void setupEventHandlers() {

        this.capabilitiesSelectedProperty.addListener((observable, oldValue,
                newValue) -> {

            LOG.debug("Old value: " + oldValue + ", newValue: " + newValue);
            if (newValue != null) {

                if (newValue.intValue() == this.selectionLimit) {

                    this.disableNonSelectedCheckboxes();
                }
                else if (oldValue.intValue() == this.selectionLimit) {

                    this.enableCheckboxes();
                }
            }

        });
    }

    private void disableNonSelectedCheckboxes() {

        LOG.debug("Disabling unchecked CheckBoxes.");
        for (CheckBox checkBox : properties.keySet()) {

            checkBox.setDisable(!checkBox.isSelected());
        }
    }

    private void enableCheckboxes() {

        LOG.debug("(Re-)enabling all CheckBoxes.");
        for (CheckBox checkBox : properties.keySet()) {

            checkBox.setDisable(false);
        }
    }

    /**
     * Returns the list of selected capabilities.
     * 
     * @return the list of selected capabilities.
     */
    public Set<VisualProperty> getSelectedCapabilities() {

        Set<VisualProperty> selected = new LinkedHashSet<>();

        for (CheckBox box : properties.keySet()) {

            if (box.isSelected()) {

                selected.add(properties.get(box));
            }
        }

        return selected;
    }
}
