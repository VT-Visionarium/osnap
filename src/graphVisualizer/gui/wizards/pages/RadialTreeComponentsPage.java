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


import java.util.Observable;
import java.util.Observer;

import org.controlsfx.dialog.Dialogs;

import graphVisualizer.gui.wizards.RadialTreeWizard;
import graphVisualizer.gui.wizards.statusobjects.RadialTreeStatus;
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
public class RadialTreeComponentsPage
        extends WizardPage
        implements Observer {

    private TextField minRadTF;

    /**
     * Creates a new RadialTreeComponentsPage object.
     */
    public RadialTreeComponentsPage() {

        super("Set Minimum Radius");
    }

    /*
     *
     */
    @Override
    Parent getContent() {

        GridPane grid = new GridPane();
        grid.setHgap(25);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 25, 25, 25));



        Text minRad = new Text("Minimum Radius");
        minRadTF = new TextField();

        grid.add(minRad, 0, 1);
        grid.add(minRadTF, 1, 1);
        this.getFinishButton().setDisable(true);

        return VBoxBuilder.create().spacing(5).children(grid).build();
    }


    @Override
    void nextPage() {

        if (minRadTF.getText() != null) {
            String str = minRadTF.getText();
            if (str.matches("[0-9]+")) {

                Integer min = Integer.parseInt(str);

                ((RadialTreeWizard) this.getWizard()).getStatusObject()
                        .setRadius(min);

                super.nextPage();
            }
            else {
                popUpWarning();
            }


        }
        else {
            popUpWarning();
        }

    }

    private void popUpWarning() {

        Dialogs.create().title("Insufficient information!")
                .message("Please enter an integer value for the radius!")
                .showError();

    }

    @Override
    public void update(Observable changedObject, Object changedProperty) {

        if (changedObject instanceof RadialTreeStatus) {

            RadialTreeStatus status = (RadialTreeStatus) changedObject;

            if (status.getRadius() != null) {
                this.minRadTF.setText(status.getRadius().toString());
            }
            else {
                this.minRadTF.setText("");
            }
        }

    }

}
