package hello.core.scan.filter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {

	@Test
	void filterScan() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
		
		// 컴포넌트 스캔 대상이었으므로 스프링 빈으로 등록되어 조회 가능
		BeanA beanA = ac.getBean(BeanA.class);
		assertThat(beanA).isNotNull();
		
		// 컴포넌트 스캔 대상이 아니었으므로 스프링 빈으로 등록되지 않아 조회 불가능
//		BeanB beanB = ac.getBean(BeanB.class); // NoSuchBeanDefinitionException
		Assertions.assertThrows(
				NoSuchBeanDefinitionException.class, 
				() -> ac.getBean(BeanB.class)
		);
		
	}
	
	@Configuration
	@ComponentScan(
			// @MyIncludeComponent이 적용된 클래스를 컴포넌트 스캔 대상에 추가
			includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
					// @MyExcludeComponent이 적용된 클래스를 컴포넌트 스캔 대상에서 제외
			excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
			
			/* 
			 * **FilterType 옵션
			 *  - ANNOTATION : 기본값. 어노테이션을 인식해서 동작함.
			 *  - ASSIGNABLE_TYPE : 지정한 타입과 자식 타입을 인식해서 동작함. 클래스를 직접 지정할 수 있음.
			 *  - ASPECTJ : AspectJ 패턴 사용.
			 *  - REGEX : 정규 표현식
			 *  - CUSTOM : 'TypeFilter'라는 인터페이스를 구현해서 처리함.
			 */
		)
	static class ComponentFilterAppConfig {
		
	}
}
