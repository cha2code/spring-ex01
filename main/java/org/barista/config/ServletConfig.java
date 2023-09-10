package org.barista.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@ComponentScan(basePackages = {"org.barista.controller"})
public class ServletConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/resources/**")
		.addResourceLocations("resources/");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/"); // 경로 체크하기
		bean.setSuffix(".jsp");
		registry.viewResolver(bean); // controller의 실행 결과를 보여주는 bean
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getResolver() throws IOException {
		
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		
		resolver.setMaxUploadSize(1024*1024*10); // 10mb
		resolver.setMaxUploadSizePerFile(1024*1024*2); // 2mb
		resolver.setMaxInMemorySize(1024*1024); // 1mb
		
		resolver.setUploadTempDir(new FileSystemResource("C:\\Users\\cha2jini\\Desktop\\backend_workspace\\spring-study\\upload\\tmp"));
		resolver.setDefaultEncoding("UTF-8");
		
		return resolver;
	}
	
}

/*
 WebMvcConfigurer
 
 - spring 프레임워크에서 제공하는 인터페이스
 - 요구사항에 맞게 프레임워크 조정이 가능
 - 특정한 spring 클래스를 구현하거나 상속할 필요 없이 MVC 구성 정보를 제어할 수 있게 함
 - @EnableWebMvc를 통해 활성화 된 Web MVC 애플리케이션의 구성 정보를 커스터마이징하는 것을 도움
 	
 
 1. 기존에 설정 된 bean을 유지하고, 기능을 단순히 추가할 때
 	-> WebMvcConfigurer를 구현하고 @Configuration을 추가한 클래스를 만듦
 
 2. 기존과 다르게 Spring MVC를 제어하려 할 때
 	-> @EnableWebMvc 추가
 	

*** 메소드 이름별 구조
- add~ : 기본 설정이 없는 bean들에 대하여 새로운 bean을 추가함
- configure~ : 수정자를 통해 기존의 설정을 대신하여 등록함
- extend~ : 기존의 설정을 이용하며 추가로 설정을 확장함
 */


/*
 ViewResolver
 
 - 실행할 view를 찾음
	(페이지 컨트롤러가 리턴한 뷰 이름에 해당하는 뷰 컴포넌트를 찾음)
	
- 리턴 값으로 url을 받아 웹 애플리케이션 디렉토리에서 JSP를 찾음
*/


/*
addResourceHandlers

- 정적 파일들의 경로를 지정
	(어느 경로로 들어왔을 때 mapping 해줄 지 경로를 지정)
*/

/*
MultipartResolver

- Multipart file upload를 위한 전략(Strategy) interface

1. DispacherServlet이 bean으로 등록 된 MultipartResolver의 인스턴스를 체크
	(default bean이 없으므로 구현체를 직접 등록해야 함)

2. DispacherServlet.doDispach()는 파일 업로드 요청에 관한 것인지
	MultipartResolver.isMultipart() 메소드를 호출해서 확인함
	(만약 return true면 이 요청은 주로 "multipart/form-data"의 content-type을 가진 POST 요청임.

3. true 값을 리턴하면 MultipartResolver.resolveMultipart()를 호출해서 HTTP 요청을 파싱하고,
	MultipartHttpSErvletRequest Object를 감싸서 반환함.
	(여기서 Lazy option을 걸지 않으면 메모리 또는 임시 폴더에 저장됨)

4. DispatcherServlet.doDispatch()의 마지막 차례에 MultipartResolver.cleanupMultipart()가 호출 되어
	업로드 된 파일의 저장소와 같은 multipart 처리를 위해 사용된 리소스들을 정리함
*/