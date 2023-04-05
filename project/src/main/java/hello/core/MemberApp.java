package hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

public class MemberApp {

	public static void main(String[] args) {
		
//		// 외부(AppConfig)를 통해 구현 객체를 가져옴
//		AppConfig appConfig = new AppConfig();
//		MemberService memberService = appConfig.memberService();
		
		/* 
		 * **스프링 컨테이너 생성
		 *  - new AnnotationConfigApplicationContext() 를 통해 자바 기반(어노테이션 기반 설정) 스프링 컨테이너 생성 
		 *  - 스프링 컨테이너를 생성할 때 설정(구성) 정보를 지정해주어야 함.
		 *  	- 설정(구성) 정보인 자바 설정 클래스(AppConfig.class)를 파라미터로 넘겨주면 자바 설정 클래스의 @Bean 메소드를 호출해서 반환된 객체를 빈 저장소에 빈으로 등록
		 *  
		 *  **빈 생명주기(Bean Life Cycle)
		 *  1. 스프링 컨테이너 생성
		 *  2. 설정(구성) 정보를 참고하여 스프링 빈 등록
		 *  3. 설정(구성) 정보를 참고하여 스프링 빈 의존관계 주입(Dependency Injection)
		 *  	* 하지만 지금처럼 자바 기반으로 빈을 등록하면 2,3단계로 나뉘지 않고 생성자를 호출하면서 의존관계 주입도 한번에 처리됨.
		 *  	  이후 의존관계 자동 주입 내용에서 자세한 설명 추가
		 *  
		 * 참고: https://velog.io/@max9106/Spring-ApplicationContext
		 */
		
		// 스프링 컨테이너
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
		// 스프링 빈(객체) 사용
		// @Bean 이 붙은 메서드의 명을 스프링 빈의 이름으로 사용한다.
		MemberService memberService = ac.getBean("memberService", MemberService.class); // getBean("스프링 빈 이름", 객체 타입)
		
		Member member = new Member(1L, "memberA", Grade.VIP);
		
		memberService.join(member); // 회원가입
		
		Member findMember = memberService.findMember(1L);
		System.out.println("new Member = " + member.getName());
		System.out.println("find Member = " + findMember.getName());
	}

}
