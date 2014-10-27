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


package edu.vt.arc.vis.osnap.gui.dialogs;


import java.util.Collection;

import edu.vt.arc.vis.osnap.graph.metadata.MetadataType;
import edu.vt.arc.vis.osnap.graph.metadata.Schema;
import edu.vt.arc.vis.osnap.graph.metadata.SchemaEntry;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * creates a dialog for adding meta-data
 * 
 * @author Shawn P Neuman
 * 
 */
public class AddMetadataDialog
        extends Stage {

    private ComboBox<MetadataType> typeChoice;
    private ComboBox<String>       keyChoice;
    private TextField              valueField;
    private Text                   key;
    private Text                   type;
    private Text                   value;
    private Text                   instructions;
    private Button                 addEntry;
    private Button                 cancel;
    private boolean                add = false;
    private Schema                 schema;
    private Stage                  warning;

    /**
     * @param theKey
     * @param theType
     * @param theSchema
     */
    public AddMetadataDialog(String theKey, MetadataType theType,
            Schema theSchema) {

        this.schema = theSchema;
        this.setTitle("New Metadata Entry");
        this.initModality(Modality.APPLICATION_MODAL);
        Scene addSchema = new Scene(new VBox());

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setVgap(10);
        grid.setHgap(10);


        key = new Text("Key");
        type = new Text("Type");
        value = new Text("Value");
        instructions = new Text("Value field must match Type choice");

        valueField = new TextField();
        keyChoice = new ComboBox<>();
        Collection<SchemaEntry> collection = schema.getEntries();
        for (SchemaEntry entry : collection) {
            keyChoice.getItems().add(entry.getKey());
        }

        typeChoice = new ComboBox<>();
        typeChoice.getItems().addAll(MetadataType.BOOLEAN, MetadataType.DOUBLE,
                MetadataType.FLOAT, MetadataType.INTEGER, MetadataType.LONG,
                MetadataType.STRING);
        typeChoice.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                switch (typeChoice.getValue()) {
                case BOOLEAN:
                    valueField.setPromptText("must be BOOLEAN");
                    break;
                case DOUBLE:
                    valueField.setPromptText("must be a DOUBLE");
                    break;
                case FLOAT:
                    valueField.setPromptText("must be a FLOAT");
                    break;
                case INTEGER:
                    valueField.setPromptText("must be an INTEGER");
                    break;
                case LONG:
                    valueField.setPromptText("must be a LONG");
                    break;
                case STRING:
                    valueField.setPromptText("must be a STRING");
                    break;
                default:
                    break;

                }

            }

        });

        if (theType != null) {
            typeChoice.setValue(theType);
            typeChoice.setDisable(true);
        }



        addEntry = new Button("Add Metadata");
        addEntry.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                if (keyChoice.getValue().equals(null)
                        || typeChoice.getValue() == null
                        || valueField.getText().equals("")) {
                    createWarningDialog("Complete all fields");
                }
                else if (!compareInputValue(typeChoice.getValue(),
                        valueField.getText())) {
                    createWarningDialog("Value and type must match");
                }
                else {
                    add = true;
                    close();
                }
            }

        });

        cancel = new Button("Cancel");
        cancel.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                add = false;
                close();

            }

        });

        grid.add(key, 0, 0);
        grid.add(keyChoice, 1, 0);
        grid.add(type, 0, 1);
        grid.add(typeChoice, 1, 1);
        grid.add(value, 0, 2);
        grid.add(valueField, 1, 2);
        grid.add(instructions, 0, 3);
        grid.add(addEntry, 0, 4);
        grid.add(cancel, 1, 4);


        ((VBox) addSchema.getRoot()).getChildren().add(grid);

        this.setScene(addSchema);

    }

    /**
     * another constructor for the add dialog
     */
    public AddMetadataDialog() {

        this(null, null, null);
    }

    /**
     * add variable true if add button pressed, false if exit or cancel
     * 
     * @return true or false
     */
    public boolean getAdd() {

        return add;
    }


    /**
     * @return the key value from value field
     */
    public String getKey() {

        return keyChoice.getValue();
    }

    /**
     * sets the type value
     * 
     * @param type
     *            the type value
     */
    public void setType(MetadataType type) {

        typeChoice.setValue(type);
        typeChoice.setEditable(false);
    }


    /**
     * gets the type of meta data from the type choice drop down
     * 
     * @return the value from type choice field
     */
    public MetadataType getType() {

        return typeChoice.getValue();
    }

    /**
     * gets the object type of the value field
     * 
     * @return the type of the value field, i.e. boolean, double, etc.
     */
    public Object getValue() {

        if (getType() == MetadataType.BOOLEAN) {

            if (valueField.getText().toLowerCase().equals("true")) {
                return true;
            }

            return false;


        }
        else if (getType() == MetadataType.DOUBLE) {

            return Double.parseDouble(this.valueField.getText());
        }
        else if (getType() == MetadataType.FLOAT) {

            return Float.parseFloat(this.valueField.getText());
        }
        else if (getType() == MetadataType.INTEGER) {

            return Integer.parseInt(this.valueField.getText());
        }
        else if (getType() == MetadataType.LONG) {

            return Long.parseLong(this.valueField.getText());
        }
        else {

            return this.valueField.getText();
        }

    }

    /**
     * creates a warning dialog for bad entries the warn string allows for
     * different messages to be passed in
     * 
     * @param warn
     *            the warning message
     */
    private void createWarningDialog(String warn) {

        warning = new Stage();
        warning.setTitle("Warning !!");
        warning.initModality(Modality.APPLICATION_MODAL);
        Scene warnScene = new Scene(new VBox());
        HBox box = new HBox();
        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER);
        grid2.setPadding(new Insets(25, 25, 25, 25));
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(0, 0, 25, 0));
        Text warnText = new Text(warn);
        warnText.setTextAlignment(TextAlignment.CENTER);
        warnText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        warnText.setFill(Color.BLACK);

        Button okButton = new Button("OK");
        okButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                warning.close();

            }

        });
        okButton.setTextAlignment(TextAlignment.CENTER);

        box.getChildren().add(okButton);

        grid2.add(warnText, 0, 0, 3, 1);

        ((VBox) warnScene.getRoot()).getChildren().addAll(grid2, box);

        warning.setScene(warnScene);

        warning.show();

    }

    /**
     * checks the input value and makes sure it is returned as the proper type
     * 
     * @param type
     *            Meta data type requested
     * @param input
     *            value of meta data type
     * @return true if values match type requested
     */
    private boolean compareInputValue(MetadataType type, String input) {

        switch (type) {
        case BOOLEAN:
            if ("true".equals(input) || "false".equals(input)) {
                return true;
            }
            return false;
        case DOUBLE:
            try {
                Double.parseDouble(input);

            }
            catch (NumberFormatException ex) {
                return false;
            }
            return true;

        case FLOAT:
            try {
                Float.parseFloat(input);

            }
            catch (NumberFormatException ex) {
                return false;
            }
            return true;

        case INTEGER:
            try {
                Integer.parseInt(input);

            }
            catch (NumberFormatException ex) {
                return false;
            }
            return true;

        case LONG:
            try {
                Long.parseLong(input);

            }
            catch (NumberFormatException ex) {
                return false;
            }
            return true;

        case STRING:

            return true;

        default:
            return false;

        }


    }



}
