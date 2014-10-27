package edu.vt.arc.vis.osnap;



import org.jutility.common.datatype.map.KeyValuePair;
import org.jutility.io.ConversionException;
import org.jutility.io.SerializationException;
import org.jutility.io.xml.XmlSerializer;

import x3d.model.X3DDocument;
import edu.vt.arc.vis.osnap.conversion.owl.OWLConverter;
import edu.vt.arc.vis.osnap.conversion.owl.OWLSerializer;
import edu.vt.arc.vis.osnap.conversion.x3d.X3DEngine;
import edu.vt.arc.vis.osnap.graph.Universe;
import edu.vt.arc.vis.osnap.graph.common.INode;
import edu.vt.arc.vis.osnap.layout.Layout;
import edu.vt.arc.vis.osnap.layout.common.ILayoutComponent;
import edu.vt.arc.vis.osnap.layout.complexComponents.SolarSystemCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.visualization.Visualization;

import com.hp.hpl.jena.ontology.OntModel;


/**
 * The main class of the application.
 */
public class GraphVisualizer {


    /**
     * Main method launching the application.
     * 
     * @param args
     *            unused
     */
    public static void main(String[] args) {

        Universe universe = null;


        System.out.print("Deserializing Ontology.");
        OntModel ontology = null;
        try {
            ontology = OWLSerializer.Instance().deserialize(
            // "/Users/peter/Desktop/ontologies/pizza.owl"
            // "http://raziel.cs.vt.edu/ontologies/wzlMachineTools.owl"
                    "/Users/peter/Desktop/ontologies/wzlMachineTools.owl"
                    // "http://www.biopax.org/release/biopax-level3.owl"
                    // "/Users/peter/Desktop/ontologies/test.owl"
                    , OntModel.class);
        }
        catch (SerializationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        System.out.println(" Done.");

        // GraphMLDocument graphML = null;
        // System.out.print("Deserializing Graph.");
        // try {
        // graphML = GraphMLSerializer.Instance().deserialize(
        // // "/Users/peter/Desktop/ontologies/pizza.owl"
        // // "http://raziel.cs.vt.edu/ontologies/wzlMachineTools.owl"
        // // "/Users/peter/Desktop/ontologies/wzlMachineTools.owl"
        // // "http://www.biopax.org/release/biopax-level3.owl"
        // "/Users/peter/Desktop/WZLOntology-graphml.xml"
        // );
        //
        // }
        // catch (SerializationException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

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

        System.out.println(" Done.");

        // System.out.print("Converting GraphML Documnt into internal format.");
        //
        // try {
        //
        // universe = GraphMLConverter.Instance().convert(graphML);
        // }
        // catch (ConversionException ex) {
        //
        // System.out.println(ex.getMessage());
        // System.exit(-1);
        // }
        //
        // System.out.println(" Done.");

        // System.out.println(ontology);
        // System.exit(0);


        // System.out.print("Deserializing Graph.");
        // GraphMLDocument doc = null;
        // try {
        // doc = GraphMLSerializer.Instance().deserialize(
        // // "/Users/peter/Desktop/socialnet.xml"
        // "C:\\Users\\spn2460\\Dropbox\\WZLOntology1-graphml.xml");
        // }
        // catch (SerializationException e) {
        // e.printStackTrace();
        // }
        // System.out.println(" Done.");
        //
        // System.out.print("Converting Graph into internal format.");
        // try {
        // universe = GraphMLConverter.Instance().convert(doc);
        // }
        // catch (ConversionException e) {
        // e.printStackTrace();
        // }
        // System.out.println(" Done.");


        //
        // System.out.println("Graph ids:");
        // for (IGraph grph : universe.getGraphs()) {
        // System.out.println(grph.getID());
        // for (INode nd : grph.getNodes()) {
        // System.out.println(nd);
        // }
        // for (IEdge eg : grph.getEdges()) {
        // System.out.println(eg);
        // }
        // }


        // System.out.println("Graph Schema: ");
        // System.out.println(universe.getGraphSchema());
        // System.out.println("\n\nNode Schema: ");
        // System.out.println(universe.getNodeSchema());
        // System.out.println("\n\nEdge Schema: ");
        // System.out.println(universe.getEdgeSchema());
        //
        //
        // System.out.println("\n\nGraphs:");
        //
        // if (universe != null) {
        // for (IGraph graph : universe.getGraphs()) {
        // System.out.println("GRAPH\t" + graph.getID());
        // if (graph instanceof edu.vt.arc.vis.osnap.graph.Graph) {
        // edu.vt.arc.vis.osnap.graph.Graph realGraph = (edu.vt.arc.vis.osnap.graph.Graph)
        // graph;
        //
        // List<Metadata> prefixes = realGraph.getMetadataProperty()
        // .getMetadata("owl:prefix");
        //
        // if (prefixes != null) {
        // for (Metadata metadata : prefixes) {
        // System.out.println("\t" + metadata.getValue());
        // }
        // }
        // }
        // }
        // System.out.println("Nodes:\n");
        // for (INode node : universe.getNodes()) {
        //
        // System.out.println("\t" + node.getID());
        // if (node instanceof Node) {
        // Node realNode = (Node) node;
        //
        // List<Metadata> prefixes = realNode.getMetadataProperty()
        // .getMetadata("owl:type");
        // System.out.println("\t\towl:type:");
        // if (prefixes != null) {
        // for (Metadata metadata : prefixes) {
        // System.out.println("\t\t\t" + metadata.getValue());
        // }
        // }
        // else {
        // System.out.println("\t\t\tMissing!!");
        // }
        // }
        // }
        // System.out.println("Edges:\n");
        // for (IEdge edge : universe.getEdges()) {
        //
        // System.out.println("\t" + edge);
        // if (edge instanceof Edge) {
        // Edge realEdge = (Edge) edge;
        //
        // List<Metadata> prefixes = realEdge.getMetadataProperty()
        // .getMetadata("owl:type");
        // System.out.println("\t\towl:type:");
        // if (prefixes != null) {
        // for (Metadata metadata : prefixes) {
        // System.out.println("\t\t\t" + metadata.getValue());
        // }
        // }
        // else {
        // System.out.println("\t\t\tMissing!!");
        // }
        // }
        // }
        // }

        System.out.println("Number of Nodes total: "
                + universe.getNodes().size());
        System.out.println("Number of Edges total: "
                + universe.getEdges().size());

        System.out.println("======================================\n\nLayout:");

        Layout layout = Layout.defaultLayout(universe);


        INode rootNode = universe.getNode("owl:Thing");
        if (rootNode == null) {

            System.out.println("Substituting owl:Thing with "
                    + universe.getNodes().iterator().next());
            rootNode = universe.getNodes().iterator().next();
        }


        // StringMetadata restriction = new StringMetadata("owl:type",
        // "rdfs:subClassOf");
        //
        // List<IEdge> filteredEdges = new LinkedList<>();
        // for (IEdge edge : universe.getEdges()) {
        //
        // if (edge instanceof Edge) {
        //
        // Edge realEdge = (Edge) edge;
        //
        // List<Metadata> metadataValues = realEdge.getMetadataProperty()
        // .getMetadata(restriction.getKey());
        //
        // if (metadataValues != null && metadataValues.contains(restriction)) {
        // System.out.println("Edge " + edge.getID()
        // + " contains restriction.");
        // filteredEdges.add(edge);
        // }
        // else {
        // System.out.println("Edge " + edge.getID()
        // + " does not contain restriction.");
        // }
        // }
        // }
        //
        // System.out.println("Filtered Edges size: " + filteredEdges.size());

        ILayoutComponent provider = null;
        provider = new SolarSystemCoordinateLayoutComponent(rootNode, null,
                0.1f, 1.0f, 250f, true);


        // provider = new BalloonTreeCoordinateLayoutComponent(rootNode);
        // provider = new NodeLinkTreeCoordinateLayoutComponent(rootNode);
        // provider = new RadialTreeCoordinateLayoutComponent(rootNode);
        // provider = new SquarifiedTreeMapCoordinateLayoutComponent(rootNode);
        // provider = new ForceDirectedCoordinateLayoutComponent();
        // provider = new FruchtermanReingoldCoordinateLayoutComponent();


        // provider.setXOutput(CoordinateComponent.FIRST_COMPONENT);
        // provider.setYOutput(CoordinateComponent.THIRD_COMPONENT);
        // provider.setZOutput(CoordinateComponent.SECOND_COMPONENT);

        // SchemaEntry domainKey =
        // universe.getNodeSchema().getEntry("rdfs:label");
        //
        //
        // MappedShapeLayoutComponent<SchemaEntry, String> map = new
        // MappedShapeLayoutComponent<SchemaEntry, String>(
        // domainKey, VisualProperty.NODE_SHAPE, String.class);
        //
        //
        // for (INode inode : universe.getNodes()) {
        // if (inode instanceof Node) {
        // Node node = (Node) inode;
        //
        // List<Metadata> metadata = node.getMetadataProperty()
        // .getMetadata("rdfs:label");
        //
        // if (metadata != null) {
        // for (Metadata metadataValue : metadata) {
        //
        // Object value = metadataValue.getValue();
        // if (value instanceof String) {
        //
        // String string = (String) value;
        //
        // if (string.contains("SIMODRIVE")) {
        // map.addValueMapping(new OneToOneValueMapping<String, Shape>(
        // string, Shape.CUBE));
        //
        // }
        //
        // }
        // }
        // }
        // }
        // }
        //
        // layout.addLayoutProviderForVisualProperty(map,
        // VisualProperty.NODE_SHAPE);

        ILayoutComponent defaultCoordinates = null;
        for (KeyValuePair<ILayoutComponent, VisualProperty> pair : layout
                .getLayoutComponents()) {

            switch (pair.getValue()) {

                case NODE_X_POSITION:
                case NODE_Y_POSITION:
                case NODE_Z_POSITION:
                    defaultCoordinates = pair.getKey();
                    break;
                default:
                    break;

            }
        }

        layout.removeLayoutProviderForVisualProperty(defaultCoordinates,
                VisualProperty.NODE_X_POSITION);
        layout.removeLayoutProviderForVisualProperty(defaultCoordinates,
                VisualProperty.NODE_Y_POSITION);
        layout.removeLayoutProviderForVisualProperty(defaultCoordinates,
                VisualProperty.NODE_Z_POSITION);

        layout.addLayoutProviderForVisualProperty(provider,
                VisualProperty.NODE_X_POSITION);
        layout.addLayoutProviderForVisualProperty(provider,
                VisualProperty.NODE_Y_POSITION);
        layout.addLayoutProviderForVisualProperty(provider,
                VisualProperty.NODE_Z_POSITION);

        System.out.println("LayoutComponents: ");
        for (KeyValuePair<ILayoutComponent, VisualProperty> component : layout
                .getLayoutComponents()) {
            System.out.println("\t" + component.getValue() + ": "
                    + component.getKey().getName());
        }

        System.out.print("\nApplying Layout to Visualization.");

        layout.layout();

        System.out.println(" Done.");


        System.out
                .println("======================================\n\nX3D Engine:");



        Visualization visualization = layout.getVisualization();

        X3DDocument x3dDocument = null;
        try {
            x3dDocument = X3DEngine.Instance().convert(visualization,
                    X3DDocument.class);
        }
        catch (ConversionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        System.out.print("Writing X3D Document to file.");
        try {
            XmlSerializer.Instance().serialize(x3dDocument,
                    "/Users/peter/ontologySolarSystemLayoutTest.x3d"
            // "C:\\Users\\spn2460\\Desktop\\X3D layouts\\WZLOntology1-graphml.x3d"
                    );
        }
        catch (SerializationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(" Done.");


        System.exit(0);
    }
}
