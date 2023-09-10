package org.barista.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// web.xml을 대신해서 java로 설정하는 class

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// root-context.xml 을 대신하는 클래스 생성
		return new Class[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// sevlet-context.xml을 대신하는 클래스 생성
		return new Class[] {ServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[] {"/"};
	}

}

/*
 getServletMappings()
 
- DispatcherServlet이 mapping 되기 위한 하나 혹은 여러 개의 경로 지정
- 애플리케이션 기본 서블릿("/")에 mapping되어 있을 경우, 애플리케이션으로 들어오는 모든 요청을 처리함
*/