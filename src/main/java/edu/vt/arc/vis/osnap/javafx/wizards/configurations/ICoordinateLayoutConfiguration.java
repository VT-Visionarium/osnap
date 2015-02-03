package edu.vt.arc.vis.osnap.javafx.wizards.configurations;


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
//@formatter:on

import edu.vt.arc.vis.osnap.core.domain.layout.common.CoordinateComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ICoordinateLayout;


/**
 * The {@code ICoordinateLayoutConfiguration} interface provides the contract
 * for {@link IConfiguration Configurations} of {@link ICoordinateLayout
 * CoordinateLayoutComponents}.
 * 
 * @param <T>
 *            the type of the {@link ICoordinateLayout}.
 * 
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public interface ICoordinateLayoutConfiguration<T extends ICoordinateLayout>
        extends ILayoutConfiguration<T> {


    /**
     * Returns which of the components of provider is mapped to the getter for
     * the x coordinate, if any output is provided for that coordinate at all.
     * 
     * @return the component that is mapped to the x coordinate, if applicable.
     */
    public abstract CoordinateComponent getXOutput();


    /**
     * Sets which of the components of provider is mapped to the getter for the
     * x coordinate or if any output should be provided for that coordinate at
     * all.
     * 
     * @param coordinateComponent
     *            the component to use for output of the x coordinate, if at
     *            all.
     */
    public abstract void setXOutput(CoordinateComponent coordinateComponent);


    /**
     * Returns which of the components of provider is mapped to the getter for
     * the y coordinate, if any output is provided for that coordinate at all.
     * 
     * @return the component that is mapped to the y coordinate, if applicable.
     */
    public abstract CoordinateComponent getYOutput();


    /**
     * Sets which of the components of provider is mapped to the getter for the
     * y coordinate or if any output should be provided for that coordinate at
     * all.
     * 
     * @param coordinateComponent
     *            the component to use for output of the y coordinate, if at
     *            all.
     */
    public abstract void setYOutput(CoordinateComponent coordinateComponent);


    /**
     * Returns which of the components of provider is mapped to the getter for
     * the z coordinate, if any output is provided for that coordinate at all.
     * 
     * @return the component that is mapped to the z coordinate, if applicable.
     */
    public abstract CoordinateComponent getZOutput();


    /**
     * Sets which of the components of provider is mapped to the getter for the
     * z coordinate or if any output should be provided for that coordinate at
     * all.
     * 
     * @param coordinateComponent
     *            the component to use for output of the z coordinate, if at
     *            all.
     */
    public abstract void setZOutput(CoordinateComponent coordinateComponent);


    /**
     * Returns the scale factor of the first {@link CoordinateComponent}.
     *
     * @return the scale factor of the first {@link CoordinateComponent}.
     */
    public abstract float getFirstComponentScale();

    /**
     * Sets the scale factor of the first {@link CoordinateComponent}.
     *
     * @param value
     *            the scale factor of the first {@link CoordinateComponent}.
     */
    public abstract void setFirstComponentScale(final float value);


    /**
     * Returns the scale factor of the second {@link CoordinateComponent}.
     *
     * @return the scale factor of the second {@link CoordinateComponent}.
     */
    public abstract float getSecondComponentScale();

    /**
     * Sets the scale factor of the second {@link CoordinateComponent}.
     *
     * @param value
     *            the scale factor of the second {@link CoordinateComponent}.
     */
    public abstract void setSecondComponentScale(final float value);


    /**
     * Returns the scale factor of the third {@link CoordinateComponent}.
     *
     * @return the scale factor of the third {@link CoordinateComponent}.
     */
    public abstract float getThirdComponentScale();

    /**
     * Sets the scale factor of the third {@link CoordinateComponent}.
     *
     * @param value
     *            the scale factor of the third {@link CoordinateComponent}.
     */
    public abstract void setThirdComponentScale(final float value);


}
