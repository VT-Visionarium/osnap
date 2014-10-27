/*******************************************************************************
 * Copyright 2014 Virginia Tech Visionarium
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/


package edu.vt.arc.vis.osnap.core.domain.graph.algorithms.shortestPath;

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


import edu.vt.arc.vis.osnap.core.domain.graph.algorithms.IWeight;
import edu.vt.arc.vis.osnap.core.domain.graph.common.*;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.IMetadataContainer;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Metadata;

import java.util.Collection;
import java.util.List;

import org.jutility.common.datatype.map.KeyValuePair;


/**
 * Floyd-Warshall algorithm for determining the shortest path between two nodes
 * in a graph.
 * <p>
 * Thread Safety: immutable
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public class FloydWarshallAlgorithmWithMetadata
        extends FloydWarshallAlgorithm {

    private final Metadata metadata;

    /**
     * Creates a new instance with the provided graph, weight, and metadata.
     * 
     * @param graph
     *            the graph to analyze.
     * @param weight
     *            the weights of the edges.
     * @param metadata
     *            the metadata that determines whether an edge is to be ignored.
     */
    public FloydWarshallAlgorithmWithMetadata(final IGraph graph,
            final IWeight weight, Metadata metadata) {

        this(graph.getNodes(), graph.getEdges(), weight, metadata);
    }


    /**
     * Creates a new instance with the provided graph, weight, and metadata.
     * 
     * @param universe
     *            the universe to analyze.
     * @param weight
     *            the weights of the edges.
     * @param metadata
     *            the metadata that determines whether an edge is to be ignored.
     */
    public FloydWarshallAlgorithmWithMetadata(final IUniverse universe,
            final IWeight weight, Metadata metadata) {

        this(universe.getNodes(), universe.getEdges(), weight, metadata);
    }


    /**
     * Create an instance of this class by describing the graph upon which it
     * will operate.
     * 
     * @param nodes
     *            Collection of Nodes; must be completely populated
     * @param edges
     *            Collection of Edges, completely populated; order is not
     *            important
     * @param weight
     *            the weight of the edges.
     * @param metadata
     *            the metadata that determines whether an edge is to be ignored.
     */
    public FloydWarshallAlgorithmWithMetadata(final Collection<? extends INode> nodes,
            final Collection<? extends IEdge> edges, final IWeight weight,
            Metadata metadata) {

        super(nodes, edges, weight);

        if (metadata == null) {

            throw new IllegalArgumentException(
                    "Cannot create an instance without metadata!");
        }
        this.metadata = metadata;


    }


    @Override
    protected void initializeDistances() {

        for (IEdge edge : this.getEdges()) {

            if (edge instanceof IMetadataContainer) {

                IMetadataContainer edgeWithMetadata = (IMetadataContainer) edge;

                List<Metadata> metadataValues = edgeWithMetadata
                        .getMetadataProperty().getMetadata(
                                this.metadata.getKey());

                if (metadataValues != null
                        && metadataValues.contains(this.metadata)) {
                    KeyValuePair<INode, INode> edgePath = new KeyValuePair<>(
                            edge.getSource(), edge.getTarget());

                    this.getDistances().put(edgePath,
                            this.getWeight().getWeight(edge));
                }
            }
        }
    }
}
