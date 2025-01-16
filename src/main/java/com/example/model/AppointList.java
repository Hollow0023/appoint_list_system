package com.example.model;  // このファイルが属するパッケージ（フォルダ）

// 以下の部分はデータベースとのやり取りに必要な情報を持つためのものです。
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity  // これはデータベースのテーブルを表しています
@Data //getter setter不要にするやつ
@Table(name = "appoint_list")  // このクラスが対応するテーブルの名前は "appoint_list" です
public class AppointList {

	 @Id  // これが各ユーザを一意に識別するためのIDとなります
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // IDは自動的に増加します
    @Column(name = "list_id", nullable = true, insertable = true, updatable = false)  // "password" カラム。ユーザーのパスワードを表します
    private int listId;
	
    @Column(name = "user_id")  // データベースに合わせてカラム名を修正
    private int userId;
    
    @Column(name = "store_name", nullable = false)  // "password" カラム。ユーザーのパスワードを表します
    private String storeName;
    
    @Column(name = "phone_number", nullable = false)  // "password" カラム。ユーザーのパスワードを表します
    private String phone;
    
    @Column(name = "address", nullable = true)  // "password" カラム。ユーザーのパスワードを表します
    private String address;
    
    @Column(name = "subject", nullable = true)  // "password" カラム。ユーザーのパスワードを表します
    private String subject;
    
    @Column(name = "industry", nullable = true)  // "password" カラム。ユーザーのパスワードを表します
    private String industry;
    
    @Column(name = "remarks", nullable = true)  // "password" カラム。ユーザーのパスワードを表します
    private String remarks;
    
    @Column(name = "status", nullable = true)  // "password" カラム。ユーザーのパスワードを表します
    private int statusId;
    
    @Column(name = "after_remarks", nullable = true)  // "password" カラム。ユーザーのパスワードを表します
    private String PostRemarks;
    
    @Transient
    private String statusDetail;
}
