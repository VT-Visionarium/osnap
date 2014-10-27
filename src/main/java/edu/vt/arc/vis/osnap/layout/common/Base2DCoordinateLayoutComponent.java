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


package edu.vt.arc.vis.osnap.layout.common;


import edu.vt.arc.vis.osnap.visualization.VisualProperty;

import java.util.EnumSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlType;


/**
 * The abstract {@link Base2DCoordinateLayoutComponent} class provides a base
 * implementation of the {@link I2DCoordinateLayoutComponent} interface.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlType(name = "Base2DCoordinateLayoutComponent")
public abstract class Base2DCoordinateLayoutComponent
        extends BaseCoordinateLayoutComponent
        implements I2DCoordinateLayoutComponent {

    /**
     * Returns the name of this {@link ILayoutComponent}.
     * 
     * @return the name.
     */
    public static String name() {

        return "2D Coordinate Layout Component";
    }

    /**
     * Returns the description of this {@link ILayoutComponent}.
     * 
     * @return the description.
     */
    public static String description() {

        return "Provides coordinates for at most two of the X, Y, or Z "
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
     * Creates a new instance of the {@link Base2DCoordinateLayoutComponent}
     * class that routes the first {@link CoordinateComponent component} to the
     * x coordinate and the second {@link CoordinateComponent component} to the
     * y coordinate.
     */
    public Base2DCoordinateLayoutComponent() {

        this(CoordinateComponent.FIRST_COMPONENT,
                CoordinateComponent.SECOND_COMPONENT,
                CoordinateComponent.NO_COMPONENT);
    }

    /**
     * Creates a new instance of the {@link Base2DCoordinateLayoutComponent}
     * class that routes the first {@link CoordinateComponent component} to the
     * x coordinate and the second {@link CoordinateComponent component} to the
     * y coordinate. It sets the description and name to the provided values and
     * optionally appends it with a random number.
     * 
     * @param name
     *            the name of the {@link ILayoutComponent layout component}.
     * @param description
     *            the description of the {@link ILayoutComponent layout
     *            component}.
     * @param randomize
     *            whether or not to append a random number to the name.
     */
    public Base2DCoordinateLayoutComponent(String name, String description,
            boolean randomize) {

        this(CoordinateComponent.FIRST_COMPONENT,
                CoordinateComponent.SECOND_COMPONENT,
                CoordinateComponent.NO_COMPONENT, name, description, randomize);
    }


    /**
     * Creates a new instance of the {@link Base2DCoordinateLayoutComponent}
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
    public Base2DCoordinateLayoutComponent(CoordinateComponent xOutput,
            CoordinateComponent yOutput, CoordinateComponent zOutput) {

        this(xOutput, yOutput, zOutput, Base2DCoordinateLayoutComponent.name(),
                Base2DCoordinateLayoutComponent.description(), true);

    }

    /**
     * Creates a new instance of the {@link Base2DCoordinateLayoutComponent}
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
     *            the name of the {@link ILayoutComponent layout component}.
     * @param description
     *            the description of the {@link ILayoutComponent layout
     *            component}.
     * @param randomize
     *            whether or not to append a random number to the name.
     */
    public Base2DCoordinateLayoutComponent(CoordinateComponent xOutput,
            CoordinateComponent yOutput, CoordinateComponent zOutput,
            String name, String description, boolean randomize) {

        this(xOutput, yOutput, zOutput, name, description, randomize, false);

    }

    /**
     * Creates a new instance of the {@link Base2DCoordinateLayoutComponent}
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
     *            the name of the {@link ILayoutComponent layout component}.
     * @param description
     *            the description of the {@link ILayoutComponent layout
     *            component}.
     * @param randomize
     *            whether or not to append a random number to the name.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    protected Base2DCoordinateLayoutComponent(CoordinateComponent xOutput,
            CoordinateComponent yOutput, CoordinateComponent zOutput,
            String name, String description, boolean randomize,
            boolean serialization) {

        super(EnumSet.of(CoordinateComponent.FIRST_COMPONENT,
                CoordinateComponent.SECOND_COMPONENT), xOutput, yOutput,
                zOutput, name, description, randomize, serialization);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.BaseCoordinateLayoutComponent#setXOutput
     * (edu.vt.arc.vis.osnap.layout.common.CoordinateComponent)
     */
    @Override
    public void setXOutput(CoordinateComponent component) {

        switch (component) {
            case FIRST_COMPONENT:
            case SECOND_COMPONENT:
                super.setXOutput(component);
                break;
            default:
                super.setXOutput(CoordinateComponent.NO_COMPONENT);
                break;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.BaseCoordinateLayoutComponent#setYOutput
     * (edu.vt.arc.vis.osnap.layout.common.CoordinateComponent)
     */
    @Override
    public void setYOutput(CoordinateComponent component) {

        switch (component) {
            case FIRST_COMPONENT:
            case SECOND_COMPONENT:
                super.setYOutput(component);
                break;
            default:
                super.setYOutput(CoordinateComponent.NO_COMPONENT);
                break;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.BaseCoordinateLayoutComponent#setZOutput
     * (edu.vt.arc.vis.osnap.layout.common.CoordinateComponent)
     */
    @Override
    public void setZOutput(CoordinateComponent component) {

        switch (component) {
            case FIRST_COMPONENT:
            case SECOND_COMPONENT:
                super.setZOutput(component);
                break;
            default:
                super.setZOutput(CoordinateComponent.NO_COMPONENT);
                break;
        }
    }
}
