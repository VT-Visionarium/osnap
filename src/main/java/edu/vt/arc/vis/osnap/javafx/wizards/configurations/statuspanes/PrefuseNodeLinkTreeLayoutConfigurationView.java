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
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseNodeLinkTreeLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.PrefuseNodeLinkTreeLayoutConfiguration;


/**
 * The {@code PrefuseNodeLinkTreeLayoutConfigurationView} class provides a status panes for
 * {@link PrefuseNodeLinkTreeLayoutConfiguration PrefuseNodeLinkTreeConfigurations}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class PrefuseNodeLinkTreeLayoutConfigurationView
        extends
        PrefuseTreeLayoutComponentConfigurationView<PrefuseNodeLinkTreeLayout, PrefuseNodeLinkTreeLayoutConfiguration> {

    private final Label orientation;
    private final Text  orientationValue;

    private final Label depthSpacing;
    private final Text  depthSpacingValue;

    private final Label siblingSpacing;
    private final Text  spaceBetweenSiblingsValue;

    private final Label subTreeSpacing;
    private final Text  spaceBetweenSubTreesValue;



    /**
     * Creates a new instance of the {@code PrefuseNodeLinkTreeLayoutConfigurationView} class.
     *
     * @param title
     *            the title for this {@link IConfigurationView}.
     */
    public PrefuseNodeLinkTreeLayoutConfigurationView(final String title) {

        super(title, new PrefuseNodeLinkTreeLayoutConfiguration(), 4);

        this.orientation = new Label("Orientation: ");
        this.orientationValue = new Text();
        this.orientationValue.setStyle("-fx-font-weight: bold");

        this.depthSpacing = new Label("Depth Spacing: ");
        this.depthSpacingValue = new Text();
        this.depthSpacingValue.setStyle("-fx-font-weight: bold");

        this.siblingSpacing = new Label("Space Between Siblings: ");
        this.spaceBetweenSiblingsValue = new Text();
        this.spaceBetweenSiblingsValue.setStyle("-fx-font-weight: bold");

        this.subTreeSpacing = new Label("Space Between Neighboring Sub-Trees: ");
        this.spaceBetweenSubTreesValue = new Text();
        this.spaceBetweenSubTreesValue.setStyle("-fx-font-weight: bold");


        this.add(this.orientation, 0, 3);
        this.add(this.orientationValue, 1, 3);
        this.add(this.depthSpacing, 0, 4);
        this.add(this.depthSpacingValue, 1, 4);
        this.add(this.siblingSpacing, 0, 5);
        this.add(this.spaceBetweenSiblingsValue, 1, 5);
        this.add(this.subTreeSpacing, 0, 6);
        this.add(this.spaceBetweenSubTreesValue, 1, 6);

    }

    @Override
    public void refreshView() {

        super.refreshView();

        this.orientationValue.setText("");
        this.depthSpacingValue.setText("");
        this.spaceBetweenSiblingsValue.setText("");
        this.spaceBetweenSubTreesValue.setText("");

        if (this.getConfiguration() != null) {

            this.orientationValue.setText(this.getConfiguration()
                    .getOrientation().toString());

            this.depthSpacingValue.setText(""
                    + this.getConfiguration().getDepthSpacing());

            this.spaceBetweenSiblingsValue.setText(""
                    + this.getConfiguration().getSpaceBetweenSiblings());

            this.spaceBetweenSubTreesValue.setText(""
                    + this.getConfiguration()
                            .getSpaceBetweenNeighboringSubtrees());
        }
    }
}
