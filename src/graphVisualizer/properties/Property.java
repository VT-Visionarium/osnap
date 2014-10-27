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


package graphVisualizer.properties;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * The <CODE>Property</CODE> class provides a thread-safe wrapper around a
 * property.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <PROPERTY_TYPE>
 *            the type of the property.
 * 
 */
public final class Property<PROPERTY_TYPE> {


    private final String  name;

    private final Lock    propertyLock;
    private PROPERTY_TYPE propertyValue;


    /**
     * Returns the property'scale name.
     * 
     * @return the name.
     */
    public String getName() {

        return this.name;
    }


    /**
     * Returns the property'scale value.
     * 
     * @return the property value.
     */
    public PROPERTY_TYPE getPropertyValue() {

        try {
            this.propertyLock.lock();

            return this.propertyValue;
        }
        finally {
            this.propertyLock.unlock();
        }
    }


    /**
     * Sets the property'scale value.
     * 
     * @param propertyValue
     *            the new property value.
     */
    public void setPropertyValue(PROPERTY_TYPE propertyValue) {

        try {
            this.propertyLock.lock();

            this.propertyValue = propertyValue;
        }
        finally {
            this.propertyLock.unlock();
        }

    }


    /**
     * Constructs a new <CODE>Property</CODE> and sets its name.
     * 
     * @param name
     *            the name.
     * 
     */
    public Property(String name) {

        this(name, null);
    }


    /**
     * Constructs a new <CODE>Property</CODE> and sets its name and value.
     * 
     * @param name
     *            the name
     * @param propertyValue
     *            the value.
     */
    public Property(final String name, final PROPERTY_TYPE propertyValue) {

        this.propertyLock = new ReentrantLock();
        this.name = name;

        this.propertyValue = propertyValue;

    }

}
