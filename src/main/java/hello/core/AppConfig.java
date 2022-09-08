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

public class AppConfig {

    /**
     * AppConfig는 애플리케이션의 실제동작의 필요한 구현객체를 생성한다.
     * AppConfig는 생성한객체의 인스턴스를 참조(래퍼런스를) 생성자를 통해 주입해준다.
     */

    // 생성자를통해서 객체를 주입 => 생성자주입
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    // 생성자를통해서 객체를 주입 => 생성자주입
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    /**
     * AppConfig 리팩토링
     * */
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        /**
         * 정책변환
         * */
        return new RateDiscountPolicy();
    }



}
