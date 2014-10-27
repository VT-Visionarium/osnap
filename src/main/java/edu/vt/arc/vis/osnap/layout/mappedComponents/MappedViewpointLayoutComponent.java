/**
 * 
 */
package edu.vt.arc.vis.osnap.layout.mappedComponents;


import edu.vt.arc.vis.osnap.layout.common.BaseLayoutComponent;
import edu.vt.arc.vis.osnap.visualization.Viewpoint;
import edu.vt.arc.vis.osnap.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.visualization.VisualNode;
import edu.vt.arc.vis.osnap.visualization.VisualObject;
import edu.vt.arc.vis.osnap.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.visualization.Visualization;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.math.geometry.IRotation;
import org.jutility.math.geometry.Rotation;
import org.jutility.math.vectorAlgebra.IMatrix4;
import org.jutility.math.vectorAlgebra.IPoint4;
import org.jutility.math.vectorAlgebra.IVector4;
import org.jutility.math.vectorAlgebra.Matrix4;
import org.jutility.math.vectorAlgebra.Point4;
import org.jutility.math.vectorAlgebra.Vector4;
import org.jutility.math.vectorAlgebra.VectorAlgebraicOperations;


/**
 * @author Peter J. Radics
 * @version 1.0
 * @since 1.0
 * 
 */

@XmlType(name = "MappedViewpointLayoutComponent")
public class MappedViewpointLayoutComponent
        extends BaseLayoutComponent {

    @XmlElement(name = "Offset", type = Vector4.class)
    private IVector4<Float> offset;

    public static String description() {

        String description = "The "
                + MappedViewpointLayoutComponent.name()
                + " provides a viewpoint into the visualization based on an "
                + "offset from a Visual Node or Visual Edge.\n\nNeeds to be applied"
                + "as the last step!";

        return description;
    }

    public static String name() {

        return "Mapped Viewpoint Layout Component";
    }


    public static Set<VisualProperty> capabilities() {

        HashSet<VisualProperty> set = new HashSet<>();
        set.add(VisualProperty.UNIVERSE_VIEWPOINT);
        return set;
    }


    /**
     * @return the offset
     */
    public IVector4<Float> getOffset() {

        return offset;
    }


    /**
     * @param offset
     *            the offset to set
     */
    public void setOffset(IVector4<Float> offset) {

        this.offset = offset;
    }

    /**
     * 
     */
    public MappedViewpointLayoutComponent() {

        super(MappedViewpointLayoutComponent.capabilities(),
                MappedViewpointLayoutComponent.name(),
                MappedViewpointLayoutComponent.description(), true);

        this.offset = new Vector4<>(0, 0, 20, Float.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.BaseLayoutComponent#layout(edu.vt.arc.vis.osnap
     * .visualization.Visualization)
     */
    @Override
    public void layout(Visualization visualization) {

        Set<VisualNode> visualNodes = BaseLayoutComponent
                .calculateRestrictedSet(this, visualization.getVisualNodes());
        Set<VisualEdge> visualEdges = BaseLayoutComponent
                .calculateRestrictedSet(this, visualization.getVisualEdges());


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


        IMatrix4<Float> w = new Matrix4<>(r, u, n,
                Point4.ORIGIN(Float.class), Float.class);


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

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap
     * .visualization.VisualNode)
     */
    @Override
    public void layout(VisualNode visualNode) {

        // Nothing to do

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

        // Nothing to do

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

        // Nothing to do

    }



}
