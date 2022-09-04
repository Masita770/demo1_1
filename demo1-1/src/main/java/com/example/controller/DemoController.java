package com.example.controller;

import com.example.domain.User;
import com.example.service.DemoService;

import java.util.Optional;
import java.util.List;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;


@Controller
//@RestController
@RequestMapping("/crud")
public class DemoController {
	
	@Autowired
	DemoService service;

	@RequestMapping("list")
	public String list(Model model) {
		List<User> users = service.getDemoList();
		model.addAttribute("userlist", users);
		//model.addAttribute("demoInfo", new DemoInfo());
		return "crud/list";
	}
	@GetMapping("form")
	String newUser(@RequestBody(required = false) Model model) {
			return "crud/form";
	}
	//新規登録依頼の受け取り
	@RequestMapping("form")
	public String add(@ModelAttribute User user, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
//		List<DemoInfo> users = service.getDemoList();
//		model.addAttribute("demoInfo", users);
//		model.addAttribute("demoInfo", demoInfo);
		return "crud/success";
		}
	service.create(user);
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
	@GetMapping("/user")
	public String select(@RequestParam(value = "id", required = true)String id, Model model) throws NotFoundException {
		//if (result.hasErrors()) {
		//String errorInfo = null;
		try {
			Optional<User> user = service.selectOne(id);
		User a = user.get();
			//userInfo.ifPresentOrElse(demoInfo -> errorInfo("null"), null);
			model.addAttribute("user", user.get());
			return "crud/user";
			} catch(IncorrectResultSizeDataAccessException e) {
				String errorMessage = "対象のユーザーは存在しません。";
				throw new NotFoundException(errorMessage);
			}
	}
	
	//		service.selectOne(id);
//		return "redirect:crud/user";
	//}
//	@RequestMapping("/update")
//	public String serch(@RequestParam(value = "id", required = true)String id, Model model) {
//		Optional<DemoInfo> userUpdate = service.selectOne(id);
//		model.addAttribute("demoUpdate", userUpdate);
//		return "crud/update";
//	}
	//編集機能 
	@PostMapping("/update")
	public String seletOne(@RequestParam(value = "id", required = true)String id, @ModelAttribute User update, Model model) {
		update.setId(id);
		service.update(update);
		model.addAttribute("update", update);
		//service.create(demoInfo);
		
		return "redirect:crud/list";
		}
//	service.update(demoInfo);
//	return "/list";
//	}
	
}