package com.example.yristanpp_3_1_1.controller;

import lombok.AllArgsConstructor;

import com.example.yristanpp_3_1_1.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.yristanpp_3_1_1.service.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {


    private UserService userService;

    @RequestMapping("/")
    public String printUsers(Model model) {

        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "user";
    }

    @RequestMapping ("/addNewUsers")
    public String addNewUsers(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user_info";
    }

    @RequestMapping("/saveUsers")
    public String saveUsers(@ModelAttribute("user") User user) {
        userService.saveUsers(user);
        return "redirect:/";
    }

    @RequestMapping("edit/{id}")
    public String updateUserGet(@PathVariable("id") int id, Model model) {
        model.addAttribute(userService.getUsers(id));
        return "edit";
    }

    @RequestMapping("/edit")
    public String updateInfo(@ModelAttribute("user") User user){
        userService.updateUsers(user);
        return "redirect:/";
    }

    @RequestMapping("/deleteUsers")
    public String deleteUsers(@RequestParam("userId") int id) {
        userService.deleteUsers(id);
        return "redirect:/";
    }

}