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


import edu.vt.arc.vis.osnap.events.domain.DomainObjectDetails;


/**
 * @author Peter J. Radics
 * @version 1.1.0
 * @since 1.1.0
 *
 */
public class FloatMetadataDetails
        extends MetadataDetails {

    private Float value;



    /**
     * Returns the value.
     * 
     * @return the value.
     */
    public Float getValue() {

        return value;
    }


    /**
     * Sets the value.
     * 
     * @param value
     *            the value.
     */
    public void setValue(Float value) {

        this.value = value;
    }

    /**
     * Creates a new instance of the {@link FloatMetadataDetails} class.
     */
    public FloatMetadataDetails() {

        this((String) null);
    }

    /**
     * Creates a new instance of the {@link FloatMetadataDetails} class with
     * the provided id.
     * 
     * @param id
     *            the id.
     */
    public FloatMetadataDetails(String id) {

        super(id);
    }



    /**
     * Creates a new instance of the {@link FloatMetadataDetails} class from
     * the provided details.
     * 
     * @param details
     *            the details.
     */
    public FloatMetadataDetails(DomainObjectDetails details) {

        super(details);
    }

    /**
     * Creates a new instance of the {@link FloatMetadataDetails} class from
     * the provided details.
     * 
     * @param details
     *            the details.
     */
    public FloatMetadataDetails(FloatMetadataDetails details) {

        super(details);

        this.setValue(details.getValue());
    }
}
