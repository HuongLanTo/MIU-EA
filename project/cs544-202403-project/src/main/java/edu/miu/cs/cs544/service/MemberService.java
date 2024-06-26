package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.service.contract.MemberPayload;

import java.util.HashMap;

public interface MemberService extends BaseReadWriteService <MemberPayload, Member, Integer>{
    public void createMember(Member member);

    public void updateMember(Integer memberId, Member member);

    public void deleteMember(Integer memberId);

    Integer memberAttendanceForEvent(Integer memberId, Integer eventId);

    public HashMap<String, Integer> getMemberAttendanceOverAccounts(Integer memberId);
}
