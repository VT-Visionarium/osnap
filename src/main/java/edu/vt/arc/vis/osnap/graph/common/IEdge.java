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


package edu.vt.arc.vis.osnap.graph.common;


/**
 * The {@link IEdge} interface provides the definition of a hyperedge.
 * <p/>
 * An {@link IEdge edge} is a connection between exactly two {@link INode nodes}
 * . Thus, it is a restriction of the {@link IHyperEdge} class.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public interface IEdge
        extends IHyperEdge, IEdgeProperties {

    /**
     * Returns the source node of the edge.
     * 
     * @return the source node.
     */
    public INode getSource();

    /**
     * Returns the target node of the edge.
     * 
     * @return the target node.
     */
    public INode getTarget();


    /**
     * Determines whether this {@link IEdge} is identical to the {@link IEdge}
     * provided.
     * 
     * @param other
     *            the other {@link IEdge}.
     * @param recurse
     *            whether or not to recurse into nested elements.
     * @return <code>true</code>, if the {@link IEdge IEdges} are identical;
     *         <code>false</code> otherwise.
     */
    public boolean isIdentical(IEdge other, boolean recurse);
}
