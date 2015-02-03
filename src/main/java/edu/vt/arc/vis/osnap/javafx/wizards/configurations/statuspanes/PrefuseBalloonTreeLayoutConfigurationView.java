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
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseBalloonTreeLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.PrefuseBalloonTreeLayoutConfiguration;


/**
 * The {@code PrefuseBalloonTreeLayoutConfigurationView} class provides a status panes
 * for {@link PrefuseBalloonTreeLayoutConfiguration PrefuseBalloonTreeConfigurations}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class PrefuseBalloonTreeLayoutConfigurationView
        extends
        PrefuseTreeLayoutComponentConfigurationView<PrefuseBalloonTreeLayout, PrefuseBalloonTreeLayoutConfiguration> {

    private final Label minimumRadiusLabel;
    private final Text  minimumRadiusValue;



    /**
     * Creates a new instance of the {@code PrefuseBalloonTreeLayoutConfigurationView}
     * class.
     *
     * @param title
     *            the title for this {@link IConfigurationView}.
     */
    public PrefuseBalloonTreeLayoutConfigurationView(final String title) {

        super(title, new PrefuseBalloonTreeLayoutConfiguration(), 1);

        this.minimumRadiusLabel = new Label("Minimum Radius:");
        this.minimumRadiusValue = new Text();
        this.minimumRadiusValue.setStyle("-fx-font-weight: bold");


        this.add(this.minimumRadiusLabel, 0,
                (super.rowsUsed() + super.getOffset()) - 1);
        this.add(this.minimumRadiusValue, 1,
                (super.rowsUsed() + super.getOffset()) - 1);
    }

    @Override
    public void refreshView() {

        super.refreshView();

        this.minimumRadiusValue.setText("");
        if (this.getConfiguration() != null) {

            this.minimumRadiusValue.setText(""
                    + this.getConfiguration().getMinimumRadius());
        }
    }
}
