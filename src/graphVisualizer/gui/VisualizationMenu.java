/*******************************************************************************
 * Copyright 2014 Virginia Tech Visionarium
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/


package graphVisualizer.gui;


import java.io.File;

import org.controlsfx.control.action.Action;
import org.controlsfx.control.action.ActionUtils;
import org.controlsfx.dialog.DefaultDialogAction;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import x3d.model.X3DDocument;
import graphVisualizer.conversion.IOManager;
import graphVisualizer.graph.Universe;
import graphVisualizer.layout.Layout;
import graphVisualizer.visualization.Visualization;


/**
 * 
 * 
 * @author Peter J. Radics
 * @version 0.1
 */

public class VisualizationMenu
        extends Menu {

    private final Stage                         stage;
    private final ObjectProperty<Visualization> visualization;
    private final ObjectProperty<Layout>        layout;
    private final ObjectProperty<Universe>      universe;

    private final Action                       createFromLayoutMI;
    private final Action                       exportToX3DMI;
    private final Action                       closeMI;


    /**
     * Returns the visualization property.
     * 
     * @return the visualization property.
     */
    public ObjectProperty<Visualization> visualization() {

        return this.visualization;
    }

    /**
     * Returns the visualization.
     * 
     * @return the visualization.
     */
    public Visualization getVisualization() {

        return this.visualization.get();
    }

    /**
     * Sets the visualization.
     * 
     * @param visualization
     *            the visualization.
     */
    public void setVisualization(Visualization visualization) {

        this.visualization.set(visualization);
    }

    /**
     * Creates a new instance of the {@link VisualizationMenu} class.
     * 
     * @param stage
     *            the stage of this menu.
     */
    public VisualizationMenu(Stage stage) {

        this(stage, null);
    }

    /**
     * Returns the layout property.
     * 
     * @return the layout property.
     */
    public ObjectProperty<Layout> layout() {

        return this.layout;
    }

    /**
     * Returns the layout.
     * 
     * @return the layout.
     */
    public Layout getLayout() {

        return this.layout.get();
    }

    /**
     * Sets the layout.
     * 
     * @param layout
     *            the layout.
     */
    public void setLayout(Layout layout) {

        this.layout.set(layout);
    }

    /**
     * Returns the universe property.
     * 
     * @return the universe property.
     */
    public ObjectProperty<Universe> universe() {

        return this.universe;
    }

    /**
     * Returns the universe.
     * 
     * @return the universe.
     */
    public Universe getUniverse() {

        return this.universe.get();
    }

    /**
     * Sets the universe.
     * 
     * @param universe
     *            the universe.
     */
    public void setUniverse(Universe universe) {

        this.universe.set(universe);
    }

    /**
     * Creates a new instance of the {@link VisualizationMenu} class.
     * 
     * @param stage
     *            the stage of this menu.
     * 
     * @param graphic
     *            the graphic of the file menu.
     */
    public VisualizationMenu(Stage stage, Node graphic) {

        super("Visualization", graphic);

        this.stage = stage;
        this.visualization = new SimpleObjectProperty<>();
        this.layout = new SimpleObjectProperty<>();
        this.universe = new SimpleObjectProperty<>();

        this.createFromLayoutMI = new DefaultDialogAction(
                "Create Visualization from Layout") {

            @Override
            public void handle(ActionEvent ae) {

                VisualizationMenu.this.getLayout().layout();
                VisualizationMenu.this.setVisualization(null);
                VisualizationMenu.this.setVisualization(VisualizationMenu.this
                        .getLayout().getVisualization());
            }
        };



        this.exportToX3DMI = new DefaultDialogAction("Export to X3D") {

            @Override
            public void handle(ActionEvent ae) {

                FileChooser fc = new FileChooser();
                fc.setTitle("Export Visualization As X3D");
                FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(
                        "X3D Files", "*.x3d");
                fc.getExtensionFilters().add(filter);
                File x3dSaveFile = fc
                        .showSaveDialog(VisualizationMenu.this.stage);
                if (x3dSaveFile != null) {
                    if (!x3dSaveFile.getAbsolutePath().endsWith(".x3d")) {

                        x3dSaveFile = new File(x3dSaveFile.getAbsolutePath()
                                + ".x3d");
                    }
                    IOManager.Instance().save(x3dSaveFile,
                            VisualizationMenu.this.visualization.get(),
                            X3DDocument.class);
                }
            }
        };


        this.closeMI = new DefaultDialogAction("Close") {

            @Override
            public void handle(ActionEvent ae) {

                VisualizationMenu.this.visualization.set(null);

            }
        };

        this.exportToX3DMI.disabledProperty().set(true);
        this.closeMI.disabledProperty().set(true);

        this.getItems().addAll(
                ActionUtils.createMenuItem(this.createFromLayoutMI),
                ActionUtils.createMenuItem(this.exportToX3DMI),
                ActionUtils.createMenuItem(this.closeMI));



        this.visualization.addListener(new ChangeListener<Visualization>() {

            @Override
            public void changed(
                    ObservableValue<? extends Visualization> property,
                    Visualization oldValue, Visualization newValue) {

                boolean requiresVisualization = (newValue == null);


                VisualizationMenu.this.createFromLayoutMI.disabledProperty()
                        .set(!requiresVisualization);

                VisualizationMenu.this.exportToX3DMI.disabledProperty().set(
                        requiresVisualization);
                VisualizationMenu.this.closeMI.disabledProperty().set(
                        requiresVisualization);
            }
        });
    }
}
