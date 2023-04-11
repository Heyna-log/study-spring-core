package hello.core.singleton;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hello.core.AppConfig;
import hello.core.member.MemberService;

public class SingletonTest {

	@Test
	@DisplayName("스프링 없는 순수한 DI 컨테이너")
	void pureContainer() {
		AppConfig appConfig = new AppConfig();
		
		// 1. 조회 : 호출할 때마다 객체를 생성
		MemberService memberService1 = appConfig.memberService(); // 호출1
		MemberService memberService2 = appConfig.memberService(); // 호출2
		
		// 2. 참조값이 다른 것을 확인
		System.out.println("memberService1 : " + memberService1);
		System.out.println("memberService2 : " + memberService2);
		
		// memberService1 != memberService2
		assertThat(memberService1).isNotSameAs(memberService2);
		
		/*
		 * AppConfig(스프링 없는 순수한 DI 컨테이너)는 요청을 할 때마다 새로운 객체를 생성
		 * ex) 고객 트래픽이 초당 100이 나오면 초당 100개 객체가 생성되고 소멸됨
		 * => 메모리 낭비가 심함
		 * 
		 * **메모리 낭비를 해결하려면
		 * 요청수와 상관없이 해당 객체가 1개만 생성되고 공유하도록 설계 ==> 싱글톤 패턴
		 * */
	}
	
	@Test
	@DisplayName("싱글톤 패턴을 적용한 객체 사용")
	void singletonServiceTest() {
		
		// private 생성자이므로 new를 이용해서 객체 생성 불가능
//		new SingletonService(); 
		
		// static 메서드를 통해 객체 조회 가능
		// 호출할 때마다 같은 객체 인스턴스 반환
		SingletonService singletonService1 = SingletonService.getInstance();
		SingletonService singletonService2 = SingletonService.getInstance();
		
		// 참조값이 같은 것을 확인
		System.out.println("singletonService1 : " + singletonService1);
		System.out.println("singletonService2 : " + singletonService2);
		
		// singletonService1 == singletonService2
		assertThat(singletonService1).isSameAs(singletonService2);
	}
}
