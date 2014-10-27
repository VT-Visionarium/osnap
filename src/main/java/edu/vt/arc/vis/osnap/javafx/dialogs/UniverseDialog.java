/*******************************************************************************
 * Copyright 2014 Virginia Tech Visionarium
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/


package edu.vt.arc.vis.osnap.javafx.dialogs;


import org.controlsfx.control.ButtonBar;
import org.controlsfx.control.ButtonBar.ButtonType;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.DefaultDialogAction;
import org.controlsfx.dialog.Dialog;

import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Window;



/**
 * The {@link UniverseDialog} class provides a {@link Dialog} for creating or
 * editing {@link Universe Universes}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
public class UniverseDialog
        extends Dialog {

    private Universe  universe;
    private TextField universeIdTF;
    private Action    confirmAction;
    private GridPane  content;



    /**
     * Creates a new instance of the {@link UniverseDialog} class.
     * 
     * @param owner
     *            the owner of this dialog.
     * @param title
     *            the title of the dialog.
     * @param universe
     *            the {@link Universe}.
     */
    public UniverseDialog(Window owner, String title, Universe universe) {

        super(owner, title);

        this.universe = universe;

        final boolean projectProvided = universe != null;

        this.content = new GridPane();
        this.content.setHgap(10);
        this.content.setVgap(10);
        this.setContent(content);

        universeIdTF = new TextField();
        universeIdTF.setPromptText("Enter Universe ID (cannot be empty)");
        if (projectProvided) {

            universeIdTF.setText(universe.getId());
        }

        universeIdTF.setMaxHeight(Double.MAX_VALUE);
        universeIdTF.setMaxWidth(Double.MAX_VALUE);

        content.add(new Label("Universe ID"), 0, 0);
        content.add(universeIdTF, 1, 0);
        GridPane.setHgrow(universeIdTF, Priority.ALWAYS);


        confirmAction = new DefaultDialogAction("Ok") {

            {
                ButtonBar.setType(this, ButtonType.OK_DONE);
            }

            @Override
            public void handle(ActionEvent ae) {

                if (!isDisabled()) {

                    if (ae.getSource() instanceof Dialog) {

                        Dialog dlg = (Dialog) ae.getSource();

                        if (projectProvided) {
                            UniverseDialog.this.universe
                                    .setId(UniverseDialog.this.universeIdTF
                                            .getText());
                        }
                        else {
                            UniverseDialog.this.universe = new Universe(
                                    UniverseDialog.this.universeIdTF.getText());
                        }
                        dlg.setResult(UniverseDialog.this.confirmAction);
                    }
                }
            }
        };
        this.confirmAction.disabledProperty().set(!projectProvided);
        this.getActions().addAll(this.confirmAction, Dialog.Actions.CANCEL);
        // request focus on the universe ID field by default (so the user can
        // type immediately without having to click first)
        Platform.runLater(new Runnable() {

            @Override
            public void run() {

                universeIdTF.requestFocus();
            }
        });
        ChangeListener<String> changeListener = new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                UniverseDialog.this.validate();
            }
        };
        this.universeIdTF.textProperty().addListener(changeListener);
    }

    private void validate() {

        confirmAction.disabledProperty().set(
                universeIdTF.getText() == null
                        || universeIdTF.getText().trim().isEmpty());

    }


    /**
     * Creates and shows a dialog for creating a new node or modifying the ID of
     * a provided {@link Universe}.
     * 
     * @param owner
     *            the owner.
     * @param universe
     *            the {@link Universe} to modify (or {@code null} to create a
     *            new universe).
     * @return the newly created (or modified) {@link Universe}.
     */
    public static Universe showUniverseDialog(Window owner, Universe universe) {

        UniverseDialog dialog;

        if (universe == null) {

            dialog = new UniverseDialog(owner, "Create New Universe", universe);
        }
        else {

            dialog = new UniverseDialog(owner, "Edit Universe", universe);
        }

        Action result = dialog.show();

        if (result == dialog.confirmAction) {

            return dialog.universe;
        }
        return null;
    }
}
