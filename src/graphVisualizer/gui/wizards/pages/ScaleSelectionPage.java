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


package graphVisualizer.gui.wizards.pages;


import org.jutility.math.geometry.Scalef;

import graphVisualizer.gui.wizards.ScaleLayoutWizard;
import graphVisualizer.gui.wizards.statusobjects.ScaleStatus;
import graphVisualizer.layout.simpleComponents.SimpleScaleLayoutComponent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;


/**
 * page for selecting components for this layout type
 * 
 * @author Shawn P Neuman
 * 
 */
public class ScaleSelectionPage
        extends WizardPage {


    private GridPane  grid;
    private TextField scaleTF;


    /**
     * 
     */
    public ScaleSelectionPage() {

        super("Scale");
        // this.setMinWidth(425.0);
    }

    @Override
    Parent getContent() {

        grid = new GridPane();
        grid.setHgap(25);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 25, 25, 25));

        Text text = new Text("Enter Scale Value");
        scaleTF = new TextField();

        grid.add(text, 0, 0);
        grid.add(scaleTF, 1, 0);
        this.getFinishButton().setDisable(true);

        return VBoxBuilder.create().spacing(5).children(grid).build();
    }

    @Override
    void nextPage() {

        if (scaleTF.getText().matches("[0-9]*[.]?[0-9]+")) {
            float scale = Float.parseFloat(scaleTF.getText());
            ScaleStatus status = ((ScaleLayoutWizard) ScaleSelectionPage.this
                    .getWizard()).getStatusObject();
            status.setScale(scale);
            Scalef scalef = new Scalef(scale, scale, scale);
            status.setLayoutComponent(new SimpleScaleLayoutComponent(scalef));
            super.nextPage();
        }
        else {
            scaleTF.setText("");
        }
    }

}
