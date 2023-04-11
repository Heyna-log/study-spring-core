package hello.core.singleton;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;

public class SingletonTest {

	@Test
	@DisplayName("������ ���� ������ DI �����̳�")
	void pureContainer() {
		AppConfig appConfig = new AppConfig();
		
		// 1. ��ȸ : ȣ���� ������ ��ü�� ����
		MemberService memberService1 = appConfig.memberService(); // ȣ��1
		MemberService memberService2 = appConfig.memberService(); // ȣ��2
		
		// 2. �������� �ٸ� ���� Ȯ��
		System.out.println("memberService1 : " + memberService1);
		System.out.println("memberService2 : " + memberService2);
		
		// memberService1 != memberService2
		assertThat(memberService1).isNotSameAs(memberService2);
		
		/*
		 * AppConfig(������ ���� ������ DI �����̳�)�� ��û�� �� ������ ���ο� ��ü�� ����
		 * ex) �� Ʈ������ �ʴ� 100�� ������ �ʴ� 100�� ��ü�� �����ǰ� �Ҹ��
		 * => �޸� ���� ����
		 * 
		 * **�޸� ���� �ذ��Ϸ���
		 * ��û���� ������� �ش� ��ü�� 1���� �����ǰ� �����ϵ��� ���� ==> �̱��� ����
		 * */
	}
	
	@Test
	@DisplayName("�̱��� ������ ������ ��ü ���")
	void singletonServiceTest() {
		
		// private �������̹Ƿ� new�� �̿��ؼ� ��ü ���� �Ұ���
//		new SingletonService(); 
		
		// static �޼��带 ���� ��ü ��ȸ ����
		// ȣ���� ������ ���� ��ü �ν��Ͻ� ��ȯ
		SingletonService singletonService1 = SingletonService.getInstance();
		SingletonService singletonService2 = SingletonService.getInstance();
		
		// �������� ���� ���� Ȯ��
		System.out.println("singletonService1 : " + singletonService1);
		System.out.println("singletonService2 : " + singletonService2);
		
		// singletonService1 == singletonService2
		assertThat(singletonService1).isSameAs(singletonService2);
	}
	
	@Test
	@DisplayName("������ �����̳ʿ� �̱���")
	void singletonContainer() {
		
		/* �������� �⺻ �� ��� ��� -> �̱��� */

		// ������ �����̳� ����(�̱��� �����̳�)
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
		// ��ȸ
		MemberService memberService1 = ac.getBean("memberService", MemberService.class); // ȣ��1
		MemberService memberService2 = ac.getBean("memberService", MemberService.class); // ȣ��2
		
		// ��ȸ�� ������ ���� ��ü�� ��ȯ�ϴ���(�������� ������) Ȯ�� => ��ü�� �̱������� ����
		System.out.println("memberService1 : " + memberService1);
		System.out.println("memberService2 : " + memberService2);
		
		// memberService1 == memberService2
		assertThat(memberService1).isSameAs(memberService2);
	}
}
