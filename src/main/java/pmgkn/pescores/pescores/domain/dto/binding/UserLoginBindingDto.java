package pmgkn.pescores.pescores.domain.dto.binding;

public class UserLoginBindingDto {

    private String email;

    public UserLoginBindingDto() {
    }

    public String getEmail() {
        return email;
    }

    public UserLoginBindingDto setEmail(String email) {
        this.email = email;
        return this;
    }

}
