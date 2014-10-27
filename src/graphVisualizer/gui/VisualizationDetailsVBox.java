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


package graphVisualizer.gui;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import org.jutility.javafx.control.ListViewWithSearchPanel;

import graphVisualizer.gui.events.ExportLayoutEvent;
import graphVisualizer.gui.events.SwitchTabEvent;
import graphVisualizer.gui.stringConverters.VisualGraphObjectStringConverter;
import graphVisualizer.gui.stringConverters.VisualGraphObjectStringConverterConfiguration;
import graphVisualizer.gui.stringConverters.VisualObjectStringConverter;
import graphVisualizer.gui.widgets.VisualObjectData;
import graphVisualizer.visualization.VisualEdge;
import graphVisualizer.visualization.VisualGraph;
import graphVisualizer.visualization.VisualNode;
import graphVisualizer.visualization.Visualization;


/**
 * @author Shawn P Neuman
 * @version 1.0
 */
public class VisualizationDetailsVBox
        extends VBox {


    private final ObjectProperty<Visualization>  visualization;

    private GridPane                             grid1;
    private ListViewWithSearchPanel<VisualGraph> graphListView;
    private ListViewWithSearchPanel<VisualNode>  nodeListView;
    private ListViewWithSearchPanel<VisualEdge>  edgeListView;

    private VisualObjectData                     nodeData;
    private VisualObjectData                     edgeData;

    private Button                               exportButton;
    private Button                               addLayoutComponentButton;



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
     * Creates a new instance of the {@link VisualizationDetailsVBox} class.
     */
    public VisualizationDetailsVBox() {

        this.visualization = new SimpleObjectProperty<>();

        this.setStyle("-fx-background-color: cornsilk");
        // main grid pane
        grid1 = new GridPane();
        grid1.setPadding(new Insets(10, 10, 10, 10));

        // first inner v box to contain graph information
        // using v box to layout label, and then inner grid pane to arrange
        // list views and tables

        VBox graphBox = new VBox();
        graphBox.setStyle("-fx-border-color: #000000; -fx-border-width: 1px");
        graphBox.setAlignment(Pos.CENTER);
        graphBox.setPadding(new Insets(10, 10, 10, 10));
        Text text = new Text();
        text.setFont(new Font(20));
        text.setStyle("-fx-font-weight: bold");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setText("Graph Information");

        graphBox.getChildren().add(text);

        // inner grid pane to house graph info

        GridPane graphGrid = new GridPane();
        graphGrid.setHgap(25);
        graphGrid.setVgap(10);
        graphGrid.setPadding(new Insets(10, 10, 10, 10));

        // houses node info

        VBox nodeBox = new VBox();
        nodeBox.setStyle("-fx-border-color: #000000; -fx-border-width: 1px");
        nodeBox.setAlignment(Pos.CENTER);
        nodeBox.setPadding(new Insets(10, 10, 10, 10));
        GridPane nodeGrid = new GridPane();
        nodeGrid.setHgap(25);
        nodeGrid.setVgap(10);
        nodeGrid.setPadding(new Insets(10, 10, 10, 10));
        Text text1 = new Text();
        text1.setFont(new Font(20));
        text1.setStyle("-fx-font-weight: bold");
        text1.setTextAlignment(TextAlignment.CENTER);
        text1.setText("Node Information");
        nodeBox.getChildren().add(text1);

        // houses edge info
        VBox edgeBox = new VBox();
        edgeBox.setStyle("-fx-border-color: #000000; -fx-border-width: 1px");
        edgeBox.setAlignment(Pos.CENTER);
        edgeBox.setPadding(new Insets(10, 10, 10, 10));
        GridPane edgeGrid = new GridPane();
        edgeGrid.setHgap(25);
        edgeGrid.setVgap(10);
        edgeGrid.setPadding(new Insets(10, 10, 10, 10));
        Text text2 = new Text();
        text2.setFont(new Font(20));
        text2.setStyle("-fx-font-weight: bold");
        text2.setTextAlignment(TextAlignment.CENTER);
        text2.setText("Edge Information");
        edgeBox.getChildren().add(text2);

        this.graphListView = new ListViewWithSearchPanel<>(
                "Graph Layout",
                new VisualGraphObjectStringConverter<VisualGraph>(
                        VisualGraphObjectStringConverterConfiguration.GRAPH_OBJECT_ID));

        this.nodeListView = new ListViewWithSearchPanel<>(
                "Node Layout",
                new VisualObjectStringConverter<VisualNode>(
                        VisualGraphObjectStringConverterConfiguration.GRAPH_OBJECT_ID));



        this.edgeListView = new ListViewWithSearchPanel<>(
                "Edge Layout",
                new VisualObjectStringConverter<VisualEdge>(
                        VisualGraphObjectStringConverterConfiguration.GRAPH_OBJECT_ID));


        this.nodeData = new VisualObjectData("Node Layout Data");
        this.edgeData = new VisualObjectData("Edge Layout Data");

        this.exportButton = new Button("Export Visualization to X3D");
        this.addLayoutComponentButton = new Button(
                "Add another layout component");



        graphGrid.add(graphListView, 0, 1);

        graphBox.getChildren().add(graphGrid);

        nodeGrid.add(nodeListView, 0, 1);
        nodeGrid.add(nodeData, 1, 1);

        nodeBox.getChildren().add(nodeGrid);

        edgeGrid.add(edgeListView, 0, 1);
        edgeGrid.add(edgeData, 1, 1);

        edgeBox.getChildren().add(edgeGrid);


        this.grid1.add(graphBox, 0, 0);
        this.grid1.add(nodeBox, 1, 0);
        this.grid1.add(edgeBox, 2, 0);
        this.grid1.add(this.addLayoutComponentButton, 0, 1);
        this.grid1.add(this.exportButton, 1, 1);

        this.getChildren().addAll(this.grid1);

        this.setUpEventHandlers();
    }



    private void populate(Visualization visualization) {

        this.clear();
        this.graphListView.items().addAll(visualization.getVisualGraphs());
        this.nodeListView.items().addAll(visualization.getVisualNodes());
        this.edgeListView.items().addAll(visualization.getVisualEdges());
    }



    private void clear() {

        graphListView.clear();
        nodeListView.clear();
        edgeListView.clear();
    }

    private void setUpEventHandlers() {


        // List Views
        this.nodeListView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<VisualNode>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends VisualNode> observable,
                            VisualNode oldValue, VisualNode newValue) {

                        nodeData.populateFields(newValue);

                    }
                });

        this.edgeListView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<VisualEdge>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends VisualEdge> observable,
                            VisualEdge oldValue, VisualEdge newValue) {

                        edgeData.populateFields(newValue);
                    }
                });



        // Clearing List Views
        this.visualization.addListener(new ChangeListener<Visualization>() {

            @Override
            public void changed(
                    ObservableValue<? extends Visualization> observable,
                    Visualization oldValue, Visualization newValue) {

                if (newValue != null) {

                    VisualizationDetailsVBox.this.populate(newValue);
                }
                else {

                    VisualizationDetailsVBox.this.clear();
                }
            }
        });



        // Buttons
        this.exportButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {


                ExportLayoutEvent exportEvent = new ExportLayoutEvent(
                        ExportLayoutEvent.EXPORT, VisualizationDetailsVBox.this
                                .getVisualization());
                fireEvent(exportEvent);
            }
        });


        this.addLayoutComponentButton
                .setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent arg0) {

                        SwitchTabEvent switchTab = new SwitchTabEvent(
                                SwitchTabEvent.SWITCH);
                        fireEvent(switchTab);
                    }
                });
    }
}
