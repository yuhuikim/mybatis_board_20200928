package com.ch.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ch.mybatis.dao.MemberDao;
import com.ch.mybatis.model.Member;
import com.ch.mybatis.model.MemberPhoto;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao md;
	// repository로 만들어진 객체가 autowired를 통해서 여기로 쏙 들어간다!

	@Override
	public Member select(String id) {
		return md.select(id);
	}

	@Override
	public int insert(Member member) {
		return md.insert(member);
	}

	@Override
	public int update(Member member) {
		return md.update(member);
	}

	@Override
	public int delete(String id) {
		return md.delete(id);
	}

	@Override
	public void insertPhoto(List<MemberPhoto> photos) {
		md.insertPhoto(photos);
	}

	@Override
	public List<MemberPhoto> listPhoto(String id) {
		return md.listPhoto(id);
	}
}
