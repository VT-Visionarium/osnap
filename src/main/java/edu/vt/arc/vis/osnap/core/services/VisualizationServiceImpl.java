package edu.vt.arc.vis.osnap.core.services;


import java.util.List;

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


/**
 * 
 * @author Willy Lund
 *
 */
public class VisualizationServiceImpl
        implements VisualizationService {

    
    
    
    
    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.core.services.VisualizationService#createProject(
     * edu.vt.arc.vis.osnap.events.ProjectDetails)
     */
    @Override
    public ProjectDetails createProject(final ProjectDetails details)
            throws RequestDeniedException, RequestFailedException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#retrieveAllProjects()
     */
    @Override
    public List<ProjectDetails> retrieveAllProjects()
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#retrieveProject(java
     * .lang.String)
     */
    @Override
    public ProjectDetails retrieveProject(final String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#updateProject(java
     * .lang.String)
     */
    @Override
    public ProjectDetails updateProject(final String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        // TODO: do something awesome
        return null;

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#deleteProject(java
     * .lang.String)
     */
    @Override
    public ProjectDetails deleteProject(final String id) {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.core.services.VisualizationService#createUniverse(
     * edu.vt.arc.vis.osnap.events.graph.UniverseDetails)
     */
    @Override
    public UniverseDetails createUniverse(final UniverseDetails details)
            throws RequestDeniedException, RequestFailedException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#retrieveAllUniverses()
     */
    @Override
    public List<UniverseDetails> retrieveAllUniverses()
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#retrieveUniverse(java
     * .lang.String)
     */
    @Override
    public UniverseDetails retrieveUniverse(final String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#updateUniverse(java
     * .lang.String)
     */
    @Override
    public UniverseDetails updateUniverse(final String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#deleteUniverse(java
     * .lang.String)
     */
    @Override
    public UniverseDetails deleteUniverse(final String id) {

        // TODO: do something awesome.
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.core.services.VisualizationService#createGraph(
     * edu.vt.arc.vis.osnap.events.graph.GraphDetails)
     */
    @Override
    public GraphDetails createGraph(final GraphDetails details)
            throws RequestDeniedException, RequestFailedException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#retrieveAllGraphs()
     */
    @Override
    public List<GraphDetails> retrieveAllGraphs()
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#retrieveGraph(java
     * .lang.String)
     */
    @Override
    public GraphDetails retrieveGraph(final String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#updateGraph(java.lang
     * .String)
     */
    @Override
    public GraphDetails updateGraph(final String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#deleteGraph(java.lang
     * .String)
     */
    @Override
    public GraphDetails deleteGraph(final String id) {

        // TODO: do something awesome
        return null;
    }



    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#createNode(edu.vt.arc.vis.osnap
     * .events.graph.NodeDetails)
     */
    @Override
    public NodeDetails createNode(final NodeDetails details)
            throws RequestDeniedException, RequestFailedException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#retrieveAllNodes()
     */
    @Override
    public List<NodeDetails> retrieveAllNodes()
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#retrieveNode(java.
     * lang.String)
     */
    @Override
    public NodeDetails retrieveNode(final String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#updateNode(edu.vt.arc.vis.osnap
     * .events.graph.NodeDetails)
     */
    @Override
    public NodeDetails updateNode(final NodeDetails details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#deleteNode(java.lang
     * .String)
     */
    @Override
    public NodeDetails deleteNode(final String id) {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#createEdge(edu.vt.arc.vis.osnap
     * .events.graph.EdgeDetails)
     */
    @Override
    public EdgeDetails createEdge(final EdgeDetails details)
            throws RequestDeniedException, RequestFailedException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#retrieveAllEdges()
     */
    @Override
    public List<EdgeDetails> retrieveAllEdges()
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#retrieveEdge(java.
     * lang.String)
     */
    @Override
    public EdgeDetails retrieveEdge(final String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#updateEdge(edu.vt.arc.vis.osnap
     * .events.graph.EdgeDetails)
     */
    @Override
    public EdgeDetails updateEdge(final EdgeDetails details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#deleteEdge(java.lang
     * .String)
     */
    @Override
    public EdgeDetails deleteEdge(final String id) {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.core.services.VisualizationService#createHyperEdge(
     * edu.vt.arc.vis.osnap.events.graph.HyperEdgeDetails)
     */
    @Override
    public HyperEdgeDetails createHyperEdge(final HyperEdgeDetails details)
            throws RequestDeniedException, RequestFailedException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#retrieveAllHyperEdges
     * ()
     */
    @Override
    public List<HyperEdgeDetails> retrieveAllHyperEdges()
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#retrieveHyperEdge(
     * java.lang.String)
     */
    @Override
    public HyperEdgeDetails retrieveHyperEdge(final String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.core.services.VisualizationService#updateHyperEdge(
     * edu.vt.arc.vis.osnap.events.graph.HyperEdgeDetails)
     */
    @Override
    public HyperEdgeDetails updateHyperEdge(final HyperEdgeDetails details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#deleteHyperEdge(java
     * .lang.String)
     */
    @Override
    public HyperEdgeDetails deleteHyperEdge(final String id) {

        // TODO: do something awesome
        return null;
    }



    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.core.services.VisualizationService#createSchema(
     * edu.vt.arc.vis.osnap.events.metadata.SchemaDetails)
     */
    @Override
    public SchemaDetails createSchema(final SchemaDetails details)
            throws RequestDeniedException, RequestFailedException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#retrieveAllSchemas()
     */
    @Override
    public List<SchemaDetails> retrieveAllSchemas()
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#retrieveSchema(java
     * .lang.String)
     */
    @Override
    public SchemaDetails retrieveSchema(final String id)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.core.services.VisualizationService#updateSchema(
     * edu.vt.arc.vis.osnap.events.metadata.SchemaDetails)
     */
    @Override
    public SchemaDetails updateSchema(final SchemaDetails details)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        // TODO: do something awesome
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.VisualizationService#deleteSchema(java.
     * lang.String)
     */
    @Override
    public SchemaDetails deleteSchema(final String id) {

        // TODO: do something awesome
        return null;
    }

}
