package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        /**
         *
         * ApplicationContext 를 스프링컨테이너라고 한다
         * 스프링 컨테이너는 @Configuration 이 붙은 AppConfig 를 구성정보로 사용하고
         * 여기서 @Bean이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다 이렇게 등록된 객체를 스프링빈이라한다.
         * 스프링 빈은 @Bean 이 붙은 메서드의 명을 스프링빈의 이름으로 사용한다 ( @Bean(name="") ) 이렇게 이름을바꿀수도있지만 관례상 디폴트값으로 사용한다.
         * 스프링 컨테이너는 xml 기반으로 만들수도있고 어노테이션기반으로도 만들수있다.
         * AppConfig는 어노테이션기반설정
         *
         * ※참고
         * 정확하게는 BeanFactory, ApplicationContext 로 구분해서 이야기하지만
         * BeanFactory 를 직접 사용하는경우는 거의없으므로 ApplicationContext 를 스프링컨테이너라고 한다
         *
         * */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);
        System.out.println("order = " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
}
