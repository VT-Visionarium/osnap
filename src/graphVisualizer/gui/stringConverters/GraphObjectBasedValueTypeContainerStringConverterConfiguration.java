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


package graphVisualizer.gui.stringConverters;


import org.jutility.javafx.stringconverter.IConfigurableStringConverter;
import org.jutility.javafx.stringconverter.IStringConverterConfiguration;

import graphVisualizer.graph.common.IGraphObject;



/**
 * The {@link GraphObjectBasedValueTypeContainerStringConverterConfiguration}
 * provides configuration options for {@link IConfigurableStringConverter
 * StringConverters} that support {@link IGraphObject GraphObjects}.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public class GraphObjectBasedValueTypeContainerStringConverterConfiguration
        implements IStringConverterConfiguration {


    /**
     * The default configuration.
     */
    public static final GraphObjectBasedValueTypeContainerStringConverterConfiguration DEFAULT = new GraphObjectBasedValueTypeContainerStringConverterConfiguration();
    /**
     * Configuration using the ID of the {@link IGraphObject}.
     */
    public static final GraphObjectBasedValueTypeContainerStringConverterConfiguration KEY     = new GraphObjectBasedValueTypeContainerStringConverterConfiguration(
                                                                                                       OutputField.KEY);
    /**
     * Configuration using the ID of the {@link IGraphObject}.
     */
    public static final GraphObjectBasedValueTypeContainerStringConverterConfiguration TO_STRING     = new GraphObjectBasedValueTypeContainerStringConverterConfiguration(
                                                                                                       OutputField.TO_STRING);

    private final OutputField                                                          output;



    /**
     * Returns the output field.
     * 
     * @return the output field.
     */
    public OutputField getOutput() {

        return output;
    }

    /**
     * Creates a new instance of the
     * {@link GraphObjectBasedValueTypeContainerStringConverterConfiguration}
     * class using the default output field.
     */
    public GraphObjectBasedValueTypeContainerStringConverterConfiguration() {

        this(OutputField.KEY);
    }

    /**
     * Creates a new instance of the
     * {@link GraphObjectBasedValueTypeContainerStringConverterConfiguration}
     * class using the provided output field.
     * 
     * @param output
     *            the output field.
     */
    public GraphObjectBasedValueTypeContainerStringConverterConfiguration(
            OutputField output) {

        this.output = output;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null
                && obj instanceof GraphObjectBasedValueTypeContainerStringConverterConfiguration) {

            GraphObjectBasedValueTypeContainerStringConverterConfiguration other = (GraphObjectBasedValueTypeContainerStringConverterConfiguration) obj;

            return this.getOutput() == other.getOutput();
        }
        return false;
    }

    @Override
    public int hashCode() {

        return this.getOutput().hashCode();
    }



    /**
     * The {@link OutputField} enum enumerates the different possible output
     * fields of {@link IGraphObject GraphObjects}.
     * 
     * @author Peter J. Radics
     * @version 0.1
     * 
     */
    public enum OutputField {

        /**
         * Specifies the option to use the key of the {@link IGraphObject}.
         */
        KEY,
        /**
         * Specifies the option to use the name of the {@link IGraphObject}.
         */
        TO_STRING;
    }
}
