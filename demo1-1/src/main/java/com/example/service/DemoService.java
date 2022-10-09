package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.mapper.UserMapper;

@Service
public class DemoService {
	@Autowired
	UserMapper mapper;
	
	public List<User> getDemoList() {
	return mapper.selectAll();
	}
	/*
	 * 仮新規追加処理 2022/04/07
	 */
	public void create(User user) {
		mapper.add(user);
	}
	//*仮作成
	
	public Optional<User> selectOne(String id) {
		//Optional<Optional<User>> st = Optional.ofNullable(mapper.selectOne(id));
		//DemoInfo result = st.orElse(new DemoInfo(id));
		return mapper.selectOne(id);
		}
	
	
	public Optional<User> updateSelect(String id) {
		return mapper.updateSelect(id);
	}
	/*
	 * 編集機能
	 */
	public void update(User update) {
		return;
	}
	
}
