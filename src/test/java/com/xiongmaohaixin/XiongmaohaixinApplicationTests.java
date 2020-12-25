package com.xiongmaohaixin;

import com.xiongmaohaixin.service.IAnalysisMessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XiongmaohaixinApplicationTests {

    @Autowired
    private IAnalysisMessageService analysisMessageService;

    @Test
    void contextLoads() {
        analysisMessageService.haoshuiCustomSend("测试");
    }

}
