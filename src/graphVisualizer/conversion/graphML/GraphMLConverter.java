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


package graphVisualizer.conversion.graphML;


import graphVisualizer.graph.HyperEdge;
import graphVisualizer.graph.Universe;
import graphVisualizer.graph.metadata.BooleanMetadata;
import graphVisualizer.graph.metadata.DoubleMetadata;
import graphVisualizer.graph.metadata.FloatMetadata;
import graphVisualizer.graph.metadata.IMetadataContainer;
import graphVisualizer.graph.metadata.IntegerMetadata;
import graphVisualizer.graph.metadata.LongMetadata;
import graphVisualizer.graph.metadata.Metadata;
import graphVisualizer.graph.metadata.MetadataMapProperty;
import graphVisualizer.graph.metadata.MetadataType;
import graphVisualizer.graph.metadata.Schema;
import graphVisualizer.graph.metadata.SchemaEntry;
import graphVisualizer.graph.metadata.StringMetadata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.graphdrawing.graphml.Edge;
import org.graphdrawing.graphml.Endpoint;
import org.graphdrawing.graphml.Graph;
import org.graphdrawing.graphml.GraphMLDocument;
import org.graphdrawing.graphml.Hyperedge;
import org.graphdrawing.graphml.IKeyDataContainer;
import org.graphdrawing.graphml.Key;
import org.graphdrawing.graphml.KeyData;
import org.graphdrawing.graphml.Locator;
import org.graphdrawing.graphml.Node;
import org.graphdrawing.graphml.Port;
import org.jutility.io.ConversionException;
import org.jutility.io.IConverter;


/**
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public final class GraphMLConverter
        implements IConverter {


    private static GraphMLConverter s_Instance;


    /**
     * Returns the singleton instance of the {@link GraphMLConverter} class;
     * 
     * @return the singleton instance.
     */
    public static GraphMLConverter Instance() {

        if (GraphMLConverter.s_Instance == null) {

            GraphMLConverter.s_Instance = new GraphMLConverter();
        }

        return GraphMLConverter.s_Instance;
    }



    private final Schema universeSchema;
    private final Schema graphSchema;
    private final Schema nodeSchema;
    private final Schema edgeSchema;
    private final Schema hyperEdgeSchema;



    private GraphMLConverter() {

        universeSchema = new Schema("UniverseSchema");
        graphSchema = new Schema("GraphSchema");
        nodeSchema = new Schema("NodeSchema");
        edgeSchema = new Schema("EdgeSchema");
        hyperEdgeSchema = new Schema("HyperedgeSchema");


        this.setUpMappings();
    }


    @Override
    public boolean supportsConversion(Class<?> sourceType, Class<?> targetType) {

        // lower bound required for source type.
        if (!GraphMLDocument.class.isAssignableFrom(sourceType)) {

            return false;
        }

        // upper bound required for target type.
        if (!targetType.isAssignableFrom(Universe.class)) {

            return false;
        }

        return true;
    }

    @Override
    public <T, S> S convert(T documentToConvert, Class<? extends S> returnType)
            throws ConversionException {

        Class<?> documentType = documentToConvert.getClass();
        if (!this.supportsConversion(documentType, returnType)) {

            throw new ConversionException("Conversion from " + documentType
                    + " to " + returnType + " is not supported by "
                    + this.getClass().toString() + "!");
        }

        return returnType.cast(this.convert(GraphMLDocument.class
                .cast(documentToConvert)));
    }

    /**
     * Converts a graphML document into the internal graph format.
     * 
     * This method creates a universe with the mapping metadata, then parses the
     * contents of the GraphMLDocument.
     * 
     * @param graphMLDocument
     *            The graphML document to be converted.
     * @return A graph in the internal representation.
     * 
     * @throws ConversionException
     *             if the parsing of the {@link GraphMLDocument} encounters an
     *             error.
     */
    private final Universe convert(GraphMLDocument graphMLDocument)
            throws ConversionException {

        // Creating a new Universe as the equivalent of a graphMLDocument
        Universe universe = new Universe(universeSchema, graphSchema,
                nodeSchema, edgeSchema, hyperEdgeSchema);


        this.parseGraphMLDocument(graphMLDocument, universe);


        return universe;
    }



    /**
     * Set-up of GraphML specific Schema elements for mapping purposes.
     * 
     * graphML:desc for descriptions (all) graphML:defaultEdgeType for default
     * edge type (graph) graphML:edgeIDType for edge id type (graph)
     * graphML:nodeIDType for node id type (graph) graphML:graphParseOrder for
     * parseOrder (graph) graphML:locatorHref for Locator (graph and node)
     * graphML:locatorType for Locator (graph and node) graphML:sourcePort for
     * source port (edge) graphML:targetPort for target port (edge)
     * graphML:graphParentNode for graph'scale parent (graph)
     * 
     */
    private void setUpMappings() {

        // graphML:desc
        universeSchema.createEntry("graphML:desc", MetadataType.STRING);
        graphSchema.createEntry("graphML:desc", MetadataType.STRING);
        nodeSchema.createEntry("graphML:desc", MetadataType.STRING);
        edgeSchema.createEntry("graphML:desc", MetadataType.STRING);
        hyperEdgeSchema.createEntry("graphML:desc", MetadataType.STRING);


        // graphML:defaultEdgeType
        graphSchema.createEntry("graphML:defaultEdgeType", MetadataType.STRING,
                null, false, true);


        // graphML:edgeIDType
        graphSchema.createEntry("graphML:edgeIDType", MetadataType.STRING,
                null, false, true);


        // graphML:nodeIDType">
        graphSchema.createEntry("graphML:nodeIDType", MetadataType.STRING,
                null, false, true);


        // graphML:graphParseOrder
        graphSchema.createEntry("graphML:graphParseOrder", MetadataType.STRING);


        // graphML:locatorHref
        graphSchema.createEntry("graphML:locatorHref", MetadataType.STRING,
                null, false, true);
        nodeSchema.createEntry("graphML:locatorHref", MetadataType.STRING,
                null, false, true);


        // graphML:locatorType
        graphSchema.createEntry("graphML:locatorType", MetadataType.STRING,
                null, false, true);
        nodeSchema.createEntry("graphML:locatorType", MetadataType.STRING,
                null, false, true);


        // graphML:sourcePort
        edgeSchema.createEntry("graphML:sourcePort", MetadataType.STRING, null,
                false, true);


        // graphML:targetPort
        edgeSchema.createEntry("graphML:targetPort", MetadataType.STRING, null,
                false, true);


        // graphML:graphParentNode
        graphSchema.createEntry("graphML:graphParentNode", MetadataType.STRING,
                null, false, true);


    }


    /**
     * Parses a Key element into a new SchemaEntry element and adds it to the
     * provided schemas.
     * <p>
     * The Key element'scale components are stored as follows:<br>
     * - key'scale name in qualifiedKeyName <br>
     * - attributeName in qualifiedKeyName.attributeName<br>
     * - attributeType in qualifiedKeyName.attributeType <br>
     * - description in qualifiedKeyName.desc<br>
     * - isDynamic in qualifiedKeyName.isDynamic
     * 
     * @param key
     *            The Key element to be parsed.
     * @param prefix
     *            The prefix for the Metadata name.
     * @param schemas
     *            The schemas to which the new Metadata element is to be added.
     * @throws ConversionException
     */
    private void createSchemaEntryFromKey(Key key, String prefix,
            List<Schema> schemas)
            throws ConversionException {

        Metadata schemaElement = null;

        StringMetadata attributeName = null;
        StringMetadata attributeType = null;
        StringMetadata description = null;
        BooleanMetadata isDynamic = null;


        String qualifiedSchemaEntryName = prefix + key.getId();

        String attributeNameKey = qualifiedSchemaEntryName + ".attributeName";
        String attributeTypeKey = qualifiedSchemaEntryName + ".attributeType";
        String descriptionKey = qualifiedSchemaEntryName + ".desc";
        String isDynamicKey = qualifiedSchemaEntryName + ".isDynamic";

        if (key.getAttributeType() != null) {

            // Parsing metadata type and default values
            switch (key.getAttributeType()) {

                case BOOLEAN: {

                    Boolean defaultValue = null;

                    if (key.getDefault() != null) {
                        defaultValue = Boolean.parseBoolean(key.getDefault()
                                .getContent());
                    }

                    schemaElement = new BooleanMetadata(
                            qualifiedSchemaEntryName, defaultValue);

                    attributeType = new StringMetadata(attributeTypeKey,
                            "Boolean");
                    break;
                }
                case DOUBLE: {
                    Double defaultValue = null;
                    if (key.getDefault() != null) {
                        defaultValue = Double.parseDouble(key.getDefault()
                                .getContent());
                    }

                    schemaElement = new DoubleMetadata(
                            qualifiedSchemaEntryName, defaultValue);
                    attributeType = new StringMetadata(attributeTypeKey,
                            "Double");

                    break;
                }
                case FLOAT: {
                    Float defaultValue = null;
                    if (key.getDefault() != null) {
                        defaultValue = Float.parseFloat(key.getDefault()
                                .getContent());
                    }

                    schemaElement = new FloatMetadata(qualifiedSchemaEntryName,
                            defaultValue);
                    attributeType = new StringMetadata(attributeTypeKey,
                            "Float");

                    break;
                }
                case INT: {
                    Integer defaultValue = null;
                    if (key.getDefault() != null) {
                        defaultValue = Integer.parseInt(key.getDefault()
                                .getContent());
                    }

                    schemaElement = new IntegerMetadata(
                            qualifiedSchemaEntryName, defaultValue);
                    attributeType = new StringMetadata(attributeTypeKey,
                            "Integer");

                    break;
                }
                case LONG: {
                    Long defaultValue = null;
                    if (key.getDefault() != null) {
                        defaultValue = Long.parseLong(key.getDefault()
                                .getContent());
                    }

                    schemaElement = new LongMetadata(qualifiedSchemaEntryName,
                            defaultValue);
                    attributeType = new StringMetadata(attributeTypeKey, "Long");

                    break;
                }
                case STRING: {
                    String defaultValue = null;
                    if (key.getDefault() != null) {
                        defaultValue = key.getDefault().getContent();
                    }

                    schemaElement = new StringMetadata(
                            qualifiedSchemaEntryName, defaultValue);
                    attributeType = new StringMetadata(attributeTypeKey,
                            "String");

                    break;
                }
                default:
                    throw new ConversionException(
                            "Did not recognize Key type: "
                                    + key.getAttributeType());
            }
        }
        else {
            String defaultValue = null;
            if (key.getDefault() != null) {
                defaultValue = key.getDefault().getContent();
            }

            schemaElement = new StringMetadata(qualifiedSchemaEntryName,
                    defaultValue);
        }


        if (key.getAttributeName() != null) {
            attributeName = new StringMetadata(attributeNameKey,
                    key.getAttributeName());
        }

        if (key.getDescription() != null) {
            description = new StringMetadata(descriptionKey,
                    key.getDescription());
        }

        if (key.isDynamic()) {
            isDynamic = new BooleanMetadata(isDynamicKey, Boolean.TRUE);
        }


        if (schemaElement != null) {
            for (Schema schema : schemas) {

                schema.createEntry(schemaElement.getKey(),
                        schemaElement.getMetadataType(), schemaElement, false,
                        false);


                if (attributeName != null) {
                    schema.createEntry(attributeName.getKey(),
                            attributeName.getMetadataType(), attributeName,
                            false, true);
                }

                if (attributeType != null) {
                    schema.createEntry(attributeType.getKey(),
                            attributeType.getMetadataType(), attributeType,
                            false, true);
                }

                if (description != null) {
                    schema.createEntry(description.getKey(),
                            description.getMetadataType(), description, false,
                            true);
                }

                if (isDynamic != null) {
                    schema.createEntry(isDynamic.getKey(),
                            isDynamic.getMetadataType(), isDynamic, false, true);
                }
            }
        }

    }


    /**
     * Creates Schema entries from Keys.
     * 
     * This method creates Schema entries from Keys, adding them to the
     * appropriate schemas.
     * 
     * The method also maps the following entry: - keyForType to
     * keyID.keyForType, graphML:port.keyID.keyForType, or
     * graphML:endpoint.keyID.keyForType respectively.
     * 
     * @param graphMLDocument
     *            The GraphMLDocument containing the keys.
     * @param universe
     *            The universe containing the Schemas
     * @throws ConversionException
     */
    private void createSchemasFromKeys(GraphMLDocument graphMLDocument,
            Universe universe)
            throws ConversionException {

        // System.out.println("\nCreating Schemas from Keys.");
        List<Key> keys = graphMLDocument.getKeys();

        // List containing all schemas
        List<Schema> forAll = new ArrayList<>(5);
        forAll.add(universe.getUniverseSchema());
        forAll.add(universe.getGraphSchema());
        forAll.add(universe.getNodeSchema());
        forAll.add(universe.getEdgeSchema());
        forAll.add(universe.getHyperEdgeSchema());

        List<Schema> forUniverse = new ArrayList<>(1);
        forUniverse.add(universe.getUniverseSchema());

        List<Schema> forGraph = new ArrayList<>(1);
        forGraph.add(universe.getGraphSchema());

        List<Schema> forNode = new ArrayList<>(1);
        forNode.add(universe.getNodeSchema());

        List<Schema> forEdge = new ArrayList<>(1);
        forEdge.add(universe.getEdgeSchema());

        List<Schema> forHyperEdge = new ArrayList<>(1);
        forHyperEdge.add(universe.getHyperEdgeSchema());


        for (Key key : keys) {

            if (key.getKeyForType() != null) {

                StringMetadata keyForType = null;
                String keyForTypeKey = key.getId() + ".keyForType";
                // Adding metadata to appropriate schemas
                switch (key.getKeyForType()) {
                    case ALL:
                        // System.out.println("Adding key \"" + key.getId() +
                        // "\" to the universe, graphs, nodes, edges, and "
                        // + "hyperedges.");

                        this.createSchemaEntryFromKey(key, "", forAll);

                        keyForType = new StringMetadata(keyForTypeKey, "All");
                        universe.getUniverseSchema().createEntry(
                                keyForType.getKey(),
                                keyForType.getMetadataType(), keyForType,
                                false, false);
                        universe.getGraphSchema().createEntry(
                                keyForType.getKey(),
                                keyForType.getMetadataType(), keyForType,
                                false, false);
                        universe.getNodeSchema().createEntry(
                                keyForType.getKey(),
                                keyForType.getMetadataType(), keyForType,
                                false, false);
                        universe.getEdgeSchema().createEntry(
                                keyForType.getKey(),
                                keyForType.getMetadataType(), keyForType,
                                false, false);
                        universe.getHyperEdgeSchema().createEntry(
                                keyForType.getKey(),
                                keyForType.getMetadataType(), keyForType,
                                false, false);


                        break;

                    case EDGE:
                        // System.out.println("Adding key \"" + key.getId() +
                        // "\" "
                        // + "to edges.");

                        this.createSchemaEntryFromKey(key, "", forEdge);

                        keyForType = new StringMetadata(keyForTypeKey, "Edge");
                        universe.getEdgeSchema().createEntry(
                                keyForType.getKey(),
                                keyForType.getMetadataType(), keyForType,
                                false, false);

                        break;


                    case HYPEREDGE:
                        // System.out.println("Adding key \"" + key.getId() +
                        // "\" "
                        // + "to hyperedges.");

                        this.createSchemaEntryFromKey(key, "", forHyperEdge);

                        keyForType = new StringMetadata(keyForTypeKey,
                                "Hyperedge");
                        universe.getHyperEdgeSchema().createEntry(
                                keyForType.getKey(),
                                keyForType.getMetadataType(), keyForType,
                                false, false);

                        break;

                    case NODE:
                        // System.out.println("Adding key \"" + key.getId() +
                        // "\" "
                        // + "to nodes.");

                        this.createSchemaEntryFromKey(key, "", forNode);

                        keyForType = new StringMetadata(keyForTypeKey, "Node");
                        universe.getNodeSchema().createEntry(
                                keyForType.getKey(),
                                keyForType.getMetadataType(), keyForType,
                                false, false);

                        break;

                    case GRAPH:
                        // System.out.println("Adding key \"" + key.getId() +
                        // "\" "
                        // + "to graphs.");

                        this.createSchemaEntryFromKey(key, "", forGraph);

                        keyForType = new StringMetadata(keyForTypeKey, "Graph");
                        universe.getGraphSchema().createEntry(
                                keyForType.getKey(),
                                keyForType.getMetadataType(), keyForType,
                                false, false);

                        break;

                    case GRAPHML:

                        // System.out.println("Adding key \"" + key.getId() +
                        // "\" "
                        // + "to the universe.");

                        this.createSchemaEntryFromKey(key, "", forUniverse);
                        keyForType = new StringMetadata(keyForTypeKey,
                                "GraphML");

                        universe.getUniverseSchema().createEntry(
                                keyForType.getKey(),
                                keyForType.getMetadataType(), keyForType,
                                false, false);

                        break;

                    case ENDPOINT:
                        // System.out.println("Adding key \"" + key.getId() +
                        // "\" "
                        // + "for endpoints to hyperedges.");

                        this.createSchemaEntryFromKey(key, "graphML:endpoint.",
                                forHyperEdge);

                        keyForTypeKey = "graphML:endpoint." + key.getId()
                                + ".keyForType";
                        keyForType = new StringMetadata(keyForTypeKey,
                                "Endpoint");
                        universe.getHyperEdgeSchema().createEntry(
                                keyForType.getKey(),
                                keyForType.getMetadataType(), keyForType,
                                false, false);

                        keyForTypeKey = key.getId() + ".keyForType";
                        break;

                    case PORT:
                        // System.out.println("Adding key \"" + key.getId() +
                        // "\" "
                        // + "for ports to nodes.");

                        this.createSchemaEntryFromKey(key, "graphML:port.",
                                forNode);

                        keyForTypeKey = "graphML:port." + key.getId()
                                + ".keyForType";

                        keyForType = new StringMetadata(keyForTypeKey, "Port");
                        universe.getNodeSchema().createEntry(
                                keyForType.getKey(),
                                keyForType.getMetadataType(), keyForType,
                                false, false);

                        keyForTypeKey = key.getId() + ".keyForType";
                        break;

                    default:
                        break;
                }

            }
            else {
                // System.out.println("Adding key \"" + key.getId() + "\" to "
                // + "the universe, graphs, nodes, edges, and hyperedges.");

                this.createSchemaEntryFromKey(key, "", forAll);
            }

            // Synchronizing the Universe'scale schema with its metadata
            // universe.synchronizeMetadataWithSchema();

        }


    }

    /**
     * Parses a graphMLDocument into a graph universe.
     * 
     * The method transforms the contents of a graphMLDocument: - Keys into
     * Schema elements - KeyData into Metadata - GraphMLGraphs into Graphs -
     * Description into a Metadata entry, graphML:desc
     * 
     * @param graphMLDocument
     *            The GraphMLDocument to parse.
     * @param universe
     *            The universe to parse into.
     * @throws ConversionException
     */
    private void parseGraphMLDocument(GraphMLDocument graphMLDocument,
            Universe universe)
            throws ConversionException {

        // System.out.println("\nParsing GraphMLDocument.");

        // Parsing keys into schemas
        createSchemasFromKeys(graphMLDocument, universe);


        // Parsing universe'scale graphs
        for (Graph graphMLGraph : graphMLDocument.getGraphs()) {

            // Creating an internal graph as the equivalent of the graphML graph
            // node

            String id;
            if (graphMLGraph.getId() != null) {
                id = graphMLGraph.getId();
            }
            else {
                Integer i = 0;
                while (universe.getEdgeMap().containsKey(i.toString())) {
                    i++;
                }
                id = i.toString();
            }


            graphVisualizer.graph.Graph graph = universe.createGraph(id);


            // Setting the parent node

            graph.getMetadataProperty().addMetadata("graphML:graphParentNode",
                    "Universe");

            // Metadata graphParentNode = graph.getMetadata().getElement(
            // "graphML:graphParentNode");
            //
            // graphParentNode.setValue("Universe");


            // parsing the graph
            this.parseGraphMLGraph(graphMLGraph, graph);

        }

        // Parsing the description into metadata
        if (graphMLDocument.getDescription() != null) {


            universe.getMetadataProperty().addMetadata("graphML:desc",
                    graphMLDocument.getDescription());

            // Metadata universeDescription = universe.getMetadata().getElement(
            // "graphML:desc");
            // universeDescription.setValue(graphMLDocument.getDescription());
        }

        // Parsing universe'scale metadata
        parseKeyData(graphMLDocument, universe, "");

    }


    /**
     * Parses the contents of a KeyData element into a Metadata element.
     * 
     * @param keyData
     *            The KeyData element.
     * @param metadata
     *            The Metadata element.
     */
    // private Metadata parseKeyDataContent(KeyData keyData, SchemaEntry entry)
    // {
    //
    // // System.out.println("parsing metadata content: " + metadata.getKey() +
    // // "=" + keyData.getContent());
    //
    //
    //
    // Metadata metadata = null;
    //
    //
    // }
    // return metadata;
    // }


    /**
     * Parses a KeyData element and converts it into a Metadata element.
     * 
     * The optional id and time of a KeyData element are stored in accompanying
     * metadata.
     * 
     * @param keyDataContainer
     *            The Element containing the KeyData elements.
     * @param metadataContainer
     *            The Element that is supposed to contain the Metadata elements.
     * @throws ConversionException
     */
    private void parseKeyData(IKeyDataContainer keyDataContainer,
            IMetadataContainer metadataContainer, String prefix)
            throws ConversionException {

        // System.out.println("Parsing metadata.");

        for (KeyData keyData : keyDataContainer.getKeyData()) {

            String metadataName = keyData.getKey();
            // System.out.println("Key: " + keyData.getKey());

            MetadataMapProperty container = metadataContainer
                    .getMetadataProperty();
            Schema schema = container.getSchema();

            String metadataContent = keyData.getContent();

            if (schema.containsKey(metadataName)) {

                SchemaEntry entry = schema.getEntry(metadataName);


                switch (entry.getType()) {
                    case BOOLEAN: {
                        Boolean content = Boolean.parseBoolean(metadataContent);
                        container.addMetadata(metadataName, content);
                        break;
                    }
                    case DOUBLE: {
                        Double content = Double.parseDouble(metadataContent);
                        container.addMetadata(metadataName, content);
                        break;
                    }
                    case FLOAT: {
                        Float content = Float.parseFloat(metadataContent);
                        container.addMetadata(metadataName, content);
                        break;
                    }
                    case INTEGER: {
                        Integer content = Integer.parseInt(metadataContent);
                        container.addMetadata(metadataName, content);
                        break;
                    }
                    case LONG: {
                        Long content = Long.parseLong(metadataContent);
                        container.addMetadata(metadataName, content);
                        break;
                    }
                    case STRING: {
                        container.addMetadata(metadataName, metadataContent);
                        break;
                    }
                    default: {
                        break;
                    }
                }

                if (keyData.getId() != null) {
                    StringMetadata metadataID = new StringMetadata(metadataName
                            + ".id");
                    metadataID.setValue(keyData.getId());
                    metadataContainer.getMetadataProperty().addMetadata(
                            metadataID);
                }
                if (keyData.getTime() != 0) {
                    LongMetadata metadataTime = new LongMetadata(metadataName
                            + ".time");
                    metadataTime.setValue(keyData.getTime());
                    metadataContainer.getMetadataProperty().addMetadata(
                            metadataTime);
                }


            }
            else {
                // Shouldn't be empty since all data elements need to be defined
                // by key elements

                throw new ConversionException(
                        "All Data elements need to be defined by Key elements!");
            }

        }

    }

    /**
     * Parses a GraphMLGraph into a Graph.
     * 
     * The method transforms the contents of a graphMLGraph: - Locator into two
     * metadata elements, graphML:locatorHref and graphML:locatorType -
     * Description into a Metadata entry: graphML:desc - DefaultEdgeType into a
     * Metadata entry: graphML:defaultEdgeType - EdgeIDType into a Metadata
     * entry: graphML:edgeIDType - NodeIDType into a Metadata entry:
     * graphML:nodeIDType - GraphParseOrder into a Metadata entry:
     * graphML:graphParseOrder - KeyData into Metadata - GraphMLNodes into Nodes
     * - GraphMLEdges into Edges - GraphMLHyperedges into HyperEdges
     * 
     * Please note that the following entries are discarded and have to be
     * re-created: - MasimumIncomingDegree - MaximumOutgoingDegree -
     * NumberOfEdges - NumberOfNodes
     * 
     * @param graphMLGraph
     *            The GraphMLGraph to be parsed.
     * @param graph
     *            The Graph to be parsed into.
     * @throws ConversionException
     */
    private void parseGraphMLGraph(Graph graphMLGraph,
            graphVisualizer.graph.Graph graph)
            throws ConversionException {



        // Locator
        if (graphMLGraph.getLocator() != null) {

            Locator locator = graphMLGraph.getLocator();

            graph.getMetadataProperty().addMetadata("graphML:locatorHref",
                    locator.getHref());

            if (locator.getType() != null) {

                graph.getMetadataProperty().addMetadata("graphML:locatorType",
                        locator.getType());
            }
        }


        // Description
        if (graphMLGraph.getDescription() != null) {

            graph.getMetadataProperty().addMetadata("graphML:desc",
                    graphMLGraph.getDescription());
        }


        // Default Edge Type
        if (graphMLGraph.getDefaultEdgeType() != null) {

            switch (graphMLGraph.getDefaultEdgeType()) {

                case DIRECTED:
                    graph.getMetadataProperty().addMetadata(
                            "graphML:defaultEdgeType", "DIRECTED");
                    break;
                case UNDIRECTED:
                    graph.getMetadataProperty().addMetadata(
                            "graphML:defaultEdgeType", "UNDIRECTED");
                    break;
            }
        }


        // Edge ID Type
        if (graphMLGraph.getEdgeIDType() != null) {

            switch (graphMLGraph.getEdgeIDType()) {

                case CANONICAL:
                    graph.getMetadataProperty().addMetadata(
                            "graphML:edgeIDType", "CANONICAL");
                    break;
                case FREE:
                    graph.getMetadataProperty().addMetadata(
                            "graphML:edgeIDType", "FREE");
                    break;
            }
        }


        // Node ID Type
        if (graphMLGraph.getNodeIDType() != null) {

            switch (graphMLGraph.getNodeIDType()) {

                case CANONICAL:
                    graph.getMetadataProperty().addMetadata(
                            "graphML:nodeIDType", "CANONICAL");
                    break;
                case FREE:
                    graph.getMetadataProperty().addMetadata(
                            "graphML:nodeIDType", "FREE");
                    break;
            }
        }


        // Parse Order
        if (graphMLGraph.getGraphParseOrder() != null) {
            switch (graphMLGraph.getGraphParseOrder()) {

                case ADJACENCYLIST:
                    graph.getMetadataProperty().addMetadata(
                            "graphML:graphParseOrder", "ADJACENCYLIST");
                    break;
                case FREE:
                    graph.getMetadataProperty().addMetadata(
                            "graphML:graphParseOrder", "FREE");
                    break;
                case NODESFIRST:
                    graph.getMetadataProperty().addMetadata(
                            "graphML:graphParseOrder", "NODESFIRST");
                    break;
            }
        }


        // Parsing graph'scale metadata
        this.parseKeyData(graphMLGraph, graph, "");


        // Parsing graph'scale nodes
        List<Node> nodes = graphMLGraph.getNodes();

        for (Node graphMLNode : nodes) {

            graphVisualizer.graph.Node node = graph.getUniverse().createNode(
                    graphMLNode.getId(), graph);

            parseGraphMLNode(graphMLNode, node);
        }


        // Parsing graph'scale edges
        List<Edge> edges = graphMLGraph.getEdges();

        for (Edge graphMLEdge : edges) {
            String id;
            if (graphMLEdge.getId() != null) {
                id = graphMLEdge.getId();
            }
            else {
                Integer i = 0;
                while (graph.getUniverse().getEdgeMap()
                        .containsKey(i.toString())) {
                    i++;
                }
                id = i.toString();
            }

            graphVisualizer.graph.Edge edge = graph.getUniverse().createEdge(
                    id, graph.getID(), graphMLEdge.getSource(),
                    graphMLEdge.getTarget());

            parseGraphMLEdge(graphMLEdge, edge);
        }


        // Parsing graph'scale hyperedges
        List<Hyperedge> hyperedges = graphMLGraph.getHyperedges();

        for (Hyperedge graphMLHyperEdge : hyperedges) {
            String id;
            if (graphMLHyperEdge.getId() != null) {
                id = graphMLHyperEdge.getId();
            }
            else {
                Integer i = 0;
                while (graph.getUniverse().getHyperEdgeMap()
                        .containsKey(i.toString())) {
                    i++;
                }
                id = i.toString();
            }


            Set<String> nodeIdentifiers = new HashSet<>();

            List<Endpoint> endpoints = graphMLHyperEdge.getEndpoints();
            for (Endpoint endpoint : endpoints) {
                nodeIdentifiers.add(endpoint.getNodeID());
            }


            HyperEdge hyperEdge = graph.getUniverse()
                    .createHyperEdgeFromIdentifiers(id, graph.getID(),
                            nodeIdentifiers);

            parseGraphMLHyperedge(graphMLHyperEdge, hyperEdge);
        }


    }


    /**
     * Parses a Port into its ancestor node'scale metadata.
     * 
     * Since ports don't have an equivalent in the internal graph representation
     * they have to be contained completely within metadata. Furthermore, ports
     * can be nested, hence why the parent'scale name has to be provided.
     * 
     * As the port is not technically part of the metadata, it'scale name is
     * prefixed with graphML:.
     * 
     * A ports components are stored as follows: - port'scale name in
     * graphML:portName - port'scale parent in graphML:portName.parent - port'scale
     * description in graphML:portName.desc - port'scale keyData in
     * graphML:portName.keyDataName
     * 
     * @param port
     *            The port to parse.
     * @param node
     *            The ancestor node that will contain the port metadata.
     * @param parentName
     *            The port'scale parent'scale name.
     * @throws ConversionException
     */
    private void parsePort(Port port, graphVisualizer.graph.Node node,
            String parentName)
            throws ConversionException {

        // System.out.println("Parsing Port.");

        String portName = port.getName();

        String qualifiedPortName = "graphML:" + portName;

        // Registering the port with the ancestor node
        StringMetadata portNameMetadata = new StringMetadata(qualifiedPortName,
                portName);
        node.getMetadataProperty().addMetadata(portNameMetadata);

        // Adding the port'scale parent to the ancestor node'scale metadata
        StringMetadata portParentMetadata = new StringMetadata(
                qualifiedPortName + ".parent", parentName);
        node.getMetadataProperty().addMetadata(portParentMetadata);


        // Adding the port'scale description to the ancestor node
        if (port.getDescription() != null) {
            StringMetadata portDescription = new StringMetadata(
                    qualifiedPortName + ".desc", port.getDescription());
            node.getMetadataProperty().addMetadata(portDescription);
        }


        // Parsing the port'scale metadata and adding it to the ancestor node
        this.parseKeyData(port, node, "graphML:port.");


        // Parsing all the sub-ports of this port
        for (Port subPort : port.getPorts()) {
            parsePort(subPort, node, port.getName());
        }
    }


    /**
     * Parses a GraphML Node into a Node.
     * 
     * The method transforms the contents of a graphML Node: - Locator into two
     * metadata elements, graphML:locatorHref and graphML:locatorType -
     * Description into a Metadata entry: graphML:desc - Ports into Metadata -
     * SubGraphs into the Universe (with an appropriate graphML:graphParent
     * entry) - KeyData into Metadata
     * 
     * Please note that the following entries are discarded and have to be
     * re-created: - IncomingDegree - OutgoingDegree
     * 
     * @param graphMLNode
     *            The GraphML Node to be parsed.
     * @param node
     *            The Node to be parsed into.
     * @throws ConversionException
     */
    private void parseGraphMLNode(Node graphMLNode,
            graphVisualizer.graph.Node node)
            throws ConversionException {



        // Locator
        if (graphMLNode.getLocator() != null) {
            // continue

            Locator locator = graphMLNode.getLocator();

            node.getMetadataProperty().addMetadata("graphML:locatorHref",
                    locator.getHref());

            if (locator.getType() != null) {

                node.getMetadataProperty().addMetadata("graphML:locatorType",
                        locator.getType());
            }

        }


        // Description
        if (graphMLNode.getDescription() != null) {

            node.getMetadataProperty().addMetadata("graphML:desc",
                    graphMLNode.getDescription());
        }


        // Ports
        for (Port port : graphMLNode.getPorts()) {

            parsePort(port, node, node.getID());
        }


        // Subgraph
        Graph subGraph = graphMLNode.getGraph();

        if (subGraph != null) {

            String id;

            if (subGraph.getId() != null) {
                id = subGraph.getId();
            }
            else {
                Integer i = 0;
                while (node.getGraph().getUniverse().containsID(i.toString())) {
                    i++;
                }
                id = i.toString();
            }

            graphVisualizer.graph.Graph graph = node.getGraph().getUniverse()
                    .createGraph(id);


            // Setting the parent node
            graph.getMetadataProperty().addMetadata("graphML:graphParentNode",
                    "node:" + node.getID());

            parseGraphMLGraph(subGraph, graph);

        }


        // Metadata
        parseKeyData(graphMLNode, node, "");
    }


    /**
     * Parses a GraphML Edge into an Edge.
     * 
     * The method transforms the contents of a graphML Edge: - Description into
     * a Metadata entry: graphML:desc - SubGraphs into the Universe (with an
     * appropriate graphML:graphParent entry) - KeyData into Metadata -
     * SourcePort into a Metadata entry, graphML:sourcePort - TargetPort into a
     * Metadata entry, graphML:targetPort
     * 
     * @param graphMLEdge
     *            The GraphML Edge to be parsed.
     * @param edge
     *            The Edge to be parsed into.
     * @throws ConversionException
     */
    private void parseGraphMLEdge(Edge graphMLEdge,
            graphVisualizer.graph.Edge edge)
            throws ConversionException {

        // System.out.println("Parsing Edge.");


        // Description
        if (graphMLEdge.getDescription() != null) {

            edge.getMetadataProperty().addMetadata("graphML:desc",
                    graphMLEdge.getDescription());
        }


        // Source Port
        if (graphMLEdge.getSourceport() != null) {

            edge.getMetadataProperty().addMetadata("graphML:sourcePort",
                    graphMLEdge.getSourceport());
        }


        // Target Port
        if (graphMLEdge.getTargetport() != null) {

            edge.getMetadataProperty().addMetadata("graphML:targetPort",
                    graphMLEdge.getTargetport());
        }


        // Subgraph
        Graph subGraph = graphMLEdge.getGraph();

        if (subGraph != null) {

            String id;

            if (subGraph.getId() != null) {
                id = subGraph.getId();
            }
            else {
                Integer i = 0;
                while (edge.getGraph().getUniverse().containsID(i.toString())) {
                    i++;
                }
                id = i.toString();
            }

            graphVisualizer.graph.Graph graph = edge.getGraph().getUniverse()
                    .createGraph(id);


            // Setting the parent node
            graph.getMetadataProperty().addMetadata("graphML:graphParentNode",
                    "edge:" + edge.getID());


            parseGraphMLGraph(subGraph, graph);

        }


        // Metadata
        parseKeyData(graphMLEdge, edge, "");

    }


    /**
     * Parses an Endpoint into its containing HyperEdge.
     * 
     * Since endpoints don't have an equivalent in the internal graph
     * representation they have to be contained completely within metadata.
     * 
     * As the port is not technically part of the metadata, it'scale name is
     * prefixed with graphML:.
     * 
     * An endpoint'scale components are stored as follows: - endpoint'scale id in
     * graphML:endpointID - endpoint'scale nodeID in graphML:endpointID.nodeID -
     * endpoint'scale description in graphML:endpointID.desc - endpoint'scale type in
     * graphML:endpointID.type - endpoint'scale portID in graphML:endpointID.portID
     * 
     * @param endpoint
     *            The Endpoint to parse.
     * @param hyperEdge
     *            The HyperEdge that will contain the endpoint metadata.
     */
    private void parseEndpoint(Endpoint endpoint, HyperEdge hyperEdge) {

        String qualifiedEndpointName;
        StringMetadata endpointNameMetadata;
        if (endpoint.getId() != null) {
            qualifiedEndpointName = "graphML:" + endpoint.getId();

            endpointNameMetadata = new StringMetadata(qualifiedEndpointName,
                    endpoint.getId());

        }
        else {
            qualifiedEndpointName = "graphML:" + hyperEdge.getID() + "-"
                    + endpoint.getNodeID();

            endpointNameMetadata = new StringMetadata(qualifiedEndpointName,
                    qualifiedEndpointName);
        }
        hyperEdge.getMetadataProperty().addMetadata(endpointNameMetadata);


        StringMetadata endpointNodeID = new StringMetadata(
                qualifiedEndpointName + ".nodeID", endpoint.getNodeID());
        hyperEdge.getMetadataProperty().addMetadata(endpointNodeID);

        if (endpoint.getType() != null) {
            StringMetadata endpointType = new StringMetadata(
                    qualifiedEndpointName + ".type");
            switch (endpoint.getType()) {
                case IN:
                    endpointType.setValue("IN");
                    break;
                case OUT:
                    endpointType.setValue("OUT");
                    break;
                case UNDIRECTED:
                    endpointType.setValue("UNDIRECTED");
                    break;
            }
            hyperEdge.getMetadataProperty().addMetadata(endpointType);
        }

        if (endpoint.getDescription() != null) {
            StringMetadata endpointDescription = new StringMetadata(
                    qualifiedEndpointName + ".desc", endpoint.getDescription());

            hyperEdge.getMetadataProperty().addMetadata(endpointDescription);
        }


        if (endpoint.getPortID() != null) {
            StringMetadata endpointPortID = new StringMetadata(
                    qualifiedEndpointName + ".portID", endpoint.getPortID());

            hyperEdge.getMetadataProperty().addMetadata(endpointPortID);
        }
    }


    /**
     * Parses a GraphML Hyperedge into a HyperEdge.
     * 
     * The method transforms the contents of a graphML Hyperedge: - Description
     * into a Metadata entry: graphML:desc - SubGraphs into the Universe (with
     * an appropriate graphML:graphParent entry) - KeyData into Metadata -
     * Endpoints into Metadata
     * 
     * @param graphMLHyperEdge
     *            The GraphML Hyperedge to parse.
     * @param hyperEdge
     *            The HyperEdge to be parsed into.
     * @throws ConversionException
     */
    private void parseGraphMLHyperedge(Hyperedge graphMLHyperEdge,
            HyperEdge hyperEdge)
            throws ConversionException {

        // Description
        if (graphMLHyperEdge.getDescription() != null) {

            hyperEdge.getMetadataProperty().addMetadata("graphML:desc",
                    graphMLHyperEdge.getDescription());
        }


        // Endpoints
        for (Endpoint endpoint : graphMLHyperEdge.getEndpoints()) {

            parseEndpoint(endpoint, hyperEdge);

        }


        // Subgraphs
        Graph subGraph = graphMLHyperEdge.getGraph();

        if (subGraph != null) {

            String id;

            if (subGraph.getId() != null) {
                id = subGraph.getId();
            }
            else {
                Integer i = 0;
                while (hyperEdge.getGraph().getUniverse()
                        .containsID(i.toString())) {
                    i++;
                }
                id = i.toString();
            }

            graphVisualizer.graph.Graph graph = hyperEdge.getGraph()
                    .getUniverse().createGraph(id);


            // Setting the parent node
            graph.getMetadataProperty().addMetadata("graphML:graphParentNode",
                    "hyperEdge:" + hyperEdge.getID());


            this.parseGraphMLGraph(subGraph, graph);

        }

        // Metadata
        this.parseKeyData(graphMLHyperEdge, hyperEdge, "");

    }


}
