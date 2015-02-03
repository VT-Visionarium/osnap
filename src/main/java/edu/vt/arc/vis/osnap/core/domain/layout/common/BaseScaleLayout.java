package edu.vt.arc.vis.osnap.core.domain.layout.common;


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


import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;

import java.util.EnumSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlType;


/**
 * The abstract {@code BaseScaleLayout} class provides common functionality of
 * all @{link IScaleLayout IScaleLayouts}.
 * 
 * @see IScaleLayout
 * @see BaseLayout
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 */
@XmlType(name = "BaseScaleLayout")
public abstract class BaseScaleLayout
        extends BaseLayout
        implements IScaleLayout {

    /**
     * Returns the name of this {@code ILayout}.
     * 
     * @return the name.
     */
    public static String name() {

        return "Scale Layout";
    }


    /**
     * Returns the description of this {@code ILayout}.
     * 
     * @return the description.
     */
    public static String description() {

        return "The " + BaseScaleLayout.name()
                + " provides the scale for nodes and edges.";
    }


    /**
     * Returns the capabilities (the set of
     * {@link edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty
     * VisualProperties} that can be provided) of this {@code ILayout}.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return EnumSet.of(VisualProperty.EDGE_SCALE,
        // VisualProperty.HYPEREDGE_SCALE,
                VisualProperty.NODE_SCALE);
    }

    /**
     * Creates a new {@code BaseScaleLayout} instance.
     */
    public BaseScaleLayout() {

        this(BaseScaleLayout.capabilities());
    }

    /**
     * Creates a new {@code BaseScaleLayout} instance with the provided
     * capabilities.
     * 
     * @param capabilities
     *            the capabilities.
     */
    public BaseScaleLayout(Set<VisualProperty> capabilities) {

        this(capabilities, BaseScaleLayout.name(), BaseScaleLayout
                .description(), true);
    }

    /**
     * Creates a new {@code BaseScaleLayout} instance with the provided
     * capabilities, (randomized) name, and description.
     * 
     * @param capabilities
     *            the capabilities.
     * @param name
     *            the name.
     * @param description
     *            the description.
     * @param randomizeName
     *            whether or not to randomize the name.
     */
    public BaseScaleLayout(Set<VisualProperty> capabilities, String name,
            String description, Boolean randomizeName) {

        super(capabilities, name, description, randomizeName);
    }

}
