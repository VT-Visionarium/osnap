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


import java.util.Set;


/**
 * The {@link INode} interface provides the definition of a Node.
 * <p/>
 * A {@link INode node} is characterized by its connections to other
 * {@link INode nodes} through {@link IEdge edges} or {@link IHyperEdge edges}.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public interface INode
        extends INodeProperties, IGraphObject, IGraphMember {

    /**
     * Returns the Edges connected with this node.
     * 
     * @return the connected edges.
     */
    public Set<? extends IEdge> getEdges();

    /**
     * Returns the HyperEdges connected with this node.
     * 
     * @return the connected hyperedges.
     */
    public Set<? extends IHyperEdge> getHyperEdges();


    /**
     * Determines whether this {@link INode} is identical to the {@link INode}
     * provided.
     * 
     * @param other
     *            the other {@link INode}.
     * @param recurse
     *            whether or not to recurse into nested elements.
     * @return <code>true</code>, if the {@link INode INodes} are identical;
     *         <code>false</code> otherwise.
     */
    public boolean isIdentical(INode other, boolean recurse);
}
