package hello.core.beanDefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

    /**
     * 스프링빈을 등록하는 방법에는 크게 2가지가있다
     * 직접 스프링빈을 등록하는경우 appConfig.xml
     * factoryMethod 를 통해서 등록하는방법 AppConfig
     * */
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    GenericXmlApplicationContext bc = new GenericXmlApplicationContext("appConfig.xml");

    /**
     *
     * BeanDefinition 정보
     * BeanClassName : 생성할 빈의 클래스명(자바설정 처럼 팩토리 역할의 빈을 사용하면없음)
     * factoryBeanName : 팩토리 역할의 빈을 사용할 경우 이름 ex) appConfig
     * factoryMethodName : 빈을 생성할 팩토리 메서드 지정 ex) memberService
     * Scope : 싱글톤(기본값)
     * lazyInit : 스프링 컨테이너를 생성할떄 빈을 생성하는 것이 아니라, 실제 빈을 사용할때 까지 최대한 생성을 지연처리하는지 여부
     * InitMethodName : 빈을 생성하고, 의존관계를 적용한 뒤에 호출되는 초기화 메서드명
     * DestroyMethodName : 빈의 생명주기가 끝나서 제거하기 직전에 호출되는 메서드명
     * Constructor arguments, Properties : 의존관계 주입에서 사용한다 (자바 설정처럼 팩토리 역할의 빈을 사용하면 없음)
     *
     * 정리
     * BeanDefinition 을 직접생성해서 스프링 컨테이너에 등록할수도있다 하지만 실무에서는 직접정의하거나 사용할일이 거의없다.
     * 스프링의 다양한형태의 설정정보를 BeanDefinition 으로 추상화 해서 사용하는 것 정도만 이해하면된다.
     * 가끔 스프링 코드나 스프링 관련 오픈소스의 코드를볼때, BeanDefinition 이라는 것이 보일때가 있다, 이떄 이러한 메커니즘을 떠올리면된다.
     * */
    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName + " beanDefinition = " + beanDefinition);
            }
        }
    }

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBeanXml() {
        String[] beanDefinitionNames = bc.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = bc.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName + " beanDefinition = " + beanDefinition);
            }
        }
    }
}
