package edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes;


// @formatter:off
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
// @formatter:on

import javafx.scene.control.Label;
import javafx.scene.text.Text;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ITreeLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ITreeLayoutConfiguration;


/**
 * The abstract {@code TreeLayoutConfigurationView} class provides the base for
 * all status panes for {@link ITreeLayoutConfiguration
 * TreeLayoutConfigurations}.
 *
 * @param <O>
 *            the type of the {@link ITreeLayout TreeLayout}.
 * @param <C>
 *            the type of the {@link ITreeLayoutConfiguration
 *            TreeLayoutConfiguration}.
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public abstract class TreeLayoutConfigurationView<O extends ITreeLayout, C extends ITreeLayoutConfiguration<O>>
        extends CoordinateLayoutConfigurationView<O, C>
        implements ITreeLayoutConfigurationView<O, C> {


    private Label rootNodeLabel;
    private Text  rootNodeValue;


    /**
     * Creates a new instance of the {@code TreeLayoutConfigurationView} class.
     *
     * @param title
     *            the title for this {@link IConfigurationView}.
     */
    public TreeLayoutConfigurationView(final String title) {

        this(title, null);
    }

    /**
     * Creates a new instance of the {@code TreeLayoutConfigurationView} class.
     *
     * @param title
     *            the title for this {@link IConfigurationView}.
     * @param defaultConfiguration
     *            the default configuration.
     */
    public TreeLayoutConfigurationView(final String title,
            final C defaultConfiguration) {

        super(title, defaultConfiguration);

        this.rootNodeLabel = new Label("Root Node:");
        this.rootNodeLabel.setStyle("-fx-font-weight: bold");
        this.rootNodeValue = new Text();


        this.add(this.rootNodeLabel, 0, super.rowsUsed());
        this.add(this.rootNodeValue, 1, super.rowsUsed());
    }

    @Override
    protected int rowsUsed() {

        return super.rowsUsed() + 1;
    }

    @Override
    public void refreshView() {

        super.refreshView();

        this.rootNodeValue.setText("");
        if (this.getConfiguration() != null && this.getConfiguration().getRootNode() != null) {

            this.rootNodeValue.setText(this.getConfiguration().getRootNode()
                    .toString());
        }
    }
}
