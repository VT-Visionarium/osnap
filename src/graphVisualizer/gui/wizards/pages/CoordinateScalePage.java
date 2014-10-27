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


import org.controlsfx.dialog.Dialogs;

import graphVisualizer.gui.wizards.SphereLayoutWizard;
import graphVisualizer.gui.wizards.TwoDGridWizard;
import graphVisualizer.gui.wizards.statusobjects.GridStatusObject;
import graphVisualizer.gui.wizards.statusobjects.SphereLayoutStatus;
import graphVisualizer.layout.complexComponents.SphereCoordinateLayoutComponent;
import graphVisualizer.layout.complexComponents.TwoDGridCoordinateLayoutComponent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;


/**
 * @author Shawn P Neuman
 * 
 */
public class CoordinateScalePage
        extends WizardPage {


    private Text      setX;
    private Text      setY;
    private Text      setZ;
    private TextField setXTF;
    private TextField setYTF;
    private TextField setZTF;

    /**
     * calls parent class to set up page with given title
     */
    public CoordinateScalePage() {

        super("Scale coordinates");
    }

    /*
     * (non-Javadoc)
     * 
     * @see graphVisualizer.gui.wizards.WizardPage#getContent()
     */
    @Override
    public Parent getContent() {

        GridPane grid = new GridPane();
        grid.setHgap(25);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 25, 25, 25));

        setX = new Text("Scale layout on X: ");
        setY = new Text("Scale layout on Y: ");
        setZ = new Text("Scale layout on Z: ");

        setXTF = new TextField("1");
        setXTF.setStyle("-fx-font-weight: bold");
        setYTF = new TextField("1");
        setYTF.setStyle("-fx-font-weight: bold");
        setZTF = new TextField("1");
        setZTF.setStyle("-fx-font-weight: bold");

        grid.add(setX, 0, 0);
        grid.add(setXTF, 1, 0);
        grid.add(setY, 0, 1);
        grid.add(setYTF, 1, 1);
        grid.add(setZ, 0, 2);
        grid.add(setZTF, 1, 2);

        this.getFinishButton().setDisable(true);

        return VBoxBuilder.create().spacing(5).children(grid).build();
    }

    @Override
    protected void nextPage() {

        float x = stringMatcher(setXTF.getText());
        float y = stringMatcher(setYTF.getText());
        float z = stringMatcher(setZTF.getText());


        if (x == 0f || y == 0f || z == 0f) {

            Dialogs.create().title("Invalid numbers in scale text fields")
                    .message("please enter a number other than 0").showError();
        }
        else {
            if(this.getWizard() instanceof SphereLayoutWizard) {
                
                SphereLayoutStatus status = ((SphereLayoutWizard) this
                        .getWizard()).getStatusObject();
                status.setxScale(x);
                status.setyScale(y);
                status.setzScale(z);
                ((SphereCoordinateLayoutComponent) status.getLayoutComponent())
                        .setxScale(x);
                ((SphereCoordinateLayoutComponent) status.getLayoutComponent())
                        .setyScale(y);
                ((SphereCoordinateLayoutComponent) status.getLayoutComponent())
                        .setzScale(z);
            }
            else if (this.getWizard() instanceof TwoDGridWizard) {
               GridStatusObject status = ((TwoDGridWizard) this
                        .getWizard()).getStatusObject();
                status.setxScale(x);
                status.setyScale(y);

                ((TwoDGridCoordinateLayoutComponent) status.getLayoutComponent())
                        .setxDistanceScale(x);
                ((TwoDGridCoordinateLayoutComponent) status.getLayoutComponent())
                        .setyDistanceScale(y);

            }
            super.nextPage();
        }
    }


    private float stringMatcher(String str) {

        float returnValue;
        try {
            returnValue = Float.parseFloat(str);
        }
        catch (NumberFormatException ex) {
            returnValue = 0f;
        }
        return returnValue;
    }

}
