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
		 * @Configuration을 적용하지 않으면 AppConfig의 자바 코드대로
		 * 'new MemberRepository'가 총 세 번 호출되고,
		 * memberRepository 객체가 3개 생성이 되어 싱글톤 보장이 되지 않는다.
		 * 
		 * */
	}
	
	@Test
	void configurationDeep() {
		AppConfig bean = ac.getBean(AppConfig.class);
		
		// AppConfig 런타임 클래스 객체 확인
		System.out.println("been : " + bean.getClass());
		
		/* 
		 * 실제 출력 결과 : class hello.core.AppConfig
		 * 
		 * @Configuration을 적용하지 않으면
		 * AppConfig가 CGLIB 기술 없이 순수한 AppConfig로 스프링 빈에 등록됨.
		 * 
		*/
	}
}
