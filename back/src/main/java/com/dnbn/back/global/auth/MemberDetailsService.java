package com.dnbn.back.global.auth;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dnbn.back.member.entity.Member;
import com.dnbn.back.member.repository.MemberRepository;

@Service
public class MemberDetailsService implements UserDetailsService {

	@Autowired MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
		// 비밀번호 체크는 스프링부트가 알아서 해준다.
		Member member = memberRepository.findByUserId(user_id).orElseThrow(NoSuchElementException::new);
		if (member != null) {
			return new MemberDetails(member);
		}
		return User.builder()
			.username(member.getUser_id())
			.password(member.getUser_pw())
			.roles(member.getRole().name())
			.build();
	}
}
