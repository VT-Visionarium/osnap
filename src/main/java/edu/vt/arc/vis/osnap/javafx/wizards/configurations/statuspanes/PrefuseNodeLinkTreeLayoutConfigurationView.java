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
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseNodeLinkTreeLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.PrefuseNodeLinkTreeLayoutConfiguration;


/**
 * The {@code PrefuseNodeLinkTreeLayoutConfigurationView} class provides a
 * status panes for {@link PrefuseNodeLinkTreeLayoutConfiguration
 * PrefuseNodeLinkTreeConfigurations}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class PrefuseNodeLinkTreeLayoutConfigurationView
        extends
        PrefuseTreeLayoutConfigurationView<PrefuseNodeLinkTreeLayout, PrefuseNodeLinkTreeLayoutConfiguration> {

    private final Label orientationLabel;
    private final Text  orientationValue;

    private final Label depthSpacingLabel;
    private final Text  depthSpacingValue;

    private final Label siblingSpacingLabel;
    private final Text  siblingSpacingValue;

    private final Label subTreeSpacingLabel;
    private final Text  subTreeSpacingValue;



    /**
     * Creates a new instance of the
     * {@code PrefuseNodeLinkTreeLayoutConfigurationView} class.
     *
     * @param title
     *            the title for this {@link IConfigurationView}.
     */
    public PrefuseNodeLinkTreeLayoutConfigurationView(final String title) {

        super(title, new PrefuseNodeLinkTreeLayoutConfiguration(), 4);

        this.orientationValue = new Text();
        this.orientationLabel = new Label("Orientation:");
        this.orientationLabel.setStyle("-fx-font-weight: bold");
        this.orientationLabel.setLabelFor(this.orientationValue);

        this.depthSpacingValue = new Text();
        this.depthSpacingLabel = new Label("Depth Spacing:");
        this.depthSpacingLabel.setStyle("-fx-font-weight: bold");
        this.depthSpacingLabel.setLabelFor(this.depthSpacingValue);

        this.siblingSpacingValue = new Text();
        this.siblingSpacingLabel = new Label("Space Between Siblings:");
        this.siblingSpacingLabel.setStyle("-fx-font-weight: bold");
        this.siblingSpacingLabel.setLabelFor(this.siblingSpacingValue);

        this.subTreeSpacingValue = new Text();
        this.subTreeSpacingLabel = new Label(
                "Space Between Neighboring Sub-Trees:");
        this.subTreeSpacingLabel.setStyle("-fx-font-weight: bold");
        this.subTreeSpacingLabel.setLabelFor(this.subTreeSpacingValue);


        this.add(this.orientationLabel, 0, 3);
        this.add(this.orientationValue, 1, 3);
        this.add(this.depthSpacingLabel, 0, 4);
        this.add(this.depthSpacingValue, 1, 4);
        this.add(this.siblingSpacingLabel, 0, 5);
        this.add(this.siblingSpacingValue, 1, 5);
        this.add(this.subTreeSpacingLabel, 0, 6);
        this.add(this.subTreeSpacingValue, 1, 6);

    }

    @Override
    public void refreshView() {

        super.refreshView();

        this.orientationValue.setText("");
        this.depthSpacingValue.setText("");
        this.siblingSpacingValue.setText("");
        this.subTreeSpacingValue.setText("");

        if (this.getConfiguration() != null) {

            this.orientationValue.setText(this.getConfiguration()
                    .getOrientation().toString());

            this.depthSpacingValue.setText(""
                    + this.getConfiguration().getDepthSpacing());

            this.siblingSpacingValue.setText(""
                    + this.getConfiguration().getSpaceBetweenSiblings());

            this.subTreeSpacingValue.setText(""
                    + this.getConfiguration()
                            .getSpaceBetweenNeighboringSubtrees());
        }
    }
}