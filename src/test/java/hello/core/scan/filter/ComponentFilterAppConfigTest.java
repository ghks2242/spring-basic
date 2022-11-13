package hello.core.scan.filter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();
        org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanB.class));
    }

    @Configuration
    @ComponentScan(
            // type 5가지종류
            // type = FilterType.ANNOTATION 기본값이라 생략가능
            // type = FilterType.ASSIGNABLE_TYPE 지정한 타입과 자식 타입을 인식해서 동작
            // type = FilterType.ASPECTJ Aspectj 패턴사용
            // type = REGEX 정규표현식 ex) org\.example\.Default.*
            // type = CUSTOM TypeFilter 라는 인터페이스를 구현해서 처리 ex) org.example.MyTypeFilter

            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }

}
