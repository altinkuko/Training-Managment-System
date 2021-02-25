package com.sda.trainingmanagmentsystem.models.pojo;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRequestParams {
    @NotNull
    @NotEmpty(message = "Required field")
    private String firstName;
    private String lastName;
    @Email(message = "Not a valid email address")
    private String email;
    private String username;
    private String password;

    @AssertTrue(message = "Password must contain at least 4 characters")
    public boolean validPassword() {
        return password.length() > 3 && !password.isBlank();
    }
}
