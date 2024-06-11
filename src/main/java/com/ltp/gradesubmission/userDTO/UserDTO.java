package com.ltp.gradesubmission.userDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class UserDTO {
    private String username;
    private long Id;
}
