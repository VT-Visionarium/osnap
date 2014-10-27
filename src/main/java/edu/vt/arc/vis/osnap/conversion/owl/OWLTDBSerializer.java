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


package edu.vt.arc.vis.osnap.conversion.owl;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.jutility.io.ConversionException;
import org.jutility.io.ISerializer;
import org.jutility.io.SerializationException;
import org.jutility.io.xml.XmlSerializer;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.TDBFactory;

import edu.vt.arc.vis.osnap.graph.Universe;


/**
 * The {@link OWLTDBSerializer} class allows for the
 * serialization/deserialization of {@link OntModel OWL Ontologies}.
 *
 * @author Peter J. Radics
 * @version 0.1
 */
public class OWLTDBSerializer
        implements ISerializer {

    private static OWLTDBSerializer s_Instance;

    /**
     * Returns the Singleton instance of the OWLSerializer.
     *
     * @return the Singleton instance.
     */
    public static OWLTDBSerializer Instance() {

        if (s_Instance == null) {
            s_Instance = new OWLTDBSerializer();
        }

        return s_Instance;
    }


    private OWLTDBSerializer() {


    }



    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.conversion.ISerializer#supportsSerializationOf(java.lang
     * .Class)
     */
    @Override
    public boolean supportsSerializationOf(Class<?> type) {

        if (OntModel.class.isAssignableFrom(type)) {

            return true;
        }
        return false;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.conversion.ISerializer#supportsDeserializationOf(java
     * .lang.Class)
     */
    @Override
    public boolean supportsDeserializationOf(Class<?> type) {

        if (type.isAssignableFrom(OntModel.class)) {

            return true;
        }
        return false;
    }



    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.conversion.ISerializer#serialize(java.lang.Object,
     * java.lang.String)
     */
    @Override
    public <T> void serialize(T document, String filename)
            throws SerializationException {

        Class<?> documentType = document.getClass();

        if (!this.supportsSerializationOf(documentType)) {

            throw new SerializationException("Serialization of type "
                    + documentType + " is not supported!");
        }

        OntModel ontology = OntModel.class.cast(document);

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(filename);
        }
        catch (FileNotFoundException e) {

            throw new SerializationException("Could not write to resource!");
        }

        ontology.write(fileOutputStream);
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.conversion.ISerializer#deserialize(java.lang.String,
     * java.lang.Class)
     */
    /**
     * @param filename
     * @param type
     * @return the deserialized file.
     * @throws SerializationException
     */
    public <T> T deserialize(String filename, Class<? extends T> type)
            throws SerializationException {

        if (!this.supportsDeserializationOf(type)) {

            throw new SerializationException("Deserialization of type " + type
                    + " is not supported!");
        }

        T doc = null;
        OntModel deserializedObject = ModelFactory
                .createOntologyModel(OntModelSpec.OWL_MEM);

        deserializedObject.read(filename);

        if (deserializedObject != null) {
            doc = type.cast(deserializedObject);
        }

        return doc;
    }


    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.conversion.ISerializer#deserialize(java.net.URL,
     * java.lang.Class)
     */
    @Override
    public <T> T deserialize(URL url, Class<? extends T> type)
            throws SerializationException {

        return this.deserialize(url.toString(), type);
    }


    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.conversion.ISerializer#deserialize(java.io.File,
     * java.lang.Class)
     */
    @Override
    public <T> T deserialize(File file, Class<? extends T> type)
            throws SerializationException {

        return this.deserialize(file.toURI(), type);
    }


    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.conversion.ISerializer#deserialize(java.net.URI,
     * java.lang.Class)
     */
    @Override
    public <T> T deserialize(URI uri, Class<? extends T> type)
            throws SerializationException {

        try {
            return this.deserialize(uri.toURL(), type);
        }
        catch (MalformedURLException e) {
            throw new SerializationException("URI " + uri + " is malformed.", e);
        }
    }

    /**
     * runs the de-serializer
     * 
     * @param argv
     *            command line args
     */
    public static void main(String[] argv) {

        // Make a TDB-backed dataset
        String directory = "ontologies/SUMO";
        Dataset dataset = TDBFactory.createDataset(directory);



        dataset.begin(ReadWrite.READ);
        // Get model inside the transaction
        Model model = dataset.getDefaultModel();

        dataset.end();

        OntModel ontModel = ModelFactory.createOntologyModel(
                OntModelSpec.OWL_DL_MEM_RULE_INF, model);

        try {
            Universe universe = OWLConverter.Instance().convert(ontModel,
                    Universe.class);


            XmlSerializer.Instance().serialize(universe, "WordNet.graphViz");
        }
        catch (ConversionException e) {
            e.printStackTrace();
        }
        catch (SerializationException e) {
            e.printStackTrace();
        }


    }
}
