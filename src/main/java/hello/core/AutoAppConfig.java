package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
// AppConfig 와 TestConfig 등 예제코드를 해치지않으려고 컴포넌트스캔 대상에서 제외
// 컴포넌트스캔은 @Component 붙은 클래스를 스캔해서 스프링빈으로 등록한다.
// Configuration 이 컴포너틑 스캔이 대상이 된이유도 소스코드를 열어보면 @Component 어노테이션이 붙어있기떄문이다.
public class AutoAppConfig {

}
