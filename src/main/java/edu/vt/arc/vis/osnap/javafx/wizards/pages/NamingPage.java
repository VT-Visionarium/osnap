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


import java.util.Observable;
import java.util.Observer;




//import edu.vt.arc.vis.osnap.javafx.events.MapNamingEvent;

import edu.vt.arc.vis.osnap.javafx.events.WizardCompleted;
import edu.vt.arc.vis.osnap.javafx.wizards.IWizardWithStatus;
import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.IStatus;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
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
public class NamingPage
        extends WizardPage
        implements Observer {

    private Text      name;
    private Text      description;
    private TextField nameTF;
    private TextArea  descriptionTF;
//    private IStatus   status;

    /**
     * constructor
     */
    public NamingPage() {

        super("Name your Mapping");
        this.setMinWidth(825.0);

        this.getFinishButton().setDisable(true);
        this.getNextButton().setDisable(true);
    }

    @Override
    Parent getContent() {

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        name = new Text("Provide a name for this layout");
        nameTF = new TextField();
        description = new Text("Provide a Description for this layout");
        descriptionTF = new TextArea();
        descriptionTF.setPrefRowCount(10);
        descriptionTF.setPrefColumnCount(20);
        descriptionTF.setWrapText(true);
        nameTF.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                enableFinish();

            }

        });

        descriptionTF.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                enableFinish();

            }



        });

        grid.add(name, 0, 0);
        grid.add(nameTF, 1, 0);
        grid.add(description, 0, 1);
        grid.add(descriptionTF, 1, 1, 1, 4);


        return VBoxBuilder.create().spacing(5).children(grid).build();

    }

    /**
     * get the previous page
     */
    @Override
    void priorPage() {

        nameTF.clear();
        super.priorPage();
    }

    @Override
    void finish() {


        IStatus status = ((IWizardWithStatus) this.getWizard())
                .getStatusObject();

        status.getLayoutComponent().setName(nameTF.getText());
        status.getLayoutComponent().setDescription(descriptionTF.getText());
        ((Observable) status).addObserver(this);
        WizardCompleted wizardCompleted = new WizardCompleted(
                WizardCompleted.WIZARD_COMPLETED, status);
        fireEvent(wizardCompleted);

        super.finish();


    }

    private void enableFinish() {

        if (!(nameTF.getText() == null || descriptionTF.getText() == null
                || "".equals(nameTF.getText()) || "".equals(descriptionTF
                .getText()))) {

            this.getFinishButton().setDisable(false);

        }
        else {

            this.getFinishButton().setDisable(true);
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable status, Object changedValue) {


        if (status != null && status instanceof IStatus
                && ((IStatus) status).getLayoutComponent() != null) {
            nameTF.setText(((IStatus) status).getLayoutComponent().getName());
            descriptionTF.setText(((IStatus) status).getLayoutComponent()
                    .getDescription());
        }

    }

}
