package hello.core.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;

public class ApplicationContextInfoTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@Test
	@DisplayName("등록된 모든 스프링 빈 출력하기")
	void findAllBean() {
		
		// getBeanDefinitionNames() : 스프링에 등록된 모든 빈 이름 조회
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		
		// getBean(String BeanName) : 빈 이름으로 빈 객체(인스턴스) 조회
		for (String beanDefinitionName : beanDefinitionNames) {
			Object bean = ac.getBean(beanDefinitionName);
			System.out.println("name : " + beanDefinitionName + " | object : " + bean);
		}
	}
	
	@Test
	@DisplayName("내가 등록한 애플리케이션 스프링 빈 출력하기")
	void findApplicationBean() {
		
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		
		for (String beanDefinitionName : beanDefinitionNames) {
			
			// 빈의 메타 데이터 정보
			BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
			
			/* **Role 종류 
			 * ROLE_APPLICATION : 애플리케이션 개발을 위해 직접 등록한 빈
			 * ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
			 */
			
			// 직접 등록한 빈만 출력
			if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				Object bean = ac.getBean(beanDefinitionName);
				System.out.println("name : " + beanDefinitionName + " | object : " + bean);
			}
		}
	}
}
