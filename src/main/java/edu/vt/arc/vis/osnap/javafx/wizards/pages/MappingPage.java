/*******************************************************************************
 * Copyright 2014 Virginia Tech Visionarium
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/


package edu.vt.arc.vis.osnap.javafx.wizards.pages;


import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import javafx.beans.value.ChangeListener;
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

import org.jutility.common.datatype.range.Interval;
import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.common.datatype.util.NumberUtils;
import org.jutility.javafx.control.ListViewWithSearchPanel;
import org.jutility.javafx.control.labeled.LabeledComboBox;
import org.jutility.math.geometry.Scalef;

import edu.vt.arc.vis.osnap.core.domain.mappings.IValueMapping;
import edu.vt.arc.vis.osnap.core.domain.mappings.LinearIntervalToIntervalValueMapping;
import edu.vt.arc.vis.osnap.core.domain.mappings.Mapping;
import edu.vt.arc.vis.osnap.core.domain.mappings.OneToOneValueMapping;
import edu.vt.arc.vis.osnap.core.domain.visualization.Shape;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.MappingStatus;


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


        this.grid = new GridPane();
        this.addMappingButton = new Button("Add Mapping");
        this.addMappingButton.setOnAction(click -> MappingPage.this
                .addMappingValue());

        this.grid.setVgap(10);
        this.grid.setHgap(10);
        this.domainValueListView = new ListViewWithSearchPanel<>(
                "Values to Map");
        this.domainValueListView.getSelectionModel().setSelectionMode(
                SelectionMode.SINGLE);


        this.oneToOne = new RadioButton("1 : 1");
        this.setToOne = new RadioButton("Set : 1");
        this.intervalToOne = new RadioButton("Interval : 1");
        this.intervalToInterval = new RadioButton("Interval : Interval");
        this.identity = new RadioButton("Identity");


        this.group = new ToggleGroup();
        this.group.selectedToggleProperty().addListener(
                (ChangeListener<Toggle>) (observable, oldValue, newValue) -> {

                    if (MappingPage.this.mappingStatus != null) {

                        if (newValue == MappingPage.this.oneToOne) {

                            MappingPage.this.domainValueListView
                                    .getSelectionModel().setSelectionMode(
                                            SelectionMode.SINGLE);
                        }
                        else {

                            MappingPage.this.domainValueListView
                                    .getSelectionModel().setSelectionMode(
                                            SelectionMode.MULTIPLE);
                        }

                        MappingPage.this.setFields();
                    }
                });

        this.oneToOne.setToggleGroup(this.group);
        this.setToOne.setToggleGroup(this.group);
        this.intervalToOne.setToggleGroup(this.group);
        this.intervalToInterval.setToggleGroup(this.group);
        this.identity.setToggleGroup(this.group);

        this.mapOptions = new Label("Available Mapping Options");

        final GridPane radioButtonGrid = new GridPane();
        radioButtonGrid.setVgap(10);
        radioButtonGrid.add(this.mapOptions, 0, 0);
        radioButtonGrid.add(this.oneToOne, 0, 1);
        radioButtonGrid.add(this.setToOne, 0, 2);
        radioButtonGrid.add(this.intervalToOne, 0, 3);
        radioButtonGrid.add(this.intervalToInterval, 0, 4);
        radioButtonGrid.add(this.identity, 0, 5);

        // Colors
        this.colorPicker = new ColorPicker(Color.WHITE);
        this.colorPicker.getStyleClass().add("split_button");
        this.colorPicker
                .setOnAction(choice -> MappingPage.this.color = MappingPage.this.colorPicker
                        .getValue());

        // Shapes
        this.shapeComboBox = new LabeledComboBox<>("");
        this.shapeComboBox.getItems().addAll(Shape.values());

        this.valueMappingList = new ListViewWithSearchPanel<>("Mapped Choices");

        this.grid.add(this.domainValueListView, 1, 0);
        this.grid.add(radioButtonGrid, 0, 0);

        this.grid.add(this.shapeComboBox, 2, 0);
        this.grid.add(this.colorPicker, 2, 0);
        this.grid.add(this.valueMappingList, 0, 1, 2, 1);

        final GridPane innerGrid = new GridPane();
        this.instructionLabel = new Label("Set Mapping value");

        this.from = new Label("From");
        this.fromTF = new TextField();
        this.from.setLabelFor(this.fromTF);

        this.to = new Label("To");
        this.toTF = new TextField();
        this.to.setLabelFor(this.toTF);

        innerGrid.add(this.instructionLabel, 0, 0);
        innerGrid.add(this.from, 0, 1);
        innerGrid.add(this.fromTF, 1, 1);
        innerGrid.add(this.to, 0, 2);
        innerGrid.add(this.toTF, 1, 2);
        innerGrid.add(this.addMappingButton, 0, 3);

        this.grid.add(innerGrid, 2, 1);

        this.getNextButton().setDisable(true);
        this.getFinishButton().setDisable(true);

        this.disableToggles();
        this.disableFields();

        this.setUpValidation();

        return VBoxBuilder.create().spacing(5).children(this.grid).build();
    }


    /**
     * @param statusObject
     *            the status object
     */
    public void populateList(final MappingStatus statusObject) {

        this.mappingStatus = statusObject;
        this.setToggles();
        final Collection<?> values = this.mappingStatus.getValuesList();

        this.domainValueListView.clear();
        if (values != null) {

            this.domainValueListView.getItems().addAll(values);
        }
    }

    @Override
    void priorPage() {

        this.disableToggles();
        this.disableFields();


        final Mapping<?, ?, ?, VisualProperty> map = this.mappingStatus
                .getMapping();

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

        this.disableFields();

        switch (this.mappingStatus.getVisualProperty().iterator().next()) {

            case EDGE_COLOR:
            case NODE_COLOR:

                this.colorPicker.setVisible(true);
                break;

            case EDGE_LABEL_TEXT:
            case NODE_LABEL_TEXT:

                if (this.group.selectedToggleProperty().getValue() != this.identity) {

                    this.to.setVisible(true);
                    this.toTF.setVisible(true);
                    this.toTF.setPromptText("Enter a label.");
                }
                break;

            case EDGE_SCALE:
            case NODE_SCALE:
            case NODE_X_POSITION:
            case NODE_Y_POSITION:
            case NODE_Z_POSITION:

                if (this.group.selectedToggleProperty().getValue() != this.identity) {

                    this.to.setVisible(true);
                    this.toTF.setVisible(true);
                    this.toTF.setPromptText("Enter a float value.");
                }

                if (this.group.selectedToggleProperty().getValue() == this.intervalToInterval) {

                    this.from.setVisible(true);
                    this.fromTF.setVisible(true);
                    this.fromTF.setPromptText("Enter a float value.");
                }
                break;

            case EDGE_SHAPE:
            case NODE_SHAPE:

                this.shapeComboBox.setVisible(true);
                break;

            default:
                break;

        }
    }


    private boolean enableIdentity() {

        if ((this.mappingStatus != null)
                && (this.mappingStatus.getKey() != null)
                && (this.mappingStatus.getVisualProperty() != null)
                && !this.mappingStatus.getVisualProperty().isEmpty()) {

            final Class<?> domainValueType = this.mappingStatus.getKey()
                    .getValueType();
            final Class<?> coDomainValueType = this.mappingStatus
                    .getVisualProperty().iterator().next().getValueType();

            return (coDomainValueType.isAssignableFrom(domainValueType))
                    || (Number.class.isAssignableFrom(domainValueType) && (Number.class
                            .isAssignableFrom(coDomainValueType) || Scalef.class
                            .isAssignableFrom(coDomainValueType)));
        }

        return false;
    }

    private boolean enableIntervalToInterval() {

        if ((this.mappingStatus != null)
                && (this.mappingStatus.getKey() != null)
                && (this.mappingStatus.getVisualProperty() != null)
                && !this.mappingStatus.getVisualProperty().isEmpty()) {

            final Class<?> domainValueType = this.mappingStatus.getKey()
                    .getValueType();
            final Class<?> coDomainValueType = this.mappingStatus
                    .getVisualProperty().iterator().next().getValueType();

            return (Number.class.isAssignableFrom(domainValueType) && (Number.class
                    .isAssignableFrom(coDomainValueType) || Scalef.class
                    .isAssignableFrom(coDomainValueType)));
        }

        return false;
    }

    private boolean enableIntervalToOne() {

        if ((this.mappingStatus != null)
                && (this.mappingStatus.getKey() != null)) {

            final Class<?> valueType = this.mappingStatus.getKey()
                    .getValueType();

            return Number.class.isAssignableFrom(valueType);
        }
        return false;
    }

    private void disableToggles() {

        // unselect toggles
        this.oneToOne.setSelected(false);
        this.setToOne.setSelected(false);
        this.intervalToOne.setSelected(false);
        this.intervalToInterval.setSelected(false);
        this.identity.setSelected(false);

        // setting toggles to disabled
        this.intervalToOne.setDisable(true);
        this.intervalToInterval.setDisable(true);
        this.identity.setDisable(true);
    }


    private void disableFields() {

        this.addMappingButton.setDisable(true);

        // hiding unnecessary objects
        this.colorPicker.setVisible(false);
        this.colorPicker.setValue(Color.WHITE);

        this.shapeComboBox.setVisible(false);
        this.shapeComboBox.getSelectionModel().clearSelection();

        // hiding unnecessary fields
        this.instructionLabel.setVisible(false);

        this.from.setVisible(false);
        this.fromTF.clear();
        this.fromTF.setVisible(false);

        this.to.setVisible(false);
        this.toTF.clear();
        this.toTF.setVisible(false);

        // Clear selections
        this.domainValueListView.clearSelection();
    }


    private void setUpValidation() {

        this.toTF.textProperty()
                .addListener(
                        (ChangeListener<String>) (observable, oldValue,
                                newValue) -> MappingPage.this.validate());
        this.fromTF.textProperty()
                .addListener(
                        (ChangeListener<String>) (observable, oldValue,
                                newValue) -> MappingPage.this.validate());
        this.colorPicker
                .valueProperty()
                .addListener(
                        (ChangeListener<Color>) (observable, oldValue, newValue) -> MappingPage.this
                                .validate());
        this.shapeComboBox
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (ChangeListener<Shape>) (observable, oldValue, newValue) -> MappingPage.this
                                .validate());

        this.domainValueListView.selectedItemProperty()
                .addListener(
                        (ChangeListener<Object>) (observable, oldValue,
                                newValue) -> MappingPage.this.validate());
    }

    private void validate() {


        final Toggle selectedToggle = this.group.getSelectedToggle();

        if ((selectedToggle != null)
                && !this.domainValueListView.getSelectedItems().isEmpty()) {

            if ((this.mappingStatus.getVisualProperty() != null)
                    && !this.mappingStatus.getVisualProperty().isEmpty()) {

                switch (this.mappingStatus.getVisualProperty().iterator()
                        .next()) {

                    case EDGE_COLOR:
                    case NODE_COLOR:

                        this.addMappingButton.setDisable(!(this.color != null));

                        break;
                    case EDGE_SHAPE:
                    case NODE_SHAPE:

                        this.addMappingButton
                                .setDisable(!(this.shapeComboBox
                                        .getSelectionModel().getSelectedItem() != null));
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
                            final boolean toNotEmpty = (this.toTF.getText() != null)
                                    && !"".equals(this.toTF.getText());

                            boolean fromNotEmpty = true;

                            if (selectedToggle == this.intervalToInterval) {

                                fromNotEmpty = (this.fromTF.getText() != null)
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

        final Mapping<?, ?, ?, VisualProperty> map = this.mappingStatus
                .getMapping();

        if (!this.mappingStatus.getVisualProperty().isEmpty()) {
            switch (this.mappingStatus.getVisualProperty().iterator().next()) {

                case EDGE_COLOR:
                case NODE_COLOR:

                    for (final Object item : this.domainValueListView
                            .getSelectedItems()) {

                        final OneToOneValueMapping<?, ?> mapping = OneToOneValueMapping
                                .create(map.getDomainValueType().cast(item),
                                        this.color);

                        map.addValueMapping((IValueMapping) mapping);

                    }
                    break;

                case EDGE_LABEL_TEXT:
                case NODE_LABEL_TEXT:


                    for (final Object item : this.domainValueListView
                            .getSelectedItems()) {
                        OneToOneValueMapping<?, ?> mapping = null;

                        if (this.group.getSelectedToggle() == this.identity) {

                            mapping = OneToOneValueMapping.create(map
                                    .getDomainValueType().cast(item), map
                                    .getCoDomainValueType().cast(item));
                        }
                        else {

                            mapping = OneToOneValueMapping.create(map
                                    .getDomainValueType().cast(item), this.toTF
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

                    if (this.group.getSelectedToggle() == this.intervalToInterval) {

                        final Interval<? extends Number> domainInterval = this
                                .createDomainInterval();

                        final Float minInterval = Float.parseFloat(this.fromTF
                                .getText());
                        final Float maxInterval = Float.parseFloat(this.toTF
                                .getText());

                        final LinearIntervalToIntervalValueMapping<?, ?> intervalMapping = LinearIntervalToIntervalValueMapping
                                .create(domainInterval.getLowerBound(),
                                        domainInterval.getUpperBound(),
                                        minInterval, maxInterval,
                                        domainInterval.getLowerBound()
                                                .getClass(), Float.class);

                        map.addValueMapping((IValueMapping) intervalMapping);


                    }
                    else if (this.group.getSelectedToggle() == this.identity) {

                        for (final Object item : this.domainValueListView
                                .getSelectedItems()) {

                            if (item instanceof Number) {
                                final OneToOneValueMapping<?, ?> mapping = OneToOneValueMapping
                                        .create(map.getDomainValueType().cast(
                                                item), NumberUtils.cast(
                                                (Number) item, Float.class));
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
                        for (final Object item : this.domainValueListView
                                .getSelectedItems()) {
                            final Float scale = Float.parseFloat(this.toTF
                                    .getText());

                            final OneToOneValueMapping<?, ?> mapping = OneToOneValueMapping
                                    .create(map.getDomainValueType().cast(item),
                                            scale);
                            map.addValueMapping((IValueMapping) mapping);

                        }
                    }
                    break;

                case EDGE_SHAPE:
                case NODE_SHAPE:

                    for (final Object item : this.domainValueListView
                            .getSelectedItems()) {

                        final OneToOneValueMapping<?, ?> mapping = OneToOneValueMapping
                                .create(map.getDomainValueType().cast(item),
                                        this.shapeComboBox.getSelectionModel()
                                                .getSelectedItem());

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
        for (final IValueMapping<?, ?> valueMapping : map.getValueMappings()) {
            this.valueMappingList.getItems().add(valueMapping);
        }
        for (final Object item : this.domainValueListView.getSelectedItems()) {

            this.domainValueListView.removeAll(item);
        }


        this.getNextButton().setDisable(false);


    }



    private Interval<? extends Number> createDomainInterval() {

        Number minValue = null;
        Number maxValue = null;



        for (final Object item : this.domainValueListView.getSelectedItems()) {

            final Number number = (Number) item;

            if ((item instanceof Number) && (item instanceof Comparable<?>)) {

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

        if ((minValue != null) && (maxValue != null)) {

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
    public void update(final Observable statusObject, final Object value) {

        if (statusObject instanceof MappingStatus) {

            this.populateList((MappingStatus) statusObject);
        }
    }
}
