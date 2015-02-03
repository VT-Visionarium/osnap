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
 * The abstract {@code BaseLabelTextLayout} class provides common functionality
 * of all @{link ILabelTextLayout ILabelTextLayoutComponents}.
 * 
 * @see ILabelTextLayout
 * @see BaseLayout
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlType(name = "BaseLabelTextLayout")
public abstract class BaseLabelTextLayout
        extends BaseLayout
        implements ILabelTextLayout {

    /**
     * Returns the name of this {@code ILayout}.
     * 
     * @return the name.
     */
    public static String name() {

        return "Label Text LayoutVisualizer Component";
    }


    /**
     * Returns the description of this {@code ILayout}.
     * 
     * @return the description.
     */
    public static String description() {

        return "The " + BaseLabelTextLayout.name()
                + " provides the label text for nodes and edges.";
    }


    /**
     * Returns the capabilities (the set of
     * {@link edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty
     * VisualProperties} that can be provided) of this {@code ILayout}.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return EnumSet.of(VisualProperty.EDGE_LABEL_TEXT,
        // VisualProperty.HYPEREDGE_LABEL_TEXT,
                VisualProperty.NODE_LABEL_TEXT);
    }

    /**
     * Creates a new {@code BaseLabelTextLayout} instance.
     */
    public BaseLabelTextLayout() {

        this(BaseLabelTextLayout.capabilities());
    }

    /**
     * Creates a new {@code BaseLabelTextLayout} instance with the provided
     * capabilities.
     * 
     * @param capabilities
     *            the capabilities.
     */
    public BaseLabelTextLayout(Set<VisualProperty> capabilities) {

        this(capabilities, BaseLabelTextLayout.name(), BaseLabelTextLayout
                .description(), true);
    }

    /**
     * Creates a new {@code BaseLabelTextLayout} instance with the provided
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
    public BaseLabelTextLayout(Set<VisualProperty> capabilities, String name,
            String description, Boolean randomizeName) {

        super(capabilities, name, description, randomizeName);
    }
}
