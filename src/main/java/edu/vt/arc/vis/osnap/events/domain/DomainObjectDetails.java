/**
 * 
 */
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



/**
 * @author Peter J. Radics
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class DomainObjectDetails {

    private String id;
    private Long   version;



    /**
     * @return the id
     */
    public String getId() {

        return id;
    }



    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {

        this.id = id;
    }



    /**
     * @return the version
     */
    public long getVersion() {

        return version;
    }



    /**
     * @param version
     *            the version to set
     */
    public void setVersion(long version) {

        this.version = version;
    }



    /**
     * Creates a new instance of the {@link DomainObjectDetails} class.
     */
    public DomainObjectDetails() {

        this("");
    }

    /**
     * Creates a new instance of the {@link DomainObjectDetails} class with the
     * provided id.
     * 
     * @param id
     *            the id.
     */
    public DomainObjectDetails(final String id) {

        this.id = id;
        this.version = 0L;
    }

    /**
     * Creates a new instance of the {@link DomainObjectDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public DomainObjectDetails(final DomainObjectDetails details) {

        this.setId(details.getId());
        this.setVersion(details.getVersion());
    }
}
