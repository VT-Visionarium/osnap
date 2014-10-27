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


import java.util.Collection;


/**
 * The <code>IGraph</code> interface provides the definition of a (mixed
 * Hyper)Graph as a collection of Nodes, Edges, and Hyperedges.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public interface IGraph
        extends IGraphProperties, IGraphObject, IUniverseMember {

    /**
     * Returns the nodes contained in the graph.
     * 
     * @return the nodes.
     */
    public Collection<? extends INode> getNodes();

    /**
     * Returns the edges contained in the graph.
     * 
     * @return the edges.
     */
    public Collection<? extends IEdge> getEdges();

    /**
     * Return the hyperedges contained in the graph.
     * 
     * @return the hyperedges.
     */
    public Collection<? extends IHyperEdge> getHyperEdges();


    /**
     * Queries the graph for the node with the provided identifier.
     * 
     * @param id
     *            The unique identifier of the node.
     * @return The node, if found. Otherwise null.
     */
    public INode getNode(String id);


    /**
     * Queries the graph for the edge with the provided identifier.
     * 
     * @param id
     *            The unique identifier of the edge to be found.
     * @return The edge, if it is found. Otherwise null.
     */
    public IEdge getEdge(String id);


    /**
     * Queries the graph for the hyperedge with the provided identifier.
     * 
     * @param id
     *            The unique identifier of the hyperedge to be found.
     * @return The hyperedge, if it is found. Otherwise null.
     */
    public IHyperEdge getHyperEdge(String id);

}
