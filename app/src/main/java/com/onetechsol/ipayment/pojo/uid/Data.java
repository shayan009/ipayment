package com.onetechsol.ipayment.pojo.uid;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

/**
 * Created by SW11 on 9/3/2015.
 */
public class Data {

    @Attribute(name = "type", required = false)
    public String type;
    @Text(required = true)
    public String value;

    public Data() {
    }

}
