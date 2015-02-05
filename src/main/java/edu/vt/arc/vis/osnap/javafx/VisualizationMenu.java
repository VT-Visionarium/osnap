package edu.vt.arc.vis.osnap.javafx;


//@formatter:off
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
//@formatter:on

import java.io.File;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.controlsfx.control.action.Action;
import org.controlsfx.control.action.ActionUtils;
import org.x3d.model.X3DDocument;

import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.layout.LayoutSet;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;
import edu.vt.arc.vis.osnap.io.IOManager;



/**
 * The {@code VisualizationMenu} class provides the options pertaining to
 * {@link Visualization Visualizations}.
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 */
public class VisualizationMenu
        extends Menu {

    private final Stage                         stage;
    private final ObjectProperty<Visualization> visualization;
    private final ObjectProperty<LayoutSet>     layoutSet;
    private final ObjectProperty<Universe>      universe;

    private final Action                        createFromLayoutMI;
    private final Action                        exportToX3DMI;
    private final Action                        closeMI;


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
     * Returns the layoutSet property.
     * 
     * @return the layoutSet property.
     */
    public ObjectProperty<LayoutSet> layoutSet() {

        return this.layoutSet;
    }

    /**
     * Returns the layoutSet.
     * 
     * @return the layoutSet.
     */
    public LayoutSet getLayout() {

        return this.layoutSet.get();
    }

    /**
     * Sets the layoutSet.
     * 
     * @param layoutSet
     *            the layoutSet.
     */
    public void setLayout(LayoutSet layoutSet) {

        this.layoutSet.set(layoutSet);
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
        this.layoutSet = new SimpleObjectProperty<>();
        this.universe = new SimpleObjectProperty<>();

        this.createFromLayoutMI = new Action(
                "Create Visualization from LayoutSet", actionEvent -> {

                    this.getLayout().layout();
                    this.setVisualization(null);
                    this.setVisualization(this.getLayout().getVisualization());
                });



        this.exportToX3DMI = new Action(
                "Export to X3D",
                actionEvent -> {

                    FileChooser fc = new FileChooser();
                    fc.setTitle("Export Visualization As X3D");
                    FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(
                            "X3D Files", "*.x3d");
                    fc.getExtensionFilters().add(filter);
                    File x3dSaveFile = fc.showSaveDialog(this.stage);
                    if (x3dSaveFile != null) {
                        if (!x3dSaveFile.getAbsolutePath().endsWith(".x3d")) {

                            x3dSaveFile = new File(
                                    x3dSaveFile.getAbsolutePath() + ".x3d");
                        }
                        IOManager.Instance().save(x3dSaveFile,
                                this.visualization.get(), X3DDocument.class);
                    }
                });


        this.closeMI = new Action("Close", actionEvent -> {

            this.visualization.set(null);

        });

        this.exportToX3DMI.disabledProperty().set(true);
        this.closeMI.disabledProperty().set(true);

        this.getItems().addAll(
                ActionUtils.createMenuItem(this.createFromLayoutMI),
                ActionUtils.createMenuItem(this.exportToX3DMI),
                ActionUtils.createMenuItem(this.closeMI));



        this.visualization.addListener((observable, oldValue, newValue) -> {

            boolean requiresVisualization = (newValue == null);


            this.createFromLayoutMI.disabledProperty().set(
                    !requiresVisualization);

            this.exportToX3DMI.disabledProperty().set(requiresVisualization);
            this.closeMI.disabledProperty().set(requiresVisualization);
        });
    }
}
