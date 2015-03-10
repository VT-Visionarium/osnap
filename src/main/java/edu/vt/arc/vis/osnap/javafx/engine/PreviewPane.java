

package edu.vt.arc.vis.osnap.javafx.engine;

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




import edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualGraph;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualObject;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;

import org.jutility.math.geometry.Rectangle4;
import org.jutility.math.vectoralgebra.IPoint4;
import org.jutility.math.vectoralgebra.IVector4;
import org.jutility.math.vectoralgebra.Point4;
import org.jutility.math.vectoralgebra.VectorAlgebraicOperations;


/**
 * The {@link JavaFX3DEngine} class converts a {@link Visualization
 * visualizations} to a JavaFX 3D scene.
 * 
 * @author William H. Lund, Peter J. Radics
 * @version 1.1
 */
public class PreviewPane
        extends Pane {

    private final Xform               cameraXform    = new Xform();
    private final Xform               cameraXform2   = new Xform();
    private final Xform               cameraXform3   = new Xform();
    private final double              cameraDistance = 550;


    private double            mousePosX;
    private double            mousePosY;
    private double            mouseOldX;
    private double            mouseOldY;
    private double            mouseDeltaX;
    private double            mouseDeltaY;

    private Visualization     visualization;
    private SubScene          scene;
    private PerspectiveCamera camera;


    /**
     * Creates a new instance of the {@link PreviewPane} class for the provided
     * Visualization.
     * 
     * @param visualization
     *            the visualization to preview.
     */
    public PreviewPane(Visualization visualization) {

        this.visualization = visualization;

        Group root = new Group();
        Rectangle4<Double> viewingPlane = this.buildPreview(this.visualization,
                root);

        scene = new SubScene(root, 1024.0, 768.0, true,
                SceneAntialiasing.BALANCED);
        scene.setFill(Color.GREY);

        this.camera = PreviewPane.buildCamera(10, 10000, viewingPlane);
        setXForm(camera);
        scene.setCamera(camera);
        scene.widthProperty().bind(this.widthProperty());
        scene.heightProperty().bind(this.heightProperty());
        Label legend = new Label("Controls:\n"
                + "Primary Button: rotates model\n"
                + "Middle Button: repositions model\n"
                // + "Mouse Wheel: zoom in/out\n"
                + "Secondary Button: zoom in/out\n"
                + "SHIFT: speed up movement\n" + "CTRL: slow down movement");
        handleInput(scene);

        this.getChildren().add(scene);
        this.getChildren().add(legend);

    }



    /**
     * Builds the camera.
     */
    private void setXForm(PerspectiveCamera camera) {

        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
    }

    /**
     * Mouse events handler for rotating, translating, and zooming the preview.
     * 
     * @param scene
     */
    private void handleInput(SubScene scene) {

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {

                mousePosX = mouseEvent.getSceneX();
                mousePosY = mouseEvent.getSceneY();
                mouseOldX = mouseEvent.getSceneX();
                mouseOldY = mouseEvent.getSceneY();
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {

                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = mouseEvent.getSceneX();
                mousePosY = mouseEvent.getSceneY();
                mouseDeltaX = (mousePosX - mouseOldX);
                mouseDeltaY = (mousePosY - mouseOldY);

                double modifier = 1.0;
                double modifierFactor = 0.1;

                if (mouseEvent.isControlDown()) {
                    modifier = 0.1;
                }
                if (mouseEvent.isShiftDown()) {
                    modifier = 10.0;
                }
                if (mouseEvent.isPrimaryButtonDown()) {
                    cameraXform.getRotationY().setAngle(
                            cameraXform.getRotationY().getAngle() - mouseDeltaX
                                    * modifierFactor * modifier * 2.0); // or
                                                                        // -
                    cameraXform.getRotationX().setAngle(
                            cameraXform.getRotationX().getAngle() + mouseDeltaY
                                    * modifierFactor * modifier * 2.0); // or
                                                                        // +
                }
                else if (mouseEvent.isMiddleButtonDown()) {
                    cameraXform2.setTranslationX(cameraXform2.getTranslation()
                            .getX()
                            + mouseDeltaX
                            * modifierFactor
                            * modifier
                            * 1.0); // -
                    cameraXform2.setTranslationY(cameraXform2.getTranslation()
                            .getY()
                            + mouseDeltaY
                            * modifierFactor
                            * modifier
                            * 1.0); // -
                }
                else if (mouseEvent.isSecondaryButtonDown()) {
                    double z = camera.getTranslateZ();
                    double newZ = z + mouseDeltaY * modifierFactor * modifier;
                    camera.setTranslateZ(newZ);
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

        PreviewPane.calculateAndSetCameraCoordinates(camera, viewingPlane);


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

        // System.out.println("minX: " + minX + ", maxX: " + maxX);
        // System.out.println("minY: " + minY + ", maxY: " + maxY);
        // System.out.println("minZ: " + minZ);

        double xExtent = maxX - minX;
        double yExtent = maxY - minY;

        // System.out.println("xExtent: " + xExtent);
        // System.out.println("yExtent: " + yExtent);

        // c
        double viewingPlaneExtent = Math.max(xExtent, yExtent);

        // gamma
        double fieldOfView = Math.toRadians(camera.getFieldOfView());
        // System.out.println("FOV: " + fieldOfView);

        double alpha = Math.toRadians((180d - camera.getFieldOfView()) / 2d);
        // System.out.println("Alpha: " + alpha);

        // a = c * (sin alpha / sin gamma)
        double cathetus = viewingPlaneExtent
                * (Math.sin(alpha) / Math.sin(fieldOfView));

        // System.out.println("Cathetus: " + cathetus);

        // c/2
        double halfViewingPlaneExtent = viewingPlaneExtent / 2d;
        // System.out.println("Half viewing plane extent: "
        // + halfViewingPlaneExtent);

        // h = sqrt (a^2 - (1/2c)^2)
        double viewingDistance = Math.sqrt((cathetus * cathetus)
                - (halfViewingPlaneExtent * halfViewingPlaneExtent));
        // System.out.println("ViewingDistance: " + viewingDistance);

        IPoint4<Double> cameraCoordinates = new Point4<>((maxX + minX) / 2d,
                (maxY + minY) / 2d, minZ - viewingDistance, Double.class);

        // System.out.println("Camera coordinates: " + cameraCoordinates);

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
                    .getRotationAngle().doubleValue()));
            shape.setRotationAxis(new Point3D(visualObject.getRotation()
                    .getRotationAxis().getX().doubleValue(), visualObject.getRotation()
                    .getRotationAxis().getY().doubleValue(), visualObject.getRotation()
                    .getRotationAxis().getZ().doubleValue()));
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
