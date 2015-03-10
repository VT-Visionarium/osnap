package edu.vt.arc.vis.osnap.javafx.wizards.pages;


// @formatter:off
/*
 * _ The Open Semantic Network Analysis Platform (OSNAP) _ Copyright (C) 2012 -
 * 2014 Visionarium at Virginia Tech _ Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License. _
 */
// @formatter:on

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.jutility.common.datatype.range.Interval;
import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.common.datatype.util.NumberUtils;
import org.jutility.javafx.control.ListViewWithSearchPanel;
import org.jutility.javafx.control.labeled.LabeledTextField;
import org.jutility.javafx.control.wrapper.ComboBoxWrapper;
import org.jutility.math.geometry.IScaleFactor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObjectBasedValueTypeContainer;
import edu.vt.arc.vis.osnap.core.domain.layout.common.IMappedLayout;
import edu.vt.arc.vis.osnap.core.domain.mappings.IValueMapping;
import edu.vt.arc.vis.osnap.core.domain.mappings.LinearIntervalToIntervalValueMapping;
import edu.vt.arc.vis.osnap.core.domain.mappings.OneToOneValueMapping;
import edu.vt.arc.vis.osnap.core.domain.visualization.Shape;
import edu.vt.arc.vis.osnap.javafx.wizards.Wizard;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.IMappedLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.IConfigurationView;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.MappedLayoutConfigurationView;


/**
 * creates a page for mapping values to properties
 *
 * @author Shawn P Neuman
 *
 */
public class MappingPage
        extends
        LayoutConfigurationWizardPage<IMappedLayout<? extends IGraphObjectBasedValueTypeContainer, ?, ?>, IMappedLayoutConfiguration, MappedLayoutConfigurationView> {


    private static final Logger                                LOG = LoggerFactory
                                                                           .getLogger(MappingPage.class);

    private final ToggleGroup                                  group;
    private final RadioButton                                  identity;
    private final RadioButton                                  oneToOne;
    private final RadioButton                                  setToOne;
    private final RadioButton                                  intervalToOne;
    private final RadioButton                                  intervalToInterval;



    private final ListViewWithSearchPanel<Object>              domainValuesLV;
    private final ListViewWithSearchPanel<IValueMapping<?, ?>> valueMappingsLV;


    private final GridPane                                     destinationGrid;
    private final Label                                        destinationLabel;
    private final LabeledTextField                             fromTF;
    private final LabeledTextField                             toTF;


    private final ColorPicker                                  colorPicker;
    private final ComboBoxWrapper<Shape>                       shapeComboBox;

    private Button                                             addMappingButton;



    /**
     * Creates a new instance of the {@code MappingPage} class.
     * 
     * @param configurationView
     *            the {@link IConfigurationView}.
     */
    public MappingPage(final MappedLayoutConfigurationView configurationView) {

        super("Create Value Mappings", configurationView);

        this.group = new ToggleGroup();

        this.identity = new RadioButton("Identity");
        this.oneToOne = new RadioButton("1 : 1");
        this.setToOne = new RadioButton("Set : 1");
        this.intervalToOne = new RadioButton("Interval : 1");
        this.intervalToInterval = new RadioButton("Interval : Interval");

        this.identity.setToggleGroup(this.group);
        this.oneToOne.setToggleGroup(this.group);
        this.setToOne.setToggleGroup(this.group);
        this.intervalToOne.setToggleGroup(this.group);
        this.intervalToInterval.setToggleGroup(this.group);

        GridPane mappingTypeGrid = new GridPane();
        mappingTypeGrid.setVgap(10);

        Label mappingTypeLabel = new Label("Mapping");
        mappingTypeLabel.setStyle("-fx-font-weight: bold");
        mappingTypeGrid.add(mappingTypeLabel, 0, 0);
        mappingTypeGrid.add(this.identity, 0, 1);
        mappingTypeGrid.add(this.oneToOne, 0, 2);
        mappingTypeGrid.add(this.setToOne, 0, 3);
        mappingTypeGrid.add(this.intervalToOne, 0, 4);
        mappingTypeGrid.add(this.intervalToInterval, 0, 5);



        this.domainValuesLV = new ListViewWithSearchPanel<>("Selected Value");
        this.domainValuesLV.getSelectionModel().setSelectionMode(
                SelectionMode.SINGLE);
        this.domainValuesLV.setVgap(10);
        this.domainValuesLV.getLabel().setStyle("-fx-font-weight: bold");



        this.destinationGrid = new GridPane();
        this.destinationGrid.setVgap(10);
        this.destinationLabel = new Label();
        this.destinationLabel.setStyle("-fx-font-weight: bold");

        this.fromTF = new LabeledTextField("From", Pos.TOP_CENTER);
        this.toTF = new LabeledTextField("To", Pos.TOP_CENTER);

        this.colorPicker = new ColorPicker(Color.WHITE);
        this.colorPicker.getStyleClass().add("split_button");

        // Shapes
        this.shapeComboBox = new ComboBoxWrapper<>();
        this.shapeComboBox.getItems().addAll(Shape.values());

        this.destinationGrid.add(this.destinationLabel, 0, 0);
        this.destinationGrid.add(this.fromTF, 0, 1);
        this.destinationGrid.add(this.toTF, 0, 2);



        this.addMappingButton = new Button("Add Value Mapping");
        this.addMappingButton.setOnAction(click -> MappingPage.this
                .addMappingValue());


        this.valueMappingsLV = new ListViewWithSearchPanel<>("Value Mappings");
        this.valueMappingsLV.getLabel().setStyle("-fx-font-weight: bold");


        this.getContentGridPane().add(mappingTypeGrid, 0, 0);
        this.getContentGridPane().add(this.domainValuesLV, 1, 0);

        this.getContentGridPane().add(this.destinationGrid, 2, 0);

        this.getContentGridPane().add(this.addMappingButton, 0, 1, 3, 1);
        this.getContentGridPane().add(this.valueMappingsLV, 0, 2, 3, 1);


        this.disableToggles();
        this.disableFields();

        this.setupEventHandlers();
        this.setupValidation();

        this.group.selectToggle(this.oneToOne);
    }

    private void setupEventHandlers() {

        this.group.selectedToggleProperty().addListener(
                (ChangeListener<Toggle>) (observable, oldValue, newValue) -> {

                    String sourceString = "From Selected";
                    String destinationString = "To ";
                    if (this.getConfiguration() != null
                            && this.getConfiguration()
                                    .getEnabledVisualProperty() != null) {

                        destinationString += this.getConfiguration()
                                .getEnabledVisualProperty().toString();
                    }
                    SelectionMode selectionMode = SelectionMode.MULTIPLE;

                    if (newValue == this.oneToOne) {

                        sourceString += " Value";
                        destinationString += " Value";
                        selectionMode = SelectionMode.SINGLE;
                    }
                    else if (newValue == this.setToOne) {

                        sourceString += " Set";
                        destinationString += " Value";
                    }
                    else if (newValue == this.identity) {

                        sourceString += " Values";
                        destinationString = "To identical "
                                + this.getConfiguration()
                                        .getEnabledVisualProperty().toString()
                                + " Values";
                    }
                    else if (newValue == this.intervalToOne) {

                        sourceString += " Interval";
                        destinationString += " Value";
                    }
                    else if (newValue == this.intervalToInterval) {

                        sourceString += " Interval";
                        destinationString += " Interval";
                    }


                    this.domainValuesLV.getSelectionModel().setSelectionMode(
                            selectionMode);
                    this.domainValuesLV.getLabel().setText(sourceString);
                    this.destinationLabel.setText(destinationString);
                    this.setFields();
                });


        this.colorPicker.setOnAction(choice -> {

            this.validate();
        });
    }

    private void setupValidation() {

        this.valueMappingsLV.registerValidator(Validator
                .createPredicateValidator(value -> {

                    return value != null;
                }, "At least one mapping is required!"));
        this.valueMappingsLV
                .setValidationDecorator(new GraphicValidationDecoration());
        this.valueMappingsLV.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.valueMappingsLV,
                this.valueMappingsLV.validationSupport());



        this.toTF.textProperty()
                .addListener(
                        (observable, oldValue, newValue) -> MappingPage.this
                                .validate());
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

        this.domainValuesLV.selectedItemProperty()
                .addListener(
                        (ChangeListener<Object>) (observable, oldValue,
                                newValue) -> MappingPage.this.validate());
    }


    @Override
    public void onEnteringPage(Wizard wizard) {

        super.onEnteringPage(wizard);

        this.domainValuesLV.clear();
        this.domainValuesLV.getItems().addAll(
                this.getConfiguration().getDomainValues());
        this.setToggles();
    }

    @Override
    public void onExitingPage(Wizard wizard) {

        super.onExitingPage(wizard);

        this.getConfiguration().setValueMappings(
                this.valueMappingsLV.getItems());
    }



    private void setToggles() {

        this.disableToggles();

        this.intervalToOne.setDisable(!this.enableIntervalToOne());
        this.intervalToInterval.setDisable(!this.enableIntervalToInterval());
        this.identity.setDisable(!this.enableIdentity());
    }


    private void setFields() {

        this.disableFields();

        if (this.getConfiguration() != null
                && this.getConfiguration().getEnabledVisualProperty() != null) {
            switch (this.getConfiguration().getEnabledVisualProperty()) {

                case EDGE_COLOR:
                case NODE_COLOR:

                    this.colorPicker.setVisible(true);
                    break;

                case EDGE_LABEL_TEXT:
                case NODE_LABEL_TEXT:

                    if (this.group.selectedToggleProperty().getValue() != this.identity) {

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

                        this.toTF.setVisible(true);
                        this.toTF.setPromptText("Enter a float value.");
                    }

                    if (this.group.selectedToggleProperty().getValue() == this.intervalToInterval) {

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
    }


    private boolean enableIdentity() {


        final Class<?> domainValueType = this.getConfiguration().getDomainKey()
                .getValueType();
        final Class<?> coDomainValueType = this.getConfiguration()
                .getDomainValueType();

        return (coDomainValueType.isAssignableFrom(domainValueType))
                || (Number.class.isAssignableFrom(domainValueType) && (Number.class
                        .isAssignableFrom(coDomainValueType) || IScaleFactor.class
                        .isAssignableFrom(coDomainValueType)));

    }

    private boolean enableIntervalToInterval() {

        final Class<?> domainValueType = this.getConfiguration().getDomainKey()
                .getValueType();
        final Class<?> coDomainValueType = this.getConfiguration()
                .getDomainValueType();

        return (Number.class.isAssignableFrom(domainValueType) && (Number.class
                .isAssignableFrom(coDomainValueType) || IScaleFactor.class
                .isAssignableFrom(coDomainValueType)));
    }

    private boolean enableIntervalToOne() {


        final Class<?> domainValueType = this.getConfiguration().getDomainKey()
                .getValueType();

        return Number.class.isAssignableFrom(domainValueType);
    }

    private void disableToggles() {

        // unselect toggles
        this.identity.setSelected(false);
        this.oneToOne.setSelected(false);
        this.setToOne.setSelected(false);
        this.intervalToOne.setSelected(false);
        this.intervalToInterval.setSelected(false);

        // setting toggles to disabled
        this.identity.setDisable(true);
        this.intervalToOne.setDisable(true);
        this.intervalToInterval.setDisable(true);
    }


    private void disableFields() {

        this.addMappingButton.setDisable(true);

        // hiding unnecessary objects
        this.colorPicker.setVisible(false);
        this.colorPicker.setValue(Color.WHITE);

        this.shapeComboBox.setVisible(false);
        this.shapeComboBox.getSelectionModel().clearSelection();

        // hiding unnecessary fields
        this.fromTF.clear();
        this.fromTF.setVisible(false);

        this.toTF.clear();
        this.toTF.setVisible(false);

        // Clear selections
        this.domainValuesLV.clearSelection();
    }



    private void validate() {


        final Toggle selectedToggle = this.group.getSelectedToggle();

        if ((selectedToggle != null)
                && !this.domainValuesLV.getSelectedItems().isEmpty()) {


            switch (this.getConfiguration().getEnabledVisualProperty()) {

                case EDGE_COLOR:
                case NODE_COLOR:

                    this.addMappingButton.setDisable(!(this.colorPicker
                            .getValue() != null));

                    break;
                case EDGE_SHAPE:
                case NODE_SHAPE:

                    this.addMappingButton.setDisable(!(this.shapeComboBox
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


    private void addMappingValue() {


        LOG.debug("Adding mapping value(s).");
        Class<?> domainValueType = this.getConfiguration().getDomainValueType();
        Class<?> coDomainValueType = this.getConfiguration()
                .getEnabledVisualProperty().getValueType();


        List<Object> selectedItems = new ArrayList<>(
                this.domainValuesLV.getSelectedItems());

        List<IValueMapping<?, ?>> mappings = new ArrayList<>();

        switch (this.getConfiguration().getEnabledVisualProperty()) {

            case EDGE_COLOR:
            case NODE_COLOR:

                for (final Object item : selectedItems) {

                    mappings.add(OneToOneValueMapping.create(
                            domainValueType.cast(item),
                            this.colorPicker.getValue()));
                    this.domainValuesLV.getItems().remove(item);
                }
                break;

            case EDGE_LABEL_TEXT:
            case NODE_LABEL_TEXT:

                for (final Object item : selectedItems) {

                    if (this.group.getSelectedToggle() == this.identity) {

                        mappings.add(OneToOneValueMapping.create(
                                domainValueType.cast(item),
                                coDomainValueType.cast(item)));
                    }
                    else {

                        mappings.add(OneToOneValueMapping.create(
                                domainValueType.cast(item), this.toTF.getText()));
                    }
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

                    mappings.add(LinearIntervalToIntervalValueMapping.create(
                            domainInterval.getLowerBound(), domainInterval
                                    .getUpperBound(), minInterval, maxInterval,
                            domainInterval.getLowerBound().getClass(),
                            Float.class));
                }
                else if (this.group.getSelectedToggle() == this.identity) {

                    for (final Object item : this.domainValuesLV
                            .getSelectedItems()) {

                        if (item instanceof Number) {

                            mappings.add(OneToOneValueMapping.create(
                                    domainValueType.cast(item), NumberUtils
                                            .cast((Number) item, Float.class)));
                        }
                    }
                }
                else {
                    for (final Object item : selectedItems) {
                        final Float scale = Float.parseFloat(this.toTF
                                .getText());

                        mappings.add(OneToOneValueMapping.create(
                                domainValueType.cast(item), scale));
                    }
                }
                break;

            case EDGE_SHAPE:
            case NODE_SHAPE:

                for (final Object item : selectedItems) {

                    mappings.add(OneToOneValueMapping.create(domainValueType
                            .cast(item), this.shapeComboBox.getSelectionModel()
                            .getSelectedItem()));
                }

                break;
            default:
                break;

        }

        LOG.debug("Selected Items: "
                + this.domainValuesLV.getSelectionModel().getSelectedItems()
                        .size());
        LOG.debug("Selected Items copy: " + selectedItems.size());
        this.domainValuesLV.getSelectionModel().clearSelection();

        LOG.debug("Selected Items: "
                + this.domainValuesLV.getSelectionModel().getSelectedItems()
                        .size());
        LOG.debug("Selected Items copy: " + selectedItems.size());

        for (Object item : selectedItems) {


            LOG.debug("Removing " + item + ": "
                    + this.domainValuesLV.getItems().remove(item));
        }

        this.valueMappingsLV.getItems().addAll(mappings);
    }



    private Interval<? extends Number> createDomainInterval() {

        Number minValue = null;
        Number maxValue = null;



        for (final Object item : this.domainValuesLV.getSelectedItems()) {

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
}
