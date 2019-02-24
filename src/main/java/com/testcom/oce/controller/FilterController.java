package com.testcom.oce.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilterController {
	
	@GetMapping(path="/completeValues")
	public List<FilterBean> getBeanProps() {
		List<FilterBean> filterValues = Arrays.asList(new FilterBean("Gopi", "Krishna", "Mandapati"), new FilterBean("Spoorthi", "Blank", "Mandapati"));
		return filterValues;
	}
	
	@GetMapping(path="/filterValue")
	public MappingJacksonValue getFilterBean() {
		
		List<FilterBean> filterValues = Arrays.asList(new FilterBean("Gopi", "Krishna", "Mandapati"),
				new FilterBean("Spoorthi", "Blank", "Mandapati"));

		//FilterBean fb = new FilterBean("Gopi", "Krishna", "Mandapati");
		
		SimpleBeanPropertyFilter filterProp = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("Case1", filterProp);
		
		
		MappingJacksonValue mapping = new MappingJacksonValue(filterValues);
		
		mapping.setFilters(filters);
		
		return mapping;
	}

}


@JsonFilter("Case1")
class FilterBean {

	private String field1;
	//Static filter
	//@JsonIgnore
	private String field2;
	//Static filter
	//@JsonIgnore
	private String field3;

	public FilterBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

}
