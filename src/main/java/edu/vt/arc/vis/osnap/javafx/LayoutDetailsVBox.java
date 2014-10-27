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


package edu.vt.arc.vis.osnap.javafx;


// import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.DefaultDialogAction;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import org.jutility.common.datatype.map.KeyValuePair;
import org.jutility.javafx.control.ListViewWithSearchPanel;

import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.layout.Layout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.SphereCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.mappings.IValueMapping;
import edu.vt.arc.vis.osnap.core.domain.mappings.Mapping;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;
import edu.vt.arc.vis.osnap.javafx.dialogs.LayoutComponentWizardSelectionDialog;
import edu.vt.arc.vis.osnap.javafx.events.ExportLayoutEvent;
import edu.vt.arc.vis.osnap.javafx.events.WizardCompleted;
import edu.vt.arc.vis.osnap.javafx.widgets.RoutingGridPane;
import edu.vt.arc.vis.osnap.javafx.wizards.IWizardWithStatus;
import edu.vt.arc.vis.osnap.javafx.wizards.WizardDialog;
import edu.vt.arc.vis.osnap.javafx.wizards.content.CapabilitiesObject;


/**
 * the layout details page
 * 
 * @author Shawn P Neuman
 * 
 */
public class LayoutDetailsVBox
        extends VBox {

    private final ObjectProperty<Universe>                                          universe;
    private final ObjectProperty<Layout>                                            layout;
    private final ObjectProperty<Visualization>                                     visualization;

    private TextField                                                               nameTF;
    private TextArea                                                                descriptionTA;

    private RoutingGridPane                                                         routingGridPane;

    private ListViewWithSearchPanel<KeyValuePair<ILayoutComponent, VisualProperty>> layoutComponentListView;
    private ListViewWithSearchPanel<IGraphObject>                                   graphObjectListView;
    private ListViewWithSearchPanel<IValueMapping<?, ?>>                            valueMappingListView;
    private CapabilitiesTableView                                                   capabilities;
    private Button                                                                  applyLayout;
    private Button                                                                  export;



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
     * Returns the layout property.
     * 
     * @return the layout property.
     */
    public ObjectProperty<Layout> layoutProperty() {

        return this.layout;
    }

    /**
     * Returns the layout.
     * 
     * @return the layout.
     */
    public Layout getLayout() {

        return this.layout.get();
    }


    /**
     * Sets the layout.
     * 
     * @param layout
     *            the layout.
     */
    public void setLayout(Layout layout) {

        this.layout.set(layout);
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

        // System.out.println("Setting vis to " + visualization);
        this.visualization.set(visualization);
    }



    /**
     * constructor
     */
    public LayoutDetailsVBox() {

        this.universe = new SimpleObjectProperty<>();
        this.layout = new SimpleObjectProperty<>();
        this.visualization = new SimpleObjectProperty<>();

        this.setStyle("-fx-background-color: cornsilk");
        GridPane grid1 = new GridPane();

        grid1.setHgap(25);
        grid1.setVgap(10);
        grid1.setPadding(new Insets(10, 25, 25, 25));

        this.layoutComponentListView = new ListViewWithSearchPanel<>(
                "Layout Components");
        GridPane.setVgrow(layoutComponentListView, Priority.ALWAYS);


        Text name = new Text("Name of layout");

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


        graphObjectListView = new ListViewWithSearchPanel<>("Restrictions");
        GridPane.setVgrow(graphObjectListView, Priority.ALWAYS);
        capabilities = new CapabilitiesTableView("Capabilities");
        GridPane.setVgrow(capabilities, Priority.ALWAYS);
        valueMappingListView = new ListViewWithSearchPanel<>("Mapping");
        GridPane.setVgrow(valueMappingListView, Priority.ALWAYS);

        applyLayout = new Button("Create Visualization from this layout");
        applyLayout.setDisable(false);


        export = new Button("Export Layout to X3D");
        export.setDisable(true);


        grid1.add(layoutComponentListView, 0, 0, 1, 5);
        grid1.add(name, 1, 0);
        grid1.add(nameTF, 1, 1);
        grid1.add(description, 1, 2);
        grid1.add(descriptionTA, 1, 3);

        grid1.add(routingGridPane, 1, 4);

        grid1.add(graphObjectListView, 2, 0, 1, 5);
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

            this.layoutComponentListView.items().clear();
//            System.out.println("Layout components: "
//                    + this.getLayout().getLayoutComponents());
            this.layoutComponentListView.items().addAll(
                    this.getLayout().getLayoutComponents());
        }
    }



    /**
     * Clear all lists
     */
    public void clear() {

        this.layoutComponentListView.clear();
        this.clearLayoutComponentDetails();
    }

    private void clearLayoutComponentDetails() {

        this.nameTF.clear();
        this.descriptionTA.clear();
        this.graphObjectListView.clear();
        this.valueMappingListView.clear();
        this.capabilities.clearList();
    }

    /**
     * based on selection string, a wizard is started to allow user to select
     * display options
     * 
     * @param wizard
     *            the wizard with status object being started
     */
    private void startWizard(IWizardWithStatus wizard) {

        if (wizard != null) {
            final WizardDialog dsw2 = new WizardDialog(wizard);
            dsw2.addEventHandler(WizardCompleted.WIZARD_COMPLETED,
                    new EventHandler<WizardCompleted>() {

                        @Override
                        public void handle(WizardCompleted event) {

                            ILayoutComponent comp = event.getStatusObject()
                                    .getLayoutComponent();

                            Set<VisualProperty> visualProperty = event
                                    .getStatusObject().getVisualProperty();

                            for (VisualProperty prop : visualProperty) {
                                LayoutDetailsVBox.this.getLayout()
                                        .addLayoutProviderForVisualProperty(
                                                comp, prop);
                            }

                            Set<IGraphObject> restriction = new LinkedHashSet<>(
                                    event.getStatusObject()
                                            .getGraphObjectList());
                            comp.setRestriction(restriction);

                            if (event.getStatusObject().getLayoutComponent() instanceof SphereCoordinateLayoutComponent) {
//                                System.out
//                                        .println("I am a sphere layout component");
                            }


                            populateList();
                        }

                    });
            wizard.setOwner(dsw2);

            dsw2.show();
        }
    }

    private void setUpEventHandlers() {


        this.layout.addListener(new ChangeListener<Layout>() {

            @Override
            public void changed(ObservableValue<? extends Layout> observable,
                    Layout oldValue, Layout newValue) {

                if (newValue != null) {

//                    System.out.println("New Layout Value: " + newValue);
                    LayoutDetailsVBox.this.populateList();
                }
                else {

                    LayoutDetailsVBox.this.clear();
                }
            }
        });

        this.layoutComponentListView
                .selectedItemProperty()
                .addListener(
                        new ChangeListener<KeyValuePair<ILayoutComponent, VisualProperty>>() {

                            @Override
                            public void changed(
                                    javafx.beans.value.ObservableValue<? extends org.jutility.common.datatype.map.KeyValuePair<ILayoutComponent, VisualProperty>> arg0,
                                    KeyValuePair<ILayoutComponent, VisualProperty> oldValue,
                                    KeyValuePair<ILayoutComponent, VisualProperty> newValue) {

                                LayoutDetailsVBox.this
                                        .clearLayoutComponentDetails();

                                if (newValue != null) {

                                    ILayoutComponent layoutComponent = newValue
                                            .getKey();
                                    VisualProperty visualProperty = newValue
                                            .getValue();



                                    LayoutDetailsVBox.this.nameTF
                                            .setText(layoutComponent.getName());
                                    LayoutDetailsVBox.this.descriptionTA
                                            .setText(layoutComponent
                                                    .getDescription());

                                    LayoutDetailsVBox.this.graphObjectListView
                                            .items().addAll(
                                                    layoutComponent
                                                            .getRestriction());

                                    LayoutDetailsVBox.this.routingGridPane
                                            .setLayoutComponent(layoutComponent);

                                    Set<VisualProperty> capabilities = layoutComponent
                                            .providesCapabilities();

                                    Set<CapabilitiesObject> caps = new HashSet<>();
                                    for (VisualProperty property : capabilities) {
                                        Boolean enabled = layoutComponent
                                                .isEnabled(property);
                                        CapabilitiesObject co = new CapabilitiesObject(
                                                visualProperty, enabled);
                                        caps.add(co);
                                    }

                                    LayoutDetailsVBox.this.capabilities
                                            .populateCapabilities(caps);


                                    if (layoutComponent instanceof Mapping<?, ?, ?, ?>) {

                                        Mapping<?, ?, ?, ?> mapping = (Mapping<?, ?, ?, ?>) layoutComponent;
                                        valueMappingListView.items().addAll(
                                                mapping.getValueMappings());
                                    }
                                }
                                else {

                                    LayoutDetailsVBox.this.routingGridPane
                                            .setLayoutComponent(null);
                                }
                            }

                        });



        applyLayout.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (LayoutDetailsVBox.this.layout != null) {

                    // Date startTime = new Date();
                    LayoutDetailsVBox.this.getLayout().layout();
                    LayoutDetailsVBox.this.setVisualization(null);
                    LayoutDetailsVBox.this
                            .setVisualization(LayoutDetailsVBox.this
                                    .getLayout().getVisualization());

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
                    export.setDisable(false);
                }
            }

        });
        export.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                ExportLayoutEvent exportEvent = new ExportLayoutEvent(
                        ExportLayoutEvent.EXPORT, LayoutDetailsVBox.this
                                .getVisualization());
                fireEvent(exportEvent);
            }
        });
    }


    private void setUpContextMenus() {



        Action addLayoutComponent = new DefaultDialogAction("Add") {

            @Override
            public void handle(ActionEvent ae) {

                IWizardWithStatus wizard = LayoutComponentWizardSelectionDialog
                        .showLayoutComponentWizardSelectionDialog(
                                LayoutDetailsVBox.this.getScene().getWindow(),
                                getUniverse());

                if (wizard != null) {

                    LayoutDetailsVBox.this.startWizard(wizard);
                }
            }
        };



        Action removeLayoutComponent = new DefaultDialogAction("Remove") {

            @Override
            public void handle(ActionEvent ae) {

                KeyValuePair<ILayoutComponent, VisualProperty> selectedItem = LayoutDetailsVBox.this.layoutComponentListView
                        .getSelectedItem();

                Action result = Dialogs
                        .create()
                        .title("Confirm removal!")
                        .message(
                                "Are you sure you want to remove layout component "
                                        + selectedItem.getKey()
                                        + " for Visual Property "
                                        + selectedItem.getValue() + "?")
                        .showConfirm();

                if (result == Dialog.Actions.YES) {

                    LayoutDetailsVBox.this.getLayout()
                            .removeLayoutProviderForVisualProperty(
                                    selectedItem.getKey(),
                                    selectedItem.getValue());
                    LayoutDetailsVBox.this.layoutComponentListView.items()
                            .remove(selectedItem);
                    LayoutDetailsVBox.this.layoutComponentListView.update();
                }

            }
        };

        this.layoutComponentListView.contextMenuActions().addAll(
                addLayoutComponent, removeLayoutComponent);
    }
}
