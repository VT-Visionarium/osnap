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

import graphVisualizer.gui.wizards.ForceDirectedWizard;
import graphVisualizer.gui.wizards.statusobjects.ForceDirectedStatus;
import graphVisualizer.gui.wizards.statusobjects.FruchtermanReingoldStatus;
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
public class ForceDirectedComponentsPage
        extends WizardPage
        implements Observer {


    private Text      enforceBounds;
    private TextField enforceBoundsTF;
    private Text      maxIterations;
    private TextField maxIterationsTF;

    /**
     *
     */
    public ForceDirectedComponentsPage() {

        // flag is true for force directed, false for FruchtermanReingold
        super("Set Directed Components");
    }

    @Override
    Parent getContent() {

        GridPane grid = new GridPane();
        grid.setHgap(25);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 25, 25, 25));

        enforceBounds = new Text("Enforce Bounds? ");
        enforceBoundsTF = new TextField("False");
        enforceBoundsTF.setStyle("-fx-font-weight: bold");

        maxIterations = new Text("Maximum Iterations: ");
        maxIterationsTF = new TextField();
        maxIterationsTF.setStyle("-fx-font-weight: bold");

        grid.add(enforceBounds, 0, 1);
        grid.add(enforceBoundsTF, 1, 1);
        grid.add(maxIterations, 0, 2);
        grid.add(maxIterationsTF, 1, 2);

        this.getFinishButton().setDisable(true);

        return VBoxBuilder.create().spacing(5).children(grid).build();
    }

    @Override
    public void nextPage() {

        if (this.getWizard() instanceof ForceDirectedWizard) {
            if (((ForceDirectedWizard) this.getWizard()).getStatusObject() instanceof FruchtermanReingoldStatus) {


                FruchtermanReingoldStatus status = (FruchtermanReingoldStatus) ((ForceDirectedWizard) this
                        .getWizard()).getStatusObject();


                if (status instanceof ForceDirectedStatus) {
                    ForceDirectedStatus fdstatus = (ForceDirectedStatus) ((ForceDirectedWizard) this
                            .getWizard()).getStatusObject();

                    if (enforceBoundsTF.getText() != null) {

                        fdstatus.setEnforceBounds(Boolean
                                .parseBoolean(enforceBoundsTF.getText()));
                    }


                }
                Integer max = stringMatcher(maxIterationsTF.getText());
                if (max != null) {
                    status.setMaxIterations(max);
                }
            }
            super.nextPage();


        }
    }

    private Integer stringMatcher(String str) {

        Integer returnValue = null;
        try {
            returnValue = Integer.parseInt(str);
        }
        catch (NumberFormatException ex) {
            return null;
        }
        return returnValue;
    }

    @Override
    public void update(Observable changedObject, Object changedProperty) {

        if (changedObject instanceof FruchtermanReingoldStatus) {

            FruchtermanReingoldStatus status = (FruchtermanReingoldStatus) changedObject;

            if (changedObject instanceof ForceDirectedStatus) {

                ForceDirectedStatus fdstatus = (ForceDirectedStatus) changedObject;

                this.enforceBoundsTF.setText("" + fdstatus.enforcesBounds());
            }
            else {

                enforceBounds.setVisible(false);
                enforceBoundsTF.setVisible(false);
            }

            if (status.getMaxIterations() != null) {

                this.maxIterationsTF.setText(status.getMaxIterations()
                        .toString());
            }
            else {

                this.maxIterationsTF.setText("");
            }
        }
    }

}
