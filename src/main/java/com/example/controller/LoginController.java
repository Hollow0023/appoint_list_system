package com.example.controller; // このファイルが属するパッケージ（フォルダ）

// 必要なクラスをインポートします
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // このクラスがWebコントローラーであることを示します
public class LoginController {

    @GetMapping("/login") // "/login"というURLに対するGETリクエストを処理します
    public String login() {
        return "login";  // login.htmlを表示します
    }
    
    @GetMapping("/") // ルートURL ("/") に対するGETリクエストを処理します
    public String redirectToIndex() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
        if (authentication != null && authentication.isAuthenticated()) { 
            return "forward:/appoints";  // forwardでAppointListControllerの@GatMapping("/appoints")へ遷移
        }
        return "redirect:/login"; // 未ログインの場合はログインページへリダイレクト
    }

    
    @GetMapping("/index") // "/index"というURLに対するGETリクエストを処理します
    public String index() {
        return "index"; // index.htmlを表示します
    }
}

