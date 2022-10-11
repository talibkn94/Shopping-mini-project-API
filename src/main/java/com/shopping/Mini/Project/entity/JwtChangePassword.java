package com.shopping.Mini.Project.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtChangePassword {
    private String oldPassword;
    private String newPassword;
}
