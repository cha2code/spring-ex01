package org.barista.domain;

import lombok.Data;

// request.getParameter()를 대체

@Data
public class SampleDTO {
	
	private String name;
	private int age;

}

/*
JSP/Servlet

String name = request.getParameter("name");
int age = Integer.parseInt(request.getParameter("age"));
SampleDTO dto = new SampleDTO(name, age);
*/

/*
DTO 정의 시 필요한 것들
- 기본 생성자 (default 생성자도 포함)
- setter
*/