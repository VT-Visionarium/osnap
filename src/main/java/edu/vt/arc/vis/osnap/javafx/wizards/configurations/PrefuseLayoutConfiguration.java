package edu.vt.arc.vis.osnap.javafx.wizards.configurations;


// @ formatter:off
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
// @ formatter:on


import org.jutility.math.geometry.IRectangle2;
import org.jutility.math.geometry.Rectangle2;
import org.jutility.math.vectoralgebra.IPoint2;
import org.jutility.math.vectoralgebra.Point2;

import edu.vt.arc.vis.osnap.core.domain.layout.common.CoordinateComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.IPrefuseLayout;


/**
 * The abstract {@code PrefuseStatusObject} class provides the base for
 * {@link IConfiguration Configuration} of {@link IPrefuseLayout Prefuse
 * LayoutComponents}.
 * 
 * @param <T>
 *            the type of the {@link IPrefuseLayout}.
 * 
 * @author Peter J. Radics
 * @version 1.2.3
 * @since 0.5.0
 */
public abstract class PrefuseLayoutConfiguration<T extends IPrefuseLayout>
        extends CoordinateLayoutConfiguration<T>
        implements IPrefuseLayoutConfiguration<T> {

    private long                duration;

    private IPoint2<Double>     layoutAnchor;
    private IRectangle2<Double> layoutBounds;

    private int                 topMargin;
    private int                 bottomMargin;
    private int                 leftMargin;
    private int                 rightMargin;

    /**
     * Returns the duration.
     * 
     * @return the duration.
     */
    @Override
    public long getDuration() {

        return this.duration;
    }

    /**
     * Sets the duration.
     * 
     * @param duration
     *            the duration.
     */
    @Override
    public void setDuration(final long duration) {

        this.duration = duration;
    }

    /**
     * Returns the layout anchor.
     * 
     * @return the layout anchor.
     */
    @Override
    public IPoint2<Double> getLayoutAnchor() {

        return this.layoutAnchor;
    }

    /**
     * Sets the layout anchor.
     * 
     * @param x
     *            the x coordinate of the layout anchor.
     * @param y
     *            the y coordinate of the layout anchor.
     */
    @Override
    public void setLayoutAnchor(final double x, final double y) {

        this.layoutAnchor = new Point2<>(x, y, Double.class);
    }

    /**
     * Returns the layout bounds.
     * 
     * @return the layout bounds.
     */
    @Override
    public IRectangle2<Double> getLayoutBounds() {

        return this.layoutBounds;
    }

    /**
     * Sets the layout bounds.
     * 
     * @param x
     *            the x coordinate of the top left corner of the layout bounds.
     * @param y
     *            the y coordinate of the top left corner of the layout bounds.
     * @param width
     *            the width of the layout bounds.
     * @param height
     *            the height of the layout bounds.
     */
    @Override
    public void setLayoutBounds(final double x, final double y,
            final double width, final double height) {

        this.layoutBounds = new Rectangle2<>(x, y, width, height, Double.class);
    }


    /**
     * Returns the top margin.
     * 
     * @return the top margin.
     */
    @Override
    public int getTopMargin() {

        return this.topMargin;
    }

    /**
     * Returns the bottom margin.
     * 
     * @return the bottom margin.
     */
    @Override
    public int getBottomMargin() {

        return this.bottomMargin;
    }

    /**
     * Returns the left margin.
     * 
     * @return the left margin.
     */
    @Override
    public int getLeftMargin() {

        return this.leftMargin;
    }

    /**
     * Returns the right margin.
     * 
     * @return the right margin.
     */
    @Override
    public int getRightMargin() {

        return this.rightMargin;
    }

    /**
     * Sets the margin of the layout within the layout bounds.
     * 
     * @param topMargin
     *            the top margin.
     * @param bottomMargin
     *            the bottom margin.
     * @param leftMargin
     *            the left margin.
     * @param rightMargin
     *            the right margin.
     */
    @Override
    public void setMargins(int topMargin, int bottomMargin, int leftMargin,
            int rightMargin) {

        this.topMargin = topMargin;
        this.bottomMargin = bottomMargin;
        this.leftMargin = leftMargin;
        this.rightMargin = rightMargin;
    }

    /**
     * Clears the margins;
     */
    @Override
    public void clearMargins() {

        this.topMargin = 0;
        this.bottomMargin = 0;
        this.leftMargin = 0;
        this.rightMargin = 0;
    }


    /**
     * Creates a new instance of the {@code PrefuseLayoutConfiguration} class
     * with the provided defaults for name and description.
     * 
     * @param defaultName
     *            the default name.
     * @param defaultDescription
     *            the default description.
     */
    public PrefuseLayoutConfiguration(final String defaultName,
            final String defaultDescription) {

        super(defaultName, defaultDescription);

        this.setZOutput(CoordinateComponent.NO_COMPONENT);

        this.duration = 0;
        this.layoutAnchor = new Point2<>(400, 300, Double.class);
        this.layoutBounds = new Rectangle2<>(new Point2<>(0, 0, Double.class),
                new Point2<>(800, 600, Double.class), Double.class);
        this.topMargin = 0;
        this.bottomMargin = 0;
        this.leftMargin = 0;
        this.rightMargin = 0;
    }
}
