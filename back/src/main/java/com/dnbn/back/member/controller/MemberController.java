package com.dnbn.back.member.controller;

import static org.springframework.http.HttpStatus.*;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dnbn.back.member.entity.Member;
import com.dnbn.back.member.entity.Role;
import com.dnbn.back.member.model.MemberCreateDto;
import com.dnbn.back.member.model.MemberLoginDto;
import com.dnbn.back.member.service.MemberService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

	private final MemberService memberService;

	//localhost:8098/api/join?userId=kim&userPw=1234&nickname=주비
	@PostMapping("/join")
	public ResponseEntity<?> join(MemberCreateDto memberCreateDto) {
		memberService.join(memberCreateDto);
		return ResponseEntity.ok("회원가입 success");
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(MemberLoginDto memberLoginDto) {
		String userId = memberService.login(memberLoginDto);
		return ResponseEntity.ok(userId);
	}

}
