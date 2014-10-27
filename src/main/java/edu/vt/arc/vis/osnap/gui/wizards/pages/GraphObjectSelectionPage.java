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


package edu.vt.arc.vis.osnap.gui.wizards.pages;


import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import org.controlsfx.dialog.Dialogs;

import edu.vt.arc.vis.osnap.graph.Graph;
import edu.vt.arc.vis.osnap.graph.Node;
import edu.vt.arc.vis.osnap.graph.Universe;
import edu.vt.arc.vis.osnap.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.gui.widgets.SearchPanel;
import edu.vt.arc.vis.osnap.gui.wizards.BaseLayoutWizard;
import edu.vt.arc.vis.osnap.gui.wizards.content.GraphObjectTreeView;
import edu.vt.arc.vis.osnap.gui.wizards.statusobjects.IStatus;
import edu.vt.arc.vis.osnap.visualization.VisualProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBoxBuilder;


/**
 * page for selecting components for this layout type
 * 
 * @author Shawn P Neuman
 */
public class GraphObjectSelectionPage
        extends WizardPage
        implements Observer {

    private GraphObjectTreeView      tree;
    private Universe                 uni;
    private SearchPanel              searchPanel;
    private String                   filterString = "";
    private boolean                  nodeTree;
    private boolean                  edgeTree;

    private final List<IGraphObject> selectedGraphObjects;

    /**
     * constructor
     */
    public GraphObjectSelectionPage() {

        super("Graph Object Info");
        this.setMinWidth(825.0);
        this.selectedGraphObjects = new LinkedList<>();

        this.getNextButton().setOnAction(new EventHandler<ActionEvent>() {

            private void addUniverseItems(
                    Collection<IGraphObject> selectedGraphObjects,
                    Universe universe) {

                if (nodeTree) {

                    selectedGraphObjects.addAll(universe.getNodes());
                }
                if (edgeTree) {

                    selectedGraphObjects.addAll(universe.getEdges());
                }
            }

            private void addGraphItems(
                    Collection<IGraphObject> selectedGraphObjects, Graph graph) {

                if (nodeTree) {

                    selectedGraphObjects.addAll(graph.getNodes());
                }
                if (edgeTree) {

                    selectedGraphObjects.addAll(graph.getEdges());
                }
            }

            @Override
            public void handle(ActionEvent actionEvent) {

                selectedGraphObjects.clear();
                for (TreeItem<Object> treeItem : tree.getSelectionModel()
                        .getSelectedItems()) {

                    Object selected = treeItem.getValue();

                    if (selected instanceof Universe) {

                        this.addUniverseItems(selectedGraphObjects,
                                (Universe) selected);

                    }
                    else if (selected instanceof Graph) {

                        Graph graph = (Graph) selected;
                        this.addGraphItems(selectedGraphObjects, graph);
                    }
                    else if (selected instanceof IGraphObject) {

                        selectedGraphObjects.add((IGraphObject) selected);
                    }
                    else if (selected instanceof String) {

                        String label = (String) selected;
                        if ("Universe".equals(label)) {

                            this.addUniverseItems(selectedGraphObjects, uni);
                        }
                        else if (label.startsWith("Nodes")) {

                            selectedGraphObjects.addAll(uni.getNodes());
                        }
                        else if (label.startsWith("Edges")) {

                            selectedGraphObjects.addAll(uni.getEdges());
                        }
                        else {
                            boolean found = false;
                            for (Graph graph : uni.getGraphs()) {

                                if (label.startsWith(graph.getID())) {

                                    if (label.contains("Nodes")) {

                                        selectedGraphObjects.addAll(graph
                                                .getNodes());
                                    }
                                    else if (label.contains("Edges")) {

                                        selectedGraphObjects.addAll(graph
                                                .getEdges());
                                    }
                                    found = true;
                                    break;
                                }
                            }

                            if (!found) {

                                for (Node node : uni.getNodes()) {

                                    if (label.startsWith(node.getID())) {

                                        selectedGraphObjects.addAll(node
                                                .getEdges());
                                        found = true;
                                        break;
                                    }
                                }
                            }

                            if (!found) {

                                Dialogs.create()
                                        .title("Selected string not recognized!")
                                        .message(
                                                "String "
                                                        + label
                                                        + " was not recognized!")
                                        .showError();
                            }
                        }
                    }
                }

                nextPage();
            }
        });


    }

    @Override
    Parent getContent() {

        Label label = new Label("Choose Nodes, Edges, or Hyper-Edges");
        tree = new GraphObjectTreeView();
        tree.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.getFinishButton().setDisable(true);
        searchPanel = new SearchPanel();
        searchPanel.setVisible(false);
        searchPanel.getClose().addEventHandler(ActionEvent.ACTION,
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent arg0) {

                        if (searchPanel.isVisible()) {

                            searchPanel.setTextField("");
                            searchPanel.setVisible(false);
                        }

                    }

                });


        searchPanel.getFilterString().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldVal, String newVal) {

                filterString = newVal;
                if (uni != null && filterString != null) {

                    if (nodeTree) {
                        tree.filterNodes(filterString);
                    }
                    else if (edgeTree) {
                        tree.filterEdges(filterString);
                    }
                }

            }

        });
        GraphObjectSelectionPage.this.addEventHandler(KeyEvent.KEY_PRESSED,
                new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent event) {

                        if (event.getCode().toString() == "F"
                                && event.isControlDown()) {
                            if (!searchPanel.isVisible()) {
                                searchPanel.setVisible(true);
                                searchPanel.setTextFocus();
                            }
                            else {
                                searchPanel.setVisible(false);

                            }

                        }
                    }

                });
        tree.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<Object>() {


                    @Override
                    public void changed(
                            ObservableValue<? extends Object> observable,
                            Object oldValue, Object newValue) {

                        GraphObjectSelectionPage.this.getNextButton()
                                .setDisable(newValue == null);
                    }

                });
        this.getNextButton().setDisable(true);

        return VBoxBuilder.create().spacing(5)
                .children(label, tree, searchPanel).build();
    }


    /**
     * populates the node tree view
     * 
     */
    public void populateTree() {

        this.uni = this.getWizard().getUniverse();
        this.tree.populate(this.uni, filterString, this.nodeTree,
                this.edgeTree, false);
    }



    @Override
    public void nextPage() {


        this.getWizard().getStatusObject()
                .setGraphObjectList(this.selectedGraphObjects);
        super.nextPage();
    }

    @Override
    public void priorPage() {

        tree.clearSelections();
        this.getNextButton().setDisable(true);

        this.getWizard().getStatusObject()
                .setVisualProperty((Set<VisualProperty>) null);
        super.priorPage();
    }

    @Override
    public BaseLayoutWizard getWizard() {

        if (super.getWizard() instanceof BaseLayoutWizard) {


            return (BaseLayoutWizard) super.getWizard();
        }
        return null;
    }

    @Override
    public void update(Observable observable, Object changedValue) {

        if (observable instanceof IStatus) {


            IStatus status = (IStatus) observable;

            if (status.getVisualProperty() != null
                    && changedValue instanceof Set) {

                Set<VisualProperty> visualProperties = status
                        .getVisualProperty();


                for (VisualProperty visualProperty : visualProperties) {

                    if (visualProperty.isNodeProperty()) {

                        this.nodeTree = true;
                    }
                    if (visualProperty.isEdgeProperty()) {

                        this.edgeTree = true;
                    }
                }
                this.populateTree();
            }
        }
    }
}
