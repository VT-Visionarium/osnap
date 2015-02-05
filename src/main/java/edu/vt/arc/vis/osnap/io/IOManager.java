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


package edu.vt.arc.vis.osnap.io;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import javafx.concurrent.Task;

import com.hp.hpl.jena.ontology.OntModel;

import edu.vt.arc.vis.osnap.core.domain.Project;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.graph.common.GraphObjectProperty;
import edu.vt.arc.vis.osnap.core.domain.layout.LayoutSet;
import edu.vt.arc.vis.osnap.core.domain.layout.LayoutRegistry;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;
import edu.vt.arc.vis.osnap.io.graphML.GraphMLConverter;
import edu.vt.arc.vis.osnap.io.owl.OWLConverter;
import edu.vt.arc.vis.osnap.io.owl.OWLSerializer;
import edu.vt.arc.vis.osnap.io.x3d.X3DEngine;

import org.controlsfx.dialog.Dialogs;
import org.graphdrawing.graphml.GraphMLDocument;
import org.jutility.common.datatype.map.KeyValuePair;
import org.jutility.io.ConversionException;
import org.jutility.io.IConverter;
import org.jutility.io.ISerializer;
import org.jutility.io.SerializationException;
import org.jutility.io.xml.XmlSerializer;


/**
 * 
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public class IOManager
        implements IConverter, ISerializer {

    private static IOManager                                        s_Instance;

    private final Map<KeyValuePair<Class<?>, Class<?>>, IConverter> converterForType;
    private final Map<Class<?>, ISerializer>                        serializerForType;
    private final Map<Class<?>, ISerializer>                        deserializerForType;


    /**
     * Returns the Singleton instance of the OWLSerializer.
     * 
     * @return the Singleton instance.
     */
    public static IOManager Instance() {

        if (s_Instance == null) {

            s_Instance = new IOManager();
        }

        return s_Instance;
    }

    /**
     * Creates a new instance of the {@link IOManager} class.
     */
    private IOManager() {

        this.converterForType = new LinkedHashMap<>();
        this.deserializerForType = new LinkedHashMap<>();
        this.serializerForType = new LinkedHashMap<>();

        LayoutRegistry.Instance();
        XmlSerializer.Instance().registerClass(Project.class);
        XmlSerializer.Instance().registerClass(GraphObjectProperty.class);
        XmlSerializer.Instance().registerClass(LayoutSet.class);
    }

    @Override
    public boolean supportsConversion(Class<?> sourceType, Class<?> targetType) {

        KeyValuePair<Class<?>, Class<?>> types = new KeyValuePair<>(sourceType,
                targetType);
        if (GraphMLConverter.Instance().supportsConversion(sourceType,
                targetType)) {

            // System.out.println("Conversion supported by GraphMLConverter!");
            this.converterForType.put(types, GraphMLConverter.Instance());
            return true;
        }

        if (X3DEngine.Instance().supportsConversion(sourceType, targetType)) {
            // System.out.println("Conversion supported by X3DEngine!");
            this.converterForType.put(types, X3DEngine.Instance());
            return true;
        }

        if (OWLConverter.Instance().supportsConversion(sourceType, targetType)) {
            // System.out.println("Conversion supported by OWLConverter!");
            this.converterForType.put(types, OWLConverter.Instance());
            return true;
        }


        return false;
    }

    @Override
    public <T, S> S convert(T documentToConvert, Class<? extends S> returnType)
            throws ConversionException {

        Class<?> sourceType = documentToConvert.getClass();

        if (this.supportsConversion(sourceType, returnType)) {

            IConverter converter = this.converterForType
                    .get(new KeyValuePair<Class<?>, Class<?>>(sourceType,
                            returnType));

            if (converter != null) {

                return converter.convert(documentToConvert, returnType);
            }

        }

        throw new ConversionException("Conversion of document from "
                + sourceType + " to " + returnType + " is not supported!");

    }

    @Override
    public boolean supportsSerializationOf(Class<?> type) {

        if (XmlSerializer.Instance().supportsSerializationOf(type)) {

            // System.out.println("Serialization supported by XMLSerializer!");
            this.serializerForType.put(type, XmlSerializer.Instance());
            return true;
        }

        if (OWLSerializer.Instance().supportsSerializationOf(type)) {

            // System.out.println("Serialization supported by OWLSerializer!");
            this.serializerForType.put(type, OWLSerializer.Instance());
            return true;
        }


        return false;
    }

    @Override
    public boolean supportsDeserializationOf(Class<?> type) {

        if (XmlSerializer.Instance().supportsDeserializationOf(type)) {

            // System.out.println("DeSerialization supported by XMLSerializer!");
            this.deserializerForType.put(type, XmlSerializer.Instance());
            return true;
        }

        if (OWLSerializer.Instance().supportsDeserializationOf(type)) {

            // System.out.println("DeSerialization supported by XMLSerializer!");
            this.deserializerForType.put(type, OWLSerializer.Instance());
            return true;
        }

        return false;
    }

    @Override
    public <T> void serialize(T document, String filename)
            throws SerializationException {

        Class<?> documentType = document.getClass();

        boolean success = false;

        if (this.supportsSerializationOf(documentType)) {

            ISerializer serializer = this.serializerForType.get(documentType);

            if (serializer != null) {

                serializer.serialize(document, filename);
                success = true;
            }

        }
        if (!success) {

            throw new SerializationException(
                    "Serialization of document of type " + documentType
                            + " is not supported!");
        }
    }



    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.io.ISerializer#deserialize(java.io.File,
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
     * @see edu.vt.arc.vis.osnap.io.ISerializer#deserialize(java.net.URI,
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

    @Override
    public <T> T deserialize(URL url, Class<? extends T> type)
            throws SerializationException {

        if (this.supportsDeserializationOf(type)) {

            ISerializer serializer = this.deserializerForType.get(type);

            if (serializer != null) {

                return serializer.deserialize(url, type);
            }

        }

        throw new SerializationException("Deserialization of document of type "
                + type + " is not supported!");
    }

    /**
     * Probes the file provided for its content type.
     * 
     * @param file
     *            the File to check.
     * @return the content type.
     * @throws SerializationException
     *             if the content type was not recognized.
     */
    public Class<?> probe(File file)
            throws SerializationException {

        return this.probe(file.toURI());
    }

    /**
     * Probes the uri provided for its content type.
     * 
     * @param uri
     *            the URI to check.
     * @return the content type.
     * @throws SerializationException
     *             if the content type was not recognized.
     */
    public Class<?> probe(URI uri)
            throws SerializationException {

        try {
            return this.probe(uri.toURL());
        }
        catch (MalformedURLException e) {
            throw new SerializationException("URI " + uri + " is malformed.", e);
        }
    }


    /**
     * Probes the url provided for its content type.
     * 
     * @param url
     *            the URL to check.
     * @return the content type.
     * @throws SerializationException
     *             if the content type was not recognized.
     */
    public Class<?> probe(URL url)
            throws SerializationException {

        try {

            XmlSerializer.Instance().deserialize(url, GraphMLDocument.class);
            return GraphMLDocument.class;
        }
        catch (Exception e) {
            // e.printStackTrace();
            // Nothing to be done
        }
        try {

            XmlSerializer.Instance().deserialize(url, Project.class);
            return Project.class;
        }
        catch (Exception e) {
            // e.printStackTrace();
            // Nothing to be done
        }
        try {

            XmlSerializer.Instance().deserialize(url, Universe.class);
            return Universe.class;
        }
        catch (Exception e) {
            // e.printStackTrace();
            // Nothing to be done
        }
        try {

            XmlSerializer.Instance().deserialize(url, LayoutSet.class);
            return LayoutSet.class;
        }
        catch (Exception e) {
            // Nothing to be done
        }
        try {

            XmlSerializer.Instance().deserialize(url, Visualization.class);
            return Visualization.class;
        }
        catch (Exception e) {
            // Nothing to be done
        }
        try {

            OWLSerializer.Instance().deserialize(url, OntModel.class);
            return OntModel.class;
        }
        catch (Exception e) {
            // Nothing to be done
        }


        throw new SerializationException("Did not recognize content type of "
                + url + "!");
    }

    /**
     * Attempts to open the provided URI and deserialize an object of the
     * provided type.
     * 
     * @param uri
     *            the URI to open.
     * @param returnType
     *            the desired return type.
     * @return the deserialized object.
     */
    public <S> S open(URI uri, Class<? extends S> returnType) {

        Class<?> contentType = null;
        try {

            contentType = IOManager.Instance().probe(uri);
        }
        catch (SerializationException e) {

            Dialogs.create().title("Could Not Recognize Content Type!")
                    .showException(e);
            return null;
        }

        Object deserializedObject = null;
        try {
            deserializedObject = IOManager.Instance().deserialize(uri,
                    contentType);
        }
        catch (SerializationException e) {

            Dialogs.create().title("Could Not Deserialize Object!")
                    .showException(e);
            return null;
        }

        // System.out.println("deserializedObject: " + deserializedObject);

        if (returnType.isAssignableFrom(deserializedObject.getClass())) {

            return returnType.cast(deserializedObject);
        }
        try {

            return IOManager.Instance().convert(deserializedObject, returnType);
        }
        catch (ConversionException e) {

            Dialogs.create().title("Could Not Convert Object!")
                    .showException(e);
            return null;
        }
    }

    /**
     * Attempts to save the provided object as a file of the provided type.
     * 
     * @param file
     *            the file to save to.
     * @param objectToSave
     *            the object to save.
     * @param targetType
     *            the desired file type.
     * @return {@code true}, if saving the object was successful; {@code false}
     *         otherwise.
     */
    public <S, T> boolean save(File file, S objectToSave,
            Class<? extends T> targetType) {

        boolean success = false;

        Class<?> contentType = objectToSave.getClass();

        T serializableObject = null;

        if (targetType.isAssignableFrom(contentType)) {

            serializableObject = targetType.cast(objectToSave);
        }
        else {

            try {

                serializableObject = IOManager.Instance().convert(objectToSave,
                        targetType);
            }
            catch (ConversionException e) {

                Dialogs.create().title("Could Not Convert Object!")
                        .showException(e);
            }
        }

        if (serializableObject != null) {

            try {

                IOManager.Instance().serialize(serializableObject,
                        file.getAbsolutePath());
                success = true;
                Dialogs.create()
                        .title("File Saved Successfully!")
                        .message(
                                "File was successfully saved to "
                                        + file.getAbsolutePath() + "!")
                        .showInformation();
            }
            catch (SerializationException e) {

                Dialogs.create()
                        .title("Could Not Serialize Object to "
                                + file.getAbsolutePath() + "!")
                        .showException(e);
            }
        }
        else {

            Dialogs.create()
                    .title("Could Not Serialize Object to "
                            + file.getAbsolutePath()
                            + "! Conversion resulted in null object!")
                    .showError();
        }
        return success;
    }


    /**
     * @param uri
     * @param returnType
     * @return the task.
     */
    public <T> Task<T> openTask(URI uri, Class<? extends T> returnType) {

        Task<T> task = new Task<T>() {

            @Override
            protected T call()
                    throws Exception {

                int iterations;
                for (iterations = 0; iterations < 10000000; iterations++) {
                    if (isCancelled()) {
                        updateMessage("Cancelled");
                        break;
                    }
                    // System.out.println("Iterations: " + iterations);
                    updateMessage("Iteration " + iterations);
                    updateProgress(iterations, 10000000);
                }
                return null;
            }
        };

        return task;
    }
}
