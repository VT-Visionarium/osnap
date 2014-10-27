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
 * The <code>IGraphMember</code> interface provides the definition of the
 * functionality common to all members of a graph.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public interface IGraphMember {

    /**
     * Returns the Graph the IGraphMember is part of.
     * 
     * @return the graph.
     */
    public IGraph getGraph();
}
