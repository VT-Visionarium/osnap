package edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes;


// formatter:off
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
// formatter:on

import javafx.scene.control.Label;
import javafx.scene.text.Text;
import edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.TieredOrbitalLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.TieredOrbitalLayoutConfiguration;


/**
 * The {@code TieredOrbitalLayoutConfigurationView} class provides a status
 * panes for {@link TieredOrbitalLayoutConfiguration
 * SolarSystemCoordinateLayoutConfigurations}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class TieredOrbitalLayoutConfigurationView
        extends
        TreeLayoutConfigurationView<TieredOrbitalLayout, TieredOrbitalLayoutConfiguration> {

    private final Label metadataLabel;
    private final Text  metadataValue;

    private final Label invertPathLabel;
    private final Text  invertPathValue;

    private final Label ignoreDirectionLabel;
    private final Text  ignoreDirectionValue;

    private final Label minimalDistanceLabel;
    private final Text  minimalDistanceValue;

    private final Label maximumNodeRadiusLabel;
    private final Text  maximumNodeRadiusValue;

    private final Label depthModifierLabel;
    private final Text  depthModifierValue;


    /**
     * Creates a new instance of the
     * {@code TieredOrbitalLayoutConfigurationView} class.
     * 
     * @param title
     *            the title for this {@link IConfigurationView}.
     */
    public TieredOrbitalLayoutConfigurationView(final String title) {

        super(title, new TieredOrbitalLayoutConfiguration());

        this.metadataValue = new Text();
        this.metadataLabel = new Label("Metadata:");
        this.metadataLabel.setStyle("-fx-font-weight: bold");
        this.metadataLabel.setLabelFor(this.metadataValue);

        this.invertPathValue = new Text();
        this.invertPathLabel = new Label("Invert Path to Root?");
        this.invertPathLabel.setStyle("-fx-font-weight: bold");
        this.invertPathLabel.setLabelFor(this.invertPathValue);

        this.ignoreDirectionValue = new Text();
        this.ignoreDirectionLabel = new Label("Ignore Edge Direction?");
        this.ignoreDirectionLabel.setStyle("-fx-font-weight: bold");
        this.ignoreDirectionLabel.setLabelFor(this.ignoreDirectionValue);

        this.minimalDistanceValue = new Text();
        this.minimalDistanceLabel = new Label("Minimal Distance:");
        this.minimalDistanceLabel.setStyle("-fx-font-weight: bold");
        this.minimalDistanceLabel.setLabelFor(this.minimalDistanceValue);

        this.maximumNodeRadiusValue = new Text();
        this.maximumNodeRadiusLabel = new Label("Maximum Node Radius:");
        this.maximumNodeRadiusLabel.setStyle("-fx-font-weight: bold");
        this.maximumNodeRadiusLabel.setLabelFor(this.maximumNodeRadiusValue);

        this.depthModifierValue = new Text();
        this.depthModifierLabel = new Label("Depth Modifier:");
        this.depthModifierLabel.setStyle("-fx-font-weight: bold");
        this.depthModifierLabel.setLabelFor(this.depthModifierValue);

        this.add(this.metadataLabel, 0, super.rowsUsed());
        this.add(this.metadataValue, 1, super.rowsUsed());
        this.add(this.invertPathLabel, 0, super.rowsUsed() + 1);
        this.add(this.invertPathValue, 1, super.rowsUsed() + 1);
        this.add(this.ignoreDirectionLabel, 0, super.rowsUsed() + 2);
        this.add(this.ignoreDirectionValue, 1, super.rowsUsed() + 2);
        this.add(this.minimalDistanceLabel, 0, super.rowsUsed() + 3);
        this.add(this.minimalDistanceValue, 1, super.rowsUsed() + 3);
        this.add(this.maximumNodeRadiusLabel, 0, super.rowsUsed() + 4);
        this.add(this.maximumNodeRadiusValue, 1, super.rowsUsed() + 4);
        this.add(this.depthModifierLabel, 0, super.rowsUsed() + 5);
        this.add(this.depthModifierValue, 1, super.rowsUsed() + 5);
    }

    @Override
    protected int rowsUsed() {

        return super.rowsUsed() + 6;
    }

    @Override
    public void refreshView() {

        super.refreshView();

        this.metadataValue.setText("");
        this.invertPathValue.setText("");
        this.ignoreDirectionValue.setText("");
        this.minimalDistanceValue.setText("");
        this.maximumNodeRadiusValue.setText("");
        this.depthModifierValue.setText("");

        if (this.getConfiguration() != null) {

            if (this.getConfiguration().isInvertPathToRootNode()) {

                this.invertPathValue.setText("True");
            }
            else {

                this.invertPathValue.setText("False");
            }
            if (this.getConfiguration().isIgnoreEdgeDirection()) {

                this.ignoreDirectionValue.setText("True");
            }
            else {

                this.ignoreDirectionValue.setText("False");
            }

            this.minimalDistanceValue.setText(""
                    + this.getConfiguration().getMinimalDistance());


            this.maximumNodeRadiusValue.setText(""
                    + this.getConfiguration().getMaximumNodeRadius());

            this.depthModifierValue.setText(""
                    + this.getConfiguration().getDepthModifier());
        }
    }
}
