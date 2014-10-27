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


package edu.vt.arc.vis.osnap.core.domain.graph.common;

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


/**
 * The <code>IUniverseProperties</code> interface provides the definition of
 * universe properties.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public interface IUniverseProperties {

    /**
     * Returns the number of graphs in this Universe.
     * 
     * @return the number of graphs.
     */
    public long numberOfGraphs();

    /**
     * Returns the number of edges and hyperedges in the graph (its size).
     * 
     * @return the number of edges and hyperedges.
     */
    public long size();

    /**
     * Returns the number of edges in the graph (its edge size).
     * 
     * @return the number of edges.
     */
    public long sizeEdges();

    /**
     * Returns the number of hyperedges in the graph (its hyperedge size).
     * 
     * @return the number of hyperedges.
     */
    public long sizeHyperEdges();

    /**
     * Returns the maximum cardinality of any edge and hyperedge in the graph
     * (its rank).
     * 
     * @return the maximum cardinality of any edge and hyperedge.
     */
    public long rank();

    /**
     * Returns the number of nodes in the graph (its order).
     * 
     * @return the number of nodes.
     */
    public long order();


    /**
     * Returns the maximum number of edges and hyperedges of any node in the
     * graph.
     * 
     * @return the maximum number of edges and hyperedges of any node.
     */
    public long maxDegree();

    /**
     * Returns the maximum number of edges of any node in the graph.
     * 
     * @return the maximum number of edges of any node.
     */
    public long maxDegreeEdges();

    /**
     * Returns the maximum number of hyperedges of any node in the graph.
     * 
     * @return the maximum number of hyperedges of any node.
     */
    public long maxDegreeHyperEdges();

    /**
     * Returns the maximum number of incoming edges and hyperedges of any node
     * in the graph.
     * 
     * @return the maximum number of incoming edges and hyperedges of any node.
     */
    public long maxInDegree();

    /**
     * Returns the maximum number of incoming edges of any node in the graph.
     * 
     * @return the maximum number of incoming edges of any node.
     */
    public long maxInDegreeEdges();

    /**
     * Returns the maximum number of incoming hyperedges of any node in the
     * graph.
     * 
     * @return the maximum number of incoming hyperedges of any node.
     */
    public long maxInDegreeHyperEdges();

    /**
     * Returns the maximum number of outgoing edges and hyperedges of any node
     * in the graph.
     * 
     * @return the maximum number of outgoing edges and hyperedges of any node.
     */
    public long maxOutDegree();

    /**
     * Returns the maximum number of outgoing edges of any node in the graph.
     * 
     * @return the maximum number of outgoing edges of any node.
     */
    public long maxOutDegreeEdges();

    /**
     * Returns the maximum number of outgoing hyperedges of any node in the
     * graph.
     * 
     * @return the maximum number of outgoing hyperedges of any node.
     */
    public long maxOutDegreeHyperEdges();

    /**
     * Returns the minimum number of edges and hyperedges of any node in the
     * graph.
     * 
     * @return the minimum number of edges and hyperedges of any node.
     */
    public long minDegree();

    /**
     * Returns the minimum number of edges of any node in the graph.
     * 
     * @return the minimum number of edges of any node.
     */
    public long minDegreeEdges();

    /**
     * Returns the minimum number of hyperedges of any node in the graph.
     * 
     * @return the minimum number of hyperedges of any node.
     */
    public long minDegreeHyperEdges();

    /**
     * Returns the minimum number of incoming edges and hyperedges of any node
     * in the graph.
     * 
     * @return the minimum number of incoming edges and hyperedges of any node.
     */
    public long minInDegree();

    /**
     * Returns the minimum number of incoming edges of any node in the graph.
     * 
     * @return the minimum number of incoming edges of any node.
     */
    public long minInDegreeEdges();

    /**
     * Returns the minimum number of incoming hyperedges of any node in the
     * graph.
     * 
     * @return the minimum number of incoming hyperedges of any node.
     */
    public long minInDegreeHyperEdges();

    /**
     * Returns the minimum number of outgoing edges and hyperedges of any node
     * in the graph.
     * 
     * @return the minimum number of outgoing edges and hyperedges of any node.
     */
    public long minOutDegree();

    /**
     * Returns the minimum number of outgoing edges of any node in the graph.
     * 
     * @return the minimum number of outgoing edges of any node.
     */
    public long minOutDegreeEdges();

    /**
     * Returns the minimum number of outgoing hyperedges of any node in the
     * graph.
     * 
     * @return the minimum number of outgoing hyperedges of any node.
     */
    public long minOutDegreeHyperEdges();

    /**
     * Returns whether or not all edges (and hyperedges) of the graph have the
     * same cardinality (whether the graph is uniform).
     * 
     * @return <code>true</code>, if all edges in the graph have the same
     *         cardinality; <code>false</code> otherwise.
     */
    public Boolean isUniform();

    /**
     * Returns the cardinality of the edges in case the graph is uniform.
     * 
     * @return the cardinality of the edges of the graph, if the graph is
     *         uniform; otherwise, <code>-1L</code>.
     */
    public long uniformK();

    /**
     * Returns whether or not all nodes of the graph have the same degree
     * (whether the graph is regular).
     * 
     * @return <code>true</code>, if all edges in the graph have the same
     *         cardinality; <code>false</code> otherwise.
     */
    public Boolean isRegular();

    /**
     * Returns the degree of the nodes in case the graph is regular.
     * 
     * @return the degree of the nodes of the graph, if the graph is uniform;
     *         otherwise, <code>-1L</code>.
     */
    public long regularK();
}
