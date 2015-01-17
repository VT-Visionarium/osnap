package edu.vt.arc.vis.osnap.javafx.dialogs;


//@formatter:off
/*
* _
* The Open Semantic Network Analysis Platform (OSNAP)
* _
* Copyright (C) 2012 - 2014 Visionarium at Virginia Tech
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


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;

import org.jutility.javafx.control.labeled.LabeledTextField;

import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;


/**
 * The {@code NodePropertiesDialog} provides a dialog box for the display of
 * {@link INode Node} properties.
 * 
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.1.1
 * @since 0.1.0
 */
public class NodePropertiesDialog
        extends Dialog<ButtonType> {

    private final GridPane content;

    /**
     * Creates a new instance of the {@code GraphPropertiesDialog} class.
     * 
     * @param owner
     *            the owner of this {@code NodePropertiesDialog}.
     * @param node
     *            the node for which the properties are being displayed
     */
    public NodePropertiesDialog(final javafx.scene.Node owner, final INode node) {

        this(owner == null ? null : owner.getScene().getWindow(), node);
    }

    /**
     * Creates a new instance of the {@code NodePropertiesDialog} class.
     * 
     * @param owner
     *            the owner of this {@code NodePropertiesDialog}.
     * @param node
     *            the node for which the properties are being displayed
     */
    public NodePropertiesDialog(final Window owner, final INode node) {

        super();

        super.initOwner(owner);
        this.setTitle("Node Properties");

        this.content = new GridPane();
        this.content.setAlignment(Pos.CENTER);
        this.content.setPadding(new Insets(25, 25, 25, 25));
        this.content.setVgap(10);
        this.content.setHgap(10);

        this.getDialogPane().setContent(this.content);

        LabeledTextField degreeField = new LabeledTextField("Degree");
        degreeField.setText(String.valueOf(node.degree()));
        degreeField.setEditable(false);

        LabeledTextField inDegreeField = new LabeledTextField("In Degree");
        inDegreeField.setText(String.valueOf(node.inDegree()));
        inDegreeField.setEditable(false);

        LabeledTextField outDegreeField = new LabeledTextField("Out Degree");
        outDegreeField.setText(String.valueOf(node.outDegree()));
        outDegreeField.setEditable(false);

        LabeledTextField edgeDegreeField = new LabeledTextField("Edge Degree");
        edgeDegreeField.setText(String.valueOf(node.edgeDegree()));
        edgeDegreeField.setEditable(false);

        LabeledTextField edgeInDegreeField = new LabeledTextField(
                "Edge In Degree");
        edgeInDegreeField.setText(String.valueOf(node.edgeInDegree()));
        edgeInDegreeField.setEditable(false);

        LabeledTextField edgeOutDegreeField = new LabeledTextField(
                "Edge Out Degree");
        edgeOutDegreeField.setText(String.valueOf(node.edgeOutDegree()));
        edgeOutDegreeField.setEditable(false);

        LabeledTextField hyperEdgeDegreeField = new LabeledTextField(
                "Hyper Edge Degree");
        hyperEdgeDegreeField.setText(String.valueOf(node.hyperEdgeDegree()));
        hyperEdgeDegreeField.setEditable(false);

        LabeledTextField hyperEdgeInDegreeField = new LabeledTextField(
                "Hyper Edge In Degree");
        hyperEdgeInDegreeField
                .setText(String.valueOf(node.hyperEdgeInDegree()));
        hyperEdgeInDegreeField.setEditable(false);

        LabeledTextField hyperEdgeOutDegreeField = new LabeledTextField(
                "Hyper Edge Out Degree");
        hyperEdgeOutDegreeField.setText(String.valueOf(node
                .hyperEdgeOutDegree()));
        hyperEdgeOutDegreeField.setEditable(false);


        this.content.add(degreeField, 0, 0);
        this.content.add(inDegreeField, 0, 1);
        this.content.add(outDegreeField, 0, 2);

        this.content.add(edgeDegreeField, 1, 0);
        this.content.add(edgeInDegreeField, 1, 1);
        this.content.add(edgeOutDegreeField, 1, 2);

        this.content.add(hyperEdgeDegreeField, 2, 0);
        this.content.add(hyperEdgeInDegreeField, 2, 1);
        this.content.add(hyperEdgeOutDegreeField, 2, 2);

        this.getDialogPane().getButtonTypes().add(ButtonType.OK);
    }

}
