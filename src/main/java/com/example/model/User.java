package com.example.model;  // このファイルが属するパッケージ（フォルダ）

// 以下の部分はデータベースとのやり取りに必要な情報を持つためのものです。
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity  // これはデータベースのテーブルを表しています
@Data //getter setter不要にするやつ
@Table(name = "user")  // このクラスが対応するテーブルの名前は "Users" です
public class User {

    @Id  // これが各ユーザを一意に識別するためのIDとなります
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // IDは自動的に増加します
    @Column(name = "user_id")  // データベースに合わせてカラム名を修正
    private Integer id;

    @Column(name = "user_name", nullable = false, unique = true)  // "username" カラム。各ユーザーのユーザー名を表します。同じ名前のユーザーは存在できません
    private String username;

    @Column(name = "password", nullable = false)  // "password" カラム。ユーザーのパスワードを表します
    private String password;
}
