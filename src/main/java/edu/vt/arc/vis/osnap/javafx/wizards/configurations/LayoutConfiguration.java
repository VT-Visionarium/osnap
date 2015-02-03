package edu.vt.arc.vis.osnap.javafx.wizards.configurations;


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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;


/**
 * The abstract {@code LayoutConfiguration} class provides the minimum
 * {@link ILayoutConfiguration} for {@link ILayout Layouts}.
 *
 * @param <T>
 *            the type of the {@link ILayout}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public abstract class LayoutConfiguration<T extends ILayout>
        implements ILayoutConfiguration<T> {

    private String                    name;
    private String                    description;

    private final Set<VisualProperty> enabledVisualProperties;
    private final List<IGraphObject>  restriction;



    @Override
    public String getName() {

        return this.name;
    }

    @Override
    public void setName(final String name) {

        this.name = name;
    }


    @Override
    public String getDescription() {

        return this.description;
    }

    @Override
    public void setDescription(final String description) {

        this.description = description;
    }


    @Override
    public Set<VisualProperty> getEnabledVisualProperties() {

        return this.enabledVisualProperties;
    }

    @Override
    public VisualProperty getEnabledVisualProperty() {

        if (!this.enabledVisualProperties.isEmpty()) {

            return this.enabledVisualProperties.iterator().next();
        }

        return null;
    }

    @Override
    public void enableVisualProperties(final VisualProperty... visualProperties) {

        this.enableVisualProperties(Arrays.asList(visualProperties));
    }

    public void enableVisualProperties(
            java.util.Collection<VisualProperty> visualProperties) {

        this.enabledVisualProperties.clear();
        this.enabledVisualProperties.addAll(visualProperties);
    };


    @Override
    public List<IGraphObject> getRestriction() {

        return this.restriction;
    }

    @Override
    public void setRestriction(final List<IGraphObject> restriction) {

        this.restriction.clear();
        this.restriction.addAll(restriction);
    }


    /**
     * Creates a new instance of the {@code LayoutConfiguration} class with the
     * provided defaults for name and description.
     *
     * @param defaultName
     *            the default name.
     * @param defaultDescription
     *            the default description.
     */
    public LayoutConfiguration(final String defaultName,
            final String defaultDescription) {

        this.name = defaultName;
        this.description = defaultDescription;

        this.enabledVisualProperties = new LinkedHashSet<>();
        this.restriction = new ArrayList<>();
    }
}
