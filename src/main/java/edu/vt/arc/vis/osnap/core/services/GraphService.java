package edu.vt.arc.vis.osnap.core.services;

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



import edu.vt.arc.vis.osnap.core.domain.graph.Edge;
import edu.vt.arc.vis.osnap.core.domain.graph.Graph;
import edu.vt.arc.vis.osnap.core.domain.graph.HyperEdge;
import edu.vt.arc.vis.osnap.core.domain.graph.Node;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.events.NotFoundException;
import edu.vt.arc.vis.osnap.events.RequestDeniedException;
import edu.vt.arc.vis.osnap.events.RequestFailedException;

import java.util.List;



/**
 * @author Peter J. Radics
 * @version 1.1.0
 * @since 1.1.0
 */
public interface GraphService {

    /**
     * Creates a {@link Universe Universe}.
     * 
     * @param projectId
     *            the project id.
     * @param details
     *            the details of the request.
     * @return the result of the request.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if the project was not found.
     */
    public abstract Universe createUniverse(String projectId, Universe details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Returns a list of all {@link Universe Universes}.
     * 
     * @param projectId
     *            the project id.
     * 
     * @return returns the result of the request.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if no objects were found.
     */
    public abstract List<Universe> retrieveAllUniverses(String projectId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Return a single {@link Universe Universe}.
     * 
     * @param projectId
     *            the project id.
     * @param id
     *            the id of the object to retrieve.
     * @return the result of the request.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if no object was found.
     */
    public abstract Universe retrieveUniverse(String projectId, String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Updates a {@link Universe Universe}.
     * 
     * @param projectId
     *            the project id.
     * @param details
     *            the details of the request
     * @return returns updated universe details
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if no object was found.
     */
    public abstract Universe updateUniverse(String projectId, Universe details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Deletes a {@link Universe Universe}.
     * 
     * @param projectId
     *            the project id.
     * @param id
     *            unique identification for universe
     * @return returns deleted universe details
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if no object was found.
     */
    public abstract Universe deleteUniverse(String projectId, String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;;



    /**
     * Creates a {@link Graph Graph}.
     * 
     * @param projectId
     *            the project id.
     * @param graphId
     *            the id of the graph to create.
     * @return the result of the request.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if the project was not found.
     */
    public abstract Graph createGraph(String projectId, String graphId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Returns a list of all {@link Graph Graphs}.
     * 
     * @param projectId
     *            the project id.
     * 
     * @return returns the result of the request.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if no objects were found.
     */
    public abstract List<Graph> retrieveAllGraphs(String projectId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Return a single {@link Graph Graph}.
     * 
     * @param projectId
     *            the project id.
     * @param id
     *            the id of the object to retrieve.
     * @return the result of the request.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if no object was found.
     */
    public abstract Graph retrieveGraph(String projectId, String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Updates a {@link Graph Graph}.
     * 
     * @param projectId
     *            the project id.
     * @param details
     *            the details of the request
     * @return returns updated universe details
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if no object was found.
     */
    public abstract Graph updateGraph(String projectId, Graph details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Deletes a {@link Graph Graph}.
     * 
     * @param projectId
     *            the project id.
     * @param id
     *            unique identification for universe
     * @return returns deleted universe details
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if no object was found.
     */
    public abstract Graph deleteGraph(String projectId, String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;;



    /**
     * Creates a {@link Node Node}.
     * 
     * @param projectId
     *            the project id.
     * @param nodeId
     *            the id of the node to create.
     * @return the result of the request.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if the project was not found.
     */
    public abstract Node createNode(String projectId, String nodeId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Returns a list of all {@link Node Nodes}.
     * 
     * @param projectId
     *            the project id.
     * 
     * @return returns the result of the request.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if no objects were found.
     */
    public abstract List<Node> retrieveAllNodes(String projectId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Return a single {@link Node Node}.
     * 
     * @param projectId
     *            the project id.
     * @param id
     *            the id of the object to retrieve.
     * @return the result of the request.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if no object was found.
     */
    public abstract Node retrieveNode(String projectId, String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Updates a {@link Node Node}.
     * 
     * @param projectId
     *            the project id.
     * @param details
     *            the details of the request
     * @return returns updated universe details
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if no object was found.
     */
    public abstract Node updateNode(String projectId, Node details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Deletes a {@link Node Node}.
     * 
     * @param projectId
     *            the project id.
     * @param id
     *            unique identification for universe
     * @return returns deleted universe details
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if no object was found.
     */
    public abstract Node deleteNode(String projectId, String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;;



    /**
     * Creates a {@link Edge Edge}.
     * 
     * @param projectId
     *            the project id.
     * @param edgeId
     *            the id of the edge to create.
     * @param sourceNodeId
     *            the id of the source node.
     * @param targetNodeId
     *            the id of the target node.
     * @return the result of the request.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if the project was not found.
     */
    public abstract Edge createEdge(String projectId, String edgeId,
            String sourceNodeId, String targetNodeId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Returns a list of all {@link Edge Edges}.
     * 
     * @param projectId
     *            the project id.
     * 
     * @return returns the result of the request.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if no objects were found.
     */
    public abstract List<Edge> retrieveAllEdges(String projectId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Return a single {@link Edge Edge}.
     * 
     * @param projectId
     *            the project id.
     * @param id
     *            the id of the object to retrieve.
     * @return the result of the request.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if no object was found.
     */
    public abstract Edge retrieveEdge(String projectId, String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Updates a {@link Edge Edge}.
     * 
     * @param projectId
     *            the project id.
     * @param details
     *            the details of the request
     * @return returns updated universe details
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if no object was found.
     */
    public abstract Edge updateEdge(String projectId, Edge details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Deletes a {@link Edge Edge}.
     * 
     * @param projectId
     *            the project id.
     * @param id
     *            unique identification for universe
     * @return returns deleted universe details
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if no object was found.
     */
    public abstract Edge deleteEdge(String projectId, String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;;



    /**
     * Creates a {@link HyperEdge HyperEdge}.
     * 
     * @param projectId
     *            the project id.
     * @param details
     *            the details of the request.
     * @return the result of the request.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if the project was not found.
     */
    public abstract HyperEdge createHyperEdge(String projectId,
            HyperEdge details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Returns a list of all {@link HyperEdge HyperEdges}.
     * 
     * @param projectId
     *            the project id.
     * 
     * @return returns the result of the request.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if no objects were found.
     */
    public abstract List<HyperEdge> retrieveAllHyperEdges(String projectId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Return a single {@link HyperEdge HyperEdge}.
     * 
     * @param projectId
     *            the project id.
     * @param id
     *            the id of the object to retrieve.
     * @return the result of the request.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if no object was found.
     */
    public abstract HyperEdge retrieveHyperEdge(String projectId, String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Updates a {@link HyperEdge HyperEdge}.
     * 
     * @param projectId
     *            the project id.
     * @param details
     *            the details of the request
     * @return returns updated universe details
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if no object was found.
     */
    public abstract HyperEdge updateHyperEdge(String projectId,
            HyperEdge details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Deletes a {@link HyperEdge HyperEdge}.
     * 
     * @param projectId
     *            the project id.
     * @param id
     *            unique identification for universe
     * @return returns deleted universe details
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request failed.
     * @throws NotFoundException
     *             if no object was found.
     */
    public abstract HyperEdge deleteHyperEdge(String projectId, String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;;


}