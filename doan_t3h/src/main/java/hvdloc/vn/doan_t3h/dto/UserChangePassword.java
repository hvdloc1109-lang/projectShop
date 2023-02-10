package hvdloc.vn.doan_t3h.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserChangePassword {
    String userName;
    @Size(min = 6, message = "Nhập ít nhất 6 ký tự")
    String password;
    @Size(min = 6, message = "Nhập ít nhất 6 ký tự")
    String rePassword;
    @Size(min = 6, message = "Nhập ít nhất 6 ký tự")
    String oldPassword;
}
