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
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.IPrefuseLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.IPrefuseLayoutConfiguration;



/**
 * The {@code PrefuseLayoutConfigurationView} class provides a status panes for
 * {@link IPrefuseLayoutConfiguration Prefuse LayoutConfigurations}.
 *
 * @param <O>
 *            the type of the {@link IPrefuseLayout}.
 * @param <C>
 *            the type of the {@link IPrefuseLayoutConfiguration}.
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public abstract class PrefuseLayoutConfigurationView<O extends IPrefuseLayout, C extends IPrefuseLayoutConfiguration<O>>
        extends CoordinateLayoutConfigurationView<O, C>
        implements IPrefuseLayoutConfigurationView<O, C> {

    private final Label durationLabel;
    private final Label layoutAnchorLabel;
    private final Label layoutBoundsLabel;
    private final Label layoutMarginsLabel;

    private final Text  durationValue;
    private final Text  layoutAnchorValue;
    private final Text  layoutBoundsValue;
    private final Text  layoutMarginsValue;

    private final int   offset;


    /**
     * Returns the offset.
     * 
     * @return the offset.
     */
    public int getOffset() {

        return this.offset;
    }

    /**
     * Creates a new instance of the {@code PrefuseLayoutConfigurationView}
     * class.
     * 
     * @param title
     *            the title for this {@link IConfigurationView}.
     */
    public PrefuseLayoutConfigurationView(final String title) {

        this(title, null);
    }

    /**
     * Creates a new instance of the {@code PrefuseLayoutConfigurationView}
     * class.
     * 
     * @param title
     *            the title for this {@link IConfigurationView}.
     * @param defaultConfiguration
     *            the default configuration.
     */
    public PrefuseLayoutConfigurationView(final String title,
            final C defaultConfiguration) {

        this(title, defaultConfiguration, 0);
    }

    /**
     * Creates a new instance of the {@code PrefuseLayoutConfigurationView}
     * class.
     * 
     * @param title
     *            the title for this {@link IConfigurationView}.
     * @param defaultConfiguration
     *            the default configuration.
     * @param offset
     *            the row offset of the values displayed by this view.
     */
    public PrefuseLayoutConfigurationView(final String title,
            final C defaultConfiguration, final int offset) {

        super(title, defaultConfiguration);

        this.offset = offset;

        this.durationValue = new Text();
        this.durationLabel = new Label("Layout Duration");
        this.durationLabel.setStyle("-fx-font-weight: bold");
        this.durationLabel.setLabelFor(this.durationValue);

        this.layoutAnchorValue = new Text();
        this.layoutAnchorLabel = new Label("Layout Anchor");
        this.layoutAnchorLabel.setStyle("-fx-font-weight: bold");
        this.layoutAnchorLabel.setLabelFor(this.layoutAnchorValue);

        this.layoutBoundsValue = new Text();
        this.layoutBoundsLabel = new Label("Layout Bounds (x, y, w, h)");
        this.layoutBoundsLabel.setStyle("-fx-font-weight: bold");
        this.layoutBoundsLabel.setLabelFor(this.layoutBoundsValue);

        this.layoutMarginsValue = new Text();
        this.layoutMarginsLabel = new Label("Layout Margins (t, b, l, r)");
        this.layoutMarginsLabel.setStyle("-fx-font-weight: bold");
        this.layoutMarginsLabel.setLabelFor(this.layoutMarginsValue);


        int row = super.rowsUsed() + offset;
        this.add(this.durationLabel, 0, row);
        this.add(this.durationValue, 1, row);
        row++;
        this.add(this.layoutAnchorLabel, 0, row);
        this.add(this.layoutAnchorValue, 1, row);
        row++;
        this.add(this.layoutBoundsLabel, 0, row);
        this.add(this.layoutBoundsValue, 1, row);
        row++;
        this.add(this.layoutMarginsLabel, 0, row);
        this.add(this.layoutMarginsValue, 1, row);
    }


    @Override
    public void refreshView() {

        super.refreshView();

        this.durationValue.setText("");
        this.layoutAnchorValue.setText("");
        this.layoutBoundsValue.setText("");
        this.layoutMarginsValue.setText("");
        if (this.getConfiguration() != null) {


            this.durationValue.setText(""
                    + this.getConfiguration().getDuration());


            this.layoutAnchorValue.setText(this.getConfiguration()
                    .getLayoutAnchor().toString());

            final double topLeftX = this.getConfiguration().getLayoutBounds()
                    .getTopLeftCorner().getX();
            final double topLeftY = this.getConfiguration().getLayoutBounds()
                    .getTopLeftCorner().getY();

            final double width = this.getConfiguration().getLayoutBounds()
                    .getBottomRightCorner().getX()
                    - topLeftX;
            final double height = this.getConfiguration().getLayoutBounds()
                    .getBottomRightCorner().getY()
                    - topLeftY;
            this.layoutBoundsValue.setText(topLeftX + ", " + topLeftY + ", "
                    + width + ", " + height);
            this.layoutMarginsValue.setText(""
                    + this.getConfiguration().getTopMargin() + ", "
                    + this.getConfiguration().getBottomMargin() + ", "
                    + this.getConfiguration().getLeftMargin() + ", "
                    + this.getConfiguration().getRightMargin());
        }
    }
}
