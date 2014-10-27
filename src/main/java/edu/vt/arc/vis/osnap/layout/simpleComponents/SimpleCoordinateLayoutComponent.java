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


package edu.vt.arc.vis.osnap.layout.simpleComponents;


import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.math.vectorAlgebra.IPoint4;
import org.jutility.math.vectorAlgebra.Point4;
import org.jutility.math.vectorAlgebra.Point4f;

import edu.vt.arc.vis.osnap.layout.common.Base3DCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.layout.common.BaseCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.layout.common.CoordinateComponent;
import edu.vt.arc.vis.osnap.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.visualization.VisualNode;
import edu.vt.arc.vis.osnap.visualization.VisualProperty;


/**
 * The <code>SimpleCoordinateLayoutComponent</code> class provides a basic
 * implementation of the
 * {@link edu.vt.arc.vis.osnap.layout.common.ICoordinateLayoutComponent} interface,
 * setting every node'scale {@link Point4f Position} to the provided position.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlType(name = "SimpleCoordinateLayoutComponent")
public class SimpleCoordinateLayoutComponent
        extends Base3DCoordinateLayoutComponent {

    @XmlElement(name = "Position", type = Point4.class)
    private IPoint4<?> position;



    /**
     * Returns the name of this <code>ILayoutComponent</code>.
     * 
     * @return the name.
     */
    public static String name() {

        return "Simple Coordinate Layout Component";
    }


    /**
     * Returns the description of this <code>ILayoutComponent</code>.
     * 
     * @return the description.
     */
    public static String description() {

        return "The " + SimpleCoordinateLayoutComponent.name()
                + " provides coordinates for nodes.\n";
    }


    /**
     * Returns the capabilities (the set of
     * {@link edu.vt.arc.vis.osnap.visualization.VisualProperty VisualProperties}
     * that can be provided) of this <code>ILayoutComponent</code>.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return BaseCoordinateLayoutComponent.capabilities();
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
    public void setPosition(Point4<?> position) {

        this.position = position;
    }


    /**
     * Creates a new instance of the
     * <code>SimpleCoordinateLayoutComponent</code> class.
     */
    public SimpleCoordinateLayoutComponent() {

        this(Point4f.ORIGIN);
    }


    /**
     * Creates a new instance of the
     * <code>SimpleCoordinateLayoutComponent</code> class.
     * 
     * @param position
     *            the intended position of the nodes.
     */
    public SimpleCoordinateLayoutComponent(Point4f position) {

        super(CoordinateComponent.FIRST_COMPONENT,
                CoordinateComponent.SECOND_COMPONENT,
                CoordinateComponent.THIRD_COMPONENT,
                SimpleCoordinateLayoutComponent.name(),
                SimpleCoordinateLayoutComponent.description(), false);

        this.position = position;
    }



    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap
     * .visualization.VisualNode)
     */
    @Override
    public void layout(VisualNode visualNode) {

        this.setValue(visualNode, CoordinateComponent.FIRST_COMPONENT,
                this.position.getX());
        this.setValue(visualNode, CoordinateComponent.SECOND_COMPONENT,
                this.position.getY());
        this.setValue(visualNode, CoordinateComponent.THIRD_COMPONENT,
                this.position.getZ());
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap
     * .visualization.VisualEdge)
     */
    @Override
    public void layout(VisualEdge visualEdge) {

        // Does not provide layout options for edges.
        throw new UnsupportedOperationException(
                "Coordinate Layout Components are not applicable to Edges!");
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap
     * .visualization.VisualHyperEdge)
     */
    @Override
    public void layout(VisualHyperEdge visualHyperEdge) {

        // Does not provide layout options for hyperedges.
        throw new UnsupportedOperationException(
                "Coordinate Layout Components are not applicable to Hyperedges!");
    }
}
