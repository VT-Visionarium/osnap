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


package edu.vt.arc.vis.osnap.javafx.widgets;

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


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * panel for searching through other panel. works off control F and filters
 * through list views to show only items that match search filter
 * 
 * @author Shawn P Neuman
 * 
 */
public class SearchPanel
        extends HBox {

    private StringProperty filterString;
    private Text           search;
    private TextField      searchBox;
    private Button         close;

    /**
     * constructor
     */
    public SearchPanel() {

        this.filterString = new SimpleStringProperty();
        this.setPadding(new Insets(5, 0, 0, 0));
        setSpacing(5);
        search = new Text("Search");
        search.minHeight(25);
        search.setFont(Font.font("Verdana", 14));
        searchBox = new TextField("");
        searchBox.setMinHeight(25);

        searchBox.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldVal, String newVal) {

                filterString.setValue(searchBox.getText());
            }

        });

        close = new Button("x");
        close.addEventHandler(ActionEvent.ACTION,
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent arg0) {

                        searchBox.setText(null);

                    }

                });
        searchBox.requestFocus();
        this.getChildren().addAll(close, search, searchBox);
    }



    /**
     * @return the user entered string
     */
    public StringProperty getFilterString() {

        return filterString;
    }


    /**
     * @return the close button
     */
    public Button getClose() {

        return close;
    }

    /**
     * set focus to the text field
     */
    public void setTextFocus() {

        searchBox.requestFocus();
    }
    
    /**
     * @param str sets the text field to this param
     */
    public void setTextField(String str) {
        searchBox.setText(str);
    }



}
