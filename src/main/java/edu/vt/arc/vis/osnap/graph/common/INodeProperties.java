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


package edu.vt.arc.vis.osnap.graph.common;


/**
 * The <code>INodeProperties</code> interface provides the definition of Node
 * properties.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public interface INodeProperties {


    /**
     * Returns the number of edges and hyperedges connected to this node (its
     * degree).
     * 
     * @return the number of edges and hyperedges connected to this node.
     */
    public long degree();

    /**
     * Returns the number of incoming edges and hyperedges connected to this
     * node (its in-degree).
     * 
     * @return the number of incoming edges and hyperedges connected to this
     *         node.
     */
    public long inDegree();

    /**
     * Returns the number of outgoing edges and hyperedges connected to this
     * node (its out-degree).
     * 
     * @return the number of outgoing edges and hyperedges connected to this
     *         node.
     */
    public long outDegree();

    /**
     * Returns the number of edges connected to this node (its edge degree).
     * 
     * @return the number of edges connected to this node.
     */
    public long edgeDegree();

    /**
     * Returns the number of incoming edges connected to this node (its edge
     * in-degree).
     * 
     * @return the number of incoming edges connected to this node.
     */
    public long edgeInDegree();

    /**
     * Returns the number of outgoing edges connected to this node (its edge
     * out-degree).
     * 
     * @return the number of outgoing edges connected to this node.
     */
    public long edgeOutDegree();

    /**
     * Returns the number of hyperedges connected to this node (its hyperedge
     * degree).
     * 
     * @return the number of hyperedges connected to this node.
     */
    public long hyperEdgeDegree();

    /**
     * Returns the number of incoming hyperedges connected to this node (its
     * hyperedge in-degree).
     * 
     * @return the number of incoming hyperedges connected to this node.
     */
    public long hyperEdgeInDegree();

    /**
     * Returns the number of outgoing hyperedges connected to this node (its
     * hyperedge out-degree).
     * 
     * @return the number of outgoing hyperedges connected to this node.
     */
    public long hyperEdgeOutDegree();

}
