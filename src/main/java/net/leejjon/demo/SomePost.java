package net.leejjon.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor
@Data
public class SomePost {
    @NotNull(message = "'id' field not found")
    @Min(value = 0L, message = "The value must be a positive integer in the 'id' field.")
    private int id;

    @NotNull(message = "'message' field not found")
    @Size(min = 5, max = 200, message = "You must enter a message of min 5 characters and max 200 in the 'message' field.")
    private String message;

    @NotNull(message = "'email' field not found")
    @Email(message = "Enter a valid e-mail address in the 'email' field.")
    private String email;
}
