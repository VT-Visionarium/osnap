/**
 * 
 */
package edu.vt.arc.vis.osnap.visualization;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.jutility.math.geometry.IRotation;
import org.jutility.math.geometry.Rotation;
import org.jutility.math.vectorAlgebra.IPoint4;
import org.jutility.math.vectorAlgebra.Point4;


/**
 * @author Peter J. Radics
 * @version 1.0
 * @since 1.0
 * 
 */
@XmlType(name = "Viewpoint")
@XmlAccessorType(XmlAccessType.NONE)
public class Viewpoint {

    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    @XmlAttribute(name = "id")
    private final String                  id;

    @XmlAttribute
    private final Class<? extends Number> precision;

    @XmlElement(name = "Position", type = Point4.class)
    private IPoint4<?>                    position;

    @XmlElement(name = "LookAt", type = Point4.class)
    private IPoint4<?>                    lookAt;

    @XmlElement(name = "Rotation", type = Rotation.class)
    private IRotation<?>                  rotation;

    /**
     * Returns the id of this object.
     * 
     * @return the id.
     */
    public String getID() {

        return this.id;
    }


    /**
     * Returns the precision of this {@link Viewpoint Viewpoint'scale} numerical
     * properties.
     * 
     * @return the precision.
     */
    public Class<? extends Number> getPrecision() {

        return this.precision;
    }


    /**
     * Returns the position of this Viewpoint.
     * 
     * @return the position.
     */
    public IPoint4<?> getPosition() {

        return this.position;
    }


    /**
     * Sets the new position of this Viewpoint.
     * 
     * @param position
     *            the new position.
     */
    public void setPosition(IPoint4<?> position) {

        this.position = position;
    }

    /**
     * Returns the look-at point of this Viewpoint.
     * 
     * @return the look-at point of this Viewpoint.
     */
    public IPoint4<?> getLookAt() {

        return this.lookAt;
    }


    /**
     * Sets the look-at point of this Viewpoint
     * 
     * @param lookAt
     *            the look-at point of this Viewpoint
     */
    public void setLookAt(IPoint4<?> lookAt) {

        this.lookAt = lookAt;
    }


    /**
     * Returns the rotation of this Viewpoint.
     * 
     * @return the rotation.
     */
    public IRotation<?> getRotation() {

        return this.rotation;
    }


    /**
     * Sets the new rotation of this Viewpoint.
     * 
     * @param rotation
     *            the new rotation.
     */
    public void setRotation(IRotation<?> rotation) {

        this.rotation = rotation;
    }

    @SuppressWarnings("unused")
    private Viewpoint() {

        this(null, null, true);
    }


    /**
     * Creates a new instance of the {@link Viewpoint} class with the provided
     * id and precision.
     * 
     * @param id
     *            the id.
     * @param precision
     *            the precision.
     */
    public Viewpoint(String id, Class<? extends Number> precision) {

        this(id, precision, false);
    }

    /**
     * Creates a new instance of the {@link Viewpoint} class with the provided
     * id and precision.
     * 
     * @param id
     *            the id.
     * @param precision
     *            the precision.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    protected Viewpoint(String id, Class<? extends Number> precision,
            boolean serialization) {

        if (id == null && !serialization) {

            throw new IllegalArgumentException("Viewpoint has to have an ID!");
        }
        this.id = id;

        this.precision = precision;

        this.position = null;
        this.lookAt = null;
        this.rotation = null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        String value = this.getID() + " with position: " + this.getPosition()
                + ", lookAt: " + this.getLookAt() + ", and rotation: "
                + this.getRotation();

        return value;
    }


}
