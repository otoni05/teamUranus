package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserForm;

/**
 * ユーザー情報のデータアクセスを行うためのリポジトリクラスです。
 * Spring Data JPAのJpaRepositoryを拡張しています。
 */
public interface UserRepository extends JpaRepository<UserForm, String> {

	/**
	 * ユーザーIDをもとにユーザー情報を取得します。
	 *
	 * @param userId 取得するユーザーのID
	 * @return ユーザー情報
	 */
	UserForm findByUserId(String userId);
}