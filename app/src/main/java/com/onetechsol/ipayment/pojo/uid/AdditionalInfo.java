package com.onetechsol.ipayment.pojo.uid;

import com.onetechsol.ipayment.pojo.Param;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "additional_info")
public class AdditionalInfo {

    @ElementList(name = "Param", required = false, inline = true)
    public List<Param> params;

    public AdditionalInfo() {
    }
}
