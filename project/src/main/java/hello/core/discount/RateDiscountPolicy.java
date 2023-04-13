package hello.core.discount;

import org.springframework.stereotype.Component;

import hello.core.member.Grade;
import hello.core.member.Member;

// 정률 할인 정책
@Component
public class RateDiscountPolicy implements DiscountPolicy {
	
	private int discountPercent = 10; // 10% 할인

	// 할인되는 가격
	@Override
	public int discount(Member member, int price) {
		if (member.getGrade() == Grade.VIP) {
			return price * discountPercent / 100;
		} else {
			return 0;
		}
	}

}
