package com.chatapp.chatapp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserResponse
{
    private String id;
    private String username;
    private String email;
    private Date createdAt;
}
