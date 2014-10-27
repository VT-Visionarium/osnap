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


package graphVisualizer.gui.dialogs;


import graphVisualizer.graph.metadata.Metadata;
import graphVisualizer.graph.metadata.MetadataType;
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
 * dialog for adding schema entries
 * 
 * @author Shawn P Neuman
 * 
 * 
 */
public class AddSchemaDialog
        extends Stage {

    private TextField              keyField;
    private ComboBox<MetadataType> typeChoice;
    private TextField              valueField;
    private TextField              requiredField;
    private TextField              uniqueField;
    private Text                   key;
    private Text                   type;
    private Text                   required;
    private Text                   unique;
    private Text                   value;
    private Text                   instructions;
    private Button                 addSchemaEntry;
    private Button                 cancel;
    private boolean                add  = false;
    private Stage                  warning;
    private Metadata               data = null;

    /**
     * @param defaultValue
     *            boolean variable to determine whether adding schema, or adding
     *            default value to schema entry. default value true if adding
     *            default value
     */
    public AddSchemaDialog(final boolean defaultValue) {

        this.setTitle("New Schema Entry");
        this.initModality(Modality.APPLICATION_MODAL);
        Scene addSchema = new Scene(new VBox());

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setVgap(10);
        grid.setHgap(10);


        key = new Text("Key");
        type = new Text("Type");
        required = new Text("Required? *");
        unique = new Text("Unique? *");
        instructions = new Text("Fields marked with * are optional");

        keyField = new TextField();
        typeChoice = new ComboBox<>();
        typeChoice.getItems().addAll(MetadataType.BOOLEAN, MetadataType.DOUBLE,
                MetadataType.FLOAT, MetadataType.INTEGER, MetadataType.LONG,
                MetadataType.STRING);
        value = new Text("Value");
        value.setVisible(false);

        valueField = new TextField();
        valueField.setVisible(false);
        requiredField = new TextField();
        uniqueField = new TextField();

        addSchemaEntry = new Button("Add Schema Entry");
        addSchemaEntry.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                if (!defaultValue) {
                    if (keyField.getText().equals("")
                            || keyField.getText().equals(null)
                            || typeChoice.getValue() == null) {
                        createWarningDialog("Please complete all necessary fields");
                    }
                    else {
                        add = true;
                        close();
                    }
                }

                else {
                    if (valueField.getText().equals("")) {
                        createWarningDialog("Please enter a value");
                    }
                    else if (!compareInputValue(typeChoice.getValue(),
                            valueField.getText())) {
                        createWarningDialog("Value type must match Type");
                    }
                    else {
                        data = createMetada();
                        add = true;
                        close();
                    }
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
        grid.add(keyField, 1, 0);
        grid.add(type, 0, 1);
        grid.add(typeChoice, 1, 1);
        grid.add(value, 0, 2);
        grid.add(valueField, 1, 2);
        grid.add(required, 0, 3);
        grid.add(requiredField, 1, 3);
        grid.add(unique, 0, 4);
        grid.add(uniqueField, 1, 4);
        grid.add(addSchemaEntry, 0, 5);
        grid.add(cancel, 1, 5);
        grid.add(instructions, 0, 6, 4, 1);


        ((VBox) addSchema.getRoot()).getChildren().add(grid);

        this.setScene(addSchema);

        if (defaultValue) {
            value.setVisible(true);
            valueField.setVisible(true);
            required.setVisible(false);
            requiredField.setVisible(false);
            uniqueField.setVisible(false);
            unique.setVisible(false);
            instructions.setVisible(false);
            addSchemaEntry.setText("Add default value");
        }

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
     * @return key value
     */
    public String getKeyField() {

        return keyField.getText();
    }


    /**
     * @return type choice
     */
    public MetadataType getType() {

        return typeChoice.getValue();
    }

    /**
     * creates meta data object to be added to schema
     * 
     * @return new meta data object
     */
    public Metadata createMetada() {

        if (getType() == MetadataType.BOOLEAN) {

            if (valueField.getText().toString().toLowerCase().equals("true")) {
                return Metadata.createMetadata(keyField.getText(), getType(),
                        true);
            }

            return Metadata
                    .createMetadata(keyField.getText(), getType(), false);


        }
        else if (getType() == MetadataType.DOUBLE) {

            return Metadata.createMetadata(keyField.getText(), getType(),
                    Double.parseDouble(this.valueField.getText()));
        }
        else if (getType() == MetadataType.FLOAT) {

            return Metadata.createMetadata(keyField.getText(), getType(),
                    Float.parseFloat(this.valueField.getText()));
        }
        else if (getType() == MetadataType.INTEGER) {

            return Metadata.createMetadata(keyField.getText(), getType(),
                    Integer.parseInt(this.valueField.getText()));
        }
        else if (getType() == MetadataType.LONG) {

            return Metadata.createMetadata(keyField.getText(), getType(),
                    Long.parseLong(this.valueField.getText()));
        }

        else {
            return Metadata.createMetadata(keyField.getText(), getType(),
                    getValueField());
        }


    }

    /**
     * @return valeu field
     */
    public String getValueField() {

        return valueField.getText();
    }

    /**
     * @return required field
     */
    public boolean getRequiredField() {

        if (requiredField.getText().toLowerCase().equals("true")) {
            return true;
        }
        return false;
    }

    /**
     * @return unique field true or false
     */
    public boolean getUniqueField() {

        if (uniqueField.getText().toLowerCase().equals("true")) {
            return true;
        }
        return false;
    }

    /**
     * @return meta data object chosen
     */
    public Metadata getData() {

        return data;
    }

    /**
     * @param str
     *            value of key
     */
    public void setKey(String str) {

        keyField.setText(str);
        keyField.setDisable(true);
    }

    /**
     * @param type
     *            type of meta data
     */
    public void setType(MetadataType type) {

        typeChoice.setValue(type);
        typeChoice.setDisable(true);

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
