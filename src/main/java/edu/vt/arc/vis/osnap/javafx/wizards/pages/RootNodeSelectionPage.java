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
import java.util.List;

import javafx.scene.layout.GridPane;

import org.jutility.javafx.control.ListViewWithSearchPanel;

import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ITreeLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.Wizard;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ITreeLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.ILayoutConfigurationView;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.ITreeLayoutConfigurationView;


/**
 * The {@code RootNodeSelectionPage} provides a page for selecting the root
 * {@link INode} of a {@link ITreeLayout}.
 * 
 * @param <O>
 *            the type of the {@link ITreeLayout}.
 * @param <C>
 *            the type of the {@link ITreeLayoutConfiguration Configuration}.
 * @param <T>
 *            the type of the {@link ITreeLayoutConfigurationView
 *            ConfigurationView}.
 * 
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class RootNodeSelectionPage<O extends ITreeLayout, C extends ITreeLayoutConfiguration<O>, T extends GridPane & ITreeLayoutConfigurationView<O, C>>
        extends LayoutConfigurationWizardPage<O, C, T> {

    private ListViewWithSearchPanel<INode> availableNodes;


    /**
     * Creates a new instance of the {@code RootNodeSelectionPage} class.
     *
     * @param configurationView
     *            the {@link ILayoutConfigurationView}.
     */
    public RootNodeSelectionPage(final T configurationView) {

        super("Select Root Node", configurationView);

        this.availableNodes = new ListViewWithSearchPanel<>("Available Nodes");
        this.availableNodes.getLabel().setStyle("-fx-font-weight: bold");
        this.availableNodes.setVgap(10);


        this.getContentGridPane().add(this.availableNodes, 0, 0);
    }

    @Override
    public void onEnteringPage(Wizard wizard) {

        super.onEnteringPage(wizard);

        this.availableNodes.getItems().clear();
        if (this.getConfiguration() != null) {

            List<INode> nodes = new ArrayList<>();

            for (IGraphObject object : this.getConfiguration().getRestriction()) {

                if (object instanceof INode) {

                    nodes.add((INode) object);
                }
            }
            this.availableNodes.getItems().addAll(nodes);
        }
        if (!this.availableNodes.getItems().isEmpty()) {

            this.availableNodes.getSelectionModel().select(0);
        }
    }

    @Override
    public void onExitingPage(Wizard wizard) {

        super.onExitingPage(wizard);

        if (this.getConfiguration() != null) {

            this.getConfiguration().setRootNode(
                    this.availableNodes.getSelectedItem());
        }
    }
}
