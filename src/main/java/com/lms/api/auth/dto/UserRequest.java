package com.lms.api.auth.dto;

import lombok.*;

/**
 * Created by Patrick Muriithi.
 * Date: 3/9/2024
 * Time: 6:40 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String username;
    private String password;
}
