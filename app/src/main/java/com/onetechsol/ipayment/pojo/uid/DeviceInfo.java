package com.onetechsol.ipayment.pojo.uid;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "DeviceInfo")
public class DeviceInfo {

    @Attribute(name = "dpId")
    public String dpId;

    //    @Attribute(name = "srno")
//    public String srno;
    @Attribute(name = "rdsId")
    public String rdsId;
    @Attribute(name = "rdsVer")
    public String rdsVer;
    @Attribute(name = "dc")
    public String dc;
    @Attribute(name = "mi")
    public String mi;
    @Attribute(name = "mc")
    public String mc;
    @Element(name = "additional_info")
    public AdditionalInfo addInfo;

    public DeviceInfo() {
    }

}
