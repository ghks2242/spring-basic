package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    // 스프링 애플리케이션 실행시점에 싱글톤 빈은 생성해서 주입이 가능하지만 request 스코프 빈은 아직 생성되지않았다 이빈은 실제 고객이 요청이와야 생성할수있다
    // 이문제를 해결하기위해서 provider 를 사용

    private final LogDemoService logDemoService;
    private final MyLogger myLogger; // MyLogger 에 proxyMode = ScopedProxyMode.TARGET_CLASS 를 추가하면 다시 기존처럼 사용가능
//    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
//        MyLogger myLogger = myLoggerProvider.getObject();
        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURI);
        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
