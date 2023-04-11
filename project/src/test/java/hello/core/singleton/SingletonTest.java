package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

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
}
