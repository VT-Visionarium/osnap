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


package edu.vt.arc.vis.osnap.visualization;


import edu.vt.arc.vis.osnap.graph.common.IGraphObject;


/**
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IVisualGraphObject {

    /**
     * Returns the unique id.
     * 
     * @return the id.
     */
    public abstract String getID();
    
    /**
     * Returns the {@link IGraphObject GraphObject} associated with the
     * VisualGraphObject.
     * 
     * @return the {@link IGraphObject GraphObject} associated with the
     *         VisualGraphObject.
     */
    public abstract IGraphObject getGraphObject();

    /**
     * Returns the unique id of the associated graph object.
     * 
     * @return the id of the associated graph object.
     */
    public abstract String getGraphObjectID();
}
