package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

// 정액 할인 정책
public class FixDiscountPolicy implements DiscountPolicy {
	
	private int discountFixAmount = 1000; // 1000원 할인

	@Override
	public int discount(Member member, int price) {
		
		// 회원 등급이 VIP이면 할인금액이 1000원, 아니면 0원
		if (member.getGrade() == Grade.VIP) {
			return discountFixAmount;
		} else {			
			return 0;
		}
	}

}
