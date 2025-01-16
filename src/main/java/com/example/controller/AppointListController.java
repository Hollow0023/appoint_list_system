package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.AppointList;
import com.example.repository.AppointListRepository;
import com.example.repository.StatusRepository;  // StatusRepositoryをインポート

@Controller
public class AppointListController {

    @Autowired
    private AppointListRepository appointListRepository;

    @Autowired
    private StatusRepository statusRepository;  // StatusRepositoryを注入
    
    private List<AppointList> setStatus(List<AppointList> appointList) {
    	for (AppointList appoint : appointList) {
            // AppointListのstatusに対応するIDを使ってStatusを取得
            String status = statusRepository.findByStatusId(appoint.getStatusId()).getStatus();
            appoint.setStatusDetail(status);
        }
    	return appointList;
    }

    @GetMapping("/appoints")
    public String showAppointList(Model model) {
        List<AppointList> appointList = appointListRepository.findAll(); // 全てのデータを取得
        // AppointListの各要素に対してStatusを取得する
        appointList = setStatus(appointList);
        model.addAttribute("appointList", appointList); // 取得したリストをビューに渡す
        return "appointList"; // 表示するHTMLテンプレートの名前
    }
    
    @GetMapping("/listAdd")
    public String listAdd(Model model) {
    	List<AppointList> appointList = appointListRepository.findAll(); // 全てのデータを取得
        // AppointListの各要素に対してStatusを取得する
        appointList = setStatus(appointList);
        model.addAttribute("appointList", appointList); // 取得したリストをビューに渡す
    	return "listAdd";
    }
    
    @PostMapping("/listDelete")
    public String showDetail(@RequestParam("listId") int listId, Model model) {
    	appointListRepository.deleteById(listId);
    	return "redirect:/appoints";
    }
}
