package com.example.repository;  // このファイルが属するパッケージ（フォルダ）

// 必要なツールをインポートしています
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Status;

// UserRepositoryというインターフェースを作成します。JpaRepositoryを拡張して、UserオブジェクトとそれらのIDとしてLong型を扱えるようにします。
public interface StatusRepository extends JpaRepository<Status, Integer> {
    // ユーザー名でユーザーを探すメソッド。ユーザー名をパラメータとして渡すと、そのユーザー名を持つユーザーをデータベースから探して返します。
	Status findByStatusId(int statusId);
}

