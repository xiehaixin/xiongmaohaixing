package com.xiongmaohaixin.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ClassName:MaoTaiShop
 * Package:com.evian.sqct
 * Description:请为该功能做描述
 *
 * @Date:2020/11/4 11:39
 * @Author:XHX
 */
public class MaoTaiShop {
    @JsonProperty("V")
    private String V;
    @JsonProperty("K")
    private String K;

    public String getV() {
        return V;
    }

    public void setV(String v) {
        V = v;
    }

    public String getK() {
        return K;
    }

    public void setK(String k) {
        K = k;
    }

    @Override
    public String toString() {
        return "MaoTaiShop [" +
                "V=" + V +
                ", K=" + K +
                ']';
    }
}
