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


package edu.vt.arc.vis.osnap.gui.stringConverters;


import org.jutility.javafx.stringconverter.IConfigurableStringConverter;
import org.jutility.javafx.stringconverter.IStringConverterConfiguration;

import edu.vt.arc.vis.osnap.graph.common.IGraphObject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.StringConverter;


/**
 * The {@link GraphObjectStringConverter} class provides a string converter
 * for {@link GraphObjectStringConverter DatabaseObjectStringConverters}.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * @param <T>
 *            the type of the StringConverter (subclass of {@link IGraphObject}
 *            ).
 */
public class GraphObjectStringConverter<T extends IGraphObject>
        extends StringConverter<T>
        implements IConfigurableStringConverter<T> {

    private final ObjectProperty<GraphObjectStringConverterConfiguration> configuration;



    /**
     * Creates a new instance of the {@link GraphObjectStringConverter} class
     * with the default configuration.
     */
    public GraphObjectStringConverter() {

        this(GraphObjectStringConverterConfiguration.DEFAULT);
    }

    /**
     * Creates a new instance of the {@link GraphObjectStringConverter} class
     * with the provided configuration.
     * 
     * @param configuration
     *            the configuration to use.
     */
    public GraphObjectStringConverter(
            GraphObjectStringConverterConfiguration configuration) {

        this.configuration = new SimpleObjectProperty<>(
                configuration);
    }

    @Override
    public ObjectProperty<GraphObjectStringConverterConfiguration> configuration() {

        return this.configuration;
    }

    @Override
    public void configure(IStringConverterConfiguration configuration) {

        if (configuration instanceof GraphObjectStringConverterConfiguration) {

            this.configuration
                    .set((GraphObjectStringConverterConfiguration) configuration);
        }
    }

    @Override
    public GraphObjectStringConverterConfiguration getConfiguration() {

        return this.configuration.get();
    }



    @Override
    public String toString(T item) {

        if (item == null) {
            return "";
        }
        else {
            switch (this.configuration.get().getOutput()) {
                case TO_STRING:

                    return item.toString();

                default:
                case ID:

                    return item.getID();
            }
        }
    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof GraphObjectStringConverter<?>) {

            GraphObjectStringConverter<?> other = (GraphObjectStringConverter<?>) obj;

            boolean sameConfiguration = this.getConfiguration().equals(
                    other.getConfiguration());

            return sameConfiguration;

        }
        return false;
    }

    @Override
    public int hashCode() {

        int hash = 7;

        return hash *= this.configuration.hashCode();
    }

    @Override
    public T fromString(String stringRepresentation) {

        throw new UnsupportedOperationException(
                "Cannot create graph object from string!");
    }
}
