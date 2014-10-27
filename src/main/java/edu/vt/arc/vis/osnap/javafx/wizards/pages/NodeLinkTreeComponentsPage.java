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


import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.NodeLinkTreeCoordinateLayoutComponent.Orientation;
import edu.vt.arc.vis.osnap.javafx.wizards.NodeLinkTreeWizard;
import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.NodeLinkTreeStatus;

import java.util.ArrayList;

import org.controlsfx.dialog.Dialogs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
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
public class NodeLinkTreeComponentsPage
        extends WizardPage {

    private Text                   depthSpacing;
    private TextField              depthSpacingTF;
    private Text                   siblingSpacing;
    private TextField              siblingSpacingTF;
    private Text                   subTreeSpacing;
    private TextField              subTreeSpacingTF;
    private Text                   orientation;
    private ArrayList<String>      orientations;
    private ObservableList<String> boxItems;
    private ComboBox<String>       orientationBox;



    /**
     * 
     */
    public NodeLinkTreeComponentsPage() {

        super("Set Node Link Components");

    }

    @Override
    Parent getContent() {

        GridPane grid = new GridPane();
        grid.setHgap(25);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 25, 25, 25));

        orientation = new Text("Orientation: ");

        orientations = new ArrayList<>();
        orientationBox = new ComboBox<>();
        boxItems = FXCollections.observableList(getOrientations());
        orientationBox.setItems(boxItems);
        orientationBox.getSelectionModel().select(2);


        depthSpacing = new Text("Depth Spacing: ");
        depthSpacingTF = new TextField("50.0");
        depthSpacingTF.setStyle("-fx-font-weight: bold");

        siblingSpacing = new Text("Space Between Siblings: ");
        siblingSpacingTF = new TextField("5.0");
        siblingSpacingTF.setStyle("-fx-font-weight: bold");

        subTreeSpacing = new Text("Space Between Neighboring Sub-Trees: ");
        subTreeSpacingTF = new TextField("25.0");
        subTreeSpacingTF.setStyle("-fx-font-weight: bold");


        grid.add(orientation, 0, 0);
        grid.add(orientationBox, 1, 0);
        grid.add(depthSpacing, 0, 1);
        grid.add(depthSpacingTF, 1, 1);
        grid.add(siblingSpacing, 0, 2);
        grid.add(siblingSpacingTF, 1, 2);
        grid.add(subTreeSpacing, 0, 3);
        grid.add(subTreeSpacingTF, 1, 3);



        this.getFinishButton().setDisable(true);

        return VBoxBuilder.create().spacing(5).children(grid).build();
    }


    private ArrayList<String> getOrientations() {

        orientations.clear();
        orientations.add("Left to Right");
        orientations.add("Right to Left");
        orientations.add("Top to Bottom");
        orientations.add("Bottom to Top");

        return orientations;
    }

    @Override
    void nextPage() {

        boolean settings = true;

        NodeLinkTreeStatus status = ((NodeLinkTreeWizard) this
                .getWizard()).getStatusObject();
        if (orientationBox.getSelectionModel().getSelectedItem() != null) {
            String str = orientationBox.getSelectionModel().getSelectedItem();
            switch (str) {
            case "Left to Right":
                status.setOrientation(Orientation.LEFT_TO_RIGHT);
                break;
            case "Right to Left":
                status.setOrientation(Orientation.RIGHT_TO_LEFT);
                break;
            case "Top to Bottom":
                status.setOrientation(Orientation.TOP_TO_BOTTOM);
                break;
            case "Bottom to Top":
                status.setOrientation(Orientation.BOTTOM_TO_TOP);
                break;
            }


        }
        else {
            settings = false;
        }

        Double depth = stringMatcher(depthSpacingTF.getText());
        if (depth != null) {
            status.setDepthSpacing(depth);
        }
        else {
            settings = false;
        }
        Double siblings = stringMatcher(siblingSpacingTF.getText());
        if (depth != null) {
            status.setSpaceBetweenSiblings(siblings);
        }
        else {
            settings = false;
        }
        Double subTree = stringMatcher(subTreeSpacingTF.getText());
        if (depth != null) {
            status.setSpaceBetweenNeighboringSubtrees(subTree);
        }
        else {
            settings = false;
        }


        if (settings) {
            super.nextPage();
        }
        else {
            popUpWarning();
        }

    }

    private void popUpWarning() {


        Dialogs.create()
                .title("Insufficient information!")
                .message("Please enter a Double value for these fields!")
                .showError();

    }


    private Double stringMatcher(String str) {

        Double returnValue = null;
        try {
            returnValue = Double.parseDouble(str);
        }
        catch (NumberFormatException ex) {
            return null;
        }
        return returnValue;
    }

}
