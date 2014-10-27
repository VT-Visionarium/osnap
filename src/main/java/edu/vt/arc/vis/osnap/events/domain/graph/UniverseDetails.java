package edu.vt.arc.vis.osnap.events.domain.graph;

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



import java.util.List;
import java.util.Set;

import edu.vt.arc.vis.osnap.events.domain.DomainObjectDetails;
import edu.vt.arc.vis.osnap.events.domain.metadata.MetadataPropertyDetails;
import edu.vt.arc.vis.osnap.events.domain.metadata.SchemaDetails;


/**
 * @author Willy Lund
 * @version 1.1.0
 * @since 1.1.0
 */
public class UniverseDetails
        extends DomainObjectDetails {

    private MetadataPropertyDetails metadata;

    private SchemaDetails           universeSchema;
    private SchemaDetails           graphSchema;
    private SchemaDetails           nodeSchema;
    private SchemaDetails           edgeSchema;
    private SchemaDetails           hyperEdgeSchema;

    private Set<String>             ids;


    private List<GraphDetails>      graphs;
    private List<NodeDetails>       nodes;
    private List<EdgeDetails>       edges;
    private List<HyperEdgeDetails>  hyperEdges;


    /**
     * Returns the metadata.
     * 
     * @return the metadata.
     */
    public MetadataPropertyDetails getMetadata() {

        return metadata;
    }


    /**
     * Sets the metadata.
     * 
     * @param metadata
     *            the metadata.
     */
    public void setMetadata(MetadataPropertyDetails metadata) {

        this.metadata = metadata;
    }


    /**
     * Returns the universeSchema.
     * 
     * @return the universeSchema.
     */
    public SchemaDetails getUniverseSchema() {

        return universeSchema;
    }


    /**
     * Sets the universeSchema.
     * 
     * @param universeSchema
     *            the universeSchema.
     */
    public void setUniverseSchema(SchemaDetails universeSchema) {

        this.universeSchema = universeSchema;
    }


    /**
     * Returns the graphSchema.
     * 
     * @return the graphSchema.
     */
    public SchemaDetails getGraphSchema() {

        return graphSchema;
    }


    /**
     * Sets the graphSchema.
     * 
     * @param graphSchema
     *            the graphSchema.
     */
    public void setGraphSchema(SchemaDetails graphSchema) {

        this.graphSchema = graphSchema;
    }


    /**
     * Returns the nodeSchema.
     * 
     * @return the nodeSchema.
     */
    public SchemaDetails getNodeSchema() {

        return nodeSchema;
    }


    /**
     * Sets the nodeSchema.
     * 
     * @param nodeSchema
     *            the nodeSchema.
     */
    public void setNodeSchema(SchemaDetails nodeSchema) {

        this.nodeSchema = nodeSchema;
    }


    /**
     * Returns the edgeSchema.
     * 
     * @return the edgeSchema.
     */
    public SchemaDetails getEdgeSchema() {

        return edgeSchema;
    }


    /**
     * Sets the edgeSchema.
     * 
     * @param edgeSchema
     *            the edgeSchema.
     */
    public void setEdgeSchema(SchemaDetails edgeSchema) {

        this.edgeSchema = edgeSchema;
    }


    /**
     * Returns the hyperEdgeSchema.
     * 
     * @return the hyperEdgeSchema.
     */
    public SchemaDetails getHyperEdgeSchema() {

        return hyperEdgeSchema;
    }


    /**
     * Sets the hyperEdgeSchema.
     * 
     * @param hyperEdgeSchema
     *            the hyperEdgeSchema.
     */
    public void setHyperEdgeSchema(SchemaDetails hyperEdgeSchema) {

        this.hyperEdgeSchema = hyperEdgeSchema;
    }



    /**
     * Returns the ids.
     * 
     * @return the ids.
     */
    public Set<String> getIds() {

        return ids;
    }


    /**
     * Sets the ids.
     * 
     * @param ids
     *            the ids.
     */
    public void setIds(Set<String> ids) {

        this.ids = ids;
    }


    /**
     * Returns the graphs.
     * 
     * @return the graphs.
     */
    public List<GraphDetails> getGraphs() {

        return graphs;
    }


    /**
     * Sets the graphs.
     * 
     * @param graphs
     *            the graphs.
     */
    public void setGraphs(List<GraphDetails> graphs) {

        this.graphs = graphs;
    }


    /**
     * Returns the nodes.
     * 
     * @return the nodes.
     */
    public List<NodeDetails> getNodes() {

        return nodes;
    }


    /**
     * Sets the nodes.
     * 
     * @param nodes
     *            the nodes.
     */
    public void setNodes(List<NodeDetails> nodes) {

        this.nodes = nodes;
    }


    /**
     * Returns the edges.
     * 
     * @return the edges.
     */
    public List<EdgeDetails> getEdges() {

        return edges;
    }


    /**
     * Sets the edges.
     * 
     * @param edges
     *            the edges.
     */
    public void setEdges(List<EdgeDetails> edges) {

        this.edges = edges;
    }


    /**
     * Returns the hyperEddges.
     * 
     * @return the hyperEddges.
     */
    public List<HyperEdgeDetails> getHyperEdges() {

        return hyperEdges;
    }


    /**
     * Sets the hyperEdges.
     * 
     * @param hyperEdges
     *            the hyperEdges.
     */
    public void setHyperEdges(List<HyperEdgeDetails> hyperEdges) {

        this.hyperEdges = hyperEdges;
    }


    /**
     * Creates a new instance of the {@link UniverseDetails} class.
     */
    public UniverseDetails() {

        this((String) null);
    }

    /**
     * Creates a new instance of the {@link UniverseDetails} class with the
     * provided id.
     * 
     * @param id
     *            the id.
     */
    public UniverseDetails(final String id) {

        super(id);
    }

    /**
     * Creates a new instance of the {@link UniverseDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public UniverseDetails(final DomainObjectDetails details) {

        super(details);
    }

    /**
     * Creates a new instance of the {@link UniverseDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public UniverseDetails(final UniverseDetails details) {

        super(details);

        this.setMetadata(details.getMetadata());
        this.setUniverseSchema(details.getUniverseSchema());
        this.setGraphSchema(details.getGraphSchema());
        this.setNodeSchema(details.getNodeSchema());
        this.setEdgeSchema(details.getEdgeSchema());
        this.setHyperEdgeSchema(details.getHyperEdgeSchema());

        this.setIds(details.getIds());
        this.setGraphs(details.getGraphs());
        this.setNodes(details.getNodes());
        this.setEdges(details.getEdges());
        this.setHyperEdges(details.getHyperEdges());
    }
}
