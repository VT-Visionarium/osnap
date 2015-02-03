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

import org.controlsfx.dialog.Dialogs;

import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * @author Shawn P Neuman, Peter J. Radics
 * 
 */
public class VisualPropertyCheckBoxVBox
        extends GridPane {

    private final Map<CheckBox, VisualProperty> properties;
    private int                                 numSelected = 0;
    private int                                 selectionLimit;

    /**
     * @param values
     */
    public VisualPropertyCheckBoxVBox(Collection<VisualProperty> values) {

        this(values, values.size());
    }

    /**
     * @param values
     */
    @SafeVarargs
    public VisualPropertyCheckBoxVBox(VisualProperty... values) {

        this(Arrays.asList(values));
    }


    /**
     * @param values
     * @param selectionLimit
     */
    public VisualPropertyCheckBoxVBox(Collection<VisualProperty> values,
            int selectionLimit) {

        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(15);
        this.setHgap(10);

        this.selectionLimit = selectionLimit;

        this.properties = new LinkedHashMap<>();


        for (VisualProperty visualProperty : values) {

            switch (visualProperty) {

                case HYPEREDGE_COLOR:
                case HYPEREDGE_SCALE:
                case HYPEREDGE_LABEL_TEXT:
                case HYPEREDGE_SHAPE:
                    break;
                default:

                    CheckBox checkbox = new CheckBox(visualProperty.getName());
                    checkbox.setId(visualProperty.getKey());
                    checkbox.setFont(Font.font("verdana", 14));
                    this.properties.put(checkbox, visualProperty);

                    break;
            }
        }


        populateList(values);
    }

    /**
     * populate a list of CheckBoxes
     * 
     * @param values
     */
    public void populateList(Collection<VisualProperty> values) {

        int i = 1;
        int k = 1;
        for (CheckBox zeBox : properties.keySet()) {
            this.getChildren().remove(zeBox);
        }
        properties.clear();

        // check for node property types first
        // add only node shape, color, scale, label
        for (VisualProperty value : values) {

            if (value.toString().contains("node")
                    && !value.toString().contains("Pos")) {
                String name = value.toString().substring(14);
                final CheckBox checkBox = new CheckBox("Node " + name);
                checkBox.setFont(Font.font("verdana", 14));
                checkBox.selectedProperty().addListener(
                        new ChangeListener<Boolean>() {

                            @Override
                            public void changed(
                                    ObservableValue<? extends Boolean> observable,
                                    Boolean oldValue, Boolean newValue) {

                                if (oldValue) {
                                    numSelected--;
                                }
                                else {
                                    numSelected++;
                                    if (numSelected > selectionLimit) {
                                        checkBox.selectedProperty().setValue(
                                                false);


                                        Dialogs.create()
                                                .title("Incorrect selection!")
                                                .message(
                                                        "This property only allows "
                                                                + selectionLimit
                                                                + " choices. Please deselect one of your choices to continue!")
                                                .showError();

                                    }
                                }

                            }


                        });
                properties.put(checkBox, value);
                this.add(checkBox, 0, i);
                i++;
            }

        }
        // create a break between nodes and edges
        i++;

        // add edge check boxes next
        for (VisualProperty value : values) {

            if (value.toString().contains("edge")) {

                String name = value.toString().substring(14);
                final CheckBox checkBox = new CheckBox("Edge " + name);
                checkBox.setFont(Font.font("verdana", 14));
                checkBox.selectedProperty().addListener(
                        new ChangeListener<Boolean>() {

                            @Override
                            public void changed(
                                    ObservableValue<? extends Boolean> observable,
                                    Boolean oldValue, Boolean newValue) {

                                if (oldValue) {
                                    numSelected--;
                                }
                                else {
                                    numSelected++;
                                    if (numSelected > selectionLimit) {
                                        checkBox.selectedProperty().setValue(
                                                false);
                                        Dialogs.create()
                                                .title("Incorrect selection!")
                                                .message(
                                                        "This property only allows "
                                                                + selectionLimit
                                                                + " choices. Please deselect one of your choices to continue!")
                                                .showError();

                                    }
                                }

                            }


                        });
                properties.put(checkBox, value);
                this.add(checkBox, 0, i);
                i++;
            }

        }

        // check for node x, y or z position
        for (VisualProperty value : values) {

            if (value.toString().contains("Pos")) {

                String name = value.toString().substring(14);
                final CheckBox checkBox = new CheckBox("Node " + name);
                checkBox.setFont(Font.font("verdana", 14));
                checkBox.selectedProperty().addListener(
                        new ChangeListener<Boolean>() {

                            @Override
                            public void changed(
                                    ObservableValue<? extends Boolean> observable,
                                    Boolean oldValue, Boolean newValue) {

                                if (oldValue) {
                                    numSelected--;
                                }
                                else {
                                    numSelected++;
                                    if (numSelected > selectionLimit) {
                                        checkBox.selectedProperty().setValue(
                                                false);

                                        Dialogs.create()
                                                .title("Incorrect selection!")
                                                .message(
                                                        "This property only allows "
                                                                + selectionLimit
                                                                + " choices. Please deselect one of your choices to continue!")
                                                .showError();
                                    }
                                }

                            }


                        });
                properties.put(checkBox, value);
                this.add(checkBox, 1, k);
                k++;
            }

        }

        // check for node x, y or z position
        for (VisualProperty value : values) {

            if (value.toString().contains("viewpoint")) {

                final CheckBox checkBox = new CheckBox("Viewpoint");
                checkBox.setFont(Font.font("verdana", 14));
                checkBox.selectedProperty().addListener(
                        new ChangeListener<Boolean>() {

                            @Override
                            public void changed(
                                    ObservableValue<? extends Boolean> observable,
                                    Boolean oldValue, Boolean newValue) {

                                if (oldValue) {
                                    numSelected--;
                                }
                                else {
                                    numSelected++;
                                    if (numSelected > selectionLimit) {
                                        checkBox.selectedProperty().setValue(
                                                false);

                                        Dialogs.create()
                                                .title("Incorrect selection!")
                                                .message(
                                                        "This property only allows "
                                                                + selectionLimit
                                                                + " choices. Please deselect one of your choices to continue!")
                                                .showError();
                                    }
                                }

                            }


                        });
                properties.put(checkBox, value);
                this.add(checkBox, 1, k);
                k++;
            }

        }

    }

    /**
     * Returns the list of selected values.
     * 
     * @return the list of selected values.
     */
    public Set<VisualProperty> getList() {

        Set<VisualProperty> selectedList = new LinkedHashSet<>();

        for (CheckBox box : properties.keySet()) {
            if (box.isSelected()) {

                selectedList.add(properties.get(box));
            }
        }


        return selectedList;

    }

    /**
     * @param limit
     */
    public void setSelectionLimit(int limit) {

        selectionLimit = limit;
        // System.out.println("Push the limit! " + limit);
    }

}
