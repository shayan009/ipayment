package com.onetechsol.ipayment.pojo;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by SW11 on 3/4/2017.
 */
@Root(name = "CustOpts")
public class CustOpts {

    @ElementList(name = "Param", required = false, inline = true)
    public List<Param> params;

    public CustOpts() {
    }
}
