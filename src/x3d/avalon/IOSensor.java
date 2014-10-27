/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package x3d.avalon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import x3d.fields.MFString;
import x3d.fields.SFInt32;
import x3d.fields.SFString;
import x3d.fields.SFTime;
import x3d.model.X3DSensorNode;

/**
 * 
 * @author Peter Radics
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "IOSensor")
public class IOSensor extends X3DSensorNode {

	// <editor-fold defaultstate="collapsed" desc="Fields">

	@XmlAttribute(name = "system")
	protected SFString system;

	@XmlAttribute(name = "type")
	protected SFString type;
	@XmlAttribute(name = "name")
	protected SFString name;

	@XmlAttribute(name = "triggerName")
	protected SFString triggerName;
	@XmlAttribute(name = "maxValuesPerTrigger")
	protected SFInt32 maxValuesPerTrigger;
	@XmlAttribute(name = "triggerSlot")
	protected SFTime triggerSlot;
	@XmlAttribute(name = "logFeature")
	protected MFString logFeature;

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Properties">

	public SFString getSystem() {
		if (system == null) {
			system = new SFString("auto");
		}
		return system;
	}

	public void setSystem(SFString system) {
		this.system = system;
	}

	public SFString getType() {
		if (type == null) {
			type = new SFString();
		}
		return type;
	}

	public void setType(SFString type) {
		this.type = type;
	}

	public SFString getName() {
		if (name == null) {
			name = new SFString();
		}
		return name;
	}

	public void setName(SFString name) {
		this.name = name;
	}

	public SFString getTriggerName() {
		if (triggerName == null) {
			triggerName = new SFString();
		}
		return triggerName;
	}

	public void setTriggerName(SFString triggerName) {
		this.triggerName = triggerName;
	}

	public SFInt32 getMaxValuesPerTrigger() {
		if (maxValuesPerTrigger == null) {
			maxValuesPerTrigger = new SFInt32(1);
		}

		return maxValuesPerTrigger;
	}

	public void setMaxValuesPerTrigger(SFInt32 maxValuesPerTrigger) {
		this.maxValuesPerTrigger = maxValuesPerTrigger;
	}

	public SFTime getTriggerSlot() {
		if (triggerSlot == null) {
			triggerSlot = new SFTime();
		}
		return triggerSlot;
	}

	public void setTriggerSlot(SFTime triggerSlot) {
		this.triggerSlot = triggerSlot;
	}

	public MFString getLogFeature() {
		if (logFeature == null) {
			logFeature = new MFString();
		}
		return logFeature;
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed"
	// desc="Construction/Destruction/Initialization">

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Public Methods">

	// </editor-fold>

}
