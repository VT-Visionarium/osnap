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
 */
public class SchemaEntryDetails
        extends DomainObjectDetails {

    private String          key;

    private String          type;

    private MetadataDetails defaultValue;

    private boolean         required;
    private boolean         unique;



    /**
     * Returns the key.
     * 
     * @return the key.
     */
    public String getKey() {

        return key;
    }


    /**
     * Sets the key.
     * 
     * @param key
     *            the key.
     */
    public void setKey(String key) {

        this.key = key;
    }


    /**
     * Returns the type.
     * 
     * @return the type.
     */
    public String getType() {

        return type;
    }


    /**
     * Sets the type.
     * 
     * @param type
     *            the type.
     */
    public void setType(String type) {

        this.type = type;
    }


    /**
     * Returns the defaultValue.
     * 
     * @return the defaultValue.
     */
    public MetadataDetails getDefaultValue() {

        return defaultValue;
    }


    /**
     * Sets the defaultValue.
     * 
     * @param defaultValue
     *            the defaultValue.
     */
    public void setDefaultValue(MetadataDetails defaultValue) {

        this.defaultValue = defaultValue;
    }


    /**
     * Returns the required.
     * 
     * @return the required.
     */
    public boolean isRequired() {

        return required;
    }


    /**
     * Sets the required.
     * 
     * @param required
     *            the required.
     */
    public void setRequired(boolean required) {

        this.required = required;
    }


    /**
     * Returns the unique.
     * 
     * @return the unique.
     */
    public boolean isUnique() {

        return unique;
    }


    /**
     * Sets the unique.
     * 
     * @param unique
     *            the unique.
     */
    public void setUnique(boolean unique) {

        this.unique = unique;
    }

    /**
     * Creates a new instance of the {@link SchemaEntryDetails} class.
     */
    public SchemaEntryDetails() {

        this((String) null);
    }

    /**
     * Creates a new instance of the {@link SchemaEntryDetails} class with the
     * provided id.
     * 
     * @param id
     *            the id.
     */
    public SchemaEntryDetails(String id) {

        super(id);
    }

    /**
     * Creates a new instance of the {@link SchemaEntryDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public SchemaEntryDetails(DomainObjectDetails details) {

        super(details);
    }

    /**
     * Creates a new instance of the {@link SchemaEntryDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public SchemaEntryDetails(SchemaEntryDetails details) {

        super(details);

        this.setKey(details.getKey());
        this.setType(details.getType());
        this.setDefaultValue(details.getDefaultValue());
        this.setRequired(details.isRequired());
        this.setUnique(details.isUnique());

    }

}
