package edu.vt.arc.vis.osnap.javafx.wizards.pages;


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
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import org.jutility.javafx.control.labeled.LabeledComboBox;

import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleShapeLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.visualization.Shape;
import edu.vt.arc.vis.osnap.javafx.wizards.ShapeLayoutWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.ShapeStatus;


/**
 * page for selecting components for this layout type
 *
 * @author Shawn P Neuman
 *
 */
public class ShapeSelectionPage
        extends WizardPage {


    private GridPane               grid;
    private LabeledComboBox<Shape> shapeBox;


    /**
     * Creates a new instance of the {@link ShapeSelectionPage} class.
     */
    public ShapeSelectionPage() {

        super("Shape");
    }

    @Override
    Parent getContent() {

        this.grid = new GridPane();
        this.grid.setHgap(25);
        this.grid.setVgap(10);
        this.grid.setPadding(new Insets(10, 25, 25, 25));


        this.shapeBox = new LabeledComboBox<>("Choose a Shape");

        this.shapeBox.getItems().addAll(Shape.values());

        this.grid.add(this.shapeBox, 0, 0);
        this.getFinishButton().setDisable(true);


        return new VBox(5, this.grid);
    }



    @Override
    void nextPage() {

        if (null != this.shapeBox.getSelectionModel().getSelectedItem()) {
            final Shape shape = this.shapeBox.getSelectionModel()
                    .getSelectedItem();

            final ShapeStatus status = ((ShapeLayoutWizard) ShapeSelectionPage.this
                    .getWizard()).getStatusObject();
            status.setShape(shape);
            status.setLayoutComponent(new SimpleShapeLayoutComponent(status
                    .getShape()));
            super.nextPage();
        }
    }
}
