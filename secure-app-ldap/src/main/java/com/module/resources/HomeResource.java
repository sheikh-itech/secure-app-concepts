package com.module.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

	private String style1 = "'color:lightgreen;font-size:25px;'";
	
	
	@RequestMapping("guest")
	public String guest() {
		
		return "<div style="+style1+">Welcome to secure app- guest page</div>";
	}
	
	@RequestMapping("user")
	public String user() {
		
		return "<div style="+style1+">Welcome to secure app- user page</div>";
	}
	
	@RequestMapping("admin")
	public String admin() {
		
		return "<div style="+style1+">Welcome to secure app- admin page</div>";
	}
	
	@RequestMapping("home")
	public String home() {
		
		return "<div style="+style1+">Welcome to secure app- home page</div>";
	}
}
