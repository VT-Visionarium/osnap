package edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes;


// @formatter:off
/*
 * _ The Open Semantic Network Analysis Platform (OSNAP) _ Copyright (C) 2012 -
 * 2014 Visionarium at Virginia Tech _ Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License. _
 */
// @formatter:on

import javafx.scene.control.Label;
import javafx.scene.text.Text;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.IPrefuseTreeLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.IPrefuseTreeLayoutConfiguration;


/**
 * The {@code PrefuseTreeLayoutConfigurationView} class provides a status pane
 * for {@link IPrefuseTreeLayoutConfiguration Prefuse
 * TreeLayoutComponentConfigurations}.
 *
 * @param <O>
 *            the type of the {@link IPrefuseTreeLayout}.
 * @param <C>
 *            the type of the {@link IPrefuseTreeLayoutConfiguration}.
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public abstract class PrefuseTreeLayoutConfigurationView<O extends IPrefuseTreeLayout, C extends IPrefuseTreeLayoutConfiguration<O>>
        extends PrefuseLayoutConfigurationView<O, C>
        implements IPrefuseTreeLayoutConfigurationView<O, C> {


    private final Label rootNodeLabel;
    private final Text  rootNodeValue;

    /**
     * Creates a new instance of the {@code PrefuseTreeLayoutConfigurationView}
     * class.
     * 
     * @param title
     *            the title for this {@link IConfigurationView}.
     */
    public PrefuseTreeLayoutConfigurationView(final String title) {

        this(title, null);
    }

    /**
     * Creates a new instance of the {@code PrefuseTreeLayoutConfigurationView}
     * class.
     * 
     * @param title
     *            the title for this {@link IConfigurationView}.
     * @param defaultConfiguration
     *            the default configuration.
     */
    public PrefuseTreeLayoutConfigurationView(final String title,
            final C defaultConfiguration) {

        this(title, defaultConfiguration, 0);
    }

    /**
     * Creates a new instance of the {@code PrefuseTreeLayoutConfigurationView}
     * class.
     * 
     * @param title
     *            the title for this {@link IConfigurationView}.
     * @param defaultConfiguration
     *            the default configuration.
     * @param offset
     *            the row offset of the values displayed by this view.
     */
    public PrefuseTreeLayoutConfigurationView(final String title,
            final C defaultConfiguration, final int offset) {

        super(title, defaultConfiguration, offset + 1);


        this.rootNodeLabel = new Label("Root Node:");
        this.rootNodeValue = new Text();
        this.rootNodeLabel.setStyle("-fx-font-weight: bold");
        this.rootNodeLabel.setLabelFor(this.rootNodeValue);


        this.add(this.rootNodeLabel, 0, super.rowsUsed());
        this.add(this.rootNodeValue, 1, super.rowsUsed());
    }

    @Override
    public void refreshView() {

        super.refreshView();

        this.rootNodeValue.setText("");
        if (this.getConfiguration() != null) {

            if (this.getConfiguration().getRootNode() != null) {

                this.rootNodeValue.setText(this.getConfiguration()
                        .getRootNode().toString());
            }
        }
    }
}
