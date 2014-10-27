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


package graphVisualizer.gui.dialogs;


import graphVisualizer.graph.Graph;
import graphVisualizer.graph.common.IGraph;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * creates a dialog box for display graph properties and values
 * 
 * @author Shawn P Neuman
 * 
 */
public class GraphPropertiesDialog
        extends Stage {

    String id;
    Graph  graph;


    /**
     * @param iGraph
     *            the graph whose properties are to be displayed
     */
    public GraphPropertiesDialog(IGraph iGraph) {

        this.graph = (Graph) iGraph;

        this.setTitle("Graph Properties");
        this.initModality(Modality.APPLICATION_MODAL);
        Scene graphProperties = new Scene(new VBox());

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setVgap(10);
        grid.setHgap(10);

        Text size = new Text("Size");
        TextField sizeField = new TextField();
        sizeField.setText(String.valueOf(graph.size()));
        sizeField.setEditable(false);

        Text sizeEdges = new Text("Size of Edges");
        TextField sizeEdgesField = new TextField();
        sizeEdgesField.setText(String.valueOf(graph.sizeEdges()));
        sizeEdgesField.setEditable(false);

        Text sizeHyperEdges = new Text("Size of Hyper Edges");
        TextField sizeHyperEdgesField = new TextField();
        sizeHyperEdgesField.setText(String.valueOf(graph.sizeHyperEdges()));
        sizeHyperEdgesField.setEditable(false);

        Text order = new Text("Order");
        TextField orderField = new TextField();
        orderField.setText(String.valueOf(graph.order()));
        orderField.setEditable(false);

        Text rank = new Text("Rank");
        TextField rankField = new TextField();
        rankField.setText(String.valueOf(graph.rank()));
        rankField.setEditable(false);

        Text maxDegree = new Text("Max Degree");
        TextField maxDegreeField = new TextField();
        maxDegreeField.setText(String.valueOf(graph.maxDegree()));
        maxDegreeField.setEditable(false);

        Text maxDegreeEdges = new Text("Max Degree Edges");
        TextField maxDegreeEdgesField = new TextField();
        maxDegreeEdgesField.setText(String.valueOf(graph.maxDegreeEdges()));
        maxDegreeEdgesField.setEditable(false);

        Text maxDegreeHyperEdges = new Text("Max Degree of Hyper Edges");
        TextField maxDegreeHyperEdgesField = new TextField();
        maxDegreeHyperEdgesField.setText(String.valueOf(graph
                .maxDegreeHyperEdges()));
        maxDegreeHyperEdgesField.setEditable(false);

        Text maxInDegree = new Text("Max In Degree");
        TextField maxInDegreeField = new TextField();
        maxInDegreeField.setText(String.valueOf(graph.maxInDegree()));
        maxInDegreeField.setEditable(false);

        Text maxInDegreeEdges = new Text("Max In Degree Edges");
        TextField maxInDegreeEdgesField = new TextField();
        maxInDegreeEdgesField.setText(String.valueOf(graph.maxInDegreeEdges()));
        maxInDegreeEdgesField.setEditable(false);

        Text maxInDegreeHyperEdges = new Text("Max In Degree Hyper Edges");
        TextField maxInDegreeHyperEdgesField = new TextField();
        maxInDegreeHyperEdgesField.setText(String.valueOf(graph
                .maxInDegreeHyperEdges()));
        maxInDegreeHyperEdgesField.setEditable(false);

        Text maxOutDegree = new Text("Max Out Degree");
        TextField maxOutDegreeField = new TextField();
        maxOutDegreeField.setText(String.valueOf(graph.maxOutDegree()));
        maxOutDegreeField.setEditable(false);

        Text maxOutDegreeEdges = new Text("Max Out Degree Edges");
        TextField maxOutDegreeEdgesField = new TextField();
        maxOutDegreeEdgesField
                .setText(String.valueOf(graph.maxOutDegreeEdges()));
        maxOutDegreeEdgesField.setEditable(false);

        Text maxOutDegreeHyperEdges = new Text("Max Out Degree Hyper Edges");
        TextField maxOutDegreeHyperEdgesField = new TextField();
        maxOutDegreeHyperEdgesField.setText(String.valueOf(graph
                .maxOutDegreeHyperEdges()));
        maxOutDegreeHyperEdgesField.setEditable(false);

        Text minDegree = new Text("Min Degree");
        TextField minDegreeField = new TextField();
        minDegreeField.setText(String.valueOf(graph.minDegree()));
        minDegreeField.setEditable(false);

        Text minDegreeEdges = new Text("Min Degree Edges");
        TextField minDegreeEdgesField = new TextField();
        minDegreeEdgesField.setText(String.valueOf(graph.minDegreeEdges()));
        minDegreeEdgesField.setEditable(false);

        Text minDegreeHyperEdges = new Text("Min Degree of Hyper Edges");
        TextField minDegreeHyperEdgesField = new TextField();
        minDegreeHyperEdgesField.setText(String.valueOf(graph
                .minDegreeHyperEdges()));
        minDegreeHyperEdgesField.setEditable(false);

        Text minInDegree = new Text("Min In Degree");
        TextField minInDegreeField = new TextField();
        minInDegreeField.setText(String.valueOf(graph.minInDegree()));
        minInDegreeField.setEditable(false);

        Text minInDegreeEdges = new Text("Min In Degree Edges");
        TextField minInDegreeEdgesField = new TextField();
        minInDegreeEdgesField.setText(String.valueOf(graph.minInDegreeEdges()));
        minInDegreeEdgesField.setEditable(false);

        Text minInDegreeHyperEdges = new Text("Min In Degree Hyper Edges");
        TextField minInDegreeHyperEdgesField = new TextField();
        minInDegreeHyperEdgesField.setText(String.valueOf(graph
                .minInDegreeHyperEdges()));
        minInDegreeHyperEdgesField.setEditable(false);

        Text minOutDegree = new Text("Min Out Degree");
        TextField minOutDegreeField = new TextField();
        minOutDegreeField.setText(String.valueOf(graph.minOutDegree()));
        minOutDegreeField.setEditable(false);

        Text minOutDegreeEdges = new Text("Min Out Degree Edges");
        TextField minOutDegreeEdgesField = new TextField();
        minOutDegreeEdgesField
                .setText(String.valueOf(graph.minOutDegreeEdges()));
        minOutDegreeEdgesField.setEditable(false);

        Text minOutDegreeHyperEdges = new Text("Min Out Degree Hyper Edges");
        TextField minOutDegreeHyperEdgesField = new TextField();
        minOutDegreeHyperEdgesField.setText(String.valueOf(graph
                .minOutDegreeHyperEdges()));
        minOutDegreeHyperEdgesField.setEditable(false);

        Text uniform = new Text("Is Uniform");
        TextField uniformField = new TextField();
        uniformField.setText(graph.isUniform().toString());
        uniformField.setEditable(false);

        Text uniformK = new Text("Uniform Constant");
        TextField uniformKField = new TextField();
        uniformKField.setText(String.valueOf(graph.uniformK()));
        uniformKField.setEditable(false);

        Text regular = new Text("Is Regular");
        TextField regularField = new TextField();
        regularField.setText(graph.isRegular().toString());
        regularField.setEditable(false);

        Text regularK = new Text("Regular Constant");
        TextField regularKField = new TextField();
        regularKField.setText(String.valueOf(graph.regularK()));
        regularKField.setEditable(false);

        Button ok = new Button("OK");
        ok.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent action) {

                close();

            }

        });

        grid.add(size, 0, 0);
        grid.add(sizeField, 1, 0);
        grid.add(sizeEdges, 0, 1);
        grid.add(sizeEdgesField, 1, 1);
        grid.add(sizeHyperEdges, 0, 2);
        grid.add(sizeHyperEdgesField, 1, 2);
        grid.add(order, 0, 3);
        grid.add(orderField, 1, 3);
        grid.add(rank, 0, 4);
        grid.add(rankField, 1, 4);

        grid.add(maxDegree, 2, 0);
        grid.add(maxDegreeField, 3, 0);
        grid.add(maxInDegree, 2, 1);
        grid.add(maxInDegreeField, 3, 1);
        grid.add(maxOutDegree, 2, 2);
        grid.add(maxOutDegreeField, 3, 2);
        grid.add(minDegree, 2, 3);
        grid.add(minDegreeField, 3, 3);
        grid.add(minInDegree, 2, 4);
        grid.add(minInDegreeField, 3, 4);
        grid.add(minOutDegree, 2, 5);
        grid.add(minOutDegreeField, 3, 5);

        grid.add(maxDegreeEdges, 4, 0);
        grid.add(maxDegreeEdgesField, 5, 0);
        grid.add(maxInDegreeEdges, 4, 1);
        grid.add(maxInDegreeEdgesField, 5, 1);
        grid.add(maxOutDegreeEdges, 4, 2);
        grid.add(maxOutDegreeEdgesField, 5, 2);
        grid.add(minDegreeEdges, 4, 3);
        grid.add(minDegreeEdgesField, 5, 3);
        grid.add(minInDegreeEdges, 4, 4);
        grid.add(minInDegreeEdgesField, 5, 4);
        grid.add(minOutDegreeEdges, 4, 5);
        grid.add(minOutDegreeEdgesField, 5, 5);

        grid.add(maxDegreeHyperEdges, 6, 0);
        grid.add(maxDegreeHyperEdgesField, 7, 0);
        grid.add(maxInDegreeHyperEdges, 6, 1);
        grid.add(maxInDegreeHyperEdgesField, 7, 1);
        grid.add(maxOutDegreeHyperEdges, 6, 2);
        grid.add(maxOutDegreeHyperEdgesField, 7, 2);
        grid.add(minDegreeHyperEdges, 6, 3);
        grid.add(minDegreeHyperEdgesField, 7, 3);
        grid.add(minInDegreeHyperEdges, 6, 4);
        grid.add(minInDegreeHyperEdgesField, 7, 4);
        grid.add(minOutDegreeHyperEdges, 6, 5);
        grid.add(minOutDegreeHyperEdgesField, 7, 5);

        grid.add(uniform, 8, 0);
        grid.add(uniformField, 9, 0);
        grid.add(uniformK, 8, 1);
        grid.add(uniformKField, 9, 1);
        grid.add(regular, 8, 2);
        grid.add(regularField, 9, 2);
        grid.add(regularK, 8, 3);
        grid.add(regularKField, 9, 3);

        grid.add(ok, 5, 6, 2, 1);

        ((VBox) graphProperties.getRoot()).getChildren().add(grid);

        this.setScene(graphProperties);
        ok.requestFocus();
    }



}
