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


package graphVisualizer.graph.common;


/**
 * the <code>IHyperEdgeProperties</code> interface defines the properties of a
 * HyperEdge.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public interface IHyperEdgeProperties {

    /**
     * The number of nodes connected by the HyperEdge.
     * 
     * @return the number of connected nodes.
     */
    public long cardinality();
}
