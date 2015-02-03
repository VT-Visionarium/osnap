package edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents;


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

import org.jutility.math.geometry.IRectangle2;
import org.jutility.math.vectorAlgebra.IPoint2;

import edu.vt.arc.vis.osnap.core.domain.layout.common.I2DCoordinateLayout;
import prefuse.action.layout.Layout;


/**
 * The {@link IPrefuseLayout} interface provides the contract for all Prefuse
 * Layouts. All Prefuse Layouts are {@link I2DCoordinateLayout 2D Coordinate
 * Layouts}.
 *
 * @author Peter J. Radics
 * @version 1.2.3
 * @since 0.1.0
 *
 */
public interface IPrefuseLayout
        extends I2DCoordinateLayout {

    /**
     * Returns the prefuse layout of this LayoutComponent.
     *
     * @return the prefuse layout.
     */
    public abstract Layout getLayout();


    /**
     * Sets the duration for which the layout algorithm runs.
     *
     * @param duration
     *            the duration of the runtime.
     */
    public abstract void setDuration(long duration);

    /**
     * Returns the duration.
     *
     * @return the duration.
     */
    public abstract long getDuration();


    /**
     * Sets the anchor for the layout algorithm
     *
     * @param x
     *            X coordinate of the anchor.
     * @param y
     *            Y coordinate of the anchor.
     */
    public abstract void setLayoutAnchor(double x, double y);


    /**
     * Returns the layout anchor.
     *
     * @return the layout anchor.
     */
    public abstract IPoint2<Double> getLayoutAnchor();


    /**
     * Explicitly set the layout bounds.
     *
     * @param x
     *            X coordinate of the top left corner of the bounds.
     * @param y
     *            Y coordinate of the top left corner of the bounds.
     * @param width
     *            the width of the layout bounds.
     * @param height
     *            the height of the layout bounds.
     */
    public abstract void setLayoutBounds(double x, double y, double width,
            double height);


    /**
     * Returns the layout bounds.
     *
     * @return the layout bounds.
     */
    public abstract IRectangle2<Double> getLayoutBounds();


    /**
     * Set the margins the layout algorithm should observe within its layout
     * bounds.
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
    public abstract void setMargin(int topMargin, int bottomMargin,
            int leftMargin, int rightMargin);


    /**
     * Returns the top margin.
     *
     * @return the top margin.
     */
    public abstract int getTopMargin();


    /**
     * Returns the bottom margin.
     *
     * @return the bottom margin.
     */
    public abstract int getBottomMargin();


    /**
     * Returns the left margin.
     *
     * @return the left margin.
     */
    public abstract int getLeftMargin();


    /**
     * Returns the right margin.
     *
     * @return the right margin.
     */
    public abstract int getRightMargin();

    /**
     * Applies the provided parameters to the contained prefuse layout.
     */
    public abstract void applyParameters();
}
