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


package edu.vt.arc.vis.osnap.gui.wizards.statusobjects;


import edu.vt.arc.vis.osnap.graph.common.INode;


/**
 * Status object to hold base tree info
 *
 * @author Shawn P Neuman
 *
 */
public class BaseTreeStatus
        extends BaseStatusObject
        implements ITreeStatus {

    private INode root;


    @Override
    public INode getRootNode() {

        return this.root;
    }

    @Override
    public void setRootNode(INode node) {

        this.root = node;
        setChanged();
        notifyObservers("" + getRootNode().toString());

    }

}
