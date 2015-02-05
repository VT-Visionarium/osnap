package org.jutility.serialization.xml;


import java.io.File;
import java.util.LinkedHashSet;
import java.util.Set;

import org.jutility.io.ConversionException;
import org.jutility.io.xml.XmlSerializer;

import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;
import edu.vt.arc.vis.osnap.core.domain.layout.LayoutSet;
import edu.vt.arc.vis.osnap.core.domain.layout.LayoutRegistry;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseRadialTreeLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleColorLayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;
import edu.vt.arc.vis.osnap.io.owl.OWLConverter;
import edu.vt.arc.vis.osnap.io.owl.OWLSerializer;
import javafx.scene.paint.Color;

import com.hp.hpl.jena.ontology.OntModel;


public class XmlSerializerTest {

    public XmlSerializerTest() {

        // TODO Auto-generated constructor stub
    }

    /**
     * Tester
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)
            throws Exception {

        LayoutRegistry.Instance();

        String universeFilename = "/Users/peter/XmlSerializationTest_Universe.xml";
        String layoutFilename = "/Users/peter/XmlSerializationTest_Layout.xml";
        String visualizationFilename = "/Users/peter/XmlSerializationTest_Visualization.xml";


        System.out.println("======================================\n");
        Universe universe = null;


        System.out.print("Deserializing Ontology.");

        OntModel ontology = OWLSerializer.Instance().deserialize(

                // "/Users/peter/Desktop/ontologies/wzlMachineTools.owl"
                "http://www.biopax.org/release/biopax-level3.owl",
                OntModel.class);

        System.out.println(" Done.");


        System.out.print("Converting Ontology into internal format.");

        try {
            universe = OWLConverter.Instance()
                    .convert(ontology, Universe.class);

        }
        catch (ConversionException ex) {
            System.out.println(ex.getMessage());
            System.exit(-1);
        }

        // LinkedHashSet<String> nodes = new LinkedHashSet<>();
        // nodes.add("base:Customer");
        // nodes.add("base:Energy");
        // nodes.add("base:Data");
        // universe.createHyperEdgeFromIdentifiers("MyHyperedge",
        // "file:///Users/peter/Desktop/ontologies/base.owl", nodes);

        System.out.println(" Done.\n");

        System.out.print("XML Serializing Universe.");
        XmlSerializer.Instance().serialize(universe, universeFilename);

        System.out.println(" Done.");

        Universe deserializedUniverse = null;

        System.out.print("XML Deserializing Universe.");
        try {
            deserializedUniverse = XmlSerializer.Instance().deserialize(
                    new File(universeFilename), Universe.class);
        }
        catch (Exception ex) {
            System.out.println("Exception caught: ");
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
            System.out.println(ex.getCause());
        }
        System.out.println(" Done.\n");

        System.out.println("Original universe equals deserialized universe: "
                + universe.equals(deserializedUniverse));

        System.out
                .println("Original universe identical to deserialized universe: "
                        + universe.isIdentical(deserializedUniverse));

        System.exit(-1);
        System.out.println("======================================\n");

        System.out.print("Creating LayoutSet.");

        LayoutSet layoutSet = LayoutSet.defaultLayout(universe);


        INode rootNode = universe.getNode("owl:Thing");
        if (rootNode == null) {

            System.out.println("Substituting owl:Thing with "
                    + universe.getNodes().iterator().next());
            rootNode = universe.getNodes().iterator().next();
        }
        System.out.println("root node: " + rootNode);

        ILayout provider = null;
        // provider = new TieredOrbitalLayout(rootNode, null,
        // 0.1f, 1.0f, 250f, true);
        //
        //
        //
        // provider = new PrefuseBalloonTreeLayout(rootNode);
        //
        // provider = new PrefuseNodeLinkTreeLayout(rootNode);
        //
        provider = new PrefuseRadialTreeLayout(rootNode);
        //
        // provider = new
        // PrefuseSquarifiedTreeMapLayout(rootNode);
        //
        // provider = new ForceDirectedLayout();
        //
        // provider = new PrefuseFruchtermanReingoldLayout();
        //
        //
        //
        // provider.setXOutput(CoordinateComponent.FIRST_COMPONENT);
        //
        // provider.setYOutput(CoordinateComponent.THIRD_COMPONENT);
        //
        // provider.setZOutput(CoordinateComponent.SECOND_COMPONENT);
        //
        //
        layoutSet.addLayoutForVisualProperty(provider,
                edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty.NODE_X_POSITION);
        layoutSet.addLayoutForVisualProperty(provider,
                edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty.NODE_Y_POSITION);
        // layout.addLayoutProviderForVisualProperty(provider,
        // edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty.NODE_Z_POSITION);


        Set<IGraphObject> restriction = new LinkedHashSet<>();

        restriction.add(universe.getNodes().iterator().next());
        restriction.add(universe.getEdges().iterator().next());

        SimpleColorLayout sclc = new SimpleColorLayout(
                Color.RED);

        sclc.setRestriction(restriction);


        layoutSet.addLayoutForVisualProperty(sclc,
                VisualProperty.NODE_COLOR);


        System.out.println(" Done.");

        System.out.println();

        System.out.print("XML Serializing LayoutSet.");
        XmlSerializer.Instance().serialize(layoutSet, layoutFilename);
        System.out.println(" Done.");


        System.out.print("XML Deserializing LayoutSet.");
        LayoutSet deserializedLayout = null;

        try {
            deserializedLayout = XmlSerializer.Instance().deserialize(
                    new File(layoutFilename), LayoutSet.class);
        }
        catch (Exception ex) {
            System.out.println("Exception caught: ");
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
            System.out.println(ex.getCause());
        }



        System.out.println(" Done.\n");
        System.out.println("Original layout equals deserialized layout: "
                + layoutSet.equals(deserializedLayout));

        System.out.println("Original layout identical to deserialized layout: "
                + layoutSet.isIdentical(deserializedLayout));

        System.out.println("Deserialized LayoutSet: " + deserializedLayout);
        System.out.println("======================================\n");

        System.out.print("Applying LayoutSet.");
        layoutSet.layout();
        System.out.println(" Done.");
        System.out.print("Applying deserialized LayoutSet.");
        deserializedLayout.layout();
        System.out.println(" Done.\n");

        Visualization visualization = layoutSet.getVisualization();
        Visualization duplicateVisualization = deserializedLayout
                .getVisualization();

        System.out
                .println("Original visualization equals deserialized visualization: "
                        + visualization.equals(duplicateVisualization));
        System.out
                .println("Original visualization identical to deserialized visualization: "
                        + visualization.isIdentical(duplicateVisualization));
        System.out.println("======================================\n");

        //
        //
        // System.out.print("XML Serializing Visualization.");
        // XmlSerializer.serialize(visualization, visualizationFilename);
        // System.out.println(" Done.");
        //
        // System.out.print("XML Deserializing Visualization.");
        // Visualization deserializedVisualization = null;
        // try {
        // deserializedVisualization = XmlSerializer.Instance().deserialize(
        // visualizationFilename, Visualization.class);
        // }
        // catch (Exception ex) {
        // System.out.println("Exception caught: ");
        // System.out.println(ex.getMessage());
        // System.out.println(ex.getStackTrace());
        // System.out.println(ex.getCause());
        // }
        //
        // System.out.println(" Done.\n");
        // System.out
        // .println("Original visualization equals deserialized visualization: "
        // + visualization.equals(deserializedVisualization));
        //
        // System.out
        // .println("Original visualization identical to deserialized visualization: "
        // + visualization.isIdentical(deserializedVisualization));
        //
        // System.out.println("======================================\n");
        // Interval<Double> interval = new Interval<>(0.0, 10.0);
        //
        // XmlSerializer.serialize(interval,
        // "/Users/peter/XmlSerializationTest_Interval.xml");
        // Interval<?> deserializedInterval = null;
        // try {
        // deserializedInterval = XmlSerializer.deserialize(
        // "/Users/peter/XmlSerializationTest_Interval.xml",
        // Interval.class);
        // }
        // catch (Exception ex) {
        // System.out.println("Exception caught: ");
        // System.out.println(ex.getMessage());
        // System.out.println(ex.getStackTrace());
        // System.out.println(ex.getCause());
        // }
        //
        //
        // System.out
        // .println("Original interval equals to deserialized interval: "
        // + interval.equals(deserializedInterval));
        //
        // LinearIntervalToIntervalValueMapping<Double, Double> intervalMapping
        // = new LinearIntervalToIntervalValueMapping<>(
        // 0.0, 100.0, 0.0, 1.0, Double.class, Double.class);
        // System.out.println(intervalMapping);
        // XmlSerializer.serialize(intervalMapping,
        // "/Users/peter/XmlSerializationTest_IntervalValueMap.xml");
        //
        // LinearIntervalToIntervalValueMapping<?, ?>
        // deserializedIntervalMapping = null;
        // try {
        // deserializedIntervalMapping = XmlSerializer.deserialize(
        // "/Users/peter/XmlSerializationTest_IntervalValueMap.xml",
        // LinearIntervalToIntervalValueMapping.class);
        // }
        // catch (Exception ex) {
        // System.out.println("Exception caught: ");
        // System.out.println(ex.getMessage());
        // System.out.println(ex.getStackTrace());
        // System.out.println(ex.getCause());
        // }
        // System.out
        // .println("Original interval mapping equals to deserialized mapping: "
        // + intervalMapping.equals(deserializedIntervalMapping));
        //
        // Set<Double> doubleSet = new LinkedHashSet<>();
        // doubleSet.add(0.0);
        // doubleSet.add(10.0);
        // NToOneValueMapping<Double, Double> nToOne = new NToOneValueMapping<>(
        // doubleSet, 100.0);
        // XmlSerializer.serialize(nToOne,
        // "/Users/peter/XmlSerializationTest_NToOneValueMap.xml");
        //
        // NToOneValueMapping<?, ?> deserializedNToOne = null;
        // try {
        // deserializedNToOne = XmlSerializer.deserialize(
        // "/Users/peter/XmlSerializationTest_NToOneValueMap.xml",
        // NToOneValueMapping.class);
        // }
        // catch (Exception ex) {
        // System.out.println("Exception caught: ");
        // System.out.println(ex.getMessage());
        // System.out.println(ex.getStackTrace());
        // System.out.println(ex.getCause());
        // }
        // System.out
        // .println("Original n-to-one mapping equals to deserialized mapping: "
        // + nToOne.equals(deserializedNToOne));
        //
        // OneToOneValueMapping<Double, Double> oneToOne = new
        // OneToOneValueMapping<>(
        // 10.0, 100.0);
        // XmlSerializer.serialize(oneToOne,
        // "/Users/peter/XmlSerializationTest_OneToOneValueMap.xml");
        //
        // OneToOneValueMapping<?, ?> deserializedOneToOne = null;
        // try {
        // deserializedOneToOne = XmlSerializer.deserialize(
        // "/Users/peter/XmlSerializationTest_OneToOneValueMap.xml",
        // OneToOneValueMapping.class);
        // }
        // catch (Exception ex) {
        // System.out.println("Exception caught: ");
        // System.out.println(ex.getMessage());
        // System.out.println(ex.getStackTrace());
        // System.out.println(ex.getCause());
        // }
        // System.out
        // .println("Original one-to-one mapping equals to deserialized mapping: "
        // + oneToOne.equals(deserializedOneToOne));
        //
        //
        //
        // Mapping<String, Double, Double, String> mapping = new Mapping<String,
        // Double, Double, String>(
        // "theDomainKey", "theCoDomainKey", Double.class, Double.class);
        //
        // mapping.addValueMapping(intervalMapping);
        // mapping.addValueMapping(oneToOne);
        // mapping.addValueMapping(nToOne);
        //
        // XmlSerializer.serialize(mapping,
        // "/Users/peter/XmlSerializationTest_Mapping.xml");
        //
        // Mapping<?, ?, ?, ?> deserializedMapping = null;
        // try {
        // deserializedMapping = XmlSerializer.deserialize(
        // "/Users/peter/XmlSerializationTest_Mapping.xml",
        // Mapping.class);
        // }
        // catch (Exception ex) {
        // System.out.println("Exception caught: ");
        // System.out.println(ex.getMessage());
        // System.out.println(ex.getStackTrace());
        // System.out.println(ex.getCause());
        // }
        // System.out.println("Original mapping equals to deserialized mapping: "
        // + mapping.equals(deserializedMapping));
        //
        //
        //
        // Tuple4<String> tuple4 = new Tuple4<>("x value", "y value", "z value",
        // "w value", String.class);
        //
        // String tuple4filename =
        // "/Users/peter/XmlSerializationTest_Tuple4.xml";
        //
        // System.out.print("XML Serializing Tuple4.");
        // XmlSerializer.serialize(tuple4, tuple4filename);
        // System.out.println(" Done.");
        //
        //
        // System.out.print("XML Deserializing Tuple4.");
        // Tuple4<?> deserializedTuple4 = null;
        //
        // try {
        // deserializedTuple4 = XmlSerializer.deserialize(tuple4filename,
        // Tuple4.class);
        // }
        // catch (Exception ex) {
        // System.out.println("Exception caught: ");
        // System.out.println(ex.getMessage());
        // System.out.println(ex.getStackTrace());
        // System.out.println(ex.getCause());
        // }
        //
        // System.out.println(" Done.");
        //
        // System.out.println("Original tuple4 equals to deserialized tuple4: "
        // + tuple4.equals(deserializedTuple4));
        // System.out.println("Original tuple4: " + tuple4);
        // System.out.println("Deserialized tuple4: " + deserializedTuple4);
        //
        // System.out.println();
        // System.out.println();
        //
        // Tuple<String> tuple = new Tuple<>(String.class, "eenie", "meenie",
        // "miney", "moo", "foobar");
        //
        // String tuplefilename = "/Users/peter/XmlSerializationTest_Tuple.xml";
        //
        // System.out.print("XML Serializing Tuple.");
        // XmlSerializer.serialize(tuple, tuplefilename);
        // System.out.println(" Done.");
        //
        //
        // System.out.print("XML Deserializing Tuple.");
        // Tuple<?> deserializedTuple = null;
        //
        // try {
        // deserializedTuple = XmlSerializer.deserialize(tuplefilename,
        // Tuple.class);
        // }
        // catch (Exception ex) {
        // System.out.println("Exception caught: ");
        // System.out.println(ex.getMessage());
        // System.out.println(ex.getStackTrace());
        // System.out.println(ex.getCause());
        // }
        // System.out.println(" Done.");
        //
        //
        // System.out.println("Original tuple equals to deserialized tuple: "
        // + tuple.equals(deserializedTuple));
        // System.out.println("Original tuple: " + tuple);
        // System.out.println("Deserialized tuple: " + deserializedTuple);
        //
        // System.out.println();
        // System.out.println();
        //
        // org.jutility.math.vectorAlgebra.Tuple4<Double> numericTuple4 = new
        // org.jutility.math.vectorAlgebra.Tuple4<>(
        // 5, 6, 7, 8, Double.class);
        //
        // String numericTuple4filename =
        // "/Users/peter/XmlSerializationTest_NumericTuple4.xml";
        //
        // System.out.print("XML Serializing NumericTuple4.");
        // XmlSerializer.serialize(numericTuple4, numericTuple4filename);
        // System.out.println(" Done.");
        //
        //
        // System.out.print("XML Deserializing NumericTuple4.");
        // org.jutility.math.vectorAlgebra.Tuple4<?> deserializedNumericTuple4 = null;
        //
        // try {
        // deserializedNumericTuple4 = XmlSerializer.deserialize(
        // numericTuple4filename,
        // org.jutility.math.vectorAlgebra.Tuple4.class);
        // }
        // catch (Exception ex) {
        // System.out.println("Exception caught: ");
        // System.out.println(ex.getMessage());
        // System.out.println(ex.getStackTrace());
        // System.out.println(ex.getCause());
        // }
        //
        // System.out.println(" Done.");
        //
        // System.out
        // .println("Original numeric tuple4 equals to deserialized numeric tuple4: "
        // + numericTuple4.equals(deserializedNumericTuple4));
        // System.out.println("Original numeric tuple4: " + numericTuple4);
        // System.out.println("Deserialized numeric tuple4: "
        // + deserializedNumericTuple4);
        //
        // System.out.println();
        // System.out.println();
        //
        //
        // Point4<Double> point4 = new Point4<>(5, 6, 7, Double.class);
        //
        // String point4filename =
        // "/Users/peter/XmlSerializationTest_Point4.xml";
        //
        // System.out.print("XML Serializing Point4.");
        // XmlSerializer.serialize(point4, point4filename);
        // System.out.println(" Done.");
        //
        //
        // System.out.print("XML Deserializing Point4.");
        // Point4<?> deserializedPoint4 = null;
        //
        // try {
        // deserializedPoint4 = XmlSerializer.deserialize(point4filename,
        // Point4.class);
        // }
        // catch (Exception ex) {
        // System.out.println("Exception caught: ");
        // System.out.println(ex.getMessage());
        // System.out.println(ex.getStackTrace());
        // System.out.println(ex.getCause());
        // }
        //
        // System.out.println(" Done.");
        //
        // System.out.println("Original point4 equals to deserialized point4: "
        // + point4.equals(deserializedPoint4));
        // System.out.println("Original point4: " + point4);
        // System.out.println("Deserialized point4: " + deserializedPoint4);
        //
        // System.out.println();
        // System.out.println();
        //
        //
        // Vector4<Double> vector4 = new Vector4<>(5, 6, 7, Double.class);
        //
        // String vector4filename =
        // "/Users/peter/XmlSerializationTest_Vector4.xml";
        //
        // System.out.print("XML Serializing Vector4.");
        // XmlSerializer.serialize(vector4, vector4filename);
        // System.out.println(" Done.");
        //
        //
        // System.out.print("XML Deserializing Point4.");
        // Vector4<?> deserializedVector4 = null;
        //
        // try {
        // deserializedVector4 = XmlSerializer.deserialize(vector4filename,
        // Vector4.class);
        // }
        // catch (Exception ex) {
        // System.out.println("Exception caught: ");
        // System.out.println(ex.getMessage());
        // System.out.println(ex.getStackTrace());
        // System.out.println(ex.getCause());
        // }
        //
        // System.out.println(" Done.");
        //
        // System.out.println("Original vector4 equals to deserialized vector4: "
        // + vector4.equals(deserializedVector4));
        // System.out.println("Original vector4: " + vector4);
        // System.out.println("Deserialized vector4: " + deserializedVector4);
        //
        // System.out.println();
        // System.out.println();

    }
}
