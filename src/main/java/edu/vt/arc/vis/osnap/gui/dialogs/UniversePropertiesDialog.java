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


/**
 * 
 */
package edu.vt.arc.vis.osnap.gui.dialogs;

import edu.vt.arc.vis.osnap.graph.Universe;
import edu.vt.arc.vis.osnap.graph.common.IUniverse;
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
 * @author Shawn P Neuman
 *
 */
public class UniversePropertiesDialog extends Stage {
    
    String id;
    Universe universe;

    
    
    /**
     * @param uni
     *            the graph whose properties are to be displayed
     */
    public UniversePropertiesDialog(IUniverse uni) {

        this.universe = (Universe) uni;

        this.setTitle("Universe Properties");
        this.initModality(Modality.APPLICATION_MODAL);
        Scene graphProperties = new Scene(new VBox());

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setVgap(10);
        grid.setHgap(10);
        
        Text numGraphs = new Text("Number of Graphs");
        TextField numGraphField = new TextField();
        numGraphField.setText(String.valueOf(universe.getGraphs().size()));
        numGraphField.setEditable(false);

        Text size = new Text("Size");
        TextField sizeField = new TextField();
        sizeField.setText(String.valueOf(universe.size()));
        sizeField.setEditable(false);

        Text sizeEdges = new Text("Size of Edges");
        TextField sizeEdgesField = new TextField();
        sizeEdgesField.setText(String.valueOf(universe.sizeEdges()));
        sizeEdgesField.setEditable(false);

        Text sizeHyperEdges = new Text("Size of Hyper Edges");
        TextField sizeHyperEdgesField = new TextField();
        sizeHyperEdgesField.setText(String.valueOf(universe.sizeHyperEdges()));
        sizeHyperEdgesField.setEditable(false);

        Text order = new Text("Order");
        TextField orderField = new TextField();
        orderField.setText(String.valueOf(universe.order()));
        orderField.setEditable(false);

        Text rank = new Text("Rank");
        TextField rankField = new TextField();
        rankField.setText(String.valueOf(universe.rank()));
        rankField.setEditable(false);

        Text maxDegree = new Text("Max Degree");
        TextField maxDegreeField = new TextField();
        maxDegreeField.setText(String.valueOf(universe.maxDegree()));
        maxDegreeField.setEditable(false);

        Text maxDegreeEdges = new Text("Max Degree Edges");
        TextField maxDegreeEdgesField = new TextField();
        maxDegreeEdgesField.setText(String.valueOf(universe.maxDegreeEdges()));
        maxDegreeEdgesField.setEditable(false);

        Text maxDegreeHyperEdges = new Text("Max Degree of Hyper Edges");
        TextField maxDegreeHyperEdgesField = new TextField();
        maxDegreeHyperEdgesField.setText(String.valueOf(universe
                .maxDegreeHyperEdges()));
        maxDegreeHyperEdgesField.setEditable(false);

        Text maxInDegree = new Text("Max In Degree");
        TextField maxInDegreeField = new TextField();
        maxInDegreeField.setText(String.valueOf(universe.maxInDegree()));
        maxInDegreeField.setEditable(false);

        Text maxInDegreeEdges = new Text("Max In Degree Edges");
        TextField maxInDegreeEdgesField = new TextField();
        maxInDegreeEdgesField.setText(String.valueOf(universe.maxInDegreeEdges()));
        maxInDegreeEdgesField.setEditable(false);

        Text maxInDegreeHyperEdges = new Text("Max In Degree Hyper Edges");
        TextField maxInDegreeHyperEdgesField = new TextField();
        maxInDegreeHyperEdgesField.setText(String.valueOf(universe
                .maxInDegreeHyperEdges()));
        maxInDegreeHyperEdgesField.setEditable(false);

        Text maxOutDegree = new Text("Max Out Degree");
        TextField maxOutDegreeField = new TextField();
        maxOutDegreeField.setText(String.valueOf(universe.maxOutDegree()));
        maxOutDegreeField.setEditable(false);

        Text maxOutDegreeEdges = new Text("Max Out Degree Edges");
        TextField maxOutDegreeEdgesField = new TextField();
        maxOutDegreeEdgesField
                .setText(String.valueOf(universe.maxOutDegreeEdges()));
        maxOutDegreeEdgesField.setEditable(false);

        Text maxOutDegreeHyperEdges = new Text("Max Out Degree Hyper Edges");
        TextField maxOutDegreeHyperEdgesField = new TextField();
        maxOutDegreeHyperEdgesField.setText(String.valueOf(universe
                .maxOutDegreeHyperEdges()));
        maxOutDegreeHyperEdgesField.setEditable(false);

        Text minDegree = new Text("Min Degree");
        TextField minDegreeField = new TextField();
        minDegreeField.setText(String.valueOf(universe.minDegree()));
        minDegreeField.setEditable(false);

        Text minDegreeEdges = new Text("Min Degree Edges");
        TextField minDegreeEdgesField = new TextField();
        minDegreeEdgesField.setText(String.valueOf(universe.minDegreeEdges()));
        minDegreeEdgesField.setEditable(false);

        Text minDegreeHyperEdges = new Text("Min Degree of Hyper Edges");
        TextField minDegreeHyperEdgesField = new TextField();
        minDegreeHyperEdgesField.setText(String.valueOf(universe
                .minDegreeHyperEdges()));
        minDegreeHyperEdgesField.setEditable(false);

        Text minInDegree = new Text("Min In Degree");
        TextField minInDegreeField = new TextField();
        minInDegreeField.setText(String.valueOf(universe.minInDegree()));
        minInDegreeField.setEditable(false);

        Text minInDegreeEdges = new Text("Min In Degree Edges");
        TextField minInDegreeEdgesField = new TextField();
        minInDegreeEdgesField.setText(String.valueOf(universe.minInDegreeEdges()));
        minInDegreeEdgesField.setEditable(false);

        Text minInDegreeHyperEdges = new Text("Min In Degree Hyper Edges");
        TextField minInDegreeHyperEdgesField = new TextField();
        minInDegreeHyperEdgesField.setText(String.valueOf(universe
                .minInDegreeHyperEdges()));
        minInDegreeHyperEdgesField.setEditable(false);

        Text minOutDegree = new Text("Min Out Degree");
        TextField minOutDegreeField = new TextField();
        minOutDegreeField.setText(String.valueOf(universe.minOutDegree()));
        minOutDegreeField.setEditable(false);

        Text minOutDegreeEdges = new Text("Min Out Degree Edges");
        TextField minOutDegreeEdgesField = new TextField();
        minOutDegreeEdgesField
                .setText(String.valueOf(universe.minOutDegreeEdges()));
        minOutDegreeEdgesField.setEditable(false);

        Text minOutDegreeHyperEdges = new Text("Min Out Degree Hyper Edges");
        TextField minOutDegreeHyperEdgesField = new TextField();
        minOutDegreeHyperEdgesField.setText(String.valueOf(universe
                .minOutDegreeHyperEdges()));
        minOutDegreeHyperEdgesField.setEditable(false);

        Text uniform = new Text("Is Uniform");
        TextField uniformField = new TextField();
        uniformField.setText(universe.isUniform().toString());
        uniformField.setEditable(false);

        Text uniformK = new Text("Uniform Constant");
        TextField uniformKField = new TextField();
        uniformKField.setText(String.valueOf(universe.uniformK()));
        uniformKField.setEditable(false);

        Text regular = new Text("Is Regular");
        TextField regularField = new TextField();
        regularField.setText(universe.isRegular().toString());
        regularField.setEditable(false);

        Text regularK = new Text("Regular Constant");
        TextField regularKField = new TextField();
        regularKField.setText(String.valueOf(universe.regularK()));
        regularKField.setEditable(false);

        Button ok = new Button("OK");
        ok.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent action) {

                close();

            }

        });

        grid.add(numGraphs, 0, 0);
        grid.add(numGraphField, 1, 0);
        grid.add(size, 0, 1);
        grid.add(sizeField, 1, 1);
        grid.add(sizeEdges, 0, 2);
        grid.add(sizeEdgesField, 1, 2);
        grid.add(sizeHyperEdges, 0, 3);
        grid.add(sizeHyperEdgesField, 1, 3);
        grid.add(order, 0, 4);
        grid.add(orderField, 1, 4);
        grid.add(rank, 0, 5);
        grid.add(rankField, 1, 5);

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
