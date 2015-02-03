package edu.vt.arc.vis.osnap.core.domain.layout.common;

/*
 * _
 * The Open Semantic Network Analysis Platform (OSNAP)
 * _
 * Copyright (C) 2012 - 2015 Visionarium at Virginia Tech
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


import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;


/**
 * The {@code ITreeLayout} provides the shared contract of
 * all tree-based {@link ICoordinateLayout CoordinateLayoutComponents}.
 * 
 * @author Peter J. Radics
 * @version 1.2.3
 * @since 1.2.3
 */
public interface ITreeLayout
        extends ICoordinateLayout {

    /**
     * Returns the root {@link INode} of the tree-based
     * {@link ICoordinateLayout}.
     * 
     * @return the root {@link INode} of the tree-based
     *         {@link ICoordinateLayout}.
     */
    public abstract INode getRootNode();
}
