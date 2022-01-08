package com.zhc.cloud.system.web.conteoller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhc.cloud.system.service.TestDataService;
import com.zhc.cloud.system.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * @author zhc
 * @description 测试
 * @date 2021-12-25 02:07
 */
@RestController
@RefreshScope
@Slf4j
public class TestController {


    @Autowired
    private TestService testService;
    @Autowired
    private TestDataService testDataService;

    @GetMapping("test1")
    public String test1(){
        log.info("test");
        return "testDataService.test()";
    }
    @GetMapping("test2")
    public String test2(){
        log.info("test2");
        return testDataService.selectPage();
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @SentinelResource(value = "test")
    public String test(HttpServletRequest request){
        String s = request.getHeaders("n-d-user-name").nextElement();
        String name = "";
        try {
            name = URLDecoder.decode(s, "UTF-8");
        }catch (Exception e){
            log.error("",e);
        }
        log.info(name);
        return testDataService.select().toString();
    }

}
