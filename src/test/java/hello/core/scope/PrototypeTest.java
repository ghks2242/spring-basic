package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

//    싱글톤 빈은 스프링 컨테이너 생성시점에서 초기화 메서드가 실행되지만, 프로토타입 스코프의 빈은 스프링 컨테이너에서 빈을 조회할때 생성되고 초기화 메서드도 실행된다.
//    프로토 타입 빈을 2번 조회했으므로 완전히 다른 스프링 빈이 생성되고, 초기화도 2번 실행된 것을 확인할 수 있다.
//    스프링빈은 스프링 컨테이너가 관리하기 떄문에 스프링 컨테이너가 종료될떄 빈의 종료 메서드가 실행되지만 프로토타입 빈은 스프링 컨테이너가 생성과 의존관계 주입
//    초기화 까지만 관여하고 더는 관리하지 않는다. 따라서 프로로타입 빈은 스프링 컨테이너가 종료될떄 @PreDestory 같은 종료 메서드가 실행되지않는다.

//    정말 destroy 를 호출해야하면 수작업으로 해야한다
//    ex) prototypeBean1.destroy();

//    ##특징
//        - 스프링 컨테이너에 요청할 떄마다 새로 생성된다.
//        - 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입 그리고 초기화까지만 관여한다.
//        - 종료 메서드가 호출되지 않는다.
//        - 그래서 프로토타입빈은 프로토타입빈을 조회한 클라이언트가 관리해야한다 종료메서드에 대한 호출도 클라이언트가 직접해야한다.

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        prototypeBean1.destroy();
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.printf("PrototypeBean.destroy");
        }
    }
}
