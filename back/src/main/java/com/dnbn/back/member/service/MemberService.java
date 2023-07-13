package com.dnbn.back.member.service;

import org.springframework.stereotype.Service;

import com.dnbn.back.member.entity.Member;
import com.dnbn.back.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public void create(Member member) {
		memberRepository.save(member);
	}

}
