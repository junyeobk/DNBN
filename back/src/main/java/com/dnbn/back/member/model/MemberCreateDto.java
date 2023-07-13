package com.dnbn.back.member.model;

import java.time.LocalDateTime;

import com.dnbn.back.member.entity.Member;
import com.dnbn.back.member.entity.Role;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class MemberCreateDto {

	private Long id;
	private String user_id;
	private String user_pw;
	private String nickname;
	private Role role;

	public Member toEntity() {
		return Member.builder()
			.user_id(user_id)
			.user_pw(user_pw)
			.nickname(nickname)
			.role(role)
			.build();
	}
}
