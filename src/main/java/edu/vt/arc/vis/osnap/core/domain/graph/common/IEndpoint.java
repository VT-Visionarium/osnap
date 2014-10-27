/*******************************************************************************
 * Copyright 2014 Virginia Tech Visionarium
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/


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


/**
 * The {@link IEndpoint} interface provides the definition of all functionality
 * of an endpoint (the connection with a {@link INode node}) of an {@link IEdge
 * edge} or {@link IHyperEdge edge}.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public interface IEndpoint
        extends IEndpointProperties, IGraphObject {


    /**
     * Returns the {@link INode node} that is connected to the {@link IEdge
     * edge} or {@link IHyperEdge edge} through this {@link IEndpoint}.
     * 
     * @return the connected {@link INode node}.
     */
    public INode getNode();

    /**
     * Determines whether this {@link IEndpoint} is identical to the
     * {@link IEndpoint} provided.
     * 
     * @param other
     *            the other {@link IEndpoint}.
     * @param recurse
     *            whether or not to recurse into nested elements.
     * @return <code>true</code>, if the {@link IEndpoint IEndpoints} are
     *         identical; <code>false</code> otherwise.
     */
    public boolean isIdentical(IEndpoint other, boolean recurse);
}
