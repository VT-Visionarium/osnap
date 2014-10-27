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



import java.util.List;

import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.ForceDirectedCoordinateLayoutComponent;


/**
 * Status object to hold info for the layout matching its name
 *
 * @author Shawn P Neuman
 * @version 1.0
 */
public class ForceDirectedStatus
        extends FruchtermanReingoldStatus {

    private Boolean enforceBounds;



    /**
     * @return the enforceBounds
     */
    public Boolean enforcesBounds() {

        return enforceBounds;
    }

    /**
     * @param enforceBounds
     *            the enforceBounds to set
     */
    public void setEnforceBounds(boolean enforceBounds) {

        if (this.getLayoutComponent() != null) {
            this.setLayoutComponent(new ForceDirectedCoordinateLayoutComponent(
                    enforceBounds, this.getLayoutComponent().getIterations()));
            setChanged();
            notifyObservers(enforcesBounds());
        }
        else {
            this.setLayoutComponent(new ForceDirectedCoordinateLayoutComponent(
                    enforceBounds));
            setChanged();
            notifyObservers(enforcesBounds());
        }
        setChanged();
        notifyObservers(enforcesBounds());
    }


    @Override
    public void setGraphObjectList(List<IGraphObject> graphObjectList) {

        super.setGraphObjectList(graphObjectList);
        this.enforceBounds = false;
        super.setLayoutComponent(new ForceDirectedCoordinateLayoutComponent());
    }

    @Override
    public ForceDirectedCoordinateLayoutComponent getLayoutComponent() {

        if (super.getLayoutComponent() instanceof ForceDirectedCoordinateLayoutComponent) {
            return (ForceDirectedCoordinateLayoutComponent) super
                    .getLayoutComponent();
        }
        return null;
    }
}
