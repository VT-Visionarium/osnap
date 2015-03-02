package edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes;


// @formatter:off
/*
 * _ The Open Semantic Network Analysis Platform (OSNAP) _ Copyright (C) 2012 -
 * 2015 Visionarium at Virginia Tech _ Licensed under the Apache License,
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
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseFruchtermanReingoldLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.PrefuseFruchtermanReingoldLayoutConfiguration;


/**
 * The {@code PrefuseFruchtermanReingoldLayoutConfigurationView} class provides
 * a status panes for {@link PrefuseFruchtermanReingoldLayoutConfiguration
 * PrefuseFruchtermanReingoldLayoutConfigurations}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class PrefuseFruchtermanReingoldLayoutConfigurationView
        extends
        PrefuseLayoutConfigurationView<PrefuseFruchtermanReingoldLayout, PrefuseFruchtermanReingoldLayoutConfiguration> {

    private final Label maximumIterationsLabel;
    private final Text  maximumIterationsValue;


    /**
     * Creates a new instance of the
     * {@code PrefuseForceDirectedLayoutConfigurationView} class.
     *
     * @param title
     *            the title for this {@link IConfigurationView}.
     */
    public PrefuseFruchtermanReingoldLayoutConfigurationView(final String title) {

        super(title, new PrefuseFruchtermanReingoldLayoutConfiguration(), 1);

        this.maximumIterationsValue = new Text();
        this.maximumIterationsLabel = new Label("Maximum Iterations:");
        this.maximumIterationsLabel.setStyle("-fx-font-weight: bold");
        this.maximumIterationsLabel.setLabelFor(this.maximumIterationsValue);

        this.add(this.maximumIterationsLabel, 0,
                (super.rowsUsed() + super.getOffset()) - 2);
        this.add(this.maximumIterationsValue, 1,
                (super.rowsUsed() + super.getOffset()) - 2);
    }


    @Override
    public void refreshView() {

        super.refreshView();

        this.maximumIterationsValue.setText("");

        if (this.getConfiguration() != null) {

            this.maximumIterationsValue.setText(""
                    + this.getConfiguration().getMaximumIterations());
        }
    }
}
