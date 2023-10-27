package com.onetechsol.ipayment.pojo;

import com.onetechsol.ipayment.pojo.uid.Data;
import com.onetechsol.ipayment.pojo.uid.DeviceInfo;
import com.onetechsol.ipayment.pojo.uid.Resp;
import com.onetechsol.ipayment.pojo.uid.Skey;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "PidData")
public class PidData {

    @Element(name = "Resp", required = false)
    public Resp resp;
    @Element(name = "DeviceInfo", required = false)
    public DeviceInfo deviceInfo;
    @Element(name = "Skey", required = false)
    public Skey skey;
    @Element(name = "Hmac", required = false)
    public String hmac;
    @Element(name = "Data", required = false)
    public Data data;

    public PidData() {
    }

}
