package com.polentzi.counter;

import java.net.InetAddress;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounterApp {

	public static void main(String[] args) {
		SpringApplication.run(CounterApplication.class, args);
	}
	@RequestMapping("/")
	public String index(HttpSession session) {
		
		if(session.getAttribute("count") == null) {
			session.setAttribute("count", 1);
		}else {
			Integer count = (Integer) session.getAttribute("count");
			count++;
			session.setAttribute("count", count);
		}
		Integer count = (Integer) session.getAttribute("count");
		System.out.println("session num : "+count);
		return "index.jsp";
	}
	
	@RequestMapping("/counter")
	public String counter(Model model, HttpSession session) throws Exception {
		model.addAttribute("counter", session.getAttribute("count"));
		InetAddress hostAddress = InetAddress.getLocalHost();
		model.addAttribute("hostName", hostAddress.getHostName());
		return "counter.jsp";
	}
}