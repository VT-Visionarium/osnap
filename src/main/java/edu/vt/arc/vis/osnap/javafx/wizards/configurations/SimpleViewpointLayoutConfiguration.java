package edu.vt.arc.vis.osnap.javafx.wizards.configurations;


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

import org.jutility.math.vectoralgebra.Vector4;

import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleViewpointLayout;


/**
 * The {@code SimpleViewPointStatusLayoutComponentConfiguration} class provides
 * the {@link IConfiguration Configuration} for the
 * {@link SimpleViewpointLayout}.
 * 
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.3
 * @since 0.5.0
 */
public class SimpleViewpointLayoutConfiguration
        extends
        LayoutConfiguration<SimpleViewpointLayout> {

    private float offsetX = 0f;
    private float offsetY = 0f;
    private float offsetZ = 20f;



    /**
     * Returns the offset in x direction.
     * 
     * @return the offset in x direction.
     */
    public float getOffsetX() {

        return this.offsetX;
    }

    /**
     * Sets the offset in x direction.
     * 
     * @param offsetX
     *            the offset in x direction.
     */
    public void setOffsetX(final float offsetX) {

        this.offsetX = offsetX;
    }


    /**
     * Returns the offset in y direction.
     * 
     * @return the offset in y direction.
     */
    public float getOffsetY() {

        return this.offsetY;
    }

    /**
     * Sets the offset in y direction.
     * 
     * @param offsetY
     *            the offset in y direction.
     */
    public void setOffsetY(final float offsetY) {

        this.offsetY = offsetY;
    }


    /**
     * Returns the offset in z direction.
     * 
     * @return the offset in z direction.
     */
    public float getOffsetZ() {

        return this.offsetZ;
    }

    /**
     * Sets the offset in z direction.
     * 
     * @param offsetZ
     *            the offset in z direction.
     */
    public void setOffsetZ(final float offsetZ) {

        this.offsetZ = offsetZ;
    }



    /**
     * Sets the offset.
     * 
     * @param offsetX
     *            the offset in x direction.
     * @param offsetY
     *            the offset in y direction.
     * @param offsetZ
     *            the offset in z direction.
     */
    public void setOffset(final float offsetX, final float offsetY,
            final float offsetZ) {

        this.setOffsetX(offsetX);
        this.setOffsetY(offsetY);
        this.setOffsetZ(offsetZ);
    }

    /**
     * Creates a new instance of the {@code ViewPointStatusObject} class.
     */
    public SimpleViewpointLayoutConfiguration() {

        super(SimpleViewpointLayout.name(),
                SimpleViewpointLayout.description());
    }



    @Override
    public SimpleViewpointLayout createConfiguredObject() {

        return new SimpleViewpointLayout(new Vector4<>(
                this.getOffsetX(), this.getOffsetY(), this.getOffsetZ(),
                Float.class));
    }

}
