package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        //ThreadA : A사용자가 만원주문
        statefulService1.order("userA", 10000);
        //ThreadB : B사용자가 이만원주문
        statefulService2.order("userB", 20000);

        //ThreadA: 사용자A 가 주문금액을 조회
        int price = statefulService1.getPrice();

        System.out.println("price = " + price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

        // 무상태로 설계후다
        // 지역변수로 생성

        //ThreadA : A사용자가 만원주문
        int userAprice = statefulService1.orderStateless("userA", 10000);
        //ThreadB : B사용자가 이만원주문
        int userBprice = statefulService2.orderStateless("userB", 20000);

        System.out.println("userAprice = " + userAprice + " userBprice = " + userBprice);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}