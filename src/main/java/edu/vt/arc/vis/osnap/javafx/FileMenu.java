package edu.vt.arc.vis.osnap.javafx;


// @formatter:off
/*
 * _
 * The Open Semantic Network Analysis Platform (OSNAP) 
 * _
 * Copyright (C) 2012 - 2014 Visionarium at Virginia Tech 
 * _
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
 * _
 */
// @formatter:on

import java.io.File;
import java.net.URI;
import java.util.Optional;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.controlsfx.control.action.Action;
import org.controlsfx.control.action.ActionUtils;
import org.jutility.javafx.control.dialog.UriDialog;

import edu.vt.arc.vis.osnap.core.domain.Project;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.io.IOManager;
import edu.vt.arc.vis.osnap.javafx.dialogs.UniverseDialog;


/**
 * 
 * 
 * @author Peter J. Radics
 * @version 0.1
 */

public class FileMenu
        extends Menu {

    private final Stage                              stage;
    private final ObjectProperty<Project>            project;
    private final ObjectProperty<Universe>           universe;

    private final Action                             newMI;
    private final Action                             openProjectMI;
    private final Action                             openProjectUriMI;
    private final Action                             openUniverseMI;
    private final Action                             openUniverseUriMI;
    private final Action                             saveProjectMI;
    private final Action                             saveProjectAsMI;
    private final Action                             saveUniverseMI;
    private final Action                             saveUniverseAsMI;
    private final Action                             closeMI;
    private final Action                             exitMI;


    private File                                     universeSaveLocation;
    private File                                     projectSaveLocation;

    private File                                     tempSaveLocation;

    private static final FileChooser.ExtensionFilter osnap      = new FileChooser.ExtensionFilter(
                                                                        "OSNAP Project Files",
                                                                        "*.osnap");

    private static final FileChooser.ExtensionFilter osnapu     = new FileChooser.ExtensionFilter(
                                                                        "OSNAP Graph Universe Files",
                                                                        "*.osnapu");
    private static final FileChooser.ExtensionFilter owl        = new FileChooser.ExtensionFilter(
                                                                        "OWL files (*.owl)",
                                                                        "*.owl");
    private static final FileChooser.ExtensionFilter xml        = new FileChooser.ExtensionFilter(
                                                                        "XML files (*.xml)",
                                                                        "*.xml");
    private static final FileChooser.ExtensionFilter graphML    = new FileChooser.ExtensionFilter(
                                                                        "GraphML files (*.graphML)",
                                                                        "*.graphML");

    private static final FileChooser.ExtensionFilter allFormats = new FileChooser.ExtensionFilter(
                                                                        "All Supported Graph Formats",
                                                                        "*.osnapu",
                                                                        "*.xml",
                                                                        "*.owl",
                                                                        "*.graphml");

    /**
     * Returns the universe property.
     * 
     * @return the universe property.
     */
    public ObjectProperty<Project> project() {

        return this.project;
    }

    /**
     * Returns the project.
     * 
     * @return the project.
     */
    public Project getProject() {

        return this.project.get();
    }

    /**
     * Sets the project.
     * 
     * @param project
     *            the project.
     */
    public void setProject(Project project) {

        this.project.set(project);
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
     * Creates a new instance of the {@link FileMenu} class.
     * 
     * @param stage
     *            the stage of this menu.
     */
    public FileMenu(Stage stage) {

        this(stage, null);
    }

    /**
     * Creates a new instance of the {@link FileMenu} class.
     * 
     * @param stage
     *            the stage of this menu.
     * 
     * @param graphic
     *            the graphic of the file menu.
     */
    public FileMenu(Stage stage, Node graphic) {

        super("File", graphic);

        this.stage = stage;
        this.project = new SimpleObjectProperty<>();
        this.universe = new SimpleObjectProperty<>();

        this.universeSaveLocation = null;
        this.projectSaveLocation = null;
        this.tempSaveLocation = null;

        // menu items for File
        this.newMI = new Action("New Project", actionEvent -> {

            Optional<Universe> result = new UniverseDialog(this.stage,
                    "Create Universe", null).showAndWait();
            if (result.isPresent()) {

                this.project.set(new Project(result.get()));
                this.universe.set(result.get());
            }
            else {

                this.project.set(null);
                this.universe.set(null);
            }
        });

        this.openProjectMI = new Action(
                "Open Project File",
                actionEvent -> {

                    Project project = FileMenu.this.openFile(Project.class,
                            FileMenu.this.projectSaveLocation, FileMenu.osnap);

                    FileMenu.this.project.set(project);

                    if (project != null) {

                        FileMenu.this.projectSaveLocation = FileMenu.this.tempSaveLocation;
                        FileMenu.this.tempSaveLocation = null;

                        FileMenu.this.universe.set(project.getUniverse());
                    }

                });

        this.openProjectUriMI = new Action("Open Project URI", actionEvent -> {

            new UriDialog(FileMenu.this.stage).showAndWait().ifPresent(uri -> {

                Project project = this.open(Project.class, uri);

                this.project.set(project);

                if (project != null) {

                    this.universe.set(project.getUniverse());
                }
            });


        });


        this.openUniverseMI = new Action("Open Graph Universe File",
                actionEvent -> {

                    Universe universe = this.openFile(Universe.class,
                            this.universeSaveLocation, FileMenu.allFormats,
                            FileMenu.osnapu, FileMenu.owl, FileMenu.xml,
                            FileMenu.graphML);

                    if (universe != null) {

                        Project project = new Project(universe);
                        this.project.set(project);
                    }

                    this.universe.set(universe);

                });


        this.openUniverseUriMI = new Action("Open Graph Universe URI",
                actionEvent -> {

                    new UriDialog(FileMenu.this.stage).showAndWait().ifPresent(
                            uri -> {

                                Universe universe = this.open(Universe.class,
                                        uri);

                                if (universe != null) {

                                    Project project = new Project(universe);
                                    this.project.set(project);
                                }
                                this.universe.set(universe);
                            });
                });



        this.saveProjectMI = new Action("Save Project", actionEvent -> {

            File saveLocation = FileMenu.this.saveFile(
                    FileMenu.this.project.get(),
                    FileMenu.this.projectSaveLocation, false, FileMenu.osnap);

            if (saveLocation != null) {

                FileMenu.this.projectSaveLocation = saveLocation;
            }

        });

        this.saveProjectAsMI = new Action("Save Project As", actionEvent -> {

            File saveLocation = this.saveFile(this.project.get(),
                    this.projectSaveLocation, true, osnap);

            if (saveLocation != null) {

                this.projectSaveLocation = saveLocation;
            }
        });

        this.saveUniverseMI = new Action("Save Graph Universe",
                actionEvent -> {

                    File saveLocation = this.saveFile(this.universe.get(),
                            this.universeSaveLocation, false, FileMenu.osnapu);

                    if (saveLocation != null) {

                        FileMenu.this.universeSaveLocation = saveLocation;
                    }
                });

        this.saveUniverseAsMI = new Action("Save Graph Universe As",
                actionEvent -> {

                    File saveLocation = this.saveFile(this.universe.get(),
                            this.universeSaveLocation, true, FileMenu.osnapu);

                    if (saveLocation != null) {

                        FileMenu.this.universeSaveLocation = saveLocation;
                    }
                });



        this.closeMI = new Action("Close", actionEvent -> {

            this.project.set(null);
            this.universe.set(null);
        });


        this.exitMI = new Action("Exit", actionEvent -> {

            System.exit(0);
        });

        this.saveProjectMI.disabledProperty().set(true);
        this.saveProjectAsMI.disabledProperty().set(true);
        this.saveUniverseMI.disabledProperty().set(true);
        this.saveUniverseAsMI.disabledProperty().set(true);
        this.closeMI.disabledProperty().set(true);



        this.getItems().addAll(ActionUtils.createMenuItem(newMI),
                ActionUtils.createMenuItem(openProjectMI),
                ActionUtils.createMenuItem(openProjectUriMI),
                ActionUtils.createMenuItem(saveProjectMI),
                ActionUtils.createMenuItem(saveProjectAsMI),
                new SeparatorMenuItem(),
                ActionUtils.createMenuItem(openUniverseMI),
                ActionUtils.createMenuItem(openUniverseUriMI),
                ActionUtils.createMenuItem(saveUniverseMI),
                ActionUtils.createMenuItem(saveUniverseAsMI),
                new SeparatorMenuItem(), ActionUtils.createMenuItem(closeMI),
                ActionUtils.createMenuItem(exitMI));


        this.universe.addListener((observable, oldValue, newValue) -> {

            boolean requiresUniverse = (newValue == null);

            this.newMI.disabledProperty().set(!requiresUniverse);

            this.openProjectMI.disabledProperty().set(!requiresUniverse);
            this.openProjectUriMI.disabledProperty().set(!requiresUniverse);
            this.saveProjectAsMI.disabledProperty().set(requiresUniverse);
            this.saveProjectMI.disabledProperty().set(requiresUniverse);

            this.openUniverseMI.disabledProperty().set(!requiresUniverse);
            this.openUniverseUriMI.disabledProperty().set(!requiresUniverse);
            this.saveUniverseAsMI.disabledProperty().set(requiresUniverse);
            this.saveUniverseMI.disabledProperty().set(requiresUniverse);
            this.closeMI.disabledProperty().set(requiresUniverse);
        });
    }

    private <T> T openFile(Class<? extends T> fileType, File location,
            FileChooser.ExtensionFilter... extensionFilters) {

        FileChooser fc = new FileChooser();
        fc.setTitle("Open File");
        fc.getExtensionFilters().addAll(extensionFilters);

        if (location != null) {

            fc.setInitialDirectory(location.getParentFile());
            fc.setInitialFileName(location.getName());
        }

        File file = fc.showOpenDialog(FileMenu.this.stage);

        if (file != null) {

            T result = null;
            result = open(fileType, file.toURI());

            if (result != null) {

                this.tempSaveLocation = file;
            }
            else {

                tempSaveLocation = null;
            }

            return result;
        }

        return null;
    }

    private <T> T open(Class<? extends T> fileType, URI uri) {


        T document = IOManager.Instance().open(uri, fileType);

        if (document instanceof Universe) {

            Universe verse = (Universe) document;

            if ("default".equals(verse.getId())) {

                verse.setId("Source: " + uri.toASCIIString());
            }

        }

        return document;
    }

    private <T> File saveFile(T object, File location, boolean saveAs,
            FileChooser.ExtensionFilter extensionFilter) {

        File saveLocation = location;

        if (saveLocation == null || saveAs) {

            FileChooser fc = new FileChooser();
            fc.setTitle("Save File");

            if (location != null) {

                fc.setInitialDirectory(location.getParentFile());
                fc.setInitialFileName(location.getName());
            }

            saveLocation = fc.showSaveDialog(this.stage);
        }

        if (saveLocation != null) {
            String fileName = saveLocation.getAbsolutePath();


            String extension = extensionFilter.getExtensions().get(0);

            if (extension.startsWith("*")) {

                extension = extension.substring(1);
            }

            if (!fileName.endsWith(extension)) {

                fileName += extension;
            }


            saveLocation = new File(fileName);

            boolean success = IOManager.Instance().save(saveLocation, object,
                    object.getClass());

            if (success) {

                return saveLocation;
            }
        }

        return null;
    }
}
