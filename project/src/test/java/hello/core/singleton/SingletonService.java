package hello.core.singleton;

public class SingletonService {
	
	// private으로 생성자 생성 -> 다른 외부 클래스에서 new로 객체 생성 불가능
	private SingletonService() {
	}
	
	// private지만 같은 클래스 내이므로 생성자 호출 가능
	// static 변수이므로 공유 가능
	// 객체를 1개만 미리 생성해 둠 (싱글톤 패턴 구현 방법 중 하나 - 객체를 미리 생성해두는 방법(가장 단순하고 안전함))
	private static final SingletonService instance = new SingletonService();
	
	// public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용
	public static SingletonService getInstance() {
		return instance;
	}

	
	public void logic() {
		System.out.println("싱글톤 객체 로직 호출");
	}
	
	/*
	 * **싱글톤 패턴의 문제점
	 *  - 싱글톤 코드를 구현하는 코드 자체가 많이 들어간다.
	 *  - 의존관계상 클라이언트가 구체 클래스에 의존한다. -> DIP 위반
	 *  - 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
	 *  - 테스트하기 어렵다.
	 *  - 내부 속성을 변경하거나 초기화하기 어렵다.
	 *  - private으로 생성자를 생성하므로 자식 클래스를 만들기 어렵다.
	 *  => 결론적으로 유연성이 떨어진다.
	 *  => '안티 패턴'으로 불리기도 한다.
	 * */
}