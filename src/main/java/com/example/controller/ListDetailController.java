package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.AppointList;
import com.example.model.Status;
import com.example.repository.AppointListRepository;
import com.example.repository.StatusRepository;

@Controller
public class ListDetailController {
	
	//modelに追加
	private Model addModel(AppointList appointList, Model model) {
        model.addAttribute("userId", appointList.getUserId());
        model.addAttribute("listId", appointList.getListId());
        model.addAttribute("storeName", appointList.getStoreName());
        model.addAttribute("phone", appointList.getPhone());
        model.addAttribute("address", appointList.getAddress());
        model.addAttribute("subject",appointList.getSubject());
        model.addAttribute("industry", appointList.getIndustry());
        model.addAttribute("remarks", appointList.getRemarks());
        model.addAttribute("statusDetail", appointList.getStatusDetail());
        model.addAttribute("PostRemarks", appointList.getPostRemarks());
        
        return model;
	}
	
	@Autowired
    private StatusRepository statusRepository;
	
	@Autowired
    private AppointListRepository appointListRepository;

	//ページ表示
    @PostMapping("/listDetail")
    public String showDetail(   		
            @RequestParam("listId") int listId,
            Model model) {
        
    	List<Status> statusList = statusRepository.findAll();
        model.addAttribute("status", statusList);
       
        AppointList appointList = appointListRepository.findByListId(listId); // 全てのデータを取得
        Status status = statusRepository.getById(appointList.getStatusId());
        model.addAttribute("statusSelected", status.getStatus());
        System.out.println("検索された値は"+appointList);
    	
        // 受け取ったデータをモデルに追加
        model = addModel(appointList,model);
        
        // 詳細表示ページに遷移
        return "listDetail"; // listDetail.htmlに遷移
    }
    
    @GetMapping("/showDetail")//ページ表示
    public String showlistDetail(   		
            @RequestParam("listId") int listId,
            Model model) {
        
    	List<Status> statusList = statusRepository.findAll();
        model.addAttribute("statusList", statusList);
        AppointList appointList = appointListRepository.findByListId(listId); // 全てのデータを取得
        System.out.println("検索された値は"+appointList);
    	
        // 受け取ったデータをモデルに追加
        model = addModel(appointList,model);
        
        // 詳細表示ページに遷移
        return "listDetail"; // listDetail.htmlに遷移
    }
    
    
    
    @PostMapping("/nextpage")//ページ表示
    public ModelAndView showNextDetail(   		
            @RequestParam("listId") int listId,
            Model model) {
    	
    	List<Status> statusList = statusRepository.findAll();
        model.addAttribute("statusList", statusList);
    	
        List<AppointList> appointList = appointListRepository.findAll(); 
        int nextId = 0;
    	for (int i = 0; i < appointList.size(); i++) {
    		AppointList current = appointList.get(i);
    		if(current.getListId() == listId) {
    			if(i+1 > appointList.size()-1) {//次の値がない場合は初めに戻る
        			nextId =  appointList.get(0).getListId();
        			break;
        		}
    			nextId =  appointList.get(i+1).getListId();
    			break;
    		}
    	}
        ModelAndView mav = new ModelAndView("redirect:/showDetail");
        mav.addObject("listId", nextId);
        return mav;
    }
    
    @PostMapping("/submit")//保存
    public ModelAndView submitUpdate(
    		@RequestParam("listId") Integer listId,
    		@RequestParam(value="userId", required=false, defaultValue="1") String userId,
    		@RequestParam(value="storeName", required=false, defaultValue="test") String storeName,
    		@RequestParam(value="phone", required=false, defaultValue="111") String phone,
    		@RequestParam(value="address", required=false, defaultValue="test") String address,
    		@RequestParam(value="subject", required=false, defaultValue="test") String subject,
    		@RequestParam(value="industry", required=false, defaultValue="test") String industry,
    		@RequestParam(value="remarks", required=false, defaultValue="test") String remarks,
    		@RequestParam(value="statusDetail", required=false, defaultValue="test") Integer statusDetail,
    		@RequestParam(value="postRemarks", required=false, defaultValue="test") String postRemarks) {

        // listIdを元に該当のレコードを取得
        AppointList appointment = appointListRepository.findById(listId)
                                       .orElseThrow(() -> new IllegalArgumentException("Invalid listId:" + listId));

        // データを更新
        appointment.setStoreName(storeName);
        appointment.setPhone(phone);
        appointment.setAddress(address);
        appointment.setSubject(subject);
        appointment.setIndustry(industry);
        appointment.setRemarks(remarks);
        appointment.setStatusId(statusDetail);  // 仮に statusId を 1 に固定
        appointment.setPostRemarks(postRemarks);

        // データベースに保存
        appointListRepository.save(appointment);

        return new ModelAndView("redirect:/appoints");  // 成功ページにリダイレクト
    }
    
    @PostMapping("/submitSave")//保存
    public ModelAndView submitSave(
    		@RequestParam("listId") Integer listId,
    		@RequestParam(value="userId", required=false, defaultValue="1") String userId,
    		@RequestParam(value="storeName", required=false, defaultValue="test") String storeName,
    		@RequestParam(value="phone", required=false, defaultValue="111") String phone,
    		@RequestParam(value="address", required=false, defaultValue="test") String address,
    		@RequestParam(value="subject", required=false, defaultValue="test") String subject,
    		@RequestParam(value="industry", required=false, defaultValue="test") String industry,
    		@RequestParam(value="remarks", required=false, defaultValue="test") String remarks,
    		@RequestParam(value="statusDetail", required=false, defaultValue="test") Integer statusDetail,
    		@RequestParam(value="postRemarks", required=false, defaultValue="test") String postRemarks) {

        // listIdを元に該当のレコードを取得
        AppointList appointment = appointListRepository.findById(listId)
                                       .orElseThrow(() -> new IllegalArgumentException("Invalid listId:" + listId));

        // データを更新
        appointment.setStoreName(storeName);
        appointment.setPhone(phone);
        appointment.setAddress(address);
        appointment.setSubject(subject);
        appointment.setIndustry(industry);
        appointment.setRemarks(remarks);
        appointment.setStatusId(statusDetail);  // 仮に statusId を 1 に固定
        appointment.setPostRemarks(postRemarks);

        // データベースに保存
        appointListRepository.save(appointment);

        ModelAndView mav = new ModelAndView("redirect:/showDetail");
        mav.addObject("listId", listId);
        return mav;
    }
}
