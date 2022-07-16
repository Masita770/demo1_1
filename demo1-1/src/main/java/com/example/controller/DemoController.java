package com.example.controller;

import com.example.domain.DemoInfo;
import com.example.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
//@RestController
@RequestMapping("/")
public class DemoController {

    @Autowired
    DemoService service;

    @RequestMapping("list")
    public String list(Model model) {
        List<DemoInfo> users = service.getDemoList();
        model.addAttribute("userList", users);
        return "crud/list";
    }

    @GetMapping("form")
    String newUser(@RequestBody(required = false) Model model) {
        return "crud/form";
    }

    @PostMapping("form")
    public String add(@ModelAttribute DemoInfo demoInfo, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<DemoInfo> users = service.getDemoList();
            return "crud/list";
        }

        service.create(demoInfo);
        return "redirect:crud/list";
    }

    @GetMapping("/user")
    public String select(@RequestParam(value = "id", required = true) String id, Model model) {
        DemoInfo userInfo = service.selectOne(id);
        // TODO: ユーザーが見つからなかった場合の例外処理をすること
        model.addAttribute("userInfo", userInfo);
        return "crud/user";
    }
}
