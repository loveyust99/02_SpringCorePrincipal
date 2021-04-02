package hello.core.member.repository;

import hello.core.member.domain.Member;

public interface MemberRepository {

    /**
     *
     * @param member
     */
    void save(Member member);

    /**
     *
     * @param memberId
     * @return Member 객체
     */
    Member findById(Long memberId);

}
