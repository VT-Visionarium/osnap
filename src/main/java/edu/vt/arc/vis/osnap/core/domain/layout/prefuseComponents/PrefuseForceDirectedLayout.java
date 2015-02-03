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

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.core.domain.layout.common.Base2DCoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.CoordinateComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ICoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.io.prefuse.Constants;
import prefuse.action.layout.graph.ForceDirectedLayout;


/**
 * The {@code PrefuseForceDirectedLayout} class positions graph elements based
 * on a physics simulation of interacting forces; by default, nodes repel each
 * other, edges act as springs, and drag forces (similar to air resistance) are
 * applied. This algorithm can be run for multiple iterations for a run-once
 * layout computation or repeatedly run in an animated fashion for a dynamic and
 * interactive layout.
 *
 * <p>
 * The running time of this layout algorithm is the greater of O(N log N) and
 * O(E), where N is the number of nodes and E the number of edges. The addition
 * of custom force calculation modules may, however, increase this value.
 *
 * @see ForceDirectedLayout
 *
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 *
 */
@XmlType(name = "ForceDirectedLayout")
public class PrefuseForceDirectedLayout
        extends BasePrefuseLayout {

    private final ForceDirectedLayout layout;

    @XmlElement(name = "Iterations")
    private int                       iterations;

    /**
     * Returns the name of this {@code ILayout}.
     *
     * @return the name.
     */
    public static String name() {

        return "Force Directed Layout (Prefuse)";
    }


    /**
     * Returns the description of this {@code ILayout}.
     *
     * @return the description.
     */
    public static String description() {

        String description = "The " + PrefuseForceDirectedLayout.name()
                + " positions graph elements based on a physics simulation of"
                + " interacting forces; by default, nodes repel each other,"
                + " edges act as springs, and drag forces (similar to air"
                + " resistance) are applied. This algorithm runs for multiple"
                + " iterations.";

        return description;
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
     * Returns the capabilities (the set of
     * {@link edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty
     * VisualProperties} that can be provided) of this {@code ILayout}.
     *
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return Base2DCoordinateLayout.capabilities();
    }


    /**
     * Returns the number of iterations the ForceDirectedLayout is to run.
     *
     * @return the number of iterations.
     */
    public int getIterations() {

        return this.iterations;
    }

    /**
     * Sets the number of iterations the ForceDirectedLayout is to run.
     *
     * @param iterations
     *            the number of iterations.
     */
    public void setIterations(int iterations) {

        this.iterations = iterations;
    }


    /**
     * Creates a new instance of the {@code PrefuseForceDirectedLayout} class.
     */
    public PrefuseForceDirectedLayout() {

        this(false);
    }

    /**
     * Creates a new instance of the {@code PrefuseForceDirectedLayout} class.
     *
     * @param enforceBounds
     *            whether or not to enforce layout bounds.
     *
     */
    public PrefuseForceDirectedLayout(boolean enforceBounds) {

        this(enforceBounds, 100);
    }

    /**
     * Creates a new instance of the {@code PrefuseForceDirectedLayout} class.
     * The provided parameters signify whether the bounds of the layout are
     * strictly enforced, and how many iterations the algorithm should run.
     *
     * @param enforceBounds
     *            whether or not to enforce bounds.
     * @param iterations
     *            the number of iterations.
     */
    public PrefuseForceDirectedLayout(boolean enforceBounds, int iterations) {

        super(PrefuseForceDirectedLayout.name(), PrefuseForceDirectedLayout
                .description(), true);

        this.layout = new ForceDirectedLayout(Constants.PREFUSE_GROUP,
                enforceBounds, true);
        this.iterations = iterations;
    }

    @Override
    public ForceDirectedLayout getLayout() {

        return this.layout;
    }

    @Override
    public void applyParameters() {

        super.applyParameters();

        this.layout.setIterations(iterations);
    }
}
