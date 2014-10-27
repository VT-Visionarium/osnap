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
package graphVisualizer.gui.wizards.pages;


import graphVisualizer.gui.wizards.ColorLayoutWizard;
import graphVisualizer.gui.wizards.statusobjects.ColorStatus;
import graphVisualizer.layout.simpleComponents.SimpleColorLayoutComponent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;


/**
 * page for selecting components for this layout type
 * 
 * @author Shawn P Neuman
 * 
 */
public class ColorSelectionPage
        extends WizardPage {

    private GridPane    grid;
    private Button      add;
    private ColorPicker colorPicker;
    private Color       color;

    /**
     * 
     */
    public ColorSelectionPage() {

        super("Color");
    }

    @Override
    Parent getContent() {

        grid = new GridPane();
        grid.setHgap(25);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 25, 25, 25));
        add = new Button("Add Color");
        add.setVisible(true);

        this.color = Color.WHITE;
        add.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent click) {

                ColorStatus status = getWizard().getStatusObject();
                status.setColor(color);
                status.setLayoutComponent(new SimpleColorLayoutComponent(status
                        .getColor()));
                ColorSelectionPage.this.getNextButton().setDisable(false);

            }


        });

        colorPicker = new ColorPicker(this.color);
        colorPicker.getStyleClass().add("split_button");
        colorPicker.setOnAction(new EventHandler<ActionEvent>() {



            @Override
            public void handle(ActionEvent choice) {

                color = colorPicker.getValue();

            }
        });

        grid.add(colorPicker, 0, 0);
        grid.add(add, 0, 1);
        ColorSelectionPage.this.getNextButton().setDisable(true);
        ColorSelectionPage.this.getFinishButton().setDisable(true);

        return VBoxBuilder.create().spacing(5).children(grid).build();
    }

    @Override
    public ColorLayoutWizard getWizard() {

        if (super.getWizard() instanceof ColorLayoutWizard) {

            return (ColorLayoutWizard) super.getWizard();
        }
        return null;
    }
}
