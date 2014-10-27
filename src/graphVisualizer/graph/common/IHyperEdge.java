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


package graphVisualizer.graph.common;


import java.util.Set;


/**
 * The {@link IHyperEdge} interface provides the definition of a hyperedge.
 * <p/>
 * A {@link IHyperEdge hyperedge} is a connection between an arbitrary number of
 * {@link INode nodes}. Each {@link INode node} is connected to the
 * {@link IHyperEdge hyperedge} through an {@link IEndpoint endpoint}.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public interface IHyperEdge
        extends IHyperEdgeProperties, IGraphObject, IGraphMember {


    /**
     * Returns the nodes connected by this HyperEdge.
     * 
     * @return the connected nodes.
     */
    public Set<? extends INode> getNodes();

    /**
     * Returns the endpoints of this HyperEdge.
     * 
     * @return the endpoints.
     */
    public Set<? extends IEndpoint> getEndpoints();

    /**
     * Determines whether this {@link IHyperEdge} is identical to the
     * {@link IHyperEdge} provided.
     * 
     * @param other
     *            the other {@link IHyperEdge}.
     * @param recurse
     *            whether or not to recurse into nested elements.
     * @return <code>true</code>, if the {@link IHyperEdge IHyperEdges} are
     *         identical; <code>false</code> otherwise.
     */
    public boolean isIdentical(IHyperEdge other, boolean recurse);
}
