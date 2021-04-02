package hello.core.member.service;

import hello.core.member.domain.Member;

public interface MemberService {

    /**
     *
     * @param member
     */
    void join(Member member);

    /**
     *
     * @param memberId
     * @return Member 객체
     */
    Member findMember(Long memberId);

}
