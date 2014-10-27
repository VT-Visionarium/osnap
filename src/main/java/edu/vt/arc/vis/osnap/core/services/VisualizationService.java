package edu.vt.arc.vis.osnap.core.services;


import edu.vt.arc.vis.osnap.common.NotFoundException;
import edu.vt.arc.vis.osnap.common.RequestDeniedException;
import edu.vt.arc.vis.osnap.common.RequestFailedException;
import edu.vt.arc.vis.osnap.events.ProjectDetails;
import edu.vt.arc.vis.osnap.events.graph.EdgeDetails;
import edu.vt.arc.vis.osnap.events.graph.GraphDetails;
import edu.vt.arc.vis.osnap.events.graph.HyperEdgeDetails;
import edu.vt.arc.vis.osnap.events.graph.NodeDetails;
import edu.vt.arc.vis.osnap.events.graph.UniverseDetails;
import edu.vt.arc.vis.osnap.events.metadata.SchemaDetails;

import java.util.List;



/**
 * @author peter
 * @version
 * @since
 *
 */
public interface VisualizationService {

    /**
     * Creates a new {@link ProjectDetails Project}.
     * 
     * @param details
     *            the details of the request.
     * @return the details of the created object.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     */
    public abstract ProjectDetails createProject(ProjectDetails details)
            throws RequestDeniedException, RequestFailedException;

    /**
     * Returns a list of all projects.
     * 
     * @return returns list of all projects' details
     * @throws RequestDeniedException
     * @throws RequestFailedException
     * @throws NotFoundException
     */
    public abstract List<ProjectDetails> retrieveAllProjects()
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Returns single project.
     * 
     * @param id
     *            unique identification for project
     * @return returns project details of given id
     * @throws RequestDeniedException
     * @throws RequestFailedException
     * @throws NotFoundException
     */
    public abstract ProjectDetails retrieveProject(String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Updates user stated project.
     * 
     * @param id
     *            unique identification for project
     * @return returns updated project information
     * @throws RequestDeniedException
     * @throws RequestFailedException
     * @throws NotFoundException
     */
    public abstract ProjectDetails updateProject(String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Deletes project given unique project id from user.
     * 
     * @param id
     *            unique identification for project
     * @return returns deleted projects details
     */
    public abstract ProjectDetails deleteProject(String id);

    /**
     * Creates a universe.
     * 
     * @param details
     *            user defined universe details
     * @return returns newly created universe
     * @throws RequestDeniedException
     * @throws RequestFailedException
     */
    public abstract UniverseDetails createUniverse(UniverseDetails details)
            throws RequestDeniedException, RequestFailedException;

    /**
     * Returns a list of all universes.
     * 
     * @return returns list of all universes details
     * @throws RequestDeniedException
     * @throws RequestFailedException
     * @throws NotFoundException
     */
    public abstract List<UniverseDetails> retrieveAllUniverses()
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Return a single universe.
     * 
     * @param id
     *            unique identification for universe
     * @return the result of the request.
     * @throws RequestDeniedException
     * @throws RequestFailedException
     * @throws NotFoundException
     */
    public abstract UniverseDetails retrieveUniverse(String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Updates user stated universe.
     * 
     * @param id
     *            unique identification for universe
     * @return returns updated universe details
     * @throws RequestDeniedException
     * @throws RequestFailedException
     * @throws NotFoundException
     */
    public abstract UniverseDetails updateUniverse(String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Deletes user stated universe.
     * 
     * @param id
     *            unique identification for universe
     * @return returns deleted universe details
     */
    public abstract UniverseDetails deleteUniverse(String id);

    /**
     * Creates graph for the selected universe.
     * 
     * @param details
     *            user defined graph information
     * @return returns details on the new graph
     * @throws RequestDeniedException
     * @throws RequestFailedException
     */
    public abstract GraphDetails createGraph(GraphDetails details)
            throws RequestDeniedException, RequestFailedException;

    /**
     * Returns list of all graphs
     * 
     * @return list of all graphs in universe
     * @throws RequestDeniedException
     * @throws RequestFailedException
     * @throws NotFoundException
     */
    public abstract List<GraphDetails> retrieveAllGraphs()
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Returns user identified graph.
     * 
     * @param id
     *            of graph to be returned
     * @return graph details of user requested graph
     * @throws RequestDeniedException
     * @throws RequestFailedException
     * @throws NotFoundException
     */
    public abstract GraphDetails retrieveGraph(String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Updates requested graph details
     * 
     * @param id
     *            of requested graph
     * @return updated graph details
     * @throws RequestDeniedException
     * @throws RequestFailedException
     * @throws NotFoundException
     */
    public abstract GraphDetails updateGraph(String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Deletes requested graph.
     * 
     * @param id
     *            of requested graph to delete
     * @return details of deleted graph
     * @throws RequestDeniedException
     * @throws RequestFailedException
     * @throws NotFoundException
     */
    public abstract GraphDetails deleteGraph(String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Creates a new {@link NodeDetails Node}.
     * 
     * @param details
     *            the details of the request.
     * @return the details of the created object.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     */
    public abstract NodeDetails createNode(NodeDetails details)
            throws RequestDeniedException, RequestFailedException;

    /**
     * Returns a {@link List} of all {@list NodeDetails Nodes}.
     * 
     * @return a {@link List} of all {@list NodeDetails Nodes}.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     * @throws NotFoundException
     *             if the object was not found.
     */
    public abstract List<NodeDetails> retrieveAllNodes()
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Returns a single {@link NodeDetails Node}.
     * 
     * @param id
     *            the id.
     * @return the details of the requested object.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     * @throws NotFoundException
     *             if the object was not found.
     */
    public abstract NodeDetails retrieveNode(String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Updates a single {@link NodeDetails Node}.
     * 
     * @param details
     *            the details of the request.
     * @return the details of the updated object.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     * @throws NotFoundException
     *             if the object was not found.
     */
    public abstract NodeDetails updateNode(NodeDetails details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Deletes a single {@link NodeDetails Node}.
     * 
     * @param id
     *            the id.
     * @return the details of the deleted object.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     * @throws NotFoundException
     *             if the object was not found.
     */
    public abstract NodeDetails deleteNode(String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Creates a new {@link EdgeDetails Edge}.
     * 
     * @param details
     *            the details of the request.
     * @return the details of the created object.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     */
    public abstract EdgeDetails createEdge(EdgeDetails details)
            throws RequestDeniedException, RequestFailedException;

    /**
     * Returns a {@link List} of all {@list EdgeDetails Edges}.
     * 
     * @return a {@link List} of all {@list EdgeDetails Edges}.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     * @throws NotFoundException
     *             if the object was not found.
     */
    public abstract List<EdgeDetails> retrieveAllEdges()
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Returns a single {@link EdgeDetails Edge}.
     * 
     * @param id
     *            the id.
     * @return the details of the requested object.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     * @throws NotFoundException
     *             if the object was not found.
     */
    public abstract EdgeDetails retrieveEdge(String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Updates a single {@link EdgeDetails Edge}.
     * 
     * @param details
     *            the details of the request.
     * @return the details of the updated object.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     * @throws NotFoundException
     *             if the object was not found.
     */
    public abstract EdgeDetails updateEdge(EdgeDetails details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Deletes a single {@link EdgeDetails Edge}.
     * 
     * @param id
     *            the id.
     * @return the details of the deleted object.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     * @throws NotFoundException
     *             if the object was not found.
     */
    public abstract EdgeDetails deleteEdge(String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Creates a new {@link HyperEdgeDetails HyperEdge}.
     * 
     * @param details
     *            the details of the request.
     * @return the details of the created object.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     */
    public abstract HyperEdgeDetails createHyperEdge(HyperEdgeDetails details)
            throws RequestDeniedException, RequestFailedException;

    /**
     * Returns a {@link List} of all {@list HyperEdgeDetails HyperEdges}.
     * 
     * @return a {@link List} of all {@list HyperEdgeDetails HyperEdges}.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     * @throws NotFoundException
     *             if the object was not found.
     */
    public abstract List<HyperEdgeDetails> retrieveAllHyperEdges()
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Returns a single {@link HyperEdgeDetails HyperEdge}.
     * 
     * @param id
     *            the id.
     * @return the details of the requested object.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     * @throws NotFoundException
     *             if the object was not found.
     */
    public abstract HyperEdgeDetails retrieveHyperEdge(String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Updates a single {@link HyperEdgeDetails HyperEdge}.
     * 
     * @param details
     *            the details of the request.
     * @return the details of the updated object.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     * @throws NotFoundException
     *             if the object was not found.
     */
    public abstract HyperEdgeDetails updateHyperEdge(HyperEdgeDetails details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Deletes a single {@link HyperEdgeDetails HyperEdge}.
     * 
     * @param id
     *            the id.
     * @return the details of the deleted object.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     * @throws NotFoundException
     *             if the object was not found.
     */
    public abstract HyperEdgeDetails deleteHyperEdge(String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Creates a new {@link SchemaDetails Schema}.
     * 
     * @param details
     *            the details of the request.
     * @return the details of the created object.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     */
    public abstract SchemaDetails createSchema(SchemaDetails details)
            throws RequestDeniedException, RequestFailedException;

    /**
     * Returns a {@link List} of all {@list SchemaDetails Schemas}.
     * 
     * @return a {@link List} of all {@list SchemaDetails Schemas}.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     * @throws NotFoundException
     *             if the object was not found.
     */
    public abstract List<SchemaDetails> retrieveAllSchemas()
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Returns a single {@link SchemaDetails Schema}.
     * 
     * @param id
     *            the id.
     * @return the details of the requested object.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     * @throws NotFoundException
     *             if the object was not found.
     */
    public abstract SchemaDetails retrieveSchema(String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Updates a single {@link SchemaDetails Schema}.
     * 
     * @param details
     *            the details of the request.
     * @return the details of the updated object.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     * @throws NotFoundException
     *             if the object was not found.
     */
    public abstract SchemaDetails updateSchema(SchemaDetails details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

    /**
     * Deletes a single {@link SchemaDetails Schema}.
     * 
     * @param id
     *            the id.
     * @return the details of the deleted object.
     * @throws RequestDeniedException
     *             if the request was denied.
     * @throws RequestFailedException
     *             if the request fails.
     * @throws NotFoundException
     *             if the object was not found.
     */
    public abstract SchemaDetails deleteSchema(String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException;

}