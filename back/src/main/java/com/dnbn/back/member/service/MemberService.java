package com.dnbn.back.member.service;

import java.util.NoSuchElementException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dnbn.back.member.entity.Member;
import com.dnbn.back.member.entity.Role;
import com.dnbn.back.member.model.MemberCreateDto;
import com.dnbn.back.member.model.MemberLoginDto;
import com.dnbn.back.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public void join(MemberCreateDto memberCreateDto) {
		String rawPassword = memberCreateDto.getUserPw();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);

		memberCreateDto.setUserPw(encPassword);
		memberCreateDto.setRole(Role.ROLE_USER);

		Member member = memberCreateDto.toEntity();

		memberRepository.save(member);
	}
	//
	// public String login(MemberLoginDto memberLoginDto) {
	// 	Member member = memberRepository.findByUserId(memberLoginDto.getUserId())
	// 		.orElseThrow(NoSuchElementException::new);
	// 	if (!bCryptPasswordEncoder.matches(memberLoginDto.getUserPw(), member.getUserPw())) {
	// 		throw new NoSuchElementException();
	// 	}
	// 	return member.getUserId();
	// }

}
