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


package edu.vt.arc.vis.osnap.layout.prefuseComponents;


import java.util.Set;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.math.geometry.IRectangle2;
import org.jutility.math.geometry.Rectangle2;
import org.jutility.math.vectorAlgebra.IPoint2;
import org.jutility.math.vectorAlgebra.Point2;

import edu.vt.arc.vis.osnap.layout.common.Base2DCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.layout.common.CoordinateComponent;
import edu.vt.arc.vis.osnap.layout.common.ILayoutComponent;
import edu.vt.arc.vis.osnap.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.visualization.VisualGraph;
import edu.vt.arc.vis.osnap.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.visualization.VisualNode;
import edu.vt.arc.vis.osnap.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.visualization.Visualization;


/**
 * The abstract {@link BasePrefuseCoordinateLayoutComponent} class provides
 * common functionality of all @{link IPrefuseLayoutComponent
 * IPrefuseLayoutComponents} based on the {@link prefuse Prefuse} toolkit.
 * <p>
 * Notably, it provides the mechanics for setting the duration of a layout run,
 * as well as its anchor, bounds, and margins.
 *
 * @author Peter J. Radics
 * @version 0.1
 *
 */
@XmlType(name = "BasePrefuseCoordinateLayoutComponent")
public abstract class BasePrefuseCoordinateLayoutComponent
        extends Base2DCoordinateLayoutComponent
        implements IPrefuseLayoutComponent {

    @XmlElement(name = "Duration")
    private long                duration;

    @XmlElement(name = "LayoutAnchor", type = Point2.class)
    private IPoint2<Double>     layoutAnchor;

    @XmlElement(name = "LayoutBounds", type = Rectangle2.class)
    private IRectangle2<Double> layoutBounds;

    @XmlElement(name = "TopMargin")
    private int                 topMargin;

    @XmlElement(name = "BottomMargin")
    private int                 bottomMargin;

    @XmlElement(name = "LeftMargin")
    private int                 leftMargin;

    @XmlElement(name = "RightMargin")
    private int                 rightMargin;


    /**
     * Returns the name of this {@link ILayoutComponent}.
     *
     * @return the name.
     */
    public static String name() {

        return "Prefuse 2D Coordinate Layout Component";
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

        return Base2DCoordinateLayoutComponent.capabilities();
    }


    /**
     * Creates a new instance of the
     * {@link BasePrefuseCoordinateLayoutComponent} class. (Serialization
     * Constructor).
     */
    @SuppressWarnings("unused")
    private BasePrefuseCoordinateLayoutComponent() {

        this(null, null, false, true);
    }

    /**
     * Creates a new instance of the
     * {@link BasePrefuseCoordinateLayoutComponent} class. It sets the
     * description and name to the provided values and optionally appends it
     * with a random number.
     *
     * @param name
     *            the name of the layout component.
     * @param description
     *            the description of the layout component.
     * @param randomizeName
     *            whether or not to append a random number to the name.
     */
    public BasePrefuseCoordinateLayoutComponent(String name,
            String description, boolean randomizeName) {

        this(name, description, randomizeName, false);
    }

    /**
     * Creates a new instance of the
     * {@link BasePrefuseCoordinateLayoutComponent} class. It sets the
     * description and name to the provided values and optionally appends it
     * with a random number.
     *
     * @param name
     *            the name of the layout component.
     * @param description
     *            the description of the layout component.
     * @param randomizeName
     *            whether or not to append a random number to the name.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    protected BasePrefuseCoordinateLayoutComponent(String name,
            String description, boolean randomizeName, boolean serialization) {

        super(CoordinateComponent.FIRST_COMPONENT,
                CoordinateComponent.SECOND_COMPONENT,
                CoordinateComponent.NO_COMPONENT, name, description,
                randomizeName, serialization);

        this.duration = 0;
        this.layoutAnchor = new Point2<>(400, 300, Double.class);
        this.layoutBounds = new Rectangle2<>(new Point2<>(0, 0, Double.class),
                new Point2<>(800, 600, Double.class), Double.class);
        this.topMargin = 0;
        this.bottomMargin = 0;
        this.leftMargin = 0;
        this.rightMargin = 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap
     * .visualization.Visualization)
     */
    @Override
    public void layout(Visualization visualization) {

        PrefuseLayoutGenerator.Instance().layout(this, visualization);
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap
     * .visualization.VisualGraph)
     */
    @Override
    public void layout(VisualGraph graph) {

        // not used
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

        // not used.

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

        // not used.

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

        // not used.

    }


    /*
     * (non-Javadoc)
     *
     * @see
     * edu.vt.arc.vis.osnap.layout.prefuseComponents.IPrefuseLayoutComponent#setDuration
     * (long)
     */
    @Override
    public void setDuration(long duration) {

        this.duration = duration;
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * edu.vt.arc.vis.osnap.layout.prefuseComponents.IPrefuseLayoutComponent#getDuration
     * ()
     */
    @Override
    public long getDuration() {

        return this.duration;
    }


    /*
     * (non-Javadoc)
     *
     * @see edu.vt.arc.vis.osnap.layout.prefuseComponents.IPrefuseLayoutComponent#
     * setLayoutAnchor(double, double)
     */
    @Override
    public void setLayoutAnchor(double x, double y) {

        this.layoutAnchor = new Point2<>(x, y, Double.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.vt.arc.vis.osnap.layout.prefuseComponents.IPrefuseLayoutComponent#
     * getLayoutAnchor()
     */
    @Override
    public IPoint2<Double> getLayoutAnchor() {

        return this.layoutAnchor;
    }


    /*
     * (non-Javadoc)
     *
     * @see edu.vt.arc.vis.osnap.layout.prefuseComponents.IPrefuseLayoutComponent#
     * setLayoutBounds(double, double, double, double)
     */
    @Override
    public void setLayoutBounds(double x, double y, double width, double height) {

        IPoint2<Double> topLeftCorner = new Point2<>(x, y, Double.class);
        IPoint2<Double> bottomRightCorner = new Point2<>(x + width, y + height,
                Double.class);

        this.layoutBounds = new Rectangle2<>(topLeftCorner, bottomRightCorner,
                Double.class);


    }

    /*
     * (non-Javadoc)
     *
     * @see edu.vt.arc.vis.osnap.layout.prefuseComponents.IPrefuseLayoutComponent#
     * getLayoutBounds()
     */
    @Override
    public IRectangle2<Double> getLayoutBounds() {

        return this.layoutBounds;
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * edu.vt.arc.vis.osnap.layout.prefuseComponents.IPrefuseLayoutComponent#setMargin
     * (int, int, int, int)
     */
    @Override
    public void setMargin(int topMargin, int bottomMargin, int leftMargin,
            int rightMargin) {

        this.topMargin = topMargin;
        this.bottomMargin = bottomMargin;
        this.leftMargin = leftMargin;
        this.rightMargin = rightMargin;


    }


    /*
     * (non-Javadoc)
     *
     * @see
     * edu.vt.arc.vis.osnap.layout.prefuseComponents.IPrefuseLayoutComponent#getTopMargin
     * ()
     */
    @Override
    public int getTopMargin() {

        return this.topMargin;
    }


    /*
     * (non-Javadoc)
     *
     * @see edu.vt.arc.vis.osnap.layout.prefuseComponents.IPrefuseLayoutComponent#
     * getBottomMargin()
     */
    @Override
    public int getBottomMargin() {

        return this.bottomMargin;
    }


    /*
     * (non-Javadoc)
     *
     * @see edu.vt.arc.vis.osnap.layout.prefuseComponents.IPrefuseLayoutComponent#
     * getLeftMargin()
     */
    @Override
    public int getLeftMargin() {

        return this.leftMargin;
    }


    /*
     * (non-Javadoc)
     *
     * @see edu.vt.arc.vis.osnap.layout.prefuseComponents.IPrefuseLayoutComponent#
     * getRightMargin()
     */
    @Override
    public int getRightMargin() {

        return this.rightMargin;
    }

    @Override
    public void applyParameters() {

        if (this.getLayout() != null) {

            this.getLayout().setLayoutAnchor(
                    new Point2D.Double(this.getLayoutAnchor().getX(), this
                            .getLayoutAnchor().getY()));


            double x = this.getLayoutBounds().getTopLeftCorner().getX();
            double y = this.getLayoutBounds().getTopLeftCorner().getY();
            double x2 = this.getLayoutBounds().getBottomRightCorner().getX();
            double y2 = this.getLayoutBounds().getBottomRightCorner().getY();

            double width = x2 - x;
            double height = y2 - y;

            this.getLayout().setLayoutBounds(
                    new Rectangle2D.Double(x, y, width, height));


            this.getLayout().setMargin(topMargin, leftMargin, bottomMargin,
                    rightMargin);

            this.getLayout().setDuration(this.duration);
        }
    }
}
