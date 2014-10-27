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


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.jutility.io.ISerializer;
import org.jutility.io.SerializationException;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;


/**
 * The {@link OWLSerializer} class allows for the serialization/deserialization
 * of {@link OntModel OWL Ontologies}.
 *
 * @author Peter J. Radics
 * @version 0.1
 */
public class OWLSerializer
        implements ISerializer {

    private static OWLSerializer s_Instance;

    /**
     * Returns the Singleton instance of the OWLSerializer.
     *
     * @return the Singleton instnace.
     */
    public static OWLSerializer Instance() {

        if (s_Instance == null) {

            s_Instance = new OWLSerializer();
        }

        return s_Instance;
    }


    private OWLSerializer() {


    }



    /*
     * (non-Javadoc)
     *
     * @see
     * graphVisualizer.conversion.ISerializer#supportsSerializationOf(java.lang
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
     * graphVisualizer.conversion.ISerializer#supportsDeserializationOf(java
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
     * @see graphVisualizer.conversion.ISerializer#serialize(java.lang.Object,
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
     * @see graphVisualizer.conversion.ISerializer#deserialize(java.lang.String,
     * java.lang.Class)
     */
//    @Override
    /**
     * @param filename
     * @param type
     * @return de-serialized object
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
     * @see graphVisualizer.conversion.ISerializer#deserialize(java.net.URL,
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
     * @see graphVisualizer.conversion.ISerializer#deserialize(java.io.File,
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
     * @see graphVisualizer.conversion.ISerializer#deserialize(java.net.URI,
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
}
