package com.example.model;  // このファイルが属するパッケージ（フォルダ）

// 入力チェックをするためのツールをインポートしています。
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {  // ユーザーのデータを扱うためのクラス

    @NotEmpty  // ユーザー名は空であってはならないというルール
    private String username;  // ユーザー名を保存するための場所

    @NotEmpty  // パスワードは空であってはならないというルール
    private String password;  // パスワードを保存するための場所
    
}


