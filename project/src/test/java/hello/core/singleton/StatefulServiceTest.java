package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class StatefulServiceTest {

	/*
	 * ** �̱��� ����� ������
	 *  => ���¸� ����(stateful)�ϰ� �����ϸ� �ȵȴ�!! ��, ������(stateless)�� �����ؾ� �Ѵ�!!!
	 *   - Ư�� Ŭ���̾�Ʈ�� �������� �ʵ尡 ������ �ȵȴ�.
	 *   - Ư�� Ŭ���̾�Ʈ�� ���� ������ �� �ִ� �ʵ尡 ������ �ȵȴ�.
	 *   - ������ �б⸸ �����ؾ� �Ѵ�.
	 *   - �ʵ� ��ſ� �ڹٿ��� �������� �ʴ�, ��������, �Ķ����, ThreadLocal ���� ����ؾ� �Ѵ�.
	 *  
	 * */
	
	@Test
	void statefulServiceSingleton() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
		StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);
		
		// ThreadA: �����A�� 10000�� �ֹ�
		statefulService1.order("userA", 10000);
		
		// ThreadB: �����B�� 20000�� �ֹ�
		statefulService2.order("userB", 20000);
		
		// ThreadA: �����A�� �ֹ��� �ݾ� ��ȸ
		int priceA = statefulService1.getPrice();
		System.out.println("priceA : " + priceA);
		
		
		// �����A�� �ֹ��� �ݾ��� �����B�� �ֹ��� �ݾ��� 20000���� �Ǿ���� 
		assertThat(priceA).isEqualTo(20000);
	}
	
	// test�� config
	@Configuration
	static class TestConfig {
		
		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}
}
