package com.Library.library_management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Library.library_management.dto.MemberRequest;
import com.Library.library_management.dto.MemberResponse;
import com.Library.library_management.service.MemberService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/members")
public class MemberController {
	private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public MemberResponse registerMember(@Valid @RequestBody MemberRequest request) {
        return memberService.registerMember(request);
    }

    @GetMapping
    public List<MemberResponse> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public MemberResponse getMemberById(@Valid @PathVariable Long id) {
        return memberService.getMemberById(id);
    }
}
