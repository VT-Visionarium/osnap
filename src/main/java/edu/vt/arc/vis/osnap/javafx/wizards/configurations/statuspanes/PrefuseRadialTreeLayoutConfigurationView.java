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
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseRadialTreeLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.PrefuseRadialTreeLayoutConfiguration;


/**
 * The {@code PrefuseRadialTreeLayout} class provides a status panes
 * for {@link PrefuseRadialTreeLayoutConfiguration PrefuseRadialTreeConfigurations}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class PrefuseRadialTreeLayoutConfigurationView
        extends
        PrefuseTreeLayoutConfigurationView<PrefuseRadialTreeLayout, PrefuseRadialTreeLayoutConfiguration> {

    private final Label radiusIncrementLabel;
    private final Text  radiusIncrementValue;

    /**
     * Creates a new instance of the {@code PrefuseRadialTreeLayoutConfigurationView}
     * class.
     *
     * @param title
     *            the title for this {@link IConfigurationView}.
     */
    public PrefuseRadialTreeLayoutConfigurationView(final String title) {

        super(title, new PrefuseRadialTreeLayoutConfiguration(), 1);


        this.radiusIncrementLabel = new Label("Radius Increment:");
        this.radiusIncrementValue = new Text();
        this.radiusIncrementLabel.setStyle("-fx-font-weight: bold");

        this.add(this.radiusIncrementLabel, 0,
                (super.rowsUsed() + super.getOffset()) - 1);
        this.add(this.radiusIncrementValue, 1,
                (super.rowsUsed() + super.getOffset()) - 1);
    }


    @Override
    public void refreshView() {

        super.refreshView();

        this.radiusIncrementValue.setText("");
        if (this.getConfiguration() != null) {

            this.radiusIncrementValue.setText(""
                    + this.getConfiguration().getRadiusIncrement());
        }
    }
}
