package edu.vt.arc.vis.osnap.javafx.dialogs;


//@formatter:off
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
//@formatter:on


import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Window;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.layout.LayoutComponentRegistry;
import edu.vt.arc.vis.osnap.core.domain.layout.common.I2DCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.I3DCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.mappings.Mapping;
import edu.vt.arc.vis.osnap.javafx.wizards.IWizardWithStatus;


/**
 * Dialog page for selecting predefined mapping layout parameters or launching
 * the new display selection wizard.
 * 
 * @author Peter J. Radics, Shawn P. Neuman
 * 
 */
public class LayoutComponentWizardSelectionDialog
        extends Dialog<IWizardWithStatus> {

    private final Universe    universe;

    private final ToggleGroup group;
    private final GridPane    content;



    /**
     * Creates a new instance of the
     * {@link LayoutComponentWizardSelectionDialog} class.
     * 
     * @param owner
     *            the owner of this dialog.
     * @param universe
     *            the {@link Universe}.
     */
    public LayoutComponentWizardSelectionDialog(Node owner, Universe universe) {

        this(owner == null ? null : owner.getScene().getWindow(), universe);
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

        super();

        this.initOwner(owner);
        this.setTitle("Select a layout component to create");


        this.universe = universe;

        this.content = new GridPane();
        this.content.setHgap(10);
        this.content.setVgap(10);
        this.getDialogPane().setContent(this.content);



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

        this.getDialogPane().getButtonTypes()
                .addAll(ButtonType.NEXT, ButtonType.CANCEL);

        this.getDialogPane().lookupButton(ButtonType.NEXT).disableProperty()
                .bind(this.group.selectedToggleProperty().isNull());


        this.setResultConverter(buttonType -> {

            if (buttonType == ButtonType.NEXT) {
                Toggle toggle = this.group.selectedToggleProperty().get();

                if (toggle instanceof RadioButton) {

                    RadioButton radioButton = (RadioButton) toggle;

                    String layoutComponentClassName = radioButton.getText();

                    Class<? extends ILayoutComponent> layoutComponentClass = LayoutComponentRegistry
                            .Instance().getClassOfLayoutComponent(
                                    layoutComponentClassName);

                    return LayoutComponentRegistry.Instance()
                            .createWizardForLayoutComponentClass(
                                    layoutComponentClass, this.universe);
                }
            }

            return null;
        });
    }
}
