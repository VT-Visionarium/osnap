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


import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * The <CODE>MapProperty</CODE> class provides a thread-safe wrapper around a
 * property map.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <KEY_TYPE>
 *            the key type of the property.
 * @param <VALUE_TYPE>
 *            the value type of the property.
 * 
 */
public final class MapProperty<KEY_TYPE, VALUE_TYPE> {


    private final String                    name;

    private final Lock                      propertyLock;
    private final Map<KEY_TYPE, VALUE_TYPE> propertyValues;


    /**
     * Returns the property'scale name.
     * 
     * @return the name.
     */
    public String getName() {

        return this.name;
    }


    /**
     * Removes all of the mappings from this map. The map will be empty after
     * this call returns.
     */
    public void clear() {

        try {

            this.propertyLock.lock();
            this.propertyValues.clear();
        }
        finally {

            this.propertyLock.unlock();
        }
    }

    /**
     * Returns <CODE>true</CODE> if this map contains a mapping for the
     * specified key. More formally, returns <CODE>true</CODE> if and only if
     * this map contains a mapping for a key k such that
     * <CODE>(key==null ? k==null :
     * key.equals(k))</CODE>. (There can be at most one such mapping.)
     * 
     * @param key
     *            key whose presence in this map is to be tested
     * @return <CODE>true</CODE> if this map contains a mapping for the
     *         specified key
     */
    public boolean containsKey(Object key) {

        try {
            this.propertyLock.lock();

            return this.propertyValues.containsKey(key);
        }
        finally {
            this.propertyLock.unlock();
        }
    }


    /**
     * Returns <CODE>true</CODE> if this map maps one or more keys to the
     * specified value. More formally, returns <CODE>true</CODE> if and only if
     * this map contains at least one mapping to a value v such that
     * <CODE>(value==null ?
     * v==null : value.equals(v))</CODE>. This operation will probably require
     * time linear in the map size for most implementations of the Map
     * interface.
     * 
     * @param value
     *            value whose presence in this map is to be tested
     * @return <CODE>true</CODE> if this map maps one or more keys to the
     *         specified value
     */
    public boolean containsValue(Object value) {

        try {
            this.propertyLock.lock();

            return this.propertyValues.containsValue(value);
        }
        finally {
            this.propertyLock.unlock();
        }
    }


    /**
     * Returns an unmodifiable {@link java.util.Set} view of the mappings
     * contained in this map.
     * 
     * @return an unmodifiable set view of the mappings contained in this map
     */
    public Set<java.util.Map.Entry<KEY_TYPE, VALUE_TYPE>> entrySet() {

        try {
            this.propertyLock.lock();

            return Collections.unmodifiableSet(this.propertyValues.entrySet());
        }
        finally {
            this.propertyLock.unlock();
        }
    }


    /**
     * Returns the value to which the specified key is mapped, or
     * <CODE>null</CODE> if this map contains no mapping for the key.
     * 
     * More formally, if this map contains a mapping from a key k to a value v
     * such that <CODE>(key==null ? k==null : key.equals(k))</CODE>, then this
     * method returns v; otherwise it returns null. (There can be at most one
     * such mapping.)
     * 
     * If this map permits <CODE>null</CODE> values, then a return value of
     * <CODE>null</CODE> does not necessarily indicate that the map contains no
     * mapping for the key; it'scale also possible that the map explicitly maps the
     * key to <CODE>null</CODE>. The containsKey operation may be used to
     * distinguish these two cases.
     * 
     * @param key
     *            the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         <CODE>null</CODE> if this map contains no mapping for the key
     */
    public VALUE_TYPE get(Object key) {

        try {
            this.propertyLock.lock();

            return this.propertyValues.get(key);

        }
        finally {
            this.propertyLock.unlock();
        }

    }

    /**
     * Associates the specified value with the specified key in this map. If the
     * map previously contained a mapping for the key, the old value is replaced
     * by the specified value. (A map m is said to contain a mapping for a key k
     * if and only if m.containsKey(k) would return true.)
     * 
     * @param key
     *            key with which the specified value is to be associated
     * 
     * @param value
     *            value to be associated with the specified key
     * @return the previous value associated with key, or <CODE>null</CODE> if
     *         there was no mapping for key. (A <CODE>null</CODE> return can
     *         also indicate that the map previously associated
     *         <CODE>null</CODE> with key, if the implementation supports
     *         <CODE>null</CODE> values.)
     */
    public VALUE_TYPE put(final KEY_TYPE key, final VALUE_TYPE value) {

        try {
            this.propertyLock.lock();

            return this.propertyValues.put(key, value);

        }
        finally {
            this.propertyLock.unlock();
        }
    }


    /**
     * Copies all of the mappings from the specified map to this map. The effect
     * of this call is equivalent to that of calling put(k, v) on this map once
     * for each mapping from key k to value v in the specified map.
     * 
     * @param elements
     *            mappings to be stored in this map
     */
    public void putAll(Map<? extends KEY_TYPE, ? extends VALUE_TYPE> elements) {

        try {
            this.propertyLock.lock();

            this.propertyValues.putAll(elements);

        }
        finally {
            this.propertyLock.unlock();
        }
    }


    /**
     * Returns <CODE>true</CODE> if this map contains no key-value mappings.
     * 
     * @return <CODE>true</CODE> if this map contains no key-value mappings
     */
    public boolean isEmpty() {

        boolean empty = false;

        try {
            this.propertyLock.lock();
            empty = this.propertyValues.isEmpty();

            return empty;
        }
        finally {
            this.propertyLock.unlock();
        }
    }

    /**
     * Returns the number of key-value mappings in this map. If the map contains
     * more than <CODE>Integer.MAX_VALUE</CODE> elements, returns
     * Integer.MAX_VALUE.
     * 
     * @return the number of key-value mappings in this map
     */
    public int size() {

        try {
            this.propertyLock.lock();

            return this.propertyValues.size();
        }
        finally {

            this.propertyLock.unlock();
        }
    }

    /**
     * Returns an unmodifiable {@link java.util.Set} view of the keys contained
     * in this map.
     * 
     * @return an unmodifiable set view of the keys contained in this map
     */
    public Set<KEY_TYPE> keySet() {

        try {
            this.propertyLock.lock();

            return Collections.unmodifiableSet(this.propertyValues.keySet());
        }
        finally {
            this.propertyLock.unlock();
        }
    }

    /**
     * Removes the mapping for a key from this map if it is present. More
     * formally, if this map contains a mapping from key k to value v such that
     * <CODE>(key==null ? k==null : key.equals(k))</CODE>, that mapping is
     * removed. (The map can contain at most one such mapping.)
     * <p>
     * Returns the value to which this map previously associated the key, or
     * <CODE>null</CODE> if the map contained no mapping for the key.
     * <p>
     * If this map permits <CODE>null</CODE> values, then a return value of
     * <CODE>null</CODE> does not necessarily indicate that the map contained no
     * mapping for the key; it'scale also possible that the map explicitly mapped
     * the key to <CODE>null</CODE>.
     * <p>
     * The map will not contain a mapping for the specified key once the call
     * returns.
     * 
     * @param key
     *            key whose mapping is to be removed from the map
     * @return the previous value associated with key, or <CODE>null</CODE> if
     *         there was no mapping for key. (A <CODE>null</CODE> return can
     *         also indicate that the map previously associated
     *         <CODE>null</CODE> with key, if the implementation supports
     *         <CODE>null</CODE> values.)
     */
    public VALUE_TYPE remove(final Object key) {

        try {

            this.propertyLock.lock();

            return this.propertyValues.remove(key);
        }
        finally {
            this.propertyLock.unlock();
        }
    }

    /**
     * Returns an unmodifiable {@link java.util.Collection} view of the values
     * contained in this map.
     * 
     * @return a collection view of the values contained in this map
     */
    public Collection<VALUE_TYPE> values() {

        try {
            this.propertyLock.lock();

            return Collections.unmodifiableCollection(this.propertyValues
                    .values());
        }
        finally {
            this.propertyLock.unlock();
        }
    }


    /**
     * Returns an unmodifiable copy of the map backing the property.
     * 
     * @return an unmodifiable copy of the map.
     */
    public Map<KEY_TYPE, VALUE_TYPE> getPropertyMap() {

        try {
            this.propertyLock.lock();

            return Collections.unmodifiableMap(this.propertyValues);
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
    public MapProperty(final String name) {

        this.propertyLock = new ReentrantLock();
        this.name = name;

        this.propertyValues = new LinkedHashMap<>();

    }

}
