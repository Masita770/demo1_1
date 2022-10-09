package com.example.mapper;

import java.sql.DriverManager;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.Query;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.example.domain.User;

@Mapper
public interface UserMapper {
	

	/**
	 * DemoInfo一覧を検索する
	 */
	List<User> selectAll();
	
	
	/**
	 **編集用情報選択
	 */
	Optional<User> selectOne(String id);
	//void ifPresentOrElse(Object demoInfo);


	Optional<User> updateSelect(String id);
	//Object DemoInfo();


	/**
	 * 仮新ユーザー情報登録
	 */
	
	
	public void add(User user);


	
	void update(User update);

}
