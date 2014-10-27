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
import graphVisualizer.graph.Node;
import graphVisualizer.graph.common.INode;


/**
 * creates a dialog box for display node properties and node values
 * 
 * @author Shawn P Neuman
 * 
 */
public class NodePropertiesDialog
        extends Stage {

    private Node node;

    /**
     * @param inode
     *            the node for which the properties are being displayed
     */
    public NodePropertiesDialog(INode inode) {

        this.node = (Node) inode;

        this.setTitle("Node Properties");
        this.initModality(Modality.APPLICATION_MODAL);
        Scene nodeProperties = new Scene(new VBox());

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setVgap(10);
        grid.setHgap(10);

        Text degree = new Text("Degree");
        TextField degreeField = new TextField();
        degreeField.setText(String.valueOf(node.degree()));
        degreeField.setEditable(false);

        Text inDegree = new Text("In Degree");
        TextField inDegreeField = new TextField();
        inDegreeField.setText(String.valueOf(node.inDegree()));
        inDegreeField.setEditable(false);

        Text outDegree = new Text("Out Degree");
        TextField outDegreeField = new TextField();
        outDegreeField.setText(String.valueOf(node.outDegree()));
        outDegreeField.setEditable(false);

        Text edgeDegree = new Text("Edge Degree");
        TextField edgeDegreeField = new TextField();
        edgeDegreeField.setText(String.valueOf(node.edgeDegree()));
        edgeDegreeField.setEditable(false);

        Text edgeInDegree = new Text("Edge In Degree");
        TextField edgeInDegreeField = new TextField();
        edgeInDegreeField.setText(String.valueOf(node.edgeInDegree()));
        edgeInDegreeField.setEditable(false);

        Text edgeOutDegree = new Text("Edge Out Degree");
        TextField edgeOutDegreeField = new TextField();
        edgeOutDegreeField.setText(String.valueOf(node.edgeOutDegree()));
        edgeOutDegreeField.setEditable(false);

        Text hyperEdgeDegree = new Text("Hyper Edge Degree");
        TextField hyperEdgeDegreeField = new TextField();
        hyperEdgeDegreeField.setText(String.valueOf(node.hyperEdgeDegree()));
        hyperEdgeDegreeField.setEditable(false);

        Text hyperEdgeInDegree = new Text("Hyper Edge In Degree");
        TextField hyperEdgeInDegreeField = new TextField();
        hyperEdgeInDegreeField
                .setText(String.valueOf(node.hyperEdgeInDegree()));
        hyperEdgeInDegreeField.setEditable(false);

        Text hyperEdgeOutDegree = new Text("Hyper Edge Out Degree");
        TextField hyperEdgeOutDegreeField = new TextField();
        hyperEdgeOutDegreeField.setText(String.valueOf(node
                .hyperEdgeOutDegree()));
        hyperEdgeOutDegreeField.setEditable(false);

        Button ok = new Button("OK");
        ok.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent action) {

                close();

            }

        });

        grid.add(degree, 0, 0);
        grid.add(degreeField, 1, 0);
        grid.add(inDegree, 0, 1);
        grid.add(inDegreeField, 1, 1);
        grid.add(outDegree, 0, 2);
        grid.add(outDegreeField, 1, 2);

        grid.add(edgeDegree, 2, 0);
        grid.add(edgeDegreeField, 3, 0);
        grid.add(edgeInDegree, 2, 1);
        grid.add(edgeInDegreeField, 3, 1);
        grid.add(edgeOutDegree, 2, 2);
        grid.add(edgeOutDegreeField, 3, 2);

        grid.add(hyperEdgeDegree, 4, 0);
        grid.add(hyperEdgeDegreeField, 5, 0);
        grid.add(hyperEdgeInDegree, 4, 1);
        grid.add(hyperEdgeInDegreeField, 5, 1);
        grid.add(hyperEdgeOutDegree, 4, 2);
        grid.add(hyperEdgeOutDegreeField, 5, 2);

        grid.add(ok, 3, 3);

        ((VBox) nodeProperties.getRoot()).getChildren().add(grid);
        this.setScene(nodeProperties);
        ok.requestFocus();
    }

}
