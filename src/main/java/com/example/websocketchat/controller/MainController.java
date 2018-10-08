package com.example.websocketchat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String index(HttpServletRequest request, Model model) {
		String username = (String) request.getSession().getAttribute("username");
		
		if(StringUtils.isEmpty(username)) {
			return "redirect:/login";
		}
		model.addAttribute("username", username);
		
		return "chat";
	}

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String shoLoginPage() {
		return "login";
		
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String doLogin(HttpServletRequest request, @RequestParam(defaultValue = "") String username) {
		username = username.trim();
		if(username.isEmpty()) {
			return "login";
		}
		request.getSession().setAttribute("username", username);
		
		return "redirect:/";
	}
	
	@RequestMapping(path = "/logout")
	public String logout(HttpServletRequest request) {
		request.getSession(true).invalidate();
		
		return "redirect:/login";
	}

}
