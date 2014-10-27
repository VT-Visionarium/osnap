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


package edu.vt.arc.vis.osnap.javafx.wizards;

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


import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleShapeLayoutComponent;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.ShapeSelectionPage;
import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.ShapeStatus;
import edu.vt.arc.vis.osnap.javafx.wizards.statuspanes.ShapeStatusPane;
import javafx.stage.Stage;


/**
 * Wizard for selecting components for this layout type
 * 
 * @author Shawn P Neuman
 * 
 */
public class ShapeLayoutWizard
        extends BaseLayoutWizard {

    /**
     * The wizard for creating a layout where node or edge shape is modified for
     * better visualization
     * 
     * @param universe
     */
    public ShapeLayoutWizard(Universe universe) {

        this(null, universe);
    }

    /**
     * @param primary
     * @param uni
     */
    public ShapeLayoutWizard(Stage primary, Universe uni) {

        super(primary, new ShapeStatusPane("Shape StatusPane"),
                new ShapeStatus(), SimpleShapeLayoutComponent.capabilities(),
                1, uni, new ShapeSelectionPage());

        this.getStatusObject().setLayoutComponent(
                new SimpleShapeLayoutComponent());

    }


    @Override
    public ShapeStatus getStatusObject() {

        if (super.getStatusObject() instanceof ShapeStatus) {
            return (ShapeStatus) super.getStatusObject();
        }

        return null;
    }

}