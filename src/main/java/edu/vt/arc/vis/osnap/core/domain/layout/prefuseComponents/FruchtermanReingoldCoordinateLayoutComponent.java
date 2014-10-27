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


package edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents;


import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.core.domain.layout.common.Base2DCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.io.prefuse.Constants;
import prefuse.action.layout.graph.FruchtermanReingoldLayout;


/**
 * The <code>FruchtermanReingoldCoordinateLayoutComponent</code> class computes
 * the Fruchterman-Reingold algorithm for force-directed placement of graph
 * nodes. The computational complexity of this algorithm is quadratic [O(n^2)]
 * in the number of nodes, so should only be applied for relatively small
 * graphs, particularly in interactive situations.
 *
 * @see FruchtermanReingoldLayout
 *
 * @author Peter J. Radics
 * @version 0.1
 *
 */
@XmlType(name = "FruchtermanReingoldCoordinateLayoutComponent")
public class FruchtermanReingoldCoordinateLayoutComponent
        extends BasePrefuseCoordinateLayoutComponent {


    /**
     * @return the maximumIterations
     */
    public int getMaximumIterations() {

        return this.maximumIterations;
    }



    /**
     * @param maximumIterations
     *            the maximumIterations to set
     */
    public void setMaximumIterations(int maximumIterations) {

        this.maximumIterations = maximumIterations;
    }

    private final FruchtermanReingoldLayout layout;

    @XmlElement(name = "MaximumIterations")
    private int                             maximumIterations;

    /**
     * Returns the name of this <code>ILayoutComponent</code>.
     *
     * @return the name.
     */
    public static String name() {

        return "Fruchterman-Reingold Coordinate Layout Component (Prefuse)";
    }


    /**
     * Returns the description of this <code>ILayoutComponent</code>.
     *
     * @return the description.
     */
    public static String description() {

        String description = "The "
                + FruchtermanReingoldCoordinateLayoutComponent.name()
                + " implements the Fruchterman-Reingold algorithm for"
                + " force-directed placement of graph nodes.";

        return description;
    }


    /**
     * Returns the capabilities (the set of
     * {@link edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty VisualProperties}
     * that can be provided) of this <code>ILayoutComponent</code>.
     *
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return Base2DCoordinateLayoutComponent.capabilities();
    }


    /**
     * Creates a new <code>FruchtermanReingoldCoordinateLayoutComponent</code>
     * instance.
     *
     */
    public FruchtermanReingoldCoordinateLayoutComponent() {

        this(700);
    }


    /**
     * Creates a new <code>FruchtermanReingoldCoordinateLayoutComponent</code>
     * instance. The provided parameters signify whether the bounds of the
     * layout are strictly enforced, and how many iterations the algorithm
     * should run.
     *
     * @param maximumIterations
     *            the number of maximum iterations.
     */
    public FruchtermanReingoldCoordinateLayoutComponent(int maximumIterations) {

        super(FruchtermanReingoldCoordinateLayoutComponent.name(),
                FruchtermanReingoldCoordinateLayoutComponent.description(),
                true);

        this.maximumIterations = maximumIterations;
        this.layout = new FruchtermanReingoldLayout(Constants.PREFUSE_GROUP,
                maximumIterations);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.IPrefuseLayoutComponent#getLayout
     * ()
     */
    @Override
    public FruchtermanReingoldLayout getLayout() {

        return this.layout;
    }

    @Override
    public void applyParameters() {

        super.applyParameters();

        this.getLayout().setMaxIterations(this.getMaximumIterations());
    }
}
