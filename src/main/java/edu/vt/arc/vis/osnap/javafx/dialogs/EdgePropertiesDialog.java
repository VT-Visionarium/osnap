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


package edu.vt.arc.vis.osnap.javafx.dialogs;


import edu.vt.arc.vis.osnap.core.domain.graph.Edge;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IEdge;
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
 * dialog box for displaying the possible properties of an edge including
 * property values of a specific edge
 *
 * @author Shawn P Neuman
 *
 */
public class EdgePropertiesDialog
        extends Stage {

    private Edge edge;

    /**
     * constructor needs an edge to populate all the value fields
     *
     * @param iEdge
     */
    public EdgePropertiesDialog(IEdge iEdge) {

        this.edge = (Edge) iEdge;

        this.setTitle("Edge Properties");
        this.initModality(Modality.APPLICATION_MODAL);
        Scene edgeProperties = new Scene(new VBox());

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setVgap(10);
        grid.setHgap(10);

        Text isDirected = new Text("Directed ?");
        TextField isDirectedField = new TextField();
        isDirectedField.setText(edge.isDirected().toString());
        isDirectedField.setEditable(false);

        Button ok = new Button("OK");
        ok.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent action) {

                close();

            }

        });

        grid.add(isDirected, 0, 0);
        grid.add(isDirectedField, 1, 0);
        grid.add(ok, 1, 1);

        ((VBox) edgeProperties.getRoot()).getChildren().add(grid);

        this.setScene(edgeProperties);
        ok.requestFocus();
    }

}
