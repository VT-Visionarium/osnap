package edu.vt.arc.vis.osnap.rest.controllers.fixtures;


import edu.vt.arc.vis.osnap.events.ProjectDetails;
import edu.vt.arc.vis.osnap.events.graph.EdgeDetails;
import edu.vt.arc.vis.osnap.events.graph.GraphDetails;
import edu.vt.arc.vis.osnap.events.graph.NodeDetails;
import edu.vt.arc.vis.osnap.events.graph.UniverseDetails;
import edu.vt.arc.vis.osnap.events.metadata.SchemaDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Creates fixture for testing purposes.
 * 
 * @author whlund15
 *
 */
public class DataFixture {

    
    public static String defaultProjectId = "ProjectId";
    
    // -------------------------------------------------------------------------
    // RETRIEVE ALL PROJECTS/UNIVERSES/GRAPHS/NODES/EDGES/SCHEMA/METADATA
    // -------------------------------------------------------------------------

    /**
     * Test fixture to create several {@link ProjectDetails Projects} and return
     * a list of {@link ProjectDetails ProjectDetails}.
     * 
     * @return returns the details of the requested object.
     */
    public static List<ProjectDetails> allProjects() {

        // TODO: figure out what to return instead of event
        List<ProjectDetails> projectList = new ArrayList<>();
        projectList.add(standardProjectDetails());
        projectList.add(standardProjectDetails());
        projectList.add(standardProjectDetails());
        projectList.add(standardProjectDetails());
        // TODO: Return our equivalent to an all projects event.
        return null;
    }

    /**
     * Test fixture to create several {@link UniverseDetails Universes} and
     * return a list of {@link UniverseDetails UniverseDetails}.
     * 
     * @return returns the details of the requested object.
     */
    public static List<UniverseDetails> allUniverses() {

        // TODO: figure out what to return instead of event
        List<UniverseDetails> universeList = new ArrayList<>();
        universeList.add(standardUniverseDetails());
        universeList.add(standardUniverseDetails());
        universeList.add(standardUniverseDetails());
        return null;
    }

    /**
     * Test fixture to create several {@link GraphDetails Graphs} and return a
     * list of {@link GraphDetails GraphDetails}.
     * 
     * @return returns the details of the requested object.
     */
    public static List<GraphDetails> allGraphs() {

        // TODO: figure out what to return instead of event
        List<GraphDetails> graphList = new ArrayList<>();
        graphList.add(standardGraphDetails());
        graphList.add(standardGraphDetails());
        graphList.add(standardGraphDetails());
        graphList.add(standardGraphDetails());
        return null;

    }

    /**
     * Test fixture to create several {@link NodeDetails Nodes} and return a
     * list of {@link NodeDetails NodeDetails}.
     * 
     * @return returns the details of the requested object.
     */
    public static List<NodeDetails> allNodes() {

        // TODO: figure out what to return instead of event
        List<NodeDetails> nodeList = new ArrayList<>();
        nodeList.add(standardNodeDetails());
        nodeList.add(standardNodeDetails());
        nodeList.add(standardNodeDetails());
        nodeList.add(standardNodeDetails());

        return null;
    }

    /**
     * Test fixture to create several {@link EdgeDetails Edges} and return a
     * list of {@link EdgeDetails EdgeDetails}.
     * 
     * @return returns the details of the requested object.
     */
    public static List<EdgeDetails> allEdges() {

        // TODO: figure out what to return instead of event
        List<EdgeDetails> edgeList = new ArrayList<>();
        edgeList.add(standardEdgeDetails());
        edgeList.add(standardEdgeDetails());
        edgeList.add(standardEdgeDetails());
        edgeList.add(standardEdgeDetails());
        return null;
    }

    /**
     * Test fixture to create several {@link SchemaDetails Schema} and return a
     * list of {@link SchemaDetails SchemaDetails}.
     * 
     * @return returns the details of the requested object.
     */
    public static List<SchemaDetails> allSchema() {

        // TODO: figure out what to return instead of event
        List<SchemaDetails> schemaList = new ArrayList<>();
        schemaList.add(standardSchemaDetails());
        schemaList.add(standardSchemaDetails());
        schemaList.add(standardSchemaDetails());
        schemaList.add(standardSchemaDetails());
        return null;
    }


    // -------------------------------------------------------------------------
    // RETRIEVE SINGLE PROJECT/UNIVERSE/GRAPH/NODE/EDGE/SCHEMA/METADATA
    // -------------------------------------------------------------------------

    /**
     * Test fixture to create single {@link ProjectDetails Project} and return
     * details of newly created {@link ProjectDetails Project}.
     * 
     * @return returns the details of the requested object.
     */
    public static ProjectDetails singleProject() {

        // TODO: figure out what to return instead of event
        standardProjectDetails();
        // TODO: Return our equivalent to an all projects event.
        return null;
    }

    /**
     * Test fixture to create single {@link UniverseDetails Universe} and return
     * details of newly created {@link UniverseDetails Universe}.
     * 
     * @return returns the details of the requested object.
     */
    public static UniverseDetails singleUniverse() {

        // TODO: figure out what to return instead of event
        standardUniverseDetails();
        return null;
    }

    /**
     * Test fixture to create single {@link GraphDetails Graph} and return
     * details of newly created {@link GraphDetails Graph}.
     * 
     * @return returns the details of the requested object.
     */
    public static GraphDetails singleGraph() {

        // TODO: figure out what to return instead of event
        standardGraphDetails();
        return null;

    }

    /**
     * Test fixture to create single {@link NodeDetails Node} and return details
     * of newly created {@link NodeDetails Node}.
     * 
     * @return returns the details of the requested object.
     */
    public static NodeDetails singleNode() {

        // TODO: figure out what to return instead of event
        standardNodeDetails();

        return null;
    }

    /**
     * Test fixture to create single {@link EdgeDetails Edge} and return details
     * of newly created {@link EdgeDetails Edge}.
     * 
     * @return returns the details of the requested object.
     */
    public static EdgeDetails singleEdges() {

        // TODO: figure out what to return instead of event
        standardEdgeDetails();
        return null;
    }

    /**
     * Test fixture to create single {@link SchemaDetails Schema} and return a
     * details of newly created {@link SchemaDetails Schema}.
     * 
     * @return returns the details of the requested object.
     */
    public static SchemaDetails singleSchema() {

        // TODO: figure out what to return instead of event
        standardSchemaDetails();
        return null;
    }

    // -------------------------------------------------------------------------
    // MULTI-USE CREATION METHODS
    // -------------------------------------------------------------------------

    private static ProjectDetails standardProjectDetails() {

        return customKeyProjectDetails(DataFixture.defaultProjectId);
    }

    private static ProjectDetails customKeyProjectDetails(String id) {

        ProjectDetails projectDetails = new ProjectDetails(
                id);
        
        projectDetails.setUniverse(DataFixture.standardUniverseDetails());
        
        return projectDetails;
    }

    private static UniverseDetails standardUniverseDetails() {

        // TODO Not UUID, but ask Peter. This is just a placeholder.
        return customKeyUniverseDetails(UUID.randomUUID());
    }

    private static UniverseDetails customKeyUniverseDetails(UUID randomUUID) {

        UniverseDetails universeDetails = new UniverseDetails(
                randomUUID.toString());
        // TODO Start setting universe detail values
        // TODO Auto-generated method stub
        return null;
    }

    private static GraphDetails standardGraphDetails() {

        // TODO Not UUID, but ask Peter. This is just a placeholder.
        return customKeyGraphDetails(UUID.randomUUID());
    }

    private static GraphDetails customKeyGraphDetails(UUID randomUUID) {

        GraphDetails graphDetails = new GraphDetails(randomUUID.toString());
        // TODO Start setting graph detail values
        return null;
    }

    private static NodeDetails standardNodeDetails() {

        // TODO Not UUID, but ask Peter. This is just a placeholder.
        return customKeyNodeDetails(UUID.randomUUID());
    }

    private static NodeDetails customKeyNodeDetails(UUID randomUUID) {

        NodeDetails nodeDetails = new NodeDetails(randomUUID.toString());
        // TODO Start setting node detail values
        return null;
    }

    private static EdgeDetails standardEdgeDetails() {

        // TODO Not UUID, but ask Peter. This is just a placeholder.
        return customKeyEdgeDetails(UUID.randomUUID());
    }

    private static EdgeDetails customKeyEdgeDetails(UUID randomUUID) {

        EdgeDetails edgeDetails = new EdgeDetails(randomUUID.toString());
        // TODO Start setting edge detail values
        return null;
    }

    private static SchemaDetails standardSchemaDetails() {

        // TODO Not UUID, but ask Peter. This is just a placeholder.
        return customKeySchemaDetails(UUID.randomUUID());
    }

    private static SchemaDetails customKeySchemaDetails(UUID randomUUID) {

        SchemaDetails schemaDetails = new SchemaDetails(randomUUID.toString());
        // TODO Start setting schema detail values
        return null;
    }

    public static ProjectDetails SingleProject() {

        // TODO Auto-generated method stub
        return null;
    }
}
