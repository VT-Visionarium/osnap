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
import java.net.URI;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.controlsfx.control.action.Action;
import org.controlsfx.control.action.ActionUtils;
import org.controlsfx.dialog.DefaultDialogAction;
import org.jutility.javafx.control.dialog.UriDialog;

import graphVisualizer.common.Project;
import graphVisualizer.conversion.IOManager;
import graphVisualizer.graph.Universe;
import graphVisualizer.gui.dialogs.UniverseDialog;


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
        this.newMI = new DefaultDialogAction("New Project") {

            @Override
            public void handle(ActionEvent ae) {


                Universe universe = UniverseDialog.showUniverseDialog(
                        FileMenu.this.stage, null);

                if (universe != null) {

                    FileMenu.this.project.set(new Project(universe));
                }
                else {

                    FileMenu.this.project.set(null);
                }
                FileMenu.this.universe.set(universe);

            }
        };

        this.openProjectMI = new DefaultDialogAction("Open Project File") {

            @Override
            public void handle(ActionEvent ae) {

                Project project = FileMenu.this.openFile(Project.class,
                        FileMenu.this.projectSaveLocation, FileMenu.osnap);

                FileMenu.this.project.set(project);

                if (project != null) {

                    FileMenu.this.projectSaveLocation = FileMenu.this.tempSaveLocation;
                    FileMenu.this.tempSaveLocation = null;

                    FileMenu.this.universe.set(project.getUniverse());
                }

            }
        };

        this.openProjectUriMI = new DefaultDialogAction("Open Project URI") {

            @Override
            public void handle(ActionEvent ae) {

                URI uri = UriDialog.showUriDialog(FileMenu.this.stage);

                if (uri != null) {
                    Project project = FileMenu.this.open(Project.class, uri);

                    FileMenu.this.project.set(project);

                    if (project != null) {

                        FileMenu.this.universe.set(project.getUniverse());
                    }
                }
            }
        };


        this.openUniverseMI = new DefaultDialogAction(
                "Open Graph Universe File") {

            @Override
            public void handle(ActionEvent ae) {

                Universe universe = FileMenu.this.openFile(Universe.class,
                        FileMenu.this.universeSaveLocation, allFormats, osnapu,
                        owl, xml, graphML);

                if (universe != null) {

                    Project project = new Project(universe);
                    FileMenu.this.project.set(project);
                }

                FileMenu.this.universe.set(universe);

            }
        };


        this.openUniverseUriMI = new DefaultDialogAction(
                "Open Graph Universe URI") {

            @Override
            public void handle(ActionEvent ae) {

                URI uri = UriDialog.showUriDialog(FileMenu.this.stage);

                if (uri != null) {

                    Universe universe = FileMenu.this.open(Universe.class, uri);

                    if (universe != null) {

                        Project project = new Project(universe);
                        FileMenu.this.project.set(project);
                    }
                    FileMenu.this.universe.set(universe);
                }

            }
        };


        this.saveProjectMI = new DefaultDialogAction("Save Project") {

            @Override
            public void handle(ActionEvent ae) {

                File saveLocation = FileMenu.this.saveFile(
                        FileMenu.this.project.get(),
                        FileMenu.this.projectSaveLocation, false,
                        FileMenu.osnap);

                if (saveLocation != null) {

                    FileMenu.this.projectSaveLocation = saveLocation;
                }

            }
        };

        this.saveProjectAsMI = new DefaultDialogAction("Save Project As") {

            @Override
            public void handle(ActionEvent ae) {

                File saveLocation = FileMenu.this
                        .saveFile(FileMenu.this.project.get(),
                                FileMenu.this.projectSaveLocation, true,
                                FileMenu.osnap);

                if (saveLocation != null) {

                    FileMenu.this.projectSaveLocation = saveLocation;
                }
            }
        };

        this.saveUniverseMI = new DefaultDialogAction("Save Graph Universe") {

            @Override
            public void handle(ActionEvent ae) {

                File saveLocation = FileMenu.this.saveFile(
                        FileMenu.this.universe.get(),
                        FileMenu.this.universeSaveLocation, false,
                        FileMenu.osnapu);

                if (saveLocation != null) {

                    FileMenu.this.universeSaveLocation = saveLocation;
                }
            }
        };

        this.saveUniverseAsMI = new DefaultDialogAction(
                "Save Graph Universe As") {

            @Override
            public void handle(ActionEvent ae) {

                File saveLocation = FileMenu.this.saveFile(
                        FileMenu.this.universe.get(),
                        FileMenu.this.universeSaveLocation, true,
                        FileMenu.osnapu);

                if (saveLocation != null) {

                    FileMenu.this.universeSaveLocation = saveLocation;
                }
            }
        };



        this.closeMI = new DefaultDialogAction("Close") {

            @Override
            public void handle(ActionEvent ae) {

                FileMenu.this.project.set(null);
                FileMenu.this.universe.set(null);
            }
        };
        this.exitMI = new DefaultDialogAction("Exit") {

            @Override
            public void handle(ActionEvent ae) {

                System.exit(0);
            }
        };

        this.saveProjectMI.disabledProperty().set(true);
        this.saveProjectAsMI.disabledProperty().set(true);
        this.saveUniverseMI.disabledProperty().set(true);
        this.saveUniverseAsMI.disabledProperty().set(true);
        this.closeMI.disabledProperty().set(true);

        // Action test = new DefaultDialogAction("Test") {
        // @Override
        // public void handle(ActionEvent ae) {
        //
        // final Task<Universe> task = IOManager.Instance().openTask(null,
        // Universe.class);
        // // TODO Auto-generated method stub
        //
        // Dialogs.create().title("Opening File").showWorkerProgress(task);
        // Thread thread = new Thread(task);
        // thread.start();
        //
        // }
        // };


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
                ActionUtils.createMenuItem(exitMI)
        // ,ActionUtils.createMenuItem(test)
                );


        this.universe.addListener(new ChangeListener<Universe>() {

            @Override
            public void changed(ObservableValue<? extends Universe> property,
                    Universe oldValue, Universe newValue) {

                boolean requiresUniverse = (newValue == null);



                FileMenu.this.newMI.disabledProperty().set(!requiresUniverse);

                FileMenu.this.openProjectMI.disabledProperty().set(
                        !requiresUniverse);
                FileMenu.this.openProjectUriMI.disabledProperty().set(
                        !requiresUniverse);
                FileMenu.this.saveProjectAsMI.disabledProperty().set(
                        requiresUniverse);
                FileMenu.this.saveProjectMI.disabledProperty().set(
                        requiresUniverse);

                FileMenu.this.openUniverseMI.disabledProperty().set(
                        !requiresUniverse);
                FileMenu.this.openUniverseUriMI.disabledProperty().set(
                        !requiresUniverse);
                FileMenu.this.saveUniverseAsMI.disabledProperty().set(
                        requiresUniverse);
                FileMenu.this.saveUniverseMI.disabledProperty().set(
                        requiresUniverse);
                FileMenu.this.closeMI.disabledProperty().set(requiresUniverse);
            }
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

            if ("default".equals(verse.getID())) {

                verse.setID("Source: " + uri.toASCIIString());
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
