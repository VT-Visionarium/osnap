package edu.vt.arc.vis.osnap.javafx.engine;


import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
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
import org.jutility.math.vectoralgebra.IPoint4;
import org.jutility.math.vectoralgebra.IVector4;
import org.jutility.math.vectoralgebra.Point4;
import org.jutility.math.vectoralgebra.VectorAlgebraicOperations;

import edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualGraph;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualObject;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;
import edu.vt.arc.vis.osnap.io.x3d.X3DEngine;


/**
 * The {@link JavaFX3DEngine} class converts a {@link Visualization
 * visualizations} to a JavaFX 3D scene.
 *
 * @author William H. Lund, Peter J. Radics
 * @version 1.1
 */
public class OldJavaFX3DEngine
implements IConverter {

    private final Xform              cameraXform    = new Xform();
    private final Xform              cameraXform2   = new Xform();
    private final Xform              cameraXform3   = new Xform();
    private final double             cameraDistance = 550;
    private PerspectiveCamera        camera;
    private static OldJavaFX3DEngine s_Instance;


    private double                   mousePosX;
    private double                   mousePosY;
    private double                   mouseOldX;
    private double                   mouseOldY;
    private double                   mouseDeltaX;
    private double                   mouseDeltaY;


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
    public boolean supportsConversion(final Class<?> sourceType,
            final Class<?> targetType) {

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



    @Override
    public <T, S> S convert(final T documentToConvert,
            final Class<? extends S> returnType)
            throws ConversionException {


        final Class<?> documentType = documentToConvert.getClass();
        if (!this.supportsConversion(documentType, returnType)) {

            throw new ConversionException("Conversion from " + documentType
                    + " to " + returnType + " is not supported by "
                    + this.getClass().toString() + "!");
        }

        return returnType.cast(this.convert(Visualization.class
                .cast(documentToConvert)));
    }



    private Pane convert(final Visualization visualization) {

        final Group root = new Group();
        final Rectangle4<Double> viewingPlane = this.buildPreview(
                visualization, root);

        final SubScene scene = new SubScene(root, 1024.0, 768.0, true,
                SceneAntialiasing.BALANCED);
        scene.setFill(Color.GREY);

        this.camera = OldJavaFX3DEngine.buildCamera(10, 10000, viewingPlane);
        this.buildCamera(this.camera);
        scene.setCamera(this.camera);

        final Pane previewPane = new Pane(scene);

        scene.widthProperty().bind(previewPane.widthProperty());
        scene.heightProperty().bind(previewPane.heightProperty());
        this.handleMouse(scene);
        return previewPane;
    }

    /**
     * Builds camera.
     */
    private void buildCamera(final PerspectiveCamera camera) {

        // root.getChildren().add(cameraXform);
        this.cameraXform.getChildren().add(this.cameraXform2);
        this.cameraXform2.getChildren().add(this.cameraXform3);
        this.cameraXform3.getChildren().add(camera);
        this.cameraXform3.setRotateZ(180.0);

        camera.setNearClip(10.0);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-this.cameraDistance);
        this.cameraXform.getRotationY().setAngle(320.0);
        this.cameraXform.getRotationX().setAngle(40.0);
    }

    /**
     * Mouse events handler for rotating, translating, and zooming the preview.
     *
     * @param scene
     */
    private void handleMouse(final SubScene scene) {


        scene.setOnMousePressed(me -> {

            OldJavaFX3DEngine.this.mousePosX = me.getSceneX();
            OldJavaFX3DEngine.this.mousePosY = me.getSceneY();
            OldJavaFX3DEngine.this.mouseOldX = me.getSceneX();
            OldJavaFX3DEngine.this.mouseOldY = me.getSceneY();
        });
        scene.setOnMouseDragged(me -> {

            OldJavaFX3DEngine.this.mouseOldX = OldJavaFX3DEngine.this.mousePosX;
            OldJavaFX3DEngine.this.mouseOldY = OldJavaFX3DEngine.this.mousePosY;
            OldJavaFX3DEngine.this.mousePosX = me.getSceneX();
            OldJavaFX3DEngine.this.mousePosY = me.getSceneY();
            OldJavaFX3DEngine.this.mouseDeltaX = (OldJavaFX3DEngine.this.mousePosX - OldJavaFX3DEngine.this.mouseOldX);
            OldJavaFX3DEngine.this.mouseDeltaY = (OldJavaFX3DEngine.this.mousePosY - OldJavaFX3DEngine.this.mouseOldY);

            double modifier = 1.0;
            final double modifierFactor = 0.1;

            if (me.isControlDown()) {
                modifier = 0.1;
            }
            if (me.isShiftDown()) {
                modifier = 10.0;
            }
            if (me.isPrimaryButtonDown()) {
                OldJavaFX3DEngine.this.cameraXform.getRotationY().setAngle(
                        OldJavaFX3DEngine.this.cameraXform.getRotationY()
                                .getAngle()
                                - (OldJavaFX3DEngine.this.mouseDeltaX
                                        * modifierFactor * modifier * 2.0)); // or
                                                                             // -
                OldJavaFX3DEngine.this.cameraXform.getRotationX().setAngle(
                        OldJavaFX3DEngine.this.cameraXform.getRotationX()
                                .getAngle()
                                - (OldJavaFX3DEngine.this.mouseDeltaY
                                        * modifierFactor * modifier * 2.0)); // or
                                                                             // +
            }
            else if (me.isSecondaryButtonDown()) {
                final double z = OldJavaFX3DEngine.this.camera.getTranslateZ();
                final double newZ = z
                        + (OldJavaFX3DEngine.this.mouseDeltaY * modifierFactor * modifier);
                OldJavaFX3DEngine.this.camera.setTranslateZ(newZ);
            }
            else if (me.isMiddleButtonDown()) {
                OldJavaFX3DEngine.this.cameraXform2.getTranslation().setX(
                        OldJavaFX3DEngine.this.cameraXform2.getTranslation()
                                .getX()
                                + (OldJavaFX3DEngine.this.mouseDeltaX
                                        * modifierFactor * modifier * 0.3)); // -
                OldJavaFX3DEngine.this.cameraXform2.getTranslation().setY(
                        OldJavaFX3DEngine.this.cameraXform2.getTranslation()
                                .getY()
                                + (OldJavaFX3DEngine.this.mouseDeltaY
                                        * modifierFactor * modifier * 0.3)); // -
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

        final PerspectiveCamera camera = new PerspectiveCamera(true);

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
    private Rectangle4<Double> buildPreview(final Visualization visualization,
            final Group root) {

        Rectangle4<Double> viewingPlane = new Rectangle4<>(new Point4<>(0, 0,
                0, Double.class), new Point4<>(0, 0, 0, Double.class),
                Double.class);

        // Will run through list of each graph in the visualization.
        for (final VisualGraph graph : visualization.getVisualGraphs()) {

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
    private Rectangle4<Double> processGraph(final VisualGraph graph,
            final Group root, final Rectangle4<Double> viewingPlane) {

        Rectangle4<Double> updatedViewingPlane = viewingPlane;

        final Group graphGroup = new Group();
        for (final VisualNode node : graph.getVisualNodes()) {

            if (node.isVisible()) {
                final Shape3D nodeShape = this.processNode(node);

                if (nodeShape != null) {

                    graphGroup.getChildren().add(nodeShape);
                }
                updatedViewingPlane = this.updateViewingPlane(
                        updatedViewingPlane, node);
            }
        }

        for (final VisualEdge edge : graph.getVisualEdges()) {

            final Shape3D edgeShape = this.processEdge(edge);

            if (edgeShape != null) {

                graphGroup.getChildren().add(edgeShape);
            }

        }

        root.getChildren().add(graphGroup);

        return updatedViewingPlane;
    }

    private static void calculateAndSetCameraCoordinates(
            final PerspectiveCamera camera,
            final Rectangle4<Double> viewingPlane) {

        final double minX = viewingPlane.getBottomLeftCorner().getX();
        final double minY = viewingPlane.getBottomLeftCorner().getY();
        final double maxX = viewingPlane.getTopRightCorner().getX();
        final double maxY = viewingPlane.getTopRightCorner().getY();
        final double minZ = viewingPlane.getBottomLeftCorner().getZ();

        // System.out.println("minX: " + minX + ", maxX: " + maxX);
        // System.out.println("minY: " + minY + ", maxY: " + maxY);
        // System.out.println("minZ: " + minZ);

        final double xExtent = maxX - minX;
        final double yExtent = maxY - minY;

        // System.out.println("xExtent: " + xExtent);
        // System.out.println("yExtent: " + yExtent);

        // c
        final double viewingPlaneExtent = Math.max(xExtent, yExtent);

        // gamma
        final double fieldOfView = Math.toRadians(camera.getFieldOfView());
        // System.out.println("FOV: " + fieldOfView);

        final double alpha = Math
                .toRadians((180d - camera.getFieldOfView()) / 2d);
        // System.out.println("Alpha: " + alpha);

        // a = c * (sin alpha / sin gamma)
        final double cathetus = viewingPlaneExtent
                * (Math.sin(alpha) / Math.sin(fieldOfView));

        // System.out.println("Cathetus: " + cathetus);

        // c/2
        final double halfViewingPlaneExtent = viewingPlaneExtent / 2d;
        // System.out.println("Half viewing plane extent: "
        // + halfViewingPlaneExtent);

        // h = sqrt (a^2 - (1/2c)^2)
        final double viewingDistance = Math.sqrt((cathetus * cathetus)
                - (halfViewingPlaneExtent * halfViewingPlaneExtent));
        // System.out.println("ViewingDistance: " + viewingDistance);

        final IPoint4<Double> cameraCoordinates = new Point4<>(
                (maxX + minX) / 2d, (maxY + minY) / 2d, minZ - viewingDistance,
                Double.class);

        // System.out.println("Camera coordinates: " + cameraCoordinates);

        camera.setTranslateX(cameraCoordinates.getX());
        camera.setTranslateY(cameraCoordinates.getY());
        camera.setTranslateZ(cameraCoordinates.getZ());
    }

    private Rectangle4<Double> updateViewingPlane(
            final Rectangle4<Double> viewingPlane, final VisualNode visualNode) {

        double minX = viewingPlane.getBottomLeftCorner().getX();
        double maxX = viewingPlane.getTopRightCorner().getX();
        double minY = viewingPlane.getBottomLeftCorner().getY();
        double maxY = viewingPlane.getTopRightCorner().getY();
        double minZ = viewingPlane.getBottomLeftCorner().getZ();



        final IPoint4<?> position = visualNode.getPosition();

        final double x = position.getX().doubleValue();
        final double y = position.getY().doubleValue();
        final double z = position.getZ().doubleValue();

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
    private Shape3D processNode(final VisualNode node) {

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
    private Shape3D processEdge(final VisualEdge edge) {

        if (edge.isVisible()) {

            Shape3D shape = null;
            final IVector4<Float> distanceVector = VectorAlgebraicOperations
                    .subtract(edge.getVisualTargetNode().getPosition(), edge
                            .getVisualSourceNode().getPosition(), Float.class);

            final Float distance = distanceVector.length();

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


    private void setPosition(final Shape3D shape,
            final VisualObject visualObject) {

        shape.setTranslateX(visualObject.getPosition().getX().doubleValue());
        shape.setTranslateY(visualObject.getPosition().getY().doubleValue());
        shape.setTranslateZ(visualObject.getPosition().getZ().doubleValue());
    }

    private void setRotation(final Shape3D shape,
            final VisualObject visualObject) {

        if (visualObject.getRotation() != null) {

            shape.setRotate(Math.toDegrees(visualObject.getRotation()
                    .getRotationAngle().doubleValue()));
            shape.setRotationAxis(new Point3D(visualObject.getRotation()
                    .getRotationAxis().getX().doubleValue(), visualObject
                    .getRotation().getRotationAxis().getY().doubleValue(),
                    visualObject.getRotation().getRotationAxis().getZ()
                    .doubleValue()));
        }
    }

    private void setScale(final Shape3D shape, final VisualObject visualObject) {

        if (visualObject.getScale() != null) {

            shape.setScaleX(visualObject.getScale().getScaleFactorX()
                    .doubleValue());
            shape.setScaleY(visualObject.getScale().getScaleFactorY()
                    .doubleValue());
            shape.setScaleZ(visualObject.getScale().getScaleFactorZ()
                    .doubleValue());
        }
    }

    private void setColor(final Shape3D shape, final VisualObject visualObject) {

        final PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(visualObject.getColor());
        material.setSpecularColor(visualObject.getColor());

        shape.setMaterial(material);
    }
}
