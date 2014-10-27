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


import graphVisualizer.gui.wizards.LabelTextLayoutWizard;
import graphVisualizer.gui.wizards.statusobjects.LabelStatus;
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
public class LabelTextPage
        extends WizardPage {

    private GridPane  grid;
    private Text      label;
    private TextField labelTF;

    /**
     * 
     */
    public LabelTextPage() {

        super("Label");

        this.getFinishButton().setDisable(true);
        this.getNextButton().setDisable(false);
    }

    @Override
    Parent getContent() {

        grid = new GridPane();
        grid.setHgap(25);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 25, 25, 25));
        label = new Text("Provide label name");
        labelTF = new TextField();
        grid.add(label, 0, 0);
        grid.add(labelTF, 1, 0);


        return VBoxBuilder.create().spacing(5).children(grid).build();
    }

    @Override
    void nextPage() {

        if (null != labelTF.getText()) {
            LabelStatus status = ((LabelTextLayoutWizard) LabelTextPage.this
                    .getWizard()).getStatusObject();
            status.setLabelText(labelTF.getText());
            super.nextPage();
        }
    }

}
