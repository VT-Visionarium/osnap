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


package edu.vt.arc.vis.osnap.gui.wizards.pages;


import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;

import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.common.datatype.util.NumberUtils;
import org.jutility.javafx.control.LabeledComboBox;
import org.jutility.javafx.control.ListViewWithSearchPanel;
import org.jutility.common.datatype.range.Interval;
import org.jutility.math.geometry.Scalef;

import edu.vt.arc.vis.osnap.gui.wizards.statusobjects.MappingStatus;
import edu.vt.arc.vis.osnap.mappings.IValueMapping;
import edu.vt.arc.vis.osnap.mappings.LinearIntervalToIntervalValueMapping;
import edu.vt.arc.vis.osnap.mappings.Mapping;
import edu.vt.arc.vis.osnap.mappings.OneToOneValueMapping;
import edu.vt.arc.vis.osnap.visualization.Shape;
import edu.vt.arc.vis.osnap.visualization.VisualProperty;


/**
 * creates a page for mapping values to properties
 * 
 * @author Shawn P Neuman
 * 
 */
public class MappingPage
        extends WizardPage
        implements Observer {

    private GridPane                                     grid;

    private ListViewWithSearchPanel<Object>              domainValueListView;
    private ListViewWithSearchPanel<IValueMapping<?, ?>> valueMappingList;

    private MappingStatus                                mappingStatus;

    private Label                                        mapOptions;
    private Label                                        instructionLabel;

    private Label                                        from;
    private TextField                                    fromTF;
    private TextField                                    toTF;
    private Label                                        to;

    private LabeledComboBox<Shape>                       shapeComboBox;

    private Button                                       addMappingButton;

    private ToggleGroup                                  group;
    private RadioButton                                  oneToOne;
    private RadioButton                                  setToOne;
    private RadioButton                                  intervalToOne;
    private RadioButton                                  intervalToInterval;
    private RadioButton                                  identity;

    private ColorPicker                                  colorPicker;
    private Color                                        color;

    /**
     *
     */
    public MappingPage() {

        super("Mapping");
        this.setMinWidth(825.0);



    }

    @Override
    Parent getContent() {


        grid = new GridPane();
        addMappingButton = new Button("Add Mapping");
        addMappingButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent click) {

                addMappingValue();
            }
        });

        grid.setVgap(10);
        grid.setHgap(10);
        domainValueListView = new ListViewWithSearchPanel<>("Values to Map");
        domainValueListView.setSelectionMode(SelectionMode.SINGLE);


        oneToOne = new RadioButton("1 : 1");
        setToOne = new RadioButton("Set : 1");
        intervalToOne = new RadioButton("Interval : 1");
        intervalToInterval = new RadioButton("Interval : Interval");
        identity = new RadioButton("Identity");


        group = new ToggleGroup();
        group.selectedToggleProperty().addListener(
                new ChangeListener<Toggle>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends Toggle> observable,
                            Toggle oldValue, Toggle newValue) {

                        if (MappingPage.this.mappingStatus != null) {

                            if (newValue == oneToOne) {

                                domainValueListView
                                        .setSelectionMode(SelectionMode.SINGLE);
                            }
                            else {

                                domainValueListView
                                        .setSelectionMode(SelectionMode.MULTIPLE);
                            }

                            MappingPage.this.setFields();
                        }
                    }
                });

        oneToOne.setToggleGroup(group);
        setToOne.setToggleGroup(group);
        intervalToOne.setToggleGroup(group);
        intervalToInterval.setToggleGroup(group);
        identity.setToggleGroup(group);

        mapOptions = new Label("Available Mapping Options");

        GridPane radioButtonGrid = new GridPane();
        radioButtonGrid.setVgap(10);
        radioButtonGrid.add(mapOptions, 0, 0);
        radioButtonGrid.add(oneToOne, 0, 1);
        radioButtonGrid.add(setToOne, 0, 2);
        radioButtonGrid.add(intervalToOne, 0, 3);
        radioButtonGrid.add(intervalToInterval, 0, 4);
        radioButtonGrid.add(identity, 0, 5);

        // Colors
        colorPicker = new ColorPicker(Color.WHITE);
        colorPicker.getStyleClass().add("split_button");
        colorPicker.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent choice) {

                color = colorPicker.getValue();
            }
        });

        // Shapes
        shapeComboBox = new LabeledComboBox<>("");
        this.shapeComboBox.items().addAll(Shape.values());

        this.valueMappingList = new ListViewWithSearchPanel<>("Mapped Choices");

        grid.add(domainValueListView, 1, 0);
        grid.add(radioButtonGrid, 0, 0);

        grid.add(shapeComboBox, 2, 0);
        grid.add(colorPicker, 2, 0);
        grid.add(valueMappingList, 0, 1, 2, 1);

        GridPane innerGrid = new GridPane();
        instructionLabel = new Label("Set Mapping value");

        from = new Label("From");
        fromTF = new TextField();
        from.setLabelFor(fromTF);

        to = new Label("To");
        toTF = new TextField();
        to.setLabelFor(toTF);

        innerGrid.add(instructionLabel, 0, 0);
        innerGrid.add(from, 0, 1);
        innerGrid.add(fromTF, 1, 1);
        innerGrid.add(to, 0, 2);
        innerGrid.add(toTF, 1, 2);
        innerGrid.add(addMappingButton, 0, 3);

        grid.add(innerGrid, 2, 1);

        this.getNextButton().setDisable(true);
        this.getFinishButton().setDisable(true);

        this.disableToggles();
        this.disableFields();

        this.setUpValidation();

        return VBoxBuilder.create().spacing(5).children(grid).build();
    }


    /**
     * @param statusObject
     *            the status object
     */
    public void populateList(MappingStatus statusObject) {

        this.mappingStatus = statusObject;
        this.setToggles();
        Collection<?> values = mappingStatus.getValuesList();

        domainValueListView.clear();
        if (values != null) {

            domainValueListView.items().addAll(values);
        }
    }

    @Override
    void priorPage() {

        this.disableToggles();
        this.disableFields();


        Mapping<?, ?, ?, VisualProperty> map = mappingStatus.getMapping();

        map.clearValueMappings();
        this.valueMappingList.clearSelection();
        this.valueMappingList.clear();

        super.priorPage();
    }


    private void setToggles() {

        this.disableToggles();

        this.intervalToOne.setDisable(!this.enableIntervalToOne());
        this.intervalToInterval.setDisable(!this.enableIntervalToInterval());
        this.identity.setDisable(!this.enableIdentity());
    }


    private void setFields() {

        disableFields();

        switch (mappingStatus.getVisualProperty().iterator().next()) {

            case EDGE_COLOR:
            case NODE_COLOR:

                colorPicker.setVisible(true);
                break;

            case EDGE_LABEL_TEXT:
            case NODE_LABEL_TEXT:

                if (this.group.selectedToggleProperty().getValue() != this.identity) {

                    to.setVisible(true);
                    toTF.setVisible(true);
                    toTF.setPromptText("Enter a label.");
                }
                break;

            case EDGE_SCALE:
            case NODE_SCALE:
            case NODE_X_POSITION:
            case NODE_Y_POSITION:
            case NODE_Z_POSITION:

                if (this.group.selectedToggleProperty().getValue() != this.identity) {

                    to.setVisible(true);
                    toTF.setVisible(true);
                    toTF.setPromptText("Enter a float value.");
                }

                if (this.group.selectedToggleProperty().getValue() == this.intervalToInterval) {

                    from.setVisible(true);
                    fromTF.setVisible(true);
                    fromTF.setPromptText("Enter a float value.");
                }
                break;

            case EDGE_SHAPE:
            case NODE_SHAPE:

                shapeComboBox.setVisible(true);
                break;

            default:
                break;

        }
    }


    private boolean enableIdentity() {

        if (mappingStatus != null && mappingStatus.getKey() != null
                && mappingStatus.getVisualProperty() != null
                && !mappingStatus.getVisualProperty().isEmpty()) {

            Class<?> domainValueType = mappingStatus.getKey().getValueType();
            Class<?> coDomainValueType = mappingStatus.getVisualProperty()
                    .iterator().next().getValueType();

            return (coDomainValueType.isAssignableFrom(domainValueType))
                    || (Number.class.isAssignableFrom(domainValueType) && (Number.class
                            .isAssignableFrom(coDomainValueType) || Scalef.class
                            .isAssignableFrom(coDomainValueType)));
        }

        return false;
    }

    private boolean enableIntervalToInterval() {

        if (mappingStatus != null && mappingStatus.getKey() != null
                && mappingStatus.getVisualProperty() != null
                && !mappingStatus.getVisualProperty().isEmpty()) {

            Class<?> domainValueType = mappingStatus.getKey().getValueType();
            Class<?> coDomainValueType = mappingStatus.getVisualProperty()
                    .iterator().next().getValueType();

            return (Number.class.isAssignableFrom(domainValueType) && (Number.class
                    .isAssignableFrom(coDomainValueType) || Scalef.class
                    .isAssignableFrom(coDomainValueType)));
        }

        return false;
    }

    private boolean enableIntervalToOne() {

        if (mappingStatus != null && mappingStatus.getKey() != null) {

            Class<?> valueType = mappingStatus.getKey().getValueType();

            return Number.class.isAssignableFrom(valueType);
        }
        return false;
    }

    private void disableToggles() {

        // unselect toggles
        oneToOne.setSelected(false);
        setToOne.setSelected(false);
        intervalToOne.setSelected(false);
        intervalToInterval.setSelected(false);
        identity.setSelected(false);

        // setting toggles to disabled
        intervalToOne.setDisable(true);
        intervalToInterval.setDisable(true);
        identity.setDisable(true);
    }


    private void disableFields() {

        this.addMappingButton.setDisable(true);

        // hiding unnecessary objects
        colorPicker.setVisible(false);
        colorPicker.setValue(Color.WHITE);

        shapeComboBox.setVisible(false);
        shapeComboBox.clearSelection();

        // hiding unnecessary fields
        instructionLabel.setVisible(false);

        from.setVisible(false);
        fromTF.clear();
        fromTF.setVisible(false);

        to.setVisible(false);
        toTF.clear();
        toTF.setVisible(false);

        // Clear selections
        domainValueListView.clearSelection();
    }


    private void setUpValidation() {

        this.toTF.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                MappingPage.this.validate();
            }
        });
        this.fromTF.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                MappingPage.this.validate();
            }
        });
        this.colorPicker.valueProperty().addListener(
                new ChangeListener<Color>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends Color> observable,
                            Color oldValue, Color newValue) {

                        MappingPage.this.validate();
                    }
                });
        this.shapeComboBox.selectedItemProperty().addListener(
                new ChangeListener<Shape>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends Shape> observable,
                            Shape oldValue, Shape newValue) {

                        MappingPage.this.validate();
                    }
                });

        this.domainValueListView.selectedItemProperty().addListener(
                new ChangeListener<Object>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends Object> observable,
                            Object oldValue, Object newValue) {

                        MappingPage.this.validate();

                    }
                });
    }

    private void validate() {


        Toggle selectedToggle = this.group.getSelectedToggle();

        if (selectedToggle != null
                && !this.domainValueListView.getSelectedItems().isEmpty()) {

            if (this.mappingStatus.getVisualProperty() != null
                    && !this.mappingStatus.getVisualProperty().isEmpty()) {

                switch (mappingStatus.getVisualProperty().iterator().next()) {

                    case EDGE_COLOR:
                    case NODE_COLOR:

                        this.addMappingButton.setDisable(!(this.color != null));

                        break;
                    case EDGE_SHAPE:
                    case NODE_SHAPE:

                        this.addMappingButton.setDisable(!(shapeComboBox
                                .getSelectedItem() != null));
                        break;
                    case EDGE_LABEL_TEXT:
                    case NODE_LABEL_TEXT:
                    case EDGE_SCALE:
                    case NODE_SCALE:
                    case NODE_X_POSITION:
                    case NODE_Y_POSITION:
                    case NODE_Z_POSITION:
                        if (selectedToggle == this.identity) {

                            this.addMappingButton.setDisable(false);
                        }
                        else {
                            boolean toNotEmpty = this.toTF.getText() != null
                                    && !"".equals(this.toTF.getText());

                            boolean fromNotEmpty = true;

                            if (selectedToggle == this.intervalToInterval) {

                                fromNotEmpty = this.fromTF.getText() != null
                                        && !"".equals(this.fromTF.getText());
                            }

                            this.addMappingButton
                                    .setDisable(!(toNotEmpty && fromNotEmpty));
                        }
                        break;

                    default:
                        break;

                }
            }
            else {

                this.addMappingButton.setDisable(true);
            }
        }
        else {

            this.addMappingButton.setDisable(true);
        }
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void addMappingValue() {

        Mapping<?, ?, ?, VisualProperty> map = mappingStatus.getMapping();

        if (!mappingStatus.getVisualProperty().isEmpty()) {
            switch (mappingStatus.getVisualProperty().iterator().next()) {

                case EDGE_COLOR:
                case NODE_COLOR:

                    for (Object item : domainValueListView.getSelectedItems()) {

                        OneToOneValueMapping<?, ?> mapping = OneToOneValueMapping
                                .create(map.getDomainValueType().cast(item),
                                        color);

                        map.addValueMapping((IValueMapping) mapping);

                    }
                    break;

                case EDGE_LABEL_TEXT:
                case NODE_LABEL_TEXT:


                    for (Object item : domainValueListView.getSelectedItems()) {
                        OneToOneValueMapping<?, ?> mapping = null;

                        if (group.getSelectedToggle() == identity) {

                            mapping = OneToOneValueMapping.create(map
                                    .getDomainValueType().cast(item), map
                                    .getCoDomainValueType().cast(item));
                        }
                        else {

                            mapping = OneToOneValueMapping.create(map
                                    .getDomainValueType().cast(item), toTF
                                    .getText());
                        }
                        map.addValueMapping((IValueMapping) mapping);


                    }

                    break;

                case NODE_X_POSITION:
                case NODE_Y_POSITION:
                case NODE_Z_POSITION:
                case EDGE_SCALE:
                case NODE_SCALE:

                    if (group.getSelectedToggle() == intervalToInterval) {

                        Interval<? extends Number> domainInterval = this
                                .createDomainInterval();

                        Float minInterval = Float.parseFloat(fromTF.getText());
                        Float maxInterval = Float.parseFloat(toTF.getText());

                        LinearIntervalToIntervalValueMapping<?, ?> intervalMapping = LinearIntervalToIntervalValueMapping
                                .create(domainInterval.getLowerBound(),
                                        domainInterval.getUpperBound(),
                                        minInterval, maxInterval,
                                        domainInterval.getLowerBound()
                                                .getClass(), Float.class);

                        map.addValueMapping((IValueMapping) intervalMapping);


                    }
                    else if (group.getSelectedToggle() == identity) {

                        for (Object item : domainValueListView
                                .getSelectedItems()) {

                            if (item instanceof Number) {
                                OneToOneValueMapping<?, ?> mapping = OneToOneValueMapping
                                        .create(map.getDomainValueType().cast(
                                                item), NumberUtils.cast((Number) item,
                                                Float.class));
                                map.addValueMapping((IValueMapping) mapping);
                            }
                        }
                    }
                    // else if (group.getSelectedToggle() == intervalToInterval)
                    // {
                    //
                    // Interval<?> domainInterval = this.createDomainInterval();
                    // Float scale = Float.parseFloat(toTF.getText());
                    //
                    // IntervalToOneValueMapping<?, ?> mapping =
                    // OneToOneValueMapping(domainInterval, scale);
                    // }
                    else {
                        for (Object item : domainValueListView
                                .getSelectedItems()) {
                            Float scale = Float.parseFloat(toTF.getText());

                            OneToOneValueMapping<?, ?> mapping = OneToOneValueMapping
                                    .create(map.getDomainValueType().cast(item),
                                            scale);
                            map.addValueMapping((IValueMapping) mapping);

                        }
                    }
                    break;

                case EDGE_SHAPE:
                case NODE_SHAPE:

                    for (Object item : domainValueListView.getSelectedItems()) {

                        OneToOneValueMapping<?, ?> mapping = OneToOneValueMapping
                                .create(map.getDomainValueType().cast(item),
                                        shapeComboBox.getSelectedItem());

                        map.addValueMapping((IValueMapping) mapping);
                    }

                    break;

                //
                // if (group.getSelectedToggle() == intervalToInterval) {
                //
                // for (Object item : domainValueListView.getSelectedItems()) {
                //
                // String str = item.toString();
                // int temp = Integer.parseInt(str);
                // if (temp < minVal) {
                // minVal = temp;
                // }
                // if (temp > maxVal) {
                // maxVal = temp;
                // }
                // }
                // Float minInterval = Float.parseFloat(fromTF.getText());
                // Float maxInterval = Float.parseFloat(toTF.getText());
                // LinearIntervalToIntervalValueMapping<Integer, Float>
                // intervalMapping = new LinearIntervalToIntervalValueMapping<>(
                // minVal, maxVal, minInterval, maxInterval,
                // Integer.class, Float.class);
                // map.addValueMapping((IValueMapping) intervalMapping);
                //
                // }
                // else if (group.getSelectedToggle() == identity) {
                //
                //
                // for (Object item : domainValueListView.getSelectedItems()) {
                //
                // OneToOneValueMapping<?, ?> mapping = OneToOneValueMapping
                // .create(map.getDomainValueType().cast(item),
                // map.getCoDomainValueType().cast(item));
                //
                //
                // map.addValueMapping((IValueMapping) mapping);
                //
                //
                // }
                //
                // }
                // else {
                // for (Object item : domainValueListView.getSelectedItems()) {
                //
                // Float position = Float.parseFloat(toTF.getText());
                //
                // OneToOneValueMapping<?, ?> mapping = OneToOneValueMapping
                // .create(map.getDomainValueType().cast(item),
                // position);
                //
                // map.addValueMapping((IValueMapping) mapping);
                // }
                // }
                //
                // break;
                default:
                    break;

            }
        }
        this.valueMappingList.clear();
        for (IValueMapping<?, ?> valueMapping : map.getValueMappings()) {
            this.valueMappingList.items().add(valueMapping);
        }
        for (Object item : domainValueListView.getSelectedItems()) {

            domainValueListView.removeAll(item);
        }


        this.getNextButton().setDisable(false);


    }



    private Interval<? extends Number> createDomainInterval() {

        Number minValue = null;
        Number maxValue = null;



        for (Object item : domainValueListView.getSelectedItems()) {

            Number number = (Number) item;

            if (item instanceof Number && item instanceof Comparable<?>) {

                if (minValue == null) {

                    minValue = number;
                }
                else if (NumberComparator.smallerThan(number, minValue)) {

                    minValue = number;
                }
                if (maxValue == null) {

                    maxValue = number;
                }
                else if (NumberComparator.greaterThan(number, minValue)) {

                    maxValue = number;
                }
            }
        }

        if (minValue != null && maxValue != null) {

            return Interval.createInterval(minValue, maxValue);
        }

        return null;
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable statusObject, Object value) {

        if (statusObject instanceof MappingStatus) {

            this.populateList((MappingStatus) statusObject);
        }
    }
}
