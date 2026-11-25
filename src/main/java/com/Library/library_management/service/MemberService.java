package com.Library.library_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Library.library_management.dto.MemberRequest;
import com.Library.library_management.dto.MemberResponse;
import com.Library.library_management.entity.Member;
import com.Library.library_management.exception.ResourceNotFoundException;
import com.Library.library_management.repository.MemberRepository;

@Service
public class MemberService {
	private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponse registerMember(MemberRequest request) {

        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                "Member with this email already exists"
            );
        }
        Member member = new Member();
        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setPhone(request.getPhone());
        Member savedMember = memberRepository.save(member);

        return mapToResponse(savedMember);
    }

    public List<MemberResponse> getAllMembers() {
        return memberRepository.findAll() .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public MemberResponse getMemberById(Long id) {

    	 Member member = memberRepository.findById(id)
                 .orElseThrow(() ->
                         new ResourceNotFoundException(
                                 "Member not found with id: " + id));

         return mapToResponse(member);
    }
    
    private MemberResponse mapToResponse(Member member) {

        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getPhone()
        );
    }
}
