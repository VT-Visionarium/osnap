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
import edu.vt.arc.vis.osnap.core.domain.layout.common.ICoordinateLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ICoordinateLayoutConfiguration;


/**
 * The abstract {@code CoordinateLayoutConfigurationView} class provides the
 * base for all status panes for {@link ICoordinateLayoutConfiguration
 * CoordinateLayoutConfigurations}.
 *
 * @param <O>
 *            the type of the {@link ICoordinateLayout CoordinateLayout}.
 * @param <C>
 *            the type of the {@link ICoordinateLayoutConfiguration
 *            CoordinateLayoutConfiguration}.
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public abstract class CoordinateLayoutConfigurationView<O extends ICoordinateLayout, C extends ICoordinateLayoutConfiguration<O>>
        extends LayoutConfigurationView<O, C>
        implements ICoordinateLayoutConfigurationView<O, C> {


    private final Label xOutputLabel;
    private final Label yOutputLabel;
    private final Label zOutputLabel;
    private final Text  xOutputValue;
    private final Text  yOutputValue;
    private final Text  zOutputValue;

    private final Label xOutputScaleLabel;
    private final Label yOutputScaleLabel;
    private final Label zOutputScaleLabel;
    private final Text  xOutputScaleValue;
    private final Text  yOutputScaleValue;
    private final Text  zOutputScaleValue;


    /**
     * Creates a new instance of the {@code SimpleShapeLayoutConfigurationView}
     * class.
     *
     * @param title
     *            the title for this {@link IConfigurationView}.
     */
    public CoordinateLayoutConfigurationView(final String title) {

        this(title, null);
    }

    /**
     * Creates a new instance of the {@code SimpleShapeLayoutConfigurationView}
     * class.
     *
     * @param title
     *            the title for this {@link IConfigurationView}.
     * @param defaultConfiguration
     *            the default configuration.
     */
    public CoordinateLayoutConfigurationView(final String title,
            final C defaultConfiguration) {

        super(title, defaultConfiguration);

        this.xOutputLabel = new Label("Output on X: ");
        this.yOutputLabel = new Label("Output on Y: ");
        this.zOutputLabel = new Label("Output on Z: ");

        this.xOutputValue = new Text();
        this.xOutputValue.setStyle("-fx-font-weight: bold");
        this.yOutputValue = new Text();
        this.yOutputValue.setStyle("-fx-font-weight: bold");
        this.zOutputValue = new Text();
        this.zOutputValue.setStyle("-fx-font-weight: bold");


        this.xOutputScaleLabel = new Label("Scale on X: ");
        this.yOutputScaleLabel = new Label("Scale on Y: ");
        this.zOutputScaleLabel = new Label("Scale on Z: ");

        this.xOutputScaleValue = new Text();
        this.xOutputScaleValue.setStyle("-fx-font-weight: bold");
        this.yOutputScaleValue = new Text();
        this.yOutputScaleValue.setStyle("-fx-font-weight: bold");
        this.zOutputScaleValue = new Text();
        this.zOutputScaleValue.setStyle("-fx-font-weight: bold");

        this.add(this.xOutputLabel, 0, super.rowsUsed());
        this.add(this.xOutputValue, 1, super.rowsUsed());
        this.add(this.yOutputLabel, 0, super.rowsUsed() + 1);
        this.add(this.yOutputValue, 1, super.rowsUsed() + 1);
        this.add(this.zOutputLabel, 0, super.rowsUsed() + 2);
        this.add(this.zOutputValue, 1, super.rowsUsed() + 2);


        this.add(this.xOutputScaleLabel, 0, super.rowsUsed() + 3);
        this.add(this.xOutputScaleValue, 1, super.rowsUsed() + 3);
        this.add(this.yOutputScaleLabel, 0, super.rowsUsed() + 4);
        this.add(this.yOutputScaleValue, 1, super.rowsUsed() + 4);
        this.add(this.zOutputScaleLabel, 0, super.rowsUsed() + 5);
        this.add(this.zOutputScaleValue, 1, super.rowsUsed() + 5);
    }

    @Override
    protected int rowsUsed() {

        return super.rowsUsed() + 6;
    }

    @Override
    public void refreshView() {

        super.refreshView();

        this.xOutputValue.setText("");
        this.yOutputValue.setText("");
        this.zOutputValue.setText("");

        this.xOutputScaleValue.setText("");
        this.yOutputScaleValue.setText("");
        this.zOutputScaleValue.setText("");

        if (this.getConfiguration() != null) {

            this.xOutputValue.setText(this.getConfiguration().getXOutput()
                    .toString());
            this.yOutputValue.setText(this.getConfiguration().getYOutput()
                    .toString());
            this.zOutputValue.setText(this.getConfiguration().getZOutput()
                    .toString());

            this.xOutputScaleValue.setText(""
                    + this.getConfiguration().getFirstComponentScale());
            this.yOutputScaleValue.setText(""
                    + this.getConfiguration().getSecondComponentScale());
            this.zOutputScaleValue.setText(""
                    + this.getConfiguration().getThirdComponentScale());
        }
    }
}
