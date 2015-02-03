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
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleViewpointLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.SimpleViewpointLayoutConfiguration;


/**
 * The {@code SimpleViewpointLayoutConfigurationView} class provides a status
 * panes for {@link SimpleViewpointLayoutConfiguration
 * ViewPointLayoutConfigurations}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class SimpleViewpointLayoutConfigurationView
        extends
        LayoutConfigurationView<SimpleViewpointLayout, SimpleViewpointLayoutConfiguration> {

    private final Label cameraOffset;
    private final Text  xOffset;
    private final Text  yOffset;
    private final Text  zOffset;



    /**
     * Creates a new instance of the
     * {@code SimpleViewpointLayoutConfigurationView} class.
     *
     * @param title
     *            the title for this {@link IConfigurationView}.
     */
    public SimpleViewpointLayoutConfigurationView(final String title) {

        super(title, new SimpleViewpointLayoutConfiguration());

        this.cameraOffset = new Label("Viewpoint Camera Offsets: x, y, z");
        this.xOffset = new Text();
        this.yOffset = new Text();
        this.zOffset = new Text();

        this.add(this.cameraOffset, 0, 3);
        this.add(this.xOffset, 0, 4);
        this.xOffset.setStyle("-fx-font-weight: bold");
        this.add(this.yOffset, 1, 4);
        this.yOffset.setStyle("-fx-font-weight: bold");
        this.add(this.zOffset, 2, 4);
        this.zOffset.setStyle("-fx-font-weight: bold");

    }

    @Override
    public void refreshView() {

        super.refreshView();

        this.xOffset.setText("");
        this.yOffset.setText("");
        this.zOffset.setText("");

        if (this.getConfiguration() != null) {

            this.xOffset.setText("" + this.getConfiguration().getOffsetX());
            this.yOffset.setText("" + this.getConfiguration().getOffsetY());
            this.zOffset.setText("" + this.getConfiguration().getOffsetZ());
        }
    }
}
