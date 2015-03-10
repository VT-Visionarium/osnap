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


import edu.vt.arc.vis.osnap.core.domain.layout.common.BaseLayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.Viewpoint;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualObject;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.math.geometry.IRotation;
import org.jutility.math.geometry.Rotation;
import org.jutility.math.vectoralgebra.IMatrix4;
import org.jutility.math.vectoralgebra.IPoint4;
import org.jutility.math.vectoralgebra.IVector4;
import org.jutility.math.vectoralgebra.Matrix4;
import org.jutility.math.vectoralgebra.Point4;
import org.jutility.math.vectoralgebra.Vector4;
import org.jutility.math.vectoralgebra.VectorAlgebraicOperations;


/**
 * The {@code SimpleViewpointLayout} class provides a view point onto an edge or
 * node based on an offset.
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 1.0.0
 */

@XmlType(name = "SimpleViewpointLayout")
public class SimpleViewpointLayout
        extends BaseLayout {

    @XmlElement(name = "Offset", type = Vector4.class)
    private IVector4<Float> offset;

    public static String description() {

        String description = "The "
                + SimpleViewpointLayout.name()
                + " provides a viewpoint into the visualization based on an "
                + "offset from a Visual Node or Visual Edge.\n\nNeeds to be applied"
                + "as the last step!";

        return description;
    }

    public static String name() {

        return "Simple Viewpoint Layout";
    }


    public static Set<VisualProperty> capabilities() {

        HashSet<VisualProperty> set = new HashSet<>();
        set.add(VisualProperty.UNIVERSE_VIEWPOINT);
        return set;
    }


    /**
     * Returns the {@link IVector4 Offset}.
     * 
     * @return the {@link IVector4 Offset}.
     */
    public IVector4<Float> getOffset() {

        return offset;
    }


    /**
     * Sets the {@link IVector4 Offset}.
     * 
     * @param offset
     *            the {@link IVector4 Offset}.
     */
    public void setOffset(IVector4<Float> offset) {

        this.offset = offset;
    }


    /**
     * Creates a new instance of the {@code SimpleViewpointLayout} class.
     */
    public SimpleViewpointLayout() {

        this(new Vector4<>(0, 0, 20, Float.class));
    }


    /**
     * Creates a new instance of the {@code SimpleViewpointLayout} class with
     * the provided {@link IVector4 Offset}.
     * 
     * @param offset
     *            the {@link IVector4 Offset}.
     */
    public SimpleViewpointLayout(final IVector4<Float> offset) {

        super(SimpleViewpointLayout.capabilities(), SimpleViewpointLayout
                .name(), SimpleViewpointLayout.description(), true);

        this.offset = offset;
    }

    @Override
    public void layout(Visualization visualization) {

        Set<VisualNode> visualNodes = BaseLayout.calculateRestrictedSet(this,
                visualization.getVisualNodes());
        Set<VisualEdge> visualEdges = BaseLayout.calculateRestrictedSet(this,
                visualization.getVisualEdges());


        for (VisualNode node : visualNodes) {

            this.calculateViewpoint(visualization, node);
        }

        for (VisualEdge edge : visualEdges) {

            this.calculateViewpoint(visualization, edge);
        }

    }

    private void calculateViewpoint(Visualization visualization,
            VisualObject visualObject) {

        IPoint4<?> lookAt = visualObject.getPosition();


        IPoint4<Float> position = VectorAlgebraicOperations.add(lookAt,
                this.getOffset(), Float.class);

        IVector4<Float> up = Vector4.J_UNIT_VECTOR(Float.class);

        IVector4<Float> n = VectorAlgebraicOperations.subtract(position,
                lookAt, Float.class);

        n = n.normalizedVector();

        if (n.equals(up)) {

            up = new Vector4<>(0, 0, -1, Float.class);
        }

        IVector4<Float> r = up.crossProduct(n);
        r = r.normalizedVector();

        IVector4<Float> u = n.crossProduct(r);

        u = u.normalizedVector();


        IMatrix4<Float> w = new Matrix4<>(r, u, n, Point4.ORIGIN(Float.class),
                Float.class);


        IMatrix4<Float> v = w.transpose();

        IRotation<?> rotation = null;
        if (v.getI().equals(Vector4.I_UNIT_VECTOR(Float.class))
                && v.getJ().equals(Vector4.J_UNIT_VECTOR(Float.class))
                && v.getK().equals(Vector4.K_UNIT_VECTOR(Float.class))
                && v.getS().equals(Point4.ORIGIN(Float.class))) {

            rotation = new Rotation<>(0, 0, 1, 0, Float.class);
        }

        else {
            float sumRminus2 = v.getI().getX() + v.getJ().getY()
                    + v.getK().getZ() - 1.0f;


            float halfThat = sumRminus2 / 2f;

            Double beta = Math.acos(halfThat);

            Double ax = (v.getK().getY() - v.getJ().getZ())
                    / (2 * Math.sin(beta));
            Double ay = (v.getI().getZ() - v.getK().getX())
                    / (2 * Math.sin(beta));
            Double az = (v.getJ().getX() - v.getI().getY())
                    / (2 * Math.sin(beta));

            Vector4<Float> axis = new Vector4<>(ax, ay, az, Float.class);

            axis = axis.normalizedVector();

            rotation = new Rotation<>(axis, beta, Float.class);
        }
        Viewpoint viewpoint = new Viewpoint("Looking at "
                + visualObject.getGraphObjectID(), Float.class);

        viewpoint.setLookAt(lookAt);
        viewpoint.setPosition(position);
        viewpoint.setRotation(rotation);

        visualization.addViewpoint(viewpoint);
    }

    @Override
    public void layout(VisualNode visualNode) {

        // Nothing to do

    }

    @Override
    public void layout(VisualEdge visualEdge) {

        // Nothing to do

    }

    @Override
    public void layout(VisualHyperEdge visualHyperEdge) {

        // Nothing to do

    }



}
