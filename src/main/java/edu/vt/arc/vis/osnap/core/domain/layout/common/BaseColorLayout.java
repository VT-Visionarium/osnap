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
 * The abstract {@code BaseColorLayout} class provides common functionality of
 * all @{link IColorLayout IColorLayoutComponents}.
 * 
 * @see IColorLayout
 * @see BaseLayout
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 */
@XmlType(name = "BaseColorLayout")
public abstract class BaseColorLayout
        extends BaseLayout
        implements IColorLayout {

    /**
     * Returns the name of this {@link ILayout}.
     * 
     * @return the name.
     */
    public static String name() {

        return "Color Layout";
    }


    /**
     * Returns the description of this {@link ILayout}.
     * 
     * @return the description.
     */
    public static String description() {

        return "The " + BaseColorLayout.name()
                + " provides the color for nodes and edges.";
    }


    /**
     * Returns the capabilities (the set of
     * {@link edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty
     * VisualProperties} that can be provided) of this {@link ILayout}.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return EnumSet.of(VisualProperty.EDGE_COLOR,
        // VisualProperty.HYPEREDGE_COLOR,
                VisualProperty.NODE_COLOR);
    }


    /**
     * Creates a new instance of the {@link BaseColorLayout} class.
     */
    protected BaseColorLayout() {

        super(BaseColorLayout.capabilities());
    }


    /**
     * Creates a new instance of the {@link BaseColorLayout} class with the
     * provided capabilities.
     * 
     * @param capabilities
     *            the capabilities.
     */
    public BaseColorLayout(Set<VisualProperty> capabilities) {

        this(capabilities, BaseColorLayout.name(), BaseColorLayout
                .description(), true);
    }


    /**
     * Creates a new instance of the {@link BaseColorLayout} class with the
     * provided capabilities, (randomized) name, and description.
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
    public BaseColorLayout(final Set<VisualProperty> capabilities,
            final String name, final String description,
            final boolean randomizeName) {

        super(capabilities, name, description, randomizeName);
    }
}
