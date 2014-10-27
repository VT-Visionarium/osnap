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


import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.controlsfx.dialog.Dialogs;

import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;
import edu.vt.arc.vis.osnap.javafx.wizards.IWizardWithStatus;
import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.ITreeStatus;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;


/**
 * page for selecting components for this layout type
 * 
 * @author Shawn P Neuman
 * 
 */
public class RootNodePage
        extends WizardPage
        implements Observer {

    private Text                   title;
    private ListView<IGraphObject> listView;
    private Button                 select;

    /**
     * 
     */
    public RootNodePage() {

        super("Select Root Node");



    }

    @Override
    Parent getContent() {

        GridPane grid = new GridPane();
        grid.setHgap(25);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 25, 25, 25));


        title = new Text("Root Node Selection");
        listView = new ListView<>();


        select = new Button("Select Root Node");
        select.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                if (listView.getSelectionModel().getSelectedItem() != null) {

                    ((ITreeStatus) ((IWizardWithStatus) RootNodePage.this
                            .getWizard()).getStatusObject())
                            .setRootNode(((INode) listView.getSelectionModel()
                                    .getSelectedItem()));

                    RootNodePage.this.getNextButton().setDisable(false);
                }
                else {
                    Dialogs.create().title("Select Root Node")
                            .message("You must select a Root Node")
                            .showInformation();
                }

            }

        });

        grid.add(title, 0, 0);
        grid.add(listView, 0, 1);
        grid.add(select, 0, 2);

        this.getNextButton().setDisable(true);
        this.getFinishButton().setDisable(true);

        return VBoxBuilder.create().spacing(5).children(grid).build();
    }

    /**
     * Populates the ListView with the provided list.
     * 
     * @param list
     *            the list.
     */
    public void popList(List<IGraphObject> list) {

        listView.setItems(FXCollections.observableArrayList(list));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable status, Object list) {

        if (status != null && status instanceof ITreeStatus
                && ((ITreeStatus) status).getGraphObjectList() != null) {
            popList(((ITreeStatus) status).getGraphObjectList());
        }

    }

}
