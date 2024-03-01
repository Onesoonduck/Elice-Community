package com.example.eliceproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotBlank(message = "아이디는 필수 입력사항 입니다.")
    private String loginId;

    @Size(min = 2, max = 25)
    @NotEmpty(message = "이름은 필수 입력사항 입니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수 입력사항 입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{8,16}", message = "비밀번호는 8~16자 영문 대소문자, 숫자를 사용하세요.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인은 필수 입력사항 입니다.")
    private String password2;
}
