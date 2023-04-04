package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

public class MemberApp {

	public static void main(String[] args) {
		
		// 외부(AppConfig)를 통해 구현 객체를 가져옴
		AppConfig appConfig = new AppConfig();
		MemberService memberService = appConfig.memberService();
		
		Member member = new Member(1L, "memberA", Grade.VIP);
		
		memberService.join(member); // 회원가입
		
		Member findMember = memberService.findMember(1L);
		System.out.println("new Member = " + member.getName());
		System.out.println("find Member = " + findMember.getName());
	}

}
