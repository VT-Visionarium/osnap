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

import edu.vt.arc.vis.osnap.core.domain.graph.Edge;
import edu.vt.arc.vis.osnap.core.domain.graph.Graph;
import edu.vt.arc.vis.osnap.core.domain.graph.Node;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Metadata;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Schema;
import edu.vt.arc.vis.osnap.events.NotFoundException;
import edu.vt.arc.vis.osnap.events.RequestDeniedException;
import edu.vt.arc.vis.osnap.events.RequestFailedException;


/**
 * @author Peter J. Radics
 * @version 0.1
 * @since 0.1
 *
 */
@Service
public class MetadataServiceImpl
        implements MetadataService {

    private final DataStore dataStore;

    /**
     * Creates a new instance of the {@link MetadataServiceImpl} class.
     * 
     * @param dataStore
     *            the data store.
     */
    @Autowired
    public MetadataServiceImpl(final DataStore dataStore) {

        this.dataStore = dataStore;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.MetadataService#retrieveUniverseSchema
     * (java.lang.String, java.lang.String)
     */
    @Override
    public Schema retrieveUniverseSchema(String projectId, String universeId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Universe universe = this.dataStore.getUniverse(this.dataStore
                .getProject(projectId));

        return universe.getUniverseSchema();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.MetadataService#retrieveGraphSchema
     * (java.lang.String, java.lang.String)
     */
    @Override
    public Schema retrieveGraphSchema(String projectId, String universeId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {


        Universe universe = this.dataStore.getUniverse(this.dataStore
                .getProject(projectId));

        return universe.getGraphSchema();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.MetadataService#retrieveNodeSchema
     * (java.lang.String, java.lang.String)
     */
    @Override
    public Schema retrieveNodeSchema(String projectId, String universeId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {


        Universe universe = this.dataStore.getUniverse(this.dataStore
                .getProject(projectId));

        return universe.getNodeSchema();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.MetadataService#retrieveEdgeSchema
     * (java.lang.String, java.lang.String)
     */
    @Override
    public Schema retrieveEdgeSchema(String projectId, String universeId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {


        Universe universe = this.dataStore.getUniverse(this.dataStore
                .getProject(projectId));

        return universe.getEdgeSchema();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.MetadataService#retrieveUniverseMetadata
     * (java.lang.String, java.lang.String)
     */
    @Override
    public List<Metadata> retrieveUniverseMetadata(String projectId,
            String universeId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Universe universe = this.dataStore.getUniverse(this.dataStore
                .getProject(projectId));

        return new ArrayList<>(universe.getMetadataProperty().getMetadata());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.MetadataService#retrieveGraphMetadata
     * (java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public List<Metadata> retrieveGraphMetadata(String projectId,
            String universeId, String graphId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Universe universe = this.dataStore.getUniverse(this.dataStore
                .getProject(projectId));
        Graph graph = this.dataStore.getGraph(universe, graphId);

        return new ArrayList<>(graph.getMetadataProperty().getMetadata());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.MetadataService#retrieveNodeMetadata
     * (java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public List<Metadata> retrieveNodeMetadata(String projectId,
            String universeId, String nodeId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Universe universe = this.dataStore.getUniverse(this.dataStore
                .getProject(projectId));
        Node node = this.dataStore.getNode(universe, nodeId);

        return new ArrayList<>(node.getMetadataProperty().getMetadata());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.MetadataService#retrieveEdgeMetadata
     * (java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public List<Metadata> retrieveEdgeMetadata(String projectId,
            String universeId, String edgeId)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Universe universe = this.dataStore.getUniverse(this.dataStore
                .getProject(projectId));
        Edge edge = this.dataStore.getEdge(universe, edgeId);

        return new ArrayList<>(edge.getMetadataProperty().getMetadata());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.MetadataService#retrieveUniverseMetadata
     * (java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public List<Metadata> retrieveUniverseMetadata(String projectId,
            String universeId, String metadataKey)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Universe universe = this.dataStore.getUniverse(this.dataStore
                .getProject(projectId));

        return universe.getMetadataProperty().getMetadata(metadataKey);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.MetadataService#retrieveGraphMetadata
     * (java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public List<Metadata> retrieveGraphMetadata(String projectId,
            String universeId, String graphId, String metadataKey)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Universe universe = this.dataStore.getUniverse(this.dataStore
                .getProject(projectId));
        Graph graph = this.dataStore.getGraph(universe, graphId);

        return graph.getMetadataProperty().getMetadata(metadataKey);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.MetadataService#retrieveNodeMetadata
     * (java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public List<Metadata> retrieveNodeMetadata(String projectId,
            String universeId, String nodeId, String metadataKey)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Universe universe = this.dataStore.getUniverse(this.dataStore
                .getProject(projectId));
        Node node = this.dataStore.getNode(universe, nodeId);

        return node.getMetadataProperty().getMetadata(metadataKey);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.services.MetadataService#retrieveEdgeMetadata
     * (java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public List<Metadata> retrieveEdgeMetadata(String projectId,
            String universeId, String edgeId, String metadataKey)
            throws RequestDeniedException, RequestFailedException,
            NotFoundException {

        Universe universe = this.dataStore.getUniverse(this.dataStore
                .getProject(projectId));
        Edge edge = this.dataStore.getEdge(universe, edgeId);

        return edge.getMetadataProperty().getMetadata(metadataKey);
    }
}
