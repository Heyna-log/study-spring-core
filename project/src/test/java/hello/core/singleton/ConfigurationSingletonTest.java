package hello.core.singleton;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;

public class ConfigurationSingletonTest {

	ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@Test
	void configurationTest() {
		MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);
		MemberRepository memberRepository = ac.getBean(MemberRepository.class);
		
		MemberRepository MSMemberRepository = memberService.getMemberRepository();
		MemberRepository OSMemberRepository = orderService.getMemberRepository();
		
		System.out.println("memberService -> memberRepository : " + MSMemberRepository);
		System.out.println("orderService -> memberRepository : " + OSMemberRepository);
		System.out.println("memberRepository : " + memberRepository);
		
		assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
		assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
		
		/* 
		 * AppConfig의 자바 코드대로라면 
		 * 'new MemberRepository'가 총 세 번 호출되고,
		 * memberRepository 객체가 3개 생성이 되어 싱글톤 보장이 되지 않는다.
		 * 
		 * 하지만, 테스트 출력 결과를 보면 
		 * 'new MemberRepository'는 한 번만 호출되고, 
		 * memberRepository 객체가 1개만 생성되어 싱글톤 보장이 되는 것을 알 수 있다.
		 * 
		 * */
	}
	
	@Test
	void configurationDeep() {
		AppConfig bean = ac.getBean(AppConfig.class);
		
		// AppConfig 런타임 클래스 객체 확인
		System.out.println("been : " + bean.getClass());
		
		/* 예상 출력 결과 : class hello.core.AppConfig
		 * 실제 출력 결과 : class hello.core.AppConfig$$EnhancerBySpringCGLIB$$291fa58a
		 * 
		 * AppConfig에 @Configuration을 적용하면
		 * 바이트코드 조작 라이브러리 'CGLIB'를 통해 AppConfig 클래스를 상속받은 임의의 다른 클래스(AppConfig@CGLIB)를 만들고, 
		 * 이 임의의 클래스를 스프링 빈으로 등록함.
		 * 임의의 클래스를 통해 싱글톤이 보장됨.
		 * 
		 * *AppConfig@CGLIB는 AppConfig의 자식 타입이므로, AppConfig 타입으로 조회할 수 있다.
		 * 
		 * --------------------------------------
		 * 
		 * **AppConfig@CGLIB 예상 코드
		 ```
		 @Bean
		 public MemberRepository memberRepository() {
			 if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있으면?) {
			  	 return 스프링 컨테이너에서 찾아서 반환;
			 } else { // <- 스프링 컨테이너에 없으면
			  	 기존 로직(기존 AppConfig 클래스에 있는 memberRepository 메소드)을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록
				 return 반환
			 }
		 }
		 ```
		 * @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고,
		 * 스프링 빈이 없으면 생성해서 스프링빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.
		 * => 싱글톤 보장
		*/
	}
}
