package edu.miu.cs.cs544.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs.cs544.domain.AttendanceByMemberIdStatistics;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.Session;
import org.hibernate.mapping.Array;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends BaseRepository<Member, Integer>{
    Optional<Member> findByMemberId(Integer memberId);

    Optional<Member> findByBarCode(Integer barCode);

    @Query(value = "select a.session from Attendance a where a.member.memberId = :memberId")
    List<Session> memberAttendanceForEvent(Integer memberId);

    @Query(value = "select sc.accountType as AccountType, count(sc.accountType) as No from Attendance a join Session s on a.sessionId = s.sessionId " +
                    "join Event e on s.event_id = e.id join Scanner sc on e.id = sc.event_id " +
                    "where a.memberId = :memberId group by sc.accountType", nativeQuery = true)
    List<AttendanceByMemberIdStatistics> calculateAttendanceByMemberId(@Param("memberId") Integer memberId);
}
