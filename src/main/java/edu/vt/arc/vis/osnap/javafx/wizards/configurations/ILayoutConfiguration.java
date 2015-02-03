package edu.vt.arc.vis.osnap.javafx.wizards.configurations;


// @formatter:off
/*
 * _
 * The Open Semantic Network Analysis Platform (OSNAP)
 * _
 * Copyright (C) 2012 - 2014 Visionarium at Virginia Tech
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
// @formatter:on

import java.util.Collection;
import java.util.List;
import java.util.Set;

import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;


/**
 * The {@code ILayoutConfiguration} interface provides a shared contract for
 * {@link IConfiguration Configurations} of {@link ILayout LayoutComponents}.
 * 
 * @param <T>
 *            the type of the {@link ILayout}.
 * 
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.3
 * @since 0.5.0
 */
public interface ILayoutConfiguration<T extends ILayout>
        extends IConfiguration<T> {

    /**
     * Returns the name.
     * 
     * @return the name.
     */
    public abstract String getName();

    /**
     * Sets the name.
     * 
     * @param name
     *            the name.
     */
    public abstract void setName(String name);

    /**
     * Returns the description.
     * 
     * @return the description.
     */
    public abstract String getDescription();

    /**
     * Sets the description.
     * 
     * @param description
     *            the description.
     */
    public abstract void setDescription(final String description);



    /**
     * Returns the enabled {@link VisualProperty Visual Properties}.
     * 
     * @return the enabled {@link VisualProperty Visual Properties}.
     */
    public abstract Set<VisualProperty> getEnabledVisualProperties();



    /**
     * Returns the first enabled {@link VisualProperty Visual Properties}.
     * 
     * @return the first enabled {@link VisualProperty Visual Properties}.
     */
    public abstract VisualProperty getEnabledVisualProperty();

    /**
     * Enables the provided {@link VisualProperty Visual Properties}.
     * 
     * @param visualProperties
     *            the {@link VisualProperty Visual Properties} to enable.
     */
    public abstract void enableVisualProperties(
            final VisualProperty... visualProperties);

    /**
     * Enables the provided {@link VisualProperty Visual Properties}.
     * 
     * @param visualProperties
     *            the {@link VisualProperty Visual Properties} to enable.
     */
    public abstract void enableVisualProperties(
            final Collection<VisualProperty> visualProperties);

    /**
     * Returns the restriction (the list of {@link IGraphObject GraphObjects} to
     * which the {@link ILayout LayoutComponent} applies).
     * 
     * @return the restriction.
     */
    public abstract List<IGraphObject> getRestriction();


    /**
     * Sets the restriction (the list of {@link IGraphObject GraphObjects} to
     * which the {@link ILayout LayoutComponent} applies).
     * 
     * @param restriction
     *            the restriction.
     */
    public abstract void setRestriction(final List<IGraphObject> restriction);
}
