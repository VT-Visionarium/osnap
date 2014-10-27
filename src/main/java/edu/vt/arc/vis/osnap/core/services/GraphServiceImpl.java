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



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.vt.arc.vis.osnap.core.domain.Project;
import edu.vt.arc.vis.osnap.events.NotFoundException;
import edu.vt.arc.vis.osnap.events.RequestDeniedException;
import edu.vt.arc.vis.osnap.events.RequestFailedException;
import edu.vt.arc.vis.osnap.core.domain.graph.Edge;
import edu.vt.arc.vis.osnap.core.domain.graph.Graph;
import edu.vt.arc.vis.osnap.core.domain.graph.HyperEdge;
import edu.vt.arc.vis.osnap.core.domain.graph.Node;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;


/**
 * 
 * @author Peter J. Radics
 * @version 1.1.0
 * @since 1.1.0
 */
@Service
public class GraphServiceImpl
        implements GraphService {

    private final DataStore dataStore;

    /**
     * Creates a new instance of the {@link GraphServiceImpl} class.
     * 
     * @param dataStore
     *            the data store.
     */
    @Autowired
    public GraphServiceImpl(final DataStore dataStore) {

        this.dataStore = dataStore;
    }

    @Override
    public Universe createUniverse(String projectId, Universe details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);

        project.setUniverse(details);

        return details;
    }

    @Override
    public List<Universe> retrieveAllUniverses(String projectId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {


        Project project = this.dataStore.getProject(projectId);
        Universe universe = project.getUniverse();

        List<Universe> universes = new ArrayList<>();
        universes.add(universe);

        return universes;
    }

    @Override
    public Universe retrieveUniverse(String projectId, String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);

        return this.dataStore.getUniverse(project);
    }

    @Override
    public Universe updateUniverse(String projectId, Universe details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {



        Project project = this.dataStore.getProject(projectId);
        project.setUniverse(details);

        return details;
    }

    @Override
    public Universe deleteUniverse(String projectId, String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {


        Project project = this.dataStore.getProject(projectId);
        Universe universe = project.getUniverse();

        project.setUniverse(null);

        return universe;

    }

    @Override
    public Graph createGraph(String projectId, String graphId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);
        Universe universe = this.dataStore.getUniverse(project);

        if (universe.containsID(graphId)) {

            throw new RequestFailedException("ID " + graphId
                    + " already in use!");
        }
        Graph graph = universe.createGraph(graphId);

        return graph;
    }

    @Override
    public List<Graph> retrieveAllGraphs(String projectId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);
        Universe universe = this.dataStore.getUniverse(project);

        return new ArrayList<>(universe.getGraphs());
    }

    @Override
    public Graph retrieveGraph(String projectId, String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);
        Universe universe = this.dataStore.getUniverse(project);

        return this.dataStore.getGraph(universe, id);
    }

    @Override
    public Graph updateGraph(String projectId, Graph details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);
        Universe universe = this.dataStore.getUniverse(project);

        Graph oldGraph = this.dataStore.getGraph(universe, details.getId());

        // TODO: implement

        return oldGraph;
    }

    @Override
    public Graph deleteGraph(String projectId, String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);
        Universe universe = this.dataStore.getUniverse(project);

        Graph oldGraph = this.dataStore.getGraph(universe, id);

        universe.removeGraph(oldGraph);
        return oldGraph;
    }

    @Override
    public Node createNode(String projectId, String nodeId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);
        Universe universe = this.dataStore.getUniverse(project);

        if (universe.containsID(nodeId)) {

            throw new RequestFailedException("ID " + nodeId
                    + " already in use!");
        }

        Node node = new Node(nodeId);

        universe.addNode(node);

        return node;
    }

    @Override
    public List<Node> retrieveAllNodes(String projectId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);
        Universe universe = this.dataStore.getUniverse(project);

        return new ArrayList<>(universe.getNodes());
    }

    @Override
    public Node retrieveNode(String projectId, String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);
        Universe universe = this.dataStore.getUniverse(project);

        return this.dataStore.getNode(universe, id);
    }

    @Override
    public Node updateNode(String projectId, Node details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);
        Universe universe = this.dataStore.getUniverse(project);

        Node oldNode = this.dataStore.getNode(universe, details.getId());

        // TODO: implement

        return oldNode;
    }

    @Override
    public Node deleteNode(String projectId, String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);
        Universe universe = this.dataStore.getUniverse(project);

        Node oldNode = this.dataStore.getNode(universe, id);

        universe.deleteNode(oldNode);
        return oldNode;
    }

    @Override
    public Edge createEdge(String projectId, String edgeId,
            String sourceNodeId, String targetNodeId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);
        Universe universe = this.dataStore.getUniverse(project);

        if (universe.containsID(edgeId)) {

            throw new RequestFailedException("Universe already contains ID "
                    + edgeId);
        }

        Node sourceNode = this.dataStore.getNode(universe, sourceNodeId);
        Node targetNode = this.dataStore.getNode(universe, targetNodeId);

        Edge edge = new Edge(edgeId, sourceNode, targetNode);

        universe.addEdge(edge);

        return edge;
    }

    @Override
    public List<Edge> retrieveAllEdges(String projectId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);
        Universe universe = this.dataStore.getUniverse(project);

        return new ArrayList<>(universe.getEdges());
    }

    @Override
    public Edge retrieveEdge(String projectId, String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);
        Universe universe = this.dataStore.getUniverse(project);

        return this.dataStore.getEdge(universe, id);
    }

    @Override
    public Edge updateEdge(String projectId, Edge details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);
        Universe universe = this.dataStore.getUniverse(project);

        Edge oldEdge = this.dataStore.getEdge(universe, details.getId());

        // TODO implement
        return oldEdge;
    }

    @Override
    public Edge deleteEdge(String projectId, String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);
        Universe universe = this.dataStore.getUniverse(project);

        Edge oldEdge = this.dataStore.getEdge(universe, id);
        // TODO Auto-generated method stub

        universe.removeEdge(oldEdge);

        return oldEdge;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.GraphService#createHyperEdge(java.
     * lang.String, edu.vt.arc.vis.osnap.events.domain.graph.HyperEdge)
     */
    @Override
    public HyperEdge createHyperEdge(String projectId, HyperEdge details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);
        Universe universe = this.dataStore.getUniverse(project);
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.GraphService#retrieveAllHyperEdges
     * (java.lang.String)
     */
    @Override
    public List<HyperEdge> retrieveAllHyperEdges(String projectId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);
        Universe universe = this.dataStore.getUniverse(project);
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.GraphService#retrieveHyperEdge(java
     * .lang.String, java.lang.String)
     */
    @Override
    public HyperEdge retrieveHyperEdge(String projectId, String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);
        Universe universe = this.dataStore.getUniverse(project);
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.GraphService#updateHyperEdge(java.
     * lang.String, edu.vt.arc.vis.osnap.events.domain.graph.HyperEdge)
     */
    @Override
    public HyperEdge updateHyperEdge(String projectId, HyperEdge details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);
        Universe universe = this.dataStore.getUniverse(project);
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.GraphService#deleteHyperEdge(java.
     * lang.String)
     */
    @Override
    public HyperEdge deleteHyperEdge(String projectId, String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Project project = this.dataStore.getProject(projectId);
        Universe universe = this.dataStore.getUniverse(project);
        // TODO Auto-generated method stub
        return null;
    }



}
