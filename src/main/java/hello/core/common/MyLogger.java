package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
// proxyMode = ScopedProxyMode.TARGET_CLASS 추가하게되면 기존처럼 사용할수있다
//    - 적용 대상이 class 면 TARGET_CLASS 를 선택
//    - 적용 대상이 interface TARGET_INTERFACE 를 선택
//    - 이렇게하면 MyLogger 의 가짜 프록시 클래스를 만들어두고 HTTP request 와 상관없이 가짜 프록시 클래스를 다른빈에 미리 주입해둘수있다

//  가짜 프록시개체는 진짜 클래스를 상속받아서 만들어졌기떄문에 이 객체를 사용하는 클라이언트 입장에서는 사실 원본인지 아닌지도 모르게 동일하게 사용할수있다.(다형성)

    // 동작원리
    //  - CGLIB 라는 라이브러리 (예전 강의떄 나왔었음 스프링이 조작) 라이브러리로 내 클래스를 상속받은 가짜 프록시 객체를 만들어 주입한다.
    //  - 이 가짜 프록시 개체는 실제 요청이 오면 그떄 내부에서 실제 빈을 요청하는 위임 로직이 들어있다.
    //  - 가짜 프록시 객체는 실제 request scope 와는 관계가 없다 그냥 가짜이고 내부에 단순한 위임로직만 있고 싱글톤처럼 동작한다
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}
