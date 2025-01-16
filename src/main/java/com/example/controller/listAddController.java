package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.AppointList;
import com.example.model.User;
import com.example.repository.AppointListRepository;
import com.example.repository.UserRepository;

@Controller
public class listAddController {
	
	@Autowired
    private AppointListRepository appointListRepository;
	@Autowired
	private UserRepository userRepository;
	
    
    @PostMapping("/listDetailAdd")//保存
    public ModelAndView submitUpdate(
    		@RequestParam(value="storeName", required=false) String storeName,
    		@RequestParam(value="phone", required=false) String phone,
    		@RequestParam(value="address", required=false) String address,
    		@RequestParam(value="subject", required=false) String subject,
    		@RequestParam(value="industry", required=false) String industry,
    		@RequestParam(value="remarks", required=false) String remarks) {
    	
    	//ログイン中のユーザーIDを取得
    	String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user2 = userRepository.findByUsername(userName);
        int userId = user2.getId();
        
        // listIdを元に該当のレコードを取得
        AppointList appointment = new AppointList();
        appointment.setUserId(userId);//テスト的にuserIDを追加
        appointment.setStoreName(storeName);
        appointment.setPhone(phone);
        appointment.setAddress(address);
        appointment.setStatusId(5);
        appointment.setSubject(subject);
        appointment.setIndustry(industry);
        appointment.setRemarks(remarks);

        // データベースに追加
        appointListRepository.save(appointment);

        return new ModelAndView("redirect:/appoints");  // 成功ページにリダイレクト
    }
}
