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


/**
 * 
 */
package graphVisualizer.gui.widgets;


import graphVisualizer.visualization.VisualObject;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


/**
 * @author Shawn P Neuman
 * 
 */
public class VisualObjectData
        extends VBox {


    private Text      title;
    private GridPane  grid;
    private Text      id;
    private Text      visible;
    private Text      label;
    private Text      shape;
    private Text      scale;
    private Text      position;
    private Text      rotation;
    private Text      color;
    private Text      colorString;

    private Text      idValue;
    private Text      visibleValue;
    private Text      labelValue;
    private Text      shapeValue;
    private Text      scaleValue;
    private Text      positionValue;
    private Text      rotationValue;
    private TextField colorValue;
    private Text      colorStringValue;

    /**
     * @param title
     */
    public VisualObjectData(String title) {

        this.title = new Text(title);
        this.title.setFont(Font.font("verdana", 16));
        this.title.setTextAlignment(TextAlignment.CENTER);

        grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));



        id = new Text("ID: ");
        visible = new Text("Visible: ");
        label = new Text("Label: ");
        shape = new Text("Shape: ");
        scale = new Text("Scale: ");
        position = new Text("Position: ");
        rotation = new Text("Rotation: ");
        color = new Text("Color: ");
        colorString = new Text("Color String: ");

        idValue = new Text();
        idValue.setWrappingWidth(300);
        visibleValue = new Text();
        labelValue = new Text();
        labelValue.setWrappingWidth(300);
        shapeValue = new Text();
        scaleValue = new Text();
        positionValue = new Text();
        rotationValue = new Text();
        colorValue = new TextField();
        colorValue.setOpacity(0);
        colorStringValue = new Text();

        grid.add(id, 0, 0);
        grid.add(visible, 0, 1);
        grid.add(label, 0, 2);
        grid.add(shape, 0, 3);
        grid.add(scale, 0, 4);
        grid.add(position, 0, 5);
        grid.add(rotation, 0, 6);
        grid.add(color, 0, 7);
        grid.add(colorString, 0, 8);

        grid.add(idValue, 1, 0);
        grid.add(visibleValue, 1, 1);
        grid.add(labelValue, 1, 2);
        grid.add(shapeValue, 1, 3);
        grid.add(scaleValue, 1, 4);
        grid.add(positionValue, 1, 5);
        grid.add(rotationValue, 1, 6);
        grid.add(colorValue, 1, 7);
        grid.add(colorStringValue, 1, 8);

        this.getChildren().add(this.title);
        this.getChildren().add(grid);

    }

    /**
     * sets the field values for the visual object selected
     * 
     * @param vo
     *            the visual object selected
     */
    public void populateFields(VisualObject vo) {


        if (vo != null) {
            idValue.setText(vo.getID());
            visibleValue.setText("" + vo.isVisible());
            labelValue.setText("" + vo.getLabel());
            shapeValue.setText("" + vo.getShape());
            scaleValue.setText("" + vo.getScale());
            positionValue.setText("" + vo.getPosition());
            rotationValue.setText("" + vo.getRotation());
            colorValue.setOpacity(1);

            String color = vo.getColor().toString();
            color = color.substring(2, color.length() - 2);

            colorValue
                    .setStyle("-fx-border-color: #000000; -fx-background-color: "
                            + "#" + color);
            colorStringValue.setText(vo.getColor().toString());
        }
        else {
            clear();
        }

    }



    /**
     * clears the list view
     */
    public void clear() {

        idValue.setText("");
        visibleValue.setText("");
        labelValue.setText("");
        shapeValue.setText("");
        scaleValue.setText("");
        positionValue.setText("");
        rotationValue.setText("");
        colorValue.setText("");

    }


}
