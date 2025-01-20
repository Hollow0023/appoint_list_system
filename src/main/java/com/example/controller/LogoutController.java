package com.example.controller; // このファイルが属するパッケージ（フォルダ）
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // このクラスがWebコントローラーであることを示します
public class LogoutController {

    @GetMapping("/checkLogout") // "/checkLogout"というURLに対するGETリクエストを処理します
    public String logout() {
        return "login";  // login.htmlを表示します
    }
    
    @GetMapping("/logoutCheck")
    public String logoutCheck() {
    	return "logout";
    }
}

