package edu.vt.arc.vis.osnap.events.domain.metadata;

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


/**
 * @author Peter J. Radics
 * @version 1.1.0
 * @since 1.1.0
 *
 */
public class MetadataPropertyDetails
        extends DomainObjectDetails {

    private SchemaDetails         schema;
    private List<MetadataDetails> metadata;



    /**
     * Returns the schema.
     * 
     * @return the schema.
     */
    public SchemaDetails getSchema() {

        return schema;
    }


    /**
     * Sets the schema.
     * 
     * @param schema
     *            the schema.
     */
    public void setSchema(SchemaDetails schema) {

        this.schema = schema;
    }


    /**
     * Returns the metadata.
     * 
     * @return the metadata.
     */
    public List<MetadataDetails> getMetadata() {

        return metadata;
    }


    /**
     * Sets the metadata.
     * 
     * @param metadata
     *            the metadata.
     */
    public void setMetadata(List<MetadataDetails> metadata) {

        this.metadata = metadata;
    }


    /**
     * Creates a new instance of the {@link MetadataPropertyDetails} class.
     */
    public MetadataPropertyDetails() {

        this((String) null);
    }

    /**
     * Creates a new instance of the {@link MetadataPropertyDetails} class with
     * the provided id.
     * 
     * @param id
     *            the id.
     */
    public MetadataPropertyDetails(String id) {

        super(id);
    }



    /**
     * Creates a new instance of the {@link MetadataPropertyDetails} class from
     * the provided details.
     * 
     * @param details
     *            the details.
     */
    public MetadataPropertyDetails(DomainObjectDetails details) {

        super(details);
    }

    /**
     * Creates a new instance of the {@link MetadataPropertyDetails} class from
     * the provided details.
     * 
     * @param details
     *            the details.
     */
    public MetadataPropertyDetails(MetadataPropertyDetails details) {

        super(details);

        this.setSchema(details.getSchema());
        this.setMetadata(details.getMetadata());
    }

}
