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
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;

import java.util.EnumSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlType;


/**
 * The abstract {@link Base1DCoordinateLayout} class provides base
 * implementation of the {@link I1DCoordinateLayout} interface.
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 */
@XmlType(name = "Base1DCoordinateLayout")
public abstract class Base1DCoordinateLayout
        extends BaseCoordinateLayout
        implements I1DCoordinateLayout {

    /**
     * Returns the name of this {@link ILayout}.
     * 
     * @return the name.
     */
    public static String name() {

        return "1D Coordinate Layout";
    }


    /**
     * Returns the description of this {@link ILayout}.
     * 
     * @return the description.
     */
    public static String description() {

        return "Provides coordinates for at most one of the X, Y, or Z "
                + "components of a node.";
    }


    /**
     * Returns the capabilities (the set of {@link VisualProperty
     * VisualProperties} that can be provided) of this {@link ILayout}.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return BaseCoordinateLayout.capabilities();
    }

    /**
     * Returns the coordinate components (the set of {@link CoordinateComponent
     * CoordinateComponents} that can be provided) by this
     * {@link ICoordinateLayout}.
     * 
     * @return the components.
     */
    public static Set<CoordinateComponent> components() {

        return EnumSet.of(CoordinateComponent.FIRST_COMPONENT);
    }

    /**
     * Creates a new instance of the {@link Base1DCoordinateLayout} class that
     * routes the first {@link CoordinateComponent component} to the x
     * coordinate.
     */
    protected Base1DCoordinateLayout() {

        this(CoordinateComponent.FIRST_COMPONENT,
                CoordinateComponent.NO_COMPONENT,
                CoordinateComponent.NO_COMPONENT);
    }


    /**
     * Creates a new instance of the {@link Base1DCoordinateLayout} class that
     * routes the first {@link CoordinateComponent component} to the x
     * coordinate and sets the description and name to the provided values and
     * optionally appends it with a random number.
     * 
     * @param name
     *            the name of the {@link ILayout layout component}.
     * @param description
     *            the description of the {@link ILayout layout component}.
     * @param randomize
     *            whether or not to append a random number to the name.
     */
    public Base1DCoordinateLayout(final String name, final String description,
            final boolean randomize) {

        this(CoordinateComponent.FIRST_COMPONENT,
                CoordinateComponent.NO_COMPONENT,
                CoordinateComponent.NO_COMPONENT, name, description, randomize);
    }


    /**
     * Creates a new instance of the {@link Base1DCoordinateLayout} class with
     * the provided routings.
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
    public Base1DCoordinateLayout(final CoordinateComponent xOutput,
            final CoordinateComponent yOutput, final CoordinateComponent zOutput) {

        this(xOutput, yOutput, zOutput, Base1DCoordinateLayout.name(),
                Base1DCoordinateLayout.description(), true);

    }


    /**
     * Creates a new instance of the {@link Base1DCoordinateLayout} class with
     * the provided routings. It sets the description and name to the provided
     * values and optionally appends it with a random number.
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
     *            the name of the {@link ILayout layout component}.
     * @param description
     *            the description of the {@link ILayout layout component}.
     * @param randomize
     *            whether or not to append a random number to the name.
     */
    public Base1DCoordinateLayout(final CoordinateComponent xOutput,
            final CoordinateComponent yOutput,
            final CoordinateComponent zOutput, final String name,
            final String description, final boolean randomize) {

        this(xOutput, yOutput, zOutput, name, description, randomize, false);

    }

    /**
     * Creates a new instance of the {@link Base1DCoordinateLayout} class with
     * the provided routings. It sets the description and name to the provided
     * values and optionally appends it with a random number.
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
    protected Base1DCoordinateLayout(final CoordinateComponent xOutput,
            final CoordinateComponent yOutput,
            final CoordinateComponent zOutput, final String name,
            final String description, final boolean randomize,
            final boolean serialization) {

        super(Base1DCoordinateLayout.components(), xOutput, yOutput, zOutput,
                name, description, randomize, serialization);

        this.setXOutput(CoordinateComponent.NO_COMPONENT);
    }


    @Override
    public void setXOutput(CoordinateComponent component) {

        switch (component) {
            case FIRST_COMPONENT:
                super.setXOutput(component);
                break;
            default:
                super.setXOutput(CoordinateComponent.NO_COMPONENT);
                break;
        }
    }


    @Override
    public void setYOutput(CoordinateComponent component) {

        switch (component) {
            case FIRST_COMPONENT:
                super.setYOutput(component);
                break;
            default:
                super.setYOutput(CoordinateComponent.NO_COMPONENT);
                break;
        }
    }


    @Override
    public void setZOutput(CoordinateComponent component) {

        switch (component) {
            case FIRST_COMPONENT:
                super.setZOutput(component);
                break;
            default:
                super.setZOutput(CoordinateComponent.NO_COMPONENT);
                break;
        }
    }

    @Override
    public void enable(VisualProperty visualProperty) {

        super.enable(visualProperty);

        switch (visualProperty) {
            case NODE_X_POSITION:
                this.setXOutput(CoordinateComponent.FIRST_COMPONENT);
                break;
            case NODE_Y_POSITION:
                this.setYOutput(CoordinateComponent.FIRST_COMPONENT);
                break;
            case NODE_Z_POSITION:
                this.setZOutput(CoordinateComponent.FIRST_COMPONENT);
                break;
            default:
                break;
        }
    }

    @Override
    public void disable(VisualProperty visualProperty) {

        super.disable(visualProperty);

        switch (visualProperty) {
            case NODE_X_POSITION:
                this.setXOutput(CoordinateComponent.NO_COMPONENT);
                break;
            case NODE_Y_POSITION:
                this.setYOutput(CoordinateComponent.NO_COMPONENT);
                break;
            case NODE_Z_POSITION:
                this.setZOutput(CoordinateComponent.NO_COMPONENT);
                break;
            default:
                break;
        }
    }
}
