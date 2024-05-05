package com.web.application.member.repository;

import com.web.application.member.entity.Member;
import org.springframework.data.repository.ListCrudRepository;

public interface MemberRepository extends ListCrudRepository<Member, Long> {
}
