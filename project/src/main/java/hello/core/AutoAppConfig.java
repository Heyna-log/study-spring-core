package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/* **@ComponentScan
 *  - @Component가 붙은 클래스를 스캔해서 스프링 빈으로 등록
 *  - 스프링 빈의 기본 이름은 클래스명을 사용하되, 맨 앞글자만 소문자로 바꾸어 사용(MemberServiceImpl 클래스 -> memberServiceImpl)
 *  - 스프링 빈의 이름을 직접 지정하는 경우, @Component("지정할 빈 이름")로 지정 가능
 */

@Configuration // @Configuration 안에 @Component가 포함되어 있어서 컴포넌트 스캔 대상이 됨
@ComponentScan(
	// 이전까지 사용한 @Configuration 코드는 컴포넌트 스캔 대상에서 제외(AppConfig, TestConfig)
	excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
