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
//@formatter:on

import edu.vt.arc.vis.osnap.core.domain.layout.common.CoordinateComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ICoordinateLayout;


/**
 * The abstract {@code CoordinateLayoutConfiguration} class provides the minimum
 * {@link ICoordinateLayoutConfiguration} for {@link ICoordinateLayout t
 * CoordinateLayoutComponents}.
 *
 * @param <O>
 *            the type of the {@link ICoordinateLayout}.
 * @author Shawn P Neuman
 * @version 1.2.0
 * @since 0.5.0
 */
public abstract class CoordinateLayoutConfiguration<O extends ICoordinateLayout>
        extends LayoutConfiguration<O>
        implements ICoordinateLayoutConfiguration<O> {

    private CoordinateComponent xOutput;
    private CoordinateComponent yOutput;
    private CoordinateComponent zOutput;

    private float               xOutputScale;
    private float               yOutputScale;
    private float               zOutputScale;



    @Override
    public CoordinateComponent getXOutput() {

        return this.xOutput;
    }

    @Override
    public void setXOutput(final CoordinateComponent component) {

        if (component == null) {

            this.xOutput = CoordinateComponent.NO_COMPONENT;
        }
        else {

            this.xOutput = component;
        }
    }


    @Override
    public CoordinateComponent getYOutput() {

        return this.yOutput;
    }

    @Override
    public void setYOutput(final CoordinateComponent component) {

        if (component == null) {

            this.yOutput = CoordinateComponent.NO_COMPONENT;
        }
        else {

            this.yOutput = component;
        }
    }


    @Override
    public CoordinateComponent getZOutput() {

        return this.zOutput;
    }

    @Override
    public void setZOutput(final CoordinateComponent component) {

        if (component == null) {

            this.zOutput = CoordinateComponent.NO_COMPONENT;
        }
        else {

            this.zOutput = component;
        }
    }


    @Override
    public float getFirstComponentScale() {

        return this.xOutputScale;
    }

    @Override
    public void setFirstComponentScale(float value) {

        this.xOutputScale = value;
    }


    @Override
    public float getSecondComponentScale() {

        return this.yOutputScale;
    }

    @Override
    public void setSecondComponentScale(float value) {

        this.yOutputScale = value;
    }


    @Override
    public float getThirdComponentScale() {

        return this.zOutputScale;
    }

    public void setThirdComponentScale(float value) {

        this.zOutputScale = value;
    }



    /**
     * Creates a new instance of the {@code CoordinateLayoutConfiguration} class
     * with the provided defaults for name and description.
     * 
     * @param defaultName
     *            the default name.
     * @param defaultDescription
     *            the default description.
     */
    public CoordinateLayoutConfiguration(final String defaultName,
            final String defaultDescription) {

        super(defaultName, defaultDescription);

        this.xOutput = CoordinateComponent.FIRST_COMPONENT;
        this.yOutput = CoordinateComponent.SECOND_COMPONENT;
        this.zOutput = CoordinateComponent.THIRD_COMPONENT;

        this.xOutputScale = 1;
        this.yOutputScale = 1;
        this.zOutputScale = 1;

    }
}
