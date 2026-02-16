package com.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.library.domain.Member;
import com.library.domain.Staff;
import com.library.domain.Student;
import com.library.dto.CreateMemberRequest;
import com.library.dto.MemberResponse;

@Service
public class MemberService {

    public final LibraryState state;

    public MemberService(LibraryState state) {
        this.state = state;
    }

    public MemberResponse addMember(CreateMemberRequest req) {
        int id = this.state.getNextMemberId().getAndIncrement();

        Member member = null;

        if ("STUDENT".equalsIgnoreCase(req.type())){
            member = new Student(id, req.name());
        } else {
            member = new Staff(id, req.name()); 
        }
        this.state.getIdToMember().put(id, member);
        return toMemberResponse(id, member);
    }

    // TODO - how does this getIdToMember() work??
    public List<MemberResponse> getAllMembers() {
        List<MemberResponse> out = new ArrayList<>();

        for (Map.Entry<Integer, Member> e : this.state.getIdToMember().entrySet()) {
            out.add(toMemberResponse(e.getKey(), e.getValue()));
        }
        return out;
    }

    public MemberResponse getMember(int id) {
        Member member = this.state.getIdToMember().get(id);
        return member == null ? null : toMemberResponse(id, member);
    }

    private MemberResponse toMemberResponse(int id, Member member) {
        String type = member instanceof Staff ? "STAFF" : "STUDENT";
        int activeLoans = member.getBooks_borrowed().size();
        return new MemberResponse(id, member.getName(), type, activeLoans);
    } 
}
