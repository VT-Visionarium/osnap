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


import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import org.jutility.math.arithmetics.ArithmeticOperations;
import org.jutility.math.vectorAlgebra.IPoint4;
import org.jutility.math.vectorAlgebra.Point4;


/**
 * The abstract {@code BaseCoordinateLayout} class provides common functionality
 * of all {@link ICoordinateLayout CoordinateLayouts}.
 * <p>
 * Notably, it provides the mechanics for routing (and scaling of the)
 * {@link CoordinateComponent OutputComponents} to x, y, or z coordinates.
 * 
 * @see ICoordinateLayout
 * @see BaseLayout
 * @see CoordinateComponent
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 */
@XmlType(name = "BaseCoordinateLayout")
@XmlAccessorType(XmlAccessType.NONE)
public abstract class BaseCoordinateLayout
        extends BaseLayout
        implements ICoordinateLayout {


    @XmlElementWrapper(name = "Outputs")
    @XmlElement(name = "Component")
    private final Set<CoordinateComponent> components;

    @XmlElement(name = "XCoordinateOutput")
    private CoordinateComponent            xOutput;
    @XmlElement(name = "YCoordinateOutput")
    private CoordinateComponent            yOutput;
    @XmlElement(name = "ZCoordinateOutput")
    private CoordinateComponent            zOutput;

    @XmlElement(name = "XCoordinateOutputScale")
    private float                          xOutputScale;
    @XmlElement(name = "YCoordinateOutputScale")
    private float                          yOutputScale;
    @XmlElement(name = "ZCoordinateOutputScale")
    private float                          zOutputScale;



    /**
     * Returns the name of this {@link ILayout}.
     * 
     * @return the name.
     */
    public static String name() {

        return "Coordinate Layout";
    }

    /**
     * Returns the description of this {@link ILayout}.
     * 
     * @return the description.
     */
    public static String description() {

        return "Provides coordinates for the X, Y, or Z component of a node.";
    }


    /**
     * Returns the capabilities (the set of
     * {@link edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty
     * VisualProperties} that can be provided) of this {@link ILayout}.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return EnumSet.of(VisualProperty.NODE_X_POSITION,
                VisualProperty.NODE_Y_POSITION, VisualProperty.NODE_Z_POSITION);
    }



    /**
     * Creates a new instance of the {@link BaseCoordinateLayout} class.
     * (Serialization Constructor).
     */
    @SuppressWarnings("unused")
    private BaseCoordinateLayout() {

        this(null, null, null, null, null, null, false, true);
    }

    /**
     * Creates a new instance of the {@link BaseCoordinateLayout} class with the
     * provided routing.
     * 
     * @param components
     *            the {@link CoordinateComponent components} this class
     *            provides.
     * @param xOutput
     *            specifies which {@link CoordinateComponent component} is to be
     *            used for the x coordinate.
     * @param yOutput
     *            specifies which {@link CoordinateComponent component} is to be
     *            used for the y coordinate.
     * @param zOutput
     *            specifies which {@link CoordinateComponent component} is to be
     *            used for the z coordinate.
     */
    public BaseCoordinateLayout(Set<CoordinateComponent> components,
            CoordinateComponent xOutput, CoordinateComponent yOutput,
            CoordinateComponent zOutput) {

        this(components, xOutput, yOutput, zOutput,
                BaseCoordinateLayout.name(),
                BaseCoordinateLayout.description(), true);
    }

    /**
     * Creates a new instance of the {@link BaseCoordinateLayout} class with the
     * provided routing. It sets the description and name to the provided values
     * and optionally appends it with a random number.
     * 
     * @param components
     *            the {@link CoordinateComponent components} this class
     *            provides.
     * @param xOutput
     *            specifies which {@link CoordinateComponent component} is to be
     *            used for the x coordinate.
     * @param yOutput
     *            specifies which {@link CoordinateComponent component} is to be
     *            used for the y coordinate.
     * @param zOutput
     *            specifies which {@link CoordinateComponent component} is to be
     *            used for the z coordinate.
     * @param name
     *            the name of the layout component.
     * @param description
     *            the description of the layout component.
     * @param randomize
     *            whether or not to append a random number to the name.
     */
    public BaseCoordinateLayout(Set<CoordinateComponent> components,
            CoordinateComponent xOutput, CoordinateComponent yOutput,
            CoordinateComponent zOutput, String name, String description,
            boolean randomize) {

        this(components, xOutput, yOutput, zOutput, name, description,
                randomize, false);
    }

    /**
     * Creates a new instance of the {@link BaseCoordinateLayout} class with the
     * provided routing. It sets the description and name to the provided values
     * and optionally appends it with a random number.
     * 
     * @param components
     *            the {@link CoordinateComponent components} this class
     *            provides.
     * @param xOutput
     *            specifies which {@link CoordinateComponent component} is to be
     *            used for the x coordinate.
     * @param yOutput
     *            specifies which {@link CoordinateComponent component} is to be
     *            used for the y coordinate.
     * @param zOutput
     *            specifies which {@link CoordinateComponent component} is to be
     *            used for the z coordinate.
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
    public BaseCoordinateLayout(Set<CoordinateComponent> components,
            CoordinateComponent xOutput, CoordinateComponent yOutput,
            CoordinateComponent zOutput, String name, String description,
            boolean randomize, boolean serialization) {

        super(BaseCoordinateLayout.capabilities(), name, description,
                randomize, serialization);

        if ((components == null || components.isEmpty() || components
                .contains(CoordinateComponent.NO_COMPONENT)) && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create coordinate provider that does not provide "
                            + "coordinate components!");
        }
        this.components = components;


        if (this.components != null) {

            if ((xOutput != CoordinateComponent.NO_COMPONENT && !this.components
                    .contains(xOutput))
                    || (yOutput != CoordinateComponent.NO_COMPONENT && !this.components
                            .contains(yOutput))
                    || (zOutput != CoordinateComponent.NO_COMPONENT && !this.components
                            .contains(zOutput))) {

                throw new IllegalArgumentException(
                        "Cannot establish the desired routings since at least one "
                                + "provided component does not exist in this "
                                + "provider.");
            }
        }

        this.xOutput = xOutput;
        this.yOutput = yOutput;
        this.zOutput = zOutput;

        this.xOutputScale = 1;
        this.yOutputScale = 1;
        this.zOutputScale = 1;
    }



    @Override
    public Set<CoordinateComponent> providesComponents() {

        return Collections.unmodifiableSet(this.components);
    }


    @Override
    public CoordinateComponent getXOutput() {

        return this.xOutput;
    }


    @Override
    public void setXOutput(CoordinateComponent component) {

        this.xOutput = component;
    }


    @Override
    public CoordinateComponent getYOutput() {

        return this.yOutput;
    }


    @Override
    public void setYOutput(CoordinateComponent component) {

        this.yOutput = component;
    }


    @Override
    public CoordinateComponent getZOutput() {

        return this.zOutput;
    }

    @Override
    public void setZOutput(CoordinateComponent component) {

        this.zOutput = component;
    }


    @Override
    public float getFirstComponentScale() {

        return this.xOutputScale;
    }

    @Override
    public void setFirstComponentScale(float value) {

        this.xOutputScale = value;
    }


    @Override
    public float getSecondComponentScale() {

        return this.yOutputScale;
    }

    @Override
    public void setSecondComponentScale(float value) {

        this.yOutputScale = value;
    }


    @Override
    public float getThirdComponentScale() {

        return this.zOutputScale;
    }

    public void setThirdComponentScale(float value) {

        this.zOutputScale = value;
    }


    @Override
    public void setValue(VisualNode visualNode, CoordinateComponent component,
            Number value) {

        BaseCoordinateLayout.setValue(this, visualNode, component, value);
    }

    @Override
    public Number getValue(VisualNode visualNode, CoordinateComponent component) {

        return BaseCoordinateLayout.getValue(this, visualNode, component);
    }

    /**
     * Sets the x, y, or z value of the visualNode based on the routing of the
     * CoordinateComponent based on settings in the provided ICoordinateLayout.
     * 
     * @param coordinateLayout
     *            the coordinate layout component
     * @param visualNode
     *            the visual node.
     * @param component
     *            the coordinate component to set the value of.
     * @param value
     *            the value to set.
     */
    public static void setValue(ICoordinateLayout coordinateLayout,
            VisualNode visualNode, CoordinateComponent component, Number value) {

        if (BaseCoordinateLayout.isEnabled(coordinateLayout, component)) {

            if (!coordinateLayout.isRestricted()
                    || coordinateLayout.getRestriction().contains(
                            visualNode.getNode())) {

                if (value == null) {

                    visualNode.setPosition(null);
                }
                else {

                    IPoint4<?> oldPosition = visualNode.getPosition();

                    if (oldPosition == null) {

                        oldPosition = new Point4<>(0, 0, 0, visualNode
                                .getVisualGraph().getVisualization()
                                .getPrecision());
                    }

                    Number x = oldPosition.getX();
                    Number y = oldPosition.getY();
                    Number z = oldPosition.getZ();

                    if (coordinateLayout.getXOutput() == component) {

                        x = value;
                    }
                    if (coordinateLayout.getYOutput() == component) {

                        y = value;
                    }
                    if (coordinateLayout.getZOutput() == component) {

                        z = value;
                    }

                    Point4<?> position = new Point4<>(x, y, z,
                            oldPosition.getType());

                    visualNode.setPosition(position);
                }
            }
        }
    }


    /**
     * Returns the value of a CoordinateComponent based on the routing in this
     * ICoordinateLayout in a provided visual node based on settings in the
     * provided ICoordinateLayout.
     * 
     * @param coordinateLayout
     *            the coordinate layout component
     * @param visualNode
     *            the visual node.
     * @param component
     *            the coordinate component of which to query the value.
     * @return the value of the CoordinateComponent in the visual node.
     */
    public static Number getValue(ICoordinateLayout coordinateLayout,
            VisualNode visualNode, CoordinateComponent component) {

        IPoint4<?> position = visualNode.getPosition();
        if (position == null) {

            return null;
        }


        if (coordinateLayout.getXOutput() == component) {

            return position.getX();
        }
        else if (coordinateLayout.getYOutput() == component) {

            return position.getY();
        }
        else if (coordinateLayout.getZOutput() == component) {

            return position.getZ();
        }
        else {

            return null;
        }
    }


    /**
     * Returns the scaled value for the provided {@link CoordinateComponent}.
     * 
     * @param coordinateLayout
     *            the {@link ICoordinateLayout} for which to perform the
     *            operation.
     * @param component
     *            the {@link CoordinateComponent}.
     * @param value
     *            the value to scale.
     * @return the scaled value for the provided {@link CoordinateComponent}.
     */
    public static Number scaleComponent(ICoordinateLayout coordinateLayout,
            CoordinateComponent component, Number value) {

        if (value != null && component != null && coordinateLayout != null) {

            switch (component) {

                case FIRST_COMPONENT:
                    return ArithmeticOperations.multiply(value,
                            coordinateLayout.getFirstComponentScale());
                case SECOND_COMPONENT:
                    return ArithmeticOperations.multiply(value,
                            coordinateLayout.getSecondComponentScale());
                case THIRD_COMPONENT:
                    return ArithmeticOperations.multiply(value,
                            coordinateLayout.getThirdComponentScale());
                case NO_COMPONENT:
                default:
                    break;

            }
        }

        return null;
    }


    /**
     * Determines whether the CoordinateComponent of the provided
     * CoordinateLayoutComponent is used for output.
     * 
     * @param coordinateLayout
     *            the ICoordinateLayout for which to check the output.
     * @param coordinateComponent
     *            the coordinate component to check.
     * @return <code>true</code>, if the CoordinateComponent is used;
     *         <code>false</code> otherwise.
     */
    public static boolean isEnabled(ICoordinateLayout coordinateLayout,
            CoordinateComponent coordinateComponent) {

        boolean isX = false;
        boolean isY = false;
        boolean isZ = false;

        if (coordinateLayout.getXOutput() == coordinateComponent) {

            isX = true;
        }
        if (coordinateLayout.getYOutput() == coordinateComponent) {

            isY = true;
        }
        if (coordinateLayout.getZOutput() == coordinateComponent) {

            isZ = true;
        }

        switch (coordinateComponent) {
            case FIRST_COMPONENT:
            case SECOND_COMPONENT:
            case THIRD_COMPONENT:
                if (isX) {

                    return coordinateLayout
                            .isEnabled(VisualProperty.NODE_X_POSITION);
                }
                if (isY) {

                    return coordinateLayout
                            .isEnabled(VisualProperty.NODE_Y_POSITION);
                }
                if (isZ) {

                    return coordinateLayout
                            .isEnabled(VisualProperty.NODE_Z_POSITION);
                }
                //$FALL-THROUGH$
            case NO_COMPONENT:
            default:
                return false;

        }
    }
}
