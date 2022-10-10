package scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

//        컴포넌트 스캔은 컴포넌트가 붙은 모든 클래스를 스프링빈으로 등록한다
//        빈이름 기본전략 : MemberServiceImpl -> memberServiceImpl 맨앞글자만 소문자가된다
//        빈이름 직접지정 : @Component("memberService2") 이런식으로 이름을 지정한다

//        @Autowired 를 지정하면 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입한다.
//        이떄 기본 조회 전략은 타입이 같은 빈을 찾아서 주입한다.
//        ac.getBean(MemberRepository.class); 와 동일하다고 이해하면된다.
    }
}
