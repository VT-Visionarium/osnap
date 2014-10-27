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


package graphVisualizer.layout.common;


import graphVisualizer.visualization.VisualNode;
import graphVisualizer.visualization.VisualProperty;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import org.jutility.math.vectorAlgebra.IPoint4;
import org.jutility.math.vectorAlgebra.Point4;


/**
 * The abstract {@link BaseCoordinateLayoutComponent} class provides common
 * functionality of all @{link ICoordinateLayoutComponent coordinate layout
 * components}.
 * <p>
 * Notably, it provides the mechanics for routing {@link CoordinateComponent
 * output components} to x, y, or z coordinates.
 * 
 * @see ICoordinateLayoutComponent
 * @see BaseLayoutComponent
 * @see CoordinateComponent
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlType(name = "BaseCoordinateLayoutComponent")
@XmlAccessorType(XmlAccessType.NONE)
public abstract class BaseCoordinateLayoutComponent
        extends BaseLayoutComponent
        implements ICoordinateLayoutComponent {

    @XmlElementWrapper(name = "Outputs")
    @XmlElement(name = "Component")
    private final Set<CoordinateComponent> components;

    @XmlElement(name = "XCoordinateOutput")
    private CoordinateComponent            xOutput;
    @XmlElement(name = "YCoordinateOutput")
    private CoordinateComponent            yOutput;
    @XmlElement(name = "ZCoordinateOutput")
    private CoordinateComponent            zOutput;


    /**
     * Returns the name of this {@link ILayoutComponent}.
     * 
     * @return the name.
     */
    public static String name() {

        return "Coordinate Layout Component";
    }


    /**
     * Returns the description of this {@link ILayoutComponent}.
     * 
     * @return the description.
     */
    public static String description() {

        return "Provides coordinates for the X, Y, or Z component of a node.";
    }


    /**
     * Returns the capabilities (the set of
     * {@link graphVisualizer.visualization.VisualProperty VisualProperties}
     * that can be provided) of this {@link ILayoutComponent}.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return EnumSet.of(VisualProperty.NODE_X_POSITION,
                VisualProperty.NODE_Y_POSITION, VisualProperty.NODE_Z_POSITION);
    }


    /**
     * Creates a new instance of the {@link BaseCoordinateLayoutComponent}
     * class. (Serialization Constructor).
     */
    @SuppressWarnings("unused")
    private BaseCoordinateLayoutComponent() {

        this(null, null, null, null, null, null, false, true);
    }

    /**
     * Creates a new instance of the {@link BaseCoordinateLayoutComponent} class
     * with the provided routing.
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
    public BaseCoordinateLayoutComponent(Set<CoordinateComponent> components,
            CoordinateComponent xOutput, CoordinateComponent yOutput,
            CoordinateComponent zOutput) {

        this(components, xOutput, yOutput, zOutput,
                BaseCoordinateLayoutComponent.name(),
                BaseCoordinateLayoutComponent.description(), true);
    }


    /**
     * Creates a new instance of the {@link BaseCoordinateLayoutComponent} class
     * with the provided routing. It sets the description and name to the
     * provided values and optionally appends it with a random number.
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
    public BaseCoordinateLayoutComponent(Set<CoordinateComponent> components,
            CoordinateComponent xOutput, CoordinateComponent yOutput,
            CoordinateComponent zOutput, String name, String description,
            boolean randomize) {

        this(components, xOutput, yOutput, zOutput, name, description,
                randomize, false);
    }

    /**
     * Creates a new instance of the {@link BaseCoordinateLayoutComponent} class
     * with the provided routing. It sets the description and name to the
     * provided values and optionally appends it with a random number.
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
    public BaseCoordinateLayoutComponent(Set<CoordinateComponent> components,
            CoordinateComponent xOutput, CoordinateComponent yOutput,
            CoordinateComponent zOutput, String name, String description,
            boolean randomize, boolean serialization) {

        super(BaseCoordinateLayoutComponent.capabilities(), name, description,
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
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * graphVisualizer.layout.complexComponents.common.ICoordinateLayoutComponent
     * #providesComponents()
     */
    @Override
    public Set<CoordinateComponent> providesComponents() {

        return Collections.unmodifiableSet(this.components);
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * graphVisualizer.layout.complexComponents.common.ICoordinateLayoutComponent
     * #getXOutput()
     */
    @Override
    public CoordinateComponent getXOutput() {

        return this.xOutput;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * graphVisualizer.layout.complexComponents.common.ICoordinateLayoutComponent
     * #setXOutput(graphVisualizer .layout.common.OutputComponent)
     */
    @Override
    public void setXOutput(CoordinateComponent component) {

        this.xOutput = component;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * graphVisualizer.layout.complexComponents.common.ICoordinateLayoutComponent
     * #getYOutput()
     */
    @Override
    public CoordinateComponent getYOutput() {

        return this.yOutput;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * graphVisualizer.layout.complexComponents.common.ICoordinateLayoutComponent
     * #setYOutput(graphVisualizer .layout.common.OutputComponent)
     */
    @Override
    public void setYOutput(CoordinateComponent component) {

        this.yOutput = component;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * graphVisualizer.layout.complexComponents.common.ICoordinateLayoutComponent
     * #getZOutput()
     */
    @Override
    public CoordinateComponent getZOutput() {

        return this.zOutput;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * graphVisualizer.layout.complexComponents.common.ICoordinateLayoutComponent
     * #setZOutput(graphVisualizer .layout.common.OutputComponent)
     */
    @Override
    public void setZOutput(CoordinateComponent component) {

        this.zOutput = component;
    }


    @Override
    public void setValue(VisualNode visualNode, CoordinateComponent component,
            Number value) {

        BaseCoordinateLayoutComponent.setValue(this, visualNode, component,
                value);
    }

    @Override
    public Number getValue(VisualNode visualNode, CoordinateComponent component) {

        return BaseCoordinateLayoutComponent.getValue(this, visualNode,
                component);
    }

    /**
     * Sets the x, y, or z value of the visualNode based on the routing of the
     * CoordinateComponent based on settings in the provided
     * ICoordinateLayoutComponent.
     * 
     * @param coordinateLayoutComponent
     *            the coordinate layout component
     * @param visualNode
     *            the visual node.
     * @param component
     *            the coordinate component to set the value of.
     * @param value
     *            the value to set.
     */
    public static void setValue(
            ICoordinateLayoutComponent coordinateLayoutComponent,
            VisualNode visualNode, CoordinateComponent component, Number value) {

        if (BaseCoordinateLayoutComponent.isEnabled(coordinateLayoutComponent,
                component)) {

            if (!coordinateLayoutComponent.isRestricted()
                    || coordinateLayoutComponent.getRestriction().contains(
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

                    if (coordinateLayoutComponent.getXOutput() == component) {
                        x = value;
                    }
                    if (coordinateLayoutComponent.getYOutput() == component) {
                        y = value;
                    }
                    if (coordinateLayoutComponent.getZOutput() == component) {
                        z = value;
                    }

                    Point4<?> position = new Point4<>(x, y, z,
                            oldPosition.getType());

                    visualNode.setPosition(position);
                }
            }
            else {

            }
        }
        else {
        }
    }


    /**
     * Returns the value of a CoordinateComponent based on the routing in this
     * ICoordinateLayoutComponent in a provided visual node based on settings in
     * the provided ICoordinateLayoutComponent.
     * 
     * @param coordinateLayoutComponent
     *            the coordinate layout component
     * @param visualNode
     *            the visual node.
     * @param component
     *            the coordinate component of which to query the value.
     * @return the value of the CoordinateComponent in the visual node.
     */
    public static Number getValue(
            ICoordinateLayoutComponent coordinateLayoutComponent,
            VisualNode visualNode, CoordinateComponent component) {

        IPoint4<?> position = visualNode.getPosition();
        if (position == null) {
            return null;
        }


        if (coordinateLayoutComponent.getXOutput() == component) {
            return position.getX();
        }
        else if (coordinateLayoutComponent.getYOutput() == component) {
            return position.getY();
        }
        else if (coordinateLayoutComponent.getZOutput() == component) {
            return position.getZ();
        }
        else {
            return null;
        }
    }


    /**
     * Determines whether the CoordinateComponent of the provided
     * CoordinateLayoutComponent is used for output.
     * 
     * @param coordinateLayoutComponent
     *            the ICoordinateLayoutComponent for which to check the output.
     * @param coordinateComponent
     *            the coordinate component to check.
     * @return <code>true</code>, if the CoordinateComponent is used;
     *         <code>false</code> otherwise.
     */
    public static boolean isEnabled(
            ICoordinateLayoutComponent coordinateLayoutComponent,
            CoordinateComponent coordinateComponent) {

        boolean isX = false;
        boolean isY = false;
        boolean isZ = false;

        if (coordinateLayoutComponent.getXOutput() == coordinateComponent) {

            isX = true;
        }
        if (coordinateLayoutComponent.getYOutput() == coordinateComponent) {

            isY = true;
        }
        if (coordinateLayoutComponent.getZOutput() == coordinateComponent) {

            isZ = true;
        }

        switch (coordinateComponent) {
        case FIRST_COMPONENT:
        case SECOND_COMPONENT:
        case THIRD_COMPONENT:
            if (isX) {

                return coordinateLayoutComponent
                        .isEnabled(VisualProperty.NODE_X_POSITION);
            }
            if (isY) {

                return coordinateLayoutComponent
                        .isEnabled(VisualProperty.NODE_Y_POSITION);
            }
            if (isZ) {

                return coordinateLayoutComponent
                        .isEnabled(VisualProperty.NODE_Z_POSITION);
            }
            //$FALL-THROUGH$
        case NO_COMPONENT:
        default:
            return false;

        }
    }
}
