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


package graphVisualizer.graphicsEngine;


import graphVisualizer.conversion.x3d.X3DEngine;
import graphVisualizer.visualization.VisualEdge;
import graphVisualizer.visualization.VisualGraph;
import graphVisualizer.visualization.VisualNode;
import graphVisualizer.visualization.VisualObject;
import graphVisualizer.visualization.Visualization;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;

import org.jutility.io.ConversionException;
import org.jutility.io.IConverter;
import org.jutility.math.geometry.Rectangle4;
import org.jutility.math.vectorAlgebra.IPoint4;
import org.jutility.math.vectorAlgebra.IVector4;
import org.jutility.math.vectorAlgebra.Point4;
import org.jutility.math.vectorAlgebra.VectorAlgebraicOperations;


/**
 * The {@link JavaFX3DEngine} class converts a {@link Visualization
 * visualizations} to a JavaFX 3D scene.
 * 
 * @author William H. Lund, Peter J. Radics
 * @version 1.1
 */
public class OldJavaFX3DEngine
        implements IConverter {

    final Xform                      cameraXform    = new Xform();
    final Xform                      cameraXform2   = new Xform();
    final Xform                      cameraXform3   = new Xform();
    final double                     cameraDistance = 550;
    PerspectiveCamera                camera;
    private static OldJavaFX3DEngine s_Instance;

    // final Xform visualizationGroup = new Xform();

    // private boolean timelinePlaying = false;
    // private double ONE_FRAME = 1.0 / 24.0;
    // private double DELTA_MULTIPLIER = 200.0;
    // private double CONTROL_MULTIPLIER = 0.1;
    // private double SHIFT_MULTIPLIER = 0.1;
    // private double ALT_MULTIPLIER = 0.5;

    double                           mousePosX;
    double                           mousePosY;
    double                           mouseOldX;
    double                           mouseOldY;
    double                           mouseDeltaX;
    double                           mouseDeltaY;


    /**
     * Returns the singleton instance of the {@link X3DEngine} class;
     * 
     * @return the singleton instance.
     */
    public static OldJavaFX3DEngine Instance() {

        if (OldJavaFX3DEngine.s_Instance == null) {

            OldJavaFX3DEngine.s_Instance = new OldJavaFX3DEngine();
        }

        return OldJavaFX3DEngine.s_Instance;
    }

    @Override
    public boolean supportsConversion(Class<?> sourceType, Class<?> targetType) {

        // lower bound required for source type.
        if (!Visualization.class.isAssignableFrom(sourceType)) {

            return false;
        }

        // upper bound required for target type.
        if (!targetType.isAssignableFrom(Pane.class)) {

            return false;
        }

        return true;
    }



    /*
     * (non-Javadoc)
     * 
     * @see graphVisualizer.conversion.IConverter#convert(java.lang.Object,
     * java.lang.Class)
     */
    @Override
    public <T, S> S convert(T documentToConvert, Class<? extends S> returnType)
            throws ConversionException {


        Class<?> documentType = documentToConvert.getClass();
        if (!this.supportsConversion(documentType, returnType)) {

            throw new ConversionException("Conversion from " + documentType
                    + " to " + returnType + " is not supported by "
                    + this.getClass().toString() + "!");
        }

        return returnType.cast(this.convert(Visualization.class
                .cast(documentToConvert)));
    }



    private Pane convert(Visualization visualization)
            throws ConversionException {

        Group root = new Group();
        Rectangle4<Double> viewingPlane = this
                .buildPreview(visualization, root);

        SubScene scene = new SubScene(root, 1024.0, 768.0, true,
                SceneAntialiasing.BALANCED);
        scene.setFill(Color.GREY);

        camera = OldJavaFX3DEngine.buildCamera(10, 10000, viewingPlane);
        buildCamera(camera);
        scene.setCamera(camera);

        Pane previewPane = new Pane(scene);

        scene.widthProperty().bind(previewPane.widthProperty());
        scene.heightProperty().bind(previewPane.heightProperty());
        handleMouse(scene);
        return previewPane;
    }

    /**
     * Builds camera.
     */
    private void buildCamera(PerspectiveCamera camera) {

        // root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateZ(180.0);

        camera.setNearClip(10.0);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-cameraDistance);
        cameraXform.getRotationY().setAngle(320.0);
        cameraXform.getRotationX().setAngle(40.0);
    }

    /**
     * Mouse events handler for rotating, translating, and zooming the preview.
     * 
     * @param scene
     */
    private void handleMouse(SubScene scene) {


        scene.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent me) {

                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent me) {

                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseDeltaX = (mousePosX - mouseOldX);
                mouseDeltaY = (mousePosY - mouseOldY);

                double modifier = 1.0;
                double modifierFactor = 0.1;

                if (me.isControlDown()) {
                    modifier = 0.1;
                }
                if (me.isShiftDown()) {
                    modifier = 10.0;
                }
                if (me.isPrimaryButtonDown()) {
                    cameraXform.getRotationY().setAngle(
                            cameraXform.getRotationY().getAngle() - mouseDeltaX
                                    * modifierFactor * modifier * 2.0); // or
                                                                        // -
                    cameraXform.getRotationX().setAngle(
                            cameraXform.getRotationX().getAngle() - mouseDeltaY
                                    * modifierFactor * modifier * 2.0); // or
                                                                        // +
                }
                else if (me.isSecondaryButtonDown()) {
                    double z = camera.getTranslateZ();
                    double newZ = z + mouseDeltaY * modifierFactor * modifier;
                    camera.setTranslateZ(newZ);
                }
                else if (me.isMiddleButtonDown()) {
                    cameraXform2.getTranslation().setX(
                            cameraXform2.getTranslation().getX() + mouseDeltaX
                                    * modifierFactor * modifier * 0.3); // -
                    cameraXform2.getTranslation().setY(
                            cameraXform2.getTranslation().getY() + mouseDeltaY
                                    * modifierFactor * modifier * 0.3); // -
                }
            }
        });

    }



    /**
     * 
     * @param nearClip
     * @param farClip
     * @param viewingPlane
     * @return
     */
    private static PerspectiveCamera buildCamera(final double nearClip,
            final double farClip, final Rectangle4<Double> viewingPlane) {

        PerspectiveCamera camera = new PerspectiveCamera(true);

        OldJavaFX3DEngine
                .calculateAndSetCameraCoordinates(camera, viewingPlane);


        camera.setNearClip(nearClip);
        camera.setFarClip(farClip);
        camera.setRotate(180);



        return camera;
    }

    /**
     * Method to set color shading for nodes/edges and set up the calling path
     * of building the Xform for the given scene.
     * 
     * @param visualization
     *            visualization of the current user specs
     */
    private Rectangle4<Double> buildPreview(Visualization visualization,
            Group root) {

        Rectangle4<Double> viewingPlane = new Rectangle4<>(new Point4<>(0, 0,
                0, Double.class), new Point4<>(0, 0, 0, Double.class),
                Double.class);

        // Will run through list of each graph in the visualization.
        for (VisualGraph graph : visualization.getVisualGraphs()) {

            viewingPlane = this.processGraph(graph, root, viewingPlane);
        }

        return viewingPlane;
    }

    /**
     * Creates an initial Group to hold all of the node/edge shapes. Essentially
     * creates a universe which then adds nodes and edges to itself from the
     * processNode and processEdge methods.
     * 
     * @param graph
     *            the graph to process.
     * @param root
     *            the root of the visualization.
     */
    private Rectangle4<Double> processGraph(VisualGraph graph, Group root,
            Rectangle4<Double> viewingPlane) {

        Rectangle4<Double> updatedViewingPlane = viewingPlane;

        Group graphGroup = new Group();
        for (VisualNode node : graph.getVisualNodes()) {

            if (node.isVisible()) {
                Shape3D nodeShape = this.processNode(node);

                if (nodeShape != null) {

                    graphGroup.getChildren().add(nodeShape);
                }
                updatedViewingPlane = this.updateViewingPlane(
                        updatedViewingPlane, node);
            }
        }

        for (VisualEdge edge : graph.getVisualEdges()) {

            Shape3D edgeShape = this.processEdge(edge);

            if (edgeShape != null) {

                graphGroup.getChildren().add(edgeShape);
            }

        }

        root.getChildren().add(graphGroup);

        return updatedViewingPlane;
    }

    private static void calculateAndSetCameraCoordinates(
            PerspectiveCamera camera, Rectangle4<Double> viewingPlane) {

        double minX = viewingPlane.getBottomLeftCorner().getX();
        double minY = viewingPlane.getBottomLeftCorner().getY();
        double maxX = viewingPlane.getTopRightCorner().getX();
        double maxY = viewingPlane.getTopRightCorner().getY();
        double minZ = viewingPlane.getBottomLeftCorner().getZ();

//        System.out.println("minX: " + minX + ", maxX: " + maxX);
//        System.out.println("minY: " + minY + ", maxY: " + maxY);
//        System.out.println("minZ: " + minZ);

        double xExtent = maxX - minX;
        double yExtent = maxY - minY;

//        System.out.println("xExtent: " + xExtent);
//        System.out.println("yExtent: " + yExtent);

        // c
        double viewingPlaneExtent = Math.max(xExtent, yExtent);

        // gamma
        double fieldOfView = Math.toRadians(camera.getFieldOfView());
//        System.out.println("FOV: " + fieldOfView);

        double alpha = Math.toRadians((180d - camera.getFieldOfView()) / 2d);
//        System.out.println("Alpha: " + alpha);

        // a = c * (sin alpha / sin gamma)
        double cathetus = viewingPlaneExtent
                * (Math.sin(alpha) / Math.sin(fieldOfView));

//        System.out.println("Cathetus: " + cathetus);

        // c/2
        double halfViewingPlaneExtent = viewingPlaneExtent / 2d;
//        System.out.println("Half viewing plane extent: "
//                + halfViewingPlaneExtent);

        // h = sqrt (a^2 - (1/2c)^2)
        double viewingDistance = Math.sqrt((cathetus * cathetus)
                - (halfViewingPlaneExtent * halfViewingPlaneExtent));
//        System.out.println("ViewingDistance: " + viewingDistance);

        IPoint4<Double> cameraCoordinates = new Point4<>((maxX + minX) / 2d,
                (maxY + minY) / 2d, minZ - viewingDistance, Double.class);

//        System.out.println("Camera coordinates: " + cameraCoordinates);

        camera.setTranslateX(cameraCoordinates.getX());
        camera.setTranslateY(cameraCoordinates.getY());
        camera.setTranslateZ(cameraCoordinates.getZ());
    }

    private Rectangle4<Double> updateViewingPlane(
            Rectangle4<Double> viewingPlane, VisualNode visualNode) {

        double minX = viewingPlane.getBottomLeftCorner().getX();
        double maxX = viewingPlane.getTopRightCorner().getX();
        double minY = viewingPlane.getBottomLeftCorner().getY();
        double maxY = viewingPlane.getTopRightCorner().getY();
        double minZ = viewingPlane.getBottomLeftCorner().getZ();



        IPoint4<?> position = visualNode.getPosition();

        double x = position.getX().doubleValue();
        double y = position.getY().doubleValue();
        double z = position.getZ().doubleValue();

        if (x < minX) {

            minX = x;
        }
        else if (x > maxX) {

            maxX = x;
        }
        if (y < minY) {

            minY = y;
        }
        else if (y > maxY) {

            maxY = y;
        }
        if (z < minZ) {

            minZ = z;
        }

        return new Rectangle4<>(new Point4<>(minX, minY, minZ, Double.class),
                new Point4<>(maxX, maxY, minZ, Double.class), Double.class);
    }


    /**
     * Processes the provided node and converts it into an {@link Shape3D}.
     * 
     * @param node
     *            the node to process.
     * @return a {@link Shape3D}
     */
    private Shape3D processNode(VisualNode node) {

        Shape3D shape = null;

        if (node.isVisible()) {

            switch (node.getShape()) {
                case CONE:
                    break;
                case CUBE:
                    shape = new Box();
                    break;
                case CYLINDER:
                    shape = new Cylinder();
                    break;
                case SPHERE:
                    shape = new Sphere();

                    break;
                default:
                    break;

            }
            if (shape != null) {

                this.setColor(shape, node);

                this.setScale(shape, node);

                this.setPosition(shape, node);
            }
        }
        return shape;
    }

    /**
     * Processes the provided edge and converts it into a {@link Shape3D}.
     * 
     * @param edge
     *            the edge to process.
     * @return a {@link Shape3D}.
     */
    private Shape3D processEdge(VisualEdge edge) {

        if (edge.isVisible()) {

            Shape3D shape = null;
            IVector4<Float> distanceVector = VectorAlgebraicOperations
                    .subtract(edge.getVisualTargetNode().getPosition(), edge
                            .getVisualSourceNode().getPosition(), Float.class);

            Float distance = distanceVector.length();

            switch (edge.getShape()) {
                case CONE:
                    break;
                case CUBE:
                    shape = new Box(0.25, distance, 0.25);
                    break;
                case CYLINDER:
                    shape = new Cylinder(0.25, distance);
                    break;
                case SPHERE:
                    break;
                default:
                    break;
            }
            if (shape != null) {

                this.setColor(shape, edge);

                this.setScale(shape, edge);
                shape.setScaleY(1.0);

                this.setPosition(shape, edge);
                this.setRotation(shape, edge);

                return shape;
            }
        }
        return null;
    }


    private void setPosition(Shape3D shape, VisualObject visualObject) {

        shape.setTranslateX(visualObject.getPosition().getX().doubleValue());
        shape.setTranslateY(visualObject.getPosition().getY().doubleValue());
        shape.setTranslateZ(visualObject.getPosition().getZ().doubleValue());
    }

    private void setRotation(Shape3D shape, VisualObject visualObject) {

        if (visualObject.getRotation() != null) {

            shape.setRotate(Math.toDegrees(visualObject.getRotation()
                    .getAngle().doubleValue()));
            shape.setRotationAxis(new Point3D(visualObject.getRotation()
                    .getAxis().getX().doubleValue(), visualObject.getRotation()
                    .getAxis().getY().doubleValue(), visualObject.getRotation()
                    .getAxis().getZ().doubleValue()));
        }
    }

    private void setScale(Shape3D shape, VisualObject visualObject) {

        if (visualObject.getScale() != null) {

            shape.setScaleX(visualObject.getScale().getScaleFactorX()
                    .doubleValue());
            shape.setScaleY(visualObject.getScale().getScaleFactorY()
                    .doubleValue());
            shape.setScaleZ(visualObject.getScale().getScaleFactorZ()
                    .doubleValue());
        }
    }

    private void setColor(Shape3D shape, VisualObject visualObject) {

        final PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(visualObject.getColor());
        material.setSpecularColor(visualObject.getColor());

        shape.setMaterial(material);
    }
}
