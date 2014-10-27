/*******************************************************************************
 * Copyright 2014 Virginia Tech Visionarium
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/


package graphVisualizer.conversion.owl;


import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.jutility.io.ConversionException;
import org.jutility.io.IConverter;

import com.hp.hpl.jena.ontology.AllDifferent;
import com.hp.hpl.jena.ontology.AllValuesFromRestriction;
import com.hp.hpl.jena.ontology.BooleanClassDescription;
import com.hp.hpl.jena.ontology.CardinalityRestriction;
import com.hp.hpl.jena.ontology.DataRange;
import com.hp.hpl.jena.ontology.EnumeratedClass;
import com.hp.hpl.jena.ontology.HasValueRestriction;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.MaxCardinalityRestriction;
import com.hp.hpl.jena.ontology.MinCardinalityRestriction;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.ontology.Restriction;
import com.hp.hpl.jena.ontology.SomeValuesFromRestriction;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

import graphVisualizer.graph.Edge;
import graphVisualizer.graph.Graph;
import graphVisualizer.graph.Node;
import graphVisualizer.graph.Universe;
import graphVisualizer.graph.metadata.IMetadataContainer;
import graphVisualizer.graph.metadata.Metadata;
import graphVisualizer.graph.metadata.MetadataType;
import graphVisualizer.graph.metadata.Schema;
import graphVisualizer.graph.metadata.SchemaEntry;
import graphVisualizer.graph.metadata.StringMetadata;


/**
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
public class OWLConverter
        implements IConverter {


    private static OWLConverter s_Instance;

    private OntModel            ontologyModel;

    private Map<Resource, Node> anonymousResourceMap;

    private final Schema        universeSchema;
    private final Schema        graphSchema;
    private final Schema        nodeSchema;
    private final Schema        edgeSchema;
    private final Schema        hyperEdgeSchema;


    /**
     * Returns the Singleton instance of the OWLConverter class.
     * 
     * @return the Singleton instance.
     */
    public static OWLConverter Instance() {

        if (s_Instance == null) {

            s_Instance = new OWLConverter();
        }

        return s_Instance;
    }


    private OWLConverter() {

        this.universeSchema = new Schema("UniverseSchema");
        this.graphSchema = new Schema("GraphSchema");
        this.nodeSchema = new Schema("NodeSchema");
        this.edgeSchema = new Schema("EdgeSchema");
        this.hyperEdgeSchema = new Schema("HyperedgeSchema");

        this.anonymousResourceMap = new LinkedHashMap<>();

        this.initialize();
    }
    
    private void initialize() {

        this.universeSchema.clear();
        this.graphSchema.clear();
        this.nodeSchema.clear();
        this.edgeSchema.clear();
        this.hyperEdgeSchema.clear();
        
        this.anonymousResourceMap.clear();
        
        this.setUpMappings();
    }



    @Override
    public boolean supportsConversion(Class<?> sourceType, Class<?> targetType) {

        // lower bound required for source type.
        if (!OntModel.class.isAssignableFrom(sourceType)) {

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

        return returnType.cast(this.convert(OntModel.class
                .cast(documentToConvert)));
    }


    private Universe convert(OntModel inputDocument)
            throws ConversionException {

        this.initialize();
        
        Universe universe = new Universe(this.universeSchema, this.graphSchema,
                this.nodeSchema, this.edgeSchema, this.hyperEdgeSchema);

        this.ontologyModel = inputDocument;

        this.setUpBuiltIns(universe);
        this.parseOntology(universe);

        this.ontologyModel = null;

        return universe;
    }


    /**
     * Set-up of Ontology specific Schema elements for mapping purposes.
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


        // Specific mappings are done within metadata.

        // owl:type
        // Values out of:
        // owl:Class
        // owl:AnnotationProperty
        // owl:DatatypeProperty
        // owl:ObjectProperty
        // owl:OntologyProperty
        // owl:Individual
        // owl:Literal
        // owl:DatatypePropetyInstance
        // owl:ObjectPropertyInstance
        nodeSchema.createEntry("owl:type", MetadataType.STRING,
                new StringMetadata("owl:type", "owl:Class"), true, false);

        graphSchema.createEntry("owl:type", MetadataType.STRING,
                new StringMetadata("owl:type", "owl:Ontology"), true, true);

        edgeSchema.createEntry("owl:type", MetadataType.STRING, null, false,
                false);


        // Built-in Annotation Properties
        SchemaEntry versionInfo = graphSchema.createEntry("owl:versionInfo",
                MetadataType.STRING, null, false, false);
        nodeSchema.addEntry(versionInfo);
        edgeSchema.addEntry(versionInfo);

        SchemaEntry rdfsLabel = graphSchema.createEntry("rdfs:label",
                MetadataType.STRING, null, false, false);
        nodeSchema.addEntry(rdfsLabel);
        edgeSchema.addEntry(rdfsLabel);

        SchemaEntry rdfsComment = graphSchema.createEntry("rdfs:comment",
                MetadataType.STRING, null, false, false);
        nodeSchema.addEntry(rdfsComment);
        edgeSchema.addEntry(rdfsComment);

        SchemaEntry rdfsSeeAlso = graphSchema.createEntry("rdfs:seeAlso",
                MetadataType.STRING, null, false, false);
        nodeSchema.addEntry(rdfsSeeAlso);
        edgeSchema.addEntry(rdfsSeeAlso);

        SchemaEntry rdfsIsDefinedBy = graphSchema.createEntry(
                "rdfs:isDefinedBy", MetadataType.STRING, null, false, false);
        nodeSchema.addEntry(rdfsIsDefinedBy);
        edgeSchema.addEntry(rdfsIsDefinedBy);


        // Built-in Ontology Properties
        graphSchema.createEntry("owl:imports", MetadataType.STRING, null,
                false, false);
        graphSchema.createEntry("owl:priorVersion", MetadataType.STRING, null,
                false, false);
        graphSchema.createEntry("owl:backwardCompatibleWith",
                MetadataType.STRING, null, false, false);
        graphSchema.createEntry("owl:incompatibleWith", MetadataType.STRING,
                null, false, false);


        // The namespace prefix
        graphSchema.createEntry("owl:prefix", MetadataType.STRING, null, false,
                true);

        // The namespace
        nodeSchema.createEntry("owl:namespace", MetadataType.STRING, null,
                true, true);
    }

    private void setUpBuiltIns(Universe universe) {

        Graph owl = universe.createGraph("http://www.w3.org/2002/07/owl");
        owl.getMetadataProperty().addMetadata("owl:prefix", "owl:");

        Graph xsd = universe.createGraph("http://www.w3.org/2001/XMLSchema");
        xsd.getMetadataProperty().addMetadata("owl:prefix", "xsd:");

        Graph rdfs = universe
                .createGraph("http://www.w3.org/2000/01/rdf-schema");
        rdfs.getMetadataProperty().addMetadata("owl:prefix", "rdfs:");


        // Built-in Classes
        Node owlThing = universe.createNode("owl:Thing", owl);
        owlThing.getMetadataProperty().addMetadata("owl:prefix", "owl");
        owlThing.getMetadataProperty().addMetadata("owl:type", "owl:Class");

        Node owlNothing = universe.createNode("owl:Nothing", owl);
        owlNothing.getMetadataProperty().addMetadata("owl:prefix", "owl");
        owlNothing.getMetadataProperty().addMetadata("owl:type", "owl:Class");


        Node xsdString = universe.createNode("xsd:string", xsd);
        xsdString.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdString.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdString.getMetadataProperty()
                .addMetadata("owl:type", "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdString, universe);

        Node xsdBoolean = universe.createNode("xsd:boolean", xsd);
        xsdBoolean.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdBoolean.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdBoolean.getMetadataProperty().addMetadata("owl:type",
                "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdBoolean, universe);

        Node xsdDecimal = universe.createNode("xsd:decimal", xsd);
        xsdDecimal.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdDecimal.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdDecimal.getMetadataProperty().addMetadata("owl:type",
                "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdDecimal, universe);

        Node xsdFloat = universe.createNode("xsd:float", xsd);
        xsdFloat.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdFloat.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdFloat.getMetadataProperty().addMetadata("owl:type", "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdFloat, universe);

        Node xsdDouble = universe.createNode("xsd:double", xsd);
        xsdDouble.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdDouble.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdDouble.getMetadataProperty()
                .addMetadata("owl:type", "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdDouble, universe);

        Node xsdDateTime = universe.createNode("xsd:dateTime", xsd);
        xsdDateTime.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdDateTime.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdDateTime.getMetadataProperty().addMetadata("owl:type",
                "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdDateTime, universe);

        Node xsdTime = universe.createNode("xsd:time", xsd);
        xsdTime.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdTime.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdTime.getMetadataProperty().addMetadata("owl:type", "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdTime, universe);

        Node xsdDate = universe.createNode("xsd:date", xsd);
        xsdDate.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdDate.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdDate.getMetadataProperty().addMetadata("owl:type", "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdDate, universe);

        Node xsdGYearMonth = universe.createNode("xsd:gYearMonth", xsd);
        xsdGYearMonth.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdGYearMonth.getMetadataProperty()
                .addMetadata("owl:type", "owl:Class");
        xsdGYearMonth.getMetadataProperty().addMetadata("owl:type",
                "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdGYearMonth, universe);

        Node xsdGYear = universe.createNode("xsd:gYear", xsd);
        xsdGYear.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdGYear.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdGYear.getMetadataProperty().addMetadata("owl:type", "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdGYear, universe);

        Node xsdGMonthDay = universe.createNode("xsd:gMonthDay", xsd);
        xsdGMonthDay.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdGMonthDay.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdGMonthDay.getMetadataProperty().addMetadata("owl:type",
                "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdGMonthDay, universe);

        Node xsdGDay = universe.createNode("xsd:gDay", xsd);
        xsdGDay.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdGDay.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdGDay.getMetadataProperty().addMetadata("owl:type", "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdGDay, universe);

        Node xsdGMonth = universe.createNode("xsd:gMonth", xsd);
        xsdGMonth.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdGMonth.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdGMonth.getMetadataProperty()
                .addMetadata("owl:type", "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdGMonth, universe);

        Node xsdHexBinary = universe.createNode("xsd:hexBinary", xsd);
        xsdHexBinary.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdHexBinary.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdHexBinary.getMetadataProperty().addMetadata("owl:type",
                "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdHexBinary, universe);

        Node xsdBase64Binary = universe.createNode("xsd:base64Binary", xsd);
        xsdBase64Binary.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdBase64Binary.getMetadataProperty().addMetadata("owl:type",
                "owl:Class");
        xsdBase64Binary.getMetadataProperty().addMetadata("owl:type",
                "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdBase64Binary, universe);

        Node xsdAnyURI = universe.createNode("xsd:anyURI", xsd);
        xsdAnyURI.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdAnyURI.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdAnyURI.getMetadataProperty()
                .addMetadata("owl:type", "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdAnyURI, universe);

        Node xsdNormalizedString = universe.createNode("xsd:normalizedString",
                xsd);
        xsdNormalizedString.getMetadataProperty().addMetadata("owl:prefix",
                "xsd");
        xsdNormalizedString.getMetadataProperty().addMetadata("owl:type",
                "owl:Class");
        xsdNormalizedString.getMetadataProperty().addMetadata("owl:type",
                "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdNormalizedString, universe);

        Node xsdToken = universe.createNode("xsd:token", xsd);
        xsdToken.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdToken.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdToken.getMetadataProperty().addMetadata("owl:type", "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdToken, universe);

        Node xsdLanguage = universe.createNode("xsd:language", xsd);
        xsdLanguage.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdLanguage.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdLanguage.getMetadataProperty().addMetadata("owl:type",
                "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdLanguage, universe);

        Node xsdNMToken = universe.createNode("xsd:NMTOKEN", xsd);
        xsdNMToken.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdNMToken.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdNMToken.getMetadataProperty().addMetadata("owl:type",
                "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdNMToken, universe);

        Node xsdName = universe.createNode("xsd:Name", xsd);
        xsdName.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdName.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdName.getMetadataProperty().addMetadata("owl:type", "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdName, universe);

        Node xsdNCName = universe.createNode("xsd:NCName", xsd);
        xsdNCName.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdNCName.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdNCName.getMetadataProperty()
                .addMetadata("owl:type", "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdNCName, universe);

        Node xsdInteger = universe.createNode("xsd:integer", xsd);
        xsdInteger.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdInteger.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdInteger.getMetadataProperty().addMetadata("owl:type",
                "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdInteger, universe);

        Node xsdNonPositiveInteger = universe.createNode(
                "xsd:nonPositiveInteger", xsd);
        xsdNonPositiveInteger.getMetadataProperty().addMetadata("owl:prefix",
                "xsd");
        xsdNonPositiveInteger.getMetadataProperty().addMetadata("owl:type",
                "owl:Class");
        xsdNonPositiveInteger.getMetadataProperty().addMetadata("owl:type",
                "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdNonPositiveInteger, universe);

        Node xsdNegativeInteger = universe.createNode("xsd:negativeInteger",
                xsd);
        xsdNegativeInteger.getMetadataProperty().addMetadata("owl:prefix",
                "xsd");
        xsdNegativeInteger.getMetadataProperty().addMetadata("owl:type",
                "owl:Class");
        xsdNegativeInteger.getMetadataProperty().addMetadata("owl:type",
                "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdNegativeInteger, universe);

        Node xsdLong = universe.createNode("xsd:long", xsd);
        xsdLong.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdLong.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdLong.getMetadataProperty().addMetadata("owl:type", "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdLong, universe);

        Node xsdInt = universe.createNode("xsd:int", xsd);
        xsdInt.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdInt.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdInt.getMetadataProperty().addMetadata("owl:type", "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdInt, universe);

        Node xsdShort = universe.createNode("xsd:short", xsd);
        xsdShort.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdShort.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdShort.getMetadataProperty().addMetadata("owl:type", "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdShort, universe);

        Node xsdByte = universe.createNode("xsd:byte", xsd);
        xsdByte.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdByte.getMetadataProperty().addMetadata("owl:type", "owl:Class");
        xsdByte.getMetadataProperty().addMetadata("owl:type", "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdByte, universe);

        Node xsdNonNegativeInteger = universe.createNode(
                "xsd:nonNegativeInteger", xsd);
        xsdNonNegativeInteger.getMetadataProperty().addMetadata("owl:prefix",
                "xsd");
        xsdNonNegativeInteger.getMetadataProperty().addMetadata("owl:type",
                "owl:Class");
        xsdNonNegativeInteger.getMetadataProperty().addMetadata("owl:type",
                "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdNonNegativeInteger, universe);

        Node xsdUnsignedLong = universe.createNode("xsd:unsignedLong", xsd);
        xsdUnsignedLong.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdUnsignedLong.getMetadataProperty().addMetadata("owl:type",
                "owl:Class");
        xsdUnsignedLong.getMetadataProperty().addMetadata("owl:type",
                "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdUnsignedLong, universe);

        Node xsdUnsignedInt = universe.createNode("xsd:unsignedInt", xsd);
        xsdUnsignedInt.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdUnsignedInt.getMetadataProperty().addMetadata("owl:type",
                "owl:Class");
        xsdUnsignedInt.getMetadataProperty().addMetadata("owl:type",
                "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdUnsignedInt, universe);

        Node xsdUnsignedShort = universe.createNode("xsd:unsignedShort", xsd);
        xsdUnsignedShort.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdUnsignedShort.getMetadataProperty().addMetadata("owl:type",
                "owl:Class");
        xsdUnsignedShort.getMetadataProperty().addMetadata("owl:type",
                "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdUnsignedShort, universe);

        Node xsdUnsignedByte = universe.createNode("xsd:unsignedByte", xsd);
        xsdUnsignedByte.getMetadataProperty().addMetadata("owl:prefix", "xsd");
        xsdUnsignedByte.getMetadataProperty().addMetadata("owl:type",
                "owl:Class");
        xsdUnsignedByte.getMetadataProperty().addMetadata("owl:type",
                "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdUnsignedByte, universe);

        Node xsdPositiveInteger = universe.createNode("xsd:positiveInteger",
                xsd);
        xsdPositiveInteger.getMetadataProperty().addMetadata("owl:prefix",
                "xsd");
        xsdPositiveInteger.getMetadataProperty().addMetadata("owl:type",
                "owl:Class");
        xsdPositiveInteger.getMetadataProperty().addMetadata("owl:type",
                "rdfs:Datatype");
        this.addOwlThingSuperClass(xsdPositiveInteger, universe);


        // Built-in Annotation Properties
        Node versionInfo = universe.createNode("owl:versionInfo", owl);
        versionInfo.getMetadataProperty().addMetadata("owl:type",
                "owl:AnnotationProperty");
        versionInfo.getMetadataProperty().addMetadata("owl:prefix", "owl");
        this.addOwlThingSuperProperty(versionInfo, universe);

        Node rdfsLabel = universe.createNode("rdfs:label", rdfs);
        rdfsLabel.getMetadataProperty().addMetadata("owl:type",
                "owl:AnnotationProperty");
        rdfsLabel.getMetadataProperty().addMetadata("owl:prefix", "rdfs");
        this.addOwlThingSuperProperty(rdfsLabel, universe);

        Node rdfsComment = universe.createNode("rdfs:comment", rdfs);
        rdfsComment.getMetadataProperty().addMetadata("owl:type",
                "owl:AnnotationProperty");
        rdfsComment.getMetadataProperty().addMetadata("owl:prefix", "rdfs");
        this.addOwlThingSuperProperty(rdfsComment, universe);

        Node rdfsSeeAlso = universe.createNode("rdfs:seeAlso", rdfs);
        rdfsSeeAlso.getMetadataProperty().addMetadata("owl:type",
                "owl:AnnotationProperty");
        rdfsSeeAlso.getMetadataProperty().addMetadata("owl:prefix", "rdfs");
        this.addOwlThingSuperProperty(rdfsSeeAlso, universe);

        Node rdfsIsDefinedBy = universe.createNode("rdfs:isDefinedBy", rdfs);
        rdfsIsDefinedBy.getMetadataProperty().addMetadata("owl:type",
                "owl:AnnotationProperty");
        rdfsIsDefinedBy.getMetadataProperty().addMetadata("owl:prefix", "rdfs");
        this.addOwlThingSuperProperty(rdfsIsDefinedBy, universe);


        // Built-in Ontology Properties
        Node owlImports = universe.createNode("owl:imports", owl);
        owlImports.getMetadataProperty().addMetadata("owl:type",
                "owl:OntologyProperty");
        versionInfo.getMetadataProperty().addMetadata("owl:prefix", "owl");
        this.addOwlThingSuperProperty(owlImports, universe);

        Node owlPriorVersion = universe.createNode("owl:priorVersion", owl);
        owlPriorVersion.getMetadataProperty().addMetadata("owl:type",
                "owl:OntologyProperty");
        owlPriorVersion.getMetadataProperty().addMetadata("owl:prefix", "owl");
        this.addOwlThingSuperProperty(owlPriorVersion, universe);

        Node owlBackwardCompatibleWith = universe.createNode(
                "owl:backwardCompatibleWith", owl);
        owlBackwardCompatibleWith.getMetadataProperty().addMetadata("owl:type",
                "owl:OntologyProperty");
        owlBackwardCompatibleWith.getMetadataProperty().addMetadata(
                "owl:prefix", "owl");
        this.addOwlThingSuperProperty(owlBackwardCompatibleWith, universe);

        Node owlIncompatibleWith = universe.createNode("owl:incompatibleWith",
                owl);
        owlIncompatibleWith.getMetadataProperty().addMetadata("owl:type",
                "owl:OntologyProperty");
        owlIncompatibleWith.getMetadataProperty().addMetadata("owl:prefix",
                "owl");
        this.addOwlThingSuperProperty(owlIncompatibleWith, universe);
    }


    private void parseOntology(Universe universe)
            throws ConversionException {

        Set<String> importedOntologies = this.ontologyModel
                .listImportedOntologyURIs(true);

        // System.out.println("======================\nOntologies");

        ExtendedIterator<Ontology> ontit = this.ontologyModel.listOntologies();
        LinkedList<Ontology> ontologies = new LinkedList<>();

        while (ontit.hasNext()) {

            ontologies.addFirst(ontit.next());
        }

        for (Ontology ontology : ontologies) {

            // System.out.println("\tCreating Graph for " + ontology.getURI());


            Graph graph = universe.createGraph(ontology.getURI());

            String prefix = this.ontologyModel.getNsURIPrefix(ontology.getURI()
                    + "#");

            if (prefix != null) {

                graph.getMetadataProperty().addMetadata("owl:prefix", prefix);
            }


            // ================================================================
            // * OntologyProperties
            // ================================================================

            // owl:imports
            ExtendedIterator<OntResource> resourceIt = ontology.listImports();
            while (resourceIt.hasNext()) {

                OntResource resource = resourceIt.next();
                graph.getMetadataProperty().addMetadata("owl:imports",
                        resource.getURI());
            }

            // owl:priorVersion
            resourceIt = ontology.listPriorVersion();
            while (resourceIt.hasNext()) {

                OntResource resource = resourceIt.next();
                graph.getMetadataProperty().addMetadata("owl:priorVersion",
                        resource.getURI());
            }


            // owl:backwardsCompatibleWith
            resourceIt = ontology.listBackwardCompatibleWith();
            while (resourceIt.hasNext()) {

                OntResource resource = resourceIt.next();
                graph.getMetadataProperty().addMetadata(
                        "owl:backwardCompatibleWith", resource.getURI());
            }

            // owl:incompatibleWith
            resourceIt = ontology.listIncompatibleWith();
            while (resourceIt.hasNext()) {

                OntResource resource = resourceIt.next();
                graph.getMetadataProperty().addMetadata("owl:incompatibleWith",
                        resource.getURI());
            }

            this.parsePredefinedAnnotationPropertyInstance(ontology, graph);


            OntModel subModel;

            // System.out.println(importedOntologies);
            if (importedOntologies.contains(ontology.getURI())) {

                // System.out.println("The imported one...");
                subModel = this.ontologyModel.getImportedModel(ontology
                        .getURI());
            }
            else {
                // System.out.println("Not the imported one...");
                subModel = this.ontologyModel;
            }

            // System.out.println("\tParsing SubModel for " +
            // ontology.getURI());


            // System.out.println("======================\nNamed Classes");
            ExtendedIterator<OntClass> namedClassit = subModel.listClasses();
            while (namedClassit.hasNext()) {

                this.parseClass(namedClassit.next(), universe, graph.getID());

            }


            // System.out.println("======================\nProperties");
            ExtendedIterator<OntProperty> objectPropertyIt = subModel
                    .listAllOntProperties();
            while (objectPropertyIt.hasNext()) {

                this.parseProperty(objectPropertyIt.next(), universe,
                        graph.getID());
            }


            // System.out.println("======================\nIndividuals");
            ExtendedIterator<Individual> individualIt = subModel
                    .listIndividuals();
            while (individualIt.hasNext()) {

                this.parseIndividual(individualIt.next(), universe,
                        graph.getID());
            }


            // System.out.println("======================\nAllDifferent");
            ExtendedIterator<AllDifferent> allDifferentIt = subModel
                    .listAllDifferent();

            while (allDifferentIt.hasNext()) {

                this.parseAllDifferent(allDifferentIt.next(), universe,
                        graph.getID());
            }


            // System.out.println("======================\nPropertyInstances");
            ExtendedIterator<OntProperty> anotherPropertyIt = subModel
                    .listAllOntProperties();
            while (anotherPropertyIt.hasNext()) {
                OntProperty property = anotherPropertyIt.next();

                Node propertyNode = this.getNodeForResource(universe, property);

                if (propertyNode == null) {
                    propertyNode = this.parseProperty(property, universe,
                            graph.getID());
                }

                // System.out.println("  Parsing instances of property "
                // + propertyNode.getID());

                ResIterator resourceIterator = subModel
                        .listResourcesWithProperty(property);

                while (resourceIterator.hasNext()) {
                    Resource resource = resourceIterator.next();

                    // System.out.println("\tResource: " + resource.toString());
                    if (resource.canAs(OntResource.class)) {

                        Node resourceNode = this.getNodeForResource(universe,
                                resource);

                        if (resourceNode == null) {
                            resourceNode = this.parseResource(resource,
                                    universe, graph.getID());
                        }


                        // System.out.println("    [" + resourceNode.getID()
                        // + "]");

                        // if (resource.canAs(OntClass.class)) {
                        // System.out.println(" is a Class");
                        // }
                        // else if (resource.canAs(Individual.class)) {
                        //
                        // System.out.println(" is an Individual");
                        // }
                        // else if (resource.canAs(OntProperty.class)) {
                        // System.out.println(" is a Property");
                        // }


                        this.parsePropertyInstanceForResource(property,
                                resource.as(OntResource.class), universe,
                                graph.getID(), propertyNode, resourceNode);
                    }
                    else {
                        throw new ConversionException(
                                "Cannot parse properties for a Resource that is not a valid OntResouce!");
                    }
                }


            }

            // System.exit(-1);
        }
    }


    private boolean isNamedResource(Resource resource) {

        return resource.getURI() != null;
    }


    private String getPrefix(String uri, Universe universe) {

        Graph graph = universe.getGraph(uri);
        if (graph != null) {
            List<Metadata> prefixes = graph.getMetadataProperty().getMetadata(
                    "owl:prefix");
            if (!prefixes.isEmpty()) {
                return (String) prefixes.iterator().next().getValue();
            }
        }

        return null;
    }

    private Node parseResource(Resource resource, Universe universe,
            String definingOntology)
            throws ConversionException {

        // System.out.println("Parsing resource " + resource);
        if (resource.canAs(OntProperty.class)) {
            return this.parseProperty(resource.as(OntProperty.class), universe,
                    definingOntology);
        }
        else if (resource.canAs(OntClass.class)) {
            return this.parseClass(resource.as(OntClass.class), universe,
                    definingOntology);
        }
        else if (resource.canAs(Individual.class)) {
            return this.parseIndividual(resource.as(Individual.class),
                    universe, definingOntology);
        }

        else {
            throw new ConversionException("Cannot parse resource of type "
                    + resource.getClass() + "!");
        }

    }


    private Node parseClass(OntClass clazz, Universe universe,
            String definingOntology)
            throws ConversionException {

        Node classNode = null;
        boolean anonymous = false;
        boolean created = false;

        // Named classes
        if (this.isNamedResource(clazz)) {

            classNode = this.getNodeForNamedResource(universe, clazz);

            if (classNode == null) {

                // System.out.println("Creating new node");
                classNode = this.createNamedResource(universe, clazz);
                classNode.getMetadataProperty().addMetadata("owl:type",
                        "owl:Class");
                created = true;
            }

        }
        // Anonymous classes
        else {

            anonymous = true;
            classNode = this.getNodeForAnonymousResource(universe, clazz);

            if (classNode == null) {

                classNode = this.createAnonymousResource(clazz, universe,
                        definingOntology, null, ResourceType.CLASS);
                created = true;
            }
        }

        if (classNode == null) {

            String className = "";
            if (anonymous) {
                className = "anonymous class";
            }
            else {
                className = "class + \""
                        + this.getQualifiedNameForResource(clazz) + "\"";
            }

            throw new ConversionException("Could not create " + className);
        }


        if (created) {

            this.parseRelatedClasses(clazz, universe, definingOntology,
                    classNode);
            this.parseDescription(clazz, universe, definingOntology, classNode);
            this.parsePredefinedAnnotationPropertyInstance(clazz, classNode);
        }
        return classNode;
    }


    private Node getNodeForResource(Universe universe, Resource resource) {

        if (this.isNamedResource(resource)) {
            return this.getNodeForNamedResource(universe, resource);
        }
        return this.getNodeForAnonymousResource(universe, resource);
    }


    private Node getNodeForNamedResource(Universe universe, Resource resource) {

        return universe.getNode(this.getQualifiedNameForResource(resource));
    }


    /**
     * @param universe
     */
    private Node getNodeForAnonymousResource(Universe universe,
            Resource resource) {

        return this.anonymousResourceMap.get(resource);
    }


    private String getDefiningOntologyForResource(Resource clazz) {

        // System.out.println("    Class name: " + clazz.getURI());
        // System.out.println("    Namespace: " + clazz.getNameSpace());
        String expandedNamespace = ontologyModel.expandPrefix(clazz
                .getNameSpace());
        // System.out.println("    Expanded namespace: " + expandedNamespace);
        String ontologyName = expandedNamespace.substring(0,
                expandedNamespace.length() - 1);
        // System.out.println("    defining OntologyName: " + ontologyName);

        return ontologyName;
    }


    private String getQualifiedNameForResource(Resource resource) {

        String uri = resource.getURI();
        // String ontologyName = resource.getNameSpace().substring(0,
        // resource.getNameSpace().length() - 1);
        // System.out.println("  \"" + ontologyModel.qnameFor(uri) + "\"");
        // System.out.println("    Namespace: " + resource.getNameSpace());
        // System.out.println("    Local Name: " + resource.getLocalName());
        // System.out.println("    URI: " + uri);
        // System.out.println("    OntologyName: " + ontologyName);

        String name;
        String prefix = this.ontologyModel.getNsURIPrefix(resource
                .getNameSpace());

        if (prefix != null && prefix != "") {
            name = this.ontologyModel.qnameFor(uri);
        }
        else {
            name = resource.getLocalName();
            prefix = "";
        }

        return name;
    }


    private Node createNamedResource(Universe universe, Resource resource) {

        String name = this.getQualifiedNameForResource(resource);

        String definingOntology = this.getDefiningOntologyForResource(resource);

        if (universe.getGraph(definingOntology) == null) {

            universe.createGraph(definingOntology);
        }

//         System.out.println("  CreatingNode " + name);
        Node node = universe.createNode(name, definingOntology);


        return node;
    }

    private Node createAnonymousResource(Resource resource, Universe universe,
            String definingOntology, String name, ResourceType resourceType) {

        String prefix = this.getPrefix(definingOntology, universe);

        if (prefix != null) {
            if (prefix == "") {

                // System.out.println("Default prefix.");
            }
            else {
                // System.out.println("Prefix = " + prefix);
            }
        }
        else {
            // System.out.println("Prefix not found!!");
            prefix = "";
        }


        Random random = new Random();
        String qualifiedName = prefix + "Anonymous" + resourceType + "@"
                + random.nextLong();

        if (name != null && !"".equals(name)) {
            qualifiedName += ": " + name;
        }

        Node anonymousResourceNode = universe.createNode(qualifiedName,
                definingOntology);

        switch (resourceType) {
            case CLASS:
                anonymousResourceNode.getMetadataProperty().addMetadata(
                        "owl:type", "owl:AnonymousClass");
                break;
            case PROPERTY:
                anonymousResourceNode.getMetadataProperty().addMetadata(
                        "owl:type", "owl:AnonymousProperty");
                break;
            case DATA_RANGE:
                anonymousResourceNode.getMetadataProperty().addMetadata(
                        "owl:type", "owl:DataRange");
                break;
            default:
                break;
        }

        this.anonymousResourceMap.put(resource, anonymousResourceNode);

        return anonymousResourceNode;
    }


    private void parseDescription(OntClass clazz, Universe universe,
            String definingOntology, Node classNode)
            throws ConversionException {

        // Restrictions
        if (clazz.isRestriction()) {

            // System.out.println("  Restriction:  " + clazz);
            Restriction restriction = clazz.asRestriction();

            this.parseRestriction(restriction, universe, definingOntology,
                    classNode);
        }
        // unionOf
        if (clazz.isUnionClass()) {

            // System.out.println("  Union class:  " + clazz);
            this.parseBooleanClassCombination(clazz.asUnionClass(), universe,
                    definingOntology, classNode,
                    BooleanClassCombinations.UNION_OF);
        }
        // intersectionOf
        if (clazz.isIntersectionClass()) {

            // System.out.println("  Intersection class:  " + clazz);
            this.parseBooleanClassCombination(clazz.asIntersectionClass(),
                    universe, definingOntology, classNode,
                    BooleanClassCombinations.INTERSECTION_OF);
        }
        // owl:complementOf
        if (clazz.isComplementClass()) {

            // System.out.println("  Complement class:  " + clazz);

            this.parseBooleanClassCombination(clazz.asComplementClass(),
                    universe, definingOntology, classNode,
                    BooleanClassCombinations.COMPLEMENT_OF);
        }
        // owl:oneOf
        if (clazz.isEnumeratedClass()) {

            // System.out.println("  Enumerated class:  " + clazz);
            this.parseEnumeratedClass(clazz.asEnumeratedClass(), universe,
                    definingOntology, classNode);
        }

    }


    private void parseRelatedClasses(OntClass clazz, Universe universe,
            String definingOntology, Node classNode)
            throws ConversionException {

        // owl:disjointWith
        this.parseRelatedClasses(clazz, universe, definingOntology, classNode,
                clazz.listDisjointWith(), ClassRelation.DISJOINT_CLASS);

        // owl:equivalentClass
        this.parseRelatedClasses(clazz, universe, definingOntology, classNode,
                clazz.listEquivalentClasses(), ClassRelation.EQUIVALENT_CLASS);

        // rdfs:subClassOf (inverse)
        this.parseRelatedClasses(clazz, universe, definingOntology, classNode,
                clazz.listSuperClasses(true), ClassRelation.SUPERCLASS);

        // rdfs:subClassOf
        this.parseRelatedClasses(clazz, universe, definingOntology, classNode,
                clazz.listSubClasses(true), ClassRelation.SUBCLASS);

    }


    /**
     * @param clazz
     */
    private void parseRelatedClasses(OntClass clazz, Universe universe,
            String definingOntology, Node classNode,
            ExtendedIterator<? extends OntResource> classIt,
            ClassRelation classRelation)
            throws ConversionException {

        // Adding owl:Thing as super-class of all nodes without an explicit
        // super-class.
        if (classRelation == ClassRelation.SUPERCLASS && !classIt.hasNext()) {

            this.addOwlThingSuperClass(classNode, universe);
        }

        while (classIt.hasNext()) {
            // System.out.println("  In Related Class");
            OntResource relatedResource = classIt.next();
            OntClass relatedOntClass;
            if (relatedResource.canAs(OntClass.class)) {
                relatedOntClass = relatedResource.as(OntClass.class);
            }
            else {
                throw new IllegalArgumentException(
                        "Related class is not an instance of OntClass!");
            }
            // System.out.println("    RelatedOntClass: " + relatedOntClass);
            // System.out.println("\tURI: " + relatedOntClass.getURI());
            // System.out.println("    Parsing related class: ");
            Node relatedClass = this.parseClass(relatedOntClass, universe,
                    definingOntology);

            // System.out.println("    Related Class: " + relatedClass);
            Edge edge = null;
            String edgeInfix = "";
            Metadata owlType = null;

            switch (classRelation) {
                case DISJOINT_CLASS:
                    edgeInfix = "disjoint with";
                    owlType = Metadata.createMetadata("owl:type",
                            "owl:disjointWith");
                    break;
                case EQUIVALENT_CLASS:
                    edgeInfix = "equivalent class of";
                    owlType = Metadata.createMetadata("owl:type",
                            "owl:equivalentClass");
                    break;
                case SUBCLASS:
                    edgeInfix = "superclass of";
                    owlType = Metadata.createMetadata("owl:type",
                            "owl:superClassOf");
                    break;
                case SUPERCLASS:
                    edgeInfix = "subclass of";
                    owlType = Metadata.createMetadata("owl:type",
                            "rdfs:subClassOf");
                    break;
                default:
                    break;

            }

            edge = universe.createEdge("[" + classNode.getID() + "] "
                    + edgeInfix + " [" + relatedClass.getID() + "]",
                    classNode.getGraph(), classNode, relatedClass);

            edge.setDirected();
            edge.getMetadataProperty().addMetadata(owlType);

            // System.out.println("New edge: " + edge);

        }
    }

    private void parseEnumeratedClass(EnumeratedClass clazz, Universe universe,
            String definingOntology, Node classNode)
            throws ConversionException {

        ExtendedIterator<? extends OntResource> individualIt = clazz
                .listOneOf();


        List<Node> individuals = new LinkedList<>();

        while (individualIt.hasNext()) {

            OntResource resource = individualIt.next();

            if (resource.isIndividual()) {
                Individual individual = resource.asIndividual();

                Node individualNode = this.parseIndividual(individual,
                        universe, definingOntology);
                if (individualNode != null) {

                    individuals.add(individualNode);
                }
                else {
                    throw new ConversionException(
                            "Could not parse individual of enumerated class "
                                    + classNode.getID());
                }
            }
        }

        String individualList = "{";


        int i = 0;
        for (Node individualNode : individuals) {
            if (i > 0) {
                individualList += "; ";
            }
            individualList += individualNode.getID();
        }
        individualList += "}";


        String name = "Class [" + classNode.getID() + "] := oneOf ";

        name += individualList;


        for (Node individualNode : individuals) {

            String edgeName = "[" + individualNode.getID() + "] is part of"
                    + name;

            Edge edge = universe.createEdge(edgeName, definingOntology,
                    classNode.getID(), individualNode.getID());

            edge.getMetadataProperty().addMetadata("owl:type", "owl:oneOf");
        }

    }


    private void parseBooleanClassCombination(BooleanClassDescription clazz,
            Universe universe, String definingOntology, Node classNode,
            BooleanClassCombinations type)
            throws ConversionException {

        // System.out.println("BooleanClassCOmbination!");

        List<Node> classes = new LinkedList<>();

        ExtendedIterator<? extends OntResource> classIt = clazz.listOperands();

        while (classIt.hasNext()) {

            OntResource resource = classIt.next();

            if (resource.isClass()) {

                OntClass complementClass = resource.asClass();

                Node complementClassNode = this.parseClass(complementClass,
                        universe, definingOntology);

                if (complementClassNode != null) {

                    classes.add(complementClassNode);
                }
                else {
                    throw new ConversionException(
                            "Could not parse class of boolean class combination for "
                                    + classNode.getID());
                }
            }
        }

        String classList = "{";
        int i = 0;
        for (Node individualNode : classes) {
            if (i > 0) {
                classList += ", ";
            }
            classList += individualNode.getID();
            i++;
        }
        classList += "}";

        String name = " Class [" + classNode.getID() + "] := ";


        switch (type) {
            case COMPLEMENT_OF:
                name += "complementOf ";
                break;
            case INTERSECTION_OF:
                name += "intersectionOf ";
                break;
            case UNION_OF:
                name += "unionOf ";
                break;
            default:
                break;
        }

        name += classList;


        // System.out.println("Name: " + name);

        for (Node individualNode : classes) {

            String edgeName = "[" + individualNode.getID()
                    + "] is part of boolean class combination:" + name;

            Edge edge = universe.createEdge(edgeName, definingOntology,
                    classNode.getID(), individualNode.getID());
            edge.setDirected();

            edge.getMetadataProperty().addMetadata("owl:type", "owl:oneOf");
        }
    }


    private void parseRestriction(Restriction restriction, Universe universe,
            String definingOntology, Node classNode)
            throws ConversionException {

        // System.out.println("Parsing restriction");


        OntProperty property = restriction.getOnProperty();
        Node restrictedProperty = this.parseProperty(property, universe,
                definingOntology);

        if (restrictedProperty == null) {

            String className = "";
            if (classNode == null) {
                className = "anonymous class";
            }
            else {
                className = classNode.getID();
            }

            throw new ConversionException(
                    "Could not parse restricted property for class "
                            + className);
        }

        boolean literalRestriction = false;


        Node restrictingClass = null;
        String restrictionType = null;
        String restrictionValue = null;
        String owlType = null;

        // All Values From [some class or data range]
        if (restriction.isAllValuesFromRestriction()) {
            // System.out.println("    AllValuesFrom");

            restrictingClass = this.parseAllValuesFrom(
                    restriction.asAllValuesFromRestriction(), universe,
                    definingOntology);


            restrictionType = "AllValuesFrom";
            restrictionValue = restrictingClass.getID();
            owlType = "owl:allValuesFrom";

        }
        // Some Values From [some class or data range]
        else if (restriction.isSomeValuesFromRestriction()) {
            // System.out.println("    SomeValuesFrom");

            restrictingClass = this.parseSomeValuesFrom(
                    restriction.asSomeValuesFromRestriction(), universe,
                    definingOntology);

            restrictionType = "SomeValuesFrom";
            restrictionValue = restrictingClass.getID();
            owlType = "owl:someValuesFrom";

        }
        // Cardinality equals [cardinality]
        else if (restriction.isCardinalityRestriction()) {
            // System.out.println("    CardinalityRestriction");

            CardinalityRestriction cardinalityRestriction = restriction
                    .asCardinalityRestriction();

            restrictionType = "CardinalityRestriction";
            restrictionValue = "[cardinality = "
                    + cardinalityRestriction.getCardinality() + "]";
            owlType = "owl:cardinality";

            literalRestriction = true;

        }
        // Max cardinality equals [cardinality]
        else if (restriction.isMaxCardinalityRestriction()) {
            // System.out.println("    maxCardinalityRestriction");

            MaxCardinalityRestriction maxCardinalityRestriction = restriction
                    .asMaxCardinalityRestriction();

            restrictionType = "MaxCardinalityRestriction";
            restrictionValue = "[maxCardinality = "
                    + maxCardinalityRestriction.getMaxCardinality() + "]";
            owlType = "owl:maxCardinality";

            literalRestriction = true;
        }
        // Min cardinality equals [cardinality]
        else if (restriction.isMinCardinalityRestriction()) {
            // System.out.println("    minCardinalityRestriction");

            MinCardinalityRestriction minCardinalityRestriction = restriction
                    .asMinCardinalityRestriction();

            restrictionType = "MinCardinalityRestriction";
            restrictionValue = "[minCardinality = "
                    + minCardinalityRestriction.getMinCardinality() + "]";
            owlType = "owl:minCardinality";

            literalRestriction = true;
        }
        // Has Value [some individual]
        else if (restriction.isHasValueRestriction()) {
            // System.out.println("    hasValueRestriction");

            HasValueRestriction hasValueRestriction = restriction
                    .asHasValueRestriction();

            RDFNode hasValue = hasValueRestriction.getHasValue();
            if (hasValue.canAs(Individual.class)) {

                // System.out.println("      Individual");
                Individual individual = hasValue.as(Individual.class);
                restrictingClass = this.parseIndividual(individual, universe,
                        definingOntology);

                restrictionValue = restrictingClass.getID();

            }
            else if (hasValue.canAs(Literal.class)) {

                Literal data = hasValue.as(Literal.class);

                restrictionValue = "[" + data.getLexicalForm() + "]";
                literalRestriction = true;
            }

            restrictionType = "HasValueRestriction";
            owlType = "owl:hasValue";
        }

        if (restrictionType == null || owlType == null) {
            throw new ConversionException(
                    "Could not determine restriction type!");
        }

        if (restrictionValue == null) {
            throw new ConversionException(
                    "Could not determine restriction value for "
                            + restrictionType + " restriction on "
                            + restrictedProperty.getID());
        }


        Node restrictedClass = classNode;


        String restrictionOnPropertyName = "[" + classNode.getID() + "] has "
                + restrictionType + " restriction with value ["
                + restrictionValue + "] on property ["
                + restrictedProperty.getID() + "]";


        Edge restrictionOnProperty = universe.createEdge(
                restrictionOnPropertyName, definingOntology,
                restrictedClass.getID(), restrictedProperty.getID());

        restrictionOnProperty.getMetadataProperty().addMetadata("owl:type",
                owlType);
        restrictionOnProperty.getMetadataProperty().addMetadata("owl:type",
                "owl:onProperty");
        restrictionOnProperty.setDirected();
        // System.out.println("New edge: " + restrictionOnProperty);

        if (!literalRestriction) {

            String restrictionValueName = "restricts ["
                    + restrictedProperty.getID() + "] property of ["
                    + classNode.getID() + "] to " + restrictionType + " ["
                    + restrictionValue + "]";

            Edge restrictionOnValue = universe.createEdge(restrictionValueName,
                    definingOntology, restrictedClass.getID(),
                    restrictingClass.getID());
            restrictionOnValue.getMetadataProperty().addMetadata("owl:type",
                    owlType);
            restrictionOnValue.setDirected();
            // System.out.println("New edge: " + restrictionOnValue);
        }

        restrictedClass.getMetadataProperty().addMetadata("owl:type",
                "owl:Restriction");
    }


    private Node parseAllValuesFrom(AllValuesFromRestriction avfRestriction,
            Universe universe, String definingOntology)
            throws ConversionException {

        Node restrictingClass = null;

        Resource avfResource = avfRestriction.getAllValuesFrom();
        if (avfResource.canAs(OntClass.class)) {

            // System.out.println("      Class");
            OntClass avfClass = avfResource.as(OntClass.class);
            // System.out.println(avfClass.getURI());

            restrictingClass = this.parseClass(avfClass, universe,
                    definingOntology);

        }
        else if (avfResource.canAs(DataRange.class)) {

            DataRange avfDataRange = avfResource.as(DataRange.class);
            restrictingClass = this.parseDataRange(avfDataRange, universe,
                    definingOntology);
        }


        return restrictingClass;
    }


    private Node parseSomeValuesFrom(SomeValuesFromRestriction svfRestriction,
            Universe universe, String definingOntology)
            throws ConversionException {

        Node restrictingClass = null;

        Resource svfResource = svfRestriction.getSomeValuesFrom();
        if (svfResource.canAs(OntClass.class)) {

            // System.out.println("      Class");
            OntClass svfClass = svfResource.as(OntClass.class);
            // System.out.println(svfClass.getURI());
            restrictingClass = this.parseClass(svfClass, universe,
                    definingOntology);

        }
        else if (svfResource.canAs(DataRange.class)) {

            DataRange svfDataRange = svfResource.as(DataRange.class);
            restrictingClass = this.parseDataRange(svfDataRange, universe,
                    definingOntology);
        }

        return restrictingClass;
    }


    private Node parseDataRange(DataRange dataRange, Universe universe,
            String definingOntology) {

        // System.out.println("Parsing data range");

        String name = "OneOf ";


        List<String> literals = new LinkedList<>();
        ExtendedIterator<Literal> litIt = dataRange.listOneOf();

        while (litIt.hasNext()) {
            Literal literal = litIt.next();

            literals.add(literal.getLexicalForm());
        }

        String oneOfValues = "";
        int i = 0;
        for (String literal : literals) {

            if (i != 0) {
                oneOfValues += ", ";
            }
            i++;
            oneOfValues += "\"" + literal + "\"";
        }

        name += oneOfValues;
        // System.out.println(name);


        return this.createAnonymousResource(dataRange, universe,
                definingOntology, name, ResourceType.DATA_RANGE);


    }


    private Node parseProperty(OntProperty property, Universe universe,
            String definingOntology)
            throws ConversionException {


        Node propertyNode = null;
        boolean anonymous = false;
        boolean created = false;

        // Named classes
        if (this.isNamedResource(property)) {

            propertyNode = this.getNodeForNamedResource(universe, property);

            if (propertyNode == null) {

                // System.out.println("Creating new node");
                propertyNode = this.createNamedResource(universe, property);

                created = true;
            }

        }
        // Anonymous properties
        else {

            anonymous = true;

            propertyNode = this.getNodeForAnonymousResource(universe, property);

            if (propertyNode == null) {

                propertyNode = this.createAnonymousResource(property, universe,
                        definingOntology, null, ResourceType.PROPERTY);
                created = true;
            }
        }


        if (propertyNode == null) {

            String propertyName = "";
            if (anonymous) {
                propertyName = "anonymous property";
            }
            else {
                propertyName = "property + \""
                        + this.getQualifiedNameForResource(property) + "\"";
            }

            throw new ConversionException("Could not create " + propertyName);
        }


        if (created) {
            if (property.isAnnotationProperty()) {

                // System.out.println("    AnnotationProperty");
                propertyNode.getMetadataProperty().addMetadata("owl:type",
                        "owl:AnnotationProperty");
            }
            else if (property.isDatatypeProperty()) {

                // System.out.println("    DatatypeProperty");


                propertyNode.getMetadataProperty().addMetadata("owl:type",
                        "owl:DatatypeProperty");
                if (property.isFunctionalProperty()) {
                    propertyNode.getMetadataProperty().addMetadata("owl:type",
                            "owl:FunctionalProperty");
                }


                this.parseDatatypeOrObjectProperty(property, universe,
                        definingOntology, propertyNode);

            }
            else {
                if (property.isObjectProperty()) {

                    // System.out.println("    ObjectProperty");


                    propertyNode.getMetadataProperty().addMetadata("owl:type",
                            "owl:ObjectProperty");
                }
                if (property.isFunctionalProperty()) {

                    propertyNode.getMetadataProperty().addMetadata("owl:type",
                            "owl:ObjectProperty");
                    propertyNode.getMetadataProperty().addMetadata("owl:type",
                            "owl:FunctionalProperty");
                }
                if (property.isInverseFunctionalProperty()) {

                    propertyNode.getMetadataProperty().addMetadata("owl:type",
                            "owl:ObjectProperty");
                    propertyNode.getMetadataProperty().addMetadata("owl:type",
                            "owl:InverseFunctionalProperty");
                }
                if (property.isSymmetricProperty()) {

                    propertyNode.getMetadataProperty().addMetadata("owl:type",
                            "owl:ObjectProperty");
                    propertyNode.getMetadataProperty().addMetadata("owl:type",
                            "owl:SymmetricProperty");
                }
                if (property.isTransitiveProperty()) {

                    propertyNode.getMetadataProperty().addMetadata("owl:type",
                            "owl:ObjectProperty");
                    propertyNode.getMetadataProperty().addMetadata("owl:type",
                            "owl:TransitiveProperty");
                }


                this.parseDatatypeOrObjectProperty(property, universe,
                        definingOntology, propertyNode);

            }

            // else {
            // String propertyName = "";
            // if (anonymous) {
            // propertyName = "anonymous property";
            // }
            // else {
            // propertyName = "property + \""
            // + this.getQualifiedNameForResource(property) + "\"";
            // }
            // throw new ConversionException(
            // "Could not determine property type of " + propertyName
            // + "!");
            // }


            this.parsePredefinedAnnotationPropertyInstance(property,
                    propertyNode);
        }

        return propertyNode;
    }

    private void parseDatatypeOrObjectProperty(OntProperty property,
            Universe universe, String definingOntology, Node propertyNode)
            throws ConversionException {

        // Domains
        this.parsePropertyDomains(property, universe, definingOntology,
                propertyNode);


        // Ranges
        this.parsePropertyRanges(property, universe, definingOntology,
                propertyNode);

        // Related Properties
        this.parseRelatedProperties(property, universe, definingOntology,
                propertyNode);

    }


    private void parsePropertyDomains(OntProperty property, Universe universe,
            String definingOntology, Node propertyNode)
            throws ConversionException {


        ExtendedIterator<? extends OntResource> domainIt = property
                .listDomain();

        // System.out.println("Getting domains");
        while (domainIt.hasNext()) {

            OntResource domainResource = domainIt.next();

            if (domainResource.canAs(OntClass.class)) {
                OntClass domainClass = domainResource.asClass();

                Node domainNode = this.parseClass(domainClass, universe,
                        definingOntology);

                String edgeName = "[" + propertyNode.getID() + "] hasDomain ["
                        + domainNode.getID() + "]";

                Edge edge = universe.createEdge(edgeName, definingOntology,
                        propertyNode.getID(), domainNode.getID());

                edge.setDirected();
                // System.out.println("New edge: " + edge);
                edge.getMetadataProperty().addMetadata("owl:type",
                        "rdfs:domain");

            }
            else {
                throw new ConversionException(
                        "Domain is not a class equivalent!");
            }

        }
    }


    private void parsePropertyRanges(OntProperty property, Universe universe,
            String definingOntology, Node propertyNode)
            throws ConversionException {

        ExtendedIterator<? extends OntResource> rangeIt = property.listRange();
        while (rangeIt.hasNext()) {

            Node dataRangeNode = null;
            OntResource rangeResource = rangeIt.next();

            // System.out.print("Property range is a ");
            if (rangeResource.canAs(DataRange.class)) {

                // System.out.println("DataRange");
                DataRange dataRange = rangeResource.asDataRange();

                dataRangeNode = this.parseDataRange(dataRange, universe,
                        definingOntology);

            }
            else if (rangeResource.canAs(Literal.class)) {
                // System.out.println("Literal");
            }
            else if (rangeResource.canAs(OntClass.class)) {

                // System.out.println("Class");
                OntClass dataRangeClass = rangeResource.asClass();
                // System.out.println("Can be a data range? "
                // + rangeResource.canAs(DataRange.class));

                dataRangeNode = this.parseClass(dataRangeClass, universe,
                        definingOntology);

            }

            else {

                throw new ConversionException(
                        "Could not determine type of property range for property "
                                + propertyNode.getID());
            }


            if (dataRangeNode != null) {
                String edgeName = "[" + propertyNode.getID() + "] hasRange ["
                        + dataRangeNode.getID() + "]";

                Edge edge = universe.createEdge(edgeName, definingOntology,
                        propertyNode.getID(), dataRangeNode.getID());

                edge.setDirected();
                // System.out.println("New edge: " + edge);
                edge.getMetadataProperty()
                        .addMetadata("owl:type", "rdfs:range");
            }
            else {

                throw new ConversionException(
                        "Could not parse property range for property "
                                + propertyNode.getID());
            }
        }
    }


    private void parseRelatedProperties(OntProperty property,
            Universe universe, String definingOntology, Node propertyNode)
            throws ConversionException {


        // owl:equivalentProperty
        this.parseRelatedProperties(property, universe, definingOntology,
                propertyNode, property.listEquivalentProperties(),
                PropertyRelation.EQUIVALENT_PROPERTY);

        // rdfs:subPropertyOf (inverse)
        this.parseRelatedProperties(property, universe, definingOntology,
                propertyNode, property.listSuperProperties(true),
                PropertyRelation.SUPERPROPERTY);

        // rdfs:subPropertyOf
        this.parseRelatedProperties(property, universe, definingOntology,
                propertyNode, property.listSubProperties(true),
                PropertyRelation.SUBPROPERTY);

        // owl:inverseOf
        this.parseRelatedProperties(property, universe, definingOntology,
                propertyNode, property.listInverseOf(),
                PropertyRelation.INVERSE_PROPERTY);

    }


    /**
     * @param property
     */
    private void parseRelatedProperties(OntProperty property,
            Universe universe, String definingOntology, Node propertyNode,
            ExtendedIterator<? extends OntResource> propertyIt,
            PropertyRelation propertyRelation)
            throws ConversionException {

        if (PropertyRelation.SUPERPROPERTY == propertyRelation
                && !propertyIt.hasNext()) {

            this.addOwlThingSuperProperty(propertyNode, universe);
        }

        while (propertyIt.hasNext()) {
            // System.out.println("  In Related Properties");
            OntResource relatedResource = propertyIt.next();
            OntProperty relatedOntProperty;
            if (relatedResource.canAs(OntProperty.class)) {

                relatedOntProperty = relatedResource.as(OntProperty.class);
            }
            else {
                throw new IllegalArgumentException(
                        "Related Property not an instance of OntProperty!");
            }
            // System.out.println("    RelatedOntProperty: " +
            // relatedOntProperty);
            // System.out.println("\tURI: " + relatedOntProperty.getURI());
            // System.out.println("    Parsing related class: ");
            Node relatedProperty = this.parseProperty(relatedOntProperty,
                    universe, definingOntology);

            // System.out.println("    Related Property: " + relatedProperty);
            Edge edge = null;
            String edgeInfix = "";
            Metadata owlType = null;

            switch (propertyRelation) {
                case EQUIVALENT_PROPERTY:
                    edgeInfix = "equivalent property of";
                    owlType = Metadata.createMetadata("owl:type",
                            "owl:equivalentProperty");
                    break;
                case SUBPROPERTY:
                    edgeInfix = "superproperty of";
                    owlType = Metadata.createMetadata("owl:type",
                            "owl:superPropertyOf");
                    break;
                case SUPERPROPERTY:
                    edgeInfix = "subproperty of";
                    owlType = Metadata.createMetadata("owl:type",
                            "rdfs:subPropertyOf");
                    break;
                case INVERSE_PROPERTY:
                    edgeInfix = "inverse of";
                    owlType = Metadata.createMetadata("owl:type",
                            "rdfs:inverseOf");
                    break;
                default:
                    break;


            }

            edge = universe.createEdge("[" + propertyNode.getID() + "] "
                    + edgeInfix + " [" + relatedProperty.getID() + "]",
                    propertyNode.getGraph(), propertyNode, relatedProperty);

            edge.setDirected();
            edge.getMetadataProperty().addMetadata(owlType);

            // System.out.println("New edge: " + edge);

        }
    }


    private Node parseIndividual(Individual individual, Universe universe,
            String definingOntology)
            throws ConversionException {

        // System.out.println("  Parsing individual " + individual);
        Node individualNode = null;
        boolean anonymous = false;
        boolean created = false;

        // Named classes
        if (this.isNamedResource(individual)) {

            individualNode = this.getNodeForNamedResource(universe, individual);

            if (individualNode == null) {

                // System.out.println("Creating new node");
                individualNode = this.createNamedResource(universe, individual);
                individualNode.getMetadataProperty().addMetadata("owl:type",
                        "owl:Individual");
                created = true;
            }

        }
        // Anonymous classes
        else {

            anonymous = true;

            individualNode = this.getNodeForAnonymousResource(universe,
                    individual);

            if (individualNode == null) {

                individualNode = this.createAnonymousResource(individual,
                        universe, definingOntology, null, ResourceType.CLASS);
                created = true;
            }
        }

        if (individualNode == null) {

            String className = "";
            if (anonymous) {
                className = "anonymous class";
            }
            else {
                className = "class + \""
                        + this.getQualifiedNameForResource(individual) + "\"";
            }

            throw new ConversionException("Could not create " + className);
        }


        if (created) {

            // NodeIterator nodeIt = individual.listPropertyValues(null);
            // System.out.println("Beeep");
            // while (nodeIt.hasNext()) {
            // RDFNode node = nodeIt.next();
            // System.out.print(node.getClass() + ": ");
            // System.out.println(node);
            // if (node.canAs(Literal.class)) {
            // System.out.println("  Literal");
            // }
            // if (node.canAs(OntClass.class)) {
            // System.out.println("  Class");
            // }
            // if (node.canAs(Individual.class)) {
            // System.out.println("  Individual");
            // }
            // }
            // System.out.println("Boop");
            // System.exit(-1);

            this.parseClassInstance(individual, universe, definingOntology,
                    individualNode);

            this.parseRelatedIndividuals(individual, universe,
                    definingOntology, individualNode);

            this.parsePredefinedAnnotationPropertyInstance(individual,
                    individualNode);
        }
        return individualNode;
    }


    private void parseClassInstance(Individual individual, Universe universe,
            String definingOntology, Node individualNode)
            throws ConversionException {


        ExtendedIterator<? extends OntClass> classIt = individual
                .listOntClasses(true);

        if (!classIt.hasNext()) {

            this.addOwlThingClassInstance(individualNode, universe);
        }

        while (classIt.hasNext()) {
            // System.out.println("  Parsing Class Instance");
            OntResource relatedResource = classIt.next();
            // System.out.println("    Related resource: " + relatedResource);
            OntClass relatedOntClass;
            if (relatedResource.canAs(OntClass.class)) {
                relatedOntClass = relatedResource.as(OntClass.class);
            }
            else {
                throw new IllegalArgumentException(
                        "Related Resource not instance of OntClass!");
            }
            // System.out.println("    Instance of Class: " + relatedOntClass);
            // System.out.println("\tURI: " + relatedOntClass.getURI());
            // System.out.println("    Parsing related class: ");
            Node relatedClass = this.parseClass(relatedOntClass, universe,
                    definingOntology);

            // System.out.println("    Related Class: " + relatedClass);
            Edge edge = null;
            String edgeInfix = "instance of";


            edge = universe.createEdge("[" + individualNode.getID() + "] "
                    + edgeInfix + " [" + relatedClass.getID() + "]",
                    individualNode.getGraph(), individualNode, relatedClass);

            edge.setDirected();
            edge.getMetadataProperty()
                    .addMetadata("owl:type", "owl:instanceOf");

            // System.out.println("New edge: " + edge);

        }
    }


    private void parseRelatedIndividuals(Individual individual,
            Universe universe, String definingOntology, Node individualNode)
            throws ConversionException {

        // System.out.println("RELATED INDIVIDUALS");
        // owl:disjointWith
        this.parseRelatedIndividuals(individual, universe, definingOntology,
                individualNode, individual.listSameAs(),
                IndividualRelation.SAME_INDIVIDUAL);

        // owl:equivalentClass
        this.parseRelatedIndividuals(individual, universe, definingOntology,
                individualNode, individual.listDifferentFrom(),
                IndividualRelation.DIFFERENT_INDIVIDUAL);


    }


    /**
     * @param individual
     */
    private void parseRelatedIndividuals(Individual individual,
            Universe universe, String definingOntology, Node individualNode,
            ExtendedIterator<? extends Resource> individualIt,
            IndividualRelation individualRelation)
            throws ConversionException {

        while (individualIt.hasNext()) {
            // System.out.println("  In Related Individuals");
            Resource relatedResource = individualIt.next();
            Individual relatedIndividual;
            if (relatedResource.canAs(Individual.class)) {
                relatedIndividual = relatedResource.as(Individual.class);
            }
            else {
                throw new IllegalArgumentException(
                        "Related Individual not an instance of Individual!");
            }
            // System.out.println("    RelatedIndividual: " +
            // relatedIndividual);
            // System.out.println("\tURI: " + relatedIndividual.getURI());
            // System.out.println("    Parsing related individual: ");
            Node relatedIndividualNode = this.parseIndividual(
                    relatedIndividual, universe, definingOntology);

            // System.out.println("    Related Individual: "
            // + relatedIndividualNode);
            Edge edge = null;
            String edgeInfix = "";
            Metadata owlType = null;

            switch (individualRelation) {
                case DIFFERENT_INDIVIDUAL:
                    edgeInfix = "different from";
                    owlType = Metadata.createMetadata("owl:type",
                            "owl:differentFrom");
                    break;
                case SAME_INDIVIDUAL:
                    edgeInfix = "same as";
                    owlType = Metadata.createMetadata("owl:type", "owl:sameAs");
                    break;
                default:
                    break;

            }

            edge = universe.createEdge("[" + individualNode.getID() + "] "
                    + edgeInfix + " [" + relatedIndividualNode.getID() + "]",
                    individualNode.getGraph(), individualNode,
                    relatedIndividualNode);

            edge.setDirected();
            edge.getMetadataProperty().addMetadata(owlType);

            // System.out.println("New edge: " + edge);

        }
    }


    private void parseAllDifferent(AllDifferent allDifferent,
            Universe universe, String definingOntology)
            throws ConversionException {

        Edge edge = null;

        if (allDifferent.getDistinctMembers() != null) {
            String edgeInfix = "different from";
            ExtendedIterator<? extends OntResource> distinctMemberIt = allDifferent
                    .listDistinctMembers();

            List<Node> distinctMembers = new LinkedList<>();
            while (distinctMemberIt.hasNext()) {

                OntResource resource = distinctMemberIt.next();

                if (resource.canAs(Individual.class)) {
                    distinctMembers
                            .add(this.parseIndividual(resource.asIndividual(),
                                    universe, definingOntology));
                }
            }

            for (int i = 0; i < distinctMembers.size() - 1; i++) {

                Node individualNode = distinctMembers.get(i);
                Node relatedIndividualNode = distinctMembers.get(i + 1);
                edge = universe.createEdge("[" + individualNode.getID() + "] "
                        + edgeInfix + " [" + relatedIndividualNode.getID()
                        + "]", individualNode.getGraph(), individualNode,
                        relatedIndividualNode);

                edge.getMetadataProperty().addMetadata("owl:type",
                        "owl:differentFrom");
                edge.getMetadataProperty().addMetadata("owl:type",
                        "owl:AllDifferent");

                individualNode.getMetadataProperty().addMetadata("owl:type",
                        "owl:distinctMember");

                // System.out.println("New edge: " + edge);
            }
        }

    }


    private void parsePredefinedAnnotationPropertyInstance(
            OntResource resource, IMetadataContainer container) {

        // ================================================================
        // * AnnotationProperties
        // ================================================================

        // owl:versionInfo -> String
        ExtendedIterator<String> stringIt = resource.listVersionInfo();
        while (stringIt.hasNext()) {

            container.getMetadataProperty().addMetadata("owl:versionInfo",
                    stringIt.next());
        }

        // rdfs:comment -> Literal
        ExtendedIterator<RDFNode> rdfNodeIt = resource.listComments(null);
        while (rdfNodeIt.hasNext()) {

            RDFNode comment = rdfNodeIt.next();

            if (comment.canAs(Literal.class)) {

                container.getMetadataProperty().addMetadata("rdfs:comment",
                        this.parseLiteral(comment.asLiteral()));
            }
        }

        // rdfs:label -> Literal
        rdfNodeIt = resource.listLabels(null);
        while (rdfNodeIt.hasNext()) {

            RDFNode label = rdfNodeIt.next();

            if (label.canAs(Literal.class)) {

                container.getMetadataProperty().addMetadata("rdfs:label",
                        this.parseLiteral(label.asLiteral()));
            }
        }


        // rdfs:seeAlso -> Resource
        rdfNodeIt = resource.listSeeAlso();
        while (rdfNodeIt.hasNext()) {

            RDFNode seeAlso = rdfNodeIt.next();
            if (seeAlso.canAs(Resource.class)) {

                Resource aResource = seeAlso.asResource();
                container.getMetadataProperty().addMetadata("rdfs:seeAlso",
                        aResource.getURI());
            }
        }


        // rdfs:isDefinedBy -> Resource
        rdfNodeIt = resource.listIsDefinedBy();
        while (rdfNodeIt.hasNext()) {

            RDFNode isDefinedBy = rdfNodeIt.next();

            if (isDefinedBy.canAs(Resource.class)) {

                Resource aResource = isDefinedBy.asResource();
                container.getMetadataProperty().addMetadata("rdfs:isDefinedBy",
                        aResource.getURI());
            }
        }
    }


    private void parsePropertyInstanceForResource(OntProperty property,
            OntResource resource, Universe universe, String definingOntology,
            Node propertyNode, Node resourceNode)
            throws ConversionException {


        NodeIterator valueIt = resource.listPropertyValues(property);

        while (valueIt.hasNext()) {

            RDFNode value = valueIt.next();

            if (value.isLiteral()) {

                if (property.isObjectProperty()) {
                    throw new ConversionException(
                            "Property instance of object property "
                                    + propertyNode.getID() + " is a Literal!");
                }

                String literalString = this.parseLiteral(value.asLiteral());

                // System.out.println("      Value: " + literalString);


                resourceNode.getMetadataProperty().addMetadata(
                        propertyNode.getID(), literalString);


            }
            else if (value.isResource()) {


                if (property.isDatatypeProperty()) {
                    throw new ConversionException(
                            "Property instance of datatype property "
                                    + propertyNode.getID() + " is a Resource!");
                }

                Resource valueResource = value.asResource();

                Node valueNode = this.getNodeForResource(universe,
                        valueResource);

                if (valueNode == null) {
                    valueNode = this.parseResource(valueResource, universe,
                            definingOntology);
                }

                // System.out.println("      Value: " + valueNode.getID());
                //
                // System.out.println("Source: " + resourceNode.getID());
                // System.out.println("Target: " + valueNode.getID());
                // System.out.println("Property: " + propertyNode.getID());
                // System.out.println("Ontology: " + definingOntology);
                String edgeName = "[" + resourceNode.getID()
                        + "] hasPropertyValue [" + valueNode.getID()
                        + "] for property [" + propertyNode.getID() + "]";

                Edge edge = universe.createEdge(edgeName, definingOntology,
                        resourceNode.getID(), valueNode.getID());
                edge.getMetadataProperty().addMetadata("owl:type",
                        propertyNode.getID());
                edge.setDirected();
                // System.out.println("      " + edge);
            }
        }

    }


    private String parseLiteral(Literal literal) {

        String lexicalForm = literal.getLexicalForm();
        if (literal.getLanguage() != null && literal.getLanguage() != "") {
            lexicalForm += "@" + literal.getLanguage();
        }
        if (literal.getDatatypeURI() != null && literal.getDatatypeURI() != "") {

            lexicalForm += "^^" + literal.getDatatypeURI();
        }
        // System.out.println("      Literal Value: " + lexicalForm);
        return lexicalForm;
    }


    private void addOwlThingSuperClass(Node classNode, Universe universe) {


        Node owlThing = universe.getNode("owl:Thing");
        String edgeInfix = "subclass of";
        Edge edge = universe.createEdge("[" + classNode.getID() + "] "
                + edgeInfix + " [" + owlThing.getID() + "]",
                classNode.getGraph(), classNode, owlThing);

        edge.setDirected();
        edge.getMetadataProperty().addMetadata(
                Metadata.createMetadata("owl:type", "rdfs:subClassOf"));

    }

    private void addOwlThingSuperProperty(Node propertyNode, Universe universe) {


        Node owlThing = universe.getNode("owl:Thing");
        String edgeInfix = "subproperty of";
        Edge edge = universe.createEdge("[" + propertyNode.getID() + "] "
                + edgeInfix + " [" + owlThing.getID() + "]",
                propertyNode.getGraph(), propertyNode, owlThing);

        edge.setDirected();
        edge.getMetadataProperty().addMetadata(
                Metadata.createMetadata("owl:type", "rdfs:subPropertyOf"));
    }

    private void addOwlThingClassInstance(Node individualNode, Universe universe) {


        Node owlThing = universe.getNode("owl:Thing");
        String edgeInfix = "instance of";
        Edge edge = universe.createEdge("[" + individualNode.getID() + "] "
                + edgeInfix + " [" + owlThing.getID() + "]",
                individualNode.getGraph(), individualNode, owlThing);

        edge.setDirected();
        edge.getMetadataProperty().addMetadata(
                Metadata.createMetadata("owl:type", "owl:instanceOf"));
    }


    private enum ResourceType {
        CLASS("Class"),
        PROPERTY("Property"),
        DATA_RANGE("DataRange"),
        INDIVIDUAL("Individual");

        private String name;

        private ResourceType(String name) {

            this.name = name;
        }

        @Override
        public String toString() {

            return this.name;
        }
    }

    private enum ClassRelation {
        SUPERCLASS,
        SUBCLASS,
        DISJOINT_CLASS,
        EQUIVALENT_CLASS;
    }

    private enum PropertyRelation {
        SUPERPROPERTY,
        SUBPROPERTY,
        EQUIVALENT_PROPERTY,
        INVERSE_PROPERTY;
    }

    private enum IndividualRelation {
        SAME_INDIVIDUAL,
        DIFFERENT_INDIVIDUAL;
    }

    private enum BooleanClassCombinations {
        UNION_OF,
        INTERSECTION_OF,
        COMPLEMENT_OF;
    }


}
