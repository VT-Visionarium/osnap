package edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents;


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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.math.vectoralgebra.IPoint4;
import org.jutility.math.vectoralgebra.Point4;

import edu.vt.arc.vis.osnap.core.domain.layout.common.Base3DCoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.BaseCoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.CoordinateComponent;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;


/**
 * The {@code SimpleCoordinateLayout} class provides a basic implementation of
 * the {@link edu.vt.arc.vis.osnap.core.domain.layout.common.ICoordinateLayout}
 * interface, setting every node'scale {@link IPoint4 Position} to the provided
 * position.
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 */
@XmlType(name = "SimpleCoordinateLayout")
public class SimpleCoordinateLayout
        extends Base3DCoordinateLayout {

    @XmlElement(name = "Position", type = Point4.class)
    private IPoint4<?> position;



    /**
     * Returns the name of this {@code ILayout}.
     * 
     * @return the name.
     */
    public static String name() {

        return "Simple Coordinate Layout";
    }


    /**
     * Returns the description of this {@code ILayout}.
     * 
     * @return the description.
     */
    public static String description() {

        return "The " + SimpleCoordinateLayout.name()
                + " provides coordinates for nodes.\n";
    }


    /**
     * Returns the capabilities (the set of
     * {@link edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty
     * VisualProperties} that can be provided) of this {@code ILayout}.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return BaseCoordinateLayout.capabilities();
    }



    /**
     * Returns the position.
     * 
     * @return the position.
     */
    public IPoint4<?> getPosition() {

        return this.position;
    }


    /**
     * Sets the position.
     * 
     * @param position
     *            the position.
     */
    public void setPosition(IPoint4<?> position) {

        this.position = position;
    }


    /**
     * Creates a new instance of the {@code SimpleCoordinateLayout} class.
     */
    public SimpleCoordinateLayout() {

        this(Point4.ORIGIN(Float.class));
    }


    /**
     * Creates a new instance of the {@code SimpleCoordinateLayout} class.
     * 
     * @param position
     *            the intended position of the nodes.
     */
    public SimpleCoordinateLayout(IPoint4<?> position) {

        super(CoordinateComponent.FIRST_COMPONENT,
                CoordinateComponent.SECOND_COMPONENT,
                CoordinateComponent.THIRD_COMPONENT, SimpleCoordinateLayout
                        .name(), SimpleCoordinateLayout.description(), false);

        this.position = position;
    }



    @Override
    public void layout(VisualNode visualNode) {

        this.setValue(visualNode, CoordinateComponent.FIRST_COMPONENT,
                this.position.getX());
        this.setValue(visualNode, CoordinateComponent.SECOND_COMPONENT,
                this.position.getY());
        this.setValue(visualNode, CoordinateComponent.THIRD_COMPONENT,
                this.position.getZ());
    }


    @Override
    public void layout(VisualEdge visualEdge) {

        // Does not provide layout options for edges.
        throw new UnsupportedOperationException(
                "Coordinate Layouts are not applicable to Edges!");
    }


    @Override
    public void layout(VisualHyperEdge visualHyperEdge) {

        // Does not provide layout options for hyperedges.
        throw new UnsupportedOperationException(
                "Coordinate Layouts are not applicable to Hyperedges!");
    }
}
