package org.barista.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

// 객체 리스트를 쿼리 파라미터로 받음

@Data
public class SampleDTOList {

	private List<SampleDTO> list;
	
	public SampleDTOList() {
		
		list = new ArrayList<>();
	}
}
