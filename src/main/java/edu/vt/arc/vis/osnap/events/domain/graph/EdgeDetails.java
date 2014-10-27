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



import edu.vt.arc.vis.osnap.events.domain.DomainObjectDetails;
import edu.vt.arc.vis.osnap.events.domain.metadata.MetadataPropertyDetails;


/**
 * @author Peter J. Radics
 * @version 1.1.0
 * @since 1.1.0
 *
 */
public class EdgeDetails
        extends DomainObjectDetails {

    private MetadataPropertyDetails metadata;


    private EndpointDetails sourceEndpoint;
    private EndpointDetails targetEndpoint;

    boolean                     isDirected;



    /**
     * Returns the sourceEndpoint.
     * 
     * @return the sourceEndpoint.
     */
    public EndpointDetails getSourceEndpoint() {

        return sourceEndpoint;
    }


    /**
     * Sets the sourceEndpoint.
     * 
     * @param sourceEndpoint
     *            the sourceEndpoint.
     */
    public void setSourceEndpoint(EndpointDetails sourceEndpoint) {

        this.sourceEndpoint = sourceEndpoint;
    }


    /**
     * Returns the targetEndpoint.
     * 
     * @return the targetEndpoint.
     */
    public EndpointDetails getTargetEndpoint() {

        return targetEndpoint;
    }


    /**
     * Sets the targetEndpoint.
     * 
     * @param targetEndpoint
     *            the targetEndpoint.
     */
    public void setTargetEndpoint(EndpointDetails targetEndpoint) {

        this.targetEndpoint = targetEndpoint;
    }


    /**
     * Returns the isDirected.
     * 
     * @return the isDirected.
     */
    public boolean isDirected() {

        return isDirected;
    }



    /**
     * Sets the isDirected.
     * 
     * @param isDirected
     *            the isDirected.
     */
    public void setDirected(boolean isDirected) {

        this.isDirected = isDirected;
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
     * Creates a new instance of the {@link EdgeDetails} class.
     */
    public EdgeDetails() {

        this((String) null);
    }

    /**
     * Creates a new instance of the {@link EdgeDetails} class with the provided
     * id.
     * 
     * @param id
     *            the id.
     */
    public EdgeDetails(final String id) {

        super(id);
    }

    /**
     * Creates a new instance of the {@link EdgeDetails} class from the provided
     * details.
     * 
     * @param details
     *            the details.
     */
    public EdgeDetails(final DomainObjectDetails details) {

        super(details);
    }

    /**
     * Creates a new instance of the {@link EdgeDetails} class from the provided
     * details.
     * 
     * @param details
     *            the details.
     */
    public EdgeDetails(final EdgeDetails details) {

        super(details);

        this.setMetadata(details.getMetadata());

        this.setSourceEndpoint(details.getSourceEndpoint());
        this.setTargetEndpoint(details.getTargetEndpoint());

        this.setDirected(details.isDirected);
    }
}