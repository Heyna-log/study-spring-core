package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/* **@ComponentScan
 *  - @Component가 붙은 클래스를 스캔해서 스프링 빈으로 등록
 *  - 스프링 빈의 기본 이름은 클래스명을 사용하되, 맨 앞글자만 소문자로 바꾸어 사용(MemberServiceImpl 클래스 -> memberServiceImpl)
 *  - 스프링 빈의 이름을 직접 지정하는 경우, @Component("지정할 빈 이름")로 지정 가능
 *  
 * **컴포넌트 스캔 기본 대상 => @Component가 포함되어 있는 어노테이션
 *  - @Component : 컴포넌트 스캔에서 사용
 *  - @Controller : 스프링 MVC 컨트롤러에서 사용
 *  - @Service : 스프링 비즈니스 로직에서 사용
 *  - @Repository : 스프링 데이터 접근 계층에서 사용
 *  - @Configuration : 스프링 설정 정보에서 사용
 */

@Configuration // @Configuration 안에 @Component가 포함되어 있어서 컴포넌트 스캔 대상이 됨
@ComponentScan(
	// 탐색할 패키지의 시작 위치 지정
	// 시작 위치를 여러개 지정할 수도 있음. ex) basePakages = {"hello.core", "hello.service"}
	basePackages = "hello.core", // core 패키지와 그 하위 패키지들을 탐색
	
	// 지정한 클래스의 패키지를 탐색 시작 위치로 지정
	basePackageClasses = AutoAppConfig.class, // AutoAppConfig.class의 패키지는 hello.core
	
	// **탐색 시작 위치를 지정하지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치가 됨. => 시작 위치를 지정하지 않고, 설정 정보 클래스를 프로젝트 최상단에 두는 방법을 권장
		
	// 이전까지 사용한 @Configuration 코드는 컴포넌트 스캔 대상에서 제외(AppConfig, TestConfig)
	excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
	
}