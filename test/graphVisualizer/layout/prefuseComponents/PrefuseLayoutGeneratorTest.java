package graphVisualizer.layout.prefuseComponents;


import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.graphdrawing.graphml.GraphMLDocument;
import org.jutility.io.ConversionException;
import org.jutility.io.SerializationException;
import org.jutility.io.xml.XmlSerializer;

import prefuse.data.expression.Predicate;
import prefuse.data.expression.parser.ExpressionParser;
import graphVisualizer.conversion.graphML.GraphMLConverter;
import graphVisualizer.conversion.prefuse.PrefuseFormatConverter;
import graphVisualizer.graph.Universe;
import graphVisualizer.graph.common.IEdge;
import graphVisualizer.graph.common.IGraphObject;
import graphVisualizer.graph.common.INode;


/**
 * @author peter
 *
 */
public class PrefuseLayoutGeneratorTest
        extends PrefuseLayoutGenerator {



    /**
     * @param args
     *            not used
     */
    public static void main(String[] args) {

        Universe universe = null;


        System.out.print("Deserializing Graph.");
        GraphMLDocument doc = null;
        try {
            doc = XmlSerializer.Instance()
                    .deserialize(new File("/Users/peter/Desktop/socialnet.xml"),
                            GraphMLDocument.class);
        }
        catch (SerializationException e) {
            e.printStackTrace();
        }
        System.out.println(" Done.");


        System.out.print("Converting Graph into internal format.");
        try {
            universe = GraphMLConverter.Instance().convert(doc, Universe.class);
        }
        catch (ConversionException e) {
            e.printStackTrace();
        }
        System.out.println(" Done.");


        System.out.print("Converting Graph into prefuse format.");
        prefuse.data.Graph prefuseGraph = null;
        try {
            prefuseGraph = PrefuseFormatConverter.Instance().convert(universe,
                    prefuse.data.Graph.class);
        }
        catch (ConversionException e) {
            e.printStackTrace();
        }

        System.out.println(" Done.");


        PrefuseLayoutGenerator generator = PrefuseLayoutGenerator.Instance();

        List<IGraphObject> tester = new LinkedList<>();

        int n = 0;
        for (INode node : universe.getNodes()) {
            if (node.getID().toLowerCase().contains("power")) {
                tester.add(node);
                n++;
            }
        }

        int e = 0;
        for (IEdge edge : universe.getEdges()) {
            System.out.println(edge.getID());
            if (Integer.parseInt(edge.getID()) > 700) {
                tester.add(edge);
                e++;
            }
        }


        System.out.println("Tester contains " + n + " nodes and " + e
                + " edges.");
        String expression = generator.createRestrictionExpression(tester);

        // System.out.println("Expression: " + expression);
        // expression = "[graphViz:type] == 'graphViz:node'";
        // expression = "DEGREE() > 0";
        // expression = "[graphViz:ID] == 'automation:PowerModulePLC'";
        System.out.println("Expression: " + expression);
        Predicate filter = ExpressionParser.predicate(expression);


        Iterator<?> tuples = prefuseGraph.getNodeTable().tuples(filter);

        int i = 0;
        while (tuples.hasNext()) {
            i++;
            Object tuple = tuples.next();
            System.out.println(tuple.toString());
        }
        System.out.println(i + " nodes");

        tuples = prefuseGraph.getEdgeTable().tuples(filter);
        i = 0;
        while (tuples.hasNext()) {
            i++;
            Object tuple = tuples.next();
            tuple.toString();
        }

        System.out.println(i + " edges");

    }
}
