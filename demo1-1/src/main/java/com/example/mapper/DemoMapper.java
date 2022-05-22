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

import com.example.domain.DemoInfo;

@Mapper
public interface DemoMapper {
	

	/**
	 * DemoInfo一覧を検索する
	 * @return　
	 */
	List<DemoInfo> selectAll();
	
	
	/**
	 **編集用情報選択
	 * @return 
	 */
	DemoInfo getUpdate(String id);
	/**
	 * 仮新ユーザー情報登録
	 * @param cari
	 */
	
	//@Insert("INSERT INTO users (id, name, age) VALUES (?, ?, ?), [4, '野島', 21]")
	public void add(DemoInfo demoInfo);

	//DemoInfo createCheck(DemoInfo demoInfo);
	
	void update(DemoInfo update);

}
