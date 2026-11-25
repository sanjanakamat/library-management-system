package com.Library.library_management.dto;

public class MemberResponse {
	 private Long id;
	    private String name;
	    private String email;
	    private String phone;

	    public MemberResponse(Long id, String name, String email, String phone) {
	        this.id = id;
	        this.name = name;
	        this.email = email;
	        this.phone = phone;
	    }

	    public Long getId() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public String getPhone() {
	        return phone;
	    }
}
