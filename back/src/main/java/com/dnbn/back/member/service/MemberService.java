package com.dnbn.back.member.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dnbn.back.member.entity.Member;
import com.dnbn.back.member.entity.Role;
import com.dnbn.back.member.model.MemberCreateDto;
import com.dnbn.back.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public Member join(MemberCreateDto memberCreateDto) {
		String rawPassword = memberCreateDto.getUserPw();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		memberCreateDto.setUserPw(encPassword);
		memberCreateDto.setRole(Role.ROLE_USER);
		Member member = memberCreateDto.toEntity();
		return memberRepository.save(member);
	}

}
