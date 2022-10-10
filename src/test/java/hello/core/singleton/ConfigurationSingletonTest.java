package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService ->  memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

        // 확인해보면 memberRepository 인스턴스는 모두 같은 인스턴스가 공유되어 사용된다
    }

    @Test
    void configurationDepp() {
        // AnnotationConfigApplicationContext 에 파라미터로 넘긴 값은 스프링 빈으로 등록된다. 그래서 AppConfig.class 도 스프링 빈이된다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);
//      AppConfig.class 를 조회해보자
        System.out.println("bean.getClass() = " + bean.getClass());

//      순수한 클래스 라면 다음과 같이 출력되어야한다 class hello.core.AppConfig

//      그런데 예상과 다르게 xxxCGLIB 가 붙으면서 복잡해진걸 볼수있다. 이것은 내가만든 클래스가 아니라
//      스프링빈이 바이트코드조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고 그 다른클래스 를 빈으로 등록한 것이다.
//      class hello.core.AppConfig$$EnhancerBySpringCGLIB$$50587f02
        /*
        그 임의의 다른 클래스가 바로 싱글톤이 보장되도록 해준다. 아마도 다음과 같이 바이트 코드를 조작해서 작성되어 있을것이다.
        (실제로는 CGLIB 내부 기술을 사용하는데 매우복잡하다.)
        */

        // 자식타입이 빈으로 올라갔는데 ac.getBean(AppConfig.class) 부모타입으로 조회되는 이유는
//        ApplicationContextExtendsFindTest 클래스에서 공부한대로 부모타입으로 조회해도 자식까지 다 조회할수있기떄문에
    }
}
