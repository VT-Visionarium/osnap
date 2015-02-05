package edu.vt.arc.vis.osnap.rest.controllers;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.vt.arc.vis.osnap.core.domain.Project;
import edu.vt.arc.vis.osnap.core.domain.graph.Edge;
import edu.vt.arc.vis.osnap.core.domain.graph.Graph;
import edu.vt.arc.vis.osnap.core.domain.graph.Node;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Metadata;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Schema;
import edu.vt.arc.vis.osnap.core.domain.layout.LayoutSet;
import edu.vt.arc.vis.osnap.core.services.GraphService;
import edu.vt.arc.vis.osnap.core.services.LayoutService;
import edu.vt.arc.vis.osnap.core.services.ProjectService;
import edu.vt.arc.vis.osnap.events.NotFoundException;
import edu.vt.arc.vis.osnap.events.RequestDeniedException;
import edu.vt.arc.vis.osnap.events.RequestFailedException;


/**
 * @author Peter J. Radics, William H. Lund
 * @version 1.1.0
 * @since 1.1.0
 */
@Controller
@RequestMapping("/projects")
public class ProjectQueryController {

    private Logger               LOG = LoggerFactory
                                             .getLogger(ProjectQueryController.class);

    private final ProjectService projectService;
    private final GraphService   graphService;
    private final LayoutService  layoutService;


    /**
     * Creates a new instance of the {@link ProjectQueryController} class.
     * 
     * @param projectService
     *            the project service.
     * @param graphService
     *            the graph service.
     * @param layoutService
     *            the layout service.
     */
    @Autowired
    public ProjectQueryController(final ProjectService projectService,
            final GraphService graphService, final LayoutService layoutService) {

        this.projectService = projectService;
        this.graphService = graphService;
        this.layoutService = layoutService;
    }

    /**
     * Retrieves all {@link Project Projects}.
     * 
     * @return all {@link Project Projects}.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Project>> getAllProjects() {

        try {
            List<Project> projects = this.projectService.retrieveAllProjects();

            List<Project> sanitized = new ArrayList<>(projects.size());

            for (Project project : projects) {
                sanitized.add(new Project(project));
            }
            return new ResponseEntity<>(sanitized, HttpStatus.OK);
        }
        catch (RequestDeniedException e) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (RequestFailedException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves the {@link Project} with the provided id.
     * 
     * @param id
     *            the id.
     * @return the {@link Project} with the provided id.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Project> getProject(@PathVariable String id) {

        try {

            Project project = new Project(
                    this.projectService.retrieveProject(id));
            return new ResponseEntity<>(project, HttpStatus.OK);


        }
        catch (RequestDeniedException e) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (RequestFailedException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Retrieves all {@link Universe Universes} of the {@link Project} with the
     * provided id.
     * 
     * @param id
     *            the id
     * @return all {@link Universe Universes} of the {@link Project} with the
     *         provided id.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/universes")
    public ResponseEntity<List<Universe>> getAllUniverses(
            @PathVariable String id) {

        try {

            return new ResponseEntity<>(
                    this.graphService.retrieveAllUniverses(id), HttpStatus.OK);

        }
        catch (RequestDeniedException e) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (RequestFailedException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Retrieves the requested {@link Universe} of the {@link Project} with the
     * provided id.
     * 
     * @param projectId
     *            the project id.
     * @param universeId
     *            the universe id.
     * @return all {@link Universe Universes} of the {@link Project} with the
     *         provided id.
     */
    @RequestMapping(method = RequestMethod.GET,
            value = "/{projectId}/universes/{universeId}")
    public ResponseEntity<Universe> getUniverse(@PathVariable String projectId,
            @PathVariable String universeId) {

        try {

            Universe universe = this.graphService.retrieveUniverse(projectId,
                    universeId);

            Universe copy = new Universe();
            copy.setId(universe.getId());

            return new ResponseEntity<>(copy, HttpStatus.OK);

        }
        catch (RequestDeniedException e) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (RequestFailedException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves all {@link Graph Graphs} of the {@link Project} with the
     * provided id.
     * 
     * @param projectId
     *            the project id.
     * @param universeId
     *            the universe id.
     * @return all {@link Graph Graphs} of the {@link Project} with the provided
     *         id.
     */
    @RequestMapping(method = RequestMethod.GET,
            value = "/{projectId}/universes/{universeId}/graphs")
    public ResponseEntity<List<Graph>> getAllGraphs(
            @PathVariable String projectId, @PathVariable String universeId) {

        try {

            return new ResponseEntity<>(
                    this.graphService.retrieveAllGraphs(projectId),
                    HttpStatus.OK);

        }
        catch (RequestDeniedException e) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (RequestFailedException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Retrieves the requested {@link Graph} of the {@link Universe} within the
     * {@link Project} with the provided id.
     * 
     * @param projectId
     *            the project id.
     * @param universeId
     *            the universe id.
     * @param graphId
     *            the graph id.
     * @return the requested {@link Graph} of the {@link Project} with the
     *         provided id.
     */
    @RequestMapping(method = RequestMethod.GET,
            value = "/{projectId}/universes/{universeId}/graphs/{graphId}")
    public ResponseEntity<Graph> getGraph(@PathVariable String projectId,
            @PathVariable String universeId, @PathVariable String graphId) {

        try {

            return new ResponseEntity<>(this.graphService.retrieveGraph(
                    projectId, graphId), HttpStatus.OK);

        }
        catch (RequestDeniedException e) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (RequestFailedException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves all {@link Node Nodes} of the {@link Project} with the provided
     * id.
     * 
     * @param projectId
     *            the project id.
     * @param universeId
     *            the universe id.
     * @return all {@link Node Nodes} of the {@link Project} with the provided
     *         id.
     */
    @RequestMapping(method = RequestMethod.GET,
            value = "/{projectId}/universes/{universeId}/nodes")
    public ResponseEntity<List<Node>> getAllNodes(
            @PathVariable String projectId, @PathVariable String universeId) {

        try {
            List<Node> nodes = this.graphService.retrieveAllNodes(projectId);

            List<Node> theNodes = new ArrayList<>(nodes.size());
            for (Node node : nodes) {

                Node copy = new Node(node.getId(), null, node
                        .getMetadataProperty().getSchema());

                theNodes.add(copy);
            }

            return new ResponseEntity<>(theNodes, HttpStatus.OK);

        }
        catch (RequestDeniedException e) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (RequestFailedException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves the requested {@link Node} of the {@link Universe} within the
     * {@link Project} with the provided id.
     * 
     * @param projectId
     *            the project id.
     * @param universeId
     *            the universe id.
     * @param nodeId
     *            the node id.
     * @return the requested {@link Node} of the {@link Universe} within the
     *         {@link Project} with the provided id.
     */
    @RequestMapping(method = RequestMethod.GET,
            value = "/{projectId}/universes/{universeId}/nodes/{nodeId}")
    public ResponseEntity<Node> getNode(@PathVariable String projectId,
            @PathVariable String universeId, @PathVariable String nodeId) {

        try {

            Node node = this.graphService.retrieveNode(projectId, nodeId);


            Node copy = new Node(node.getId(), null, node.getMetadataProperty()
                    .getSchema());

            for (Edge edge : node.getEdges()) {

                Schema nodeSchema = edge.getSource().getMetadataProperty()
                        .getSchema();
                Node sourceNode = new Node(edge.getSource().getId(), null,
                        nodeSchema);
                Node targetNode = new Node(edge.getTarget().getId(), null,
                        nodeSchema);
                Edge copyE = new Edge(edge.getId(), null, sourceNode,
                        targetNode, edge.isDirected(), edge
                                .getMetadataProperty().getSchema());
                copy.addEdge(copyE);
            }

            for (Metadata metadata : node.getMetadataProperty().getMetadata()) {

                copy.getMetadataProperty().addMetadata(metadata);
            }

            return new ResponseEntity<>(copy, HttpStatus.OK);

        }
        catch (RequestDeniedException e) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (RequestFailedException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves all {@link Edge Edges} of the {@link Project} with the provided
     * id.
     * 
     * @param projectId
     *            the project id.
     * @param universeId
     *            the universe id.
     * @return all {@link Edge Edges} of the {@link Project} with the provided
     *         id.
     */
    @RequestMapping(method = RequestMethod.GET,
            value = "/{projectId}/universes/{universeId}/edges")
    public ResponseEntity<List<Edge>> getAllEdges(
            @PathVariable String projectId, @PathVariable String universeId) {

        try {
            List<Edge> edges = this.graphService.retrieveAllEdges(projectId);
            List<Edge> sanitized = new ArrayList<>(edges.size());

            for (Edge edge : edges) {
                Schema nodeSchema = edge.getSource().getMetadataProperty()
                        .getSchema();
                Node sourceNode = new Node(edge.getSource().getId(), null,
                        nodeSchema);
                Node targetNode = new Node(edge.getTarget().getId(), null,
                        nodeSchema);
                Edge copy = new Edge(edge.getId(), null, sourceNode,
                        targetNode, edge.isDirected(), edge
                                .getMetadataProperty().getSchema());

                sanitized.add(copy);
            }

            return new ResponseEntity<>(sanitized, HttpStatus.OK);

        }
        catch (RequestDeniedException e) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (RequestFailedException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves the requested {@link Edge} of the {@link Universe} within the
     * {@link Project} with the provided id.
     * 
     * @param projectId
     *            the project id.
     * @param universeId
     *            the universe id.
     * @param edgeId
     *            the edge id.
     * @return the requested {@link Edge } of the {@link Universe} within the
     *         {@link Project} with the provided id.
     */
    @RequestMapping(method = RequestMethod.GET,
            value = "/{projectId}/universes/{universeId}/edges/{edgeId}")
    public ResponseEntity<Edge> getEdge(@PathVariable String projectId,
            @PathVariable String universeId, @PathVariable String edgeId) {

        try {
            Edge edge = this.graphService.retrieveEdge(projectId, edgeId);

            Schema nodeSchema = edge.getSource().getMetadataProperty()
                    .getSchema();
            Node sourceNode = new Node(edge.getSource().getId(), null,
                    nodeSchema);
            Node targetNode = new Node(edge.getTarget().getId(), null,
                    nodeSchema);
            Edge copy = new Edge(edge.getId(), null, sourceNode, targetNode,
                    edge.isDirected(), edge.getMetadataProperty().getSchema());
            
            for (Metadata metadata : edge.getMetadataProperty().getMetadata()) {

                copy.getMetadataProperty().addMetadata(metadata);
            }
            return new ResponseEntity<>(copy, HttpStatus.OK);

        }
        catch (RequestDeniedException e) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (RequestFailedException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Retrieves all {@link LayoutSet Layouts} of the {@link Project} with the
     * provided id.
     * 
     * @param id
     *            the id
     * @return all {@link LayoutSet Layouts} of the {@link Project} with the
     *         provided id.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/layouts")
    public ResponseEntity<List<LayoutSet>> getAllLayouts(@PathVariable String id) {

        try {

            return new ResponseEntity<>(
                    this.layoutService.retrieveAllLayouts(id), HttpStatus.OK);

        }
        catch (RequestDeniedException e) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (RequestFailedException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Retrieves the requested {@link LayoutSet} of the {@link Project} with the
     * provided id.
     * 
     * @param projectId
     *            the project id.
     * @param layoutId
     *            the layout id.
     * @return the requested {@link LayoutSet} of the {@link Project} with the
     *         provided id.
     */
    @RequestMapping(method = RequestMethod.GET,
            value = "/{projectId}/layouts/{layoutId}")
    public ResponseEntity<LayoutSet> getLayout(@PathVariable String projectId,
            @PathVariable String layoutId) {

        try {

            return new ResponseEntity<>(this.layoutService.retrieveLayout(
                    projectId, layoutId), HttpStatus.OK);

        }
        catch (RequestDeniedException e) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (RequestFailedException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
