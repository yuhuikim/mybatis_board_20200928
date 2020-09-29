package com.ch.mybatis.dao;

import com.ch.mybatis.model.Member;
import com.ch.mybatis.model.MemberPhoto;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl implements MemberDao {

	// mybatis 연결
	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public Member select(String id) {
		return sst.selectOne("memberns.select", id);
	}

	@Override
	public int insert(Member member) {
		return sst.insert("memberns.insert", member);
	}

	@Override
	public int update(Member member) {
		return sst.update("memberns.update", member);
	}

	@Override
	public int delete(String id) {
		return sst.update("memberns.delete", id);
	}

	@Override
	public void insertPhoto(List<MemberPhoto> photos) {
		sst.insert("memberns.insertPhoto", photos);
	}

	@Override
	public List<MemberPhoto> listPhoto(String id) {
		return sst.selectList("memberns.listPhoto", id);
	}
}
