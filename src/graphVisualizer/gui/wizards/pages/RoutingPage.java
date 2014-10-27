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


package graphVisualizer.gui.wizards.pages;


import java.util.Observable;
import java.util.Observer;

import javafx.scene.Parent;
import javafx.scene.layout.VBoxBuilder;
import graphVisualizer.gui.widgets.RoutingGridPane;
import graphVisualizer.gui.wizards.IWizardWithStatus;
import graphVisualizer.gui.wizards.statusobjects.IStatus;
import graphVisualizer.layout.common.CoordinateComponent;
import graphVisualizer.layout.common.ICoordinateLayoutComponent;
import graphVisualizer.layout.common.ILayoutComponent;
import graphVisualizer.visualization.VisualNode;
import graphVisualizer.visualization.VisualProperty;


/**
 * The {@link RoutingPage} class provides a {@link WizardPage} for routing
 * coordinate output of {@link ICoordinateLayoutComponent
 * CoordinateLayoutComponents} to the x, y, or z coordinate(scale) of a
 * {@link VisualNode}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @since 1.0
 * 
 */
public class RoutingPage
        extends WizardPage
        implements Observer {

    private RoutingGridPane routingGridPane;



    /**
     * Creates a new instance of the {@link RoutingPage} class.
     */
    public RoutingPage() {

        super("Route Coordinate Outputs");


    }

    @Override
    Parent getContent() {

        this.routingGridPane = new RoutingGridPane();

        this.getFinishButton().setDisable(true);

        return VBoxBuilder.create().spacing(5).children(this.routingGridPane)
                .build();
    }



    @Override
    public IWizardWithStatus getWizard() {

        if (super.getWizard() instanceof IWizardWithStatus) {

            return (IWizardWithStatus) super.getWizard();
        }
        return null;
    }

    @Override
    public void update(Observable observable, Object changedValue) {

        if (observable instanceof IStatus) {


            IStatus status = (IStatus) observable;

            if (status.getLayoutComponent() != null) {

                ILayoutComponent layoutComponent = status.getLayoutComponent();
                this.routingGridPane.setLayoutComponent(layoutComponent);

                if (status.getVisualProperty().contains(
                        VisualProperty.NODE_X_POSITION)) {

                    this.routingGridPane.getXCoordinate().setDisable(false);
                }
                else {

                    this.routingGridPane.getXCoordinate().getSelectionModel()
                            .select(CoordinateComponent.NO_COMPONENT);
                }
                if (status.getVisualProperty().contains(
                        VisualProperty.NODE_Y_POSITION)) {

                    this.routingGridPane.getYCoordinate().setDisable(false);
                }
                else {

                    this.routingGridPane.getYCoordinate().getSelectionModel()
                            .select(CoordinateComponent.NO_COMPONENT);
                }
                if (status.getVisualProperty().contains(
                        VisualProperty.NODE_Z_POSITION)) {

                    this.routingGridPane.getZCoordinate().setDisable(false);
                }
                else {

                    this.routingGridPane.getZCoordinate().getSelectionModel()
                            .select(CoordinateComponent.NO_COMPONENT);
                }

            }
        }
    }

}
