package edu.vt.arc.vis.osnap.core.domain.layout.common;


import java.util.LinkedHashSet;
import java.util.Set;

import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObjectBasedValueTypeContainer;
import edu.vt.arc.vis.osnap.core.domain.mappings.IMapping;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;


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

/**
 * The {@code IMappedLayout} interface is a tagging interface for all
 * {@link ILayout ILayoutComponents} that are based on a
 * {@link edu.vt.arc.vis.osnap.core.domain.mappings.Mapping}.
 * 
 * @param <DOMAIN_KEY_TYPE>
 *            The type of the identifier of the domain property. Has to extend
 *            {@link IGraphObjectBasedValueTypeContainer}.
 * @param <DOMAIN_VALUE_TYPE>
 *            The type of the domain values.
 * @param <CODOMAIN_VALUE_TYPE>
 *            The datatype of the visual property mapped to.
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 */
public interface IMappedLayout<DOMAIN_KEY_TYPE extends IGraphObjectBasedValueTypeContainer, DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE>
        extends
        ILayout,
        IMapping<DOMAIN_KEY_TYPE, DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE, VisualProperty> {

    // Tagging interface.

    /**
     * Returns the name of this {@code ILayout}.
     * 
     * @return the name.
     */
    public static String name() {

        return "Mapped Layout";
    }


    /**
     * Returns the description of this {@code ILayout}.
     * 
     * @return the description.
     */
    public static String description() {

        return "Provides a Visual Property based on a Mapping.";
    }


    /**
     * Returns the capabilities (the set of
     * {@link edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty
     * VisualProperties} that can be provided) of this {@code ILayout}.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return new LinkedHashSet<>();
    }
}
