package edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes;


// @formatter:off
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
// @formatter:on

import javafx.scene.control.Label;
import javafx.scene.text.Text;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseForceDirectedLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.PrefuseForceDirectedLayoutConfiguration;


/**
 * The {@code PrefuseForceDirectedLayoutConfigurationView} class provides a
 * status panes for {@link PrefuseForceDirectedLayoutConfiguration
 * PrefuseForceDirectedLayoutConfigurations}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class PrefuseForceDirectedLayoutConfigurationView
        extends
        PrefuseLayoutConfigurationView<PrefuseForceDirectedLayout, PrefuseForceDirectedLayoutConfiguration> {


    private final Label iterationsLabel;
    private final Text  iterationsValue;

    private final Label enforceBoundsLabel;
    private final Text  enforceBoundsValue;



    /**
     * Creates a new instance of the
     * {@code PrefuseForceDirectedLayoutConfigurationView} class.
     *
     * @param title
     *            the title for this {@link IConfigurationView}.
     */
    public PrefuseForceDirectedLayoutConfigurationView(final String title) {

        super(title, new PrefuseForceDirectedLayoutConfiguration(), 2);

        this.iterationsLabel = new Label("Iterations: ");
        this.iterationsValue = new Text();
        this.iterationsLabel.setStyle("-fx-font-weight: bold");

        this.add(this.iterationsLabel, 0,
                (super.rowsUsed() + super.getOffset()) - 2);
        this.add(this.iterationsValue, 1,
                (super.rowsUsed() + super.getOffset()) - 2);

        this.enforceBoundsLabel = new Label("Enforce Bounds? ");
        this.enforceBoundsValue = new Text();
        this.enforceBoundsLabel.setStyle("-fx-font-weight: bold");

        this.add(this.enforceBoundsLabel, 0,
                (super.rowsUsed() + super.getOffset()) - 1);
        this.add(this.enforceBoundsValue, 1,
                (super.rowsUsed() + super.getOffset()) - 1);
    }



    @Override
    public void refreshView() {

        super.refreshView();

        this.iterationsValue.setText("");
        this.enforceBoundsValue.setText("");

        if (this.getConfiguration() != null) {

            this.iterationsValue.setText(""
                    + this.getConfiguration().getIterations());

            this.enforceBoundsValue.setText(""
                    + this.getConfiguration().enforcesBounds());
        }
    }
}
