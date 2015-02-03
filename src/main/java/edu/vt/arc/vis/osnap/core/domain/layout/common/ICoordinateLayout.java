package edu.vt.arc.vis.osnap.core.domain.layout.common;


//@formatter:off
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
//@formatter:on



import java.util.Set;

import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;


/**
 * The {@code ICoordinateLayout} interface provides a contract for all
 * {@link ILayout ILayoutComponents} that can provide x, y, or z-coordinates.
 * <p>
 * It allows the routing of the coordinate components of the layout component to
 * the different coordinates of a node.
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 */
public interface ICoordinateLayout
        extends ILayout {


    /**
     * Returns the set of visual properties that the layout provider can
     * provide.
     * 
     * @return the set of visual properties provided by the layout provider.
     */
    public abstract Set<CoordinateComponent> providesComponents();


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


    /**
     * Sets the x, y, or z value of the visualNode based on the routing of the
     * CoordinateComponent.
     * 
     * @param visualNode
     *            the visual node.
     * @param component
     *            the coordinate component to set the value of.
     * @param value
     *            the value to set.
     */
    public abstract void setValue(VisualNode visualNode,
            CoordinateComponent component, Number value);

    /**
     * Returns the value of a CoordinateComponent based on the routing in this
     * ICoordinateLayout in a provided visual node.
     * 
     * @param visualNode
     *            the visual node.
     * @param component
     *            the coordinate component of which to query the value.
     * @return the value of the CoordinateComponent in the visual node.
     */
    public abstract Number getValue(VisualNode visualNode,
            CoordinateComponent component);
}
