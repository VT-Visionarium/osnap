package edu.vt.arc.vis.osnap.javafx;


// @formatter:off
/*
 * _
 * The Open Semantic Network Analysis Platform (OSNAP)
 * _
 * Copyright (C) 2012 - 2015 Visionarium at Virginia Tech
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
// @formatter:on

import java.io.File;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.graphdrawing.graphml.GraphMLDocument;
import org.jutility.io.ConversionException;
import org.jutility.io.xml.XmlSerializer;
import org.x3d.model.X3DDocument;

import edu.vt.arc.vis.osnap.core.domain.Project;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.layout.LayoutSet;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;
import edu.vt.arc.vis.osnap.io.IOManager;
import edu.vt.arc.vis.osnap.javafx.engine.JavaFX3DEngine;
import edu.vt.arc.vis.osnap.javafx.events.ExportLayoutEvent;
import edu.vt.arc.vis.osnap.javafx.events.SwitchTabEvent;


// -------------------------------------------------------------------------
/**
 * Main class. Creates a GUI using JavaFX for users to select graphs and
 * customize the display properties through the use of dialog boxes and a
 * display wizard. Users can also create graphs and networks from scratch using
 * the add graph dialog through the context menu.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version May 21, 2013
 */
public class JavaFXGui
        extends Application {

    private final String                        title = "Open Semantic Network Analysis Platform";

    private Stage                               stage;
    private Scene                               scene;

    private UniverseEditorGridPane              universeDetails;
    private GraphEditorGridPane                 graphDetails;
    private LayoutDetailsVBox                   layoutDetails;
    private VisualizationDetailsVBox            visDetails;
    private TabPane                             tabPane;


    private final ObjectProperty<Project>       project;
    private final ObjectProperty<Universe>      universe;
    private final ObjectProperty<LayoutSet>     layoutSet;
    private final ObjectProperty<Visualization> visualization;

    private Tab                                 graphInformationTab;
    private Tab                                 layoutInformationTab;
    private Tab                                 visualizationInformationTab;
    private Tab                                 universeInformationTab;
    private Tab                                 previewTab;

    private FileMenu                            fileMenu;
    private LayoutMenu                          layoutMenu;
    private VisualizationMenu                   visualizationMenu;


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
    public void setProject(final Project project) {

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
    public void setUniverse(final Universe universe) {

        this.universe.set(universe);
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
    public void setLayout(final LayoutSet layoutSet) {

        this.layoutSet.set(layoutSet);
    }

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
    public void setVisualization(final Visualization visualization) {

        this.visualization.set(visualization);
    }



    /**
     * main method, will launch with command line arguments
     *
     * @param args
     *            command line arguments (unused)
     */
    public static void main(final String[] args) {

        Application.launch(args);
    }


    /**
     * Creates a new instance of the {@link JavaFXGui} class.
     */
    public JavaFXGui() {

        super();

        this.project = new SimpleObjectProperty<>();
        this.universe = new SimpleObjectProperty<>();
        this.layoutSet = new SimpleObjectProperty<>();
        this.visualization = new SimpleObjectProperty<>();
    }

    @Override
    public void start(final Stage primary) {

        this.stage = primary;
        this.stage.setTitle(this.title);

        XmlSerializer.Instance().registerClass(Project.class);
        XmlSerializer.Instance().registerClass(GraphMLDocument.class);
        XmlSerializer.Instance().registerClass(X3DDocument.class);

        this.scene = new Scene(new VBox(), 1000, 700, Color.LIGHTSLATEGREY);


        this.scene.getRoot().addEventHandler(
                SwitchTabEvent.SWITCH,
                event -> JavaFXGui.this.tabPane.getSelectionModel().select(
                        JavaFXGui.this.layoutInformationTab));

        this.scene
                .getRoot()
                .addEventHandler(
                        ExportLayoutEvent.EXPORT,
                        event -> {

                            final FileChooser fc = new FileChooser();
                            fc.setTitle("Save File As");
                            final FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(
                                    "X3D Files", "*.x3d");
                            fc.getExtensionFilters().add(filter);
                            File x3dSaveFile = fc.showSaveDialog(primary);

                            if (x3dSaveFile != null) {

                                if (!x3dSaveFile.getAbsolutePath().endsWith(
                                        ".x3d")) {

                                    // System.out.println("File extension");
                                    x3dSaveFile = new File(x3dSaveFile
                                            .getAbsolutePath() + ".x3d");
                                }

                                IOManager.Instance().save(x3dSaveFile,
                                        JavaFXGui.this.visualization.get(),
                                        X3DDocument.class);
                            }
                        });

        this.universeDetails = new UniverseEditorGridPane();
        this.universeDetails.universe().bindBidirectional(this.universe);

        this.graphDetails = new GraphEditorGridPane();
        this.graphDetails.universe().bindBidirectional(this.universe);

        this.layoutDetails = new LayoutDetailsVBox();
        this.layoutDetails.universe().bindBidirectional(this.universe);
        this.layoutDetails.layoutProperty().bindBidirectional(this.layoutSet);
        this.layoutDetails.visualization()
                .bindBidirectional(this.visualization);

        this.visDetails = new VisualizationDetailsVBox();
        this.visDetails.visualization().bindBidirectional(this.visualization);

        final Tooltip layoutTabTip = new Tooltip();
        layoutTabTip
                .setText("This tab will become enabled after graph(scale) have been selected from the Graph Information page");


        this.tabPane = new TabPane();
        this.tabPane.setMinHeight(30);

        this.graphInformationTab = new Tab("Graph Information");
        this.graphInformationTab.setDisable(true);
        this.layoutInformationTab = new Tab("Layout Information");
        this.visualizationInformationTab = new Tab("Visualization Information");
        this.universeInformationTab = new Tab("Universe Information");
        this.universeInformationTab.setClosable(false);
        this.graphInformationTab.setClosable(false);
        this.layoutInformationTab.setClosable(false);
        this.visualizationInformationTab.setClosable(false);
        this.previewTab = new Tab("Preview");
        this.previewTab.setDisable(true);
        this.previewTab.setClosable(false);


        this.tabPane.getTabs().addAll(this.universeInformationTab,
                this.graphInformationTab, this.layoutInformationTab,
                this.visualizationInformationTab, this.previewTab);

        this.universeInformationTab.setContent(this.universeDetails);
        this.graphInformationTab.setContent(this.graphDetails);
        this.layoutInformationTab.setContent(this.layoutDetails);
        this.visualizationInformationTab.setContent(this.visDetails);
        this.layoutInformationTab.setDisable(true);
        this.visualizationInformationTab.setDisable(true);

        ((VBox) this.scene.getRoot()).getChildren().addAll(
                this.createMenuBar(), this.tabPane);

        this.project.addListener((changedProperty, oldValue, newValue) -> {


            if (newValue != null) {

                if (!newValue.getLayouts().isEmpty()) {

                    // System.out.println("Layouts: " +
                    // newValue.getLayouts());
                JavaFXGui.this.layoutSet.set(newValue.getLayouts().get(0));
            }
            if (!newValue.getVisualizations().isEmpty()) {

                // System.out.println("Visualizations: "
                // + newValue.getVisualizations());
                JavaFXGui.this.visualization.set(newValue.getVisualizations()
                        .get(0));
            }
        }
    })  ;

        this.universe.addListener((ChangeListener<Universe>) (changedProperty,
                oldValue, newValue) -> {

            final boolean requiresUniverse = (newValue == null);

            JavaFXGui.this.layoutMenu.setDisable(requiresUniverse);
            JavaFXGui.this.graphInformationTab.setDisable(requiresUniverse);

            if (requiresUniverse) {

                JavaFXGui.this.layoutSet.set(null);
                JavaFXGui.this.visualization.set(null);
            }
        });

        this.layoutSet.addListener((ChangeListener<LayoutSet>) (observable,
                oldValue, newValue) -> {


            final boolean requiresLayout = (newValue == null);
            JavaFXGui.this.layoutInformationTab.setDisable(requiresLayout);
            JavaFXGui.this.visualizationMenu.setDisable(requiresLayout);

            if (JavaFXGui.this.getProject() != null) {
                JavaFXGui.this.getProject().clearLayouts();
            }
            // LayoutSet not null
                if (!requiresLayout) {

                    if (JavaFXGui.this.getProject() != null) {

                        JavaFXGui.this.getProject().addLayout(newValue);
                    }
                    JavaFXGui.this.tabPane.getSelectionModel().select(
                            JavaFXGui.this.layoutInformationTab);
                }
                // LayoutSet null
                else {

                    JavaFXGui.this.visualization.set(null);
                }
            });

        this.visualization.addListener((ChangeListener<Visualization>) (
                observable, oldValue, newValue) -> {

            final boolean requiresVisualization = (newValue == null);
            JavaFXGui.this.visualizationInformationTab
                    .setDisable(requiresVisualization);
            JavaFXGui.this.previewTab.setDisable(requiresVisualization);

            if (JavaFXGui.this.getProject() != null) {
                JavaFXGui.this.getProject().clearVisualizations();
            }
            // Visualization not null
                if (!requiresVisualization) {
                    Pane previewScene = null;
                    try {
                        previewScene = JavaFX3DEngine.Instance().convert(
                                newValue, Pane.class);
                        JavaFXGui.this.previewTab.setContent(previewScene);

                    }
                    catch (final ConversionException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    if (JavaFXGui.this.getProject() != null) {
                        JavaFXGui.this.getProject().addVisualization(newValue);
                    }
                    JavaFXGui.this.tabPane.getSelectionModel().select(
                            JavaFXGui.this.visualizationInformationTab);
                }
            });

        this.stage.setScene(this.scene);

        this.stage.show();
    }


    // this method for testing identity mapping of GPS coordinates

    // /**
    // * creates coordinates to test identity mapping
    // *
    // * @param uni
    // */
    // private void addCoordinateSchema(Universe uni) {
    //
    // Metadata lat;
    // Metadata lon;
    // Metadata ele;
    //
    // float i = 0;
    // for (INode node : uni.getNodes()) {
    // // x coord
    // lon = Metadata.createMetadata("Longitude", MetadataType.FLOAT,
    // (float) (50 * Math.cos(i)));
    // // y coord
    // lat = Metadata.createMetadata("Latitude", MetadataType.FLOAT,
    // (float) (50 * Math.sin(i)));
    // // z coord
    // ele = Metadata.createMetadata("Elevation", MetadataType.FLOAT,
    // (2 * i));
    //
    // uni.getNode(node.getID()).getMetadataProperty().addMetadata(lon);
    // uni.getNode(node.getID()).getMetadataProperty().addMetadata(lat);
    // uni.getNode(node.getID()).getMetadataProperty().addMetadata(ele);
    // i++;
    //
    // }
    //
    // }
    //


    private MenuBar createMenuBar() {


        final MenuBar menuBar = new MenuBar();

        this.fileMenu = new FileMenu(this.stage);
        this.universe.bind(this.fileMenu.universe());
        this.project.bind(this.fileMenu.project());

        this.layoutMenu = new LayoutMenu(this.stage);
        this.layoutMenu.layoutSet().bindBidirectional(this.layoutSet);
        this.layoutMenu.universe().bindBidirectional(this.universe);
        this.layoutMenu.setDisable(true);

        this.visualizationMenu = new VisualizationMenu(this.stage);
        this.visualizationMenu.visualization().bindBidirectional(
                this.visualization);
        this.visualizationMenu.layoutSet().bindBidirectional(this.layoutSet);
        this.visualizationMenu.universe().bindBidirectional(this.universe);
        this.visualizationMenu.setDisable(true);

        menuBar.getMenus().addAll(this.fileMenu, this.layoutMenu,
                this.visualizationMenu);
        return menuBar;
    }
}