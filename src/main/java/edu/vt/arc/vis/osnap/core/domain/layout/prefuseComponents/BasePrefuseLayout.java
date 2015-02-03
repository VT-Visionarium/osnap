package edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents;


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

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.math.geometry.IRectangle2;
import org.jutility.math.geometry.Rectangle2;
import org.jutility.math.vectorAlgebra.IPoint2;
import org.jutility.math.vectorAlgebra.Point2;

import edu.vt.arc.vis.osnap.core.domain.layout.common.Base2DCoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.CoordinateComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ICoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualGraph;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;


/**
 * The abstract {@code BasePrefuseLayout} class provides common functionality of
 * all @{link IPrefuseLayout IPrefuseLayoutComponents} based on the
 * {@link prefuse Prefuse} toolkit.
 * <p>
 * Notably, it provides the mechanics for setting the duration of a layout run,
 * as well as its anchor, bounds, and margins.
 *
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 */
@XmlType(name = "BasePrefuseLayout")
public abstract class BasePrefuseLayout
extends Base2DCoordinateLayout
implements IPrefuseLayout {

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
     * Returns the name of this {@link ILayout}.
     *
     * @return the name.
     */
    public static String name() {

        return "Prefuse 2D Coordinate Layout";
    }


    /**
     * Returns the description of this {@link ILayout}.
     *
     * @return the description.
     */
    public static String description() {

        return "Provides coordinates for at most two of the X, Y, or Z "
                + "components of a node.";
    }


    /**
     * Returns the capabilities (the set of {@link VisualProperty
     * VisualProperties} that can be provided) of this {@link ILayout}.
     *
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return Base2DCoordinateLayout.capabilities();
    }

    /**
     * Returns the coordinate components (the set of {@link CoordinateComponent
     * CoordinateComponents} that can be provided) by this
     * {@link ICoordinateLayout}.
     *
     * @return the components.
     */
    public static Set<CoordinateComponent> components() {

        return Base2DCoordinateLayout.components();
    }

    /**
     * Creates a new instance of the {@link BasePrefuseLayout} class.
     * (Serialization Constructor).
     */
    @SuppressWarnings("unused")
    private BasePrefuseLayout() {

        this(null, null, false, true);
    }

    /**
     * Creates a new instance of the {@link BasePrefuseLayout} class. It sets
     * the description and name to the provided values and optionally appends it
     * with a random number.
     *
     * @param name
     *            the name of the layout component.
     * @param description
     *            the description of the layout component.
     * @param randomizeName
     *            whether or not to append a random number to the name.
     */
    public BasePrefuseLayout(final String name, final String description,
            final boolean randomizeName) {

        this(name, description, randomizeName, false);
    }

    /**
     * Creates a new instance of the {@link BasePrefuseLayout} class. It sets
     * the description and name to the provided values and optionally appends it
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
    protected BasePrefuseLayout(final String name, final String description,
            final boolean randomizeName, final boolean serialization) {

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

    @Override
    public void layout(final Visualization visualization) {

        PrefuseLayoutGenerator.Instance().layout(this, visualization);
    }


    @Override
    public void layout(final VisualGraph graph) {

        // not used
    }


    @Override
    public void layout(final VisualNode visualNode) {

        // not used.

    }


    @Override
    public void layout(final VisualEdge visualEdge) {

        // not used.

    }


    @Override
    public void layout(final VisualHyperEdge visualHyperEdge) {

        // not used.

    }


    @Override
    public void setDuration(final long duration) {

        this.duration = duration;
    }


    @Override
    public long getDuration() {

        return this.duration;
    }


    @Override
    public void setLayoutAnchor(final double x, final double y) {

        this.layoutAnchor = new Point2<>(x, y, Double.class);
    }

    @Override
    public IPoint2<Double> getLayoutAnchor() {

        return this.layoutAnchor;
    }


    @Override
    public void setLayoutBounds(final double x, final double y,
            final double width, final double height) {

        final IPoint2<Double> topLeftCorner = new Point2<>(x, y, Double.class);
        final IPoint2<Double> bottomRightCorner = new Point2<>(x + width, y
                + height, Double.class);

        this.layoutBounds = new Rectangle2<>(topLeftCorner, bottomRightCorner,
                Double.class);
    }

    @Override
    public IRectangle2<Double> getLayoutBounds() {

        return this.layoutBounds;
    }


    @Override
    public void setMargin(final int topMargin, final int bottomMargin,
            final int leftMargin, final int rightMargin) {

        this.topMargin = topMargin;
        this.bottomMargin = bottomMargin;
        this.leftMargin = leftMargin;
        this.rightMargin = rightMargin;
    }


    @Override
    public int getTopMargin() {

        return this.topMargin;
    }


    @Override
    public int getBottomMargin() {

        return this.bottomMargin;
    }


    @Override
    public int getLeftMargin() {

        return this.leftMargin;
    }


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


            final double x = this.getLayoutBounds().getTopLeftCorner().getX();
            final double y = this.getLayoutBounds().getTopLeftCorner().getY();
            final double x2 = this.getLayoutBounds().getBottomRightCorner()
                    .getX();
            final double y2 = this.getLayoutBounds().getBottomRightCorner()
                    .getY();

            final double width = x2 - x;
            final double height = y2 - y;

            this.getLayout().setLayoutBounds(
                    new Rectangle2D.Double(x, y, width, height));


            this.getLayout().setMargin(this.topMargin, this.leftMargin,
                    this.bottomMargin, this.rightMargin);

            this.getLayout().setDuration(this.duration);
        }
    }
}
