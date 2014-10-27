package edu.vt.arc.vis.osnap.core.domain;

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
 * The {@link IDomainObject} class provides the contract for domain objects in
 * the core layer.
 * 
 * @author Peter J. Radics
 * @version 1.1.0
 * @since 1.1.0
 */
public interface IDomainObject {


    /**
     * Returns the id.
     * 
     * @return the id.
     */
    public String getId();

    /**
     * Sets the id.
     * 
     * @param id
     *            the id.
     */
    public void setId(final String id);

    /**
     * Returns the version.
     * 
     * @return the version.
     */
    public long getVersion();

    /**
     * Sets the version.
     * 
     * @param version
     *            the version.
     */
    public void setVersion(final long version);

    /**
     * Returns the details of the {@link IDomainObject}.
     * 
     * @return the details of the {@link IDomainObject}.
     */
    public DomainObjectDetails toDetails();
}
