package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(
//        basePackages = "hello.core", // 탐색할패키지의 시작위치를 지정한다. 이패키지를 포함해서 하위패키지를 모두탐색한다 {"",""} 로 여러개의 시작위치를 지정할수있다
//        basePackageClasses = AutoAppConfig.class, // 지정한 클래스의 패키지를 탐색 시작 위로 지정한다.
//        아무것도 지정하지않으면 default 값은 ComponentScan이 붙은 클래스(AutoAppConfig)가 속한패키지(hello.core) 가 시작위치가된다.
/*
컴포넌트 스캔은 @ComponentScan 뿐만아니라 다음과 내용도 추가로 대상에 포함한다.
    - @Component    컴포넌트 스캔에서 사용
    - @Controller   스프링 mvc 컨트롤러에서 사용
    - @Service      스프링 비지니스 로직에서 사용
    - @Repository   스프링 데이터 접근계층에서 사용
    - @Configuration    스프링 설정정보에서 사용

    위에 어노테이션안에는 전부 @Component 어노테이션이 지정되어있다
    하지만 어노테이션에는 상속관계라는 것이없다 그래서 어노테이션이 어노테이션을 들고있는것을 인식할수있는것은
    java 언어가 지원하는 기능이 아니라 스프링이 지원하는 기능이다.

    컴포넌트 스캔의 용도뿐만아니라 다음 어노테이션이 있으면 스프링은 부가기능을 수행한다.
    @Controller   스프링 mvc 컨트롤러로 인식
    @Service      사실 서비시는 특별한 처리를 하지않는다 하지만 개발자들이 핵심 비즈니스 로직이 여기에 있겠구나 라고 비즈니스 계층을 인식하는데 도움이된다
    @Repository   스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해준다.
    @Configuration  앞서 보았듯이 스프링 설정정보로 인식하고, 스프링빈이 싱글톤을 유지하도록 추가처리를한다.

    참고로 useDefaultFilters 옵션은 기본으로 켜져있는데 이옵션을 끄면 기본 스캔 대상들이 제외된다.

*/
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
//        includeFilters 컴포넌트 스캔 대상을 추가로 지정한다.
//        excludeFilters 컴포넌트 스캔 제외할 대상을 지정한다.
// AppConfig 와 TestConfig 등 예제코드를 해치지않으려고 컴포넌트스캔 대상에서 제외
// 컴포넌트스캔은 @Component 붙은 클래스를 스캔해서 스프링빈으로 등록한다.
// Configuration 이 컴포너틑 스캔이 대상이 된이유도 소스코드를 열어보면 @Component 어노테이션이 붙어있기떄문이다.
public class AutoAppConfig {

}
