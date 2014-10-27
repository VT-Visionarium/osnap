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


package edu.vt.arc.vis.osnap.gui.wizards.pages;


import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBoxBuilder;

import org.jutility.javafx.control.LabeledComboBox;

import edu.vt.arc.vis.osnap.gui.wizards.ShapeLayoutWizard;
import edu.vt.arc.vis.osnap.gui.wizards.statusobjects.ShapeStatus;
import edu.vt.arc.vis.osnap.layout.simpleComponents.SimpleShapeLayoutComponent;
import edu.vt.arc.vis.osnap.visualization.Shape;


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

        grid = new GridPane();
        grid.setHgap(25);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 25, 25, 25));


        shapeBox = new LabeledComboBox<>("Choose a Shape");

        shapeBox.items().addAll(Shape.values());

        grid.add(shapeBox, 0, 0);
        this.getFinishButton().setDisable(true);

        return VBoxBuilder.create().spacing(5).children(grid).build();
    }



    @Override
    void nextPage() {

        if (null != shapeBox.getSelectedItem()) {
            Shape shape = shapeBox.getSelectedItem();

            ShapeStatus status = ((ShapeLayoutWizard) ShapeSelectionPage.this
                    .getWizard()).getStatusObject();
            status.setShape(shape);
            status.setLayoutComponent(new SimpleShapeLayoutComponent(status
                    .getShape()));
            super.nextPage();
        }
    }
}
