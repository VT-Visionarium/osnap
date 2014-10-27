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

import edu.vt.arc.vis.osnap.javafx.wizards.IWizardWithStatus;
import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.IStatus;
import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.PrefuseStatusObject;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;


/**
 * @author Shawn P Neuman, Peter J. Radics
 *
 */
public class PrefuseComponentPage
        extends WizardPage
        implements Observer {

    private GridPane  grid;
    private Text      durationTXT;
    private Text      xAnchorTXT;
    private Text      yAnchorTXT;
    private Text      rectUpperLeftX;
    private Text      rectUpperLeftY;
    private Text      rectWidthTXT;
    private Text      rectHeightTXT;
    private Text      topMarginTXT;
    private Text      bottomMarginTXT;
    private Text      leftMarginTXT;
    private Text      rightMarginTXT;

    private TextField durationTF;
    private TextField xAnchorTF;
    private TextField yAnchorTF;
    private TextField rectUpperLeftXtf;
    private TextField rectUpperLeftYtf;
    private TextField rectWidthTF;
    private TextField rectHeightTF;
    private TextField topMarginTF;
    private TextField bottomMarginTF;
    private TextField leftMarginTF;
    private TextField rightMarginTF;

    /**
     *
     */
    public PrefuseComponentPage() {

        super("Prefuse Components");

        this.getFinishButton().setDisable(true);
        this.getNextButton().setDisable(false);
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.vt.arc.vis.osnap.javafx.wizards.WizardPage#getContent()
     */
    @Override
    Parent getContent() {

        grid = new GridPane();
        grid.setHgap(25);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 25, 25, 25));

        durationTXT = new Text("Layout Duration (long)");
        xAnchorTXT = new Text("X position for anchor node (double)");
        yAnchorTXT = new Text("Y position for anchor node (double)");
        rectUpperLeftX = new Text("Upper Left X Coord of boundary (double)");
        rectUpperLeftY = new Text("Upper Left Y Coord of boundary (double)");
        rectWidthTXT = new Text("Width of layout boundary (double)");
        rectHeightTXT = new Text("Height of layout boundary (double)");
        topMarginTXT = new Text("Top margin (int)");
        bottomMarginTXT = new Text("Bottom margin (int)");
        leftMarginTXT = new Text("Left margin (int)");
        rightMarginTXT = new Text("Right margin (int)");

        durationTF = new TextField("");
        xAnchorTF = new TextField("");
        yAnchorTF = new TextField("");
        rectUpperLeftXtf = new TextField("");
        rectUpperLeftYtf = new TextField("");
        rectWidthTF = new TextField("");
        rectHeightTF = new TextField("");
        topMarginTF = new TextField("");
        bottomMarginTF = new TextField("");
        leftMarginTF = new TextField("");
        rightMarginTF = new TextField("");

        grid.add(durationTXT, 0, 0);
        grid.add(xAnchorTXT, 0, 1);
        grid.add(yAnchorTXT, 0, 2);
        grid.add(rectUpperLeftX, 0, 3);
        grid.add(rectUpperLeftY, 0, 4);
        grid.add(rectWidthTXT, 0, 5);
        grid.add(rectHeightTXT, 0, 6);
        grid.add(topMarginTXT, 0, 7);
        grid.add(bottomMarginTXT, 0, 8);
        grid.add(leftMarginTXT, 0, 9);
        grid.add(rightMarginTXT, 0, 10);

        grid.add(durationTF, 1, 0);
        grid.add(xAnchorTF, 1, 1);
        grid.add(yAnchorTF, 1, 2);
        grid.add(rectUpperLeftXtf, 1, 3);
        grid.add(rectUpperLeftYtf, 1, 4);
        grid.add(rectWidthTF, 1, 5);
        grid.add(rectHeightTF, 1, 6);
        grid.add(topMarginTF, 1, 7);
        grid.add(bottomMarginTF, 1, 8);
        grid.add(leftMarginTF, 1, 9);
        grid.add(rightMarginTF, 1, 10);

        return VBoxBuilder.create().spacing(5).children(grid).build();


    }

    @Override
    void nextPage() {

        boolean nullCheck = checkFieldsForNullValues();
        if (!nullCheck) {

            setStatusObjectValues();
            super.nextPage();
        }
    }



    /**
     * @return true if any field is null
     */
    private boolean checkFieldsForNullValues() {

        if ("".equals(durationTF.getText())) {
            durationTF.requestFocus();
            return true;
        }
        if ("".equals(xAnchorTF.getText())) {
            xAnchorTF.requestFocus();
            return true;
        }
        if ("".equals(yAnchorTF.getText())) {
            yAnchorTF.requestFocus();
            return true;
        }
        if ("".equals(rectWidthTF.getText())) {
            rectWidthTF.requestFocus();
            return true;
        }
        if ("".equals(rectHeightTF.getText())) {
            rectHeightTF.requestFocus();
            return true;
        }
        if ("".equals(topMarginTF.getText())) {
            topMarginTF.requestFocus();
            return true;
        }
        if ("".equals(bottomMarginTF.getText())) {
            bottomMarginTF.requestFocus();
            return true;
        }
        if ("".equals(leftMarginTF.getText())) {
            leftMarginTF.requestFocus();
            return true;
        }
        if ("".equals(rightMarginTF.getText())) {
            rightMarginTF.requestFocus();
            return true;
        }

        return false;
    }

    /**
     *
     */
    private void setStatusObjectValues() {


        if (this.getWizard() instanceof IWizardWithStatus) {
            IStatus iStatus = ((IWizardWithStatus) this.getWizard())
                    .getStatusObject();

            if (iStatus instanceof PrefuseStatusObject) {

                PrefuseStatusObject status = (PrefuseStatusObject) iStatus;


                long duration = Long.parseLong(durationTF.getText());
                double xAnchor = Double.parseDouble(xAnchorTF.getText());
                double yAnchor = Double.parseDouble(yAnchorTF.getText());

                double rectX = Double.parseDouble(rectUpperLeftXtf.getText());
                double rectY = Double.parseDouble(rectUpperLeftYtf.getText());
                double rectWidth = Double.parseDouble(rectWidthTF.getText());
                double rectHeight = Double.parseDouble(rectHeightTF.getText());


                int topMargin = Integer.parseInt(topMarginTF.getText());
                int bottomMargin = Integer.parseInt(bottomMarginTF.getText());
                int leftMargin = Integer.parseInt(leftMarginTF.getText());
                int rightMargin = Integer.parseInt(rightMarginTF.getText());

                status.setDuration(duration);
                status.setLayoutAnchor(xAnchor, yAnchor);
                status.setLayoutBounds(rectX, rectY, rectWidth, rectHeight);
                status.setMargins(topMargin, bottomMargin, leftMargin,
                        rightMargin);
            }

        }



    }

    @Override
    public void update(Observable changedObject, Object changedValue) {

        if (changedObject != null
                && changedObject instanceof PrefuseStatusObject) {

            PrefuseStatusObject status = (PrefuseStatusObject) changedObject;

            if (status.getDuration() != null) {
                this.durationTF.setText(status.getDuration().toString());
            }
            else {
                this.durationTF.setText("");
            }

            if (status.getLayoutAnchor() != null) {
                this.xAnchorTF.setText(status.getLayoutAnchor().getX()
                        .toString());
                this.yAnchorTF.setText(status.getLayoutAnchor().getY()
                        .toString());
            }
            else {
                this.xAnchorTF.setText("");
                this.yAnchorTF.setText("");
            }

            if (status.getLayoutBounds() != null) {

                this.rectUpperLeftXtf.setText(status.getLayoutBounds()
                        .getTopLeftCorner().getX().toString());
                this.rectUpperLeftYtf.setText(status.getLayoutBounds()
                        .getTopLeftCorner().getY().toString());

                double width = status.getLayoutBounds().getTopRightCorner()
                        .getX()
                        - status.getLayoutBounds().getTopLeftCorner().getX();
                double height = status.getLayoutBounds().getBottomLeftCorner()
                        .getY()
                        - status.getLayoutBounds().getTopLeftCorner().getY();



                this.rectHeightTF.setText("" + height);
                this.rectWidthTF.setText("" + width);
            }
            else {
                this.rectUpperLeftXtf.setText("");
                this.rectUpperLeftYtf.setText("");
                rectWidthTF.setText("");
                rectHeightTF.setText("");
            }

            if (status.areMarginsSet()) {
                topMarginTF.setText(status.getTopMargin().toString());
                bottomMarginTF.setText(status.getBottomMargin().toString());
                leftMarginTF.setText(status.getLeftMargin().toString());
                rightMarginTF.setText(status.getRightMargin().toString());
            }
            else {
                topMarginTF.setText("");
                bottomMarginTF.setText("");
                leftMarginTF.setText("");
                rightMarginTF.setText("");
            }


        }

    }
}
