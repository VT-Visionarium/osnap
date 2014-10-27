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


package edu.vt.arc.vis.osnap.core.domain.visualization;

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


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.math.vectorAlgebra.IVector4;
import org.jutility.math.vectorAlgebra.Vector4;


/**
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlType(name = "Label")
@XmlAccessorType(XmlAccessType.NONE)
public class Label {

    @XmlElement(name = "Text")
    private String                        text;

    @XmlAttribute
    private final Class<? extends Number> precision;

    @XmlElement(name = "Offset", type = Vector4.class)
    private IVector4<?>                   offset;

    @XmlAttribute
    private boolean                       visible;


    /**
     * Returns the text of this Label.
     * 
     * @return the text.
     */
    public String getText() {

        return this.text;
    }


    /**
     * Sets the new text of this Label.
     * 
     * @param text
     *            the new text.
     */
    public void setText(String text) {

        this.text = text;
    }


    /**
     * Returns the offset of this Label.
     * 
     * @return the offset.
     */
    public IVector4<?> getOffset() {

        return this.offset;
    }

    /**
     * Sets the new offset of this Label.
     * 
     * @param offset
     *            the new offset.
     */
    public void setOffset(IVector4<?> offset) {

        this.offset = offset;
    }


    /**
     * Returns the visibility of this Label.
     * 
     * @return <code>true</code> if the Label is visible; <code>false</code>
     *         otherwise.
     */
    public boolean isVisible() {

        return this.visible;
    }

    /**
     * Sets the new visibility of this Label.
     * 
     * @param visible
     *            the new visibility.
     */
    public void setVisible(boolean visible) {

        this.visible = visible;
    }


    /**
     * Creates a new {@link Label}. (Serialization constructor)
     */
    @SuppressWarnings("unused")
    private Label() {

        this(null, false, null, true);
    }

    /**
     * Creates a new {@link Label} with an offset of (1, 1, 0, 0).
     * 
     * @param precision
     *            the numerical precision of the offset.
     */
    public <T extends Number> Label(Class<? extends T> precision) {

        this(new Vector4<T>(1, 1, 0, precision), false, precision, false);
    }


    /**
     * Creates a new {@link Label} with the provided offset.
     * 
     * @param precision
     *            the numerical precision of the offset.
     */
    private <T extends Number> Label(IVector4<T> offset, boolean isVisible,
            Class<? extends T> precision, boolean serialization) {

        if (offset == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a label without an offset!");
        }

        this.offset = offset;

        this.precision = precision;
        this.visible = isVisible;

    }

    @Override
    public String toString() {

        return this.text + "@" + this.offset;
    }


    /**
     * Determines whether or not this {@link Label} is identical to the one
     * provided.
     * 
     * @param other
     *            the other {@link Label}.
     * @return <code>true</code> if they are identical; <code>false</code>
     *         otherwise.
     */
    public boolean isIdentical(Label other) {

        if (this.equals(other)) {

            boolean sameOffset = (this.getOffset() == other.getOffset())
                    || (this.getOffset() != null && this.getOffset().equals(
                            other.getOffset()));
            boolean sameVisibility = (this.isVisible() == other.isVisible());

            return sameOffset && sameVisibility;
        }


        return false;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof Label) {
            Label other = (Label) obj;

            if ((this.getText() == other.getText())
                    || (this.getText() != null && this.getText().equals(
                            other.getText()))) {

                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {

        return this.text.hashCode();
    }
}
