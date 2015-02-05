package edu.vt.arc.vis.osnap.javafx;


//@formatter:off
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
//@formatter:on


import java.util.LinkedHashSet;
import java.util.Set;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import org.controlsfx.control.action.Action;
import org.jutility.common.datatype.map.KeyValuePair;
import org.jutility.javafx.control.ListViewWithSearchPanel;

import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.layout.LayoutSet;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout;
import edu.vt.arc.vis.osnap.core.domain.mappings.IValueMapping;
import edu.vt.arc.vis.osnap.core.domain.mappings.Mapping;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;
import edu.vt.arc.vis.osnap.javafx.dialogs.LayoutComponentWizardSelectionDialog;
import edu.vt.arc.vis.osnap.javafx.events.ExportLayoutEvent;
import edu.vt.arc.vis.osnap.javafx.widgets.RoutingGridPane;
import edu.vt.arc.vis.osnap.javafx.wizards.ILayoutConfigurationWizard;


/**
 * The {@code LayoutDetailsVBox} class provides a detailed view of the contents
 * of a {@link LayoutSet}.
 * 
 * @author Shawn P Neuman
 * @version 1.2.0
 * @since 0.1.0
 */
public class LayoutDetailsVBox
        extends VBox {

    private final ObjectProperty<Universe>                                 universe;
    private final ObjectProperty<LayoutSet>                                layoutSet;
    private final ObjectProperty<Visualization>                            visualization;

    private TextField                                                      nameTF;
    private TextArea                                                       descriptionTA;

    private RoutingGridPane                                                routingGridPane;

    private ListViewWithSearchPanel<KeyValuePair<ILayout, VisualProperty>> layoutLV;
    private ListViewWithSearchPanel<IGraphObject>                          graphObjectLV;
    private ListViewWithSearchPanel<IValueMapping<?, ?>>                   valueMappingListView;
    private LayoutCapabilitiesTableView                                    capabilities;
    private Button                                                         applyLayout;
    private Button                                                         export;



    /**
     * Returns the universe property.
     * 
     * @return the universe property.
     */
    public ObjectProperty<Universe> universe() {

        return this.universe;
    }

    /**
     * Returns the universe.
     * 
     * @return the universe.
     */
    public Universe getUniverse() {

        return this.universe.get();
    }


    /**
     * Sets the universe.
     * 
     * @param universe
     *            the universe.
     */
    public void setUniverse(Universe universe) {

        this.universe.set(universe);
    }



    /**
     * Returns the layoutSet property.
     * 
     * @return the layoutSet property.
     */
    public ObjectProperty<LayoutSet> layoutProperty() {

        return this.layoutSet;
    }

    /**
     * Returns the layoutSet.
     * 
     * @return the layoutSet.
     */
    public LayoutSet getLayout() {

        return this.layoutSet.get();
    }


    /**
     * Sets the layoutSet.
     * 
     * @param layoutSet
     *            the layoutSet.
     */
    public void setLayout(LayoutSet layoutSet) {

        this.layoutSet.set(layoutSet);
    }



    /**
     * Returns the visualization property.
     * 
     * @return the visualization property.
     */
    public ObjectProperty<Visualization> visualization() {

        return this.visualization;
    }

    /**
     * Returns the visualization.
     * 
     * @return the visualization.
     */
    public Visualization getVisualization() {

        return this.visualization.get();
    }


    /**
     * Sets the visualization.
     * 
     * @param visualization
     *            the visualization.
     */
    public void setVisualization(Visualization visualization) {

        this.visualization.set(visualization);
    }



    /**
     * constructor
     */
    public LayoutDetailsVBox() {

        this.universe = new SimpleObjectProperty<>();
        this.layoutSet = new SimpleObjectProperty<>();
        this.visualization = new SimpleObjectProperty<>();

        this.setStyle("-fx-background-color: cornsilk");
        GridPane grid1 = new GridPane();

        grid1.setHgap(25);
        grid1.setVgap(10);
        grid1.setPadding(new Insets(10, 25, 25, 25));

        this.layoutLV = new ListViewWithSearchPanel<>("Layout Components");
        GridPane.setVgrow(layoutLV, Priority.ALWAYS);


        Text name = new Text("Name");

        name.setFont(Font.font("verdana", 16));
        nameTF = new TextField();
        nameTF.setMinHeight(25);
        Text description = new Text("Description");

        description.setFont(Font.font("verdana", 16));
        descriptionTA = new TextArea();
        descriptionTA.setWrapText(true);
        descriptionTA.setPrefColumnCount(10);
        descriptionTA.setPrefRowCount(10);

        this.routingGridPane = new RoutingGridPane();
        this.routingGridPane.setPadding(new Insets(0));


        graphObjectLV = new ListViewWithSearchPanel<>("Restrictions");
        GridPane.setVgrow(graphObjectLV, Priority.ALWAYS);
        capabilities = new LayoutCapabilitiesTableView("Capabilities");
        GridPane.setVgrow(capabilities, Priority.ALWAYS);
        valueMappingListView = new ListViewWithSearchPanel<>("Mapping");
        GridPane.setVgrow(valueMappingListView, Priority.ALWAYS);

        applyLayout = new Button("Create Visualization from this layout");
        applyLayout.setDisable(false);


        export = new Button("Export Visualization to X3D");
        export.setDisable(true);


        grid1.add(layoutLV, 0, 0, 1, 5);
        grid1.add(name, 1, 0);
        grid1.add(nameTF, 1, 1);
        grid1.add(description, 1, 2);
        grid1.add(descriptionTA, 1, 3);

        grid1.add(routingGridPane, 1, 4);

        grid1.add(graphObjectLV, 2, 0, 1, 5);
        grid1.add(capabilities, 3, 0, 1, 5);
        grid1.add(valueMappingListView, 4, 0, 1, 5);

        grid1.add(applyLayout, 0, 6);
        grid1.add(export, 2, 6);

        this.getChildren().add(grid1);



        this.setUpEventHandlers();
        this.setUpContextMenus();
    }


    private void populateList() {

        if (this.getLayout() != null) {

            this.layoutLV.getItems().clear();
            this.layoutLV.getItems().addAll(
                    this.getLayout().getLayoutComponents());
        }
    }



    /**
     * Clear all lists
     */
    public void clear() {

        this.layoutLV.clear();
        this.clearLayoutComponentDetails();
    }

    private void clearLayoutComponentDetails() {

        this.nameTF.clear();
        this.descriptionTA.clear();
        this.graphObjectLV.clear();
        this.valueMappingListView.clear();
        this.capabilities.setLayout(null);
    }

    /**
     * based on selection string, a wizard is started to allow user to select
     * display options
     * 
     * @param wizard
     *            the wizard with status object being started
     */
    private void startWizard(final ILayoutConfigurationWizard<?, ?> wizard) {

        if (wizard != null) {

            wizard.showAndWait()
                    .filter(buttonType -> buttonType == ButtonType.FINISH)
                    .ifPresent(
                            param -> {


                                ILayout comp = wizard.getConfiguration()
                                        .createConfiguredObject();

                                Set<VisualProperty> visualProperty = wizard
                                        .getConfiguration()
                                        .getEnabledVisualProperties();

                                for (VisualProperty prop : visualProperty) {
                                    this.getLayout()
                                            .addLayoutForVisualProperty(comp,
                                                    prop);
                                }

                                Set<IGraphObject> restriction = new LinkedHashSet<>(
                                        wizard.getConfiguration()
                                                .getRestriction());
                                comp.setRestriction(restriction);


                                populateList();

                            });

        }
    }

    private void setUpEventHandlers() {


        this.layoutSet.addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {

                this.populateList();
            }
            else {

                this.clear();
            }
        });

        this.layoutLV
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {

                            this.clearLayoutComponentDetails();

                            if (newValue != null) {

                                ILayout layout = newValue.getKey();

                                this.nameTF.setText(layout.getName());
                                this.descriptionTA.setText(layout
                                        .getDescription());

                                this.graphObjectLV.getItems().addAll(
                                        layout.getRestriction());

                                this.routingGridPane.setLayout(layout);
                                this.capabilities.setLayout(layout);

                                if (layout instanceof Mapping<?, ?, ?, ?>) {

                                    Mapping<?, ?, ?, ?> mapping = (Mapping<?, ?, ?, ?>) layout;
                                    this.valueMappingListView.getItems()
                                            .addAll(mapping.getValueMappings());
                                }
                            }
                            else {

                                this.routingGridPane.setLayout(null);
                            }

                        });



        this.applyLayout.setOnAction(actionEvent -> {

            if (this.layoutSet != null) {

                // Date startTime = new Date();
                this.getLayout().layout();
                this.setVisualization(null);
                this.setVisualization(this.getLayout().getVisualization());

                // gives a time dialog, tells user how long it took
                // to create the visualization

                // Date endTime = new Date();
                //
                // Date difference = new Date(endTime.getTime()
                // - startTime.getTime());
                // Dialogs.create()
                // .title("Creating Visualization Finished.")
                // .message(
                // "Creating visualization took "
                // + difference.getTime() / 1000
                // + " seconds.").showInformation();
                this.export.setDisable(false);
            }

        });
        this.export.setOnAction(actionEvent -> {

            ExportLayoutEvent exportEvent = new ExportLayoutEvent(
                    ExportLayoutEvent.EXPORT, this.getVisualization());
            fireEvent(exportEvent);
        });
    }

    private void setUpContextMenus() {


        Action addLayoutComponent = new Action("Add", actionEvent -> {

            new LayoutComponentWizardSelectionDialog(this, this.getUniverse())
                    .showAndWait().ifPresent(wizard -> {

                        this.startWizard(wizard);
                    });
        });



        Action removeLayoutComponent = new Action(
                "Remove",
                actionEvent -> {

                    KeyValuePair<ILayout, VisualProperty> selectedItem = this.layoutLV
                            .getSelectedItem();

                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirm removal!");
                    alert.setContentText("Are you sure you want to remove layout component "
                            + selectedItem.getKey()
                            + " for Visual Property "
                            + selectedItem.getValue() + "?");
                    alert.showAndWait()
                            .filter(buttonType -> {
                                return buttonType == ButtonType.OK;
                            })
                            .ifPresent(
                                    param -> {

                                        this.getLayout()
                                                .removeLayoutProviderForVisualProperty(
                                                        selectedItem.getKey(),
                                                        selectedItem.getValue());
                                        this.layoutLV.getItems().remove(
                                                selectedItem);
                                    });

                });

        this.layoutLV.contextMenuActions().addAll(addLayoutComponent,
                removeLayoutComponent);
    }
}
