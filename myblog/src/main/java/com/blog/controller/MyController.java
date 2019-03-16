package com.blog.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.blog.pojo.User;
import com.blog.service.UserService;
import com.blog.util.ImgUtils;

@RestController
public class MyController {
	public static String fileServerUrl =  "" ; 
	@Autowired
	UserService userService ; 
	
	@GetMapping("{username}/profile")
	@PreAuthorize("authentication.name.equals(#username)")
	public ModelAndView profile(@PathVariable(name = "username")String username) {
		ModelAndView mav = new ModelAndView("my");
		User user = userService.getByUsername(username);
		
		mav.addObject("user",user);
		mav.addObject("fileServerUrl",fileServerUrl);
		return mav ; 
	}
	@PostMapping("{username}/profile")
	@PreAuthorize("authentication.name.equals(#username)")
	public ModelAndView update(@PathVariable("username") String username , User user) {
	ModelAndView mav = new ModelAndView("redirect:profile");	
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder() ; 
		User old = userService.getByUsername(username);
		String result = encoder.encode(old.getPassword());
		user.setId(old.getId());
		if(user.getPassword().isEmpty())
		user.setPassword(old.getPassword());
		else {
			user.setPassword(encoder.encode(user.getPassword()));
		}

		userService.update(user);
	return mav;
	}
	@PostMapping("{username}/avatar")
	@PreAuthorize("authentication.name.equals(#username)")
	public ModelAndView avatar(@PathVariable("username") String username , MultipartFile multipartFile,HttpSession session) {
		//							此方法只有在 jsp 中可以使用
		//	File file = new File(session.getServletContext().getRealPath("/img/"+username+".jpg"));
	
	try {
		String path = ResourceUtils.getURL("classpath:").getPath()+"static/"+"img";
		System.out.println(path);
		File file = new File(path,username+".jpg");
	if(!new File(file.getParent()).exists()) {
		new File(file.getParent()).mkdirs();
	}
		if(multipartFile != null) {
		multipartFile.transferTo(file);}
		System.out.println(file.getAbsolutePath()); 
		ImgUtils.scale(file.getAbsolutePath(), file.getAbsolutePath(), 50, 50, true);
	} catch (IllegalStateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		return new ModelAndView("redirect:/homepage");
	}
	@PostMapping("headCheck")
	public String check(String username,HttpSession session) {
		File file = new File(session.getServletContext().getRealPath("img"));
		
		return "success";
	}
	
}
