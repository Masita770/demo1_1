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
		List<User> users = service.getDemoList();
		model.addAttribute("success", users);
		return "crud/success";
		}
	service.create(user);
	return "redirect:list";
	}
	
	//更新画面表示
//	@GetMapping("/update")
//	public String updateSelect(@PathVariable("id") String id, Model model) {
//		Optional<User> user = service.updateSelect(id);
//		model.addAttribute("update", user);
//		return "crud/update";
//	}
	
	//個別参照
	@GetMapping("user/{id}")
	public String select(@PathVariable("id") String id, Model model) throws NotFoundException {
		//if (result.hasErrors()) {
		//String errorInfo = null;
		Optional<User> user = service.selectOne(id);
		//User myUser = user;
		user.ifPresentOrElse(inside -> {
			model.addAttribute("user", inside);
			return;
		}, () -> {
			System.out.println("値が存在しない");
		});
		return "crud/user";
		}
		//user.ifPresentOrElse(NullController::NullError, () -> System.out.println("NULL"));
		
//			User myUser = user.get();
//			model.addAttribute("user", myUser);
//			return "crud/user";
////			} (IncorrectResultSizeDataAccessException e) {
////				String errorMessage = "対象のユーザーは存在しません。";
////				throw new NotFoundException(errorMessage);
//			}
	
	
	//		service.selectOne(id);
//		return "redirect:crud/user";
	//}
	//編集対象指定
	@GetMapping("update/{id}")
	public String update(@PathVariable("id")String id, Model model) {
		Optional<User> userUpdate = service.selectOne(id);
		userUpdate.ifPresentOrElse(inside -> {
			model.addAttribute("userUpdate", inside);
			return;
		}, () -> {
			System.out.println("値が存在しない");
		});
		//model.addAttribute("update", userUpdate);
		return "crud/update";
	}
//	//指定した箇所を編集実行 
	@RequestMapping("/edit/{id}")
	public String update(@PathVariable("id") String id, @ModelAttribute User user) {
		user.setId(id);
		service.update(user);
		return "crud/edit";
		}
//	service.update(user);
//	return "/list";
	
	
}