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
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleScaleLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.SimpleScaleLayoutConfiguration;


/**
 * The {@code SimpleScaleLayoutConfigurationView} class provides a status panes
 * for {@link SimpleScaleLayoutConfiguration ScaleLayoutConfigurations}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class SimpleScaleLayoutConfigurationView
        extends
        LayoutConfigurationView<SimpleScaleLayout, SimpleScaleLayoutConfiguration> {

    private final Label scaleLabel;
    private final Text  scaleValue;


    /**
     * Creates a new instance of the {@code SimpleScaleLayoutConfigurationView}
     * class.
     *
     * @param title
     *            the title for this {@link IConfigurationView}.
     */
    public SimpleScaleLayoutConfigurationView(final String title) {

        super(title, new SimpleScaleLayoutConfiguration());


        this.scaleValue = new Text();
        this.scaleLabel = new Label("Scale:");
        this.scaleLabel.setStyle("-fx-font-weight: bold");
        this.scaleLabel.setLabelFor(this.scaleValue);

        this.add(this.scaleLabel, 0, super.rowsUsed());
        this.add(this.scaleValue, 1, super.rowsUsed());

    }



    @Override
    public void refreshView() {

        super.refreshView();
        this.scaleValue.setText("");
        if (this.getConfiguration() != null) {

            if (this.getConfiguration().getScale() != null) {

                this.scaleValue.setText(this.getConfiguration().getScale()
                        .toString());
            }
        }
    }
}