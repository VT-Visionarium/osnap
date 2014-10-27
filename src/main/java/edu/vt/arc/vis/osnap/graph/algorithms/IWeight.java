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


package edu.vt.arc.vis.osnap.graph.algorithms;


import edu.vt.arc.vis.osnap.graph.common.IEdge;
import edu.vt.arc.vis.osnap.graph.common.IHyperEdge;
import edu.vt.arc.vis.osnap.graph.common.INode;


/**
 * The <CODE>IWeight</CODE> interface provides a contract for classes that
 * determine the weight of edges, hyperedges, and nodes.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public interface IWeight {

    /**
     * Returns the weight of an edge.
     * 
     * @param edge
     *            the edge.
     * @return the weight.
     */
    public Integer getWeight(IEdge edge);

    /**
     * Returns the weight of an hyperedge.
     * 
     * @param hyperedge
     *            the hyperedge.
     * @return the weight.
     */
    public Integer getWeight(IHyperEdge hyperedge);

    /**
     * Returns the weight of an node.
     * 
     * @param node
     *            the node.
     * @return the weight.
     */
    public Integer getWeight(INode node);

}
