package com.dnbn.back.member.controller;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnbn.back.member.entity.Member;
import com.dnbn.back.member.entity.Role;
import com.dnbn.back.member.model.MemberCreateDto;
import com.dnbn.back.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

	private final MemberService memberService;

	//localhost:8098/api/join?userId=kim&userPw=1234&nickname=주비
	@PostMapping("/join")
	public ResponseEntity<?> join(MemberCreateDto memberCreateDto) {
		Member member = memberService.join(memberCreateDto);
		return ResponseEntity.ok(member);
	}




}
