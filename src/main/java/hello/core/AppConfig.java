package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // || spring 으로 전환하기
public class AppConfig {

    /**
     * AppConfig는 애플리케이션의 실제동작의 필요한 구현객체를 생성한다.
     * AppConfig는 생성한객체의 인스턴스를 참조(래퍼런스를) 생성자를 통해 주입해준다.
     */

//@Bean memberService -> new MemoryMemberRepository()
//@Bean orderService -> new MemoryMemberRepository()
// 과연 싱글톤이 유지될까?

//    이렇게 호출되어야할거같지만
//    call AppConfig.memberService
//    call AppConfig.memberRepository
//    call AppConfig.memberRepository
//    call AppConfig.orderService
//    call AppConfig.memberRepository

//    실제론 이렇게 호출된다
//    call AppConfig.memberService
//    call AppConfig.memberRepository
//    call AppConfig.orderService
    /*
    ## 스프링 컨테이너는 싱글톤 레지스트리다. 따라서 스프링 빈이 싱글톤이 되도록 보장해주어야한다. 그런데 스프링이 자바코드까지 어떻게 하기는어렵다.
    저 자바 코드를 보면 분명 3번호출되어야 하는것이맞다. 그래서 스프링은 클래스의 바이트코드를 조작하는 라이브러리를 사용한다.
    모든 비밀은 @Configuration 을 적용한 AppConfig 에 있다
    */

    @Bean // || spring 으로 전환하기
    // 생성자를통해서 객체를 주입 => 생성자주입
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean // || spring 으로 전환하기
    // 생성자를통해서 객체를 주입 => 생성자주입
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }


    @Bean // || spring 으로 전환하기
    /**
     * AppConfig 리팩토링
     * */
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean // || spring 으로 전환하기
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        /**
         * 정책변환
         * */
        return new RateDiscountPolicy();
    }



}
