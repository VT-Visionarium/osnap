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
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleColorLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.SimpleColorLayoutConfiguration;


/**
 * The {@code SimpleColorLayoutConfigurationView} class provides a status panes
 * for {@link SimpleColorLayoutConfiguration ColorLayoutConfigurations}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class SimpleColorLayoutConfigurationView
        extends
        LayoutConfigurationView<SimpleColorLayout, SimpleColorLayoutConfiguration> {


    private final Label colorLabel;
    private final Text  colorValue;



    /**
     * Creates a new instance of the {@code SimpleColorLayoutConfigurationView}
     * class.
     * 
     * @param title
     *            the title for this {@link IConfigurationView}.
     */
    public SimpleColorLayoutConfigurationView(final String title) {

        super(title, new SimpleColorLayoutConfiguration());

        this.colorValue = new Text();
        this.colorLabel = new Label("Color:");
        this.colorLabel.setStyle("-fx-font-weight: bold");
        this.colorLabel.setLabelFor(this.colorValue);


        this.add(this.colorLabel, 0, super.rowsUsed());
        this.add(this.colorValue, 1, super.rowsUsed());
    }

    @Override
    public void refreshView() {

        super.refreshView();

        this.colorValue.setText("");
        if (this.getConfiguration() != null) {

            if (this.getConfiguration().getColor() != null) {

                this.colorValue.setText(this.getConfiguration().getColor()
                        .toString());
            }
        }
    }
}
