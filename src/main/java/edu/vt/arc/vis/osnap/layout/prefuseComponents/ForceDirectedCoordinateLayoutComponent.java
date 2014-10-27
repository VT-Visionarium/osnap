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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.conversion.prefuse.Constants;
import edu.vt.arc.vis.osnap.layout.common.Base2DCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.visualization.VisualProperty;
import prefuse.action.layout.graph.ForceDirectedLayout;


/**
 * The <code>ForceDirectedCoordinateLayoutComponent</code> class positions graph
 * elements based on a physics simulation of interacting forces; by default,
 * nodes repel each other, edges act as springs, and drag forces (similar to air
 * resistance) are applied. This algorithm can be run for multiple iterations
 * for a run-once layout computation or repeatedly run in an animated fashion
 * for a dynamic and interactive layout.
 *
 * <p>
 * The running time of this layout algorithm is the greater of O(N log N) and
 * O(E), where N is the number of nodes and E the number of edges. The addition
 * of custom force calculation modules may, however, increase this value.
 *
 * @see ForceDirectedLayout
 *
 * @author Peter J. Radics
 * @version 0.1
 *
 */
@XmlType(name = "ForceDirectedCoordinateLayoutComponent")
public class ForceDirectedCoordinateLayoutComponent
        extends BasePrefuseCoordinateLayoutComponent {

    private final ForceDirectedLayout layout;

    @XmlElement(name = "Iterations")
    private int                       iterations;

    /**
     * Returns the name of this <code>ILayoutComponent</code>.
     *
     * @return the name.
     */
    public static String name() {

        return "Force Directed Coordinate Layout Component (Prefuse)";
    }


    /**
     * Returns the description of this <code>ILayoutComponent</code>.
     *
     * @return the description.
     */
    public static String description() {

        String description = "The "
                + ForceDirectedCoordinateLayoutComponent.name()
                + " positions graph elements based on a physics simulation of"
                + " interacting forces; by default, nodes repel each other,"
                + " edges act as springs, and drag forces (similar to air"
                + " resistance) are applied. This algorithm runs for multiple"
                + " iterations.";

        return description;
    }


    /**
     * Returns the capabilities (the set of
     * {@link edu.vt.arc.vis.osnap.visualization.VisualProperty VisualProperties}
     * that can be provided) of this <code>ILayoutComponent</code>.
     *
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return Base2DCoordinateLayoutComponent.capabilities();
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
     * Creates a new <code>ForceDirectedCoordinateLayoutComponent</code>
     * instance.
     *
     */
    public ForceDirectedCoordinateLayoutComponent() {

        this(false);
    }

    /**
     * Creates a new <code>ForceDirectedCoordinateLayoutComponent</code>
     * instance.
     *
     * @param enforceBounds
     *            whether or not to enforce layout bounds.
     *
     */
    public ForceDirectedCoordinateLayoutComponent(boolean enforceBounds) {

        this(enforceBounds, 100);
    }

    /**
     * Creates a new <code>ForceDirectedCoordinateLayoutComponent</code>
     * instance. The provided parameters signify whether the bounds of the
     * layout are strictly enforced, and how many iterations the algorithm
     * should run.
     *
     * @param enforceBounds
     *            whether or not to enforce bounds.
     * @param iterations
     *            the number of iterations.
     */
    public ForceDirectedCoordinateLayoutComponent(boolean enforceBounds,
            int iterations) {

        super(ForceDirectedCoordinateLayoutComponent.name(),
                ForceDirectedCoordinateLayoutComponent.description(), true);

        this.layout = new ForceDirectedLayout(Constants.PREFUSE_GROUP,
                enforceBounds, true);
        this.iterations = iterations;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * edu.vt.arc.vis.osnap.layout.prefuseComponents.IPrefuseLayoutComponent#getLayout
     * ()
     */
    @Override
    public ForceDirectedLayout getLayout() {

        return this.layout;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * edu.vt.arc.vis.osnap.layout.prefuseComponents.BasePrefuseCoordinateLayoutComponent
     * #applyParameters()
     */
    @Override
    public void applyParameters() {

        super.applyParameters();

        this.layout.setIterations(iterations);
    }
}
