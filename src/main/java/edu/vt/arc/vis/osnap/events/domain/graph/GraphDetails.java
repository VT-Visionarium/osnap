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

import edu.vt.arc.vis.osnap.events.domain.DomainObjectDetails;
import edu.vt.arc.vis.osnap.events.domain.metadata.MetadataPropertyDetails;
import edu.vt.arc.vis.osnap.events.domain.visualization.VisualGraphDetails;


/**
 * @author Peter J. Radics
 * @version 1.1.0
 * @since 1.1.0
 *
 */
public class GraphDetails
        extends DomainObjectDetails {

    private MetadataPropertyDetails metadata;


    private List<NodeDetails>       nodes;
    private List<EdgeDetails>       edges;
    private List<HyperEdgeDetails>  hyperEdges;


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
     * Returns the hyperEdges.
     * 
     * @return the hyperEdges.
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
     * Creates a new instance of the {@link VisualGraphDetails} class.
     */
    public GraphDetails() {

        this((String) null);
    }

    /**
     * Creates a new instance of the {@link GraphDetails} class with the
     * provided id.
     * 
     * @param id
     *            the id.
     */
    public GraphDetails(final String id) {

        super(id);
    }

    /**
     * Creates a new instance of the {@link GraphDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public GraphDetails(final DomainObjectDetails details) {

        super(details);
    }

    /**
     * Creates a new instance of the {@link GraphDetails} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public GraphDetails(final GraphDetails details) {

        super(details);

        this.setMetadata(details.getMetadata());

        this.setNodes(details.getNodes());
        this.setEdges(details.getEdges());
        this.setHyperEdges(details.getHyperEdges());
    }
}