package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("service")
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository memberRepository;
	
	/* **@Autowired - 의존관계 자동 주입
	 *  - 스프링 컨테이너가 자동으로 해당하는 스프링 빈을 찾아서 의존관계를 주입
	 *  - 기본 조회 전략 : 타입이 같은 스프링 빈을 찾음
	 */

	// ** 생성자 주입
	@Autowired
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}
	
	
	// test용
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}

}
