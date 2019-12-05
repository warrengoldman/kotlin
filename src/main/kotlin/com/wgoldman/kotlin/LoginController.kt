package com.wgoldman.kotlin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class LoginController {

  @PostMapping("/login")
  fun login(@RequestParam username: String, @RequestParam password: String): String {
	  System.out.println("username:" + username +", password:" + password)
	  return "blog";
  }
}