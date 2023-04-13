package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/* **@ComponentScan
 *  - @Component�� ���� Ŭ������ ��ĵ�ؼ� ������ ������ ���
 *  - ������ ���� �⺻ �̸��� Ŭ�������� ����ϵ�, �� �ձ��ڸ� �ҹ��ڷ� �ٲپ� ���(MemberServiceImpl Ŭ���� -> memberServiceImpl)
 *  - ������ ���� �̸��� ���� �����ϴ� ���, @Component("������ �� �̸�")�� ���� ����
 */

@Configuration // @Configuration �ȿ� @Component�� ���ԵǾ� �־ ������Ʈ ��ĵ ����� ��
@ComponentScan(
	// �������� ����� @Configuration �ڵ�� ������Ʈ ��ĵ ��󿡼� ����(AppConfig, TestConfig)
	excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
