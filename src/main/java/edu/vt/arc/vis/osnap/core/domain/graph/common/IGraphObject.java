package edu.vt.arc.vis.osnap.core.domain.graph.common;

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


import java.util.Set;

import edu.vt.arc.vis.osnap.core.domain.IDomainObject;


/**
 * The {@link IGraphObject} interface provides common functionality of all
 * graph-related objects. Currently, this only contains a unique identifier.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public interface IGraphObject
        extends IDomainObject {

    /**
     * Returns the GraphObjectProperties supported by this {@link IGraphObject}.
     * 
     * @return a {@link Set} of supported {@link GraphObjectProperty
     *         GraphObjectProperties}.
     */
    public Set<GraphObjectProperty> hasGraphProperties();

    /**
     * Returns the value of the provided {@link GraphObjectProperty} (if
     * supported).
     * 
     * @param graphObjectProperty
     *            the graphObjectProperty for which the value is queried.
     * @return the value of the {@link GraphObjectProperty} or <code>null</code>
     *         , if the GraphObjectProperty is not supported by the
     *         IGraphObject.
     */
    public Object getValueOfGraphProperty(
            GraphObjectProperty graphObjectProperty);


    /**
     * Determines whether this {@link IGraphObject} is identical to the
     * {@link IGraphObject} provided.
     * 
     * @param other
     *            the other {@link IGraphObject}.
     * @return <code>true</code>, if the {@link IGraphObject IGraphObjects} are
     *         identical; <code>false</code> otherwise.
     */
    public abstract boolean isIdentical(IGraphObject other);
}
