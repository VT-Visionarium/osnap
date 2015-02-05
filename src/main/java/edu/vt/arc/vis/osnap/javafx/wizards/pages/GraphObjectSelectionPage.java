package edu.vt.arc.vis.osnap.javafx.wizards.pages;


//@formatter:off
/*
* _
* The Open Semantic Network Analysis Platform (OSNAP)
* _
* Copyright (C) 2012 - 2015 Visionarium at Virginia Tech
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


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import edu.vt.arc.vis.osnap.core.domain.graph.Graph;
import edu.vt.arc.vis.osnap.core.domain.graph.Node;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.javafx.widgets.GraphObjectTreeView;
import edu.vt.arc.vis.osnap.javafx.widgets.SearchPanel;
import edu.vt.arc.vis.osnap.javafx.wizards.Wizard;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ILayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.ILayoutConfigurationView;


/**
 * The {@code GraphObjectSelectionPage} provides a
 * {@link LayoutConfigurationWizardPage} for selecting the {@link IGraphObject
 * GraphObjects} to which the {@link ILayout LayoutSet Component} is to be
 * applied.
 *
 * @param <O>
 *            the type of the {@link ILayout}.
 * @param <C>
 *            the type of the {@link ILayoutConfiguration}.
 * @param <T>
 *            the type of the {@link ILayoutConfigurationView}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class GraphObjectSelectionPage<O extends ILayout, C extends ILayoutConfiguration<O>, T extends GridPane & ILayoutConfigurationView<O, C>>
        extends LayoutConfigurationWizardPage<O, C, T> {

    private final GraphObjectTreeView treeView;
    private final Universe            universe;
    private final SearchPanel         searchPanel;
    private String                    filterString = "";
    private boolean                   nodeTree;
    private boolean                   edgeTree;

    /**
     * Creates a new instance of the {@code GraphObjectSelectionPage} class.
     *
     * @param configurationView
     *            the {@link ILayoutConfigurationView}.
     * @param universe
     *            the {@link Universe} for which the
     *            {@link ILayoutConfiguration Layout Configuration} is created.
     */
    public GraphObjectSelectionPage(final T configurationView,
            final Universe universe) {

        super("Select Graph Objects", configurationView);

        Objects.nonNull(universe);

        this.universe = universe;

        this.treeView = new GraphObjectTreeView();
        this.treeView.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE);
        GridPane.setHgrow(this.treeView, Priority.SOMETIMES);
        GridPane.setVgrow(this.treeView, Priority.SOMETIMES);
        this.searchPanel = new SearchPanel();
        this.searchPanel.setVisible(false);
        GridPane.setHgrow(this.searchPanel, Priority.SOMETIMES);
        GridPane.setVgrow(this.searchPanel, Priority.NEVER);

        this.getContentGridPane().add(this.treeView, 0, 0);

        this.setupEventHandlers();
    }


    private void setupEventHandlers() {

        this.searchPanel.getClose().setOnAction(
                actionEvent -> {

                    if (this.searchPanel.isVisible()) {

                        this.searchPanel.setTextField("");
                        this.searchPanel.setVisible(false);
                        this.getContentGridPane().getChildren()
                                .remove(this.searchPanel);
                    }
                });

        this.searchPanel.getFilterString()
                .addListener(
                        (observable, oldValue, newValue) -> {

                            this.filterString = newValue;
                            if ((this.universe != null)
                                    && (this.filterString != null)) {

                                if (this.nodeTree) {

                                    this.treeView
                                            .filterNodes(this.filterString);
                                }
                                else if (this.edgeTree) {

                                    this.treeView
                                            .filterEdges(this.filterString);
                                }
                            }
                        });


        this.setOnKeyPressed(keyEvent -> {

            final KeyCombination acceleratorKey = new KeyCodeCombination(
                    KeyCode.F, KeyCombination.SHORTCUT_DOWN);

            if (acceleratorKey.match(keyEvent)) {

                this.getContentGridPane().add(this.searchPanel, 0, 1);
                this.searchPanel.setVisible(true);
                this.searchPanel.requestFocus();
            }
        });
    }


    /**
     * populates the node treeView view
     *
     */
    public void populateTree() {

        this.treeView.populate(this.universe, this.filterString, this.nodeTree,
                this.edgeTree, false);
    }

    private void addUniverseItems(
            final Collection<IGraphObject> selectedGraphObjects,
            final Universe universe) {

        if (this.nodeTree) {

            selectedGraphObjects.addAll(universe.getNodes());
        }
        if (this.edgeTree) {

            selectedGraphObjects.addAll(universe.getEdges());
        }
    }

    private void addGraphItems(
            final Collection<IGraphObject> selectedGraphObjects,
            final Graph graph) {

        if (this.nodeTree) {

            selectedGraphObjects.addAll(graph.getNodes());
        }
        if (this.edgeTree) {

            selectedGraphObjects.addAll(graph.getEdges());
        }
    }

    private List<IGraphObject> getSelectedObjects() {

        final List<IGraphObject> selectedGraphObjects = new ArrayList<>();

        for (final TreeItem<Object> treeItem : this.treeView
                .getSelectionModel().getSelectedItems()) {

            final Object selected = treeItem.getValue();

            if (selected instanceof Universe) {

                this.addUniverseItems(selectedGraphObjects, (Universe) selected);

            }
            else if (selected instanceof Graph) {

                final Graph graph = (Graph) selected;
                this.addGraphItems(selectedGraphObjects, graph);
            }
            else if (selected instanceof IGraphObject) {

                selectedGraphObjects.add((IGraphObject) selected);
            }
            else if (selected instanceof String) {

                final String label = (String) selected;
                if ("Universe".equals(label)) {

                    this.addUniverseItems(selectedGraphObjects, this.universe);
                }
                else if (label.startsWith("Nodes")) {

                    selectedGraphObjects.addAll(this.universe.getNodes());
                }
                else if (label.startsWith("Edges")) {

                    selectedGraphObjects.addAll(this.universe.getEdges());
                }
                else {
                    boolean found = false;
                    for (final Graph graph : this.universe.getGraphs()) {

                        if (label.startsWith(graph.getId())) {

                            if (label.contains("Nodes")) {

                                selectedGraphObjects.addAll(graph.getNodes());
                            }
                            else if (label.contains("Edges")) {

                                selectedGraphObjects.addAll(graph.getEdges());
                            }
                            found = true;
                            break;
                        }
                    }

                    if (!found) {

                        for (final Node node : this.universe.getNodes()) {

                            if (label.startsWith(node.getId())) {

                                selectedGraphObjects.addAll(node.getEdges());
                                found = true;
                                break;
                            }
                        }
                    }

                    if (!found) {

                        final Alert alert = new Alert(AlertType.ERROR);

                        alert.setTitle("Selected string not recognized!");
                        alert.setContentText("String " + label
                                + " was not recognized!");

                        alert.showAndWait();
                    }
                }
            }
        }

        return selectedGraphObjects;
    }



    @Override
    public void onEnteringPage(final Wizard wizard) {

        super.onEnteringPage(wizard);


        final ILayoutConfiguration<?> config = this.getConfigurationView()
                .getConfiguration();

        if (config != null) {

            final Set<VisualProperty> visualProperties = config
                    .getEnabledVisualProperties();

            for (final VisualProperty visualProperty : visualProperties) {

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

    @Override
    public void onExitingPage(final Wizard wizard) {

        super.onExitingPage(wizard);


        this.getConfigurationView().getConfiguration()
                .setRestriction(this.getSelectedObjects());
    }
}
