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
import prefuse.action.layout.graph.FruchtermanReingoldLayout;


/**
 * The {@code PrefuseFruchtermanReingoldLayout} class computes the
 * Fruchterman-Reingold algorithm for force-directed placement of graph nodes.
 * The computational complexity of this algorithm is quadratic [O(n^2)] in the
 * number of nodes, so should only be applied for relatively small graphs,
 * particularly in interactive situations.
 *
 * @see FruchtermanReingoldLayout
 *
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 *
 */
@XmlType(name = "PrefuseFruchtermanReingoldLayout")
public class PrefuseFruchtermanReingoldLayout
        extends BasePrefuseLayout {


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
     * Returns the name of this {@code ILayout}.
     *
     * @return the name.
     */
    public static String name() {

        return "Fruchterman-Reingold Layout (Prefuse)";
    }


    /**
     * Returns the description of this {@code ILayout}.
     *
     * @return the description.
     */
    public static String description() {

        String description = "The " + PrefuseFruchtermanReingoldLayout.name()
                + " implements the Fruchterman-Reingold algorithm for"
                + " force-directed placement of graph nodes.";

        return description;
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
     * Creates a new {@code PrefuseFruchtermanReingoldLayout} instance.
     *
     */
    public PrefuseFruchtermanReingoldLayout() {

        this(700);
    }


    /**
     * Creates a new {@code PrefuseFruchtermanReingoldLayout} instance. The
     * provided parameters signify whether the bounds of the layout are strictly
     * enforced, and how many iterations the algorithm should run.
     *
     * @param maximumIterations
     *            the number of maximum iterations.
     */
    public PrefuseFruchtermanReingoldLayout(int maximumIterations) {

        super(PrefuseFruchtermanReingoldLayout.name(),
                PrefuseFruchtermanReingoldLayout.description(), true);

        this.maximumIterations = maximumIterations;
        this.layout = new FruchtermanReingoldLayout(Constants.PREFUSE_GROUP,
                maximumIterations);
    }

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
