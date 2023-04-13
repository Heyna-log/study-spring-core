package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/* **@ComponentScan
 *  - @Component�� ���� Ŭ������ ��ĵ�ؼ� ������ ������ ���
 *  - ������ ���� �⺻ �̸��� Ŭ�������� ����ϵ�, �� �ձ��ڸ� �ҹ��ڷ� �ٲپ� ���(MemberServiceImpl Ŭ���� -> memberServiceImpl)
 *  - ������ ���� �̸��� ���� �����ϴ� ���, @Component("������ �� �̸�")�� ���� ����
 *  
 * **������Ʈ ��ĵ �⺻ ��� => @Component�� ���ԵǾ� �ִ� ������̼�
 *  - @Component : ������Ʈ ��ĵ���� ���
 *  - @Controller : ������ MVC ��Ʈ�ѷ����� ���
 *  - @Service : ������ ����Ͻ� �������� ���
 *  - @Repository : ������ ������ ���� �������� ���
 *  - @Configuration : ������ ���� �������� ���
 */

@Configuration // @Configuration �ȿ� @Component�� ���ԵǾ� �־ ������Ʈ ��ĵ ����� ��
@ComponentScan(
	// Ž���� ��Ű���� ���� ��ġ ����
	// ���� ��ġ�� ������ ������ ���� ����. ex) basePakages = {"hello.core", "hello.service"}
	basePackages = "hello.core", // core ��Ű���� �� ���� ��Ű������ Ž��
	
	// ������ Ŭ������ ��Ű���� Ž�� ���� ��ġ�� ����
	basePackageClasses = AutoAppConfig.class, // AutoAppConfig.class�� ��Ű���� hello.core
	
	// **Ž�� ���� ��ġ�� �������� ������ @ComponentScan�� ���� ���� ���� Ŭ������ ��Ű���� ���� ��ġ�� ��. => ���� ��ġ�� �������� �ʰ�, ���� ���� Ŭ������ ������Ʈ �ֻ�ܿ� �δ� ����� ����
		
	// �������� ����� @Configuration �ڵ�� ������Ʈ ��ĵ ��󿡼� ����(AppConfig, TestConfig)
	excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
	
}
