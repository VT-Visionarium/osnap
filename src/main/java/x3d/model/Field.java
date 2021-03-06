//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.06.16 at 09:21:15 AM EDT 
//


package x3d.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import x3d.fields.SFString;

/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{}SceneGraphStructureNodeType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;group ref="{}SceneGraphFragmentContentModel"/>
 *       &lt;/choice>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="accessType" use="required" type="{}accessTypeNames" />
 *       &lt;attribute name="type" use="required" type="{}fieldTypeName" />
 *       &lt;attribute name="value" type="{}SFString" />
 *       &lt;attribute name="appinfo" type="{}SFString" />
 *       &lt;attribute name="documentation" type="{}SFString" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "childObjects"
})
@XmlRootElement(name = "field")
public class Field
    extends SceneGraphStructureNode
{

    @XmlElements({
        @XmlElement(name = "FillProperties", type = FillProperties.class),
        @XmlElement(name = "LineProperties", type = LineProperties.class),
        @XmlElement(name = "Material", type = Material.class),
        @XmlElement(name = "TwoSidedMaterial", type = TwoSidedMaterial.class),
        //@XmlElement(name = "ComposedShader", type = ComposedShader.class),
        //@XmlElement(name = "PackagedShader", type = PackagedShader.class),
        //@XmlElement(name = "ProgramShader", type = ProgramShader.class),
        //@XmlElement(name = "ComposedCubeMapTexture", type = ComposedCubeMapTexture.class),
        //@XmlElement(name = "ComposedTexture3D", type = ComposedTexture3D.class),
        //@XmlElement(name = "ImageTexture", type = ImageTexture.class),
        //@XmlElement(name = "ImageTexture3D", type = ImageTexture3D.class),
        //@XmlElement(name = "MovieTexture", type = MovieTexture.class),
        //@XmlElement(name = "MultiTexture", type = MultiTexture.class),
        //@XmlElement(name = "PixelTexture", type = PixelTexture.class),
        //@XmlElement(name = "PixelTexture3D", type = PixelTexture3D.class),
        //@XmlElement(name = "GeneratedCubeMapTexture", type = GeneratedCubeMapTexture.class),
        //@XmlElement(name = "ImageCubeMapTexture", type = ImageCubeMapTexture.class),
        //@XmlElement(name = "MultiTextureTransform", type = MultiTextureTransform.class),
        //@XmlElement(name = "TextureTransform", type = TextureTransform.class),
        @XmlElement(name = "MetadataDouble", type = MetadataDouble.class),
        @XmlElement(name = "MetadataFloat", type = MetadataFloat.class),
        @XmlElement(name = "MetadataInteger", type = MetadataInteger.class),
        @XmlElement(name = "MetadataSet", type = MetadataSet.class),
        @XmlElement(name = "MetadataString", type = MetadataString.class),
        @XmlElement(name = "Appearance", type = Appearance.class),
        @XmlElement(name = "Background", type = Background.class),
//        @XmlElement(name = "ColorInterpolator", type = ColorInterpolator.class),
//        @XmlElement(name = "CoordinateInterpolator", type = CoordinateInterpolator.class),
        @XmlElement(name = "DirectionalLight", type = DirectionalLight.class),
        @XmlElement(name = "Group", type = Group.class),
        @XmlElement(name = "NavigationInfo", type = NavigationInfo.class),
//        @XmlElement(name = "NormalInterpolator", type = NormalInterpolator.class),
//        @XmlElement(name = "OrientationInterpolator", type = OrientationInterpolator.class),
//        @XmlElement(name = "PositionInterpolator", type = PositionInterpolator.class),
//        @XmlElement(name = "ScalarInterpolator", type = ScalarInterpolator.class),
        @XmlElement(name = "Shape", type = Shape.class),
//        @XmlElement(name = "TimeSensor", type = TimeSensor.class),
        @XmlElement(name = "Transform", type = Transform.class),
//        @XmlElement(name = "Viewpoint", type = Viewpoint.class),
        @XmlElement(name = "WorldInfo", type = WorldInfo.class),
        @XmlElement(name = "Anchor", type = Anchor.class),
//        @XmlElement(name = "BooleanFilter", type = BooleanFilter.class),
//        @XmlElement(name = "BooleanSequencer", type = BooleanSequencer.class),
//        @XmlElement(name = "BooleanToggle", type = BooleanToggle.class),
//        @XmlElement(name = "BooleanTrigger", type = BooleanTrigger.class),
//        @XmlElement(name = "CylinderSensor", type = CylinderSensor.class),
//        @XmlElement(name = "Inline", type = Inline.class),
//        @XmlElement(name = "IntegerSequencer", type = IntegerSequencer.class),
//        @XmlElement(name = "IntegerTrigger", type = IntegerTrigger.class),
        @XmlElement(name = "KeySensor", type = KeySensor.class),
//        @XmlElement(name = "PlaneSensor", type = PlaneSensor.class),
        @XmlElement(name = "PointLight", type = PointLight.class),
//        @XmlElement(name = "ProximitySensor", type = ProximitySensor.class),
//        @XmlElement(name = "SphereSensor", type = SphereSensor.class),
        @XmlElement(name = "SpotLight", type = SpotLight.class),
        @XmlElement(name = "StringSensor", type = StringSensor.class),
        @XmlElement(name = "Switch", type = Switch.class),
//        @XmlElement(name = "TimeTrigger", type = TimeTrigger.class),
//        @XmlElement(name = "TouchSensor", type = TouchSensor.class),
//        @XmlElement(name = "AudioClip", type = AudioClip.class),
        @XmlElement(name = "Billboard", type = Billboard.class),
//        @XmlElement(name = "Collision", type = Collision.class),
//        @XmlElement(name = "Fog", type = Fog.class),
//        @XmlElement(name = "LoadSensor", type = LoadSensor.class),
//        @XmlElement(name = "LocalFog", type = LocalFog.class),
        @XmlElement(name = "LOD", type = LOD.class),
        @XmlElement(name = "Script", type = Script.class),
//        @XmlElement(name = "Sound", type = Sound.class),
//        @XmlElement(name = "VisibilitySensor", type = VisibilitySensor.class),
//        @XmlElement(name = "CoordinateInterpolator2D", type = CoordinateInterpolator2D.class),
//        @XmlElement(name = "PositionInterpolator2D", type = PositionInterpolator2D.class),
        @XmlElement(name = "StaticGroup", type = StaticGroup.class),
//        @XmlElement(name = "CADAssembly", type = CADAssembly.class),
//        @XmlElement(name = "CADLayer", type = CADLayer.class),
//        @XmlElement(name = "OrthoViewpoint", type = OrthoViewpoint.class),
//        @XmlElement(name = "ViewpointGroup", type = ViewpointGroup.class),
//        @XmlElement(name = "ColorDamper", type = ColorDamper.class),
//        @XmlElement(name = "CoordinateDamper", type = CoordinateDamper.class),
//        @XmlElement(name = "OrientationChaser", type = OrientationChaser.class),
//        @XmlElement(name = "OrientationDamper", type = OrientationDamper.class),
//        @XmlElement(name = "PositionChaser", type = PositionChaser.class),
//        @XmlElement(name = "PositionChaser2D", type = PositionChaser2D.class),
//        @XmlElement(name = "PositionDamper", type = PositionDamper.class),
//        @XmlElement(name = "PositionDamper2D", type = PositionDamper2D.class),
//        @XmlElement(name = "ScalarChaser", type = ScalarChaser.class),
//        @XmlElement(name = "TexCoordDamper2D", type = TexCoordDamper2D.class),
//        @XmlElement(name = "TextureBackground", type = TextureBackground.class),
//        @XmlElement(name = "CollidableShape", type = CollidableShape.class),
//        @XmlElement(name = "CollisionSensor", type = CollisionSensor.class),
//        @XmlElement(name = "RigidBodyCollection", type = RigidBodyCollection.class),
//        @XmlElement(name = "EspduTransform", type = EspduTransform.class),
//        @XmlElement(name = "ReceiverPdu", type = ReceiverPdu.class),
//        @XmlElement(name = "SignalPdu", type = SignalPdu.class),
//        @XmlElement(name = "TransmitterPdu", type = TransmitterPdu.class),
//        @XmlElement(name = "DISEntityManager", type = DISEntityManager.class),
//        @XmlElement(name = "GeoLocation", type = GeoLocation.class),
//        @XmlElement(name = "GeoLOD", type = GeoLOD.class),
//        @XmlElement(name = "GeoMetadata", type = GeoMetadata.class),
//        @XmlElement(name = "GeoOrigin", type = GeoOrigin.class),
//        @XmlElement(name = "GeoPositionInterpolator", type = GeoPositionInterpolator.class),
//        @XmlElement(name = "GeoProximitySensor", type = GeoProximitySensor.class),
//        @XmlElement(name = "GeoTouchSensor", type = GeoTouchSensor.class),
//        @XmlElement(name = "GeoViewpoint", type = GeoViewpoint.class),
//        @XmlElement(name = "GeoTransform", type = GeoTransform.class),
//        @XmlElement(name = "HAnimHumanoid", type = HAnimHumanoid.class),
//        @XmlElement(name = "HAnimJoint", type = HAnimJoint.class),
//        @XmlElement(name = "HAnimSegment", type = HAnimSegment.class),
//        @XmlElement(name = "HAnimSite", type = HAnimSite.class),
//        @XmlElement(name = "NurbsOrientationInterpolator", type = NurbsOrientationInterpolator.class),
//        @XmlElement(name = "NurbsPositionInterpolator", type = NurbsPositionInterpolator.class),
//        @XmlElement(name = "NurbsSurfaceInterpolator", type = NurbsSurfaceInterpolator.class),
//        @XmlElement(name = "NurbsSet", type = NurbsSet.class),
//        @XmlElement(name = "ProtoInstance", type = ProtoInstance.class),
        @XmlElement(name = "Box", type = Box.class),
        @XmlElement(name = "Cone", type = Cone.class),
        @XmlElement(name = "Cylinder", type = Cylinder.class),
//        @XmlElement(name = "IndexedFaceSet", type = IndexedFaceSet.class),
//        @XmlElement(name = "IndexedLineSet", type = IndexedLineSet.class),
//        @XmlElement(name = "IndexedTriangleFanSet", type = IndexedTriangleFanSet.class),
//        @XmlElement(name = "IndexedTriangleSet", type = IndexedTriangleSet.class),
//        @XmlElement(name = "IndexedTriangleStripSet", type = IndexedTriangleStripSet.class),
//        @XmlElement(name = "LineSet", type = LineSet.class),
//        @XmlElement(name = "PointSet", type = PointSet.class),
        @XmlElement(name = "Sphere", type = Sphere.class),
//        @XmlElement(name = "TriangleFanSet", type = TriangleFanSet.class),
//        @XmlElement(name = "TriangleSet", type = TriangleSet.class),
//        @XmlElement(name = "TriangleStripSet", type = TriangleStripSet.class),
//        @XmlElement(name = "ElevationGrid", type = ElevationGrid.class),
//        @XmlElement(name = "Polyline2D", type = Polyline2D.class),
//        @XmlElement(name = "Polypoint2D", type = Polypoint2D.class),
//        @XmlElement(name = "Rectangle2D", type = Rectangle2D.class),
//        @XmlElement(name = "TriangleSet2D", type = TriangleSet2D.class),
//        @XmlElement(name = "Extrusion", type = Extrusion.class),
        @XmlElement(name = "Text", type = Text.class)
//        @XmlElement(name = "Arc2D", type = Arc2D.class),
//        @XmlElement(name = "ArcClose2D", type = ArcClose2D.class),
//        @XmlElement(name = "Circle2D", type = Circle2D.class),
//        @XmlElement(name = "Disk2D", type = Disk2D.class),
//        @XmlElement(name = "QuadSet", type = QuadSet.class),
//        @XmlElement(name = "IndexedQuadSet", type = IndexedQuadSet.class),
//        @XmlElement(name = "GeoElevationGrid", type = GeoElevationGrid.class),
//        @XmlElement(name = "NurbsCurve", type = NurbsCurve.class),
//        @XmlElement(name = "NurbsCurve2D", type = NurbsCurve2D.class),
//        @XmlElement(name = "NurbsPatchSurface", type = NurbsPatchSurface.class),
//        @XmlElement(name = "NurbsSweptSurface", type = NurbsSweptSurface.class),
//        @XmlElement(name = "NurbsSwungSurface", type = NurbsSwungSurface.class),
//        @XmlElement(name = "NurbsTrimmedSurface", type = NurbsTrimmedSurface.class),
//        @XmlElement(name = "Color", type = Color.class),
//        @XmlElement(name = "ColorRGBA", type = ColorRGBA.class),
//        @XmlElement(name = "Coordinate", type = Coordinate.class),
//        @XmlElement(name = "CoordinateDouble", type = CoordinateDouble.class),
//        @XmlElement(name = "FontStyle", type = FontStyle.class),
//        @XmlElement(name = "GeoCoordinate", type = GeoCoordinate.class),
//        @XmlElement(name = "Normal", type = Normal.class),
//        @XmlElement(name = "TextureCoordinate", type = TextureCoordinate.class),
//        @XmlElement(name = "HAnimDisplacer", type = HAnimDisplacer.class),
//        @XmlElement(name = "Contour2D", type = Contour2D.class),
//        @XmlElement(name = "ContourPolyline2D", type = ContourPolyline2D.class),
//        @XmlElement(name = "NurbsTextureCoordinate", type = NurbsTextureCoordinate.class),
//        @XmlElement(name = "BallJoint", type = BallJoint.class),
//        @XmlElement(name = "CollidableOffset", type = CollidableOffset.class),
//        @XmlElement(name = "CollisionCollection", type = CollisionCollection.class),
//        @XmlElement(name = "CollisionSpace", type = CollisionSpace.class),
//        @XmlElement(name = "Contact", type = Contact.class),
//        @XmlElement(name = "DoubleAxisHingeJoint", type = DoubleAxisHingeJoint.class),
//        @XmlElement(name = "MotorJoint", type = MotorJoint.class),
//        @XmlElement(name = "RigidBody", type = RigidBody.class),
//        @XmlElement(name = "SingleAxisHingeJoint", type = SingleAxisHingeJoint.class),
//        @XmlElement(name = "SliderJoint", type = SliderJoint.class),
//        @XmlElement(name = "UniversalJoint", type = UniversalJoint.class)
    })
    protected List<X3DNode> childObjects;

    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String name;

    @XmlAttribute(name = "accessType", required = true)
    protected AccessTypeNames accessType;

    @XmlAttribute(name = "type", required = true)
    protected FieldTypeName type;

    @XmlAttribute(name = "value")
    protected SFString value;

    @XmlAttribute(name = "appinfo")
    protected SFString appinfo;
    
    @XmlAttribute(name = "documentation")
    protected SFString documentation;

    /**
     * Gets the value of the childObjects property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the childObjects property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChildObjects().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(scale) are allowed in the list
     * {@link FillProperties }
     * {@link LineProperties }
     * {@link Material }
     * {@link TwoSidedMaterial }
     * {@link ComposedShader }
     * {@link PackagedShader }
     * {@link ProgramShader }
     * {@link ComposedCubeMapTexture }
     * {@link ComposedTexture3D }
     * {@link ImageTexture }
     * {@link ImageTexture3D }
     * {@link MovieTexture }
     * {@link MultiTexture }
     * {@link PixelTexture }
     * {@link PixelTexture3D }
     * {@link GeneratedCubeMapTexture }
     * {@link ImageCubeMapTexture }
     * {@link MultiTextureTransform }
     * {@link TextureTransform }
     * {@link MetadataDouble }
     * {@link MetadataFloat }
     * {@link MetadataInteger }
     * {@link MetadataSet }
     * {@link MetadataString }
     * {@link Appearance }
     * {@link Background }
     * {@link ColorInterpolator }
     * {@link CoordinateInterpolator }
     * {@link DirectionalLight }
     * {@link Group }
     * {@link NavigationInfo }
     * {@link NormalInterpolator }
     * {@link OrientationInterpolator }
     * {@link PositionInterpolator }
     * {@link ScalarInterpolator }
     * {@link Shape }
     * {@link TimeSensor }
     * {@link Transform }
     * {@link Viewpoint }
     * {@link WorldInfo }
     * {@link Anchor }
     * {@link BooleanFilter }
     * {@link BooleanSequencer }
     * {@link BooleanToggle }
     * {@link BooleanTrigger }
     * {@link CylinderSensor }
     * {@link Inline }
     * {@link IntegerSequencer }
     * {@link IntegerTrigger }
     * {@link KeySensor }
     * {@link PlaneSensor }
     * {@link PointLight }
     * {@link ProximitySensor }
     * {@link SphereSensor }
     * {@link SpotLight }
     * {@link StringSensor }
     * {@link Switch }
     * {@link TimeTrigger }
     * {@link TouchSensor }
     * {@link AudioClip }
     * {@link Billboard }
     * {@link Collision }
     * {@link Fog }
     * {@link LoadSensor }
     * {@link LocalFog }
     * {@link LOD }
     * {@link Script }
     * {@link Sound }
     * {@link VisibilitySensor }
     * {@link CoordinateInterpolator2D }
     * {@link PositionInterpolator2D }
     * {@link StaticGroup }
     * {@link CADAssembly }
     * {@link CADLayer }
     * {@link OrthoViewpoint }
     * {@link ViewpointGroup }
     * {@link ColorDamper }
     * {@link CoordinateDamper }
     * {@link OrientationChaser }
     * {@link OrientationDamper }
     * {@link PositionChaser }
     * {@link PositionChaser2D }
     * {@link PositionDamper }
     * {@link PositionDamper2D }
     * {@link ScalarChaser }
     * {@link TexCoordDamper2D }
     * {@link TextureBackground }
     * {@link CollidableShape }
     * {@link CollisionSensor }
     * {@link RigidBodyCollection }
     * {@link EspduTransform }
     * {@link ReceiverPdu }
     * {@link SignalPdu }
     * {@link TransmitterPdu }
     * {@link DISEntityManager }
     * {@link GeoLocation }
     * {@link GeoLOD }
     * {@link GeoMetadata }
     * {@link GeoOrigin }
     * {@link GeoPositionInterpolator }
     * {@link GeoProximitySensor }
     * {@link GeoTouchSensor }
     * {@link GeoViewpoint }
     * {@link GeoTransform }
     * {@link HAnimHumanoid }
     * {@link HAnimJoint }
     * {@link HAnimSegment }
     * {@link HAnimSite }
     * {@link NurbsOrientationInterpolator }
     * {@link NurbsPositionInterpolator }
     * {@link NurbsSurfaceInterpolator }
     * {@link NurbsSet }
     * {@link ProtoInstance }
     * {@link Box }
     * {@link Cone }
     * {@link Cylinder }
     * {@link IndexedFaceSet }
     * {@link IndexedLineSet }
     * {@link IndexedTriangleFanSet }
     * {@link IndexedTriangleSet }
     * {@link IndexedTriangleStripSet }
     * {@link LineSet }
     * {@link PointSet }
     * {@link Sphere }
     * {@link TriangleFanSet }
     * {@link TriangleSet }
     * {@link TriangleStripSet }
     * {@link ElevationGrid }
     * {@link Polyline2D }
     * {@link Polypoint2D }
     * {@link Rectangle2D }
     * {@link TriangleSet2D }
     * {@link Extrusion }
     * {@link Text }
     * {@link Arc2D }
     * {@link ArcClose2D }
     * {@link Circle2D }
     * {@link Disk2D }
     * {@link QuadSet }
     * {@link IndexedQuadSet }
     * {@link GeoElevationGrid }
     * {@link NurbsCurve }
     * {@link NurbsCurve2D }
     * {@link NurbsPatchSurface }
     * {@link NurbsSweptSurface }
     * {@link NurbsSwungSurface }
     * {@link NurbsTrimmedSurface }
     * {@link Color }
     * {@link ColorRGBA }
     * {@link Coordinate }
     * {@link CoordinateDouble }
     * {@link FontStyle }
     * {@link GeoCoordinate }
     * {@link Normal }
     * {@link TextureCoordinate }
     * {@link HAnimDisplacer }
     * {@link Contour2D }
     * {@link ContourPolyline2D }
     * {@link NurbsTextureCoordinate }
     * {@link BallJoint }
     * {@link CollidableOffset }
     * {@link CollisionCollection }
     * {@link CollisionSpace }
     * {@link Contact }
     * {@link DoubleAxisHingeJoint }
     * {@link MotorJoint }
     * {@link RigidBody }
     * {@link SingleAxisHingeJoint }
     * {@link SliderJoint }
     * {@link UniversalJoint }
     * 
     * 
     */
    public List<X3DNode> getChildObjects() {
        if (childObjects == null) {
            childObjects = new ArrayList<X3DNode>();
        }
        return this.childObjects;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the accessType property.
     * 
     * @return
     *     possible object is
     *     {@link AccessTypeNames }
     *     
     */
    public AccessTypeNames getAccessType() {
        return accessType;
    }

    /**
     * Sets the value of the accessType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessTypeNames }
     *     
     */
    public void setAccessType(AccessTypeNames value) {
        this.accessType = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link FieldTypeName }
     *     
     */
    public FieldTypeName getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldTypeName }
     *     
     */
    public void setType(FieldTypeName value) {
        this.type = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link SFString }
     *     
     */
    public SFString getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFString }
     *     
     */
    public void setValue(SFString value) {
        this.value = value;
    }

    /**
     * Gets the value of the appinfo property.
     * 
     * @return
     *     possible object is
     *     {@link SFString }
     *     
     */
    public SFString getAppinfo() {
        return appinfo;
    }

    /**
     * Sets the value of the appinfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFString }
     *     
     */
    public void setAppinfo(SFString value) {
        this.appinfo = value;
    }

    /**
     * Gets the value of the documentation property.
     * 
     * @return
     *     possible object is
     *     {@link SFString }
     *     
     */
    public SFString getDocumentation() {
        return documentation;
    }

    /**
     * Sets the value of the documentation property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFString }
     *     
     */
    public void setDocumentation(SFString value) {
        this.documentation = value;
    }

}
