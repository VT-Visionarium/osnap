package edu.vt.arc.vis.osnap.io.x3d;


import javafx.scene.paint.Color;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;

import org.jutility.common.datatype.util.NumberUtils;
import org.jutility.io.ConversionException;
import org.jutility.io.IConverter;
import org.jutility.math.geometry.GeometricOperations;
import org.jutility.math.geometry.IRotation;
import org.jutility.math.geometry.IScaleFactor;
import org.jutility.math.vectoralgebra.IPoint4;
import org.jutility.math.vectoralgebra.IVector4;
import org.jutility.math.vectoralgebra.Vector4;
import org.jutility.math.vectoralgebra.VectorAlgebraicOperations;
import org.x3d.fields.MFColor;
import org.x3d.fields.MFFloat;
import org.x3d.fields.MFString;
import org.x3d.fields.SFColor;
import org.x3d.fields.SFInt32;
import org.x3d.fields.SFRotation;
import org.x3d.fields.SFString;
import org.x3d.fields.SFVec3f;
import org.x3d.model.Appearance;
import org.x3d.model.Background;
import org.x3d.model.Billboard;
import org.x3d.model.BooleanFilter;
import org.x3d.model.BooleanToggle;
import org.x3d.model.BooleanTrigger;
import org.x3d.model.Box;
import org.x3d.model.Cone;
import org.x3d.model.Cylinder;
import org.x3d.model.Group;
import org.x3d.model.Head;
import org.x3d.model.IntegerTrigger;
import org.x3d.model.KeySensor;
import org.x3d.model.Material;
import org.x3d.model.ProfileNames;
import org.x3d.model.ROUTE;
import org.x3d.model.Scene;
import org.x3d.model.Script;
import org.x3d.model.Shape;
import org.x3d.model.Sphere;
import org.x3d.model.Switch;
import org.x3d.model.Text;
import org.x3d.model.TouchSensor;
import org.x3d.model.Transform;
import org.x3d.model.WorldInfo;
import org.x3d.model.X3DChildNode;
import org.x3d.model.X3DDocument;
import org.x3d.model.X3DGeometryNode;
import org.x3d.model.X3DNode;
import org.x3d.model.X3DVersion;

import edu.vt.arc.vis.osnap.core.domain.visualization.Label;
import edu.vt.arc.vis.osnap.core.domain.visualization.Viewpoint;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualGraph;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualObject;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;
import edu.vt.arc.vis.osnap.io.x3d.KeyScriptFactory.ActionKey;


/**
 * The {@link X3DEngine} class provides a {@link IConverter converter} from
 * {@link Visualization visualizations} to {@link X3DDocument X3D documents}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
public class X3DEngine
        implements IConverter {

    private static X3DEngine s_Instance;

    /**
     * Returns the singleton instance of the {@link X3DEngine} class;
     * 
     * @return the singleton instance.
     */
    public static X3DEngine Instance() {

        if (X3DEngine.s_Instance == null) {

            X3DEngine.s_Instance = new X3DEngine();
        }

        return X3DEngine.s_Instance;
    }


    private X3DDocument                          x3dDocument;
    private final Map<Color, Appearance>         colorMap;

    private final Map<VisualObject, TouchSensor> sensors;
    private final Map<VisualObject, Switch>      switches;


    /**
     * Constructs a new instance of the {@link X3DEngine} class.
     */
    private X3DEngine() {

        this.colorMap = new LinkedHashMap<>();
        this.sensors = new LinkedHashMap<>();
        this.switches = new LinkedHashMap<>();
    }



    @Override
    public boolean supportsConversion(Class<?> sourceType, Class<?> targetType) {

        // lower bound required for source type.
        if (!Visualization.class.isAssignableFrom(sourceType)) {

            return false;
        }

        // upper bound required for target type.
        if (!targetType.isAssignableFrom(X3DDocument.class)) {

            return false;
        }

        return true;
    }



    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.io.IConverter#convert(java.lang.Object,
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



    private X3DDocument convert(Visualization visualization) {

        // System.out.print("  Converting Visualization into X3D.");

        this.colorMap.clear();
        this.sensors.clear();
        this.switches.clear();

        this.createX3DDocumentWithHeader(visualization.getID(), null);


        List<Group> graphGroups = new LinkedList<>();

        for (VisualGraph graph : visualization.getVisualGraphs()) {

            graphGroups.add(this.processGraph(graph));
        }

        for (Appearance appearance : this.colorMap.values()) {

            this.x3dDocument.getScene().getChildObjects().add(appearance);
        }

        this.createBackground();


        for (Viewpoint viewpoint : visualization.getViewpoints()) {

            org.x3d.model.Viewpoint x3dViewpoint = new org.x3d.model.Viewpoint();

            x3dViewpoint.setCenterOfRotation(new SFVec3f(viewpoint.getLookAt()
                    .getX().floatValue(), viewpoint.getLookAt().getY()
                    .floatValue(), viewpoint.getLookAt().getZ().floatValue()));

            x3dViewpoint.setDescription(new SFString(viewpoint.getID()));

            x3dViewpoint.setPosition(new SFVec3f(viewpoint.getPosition().getX()
                    .floatValue(), viewpoint.getPosition().getY().floatValue(),
                    viewpoint.getPosition().getZ().floatValue()));

            x3dViewpoint.setOrientation(new SFRotation(viewpoint.getRotation()
                    .getRotationAxis().getX().floatValue(), viewpoint
                    .getRotation().getRotationAxis().getY().floatValue(),
                    viewpoint.getRotation().getRotationAxis().getZ()
                            .floatValue(), viewpoint.getRotation()
                            .getRotationAngle().floatValue()));
            this.x3dDocument.getScene().getChildObjects().add(x3dViewpoint);
        }

        for (Group group : graphGroups) {

            this.x3dDocument.getScene().getChildObjects().add(group);
        }

        this.createRoutesForLabelSwitches(x3dDocument);

        // System.out.println(" Done.");

        return x3dDocument;
    }


    private void createX3DDocumentWithHeader(String id, String description) {

        x3dDocument = new X3DDocument();
        x3dDocument.setVersion(X3DVersion.X3D_3_2);
        x3dDocument.setProfile(ProfileNames.IMMERSIVE);

        Head header = new Head();
        x3dDocument.setHead(header);

        Scene scene = new Scene();


        // <WorldInfo
        // title='Keyboard Test'
        // info='"ttt"'/>
        WorldInfo worldInfo = new WorldInfo();
        worldInfo.setTitle("Visualization of " + id);
        if (description != null) {
            worldInfo.getInfo().add("ttt");
        }

        x3dDocument.setScene(scene);

    }

    private void createBackground() {

        MFFloat angles = new MFFloat(1.309f, 1.571f);

        MFColor groundColor = new MFColor(new SFColor(0.2f, 0.2f, 0.2f),
                new SFColor(0.5f, 0.5f, 0.5f), new SFColor(0.8f, 0.8f, 0.8f));

        MFColor skyColor = new MFColor(new SFColor(0.4f, 0.4f, 0.4f),
                new SFColor(0.7f, 0.7f, 0.7f), new SFColor(1.0f, 1.0f, 1.0f));


        Background background = this.createBackground(null, "background",
                angles, angles, groundColor, skyColor);

        this.x3dDocument.getScene().getChildObjects().add(background);
    }

    /**
     * Processes the provided graph and converts it into an X3D Group.
     * 
     * @param graph
     *            the graph to process.
     * @return an X3D Group.
     */
    private Group processGraph(VisualGraph graph) {

        Group graphGroup = new Group();
        graphGroup.setDEF(graph.getID());
        for (VisualNode node : graph.getVisualNodes()) {

            X3DChildNode transformation = this.processNode(node);

            if (transformation != null) {
                graphGroup.getChildObjects().add(transformation);
                // break;
            }
        }

        for (VisualEdge edge : graph.getVisualEdges()) {

            X3DChildNode transformation = this.processEdge(edge);

            if (transformation != null) {
                graphGroup.getChildObjects().add(transformation);
                // break;
            }

        }

        return graphGroup;
    }

    /**
     * Processes the provided node and converts it into an X3D Transform.
     * 
     * @param node
     *            the node to process.
     * @return an X3D Transform.
     */
    private X3DChildNode processNode(VisualNode node) {


        if (node.isVisible()) {

            String name = node.getGraphObjectID();

            // System.out.println("Processing " + name);

            Transform transformation = new Transform();
            transformation.setDEF(name);

            if (node.getPosition() != null) {

                IPoint4<?> position = node.getPosition();

                transformation.setTranslation(
                        NumberUtils.cast(position.getX(), Float.class),
                        NumberUtils.cast(position.getY(), Float.class),
                        NumberUtils.cast(position.getZ(), Float.class));
            }

            if (node.getRotation() != null) {
                IRotation<?> rotation = node.getRotation();
                transformation.setRotation(NumberUtils.cast(rotation
                        .getRotationAxis().getX(), Float.class), NumberUtils
                        .cast(rotation.getRotationAxis().getY(), Float.class),
                        NumberUtils.cast(rotation.getRotationAxis().getZ(),
                                Float.class), NumberUtils.cast(
                                rotation.getRotationAngle(), Float.class));
            }

            if (node.getScale() != null) {

                IScaleFactor<?> scale = node.getScale();
                transformation.setScale(
                        NumberUtils.cast(scale.getScaleFactorX(), Float.class),
                        NumberUtils.cast(scale.getScaleFactorY(), Float.class),
                        NumberUtils.cast(scale.getScaleFactorZ(), Float.class));
            }

            Shape shape = createShape(node.getShape(), node.getColor());

            transformation.getChildObjects().add(shape);

            if (node.getLabel() != null && node.getLabel().isVisible()) {
                transformation.getChildObjects().add(
                        this.createLabel(node, name + "_label"));

                TouchSensor touchSensor = new TouchSensor();
                touchSensor.setDEF(name + "_touchSensor");
                touchSensor.setDescription(new SFString("Toggle Label"));
                transformation.getChildObjects().add(touchSensor);
                this.sensors.put(node, touchSensor);
            }


            return transformation;
        }
        return null;
    }

    private Shape createShape(
            edu.vt.arc.vis.osnap.core.domain.visualization.Shape baseShape,
            Color color) {

        return this.createShape(baseShape, color, 1f);
    }

    private Shape createShape(
            edu.vt.arc.vis.osnap.core.domain.visualization.Shape baseShape,
            Color color, float height) {

        return this.createShape(baseShape, color, height, 1.0f);
    }

    private Shape createShape(
            edu.vt.arc.vis.osnap.core.domain.visualization.Shape baseShape,
            Color color, float height, float width) {

        X3DGeometryNode geometry = null;

        Appearance appearance = this.retrieveAppearance(color);
        switch (baseShape) {
            case CONE:
                Cone cone = new Cone();
                cone.setHeight(height);
                cone.setBottomRadius(width);
                geometry = cone;
                break;
            case CUBE:
                Box box = new Box();
                box.setSize(new SFVec3f(width, height, width));
                geometry = box;
                break;
            case CYLINDER:
                Cylinder cylinder = new Cylinder();
                cylinder.setHeight(height);
                cylinder.setRadius(width);
                geometry = cylinder;
                break;
            case SPHERE:
            default:
                Sphere sphere = new Sphere();
                sphere.setRadius(height);
                geometry = sphere;
                break;
        }

        Shape shape = new Shape(geometry, appearance);
        return shape;
    }

    /**
     * Processes the provided edge and converts it into an X3D Transform.
     * 
     * @param edge
     *            the edge to process.
     * @return an X3D Transform.
     */
    private X3DChildNode processEdge(VisualEdge edge) {

        if (edge.isVisible()) {

            String name = edge.getGraphObjectID();
            // System.out.println("Processing " + edge);

            Transform transformation = new Transform();
            transformation.setDEF(name);
            IVector4<Float> distanceVector = VectorAlgebraicOperations
                    .subtract(edge.getVisualTargetNode().getPosition(), edge
                            .getVisualSourceNode().getPosition(), Float.class);

            Float distance = distanceVector.length();


            if (edge.getPosition() != null) {

                IPoint4<?> position = edge.getPosition();

                transformation.setTranslation(
                        NumberUtils.cast(position.getX(), Float.class),
                        NumberUtils.cast(position.getY(), Float.class),
                        NumberUtils.cast(position.getZ(), Float.class));
            }
            else {



                IVector4<Float> halfDistance = VectorAlgebraicOperations
                        .multiply(0.5f, distanceVector, Float.class);

                IPoint4<Float> position = VectorAlgebraicOperations.add(edge
                        .getVisualSourceNode().getPosition(), halfDistance,
                        Float.class);

                transformation.setTranslation(position.getX(), position.getY(),
                        position.getZ());
            }

            if (edge.getRotation() != null) {
                IRotation<?> rotation = edge.getRotation();
                transformation.setRotation(NumberUtils.cast(rotation
                        .getRotationAxis().getX(), Float.class), NumberUtils
                        .cast(rotation.getRotationAxis().getY(), Float.class),
                        NumberUtils.cast(rotation.getRotationAxis().getZ(),
                                Float.class), NumberUtils.cast(
                                rotation.getRotationAngle(), Float.class));
            }
            else {
                IRotation<Float> rotation = GeometricOperations
                        .getRotationBetweenVectors(
                                Vector4.J_UNIT_VECTOR(Float.class),
                                distanceVector, Float.class);

                transformation.setRotation(rotation.getRotationAxis().getX(),
                        rotation.getRotationAxis().getY(), rotation
                                .getRotationAxis().getZ(), rotation
                                .getRotationAngle());
            }

            if (edge.getScale() != null) {

                IScaleFactor<?> scale = edge.getScale();
                transformation.setScale(
                        NumberUtils.cast(scale.getScaleFactorX(), Float.class),
                        1.0f,
                        NumberUtils.cast(scale.getScaleFactorZ(), Float.class));
            }


            Shape shape = this.createShape(edge.getShape(), edge.getColor(),
                    distance, 0.25f);

            transformation.getChildObjects().add(shape);

            if (edge.getLabel() != null && edge.getLabel().isVisible()) {
                transformation.getChildObjects().add(
                        this.createLabel(edge, name + "_label"));

                TouchSensor touchSensor = new TouchSensor();
                touchSensor.setDEF(name + "_touchSensor");
                touchSensor.setDescription(new SFString("Toggle Label"));
                transformation.getChildObjects().add(touchSensor);
                this.sensors.put(edge, touchSensor);
            }

            return transformation;
        }
        return null;
    }

    private Appearance retrieveAppearance(Color color) {

        Appearance appearance = new Appearance();

        if (!this.colorMap.containsKey(color)) {


            Appearance newAppearance = new Appearance();
            newAppearance.setDEF("COLOR-" + color.toString());

            Material material = new Material();
            material.setDiffuseColor(new SFColor(color));
            newAppearance.setMaterial(material);

            this.colorMap.put(color, newAppearance);

        }
        appearance.setUSE(this.colorMap.get(color));

        return appearance;
    }

    /**
     * Creates an X3D Background node with the specified parameters.
     * 
     * @param namespace
     *            the namespace name of the created node.
     * @param name
     *            the name of the created node.
     * @param groundAngles
     *            the ground angles.
     * @param skyAngles
     *            the sky angles.
     * @param groundColors
     *            the ground colors.
     * @param skyColors
     *            the sky colors.
     * @return an X3D Background node.
     */
    private Background createBackground(String namespace, String name,
            MFFloat groundAngles, MFFloat skyAngles, MFColor groundColors,
            MFColor skyColors) {

        Background background = new Background();

        this.setX3DNodeName(background, namespace, name);

        background.getGroundAngle().getValue().addAll(groundAngles.getValue());
        background.getSkyAngle().getValue().addAll(skyAngles.getValue());

        background.getGroundColor().clear();
        background.getGroundColor().getValue().addAll(groundColors.getValue());

        background.getSkyColor().clear();
        background.getSkyColor().getValue().addAll(skyColors.getValue());

        return background;
    }

    /**
     * Creates a label.
     * 
     * @param label
     *            the label
     * @param name
     *            the name of the created label.
     * @return the Transformation containing the label.
     */
    private X3DChildNode createLabel(VisualObject object, String name) {

        Label label = object.getLabel();

        Switch labelSwitch = new Switch();
        labelSwitch.setDEF(name + "_switch");
        labelSwitch.setWhichChoice(new SFInt32(1));

        SFVec3f offset = null;

        if (label.getOffset() != null) {
            offset = new SFVec3f(NumberUtils.cast(label.getOffset().getX(),
                    Float.class), NumberUtils.cast(label.getOffset().getY(),
                    Float.class), NumberUtils.cast(label.getOffset().getZ(),
                    Float.class));
        }

        Billboard billboard = new Billboard();
        billboard.setAxisOfRotation(new SFVec3f(0f, 0f, 0f));

        Transform transform = new Transform();

        transform.setCenter(null);
        transform.setRotation(null);
        transform.setScale(new SFVec3f(1f, 1f, 1f));
        transform.setScaleOrientation(null);
        transform.setTranslation(offset);


        Text theText = new Text();

        theText.setString(new MFString());
        theText.getString().add(label.getText());

        Shape textShape = new Shape();
        textShape.setGeometry(theText);

        Appearance labelAppearance = this.retrieveAppearance(Color.WHITESMOKE);
        textShape.setAppearance(labelAppearance);

        labelSwitch.getChildObjects().add(billboard);
        billboard.getChildObjects().add(transform);
        transform.getChildObjects().add(textShape);

        this.setX3DNodeName(billboard, null, name);

        this.switches.put(object, labelSwitch);

        return labelSwitch;
    }


    private void createRoutesForLabelSwitches(X3DDocument document) {

        Scene scene = document.getScene();


        boolean firstRun = true;
        BooleanToggle toggleSwitchableLabels = null;
        for (VisualObject visualObject : this.switches.keySet()) {


            if (firstRun) {

                Script keyFilterScript = KeyScriptFactory.generateKeyScript(
                        "LabelToggleKey", ActionKey.END, 'f');


                KeySensor toggleSwitchableLabelsKey = new KeySensor();
                toggleSwitchableLabelsKey.setDEF("toggleSwitchableLablesKey");


                ROUTE actionKeyPress = new ROUTE();
                actionKeyPress.setFromNode(toggleSwitchableLabelsKey);
                actionKeyPress.setFromField("actionKeyPress");
                actionKeyPress.setToNode(keyFilterScript);
                actionKeyPress.setToField("actionKeyPress");

                ROUTE actionKeyRelease = new ROUTE();
                actionKeyRelease.setFromNode(toggleSwitchableLabelsKey);
                actionKeyRelease.setFromField("actionKeyRelease");
                actionKeyRelease.setToNode(keyFilterScript);
                actionKeyRelease.setToField("actionKeyRelease");

                ROUTE keyPress = new ROUTE();
                keyPress.setFromNode(toggleSwitchableLabelsKey);
                keyPress.setFromField("keyPress");
                keyPress.setToNode(keyFilterScript);
                keyPress.setToField("keyPress");

                ROUTE keyRelease = new ROUTE();
                keyRelease.setFromNode(toggleSwitchableLabelsKey);
                keyRelease.setFromField("keyRelease");
                keyRelease.setToNode(keyFilterScript);
                keyRelease.setToField("keyRelease");


                scene.getChildObjects().add(keyFilterScript);
                scene.getChildObjects().add(toggleSwitchableLabelsKey);
                scene.getChildObjects().add(keyPress);
                scene.getChildObjects().add(keyRelease);
                scene.getChildObjects().add(actionKeyPress);
                scene.getChildObjects().add(actionKeyRelease);



                toggleSwitchableLabels = new BooleanToggle();
                toggleSwitchableLabels.setDEF("toggleSwitchableLables");



                ROUTE toggleLabelKey = new ROUTE();
                toggleLabelKey.setFromField("key");
                toggleLabelKey.setFromNode(keyFilterScript);
                toggleLabelKey.setToField("set_boolean");
                toggleLabelKey.setToNode(toggleSwitchableLabels);


                scene.getChildObjects().add(toggleSwitchableLabels);
                scene.getChildObjects().add(toggleLabelKey);

                firstRun = false;
            }



            Switch labelSwitch = this.switches.get(visualObject);
            TouchSensor sensor = this.sensors.get(visualObject);

            if (labelSwitch != null && sensor != null) {

                ROUTE switchToggle = new ROUTE();
                switchToggle.setFromField("toggle");
                switchToggle.setFromNode(toggleSwitchableLabels);
                switchToggle.setToField("enabled");
                switchToggle.setToNode(sensor);

                scene.getChildObjects().add(switchToggle);

                String name = visualObject.getGraphObjectID();

                BooleanFilter bf_0 = new BooleanFilter();
                bf_0.setDEF(name + "_bf_0");

                IntegerTrigger on_0 = new IntegerTrigger();
                on_0.setIntegerKey(new SFInt32(0));
                on_0.setDEF(name + "_on_0");
                IntegerTrigger off_0 = new IntegerTrigger();
                off_0.setIntegerKey(new SFInt32(1));
                off_0.setDEF(name + "_off_0");

                BooleanToggle bt_0 = new BooleanToggle();
                bt_0.setDEF(name + "_bt_0");
                BooleanTrigger btr_0 = new BooleanTrigger();
                btr_0.setDEF(name + "_btr_0");

                scene.getChildObjects().add(bf_0);
                scene.getChildObjects().add(on_0);
                scene.getChildObjects().add(off_0);
                scene.getChildObjects().add(bt_0);
                scene.getChildObjects().add(btr_0);

                ROUTE touchTime = new ROUTE();
                touchTime.setFromField("touchTime");
                touchTime.setFromNode(sensor);
                touchTime.setToField("set_triggerTime");
                touchTime.setToNode(btr_0);

                ROUTE triggerTrue = new ROUTE();
                triggerTrue.setFromField("triggerTrue");
                triggerTrue.setFromNode(btr_0);
                triggerTrue.setToField("set_boolean");
                triggerTrue.setToNode(bt_0);

                ROUTE toggle = new ROUTE();
                toggle.setFromField("toggle");
                toggle.setFromNode(bt_0);
                toggle.setToField("set_boolean");
                toggle.setToNode(bf_0);

                ROUTE inputTrue = new ROUTE();
                inputTrue.setFromField("inputTrue");
                inputTrue.setFromNode(bf_0);
                inputTrue.setToField("set_boolean");
                inputTrue.setToNode(on_0);

                ROUTE inputFalse = new ROUTE();
                inputFalse.setFromField("inputFalse");
                inputFalse.setFromNode(bf_0);
                inputFalse.setToField("set_boolean");
                inputFalse.setToNode(off_0);

                ROUTE triggerValueOn = new ROUTE();
                triggerValueOn.setFromField("triggerValue");
                triggerValueOn.setFromNode(on_0);
                triggerValueOn.setToField("whichChoice");
                triggerValueOn.setToNode(labelSwitch);

                ROUTE triggerValueOff = new ROUTE();
                triggerValueOff.setFromField("triggerValue");
                triggerValueOff.setFromNode(off_0);
                triggerValueOff.setToField("whichChoice");
                triggerValueOff.setToNode(labelSwitch);

                scene.getChildObjects().add(touchTime);
                scene.getChildObjects().add(triggerTrue);
                scene.getChildObjects().add(toggle);
                scene.getChildObjects().add(inputTrue);
                scene.getChildObjects().add(inputFalse);
                scene.getChildObjects().add(triggerValueOn);
                scene.getChildObjects().add(triggerValueOff);
            }
        }

    }

    /**
     * Sets the name of the provided X3D node.
     * 
     * @param node
     *            the node to name.
     * @param namespace
     *            the namespace of the node.
     * @param name
     *            the new name.
     */
    private void setX3DNodeName(X3DNode node, String namespace, String name) {

        if (name != null) {
            String qualifiedName = name;
            if (namespace != null) {
                qualifiedName = namespace + ":" + qualifiedName;
            }
            node.setDEF(qualifiedName);
        }
    }


}
