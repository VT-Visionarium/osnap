package edu.vt.arc.vis.osnap.events.domain.graph;

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

import edu.vt.arc.vis.osnap.events.domain.DomainObjectDetails;
import edu.vt.arc.vis.osnap.events.domain.metadata.MetadataPropertyDetails;


/**
 * @author Peter J. Radics
 * @version 1.1.0
 * @since 1.1.0
 *
 */
public class HyperEdgeDetails
        extends DomainObjectDetails {

    private MetadataPropertyDetails   metadata;

    private List<EndpointDetails> endpoints;



    /**
     * Returns the endpoints.
     * 
     * @return the endpoints.
     */
    public List<EndpointDetails> getEndpoints() {

        return endpoints;
    }


    /**
     * Sets the endpoints.
     * 
     * @param endpoints
     *            the endpoints.
     */
    public void setEndpoints(List<EndpointDetails> endpoints) {

        this.endpoints = endpoints;
    }

    /**
     * Returns the metadata.
     * 
     * @return the metadata.
     */
    public MetadataPropertyDetails getMetadata() {

        return metadata;
    }


    /**
     * Sets the metadata.
     * 
     * @param metadata
     *            the metadata.
     */
    public void setMetadata(MetadataPropertyDetails metadata) {

        this.metadata = metadata;
    }


    /**
     * Creates a new instance of the {@link HyperEdgeDetails} class.
     */
    public HyperEdgeDetails() {

        this((String) null);
    }

    /**
     * Creates a new instance of the {@link HyperEdgeDetails} class with the
     * provided id.
     * 
     * @param id
     *            the id.
     */
    public HyperEdgeDetails(final String id) {

        super(id);
    }

    /**
     * Creates a new instance of the {@link HyperEdgeDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public HyperEdgeDetails(final DomainObjectDetails details) {

        super(details);
    }

    /**
     * Creates a new instance of the {@link HyperEdgeDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public HyperEdgeDetails(final HyperEdgeDetails details) {

        super(details);

        this.setMetadata(details.getMetadata());

        this.setEndpoints(details.getEndpoints());
    }
}