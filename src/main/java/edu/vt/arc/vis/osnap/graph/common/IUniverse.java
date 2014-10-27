/*******************************************************************************
 * Copyright 2014 Virginia Tech Visionarium
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/


package edu.vt.arc.vis.osnap.graph.common;


import java.util.Collection;

import edu.vt.arc.vis.osnap.graph.base.GraphObjectBase;


/**
 * The <CODE>IUniverse</CODE> interface provides a contract for classes modeling
 * graph-theoretical containers capable of containing multiple mixed
 * hypergraphs.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public interface IUniverse
        extends IUniverseProperties, IGraphObject {

    /**
     * Returns a collection of all the graphs within this universe.
     * 
     * @return the graphs.
     */
    public Collection<? extends IGraph> getGraphs();

    /**
     * Queries the graph for the graph with the provided identifier.
     * 
     * @param id
     *            The unique identifier of the graph.
     * @return The graph, if found. Otherwise null.
     */
    public IGraph getGraph(String id);

    /**
     * Returns a collection of all the nodes within this universe.
     * 
     * @return the nodes.
     */
    public Collection<? extends INode> getNodes();

    /**
     * Queries the graph for the node with the provided identifier.
     * 
     * @param id
     *            The unique identifier of the node.
     * @return The node, if found. Otherwise null.
     */
    public INode getNode(String id);

    /**
     * Returns a collection of all the edges within this universe.
     * 
     * @return the edges.
     */
    public Collection<? extends IEdge> getEdges();

    /**
     * Queries the graph for the edge with the provided identifier.
     * 
     * @param id
     *            The unique identifier of the edge to be found.
     * @return The edge, if it is found. Otherwise null.
     */
    public IEdge getEdge(String id);

    /**
     * Returns a collection of all the hyperedges within this universe.
     * 
     * @return the hyperedges.
     */
    public Collection<? extends IHyperEdge> getHyperEdges();

    /**
     * Queries the graph for the edge with the provided identifier.
     * 
     * @param id
     *            The unique identifier of the edge to be found.
     * @return The edge, if it is found. Otherwise null.
     */
    public IHyperEdge getHyperEdge(String id);

    /**
     * Determines whether or not the provided ID is already in use.
     * 
     * @param id
     *            the ID to check.
     * @return {@code true}, if the ID is already in use; {@code false}
     *         otherwise.
     */
    public boolean containsID(String id);

    /**
     * Determines whether changing the ID of the {@link GraphObjectBase
     * GraphObject}, if possible (and necessary).
     * 
     * @param graphObject
     *            the graph object to change.
     * @param newValue
     *            the new ID value.
     * @return whether or not a change is possible or necessary.
     */
    public boolean changeID(IGraphObject graphObject, String newValue);

}
