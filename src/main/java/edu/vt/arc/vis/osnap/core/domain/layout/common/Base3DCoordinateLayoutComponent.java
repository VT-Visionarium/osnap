/*******************************************************************************
 * Copyright 2014 Virginia Tech Visionarium
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/


package edu.vt.arc.vis.osnap.core.domain.layout.common;


import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;

import java.util.EnumSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlType;


/**
 * The abstract {@link Base3DCoordinateLayoutComponent} class provides base
 * implementation of the {@link I3DCoordinateLayoutComponent} interface.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlType(name = "Base3DCoordinateLayoutComponent")
public abstract class Base3DCoordinateLayoutComponent
        extends BaseCoordinateLayoutComponent
        implements I3DCoordinateLayoutComponent {

    /**
     * Returns the name of this {@link ILayoutComponent}.
     * 
     * @return the name.
     */
    public static String name() {

        return "3D Coordinate Layout Component";
    }

    /**
     * Returns the description of this {@link ILayoutComponent}.
     * 
     * @return the description.
     */
    public static String description() {

        return "Provides coordinates for at most three of the X, Y, or Z "
                + "components of a node.";
    }


    /**
     * Returns the capabilities (the set of {@link VisualProperty
     * VisualProperties} that can be provided) of this {@link ILayoutComponent}.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return BaseCoordinateLayoutComponent.capabilities();
    }


    /**
     * Creates a new instance of the {@link Base3DCoordinateLayoutComponent}
     * class that routes the first {@link CoordinateComponent component} to the
     * x coordinate, the second {@link CoordinateComponent component} to the y
     * coordinate, and the third {@link CoordinateComponent component} to the z
     * coordinate.
     */
    public Base3DCoordinateLayoutComponent() {

        this(CoordinateComponent.FIRST_COMPONENT,
                CoordinateComponent.SECOND_COMPONENT,
                CoordinateComponent.THIRD_COMPONENT);
    }

    /**
     * Creates a new instance of the {@link Base3DCoordinateLayoutComponent}
     * class that routes the first {@link CoordinateComponent component} to the
     * x coordinate, the second {@link CoordinateComponent component} to the y
     * coordinate, and the third {@link CoordinateComponent component} to the z
     * coordinate. It sets the description and name to the provided values and
     * optionally appends it with a random number.
     * 
     * @param name
     *            the name of the layout component.
     * @param description
     *            the description of the layout component.
     * @param randomize
     *            whether or not to append a random number to the name.
     */
    public Base3DCoordinateLayoutComponent(final String name,
            final String description, final boolean randomize) {

        this(CoordinateComponent.FIRST_COMPONENT,
                CoordinateComponent.SECOND_COMPONENT,
                CoordinateComponent.THIRD_COMPONENT, name, description,
                randomize);
    }

    /**
     * Creates a new instance of the {@link Base3DCoordinateLayoutComponent}
     * class that routes the first {@link CoordinateComponent component} to the
     * x coordinate, the second {@link CoordinateComponent component} to the y
     * coordinate, and the third {@link CoordinateComponent component} to the z
     * coordinate. It sets the description and name to the provided values and
     * optionally appends it with a random number.
     * 
     * @param name
     *            the name of the layout component.
     * @param description
     *            the description of the layout component.
     * @param randomize
     *            whether or not to append a random number to the name.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    protected Base3DCoordinateLayoutComponent(final String name,
            final String description, final boolean randomize,
            boolean serialization) {

        this(CoordinateComponent.FIRST_COMPONENT,
                CoordinateComponent.SECOND_COMPONENT,
                CoordinateComponent.THIRD_COMPONENT, name, description,
                randomize, serialization);
    }

    /**
     * Creates a new instance of the {@link Base3DCoordinateLayoutComponent}
     * class with the provided routings.
     * 
     * @param xOutput
     *            the {@link CoordinateComponent component} to route to the x
     *            coordinate.
     * @param yOutput
     *            the {@link CoordinateComponent component} to route to the y
     *            coordinate.
     * @param zOutput
     *            the {@link CoordinateComponent component} to route to the z
     *            coordinate.
     */
    public Base3DCoordinateLayoutComponent(final CoordinateComponent xOutput,
            final CoordinateComponent yOutput, final CoordinateComponent zOutput) {

        this(xOutput, yOutput, zOutput, Base3DCoordinateLayoutComponent.name(),
                Base3DCoordinateLayoutComponent.description(), true);
    }

    /**
     * Creates a new instance of the {@link Base3DCoordinateLayoutComponent}
     * class with the provided routings. It sets the description and name to the
     * provided values and optionally appends it with a random number.
     * 
     * @param xOutput
     *            the {@link CoordinateComponent component} to route to the x
     *            coordinate.
     * @param yOutput
     *            the {@link CoordinateComponent component} to route to the y
     *            coordinate.
     * @param zOutput
     *            the {@link CoordinateComponent component} to route to the z
     *            coordinate.
     * @param name
     *            the name of the layout component.
     * @param description
     *            the description of the layout component.
     * @param randomize
     *            whether or not to append a random number to the name.
     */
    public Base3DCoordinateLayoutComponent(final CoordinateComponent xOutput,
            final CoordinateComponent yOutput,
            final CoordinateComponent zOutput, final String name,
            final String description, final boolean randomize) {

        this(xOutput, yOutput, zOutput, name, description, randomize, false);
    }

    /**
     * Creates a new instance of the {@link Base3DCoordinateLayoutComponent}
     * class with the provided routings. It sets the description and name to the
     * provided values and optionally appends it with a random number.
     * 
     * @param xOutput
     *            the {@link CoordinateComponent component} to route to the x
     *            coordinate.
     * @param yOutput
     *            the {@link CoordinateComponent component} to route to the y
     *            coordinate.
     * @param zOutput
     *            the {@link CoordinateComponent component} to route to the z
     *            coordinate.
     * @param name
     *            the name of the layout component.
     * @param description
     *            the description of the layout component.
     * @param randomize
     *            whether or not to append a random number to the name.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    protected Base3DCoordinateLayoutComponent(
            final CoordinateComponent xOutput,
            final CoordinateComponent yOutput,
            final CoordinateComponent zOutput, final String name,
            final String description, final boolean randomize,
            final boolean serialization) {

        super(EnumSet.of(CoordinateComponent.FIRST_COMPONENT,
                CoordinateComponent.SECOND_COMPONENT,
                CoordinateComponent.THIRD_COMPONENT), xOutput, yOutput,
                zOutput, name, description, randomize, serialization);
    }
}
