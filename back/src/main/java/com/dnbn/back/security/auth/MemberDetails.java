package com.dnbn.back.security.auth;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dnbn.back.member.entity.Member;

import lombok.Data;

@Data
public class MemberDetails implements UserDetails {

	private final Member member;

	public MemberDetails(Member member) {
		this.member = member;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(
			new SimpleGrantedAuthority(member.getRole().name())
		);
	}

	@Override
	public String getPassword() {
		return member.getUserPw();
	}

	@Override
	public String getUsername() {
		return member.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
