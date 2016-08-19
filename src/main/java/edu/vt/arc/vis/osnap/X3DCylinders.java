package edu.vt.arc.vis.osnap;


import java.io.File;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jutility.common.datatype.table.Table;
import org.jutility.io.ConversionException;
import org.jutility.io.IConverter;
import org.jutility.io.SerializationException;
import org.jutility.io.csv.CsvSerializer;
import org.jutility.io.xml.XmlSerializer;
import org.x3d.fields.MFColor;
import org.x3d.fields.MFFloat;
import org.x3d.fields.SFColor;
import org.x3d.model.Appearance;
import org.x3d.model.Background;
import org.x3d.model.Cylinder;
import org.x3d.model.Group;
import org.x3d.model.Head;
import org.x3d.model.Material;
import org.x3d.model.ProfileNames;
import org.x3d.model.Scene;
import org.x3d.model.Shape;
import org.x3d.model.Switch;
import org.x3d.model.TouchSensor;
import org.x3d.model.Transform;
import org.x3d.model.WorldInfo;
import org.x3d.model.X3DDocument;
import org.x3d.model.X3DGeometryNode;
import org.x3d.model.X3DNode;
import org.x3d.model.X3DVersion;

import edu.vt.arc.vis.osnap.core.domain.visualization.VisualObject;
import edu.vt.arc.vis.osnap.io.IOManager;
import edu.vt.arc.vis.osnap.io.x3d.X3DEngine;
import javafx.scene.paint.Color;


/**
 * @author Peter J. Radics
 *
 * @version 0.1.0
 */
public class X3DCylinders
        implements IConverter {

    /**
     * Runs the {@link X3DCylinders} hack.
     * 
     * @param args
     *            unused
     * @throws SerializationException
     * @throws ConversionException
     */
    public static void main(String[] args)
            throws SerializationException, ConversionException {
        // TODO Auto-generated method stub

        File file = new File("C:/Users/NewAccount/x3d.csv");

        Table<?> table = null;
        try {

            table = CsvSerializer.Instance().deserialize(file, Table.class);
        }
        catch (SerializationException e) {

            System.out.println("We done goofed up!");
        }

        if (table != null) {

            System.out.println("Table:\n" + table.toString());
            X3DDocument x3DDocument = X3DCylinders.Instance().convert(table,
                    X3DDocument.class);

            System.out.println("X3DDocument:\n" + x3DDocument);
            XmlSerializer.Instance().serialize(x3DDocument, "C:/Users/NewAccount/output.x3d");
        
            System.out.println("Done Done");
        }
        else {
            System.out.println("Table is null!");
        }
    }


    private static X3DCylinders s_Instance;

    /**
     * Returns the singleton instance of the {@link X3DEngine} class;
     * 
     * @return the singleton instance.
     */
    public static X3DCylinders Instance() {

        if (X3DCylinders.s_Instance == null) {

            X3DCylinders.s_Instance = new X3DCylinders();
        }

        return X3DCylinders.s_Instance;
    }


    private X3DDocument                          x3dDocument;
    private final Map<Color, Appearance>         colorMap;

    private final Map<VisualObject, Switch>      switches;


    /**
     * Constructs a new instance of the {@link X3DEngine} class.
     */
    private X3DCylinders() {

        this.colorMap = new LinkedHashMap<>();
        this.switches = new LinkedHashMap<>();
    }



    @Override
    public boolean supportsConversion(Class<?> sourceType,
            Class<?> targetType) {

        // lower bound required for source type.
        if (!Table.class.isAssignableFrom(sourceType)) {

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

        return returnType
                .cast(this.convert(Table.class.cast(documentToConvert)));
    }



    private X3DDocument convert(Table<?> table) {

         System.out.print(" Converting Table into X3D.");

        this.colorMap.clear();
        this.switches.clear();

        this.createX3DDocumentWithHeader("Nuclear Reactor Fuel Pool", null);


        Group cylinderGroup = new Group();

        for (int row = 0; row < table.rows(); row++) {

            String name = "Cylinder " + row;
            System.out.println("Processing Cylinder " + name);

            Object xValue = table.get(row, 0);
            Object yValue = table.get(row, 1);
            Object zValue = table.get(row, 2);

            Float x = Float.parseFloat(String.valueOf(xValue));
            Float y = Float.parseFloat(String.valueOf(yValue));
            Float z = Float.parseFloat(String.valueOf(zValue));



            Transform transformation = new Transform();
            transformation.setDEF(name);



            transformation.setTranslation(x, z, y);

            // The radius is 0.4572 and the height of each cylinder is 16.1708.

            Shape shape = this.createShape(Color.RED, 16.1708f, 0.4572f);

            transformation.getChildObjects().add(shape);

            cylinderGroup.getChildObjects().add(transformation);
        }


        for (Appearance appearance : this.colorMap.values()) {

            this.x3dDocument.getScene().getChildObjects().add(appearance);
        }

        this.createBackground();


        this.x3dDocument.getScene().getChildObjects().add(cylinderGroup);

        System.out.println(" Done.");

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



    private Shape createShape(Color color, float height, float width) {

        X3DGeometryNode geometry = null;

        Appearance appearance = this.retrieveAppearance(color);
        // switch (baseShape) {
        // case CONE:
        // Cone cone = new Cone();
        // cone.setHeight(height);
        // cone.setBottomRadius(width);
        // geometry = cone;
        // break;
        // case CUBE:
        // Box box = new Box();
        // box.setSize(new SFVec3f(width, height, width));
        // geometry = box;
        // break;
        // case CYLINDER:
        Cylinder cylinder = new Cylinder();
        cylinder.setHeight(height);
        cylinder.setRadius(width);
        geometry = cylinder;
        // break;
        // case SPHERE:
        // default:
        // Sphere sphere = new Sphere();
        // sphere.setRadius(height);
        // geometry = sphere;
        // break;
        // }

        Shape shape = new Shape(geometry, appearance);
        return shape;
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
