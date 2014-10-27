package edu.vt.arc.vis.osnap.events.domain;

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



import java.util.List;

import edu.vt.arc.vis.osnap.events.domain.graph.UniverseDetails;
import edu.vt.arc.vis.osnap.events.domain.layout.LayoutDetails;
import edu.vt.arc.vis.osnap.events.domain.visualization.VisualizationDetails;


/**
 * @author Peter J. Radics
 * @version 1.1.0
 * @since 1.1.0
 */
public class ProjectDetails
        extends DomainObjectDetails {

    private UniverseDetails            universe;

    private List<LayoutDetails>        layouts;
    private List<VisualizationDetails> visualizations;

    /**
     * Returns the universe.
     * 
     * @return the universe the universe.
     */
    public UniverseDetails getUniverse() {

        return universe;
    }

    /**
     * Sets the universe.
     * 
     * @param universe
     *            the universe.
     */
    public void setUniverse(UniverseDetails universe) {

        this.universe = universe;
    }



    /**
     * Returns the layouts.
     * 
     * @return the layouts.
     */
    public List<LayoutDetails> getLayouts() {

        return layouts;
    }


    /**
     * Sets the layouts.
     * 
     * @param layouts
     *            the layouts.
     */
    public void setLayouts(List<LayoutDetails> layouts) {

        this.layouts = layouts;
    }


    /**
     * Returns the visualizations.
     * 
     * @return the visualizations.
     */
    public List<VisualizationDetails> getVisualizations() {

        return visualizations;
    }


    /**
     * Sets the visualizations.
     * 
     * @param visualizations
     *            the visualizations.
     */
    public void setVisualizations(List<VisualizationDetails> visualizations) {

        this.visualizations = visualizations;
    }

    /**
     * Creates a new instance of the {@link ProjectDetails} class.
     */
    public ProjectDetails() {

        this((String) null);
    }

    /**
     * Creates a new instance of the {@link ProjectDetails} class with the
     * provided id.
     * 
     * @param id
     *            the id.
     */
    public ProjectDetails(final String id) {

        super(id);
    }

    /**
     * Creates a new instance of the {@link ProjectDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public ProjectDetails(final DomainObjectDetails details) {

        super(details);
    }

    /**
     * Creates a new instance of the {@link ProjectDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public ProjectDetails(final ProjectDetails details) {

        super(details);
        this.setUniverse(details.getUniverse());
    }
}
