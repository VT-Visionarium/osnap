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


package edu.vt.arc.vis.osnap.javafx.stringconverters;


import org.jutility.javafx.stringconverter.IConfigurableStringConverter;
import org.jutility.javafx.stringconverter.IStringConverterConfiguration;

import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObjectBasedValueTypeContainer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.StringConverter;


/**
 * The {@link GraphObjectBasedValueTypeContainerStringConverter} class provides
 * a string converter for
 * {@link GraphObjectBasedValueTypeContainerStringConverter
 * DatabaseObjectStringConverters}.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * @param <T>
 *            the type of the StringConverter (subclass of {@link IGraphObject}
 *            ).
 */
public class GraphObjectBasedValueTypeContainerStringConverter<T extends IGraphObjectBasedValueTypeContainer>
        extends StringConverter<T>
        implements IConfigurableStringConverter<T> {

    private final ObjectProperty<GraphObjectBasedValueTypeContainerStringConverterConfiguration> configuration;



    /**
     * Creates a new instance of the
     * {@link GraphObjectBasedValueTypeContainerStringConverter} class with the
     * default configuration.
     */
    public GraphObjectBasedValueTypeContainerStringConverter() {

        this(GraphObjectBasedValueTypeContainerStringConverterConfiguration.DEFAULT);
    }

    /**
     * Creates a new instance of the
     * {@link GraphObjectBasedValueTypeContainerStringConverter} class with the
     * provided configuration.
     * 
     * @param configuration
     *            the configuration to use.
     */
    public GraphObjectBasedValueTypeContainerStringConverter(
            GraphObjectBasedValueTypeContainerStringConverterConfiguration configuration) {

        this.configuration = new SimpleObjectProperty<>(
                configuration);
    }

    @Override
    public ObjectProperty<GraphObjectBasedValueTypeContainerStringConverterConfiguration> configuration() {

        return this.configuration;
    }

    @Override
    public void configure(IStringConverterConfiguration configuration) {

        if (configuration instanceof GraphObjectBasedValueTypeContainerStringConverterConfiguration) {

            this.configuration
                    .set((GraphObjectBasedValueTypeContainerStringConverterConfiguration) configuration);
        }
    }

    @Override
    public GraphObjectBasedValueTypeContainerStringConverterConfiguration getConfiguration() {

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
                case KEY:

                    return item.getKey();
            }
        }
    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null
                && obj instanceof GraphObjectBasedValueTypeContainerStringConverter<?>) {

            GraphObjectBasedValueTypeContainerStringConverter<?> other = (GraphObjectBasedValueTypeContainerStringConverter<?>) obj;

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
