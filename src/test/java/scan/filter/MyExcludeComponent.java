package scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)   // type 은 클래스 레벨에 붙는다
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}
