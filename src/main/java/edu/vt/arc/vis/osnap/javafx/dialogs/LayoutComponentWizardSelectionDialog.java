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


/**
 * 
 */
package edu.vt.arc.vis.osnap.javafx.dialogs;


import org.controlsfx.control.ButtonBar;
import org.controlsfx.control.ButtonBar.ButtonType;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.DefaultDialogAction;
import org.controlsfx.dialog.Dialog;

import edu.vt.arc.vis.osnap.core.domain.graph.Edge;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.layout.LayoutComponentRegistry;
import edu.vt.arc.vis.osnap.core.domain.layout.common.I2DCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.I3DCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.mappings.Mapping;
import edu.vt.arc.vis.osnap.javafx.wizards.IWizardWithStatus;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Window;


/**
 * Dialog page for selecting predefined mapping layout parameters or launching
 * the new display selection wizard.
 * 
 * @author Peter J. Radics, Shawn P. Neuman
 * 
 */
public class LayoutComponentWizardSelectionDialog
        extends Dialog {


    private final Universe    universe;
    private Action            confirmAction;

    private ToggleGroup       group;
    private GridPane          content;

    private IWizardWithStatus wizard;



    /**
     * Returns the {@link IWizardWithStatus Wizard} chosen by this dialog.
     * 
     * @return the {@link IWizardWithStatus Wizard}.
     */
    public IWizardWithStatus getWizard() {

        return this.wizard;
    }

    /**
     * Creates a new instance of the
     * {@link LayoutComponentWizardSelectionDialog} class.
     * 
     * @param owner
     *            the owner of this dialog.
     * @param universe
     *            the {@link Universe}.
     */
    public LayoutComponentWizardSelectionDialog(Window owner, Universe universe) {

        super(owner, "Select a layout component to create");

        this.universe = universe;

        this.content = new GridPane();
        this.content.setHgap(10);
        this.content.setVgap(10);
        this.setContent(content);



        Text simple = new Text("Simple Layout Components");
        simple.setFont(Font.font("Verdana", FontWeight.BOLD, 14));

        Text prefuse = new Text("2D Coordinate Layout Components");
        prefuse.setFont(Font.font("Verdana", FontWeight.BOLD, 14));

        Text threeD = new Text("3D Coordinate Layout Components");
        threeD.setFont(Font.font("Verdana", FontWeight.BOLD, 14));

        Text special = new Text("Mapped Layout Components");
        special.setFont(Font.font("Verdana", FontWeight.BOLD, 14));



        this.group = new ToggleGroup();


        int simpleLayoutComponents = 1;
        int twoDLayoutComponents = 1;
        int threeDLayoutComponents = 1;
        int mappings = 1;

        this.content.add(simple, 0, 0);
        this.content.add(prefuse, 1, 0);
        this.content.add(threeD, 2, 0);
        this.content.add(special, 3, 0);


        for (Class<? extends ILayoutComponent> layoutComponentClass : LayoutComponentRegistry
                .Instance().getLayoutComponentClasses()) {

            String name = LayoutComponentRegistry.Instance()
                    .getNameOfLayoutComponentClass(layoutComponentClass);

            final RadioButton button = new RadioButton(name);
            button.setToggleGroup(this.group);

            Tooltip tip = new Tooltip();
            tip.setText(LayoutComponentRegistry.Instance()
                    .getDescriptionOfLayoutComponent(name));
            button.setTooltip(tip);


            if (Mapping.class.isAssignableFrom(layoutComponentClass)) {

                this.content.add(button, 3, mappings++);
            }
            else if (layoutComponentClass.getName().contains("Viewpoint")) {
                this.content.add(button, 3, mappings++);
            }
            else if (I3DCoordinateLayoutComponent.class
                    .isAssignableFrom(layoutComponentClass)) {

                this.content.add(button, 2, threeDLayoutComponents++);
            }
            else if (I2DCoordinateLayoutComponent.class
                    .isAssignableFrom(layoutComponentClass)) {

                this.content.add(button, 1, twoDLayoutComponents++);
            }
            else {

                this.content.add(button, 0, simpleLayoutComponents++);
            }
        }


        this.confirmAction = new DefaultDialogAction("Add LayoutComponent") {

            {
                ButtonBar.setType(this, ButtonType.OK_DONE);
            }

            @Override
            public void handle(ActionEvent ae) {

                if (!isDisabled()) {

                    if (ae.getSource() instanceof Dialog) {

                        Dialog dlg = (Dialog) ae.getSource();

                        Toggle toggle = LayoutComponentWizardSelectionDialog.this.group
                                .selectedToggleProperty().get();

                        if (toggle instanceof RadioButton) {

                            RadioButton radioButton = (RadioButton) toggle;

                            String layoutComponentClassName = radioButton
                                    .getText();

                            Class<? extends ILayoutComponent> layoutComponentClass = LayoutComponentRegistry
                                    .Instance().getClassOfLayoutComponent(
                                            layoutComponentClassName);

                            LayoutComponentWizardSelectionDialog.this.wizard = LayoutComponentRegistry
                                    .Instance()
                                    .createWizardForLayoutComponentClass(
                                            layoutComponentClass,
                                            LayoutComponentWizardSelectionDialog.this.universe);

                        }

                        dlg.setResult(LayoutComponentWizardSelectionDialog.this.confirmAction);
                    }
                }
            }
        };

        this.confirmAction.disabledProperty().set(true);
        this.getActions().addAll(this.confirmAction, Dialog.Actions.CANCEL);

        this.group.selectedToggleProperty().addListener(
                new ChangeListener<Toggle>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends Toggle> observable,
                            Toggle oldValue, Toggle newValue) {

                        LayoutComponentWizardSelectionDialog.this.validate();
                    }
                });

    }

    private void validate() {

        confirmAction.disabledProperty().set(group.getSelectedToggle() == null);
    }


    /**
     * Creates and shows a dialog for creating or modifying an edge.
     * 
     * @param owner
     *            the owner.
     * @param universe
     *            the {@link Universe}.
     * @return the newly created (or modified) {@link Edge}.
     */
    public static IWizardWithStatus showLayoutComponentWizardSelectionDialog(
            Window owner, Universe universe) {

        LayoutComponentWizardSelectionDialog dialog = new LayoutComponentWizardSelectionDialog(
                owner, universe);


        Action result = dialog.show();

        if (result == dialog.confirmAction) {

            return dialog.wizard;
        }
        return null;
    }

}
