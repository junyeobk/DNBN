package com.dnbn.back.member.model;

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
	private String userId;
	private String userPw;
	private String nickname;
	private Role role;

	public Member toEntity() {
		return Member.builder()
			.userId(userId)
			.userPw(userPw)
			.nickname(nickname)
			.role(role)
			.build();
	}
}
