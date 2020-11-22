package com.xiongmaohaixin.bean;

import java.util.List;

/**
 * ClassName:MaoTaiProductsData
 * Package:com.evian.sqct
 * Description:请为该功能做描述
 *
 * @Date:2020/11/4 15:18
 * @Author:XHX
 */
public class MaoTaiProductsData {
    private Integer total;
    private List<MaoTaiProductsRows> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<MaoTaiProductsRows> getRows() {
        return rows;
    }

    public void setRows(List<MaoTaiProductsRows> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "MaoTaiProductsBean [" +
                "total=" + total +
                ", rows=" + rows +
                ']';
    }
}
