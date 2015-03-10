package edu.vt.arc.vis.osnap.javafx.wizards.configurations;

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


import org.jutility.math.geometry.IRectangle2;
import org.jutility.math.vectoralgebra.IPoint2;

import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.IPrefuseLayout;



/**
 * The {@link IPrefuseLayoutConfiguration} provides a contract for all
 * {@link IConfiguration Configurations} of {@link IPrefuseLayout
 * Prefuse LayoutComponents}.
 *
 * @param <T>
 *            the type of the {@link IPrefuseLayout Prefuse
 *            LayoutComponent}.
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 1.2.0
 */
public interface IPrefuseLayoutConfiguration<T extends IPrefuseLayout>
        extends ICoordinateLayoutConfiguration<T> {

    /**
     * Returns the duration.
     *
     * @return the duration.
     */
    public abstract long getDuration();

    /**
     * Sets the duration.
     *
     * @param duration
     *            the duration.
     */
    public abstract void setDuration(final long duration);

    /**
     * Returns the layout anchor.
     *
     * @return the layout anchor.
     */
    public abstract IPoint2<Double> getLayoutAnchor();

    /**
     * Sets the layout anchor.
     *
     * @param x
     *            the x coordinate of the layout anchor.
     * @param y
     *            the y coordinate of the layout anchor.
     */
    public abstract void setLayoutAnchor(final double x, final double y);

    /**
     * Returns the layout bounds.
     *
     * @return the layout bounds.
     */
    public abstract IRectangle2<Double> getLayoutBounds();

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
    public abstract void setLayoutBounds(final double x, final double y,
            final double width, final double height);


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
    public abstract void setMargins(final int topMargin,
            final int bottomMargin, final int leftMargin, final int rightMargin);

    /**
     * Clears the margins;
     */
    public abstract void clearMargins();

}