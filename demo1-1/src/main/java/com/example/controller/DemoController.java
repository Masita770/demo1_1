package com.example.controller;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.DemoInfo;
import com.example.service.DemoService;


@Controller
//@RestController
@RequestMapping("/crud")
public class DemoController {
	
	@Autowired
	DemoService service;

	@RequestMapping("list")
	public String list(Model model) {
		List<DemoInfo> users = service.getDemoList();
		model.addAttribute("list", users);
		model.addAttribute("demoInfo", new DemoInfo());
		return "crud/list";
	}
	@GetMapping("form")
	String newUser(@RequestBody(required = false) Model model) {
			return "crud/form";
	}
	//新規登録依頼の受け取り
	@PostMapping("form")
	public String add(@ModelAttribute DemoInfo demoInfo, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
		List<DemoInfo> users = service.getDemoList();
		model.addAttribute("demoInfo", users);
//		model.addAttribute("demoInfo", demoInfo);
		return "crud/list";
		}
	
	service.create(demoInfo);
	return "redirect:crud/list";
	}
	
	//ユーザー個別編集画面
//	@GetMapping("{id}/update")
//	public String edit(@PathVariable String id, Model model) {
//		DemoInfo demoInfo = service.updateSelect(id);
//		model.addAttribute("demoInfo", demoInfo);
//		return "crud/update";
//	}
	
	//1 エラー発生箇所
	@GetMapping("{id}")
	public String user(@PathVariable(required = false)String id, DemoInfo demoInfo, Model model) {
		DemoInfo userInfo= service.selectOne(id);
		System.out.println(1);
		model.addAttribute("userInfo", userInfo);
		return "crud/user";
	}
//	@RequestMapping("/update")
//	public String serch(@ModelAttribute DemoInfo demoInfo, Model model) {
//		DemoInfo demoUpdate = service.update(demoInfo);
//		model.addAttribute("demoUpdate", demoInfo);
//	}
	//編集機能 
//	@PostMapping("{id}")
//	public String seletOne(@PathVariable String id, @ModelAttribute DemoInfo demoInfo, BindingResult bindingResult, Model model) {
//		service.updateSelect(id);
//		model.addAttribute("demoInfo", demoInfo);
//		//service.create(demoInfo);
//		return "redirect:crud/update";
//		}
//	service.update(demoInfo);
//	return "/list";
//	}
}