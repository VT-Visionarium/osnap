package edu.vt.arc.vis.osnap.javafx.wizards.configurations;


import org.jutility.math.geometry.IScale;
import org.jutility.math.geometry.Scale;

import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleScaleLayout;


// @formatter:off
/*
 * _
 * The Open Semantic Network Analysis Platform (OSNAP)
 * _
 * Copyright (C) 2012 - 2015 Visionarium at Virginia Tech
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

/**
 * The {@code SimpleScaleLayoutConfiguration} class provides the
 * {@link IConfiguration Configuration} for the
 * {@link SimpleScaleLayout}.
 * 
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class SimpleScaleLayoutConfiguration
        extends LayoutConfiguration<SimpleScaleLayout> {

    private IScale<Float> scale;


    /**
     * Sets the uniform {@link IScale Scale} assigned to the restrictions.
     * 
     * @param scale
     *            the uniform scale assigned to the restrictions.
     */
    public void setUniformScale(final float scale) {

        this.scale = new Scale<>(scale, scale, scale, Float.class);
    }

    /**
     * Sets the {@link IScale Scale} assigned to the restrictions.
     * 
     * @param scaleX
     *            the scale in x-direction.
     * @param scaleY
     *            the scale in y-direction.
     * @param scaleZ
     *            the scale in z-direction.
     */
    public void setScale(final float scaleX, final float scaleY,
            final float scaleZ) {

        this.scale = new Scale<>(scaleX, scaleY, scaleZ, Float.class);
    }

    /**
     * Returns the {@link IScale Scale} assigned to the restrictions.
     * 
     * @return the {@link IScale Scale} assigned to the restrictions.
     */
    public IScale<Float> getScale() {

        return this.scale;
    }

    /**
     * Creates a new instance of the {@code ScaleStatus} class.
     */
    public SimpleScaleLayoutConfiguration() {

        super(SimpleScaleLayout.name(), SimpleScaleLayout
                .description());

        this.scale = new Scale<>(Float.class);
    }

    @Override
    public SimpleScaleLayout createConfiguredObject() {

        return new SimpleScaleLayout(this.getScale());
    }
}
