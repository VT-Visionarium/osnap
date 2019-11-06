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
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;

import org.jutility.javafx.control.labeled.LabeledTextField;

import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraph;


/**
 * The {@code GraphPropertiesDialog} provides a dialog box for the display of
 * {@link IGraph Graph} properties.
 * 
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.1.1
 * @since 0.1.0
 */
public class GraphPropertiesDialog
        extends Dialog<ButtonType> {

    private final GridPane content;


    /**
     * Creates a new instance of the {@code GraphPropertiesDialog} class.
     * 
     * @param owner
     *            the owner of this {@code GraphPropertiesDialog}.
     * @param graph
     *            the {@link IGraph} whose properties are to be displayed.
     */
    public GraphPropertiesDialog(final Node owner, final IGraph graph) {

        this(owner == null ? null : owner.getScene().getWindow(), graph);
    }

    /**
     * Creates a new instance of the {@code GraphPropertiesDialog} class.
     * 
     * @param owner
     *            the owner of this {@code GraphPropertiesDialog}.
     * @param graph
     *            the {@link IGraph} whose properties are to be displayed.
     */
    public GraphPropertiesDialog(final Window owner, final IGraph graph) {

        super();

        this.initOwner(owner);
        this.setTitle("Graph Properties");

        this.content = new GridPane();
        this.content.setAlignment(Pos.CENTER);
        this.content.setPadding(new Insets(25, 25, 25, 25));
        this.content.setVgap(10);
        this.content.setHgap(20);

        this.getDialogPane().setContent(this.content);

        LabeledTextField sizeField = new LabeledTextField("Size");
        sizeField.setText(String.valueOf(graph.size()));
        sizeField.setEditable(false);

        LabeledTextField sizeEdgesField = new LabeledTextField("Size of Edges");
        sizeEdgesField.setText(String.valueOf(graph.sizeEdges()));
        sizeEdgesField.setEditable(false);

        LabeledTextField sizeHyperEdgesField = new LabeledTextField(
                "Size of Hyper Edges");
        sizeHyperEdgesField.setText(String.valueOf(graph.sizeHyperEdges()));
        sizeHyperEdgesField.setEditable(false);

        LabeledTextField orderField = new LabeledTextField("Order");
        orderField.setText(String.valueOf(graph.order()));
        orderField.setEditable(false);

        LabeledTextField rankField = new LabeledTextField("Rank");
        rankField.setText(String.valueOf(graph.rank()));
        rankField.setEditable(false);

        LabeledTextField maxDegreeField = new LabeledTextField("Max Degree");
        maxDegreeField.setText(String.valueOf(graph.maxDegree()));
        maxDegreeField.setEditable(false);

        LabeledTextField maxDegreeEdgesField = new LabeledTextField(
                "Max Degree Edges");
        maxDegreeEdgesField.setText(String.valueOf(graph.maxDegreeEdges()));
        maxDegreeEdgesField.setEditable(false);

        LabeledTextField maxDegreeHyperEdgesField = new LabeledTextField(
                "Max Degree of Hyper Edges");
        maxDegreeHyperEdgesField.setText(String.valueOf(graph
                .maxDegreeHyperEdges()));
        maxDegreeHyperEdgesField.setEditable(false);

        LabeledTextField maxInDegreeField = new LabeledTextField(
                "Max In Degree");
        maxInDegreeField.setText(String.valueOf(graph.maxInDegree()));
        maxInDegreeField.setEditable(false);

        LabeledTextField maxInDegreeEdgesField = new LabeledTextField(
                "Max In Degree Edges");
        maxInDegreeEdgesField.setText(String.valueOf(graph.maxInDegreeEdges()));
        maxInDegreeEdgesField.setEditable(false);

        LabeledTextField maxInDegreeHyperEdgesField = new LabeledTextField(
                "Max In Degree Hyper Edges");
        maxInDegreeHyperEdgesField.setText(String.valueOf(graph
                .maxInDegreeHyperEdges()));
        maxInDegreeHyperEdgesField.setEditable(false);

        LabeledTextField maxOutDegreeField = new LabeledTextField(
                "Max Out Degree");
        maxOutDegreeField.setText(String.valueOf(graph.maxOutDegree()));
        maxOutDegreeField.setEditable(false);

        LabeledTextField maxOutDegreeEdgesField = new LabeledTextField(
                "Max Out Degree Edges");
        maxOutDegreeEdgesField
                .setText(String.valueOf(graph.maxOutDegreeEdges()));
        maxOutDegreeEdgesField.setEditable(false);

        LabeledTextField maxOutDegreeHyperEdgesField = new LabeledTextField(
                "Max Out Degree Hyper Edges");
        maxOutDegreeHyperEdgesField.setText(String.valueOf(graph
                .maxOutDegreeHyperEdges()));
        maxOutDegreeHyperEdgesField.setEditable(false);

        LabeledTextField minDegreeField = new LabeledTextField("Min Degree");
        minDegreeField.setText(String.valueOf(graph.minDegree()));
        minDegreeField.setEditable(false);

        LabeledTextField minDegreeEdgesField = new LabeledTextField(
                "Min Degree Edges");
        minDegreeEdgesField.setText(String.valueOf(graph.minDegreeEdges()));
        minDegreeEdgesField.setEditable(false);

        LabeledTextField minDegreeHyperEdgesField = new LabeledTextField(
                "Min Degree of Hyper Edges");
        minDegreeHyperEdgesField.setText(String.valueOf(graph
                .minDegreeHyperEdges()));
        minDegreeHyperEdgesField.setEditable(false);

        LabeledTextField minInDegreeField = new LabeledTextField(
                "Min In Degree");
        minInDegreeField.setText(String.valueOf(graph.minInDegree()));
        minInDegreeField.setEditable(false);

        LabeledTextField minInDegreeEdgesField = new LabeledTextField(
                "Min In Degree Edges");
        minInDegreeEdgesField.setText(String.valueOf(graph.minInDegreeEdges()));
        minInDegreeEdgesField.setEditable(false);

        LabeledTextField minInDegreeHyperEdgesField = new LabeledTextField(
                "Min In Degree Hyper Edges");
        minInDegreeHyperEdgesField.setText(String.valueOf(graph
                .minInDegreeHyperEdges()));
        minInDegreeHyperEdgesField.setEditable(false);

        LabeledTextField minOutDegreeField = new LabeledTextField(
                "Min Out Degree");
        minOutDegreeField.setText(String.valueOf(graph.minOutDegree()));
        minOutDegreeField.setEditable(false);

        LabeledTextField minOutDegreeEdgesField = new LabeledTextField(
                "Min Out Degree Edges");
        minOutDegreeEdgesField
                .setText(String.valueOf(graph.minOutDegreeEdges()));
        minOutDegreeEdgesField.setEditable(false);

        LabeledTextField minOutDegreeHyperEdgesField = new LabeledTextField(
                "Min Out Degree Hyper Edges");
        minOutDegreeHyperEdgesField.setText(String.valueOf(graph
                .minOutDegreeHyperEdges()));
        minOutDegreeHyperEdgesField.setEditable(false);

        LabeledTextField uniformField = new LabeledTextField("Is Uniform");
        uniformField.setText(graph.isUniform().toString());
        uniformField.setEditable(false);

        LabeledTextField uniformKField = new LabeledTextField(
                "Uniform Constant");
        uniformKField.setText(String.valueOf(graph.uniformK()));
        uniformKField.setEditable(false);

        LabeledTextField regularField = new LabeledTextField("Is Regular");
        regularField.setText(graph.isRegular().toString());
        regularField.setEditable(false);

        LabeledTextField regularKField = new LabeledTextField(
                "Regular Constant");
        regularKField.setText(String.valueOf(graph.regularK()));
        regularKField.setEditable(false);


        this.content.add(sizeField, 0, 0);
        this.content.add(sizeEdgesField, 0, 1);
        this.content.add(sizeHyperEdgesField, 1, 2);
        this.content.add(orderField, 0, 3);
        this.content.add(rankField, 0, 4);

        this.content.add(maxDegreeField, 1, 0);
        this.content.add(maxInDegreeField, 1, 1);
        this.content.add(maxOutDegreeField, 1, 2);
        this.content.add(minDegreeField, 1, 3);
        this.content.add(minInDegreeField, 1, 4);
        this.content.add(minOutDegreeField, 1, 5);

        this.content.add(maxDegreeEdgesField, 2, 0);
        this.content.add(maxInDegreeEdgesField, 2, 1);
        this.content.add(maxOutDegreeEdgesField, 2, 2);
        this.content.add(minDegreeEdgesField, 2, 3);
        this.content.add(minInDegreeEdgesField, 2, 4);
        this.content.add(minOutDegreeEdgesField, 2, 5);

        this.content.add(maxDegreeHyperEdgesField, 3, 0);
        this.content.add(maxInDegreeHyperEdgesField, 3, 1);
        this.content.add(maxOutDegreeHyperEdgesField, 3, 2);
        this.content.add(minDegreeHyperEdgesField, 3, 3);
        this.content.add(minInDegreeHyperEdgesField, 3, 4);
        this.content.add(minOutDegreeHyperEdgesField, 3, 5);

        this.content.add(uniformField, 4, 0);
        this.content.add(uniformKField, 4, 1);
        this.content.add(regularField, 4, 2);
        this.content.add(regularKField, 4, 3);

        this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
    }



}