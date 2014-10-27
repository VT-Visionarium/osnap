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


import edu.vt.arc.vis.osnap.javafx.wizards.IWizard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


/**
 * Wizard page abstract class
 * 
 * @author Shawn P Neuman
 * 
 */
public abstract class WizardPage
        extends VBox {

    private IWizard wizard;

    private Button  priorButton  = new Button("_Previous");
    private Button  nextButton   = new Button("N_ext");
    private Button  cancelButton = new Button("Cancel");
    private Button  finishButton = new Button("_Finish");


    /**
     * Returns the "previous" {@link Button}.
     * 
     * @return the "previous" {@link Button}.
     */
    protected Button getPriorButton() {

        return this.priorButton;
    }

    /**
     * Returns the "next" {@link Button}.
     * 
     * @return the "next" {@link Button}.
     */
    protected Button getNextButton() {

        return this.nextButton;
    }

    /**
     * Returns the "cancel" {@link Button}.
     * 
     * @return the "cancel" {@link Button}.
     */
    protected Button getCancelButton() {

        return this.cancelButton;
    }

    /**
     * Returns the "finish" {@link Button}.
     * 
     * @return the "finish" {@link Button}.
     */
    protected Button getFinishButton() {

        return this.finishButton;

    }


    /**
     * Creates a new instance of the {@link WizardPage} class with the provided
     * title.
     * 
     * @param title
     *            the title of the page
     */
    protected WizardPage(String title) {

        getChildren().add(
                LabelBuilder.create().text(title)
                        .style("-fx-font-weight: bold; -fx-padding: 0 0 5 0;")
                        .build());
        setId(title);
        setSpacing(5);
        setStyle("-fx-padding:10; -fx-background-color: honeydew; -fx-border-color: derive(honeydew, -30%); -fx-border-width: 3;");

        Region spring = new Region();
        VBox.setVgrow(spring, Priority.ALWAYS);
        getChildren().addAll(getContent(), spring, getButtons());

        priorButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {

                priorPage();
            }
        });
        nextButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {


                nextPage();
            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {

                getWizard().cancel();
            }
        });
        finishButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {

                finish();
            }
        });
    }

    /**
     * @return horizontal bar of buttons
     */
    HBox getButtons() {

        Region spring = new Region();
        HBox.setHgrow(spring, Priority.ALWAYS);
        HBox buttonBar = new HBox(5);
        cancelButton.setCancelButton(true);
        finishButton.setDefaultButton(true);
        buttonBar.getChildren().addAll(spring, priorButton, nextButton,
                cancelButton, finishButton);
        return buttonBar;
    }

    /**
     * @return the content
     */
    abstract Parent getContent();


    /**
     * @return true if next page exists
     */
    boolean hasNextPage() {

        return getWizard().hasNextPage();
    }

    /**
     * @return true if previous page exists
     */
    boolean hasPriorPage() {

        return getWizard().hasPriorPage();
    }

    /**
     * get the next page
     */
    void nextPage() {

        getWizard().nextPage();
    }

    /**
     * get the previous page
     */
    void priorPage() {

        getWizard().priorPage();
    }

    void finish() {

        getWizard().finish();
    }

    /**
     * go to a page with this string id
     * 
     * @param id
     *            sting value of page
     */
    void navTo(String id) {

        getWizard().navTo(id);
    }

    /**
     * go to page with this integer id
     * 
     * @param id
     *            integer index of page
     */
    void navTo(int id) {

        getWizard().navTo(id);
    }

    /**
     * Sets the {@link IWizard} this page belongs to.
     * 
     * @param wizard
     *            the {@link IWizard} this page belongs to.
     */
    public void setWizard(IWizard wizard) {

        this.wizard = wizard;
    }

    /**
     * Returns the {@link IWizard} this page belongs to.
     * 
     * @return the {@link IWizard} this page belongs to.
     */
    public IWizard getWizard() {

        return this.wizard;
    }

    /**
     * manages button visibility
     */
    public void manageButtons() {

        if (!hasPriorPage()) {
            priorButton.setDisable(true);
        }

        if (!hasNextPage()) {
            nextButton.setDisable(true);
        }
    }

    /**
     * @return the string value of a page
     */
    public String getByID() {

        return this.getId();
    }


}
