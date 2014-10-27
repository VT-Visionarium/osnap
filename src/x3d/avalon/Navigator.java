/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package x3d.avalon;

import x3d.model.*;
import x3d.fields.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Peter Radics
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Navigator")
public abstract class Navigator 
        extends X3DNode {

    // <editor-fold defaultstate="collapsed" desc="Fields">

    @XmlAttribute(name = "enabled")
    protected SFBool enabled;
    
    @XmlAttribute(name = "message")
    protected SFString message;
    
    @XmlAttribute(name = "keyDic")
    protected MFString keyDic;
    
    @XmlAttribute(name = "keyPress")
    protected SFInt32 keyPress;
    
    @XmlAttribute(name = "keyRelease")
    protected SFInt32 keyRelease;
    
    @XmlAttribute(name = "logFeature")
    protected MFString logFeature;
    
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Properties">

    
    public SFBool getEnabled() {
        if (enabled == null) {
            enabled = SFBool.TRUE;
        }
        return enabled;
    }

    public void setEnabled(SFBool enabled) {
        this.enabled = enabled;
    }

    public SFString getMessage() {
        if (message == null) {
            message = new SFString();
        }
        return message;
    }
    
    public void setMessage(SFString message) {
        this.message = message;
    }

    
    public MFString getKeyDic() {
        if (keyDic == null) {
            keyDic = new MFString ();
            String keyMapping = "u straightenUp";
            keyMapping += ", G dumpScreen";
            keyMapping += ", d dumpKeyMapping";
            keyMapping += ", D dumpMessageList";
            keyMapping += ", q noneNav";
            keyMapping += ", T toggleSortTrans";
            keyMapping += ", ENTER toggleFullScreen";
            keyMapping += ", t nextShadowMode";
            keyMapping += ", x toggleGlobalShadow";
            keyMapping += ", h toggleHeadlight";
            keyMapping += ", I toggleCollision";
            keyMapping += ", i toggleLazyInteractionEvaluation";
            keyMapping += ", a showAll";
            keyMapping += ", v toggleDrawVolume";
            keyMapping += ", c toggleCullFrustum";
            keyMapping += ", C toggleCullBackFace";
            keyMapping += ", S toggleCullSmallFeature";
            keyMapping += ", o toggleCullOcclusion";
            keyMapping += ", O nextOcclusionCullMode";
            keyMapping += ", [ decreaseCullFeature";
            keyMapping += ", ] increaseCullFeature";
            keyMapping += ", m nextDrawMode";
            keyMapping += ", SPACE nextInfoScreen";
            keyMapping += ", HOME firstView";
            keyMapping += ", END lastView";
            keyMapping += ", PGDN nextView";
            keyMapping += ", PGUP prevView";
            keyMapping += ", r resetViewPosition";
            keyMapping += ", w walkNav";
            keyMapping += ", scale slideNav";
            keyMapping += ", p panNav";
            keyMapping += ", f flyNav";
            keyMapping += ", F freeFlyNav";
            keyMapping += ", e examineNav";
            keyMapping += ", E geoExamineNav";
            keyMapping += ", g gameNav";
            keyMapping += ", l lookatNav";
            keyMapping += ", UP forward";
            keyMapping += ", DOWN backward";
            keyMapping += ", LEFT left";
            keyMapping += ", RIGHT right";
            keyMapping += ", R reload";
            keyMapping += ", n exportBackendASC";
            keyMapping += ", N exportBackendBIN";
            keyMapping += ", + increaseNavSpeed";
            keyMapping += ", - decreaseNavSpeed";
            keyMapping += ", B toggleFastRayIntersect";
            keyMapping += ", b backendWebInterface";
            keyMapping += ", { prevNavMode";
            keyMapping += ", } nextNavMode";
            keyMapping += ", ESC escape";
            keyDic.add(keyMapping);
                   
        }
        return keyDic;
    }

    public SFInt32 getKeyPress() {
        if (keyPress == null) {
            keyPress = new SFInt32();
        }
        return keyPress;
    }
    
    public void setKeyPress(SFInt32 keyPress) {
        this.keyPress = keyPress;
    }    
    
    public SFInt32 getKeyRelease() {
        if (keyRelease == null) {
            keyRelease = new SFInt32();
        }        
        return keyRelease;
    }
    
    public void setKeyRelease(SFInt32 keyRelease) {
        this.keyRelease = keyRelease;
    }    

    public MFString getLogFeature() {
        if (logFeature == null) {
            logFeature = new MFString ();
        }
        return logFeature;
    }

    
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Construction/Destruction/Initialization">

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // </editor-fold>


}
