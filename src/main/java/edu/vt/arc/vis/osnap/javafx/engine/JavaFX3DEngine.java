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


package edu.vt.arc.vis.osnap.javafx.engine;



import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;
import edu.vt.arc.vis.osnap.io.x3d.X3DEngine;
import javafx.scene.layout.Pane;

import org.jutility.io.ConversionException;
import org.jutility.io.IConverter;


/**
 * The {@link JavaFX3DEngine} class converts a {@link Visualization
 * visualizations} to a JavaFX 3D scene.
 * 
 * @author William H. Lund, Peter J. Radics
 * @version 1.1
 */
public class JavaFX3DEngine
        implements IConverter {

    private static JavaFX3DEngine s_Instance;


    /**
     * Returns the singleton instance of the {@link X3DEngine} class;
     * 
     * @return the singleton instance.
     */
    public static JavaFX3DEngine Instance() {

        if (JavaFX3DEngine.s_Instance == null) {

            JavaFX3DEngine.s_Instance = new JavaFX3DEngine();
        }

        return JavaFX3DEngine.s_Instance;
    }

    @Override
    public boolean supportsConversion(Class<?> sourceType, Class<?> targetType) {

        // lower bound required for source type.
        if (!Visualization.class.isAssignableFrom(sourceType)) {

            return false;
        }

        // upper bound required for target type.
        if (!targetType.isAssignableFrom(Pane.class)) {

            return false;
        }

        return true;
    }



    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.io.IConverter#convert(java.lang.Object,
     * java.lang.Class)
     */
    @Override
    public <T, S> S convert(T documentToConvert, Class<? extends S> returnType)
            throws ConversionException {


        Class<?> documentType = documentToConvert.getClass();
        if (!this.supportsConversion(documentType, returnType)) {

            throw new ConversionException("Conversion from " + documentType
                    + " to " + returnType + " is not supported by "
                    + this.getClass().toString() + "!");
        }

        return returnType.cast(this.convert(Visualization.class
                .cast(documentToConvert)));
    }



    private Pane convert(Visualization visualization)
            throws ConversionException {

        return new PreviewPane(visualization);
    }

}
