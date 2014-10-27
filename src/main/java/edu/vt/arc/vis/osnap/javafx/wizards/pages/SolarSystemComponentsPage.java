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


package edu.vt.arc.vis.osnap.javafx.wizards.pages;

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


import java.util.Observable;
import java.util.Observer;

import edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.SolarSystemCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.javafx.wizards.SolarSystemWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.SolarSystemStatus;
//import edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.SolarSystemCoordinateLayoutComponent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
public class SolarSystemComponentsPage
        extends WizardPage
        implements Observer {

    private GridPane  grid;
    private Text      invertPathToRootNode;
    private Text      ignoreEdgeDirection;
    private Text      minDist;
    private Text      maxNodeSize;
    private Text      depthMod;

    private TextField ignoreEdgeDirectionTF;
    private TextField inversePathTF;
    private TextField minDistTF;
    private TextField maxNodeSizeTF;
    private TextField depthModTF;


    /**
     * Creates a new instance of the {@link SolarSystemComponentsPage} class.
     */
    public SolarSystemComponentsPage() {

        super("Solar System Components");

    }

    @Override
    Parent getContent() {

        grid = new GridPane();
        grid.setHgap(25);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 25, 25, 25));

        ignoreEdgeDirection = new Text("Ignore Edge IterationOrder?");
        ignoreEdgeDirectionTF = new TextField();

        invertPathToRootNode = new Text("Invert Path to Root Node?");
        inversePathTF = new TextField();

        minDist = new Text("Minimum Distance between Nodes");
        minDistTF = new TextField();

        maxNodeSize = new Text("Maximum Node Size");
        maxNodeSizeTF = new TextField();

        depthMod = new Text("Depth Modifier (Distance between Tree Levels)");
        depthModTF = new TextField();

        grid.add(ignoreEdgeDirection, 0, 0);
        grid.add(ignoreEdgeDirectionTF, 1, 0);

        grid.add(invertPathToRootNode, 0, 1);
        grid.add(inversePathTF, 1, 1);

        grid.add(minDist, 0, 2);
        grid.add(minDistTF, 1, 2);

        grid.add(maxNodeSize, 0, 3);
        grid.add(maxNodeSizeTF, 1, 3);

        grid.add(depthMod, 0, 4);
        grid.add(depthModTF, 1, 4);


        this.getFinishButton().setDisable(true);

        this.setUpEventHandlers();

        return VBoxBuilder.create().spacing(5).children(grid).build();
    }


    private void setUpEventHandlers() {


        this.inversePathTF.textProperty().addListener(
                new ChangeListener<String>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends String> observable,
                            String oldValue, String newValue) {

                        SolarSystemStatus status = SolarSystemComponentsPage.this
                                .getWizard().getStatusObject();

                        if (newValue != null && status != null) {

                            boolean bool = Boolean.parseBoolean(newValue);
                            status.setInvertPathToRootNode(bool);
                        }
                    }
                });


        this.ignoreEdgeDirectionTF.textProperty().addListener(
                new ChangeListener<String>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends String> observable,
                            String oldValue, String newValue) {

                        SolarSystemStatus status = SolarSystemComponentsPage.this
                                .getWizard().getStatusObject();

                        if (newValue != null && status != null) {

                            boolean bool = Boolean.parseBoolean(newValue);
                            status.setIgnoreEdgeDirection(bool);

                        }

                    }
                });
        
        this.minDistTF.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                SolarSystemStatus status = SolarSystemComponentsPage.this
                        .getWizard().getStatusObject();

                if (newValue != null && status != null) {

                    try {

                        Float min = Float.parseFloat(newValue);
                        status.setMinimalDistance(min);
                    }
                    catch (NumberFormatException ex) {
                        // Do nothing
                    }

                }

            }
        });


        this.maxNodeSizeTF.textProperty().addListener(
                new ChangeListener<String>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends String> observable,
                            String oldValue, String newValue) {

                        SolarSystemStatus status = SolarSystemComponentsPage.this
                                .getWizard().getStatusObject();

                        if (newValue != null && status != null) {

                            try {

                                Float max = Float.parseFloat(newValue);
                                status.setMaximumNodeSize(max);
                            }
                            catch (NumberFormatException ex) {
                                // Do nothing
                            }

                        }

                    }
                });
        
        
        this.depthModTF.textProperty().addListener(
                new ChangeListener<String>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends String> observable,
                            String oldValue, String newValue) {

                        SolarSystemStatus status = SolarSystemComponentsPage.this
                                .getWizard().getStatusObject();

                        if (newValue != null && status != null) {

                            try {

                                Float depth = Float.parseFloat(newValue);
                                status.setDepthModifier(depth);
                            }
                            catch (NumberFormatException ex) {
                                // Do nothing
                            }
                        }
                    }
                });

    }

    @Override
    void nextPage() {


        super.nextPage();

    }

    @Override
    public SolarSystemWizard getWizard() {

        if (super.getWizard() instanceof SolarSystemWizard) {
            return (SolarSystemWizard) super.getWizard();
        }
        return null;
    }

    @Override
    public void update(Observable observable, Object changedValue) {

        if (observable instanceof SolarSystemStatus) {

            SolarSystemStatus status = (SolarSystemStatus) observable;

            if (status.getLayoutComponent() != null) {
                SolarSystemCoordinateLayoutComponent component = status
                        .getLayoutComponent();

                this.inversePathTF.setText(""
                        + component.isInvertPathToRootNode());

                this.ignoreEdgeDirectionTF.setText(""
                        + component.isIgnoreEdgeDirection());

                this.minDistTF.setText("" + component.getMinimalDistance());

                this.maxNodeSizeTF.setText("" + component.getMaximumNodeSize());

                this.depthModTF.setText("" + component.getDepthModifier());
            }

        }

    }

}
