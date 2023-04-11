package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class StatefulServiceTest {

	/*
	 * ** 싱글톤 방식의 주의점
	 *  => 상태를 유지(stateful)하게 설계하면 안된다!! 즉, 무상태(stateless)로 설계해야 한다!!!
	 *   - 특정 클라이언트에 의존적인 필드가 있으면 안된다.
	 *   - 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다.
	 *   - 가급적 읽기만 가능해야 한다.
	 *   - 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
	 *  
	 * */
	
	@Test
	void statefulServiceSingleton() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
		StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);
		
		// ThreadA: 사용자A가 10000원 주문
		int userAPrice = statefulService1.order("userA", 10000);
		
		// ThreadB: 사용자B가 20000원 주문
		int userBPrice = statefulService2.order("userB", 20000);
		
		// ThreadA: 사용자A가 주문한 금액 조회
		System.out.println("priceA : " + userAPrice);
		
		
		// 사용자A가 주문한 금액이 사용자B가 주문한 금액에 영향을 받지 않음
		assertThat(userAPrice).isEqualTo(10000);
	}
	
	// test용 config
	@Configuration
	static class TestConfig {
		
		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}
}
