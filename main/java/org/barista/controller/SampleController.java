package org.barista.controller;

import java.util.ArrayList;

import org.barista.domain.SampleDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	@RequestMapping("")
	public void basic() {
		
		log.info("basic.............");
	}
	
	// /sample/basic 값을 GET 또는 POST로 요청함
	@RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		
		log.info("basic get...................");
	}
	
	// /sample/basicOnlyGet 값을 Get으로 mapping
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		
		log.info("basic get only get...................");
	}
	
	// 리턴 값을 주소로 사용, get 형식으로 mapping
	// SampleDTO dto = bean으로 등록 될 필요 없음-요청이기 때문
	// 자동으로 request scope에 저장 됨 - 타입의 이름을 camel case로 변경해서 속성명으로 사용(jsp)
	// DI(Dependency Injection), IoC(Inversion of Control)-제어의 역전
	// 나는 요청만 하고 spring이 알아서 요청한 객체를 전달함
	// query parameter와 맞춰서 요청
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		
		log.info("" + dto);
		
		return "ex01";
	}
	
	// scope로 전달 되지 않고 el의 parameter로 전달 = jsp
	// default 값이 없어 parameter에 입력 되지 않으면 에러 발생
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name,
										@RequestParam("age") int age) {
	
		log.info("name" + name);
		log.info("age" + age);
		
		return "ex02";
	}
	
	// ArrayList로  여러 개의 parameter 전달
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		
		log.info("ids : " + ids);
		
		return "ex02List";
	}
	
	@GetMapping("/ex04")
	public String ex04(
			Model model,
			@ModelAttribute("sample") SampleDTO dto,
			@ModelAttribute("page") int page) {
		
		log.info("dto : " + dto);
		log.info("page : " + page);
		
		//model.addAttribute("now", new Date());
		
		return "ex04";
	}
	
	//JSON 타입
	// Spring MVC가 자동으로 브라우저에 JSON 타입으로 객체를 변환 후 전달
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		
		log.info("/ex06...........");
		
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("hong");
		
		return dto;
	}
	
	@GetMapping("/ex07")
	// body에 담을 객체 타입 = <String>
	public ResponseEntity<String> ex07() {
		
		log.info("/ex07....................");
		
		// JSON 문자열 ({"name" : "hong"})
		String msg = "{\"name\" : \"hong\"}";
		
		// HTTPHeader에 데이터를 넘겨줌
		HttpHeaders header = new HttpHeaders();
		header.add("Content-type", "application/json;charset=UTF-8");
		
		// msg : body, HttpStatus : 상태코드, OK : 200
		return new ResponseEntity<>(msg, header, HttpStatus.OK);
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
		
		log.info("/exUpload................");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList <MultipartFile> files) {
		
		files.forEach(file -> {
			log.info("----------------------");
			log.info("name" + file.getOriginalFilename());
			log.info("size" + file.getSize());
		});
	}

}

/*
@Controller

- 사용자의 요청을 처리한 후 정해진 view에 객체를 넘겨주는 역할
	(중간 제어자 역할)
*/

/*
@RequestMapping()

- 들어온 요청을 특정 method와 mapping하기 위해 사용
- value, method를 가장 많이 사용
	(value:요청 받을 url 설정 / method:어떤 요청으로 받을 지 정의-get, post, put, delete 등)
*/

/*
@RequestParam()

- HttpServletRequest 객체와 같은 역할
- method의 parameter 값으로 넣음
- @RequestParam("가져올 데이터의 이름") [데이터 타입] [가져온 데이터를 담을 변수]
- model 객체를 이용해 view로 넘겨줌
*/

/*
Model

- Controller에서 생성된 데이터를 담아 View로 전달할 때 사용하는 객체
	(Servlet의 request.setAttribute와 같은 역할을 함)

- addAttribute("key", "value") 메소드를 이용해 view에 전달할 데이터를 key, value 형식으로 전달


@ModelAttribute("파라미터명")

- http body 내용과 http parameter의 값들을 getter, setter, 생성자를 통해 주입하기 위해 사용
- 일반 변수는 전달이 불가능하기 때문에 Model을 통해서 전달해야 함

** ModelAttribute 선언 후 자동으로 진행되는 작업들
1. 파라미터로 넘겨준 타입의 object를 자동 생성
	(이때 지정되는 클래스는 getter, setter가 있는 beans 클래스여야 함)

2. 생성된 object HTTP로 넘어온 값들을 자동으로 바인딩
	(HTTP의 값들은 각 해당 변수의 setter를 통해 해당 멤버 변수로 바인딩 됨)
	
3. 어노테이션이 붙은 객체가 자동으로 Model 객체에 추가되고, View로 전달됨
*/