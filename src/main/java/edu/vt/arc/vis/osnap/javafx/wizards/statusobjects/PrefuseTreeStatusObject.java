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


package edu.vt.arc.vis.osnap.javafx.wizards.statusobjects;

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


import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.IPrefuseTreeLayoutComponent;



/**
 * The abstract{@link PrefuseTreeStatusObject} class provides a wrapper around
 * the common parameters of {@link IPrefuseTreeLayoutComponent Prefuse tree
 * layout components}.
 *
 * @author Peter J. Radics
 * @version 1.0
 */
public abstract class PrefuseTreeStatusObject
        extends PrefuseStatusObject
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

    @Override
    public IPrefuseTreeLayoutComponent getLayoutComponent() {

        if (super.getLayoutComponent() instanceof IPrefuseTreeLayoutComponent) {

            return (IPrefuseTreeLayoutComponent) super.getLayoutComponent();
        }
        return null;
    }

}
