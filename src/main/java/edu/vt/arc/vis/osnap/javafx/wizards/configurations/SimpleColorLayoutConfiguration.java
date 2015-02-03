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

import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleColorLayout;
import javafx.scene.paint.Color;


/**
 * The {@code SimpleColorLayoutConfiguration} class provides the
 * {@link IConfiguration Configuration} for the
 * {@link SimpleColorLayout}.
 * 
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.3
 * @since 0.5.0
 */
public class SimpleColorLayoutConfiguration

        extends LayoutConfiguration<SimpleColorLayout> {

    private Color color;


    /**
     * Returns the {@link Color} assigned to the restrictions.
     * 
     * @return the {@link Color} assigned to the restrictions.
     */
    public Color getColor() {

        return this.color;
    }

    /**
     * Sets the {@link Color} assigned to the restrictions.
     * 
     * @param color
     *            the {@link Color} assigned to the restrictions.
     */
    public void setColor(Color color) {

        if (color == null) {

            this.color = Color.WHITE;
        }
        else {

            this.color = color;
        }
    }


    /**
     * Creates a new instance of the
     * {@code SimpleColorLayoutConfiguration} class.
     */
    public SimpleColorLayoutConfiguration() {

        super(SimpleColorLayout.name(), SimpleColorLayout
                .description());

        this.color = Color.WHITE;
    }

    @Override
    public SimpleColorLayout createConfiguredObject() {

        return new SimpleColorLayout(this.getColor());
    }
}
