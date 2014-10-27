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


package edu.vt.arc.vis.osnap.javafx.stringConverters;


import org.jutility.javafx.stringconverter.IConfigurableStringConverter;
import org.jutility.javafx.stringconverter.IStringConverterConfiguration;

import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.visualization.IVisualGraphObject;
import edu.vt.arc.vis.osnap.core.domain.visualization.Label;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualObject;



/**
 * The {@link VisualGraphObjectStringConverterConfiguration} provides configuration
 * options for {@link IConfigurableStringConverter StringConverters} that
 * support {@link IVisualGraphObject VisualGraphObjects}.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public class VisualGraphObjectStringConverterConfiguration
        implements IStringConverterConfiguration {


    /**
     * The default configuration.
     */
    public static final VisualGraphObjectStringConverterConfiguration DEFAULT = new VisualGraphObjectStringConverterConfiguration();
    /**
     * Configuration using the ID of the {@link VisualObject}.
     */
    public static final VisualGraphObjectStringConverterConfiguration ID      = new VisualGraphObjectStringConverterConfiguration(
                                                                                 OutputField.ID);
    /**
     * Configuration using the graph object ID of the {@link VisualObject}.
     */
    public static final VisualGraphObjectStringConverterConfiguration GRAPH_OBJECT_ID      = new VisualGraphObjectStringConverterConfiguration(
                                                                                 OutputField.GRAPH_OBJECT_ID);
    /**
     * Configuration using the graph object ID of the {@link VisualObject}.
     */
    public static final VisualGraphObjectStringConverterConfiguration LABEL      = new VisualGraphObjectStringConverterConfiguration(
                                                                                 OutputField.LABEL);

    private final OutputField                                    output;



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
     * {@link VisualGraphObjectStringConverterConfiguration} class using the default
     * output field.
     */
    public VisualGraphObjectStringConverterConfiguration() {

        this(OutputField.ID);
    }

    /**
     * Creates a new instance of the
     * {@link VisualGraphObjectStringConverterConfiguration} class using the provided
     * output field.
     * 
     * @param output
     *            the output field.
     */
    public VisualGraphObjectStringConverterConfiguration(OutputField output) {

        this.output = output;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null
                && obj instanceof VisualGraphObjectStringConverterConfiguration) {

            VisualGraphObjectStringConverterConfiguration other = (VisualGraphObjectStringConverterConfiguration) obj;

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
     * fields of {@link IVisualGraphObject VisualObjects}.
     * 
     * @author Peter J. Radics
     * @version 0.1
     * 
     */
    public enum OutputField {

        /**
         * Specifies the option to use the ID of the {@link VisualObject}.
         */
        ID,
        /**
         * Specifies the option to use the ID of the {@link IGraphObject}.
         */
        GRAPH_OBJECT_ID,
        /**
         * Specifies the option to use the {@link Label} of the {@link VisualObject}.
         */
        LABEL,
        /**
         * Specifies the option to use the name of the {@link VisualObject}.
         */
        TO_STRING;
    }
}
