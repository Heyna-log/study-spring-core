package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
	
	private final MemberRepository memberRepository = new MemoryMemberRepository();
	private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
	

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		// 회원 정보 조회
		Member member = memberRepository.findById(memberId);
		
		// 회원 등급에 따른 할인 가격 찾기
		int discountPrice = discountPolicy.discount(member, itemPrice);
		
		// 주문 반환
		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	
}
