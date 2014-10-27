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


package edu.vt.arc.vis.osnap.properties;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * The <CODE>ListProperty</CODE> class provides a thread-safe wrapper around a
 * property list.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <PROPERTY_TYPE>
 *            the type of the property.
 * 
 */
public final class ListProperty<PROPERTY_TYPE> {


    private final String              name;

    private final Lock                propertyLock;
    private final List<PROPERTY_TYPE> propertyValues;


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
    public List<PROPERTY_TYPE> getPropertyValues() {

        try {
            this.propertyLock.lock();

            return Collections.unmodifiableList(this.propertyValues);
        }
        finally {
            this.propertyLock.unlock();
        }
    }


    /**
     * Adds a property value to the list.
     * 
     * @param propertyValue
     *            the new property value.
     * @return <CODE>true</CODE> if this collection changed as a result of the
     *         call
     */
    public boolean addPropertyValue(final PROPERTY_TYPE propertyValue) {

        try {
            this.propertyLock.lock();

            return this.propertyValues.add(propertyValue);
        }
        finally {
            this.propertyLock.unlock();
        }
    }


    /**
     * Adds a property value to the list at the provided index.
     * 
     * @param propertyValue
     *            the new property value.
     * @param index
     *            the index.
     */
    public void addPropertyValue(final PROPERTY_TYPE propertyValue,
            final int index) {

        try {
            this.propertyLock.lock();

            this.propertyValues.add(index, propertyValue);
        }
        finally {
            this.propertyLock.unlock();
        }
    }


    /**
     * Removes a property value from the list.
     * 
     * @param propertyValue
     *            the property value to remove.
     * @return <CODE>true</CODE> if this list contained the specified element.
     */
    public boolean removePropertyValue(final PROPERTY_TYPE propertyValue) {

        try {

            this.propertyLock.lock();
            return this.propertyValues.remove(propertyValue);
        }
        finally {
            this.propertyLock.unlock();
        }
    }


    /**
     * Removes the property value at the provided index from the list.
     * 
     * @param index
     *            the index of the property value to be removed.
     * @return true if this collection changed as a result of the call
     */
    public PROPERTY_TYPE removePropertyValue(final int index) {

        try {

            this.propertyLock.lock();
            if (index >= 0 && index < this.propertyValues.size()) {
                return this.propertyValues.remove(index);
            }
        }
        finally {
            this.propertyLock.unlock();
        }
        return null;
    }


    /**
     * Clears all property values.
     */
    public void clearPropertyValues() {

        try {

            this.propertyLock.lock();
            this.propertyValues.clear();
        }
        finally {
            this.propertyLock.unlock();
        }
    }


    /**
     * Constructs a new <CODE>ListProperty</CODE> and sets its name.
     * 
     * @param name
     *            the name.
     */
    public ListProperty(final String name) {

        this.propertyLock = new ReentrantLock();
        this.name = name;

        this.propertyValues = new ArrayList<>();

    }

}
