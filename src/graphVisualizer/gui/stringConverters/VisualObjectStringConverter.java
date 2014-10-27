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


import graphVisualizer.visualization.VisualObject;


/**
 * The {@link VisualObjectStringConverter} class provides a string converter for
 * {@link VisualObject VisualObjects}.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * @param <T>
 *            the type of the StringConverter (subclass of {@link VisualObject}
 *            ).
 */
public class VisualObjectStringConverter<T extends VisualObject>
        extends VisualGraphObjectStringConverter<T> {



    /**
     * Creates a new instance of the {@link VisualObjectStringConverter} class
     * with the default configuration.
     */
    public VisualObjectStringConverter() {

        super();
    }

    /**
     * Creates a new instance of the {@link VisualObjectStringConverter} class
     * with the provided configuration.
     * 
     * @param configuration
     *            the configuration to use.
     */
    public VisualObjectStringConverter(
            VisualGraphObjectStringConverterConfiguration configuration) {

        super(configuration);
    }



    @Override
    public String toString(T item) {

        if (item == null) {
            return "";
        }
        else {
            switch (this.getConfiguration().getOutput()) {
                case LABEL:

                    if (item.getLabel() == null) {

                        return "Unlabeled " + item.getClass().getSimpleName();
                    }

                    return item.getLabel().getText();
                default:

                    return super.toString(item);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {

        return obj instanceof VisualObjectStringConverter<?>
                && super.equals(obj);
    }
}
