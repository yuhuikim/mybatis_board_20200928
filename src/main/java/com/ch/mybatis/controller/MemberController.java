package com.ch.mybatis.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ch.mybatis.model.Member;
import com.ch.mybatis.model.MemberPhoto;
import com.ch.mybatis.service.MemberService;

@Controller
public class MemberController {
//자바에서 부르는게 아니라 JSP가 부르는거라 인터페이스를 따로 만들지 않는다.

	@Autowired
	private MemberService ms;

	@RequestMapping("joinForm")
	public String joinForm() {
		return "joinForm";
	}

	@RequestMapping("joinForm2")
	public String joinForm2() {
		return "joinForm2";
	}

	@RequestMapping(value = "idChk", produces = "text/html;charset=utf-8") // text를 html로 바꿔주고 한글은 utf-8로 받기
	@ResponseBody // jsp 통하지 말고 바로 문자로 전달
	public String idChk(String id) { // id가지고 데이터베이스 이동하기
		String msg = "";
		Member member = ms.select(id);
		if (member == null)
			msg = "사용 가능한 ID 입니다.";
		else
			msg = "사용 중인 아이디 입니다.";
		return msg;
	}

	@RequestMapping("join")
	public String join(Member member, Model model, HttpSession session) throws IOException {
		int result = 0;
		// member는 화면에서 입력한 값, mem은 읽어온 데이터.
		Member mem = ms.select(member.getId());
		if (mem == null) {
			String fileName = member.getFile().getOriginalFilename();
			member.setFileName(fileName);
			String real = session.getServletContext().getRealPath("/upload");
			FileOutputStream fos = new FileOutputStream(new File(real + "/" + fileName));
			fos.write(member.getFile().getBytes());
			fos.close();
			result = ms.insert(member);
		} else
			result = -1; // 중복된 아이디
		model.addAttribute("result", result);
		return "join";
	}

	@RequestMapping("join2")
	public String join2(Member member, Model model, HttpSession session, MultipartHttpServletRequest mhsr)
			throws IOException {
		String real = session.getServletContext().getRealPath("/upload");
		List<MultipartFile> list = mhsr.getFiles("file");
		List<MemberPhoto> photos = new ArrayList<>();
		for (MultipartFile mf : list) {
			MemberPhoto mp = new MemberPhoto();
			String fileName = mf.getOriginalFilename();
			mp.setFileName(fileName);
			mp.setId(member.getId());
			photos.add(mp);
			FileOutputStream fos = new FileOutputStream(new File(real + "/" + fileName));
			fos.write(mf.getBytes());
			fos.close();
			// 마지막 그림을 기록, 의미없음
			member.setFileName(fileName);
		}
		int result = ms.insert(member);
		ms.insertPhoto(photos);
		model.addAttribute("result", result);
		return "join";
	}

	@RequestMapping("loginForm")
	public String loginForm() {
		return "loginForm";
	}

	@RequestMapping("login")
	public String login(Member member, Model model, HttpSession session) {
		int result = 0;
		Member mem = ms.select(member.getId());
		if (mem == null || mem.getDel().equals("y"))
			result = -1; // 없거나 탈퇴한 회원이다.
		else if (member.getPassword().equals(mem.getPassword())) {
			result = 1; // 로그인 성공
			session.setAttribute("id", member.getId()); // 로그인 상태 유지
		}
		model.addAttribute("result", result);
		return "login";
	}

	@RequestMapping("main")
	public String main(Model model, HttpSession session) {
		String id = (String) session.getAttribute("id");
		Member member = null;
		if (id != null && !id.equals("")) {
			member = ms.select(id);
		}
		model.addAttribute("member", member);
		return "main";
	}

	@RequestMapping("view")
	public String view(Model model, HttpSession session) {
		String id = (String) session.getAttribute("id");
		Member member = ms.select(id);
		model.addAttribute("member", member);
		return "view";
	}

	@RequestMapping("updateForm")
	public String updateForm(Model model, HttpSession session) {
		String id = (String) session.getAttribute("id");
		Member member = ms.select(id);
		model.addAttribute("member", member);
		return "updateForm";
	}

	@RequestMapping("update")
	public String update(Member member, Model model, HttpSession session) throws IOException {
		String fileName = member.getFile().getOriginalFilename();
		// 그림파일을 수정하지 않으면 null 또는 ""이 넘어온다
		if (fileName != null && !fileName.equals("")) {
			// 그림파일을 수정했을 경우에만 수행
			member.setFileName(fileName);
			String real = session.getServletContext().getRealPath("/upload");
			FileOutputStream fos = new FileOutputStream(new File(real + "/" + fileName));
			fos.write(member.getFile().getBytes());
			fos.close();
		}
		int result = ms.update(member);
		model.addAttribute("result", result);
		return "update";
	}

	@RequestMapping("delete")
	public String delete(Model model, HttpSession session) {
		String id = (String) session.getAttribute("id");
		int result = ms.delete(id);
		if (result > 0) {
			session.invalidate(); // session 삭제
		}
		model.addAttribute("result", result);
		return "delete";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "logout";
	}
}
