package com.xiongmaohaixin.bean;

/**
 * ClassName:MaoTaiProductsBean
 * Package:com.evian.sqct
 * Description:请为该功能做描述
 *
 * @Date:2020/11/4 14:57
 * @Author:XHX
 */
public class MaoTaiProductsBean extends BaseMaoTaiBean {
    private MaoTaiProductsData data;

    public MaoTaiProductsData getData() {
        return data;
    }

    public void setData(MaoTaiProductsData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MaoTaiProductsBean [" +
                "data=" + data +
                ", code=" + code +
                ", message=" + message +
                ']';
    }
}
