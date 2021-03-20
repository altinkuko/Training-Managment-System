package com.sda.trainingmanagmentsystem.models.pojo;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRequestParams {
    @NotNull
    @NotEmpty(message = "Please enter e Name")
    @Size(min = 3, max = 20)
    private String firstName;
    private String lastName;
    @Email(message = "Not a valid email address")
    private String email;
    @NotNull
    @NotEmpty(message = "Please enter a Username")
    @Size(min = 4, message = "Username must have at least 4 characters")
    private String username;
    @NotNull
    @NotEmpty
    @Size(min = 4)
    private String password;

    @AssertTrue(message = "Password must contain at least 4 characters")
    public boolean validPassword() {
        return password.length() > 3 && !password.isBlank();
    }
}
