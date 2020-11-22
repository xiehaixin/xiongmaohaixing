package com.xiongmaohaixin.bean;

import java.util.List;

/**
 * ClassName:MaoTaiShopsBean
 * Package:com.evian.sqct
 * Description:请为该功能做描述
 *
 * @Date:2020/11/4 11:38
 * @Author:XHX
 */
public class MaoTaiShopsBean extends BaseMaoTaiBean {
    private List<MaoTaiShop> data;

    public List<MaoTaiShop> getData() {
        return data;
    }

    public void setData(List<MaoTaiShop> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MaoTaiShopsBean [" +
                "code=" + code +
                ", message=" + message +
                ", data=" + data +
                ']';
    }
}
