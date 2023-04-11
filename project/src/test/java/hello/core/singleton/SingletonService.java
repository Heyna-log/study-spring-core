package hello.core.singleton;

public class SingletonService {
	
	// private���� ������ ���� -> �ٸ� �ܺ� Ŭ�������� new�� ��ü ���� �Ұ���
	private SingletonService() {
	}
	
	// private���� ���� Ŭ���� ���̹Ƿ� ������ ȣ�� ����
	// static �����̹Ƿ� ���� ����
	// ��ü�� 1���� �̸� ������ �� (�̱��� ���� ���� ��� �� �ϳ� - ��ü�� �̸� �����صδ� ���(���� �ܼ��ϰ� ������))
	private static final SingletonService instance = new SingletonService();
	
	// public���� ��� ��ü �ν��Ͻ��� �ʿ��ϸ� �� static �޼��带 ���ؼ��� ��ȸ�ϵ��� ���
	public static SingletonService getInstance() {
		return instance;
	}

	
	public void logic() {
		System.out.println("�̱��� ��ü ���� ȣ��");
	}
	
	/*
	 * **�̱��� ������ ������
	 *  - �̱��� �ڵ带 �����ϴ� �ڵ� ��ü�� ���� ����.
	 *  - ��������� Ŭ���̾�Ʈ�� ��ü Ŭ������ �����Ѵ�. -> DIP ����
	 *  - Ŭ���̾�Ʈ�� ��ü Ŭ������ �����ؼ� OCP ��Ģ�� ������ ���ɼ��� ����.
	 *  - �׽�Ʈ�ϱ� ��ƴ�.
	 *  - ���� �Ӽ��� �����ϰų� �ʱ�ȭ�ϱ� ��ƴ�.
	 *  - private���� �����ڸ� �����ϹǷ� �ڽ� Ŭ������ ����� ��ƴ�.
	 *  => ��������� �������� ��������.
	 *  => '��Ƽ ����'���� �Ҹ��⵵ �Ѵ�.
	 * */
}
