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


package edu.vt.arc.vis.osnap.io.prefuse;


import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;


/**
 * The {@link Constants} class contains constants for the use within the Prefuse
 * utility classes.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
public class Constants {

    /**
     * The identifier for the column containing the prefuse-version of a
     * {@link IGraphObject} id.
     */
    public static final String PREFUSE_ID        = "graphViz:PrefuseID";

    /**
     * The identifier for the column containing the id of a {@link IGraphObject}
     * .
     */
    public static final String ID                = "graphViz:ID";

    /**
     * The identifier for the column containing the prefuse-version of a
     * {@link INode source node} id.
     */
    public static final String PREFUSE_SOURCE_ID = "graphViz:PrefuseSourceID";

    /**
     * The identifier for the column containing the prefuse-version of a
     * {@link INode target node} id.
     */
    public static final String PREFUSE_TARGET_ID = "graphViz:PrefuseTargetID";

    /**
     * The identifier for the column containing the id of a {@link INode source
     * node}.
     */
    public static final String SOURCE_ID         = "graphViz:SourceID";

    /**
     * The identifier for the column containing the id of a {@link INode target
     * node}.
     */
    public static final String TARGET_ID         = "graphViz:TargetID";

    /**
     * The identifier for a prefuse graph.
     */
    public static final String PREFUSE_GROUP     = "graph";
}
