package edu.vt.arc.vis.osnap.javafx.wizards.configurations;


// @formatter:off
/*
 * _
 * The Open Semantic Network Analysis Platform (OSNAP)
 * _
 * Copyright (C) 2012 - 2014 Visionarium at Virginia Tech
 * _
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * _
 */
// @formatter:on

import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleShapeLayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.Shape;



/**
 * The {@code SimpleShapeLayoutConfiguration} class provides the
 * {@link IConfiguration Configuration} for the
 * {@link SimpleShapeLayout}.
 * 
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.3
 * @since 0.5.0
 */
public class SimpleShapeLayoutConfiguration
        extends LayoutConfiguration<SimpleShapeLayout> {

    private Shape shape;


    /**
     * Returns the {@link Shape} assigned to the restriction.
     * 
     * @return the {@link Shape} assigned to the restriction.
     */
    public Shape getShape() {

        return this.shape;
    }

    /**
     * Sets the {@link Shape} assigned to the restriction.
     * 
     * @param shape
     *            the {@link Shape} assigned to the restriction.
     */
    public void setShape(Shape shape) {

        this.shape = shape;
    }



    /**
     * Creates a new instance of the {@code ShapeStatus} class.
     */
    public SimpleShapeLayoutConfiguration() {

        super(SimpleShapeLayout.name(), SimpleShapeLayout
                .description());

        this.shape = Shape.CUBE;
    }



    @Override
    public SimpleShapeLayout createConfiguredObject() {

        return new SimpleShapeLayout(this.getShape());
    }

}
