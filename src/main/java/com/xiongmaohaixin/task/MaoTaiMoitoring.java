package com.xiongmaohaixin.task;

import com.xiongmaohaixin.bean.MaoTaiProductsBean;
import com.xiongmaohaixin.bean.MaoTaiProductsRows;
import com.xiongmaohaixin.bean.MaoTaiShop;
import com.xiongmaohaixin.bean.MaoTaiShopsBean;
import com.xiongmaohaixin.service.IAnalysisMessageService;
import com.xiongmaohaixin.service.IMaoTaiService;
import com.xiongmaohaixin.utils.GZH_mao_tai;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:MaoTaiMoitoring
 * Package:com.xiongmaohaixin.task
 * Description:茅台定时
 *
 * @Date:2020/11/17 11:05
 * @Author:XHX
 */
@Component
public class MaoTaiMoitoring {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<MaoTaiShop> tempShops = new ArrayList<>();

    private GZH_mao_tai mt = new GZH_mao_tai();

    @Autowired
    private IMaoTaiService maoTaiService;

    @Autowired
    private IAnalysisMessageService analysisMessageService;


    /**
     * 第一次延迟1秒后执行，之后按fixedRate的规则30秒执行一次
     */
    @Scheduled(initialDelay=1000, fixedRate=20000)
    public void timing() throws IOException {
        String token = maoTaiService.getCode();
        if(StringUtils.isBlank(token)){
            return;
        }
        // http 请求查询店铺
        MaoTaiShopsBean maoTaiShopsBean = mt.monitoringMaoTaiShops(token+"#"+analysisMessageService.getXHXMaoTaiOpenId(),token);
        if(!"000".equals(maoTaiShopsBean.getCode())){
            // 请求异常
            logger.info("{}",maoTaiShopsBean);
            maoTaiService.clearCode();
//            displayTray(maoTaiShopsBean.getMessage(),maoTaiShopsBean.getCode());
            analysisMessageService.haoshuiCustomSend(analysisMessageService.getHaoshuiXHXOpenId(),maoTaiShopsBean.getMessage());
            return;
        }
        String shops = "";
        List<MaoTaiShop> data = maoTaiShopsBean.getData();
        // 差集 用来对比是否新增了店铺
        List<MaoTaiShop> difference = new ArrayList<>();
        for(MaoTaiShop shop : data){
            shops += shop.getV()+"="+shop.getK()+",";
            if("广东自营店".equals(shop.getV())){
                logger.info("[广东自营店来了！！！！！！！]");
                // http 请求查询广东自营店的商品
                MaoTaiProductsBean maoTaiProductsBean = mt.helpBoxShopId(token + "#" + analysisMessageService.getXHXMaoTaiOpenId(), token, shop.getK());
                logger.info("{}",maoTaiProductsBean);
                String base = "";
                for (MaoTaiProductsRows row : maoTaiProductsBean.getData().getRows()){
                    // 去掉空格，去掉回车
                    String tempbase = "<img src=\"data:image/png;base64,"+row.getWje().replace("\\s+", "").replace("(?s)'.*'","")+"\">";
                    base += tempbase+"，";
                }
                // 打印图片
                logger.info("[base:{}]",base);
                logger.info("[广东自营店来了！！！！！！！]");
//                displayTray("广东自营店来了！！！！！！！","total:"+maoTaiProductsBean.getData().getTotal());
                analysisMessageService.haoshuiCustomSend("广东自营店来了！！！！！！！total:"+maoTaiProductsBean.getData().getTotal());
            }
            // 进行对比
            if(tempShops.size()>0){
                for(int i =0;i<tempShops.size();i++){
                    MaoTaiShop temp = tempShops.get(i);
                    if(temp.getV().equals(shop.getV())){
                        break;
                    }
                    if(i==tempShops.size()-1){
                        difference.add(shop);
                    }
                }
            }
        }
        if(tempShops.size()==0){
            difference = data;
        }
        tempShops = data;
        // 查询 新增店铺的商品
        difference.stream().forEach(shop -> {
            try {
                // http 请求查询商品
                MaoTaiProductsBean maoTaiProductsBean = mt.helpBoxShopId(token + "#" + analysisMessageService.getXHXMaoTaiOpenId(), token, shop.getK());
                if(!"000".equals(maoTaiProductsBean.getCode())){
                    logger.info("{}",maoTaiProductsBean);
                }
                String base = "";
                for (MaoTaiProductsRows row : maoTaiProductsBean.getData().getRows()){
                    // 去掉空格，去掉回车
                    String tempbase = "<img src=\"data:image/png;base64,"+row.getWje().replace("\\s+", "").replace("(?s)'.*'","")+"\">";
                    base += tempbase+"，";
                }
                logger.info("[shopName:{}][total:{}] [base:{}]",shop.getV(),maoTaiProductsBean.getData().getTotal(),base);
//                displayTray("新增："+shop.getV(),"total:"+maoTaiProductsBean.getData().getTotal());
            } catch (IOException e) {
                logger.error("{}",e);
            }
        });
        logger.info(shops);
    }

}
