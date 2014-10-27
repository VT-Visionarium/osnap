/**
 * 
 */
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
import edu.vt.arc.vis.osnap.core.domain.layout.mappedComponents.MappedViewpointLayoutComponent;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.ViewPointOffsetPage;
import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.ViewPointStatusObject;
import edu.vt.arc.vis.osnap.javafx.wizards.statuspanes.ViewPointStatusPane;
import javafx.stage.Stage;


/**
 * @author Shawn P Neuman
 * 
 */
public class ViewPointWizard
        extends BaseLayoutWizard {


private ViewPointStatusPane pane;
    /**
     * @param uni
     */
    public ViewPointWizard(Universe uni) {

        this(null, uni);
    }

    /**
     * @param primary
     * @param uni
     */
    public ViewPointWizard(Stage primary, Universe uni) {

        super(primary, new ViewPointStatusPane("ViewPoint Selection Wizard"),
                new ViewPointStatusObject(), MappedViewpointLayoutComponent.capabilities(), 2, uni, new ViewPointOffsetPage(
                        "Set X, Y, and Z offsets"));
        getStatusObject().setLayoutComponent(
                new MappedViewpointLayoutComponent());
        pane = (ViewPointStatusPane) this.getStatusPane();
        getStatusObject().addObserver(pane);

    }


    @Override
    public ViewPointStatusObject getStatusObject() {

        if (super.getStatusObject() instanceof ViewPointStatusObject) {
            return (ViewPointStatusObject) super.getStatusObject();
        }
        return null;
    }

}
