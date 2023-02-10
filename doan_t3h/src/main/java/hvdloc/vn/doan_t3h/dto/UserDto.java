package hvdloc.vn.doan_t3h.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class UserDto {
    Long id;
    @NotBlank(message = "Email không được rỗng")
    @Email(message = "Không đúng định dạng email")
    String userName;
    @NotBlank(message = "Mật khẩu không được rỗng")
    @Size(min = 6, message = "Nhập ít nhất 6 ký tự")
    String password;
    String rePassword;
    @NotBlank(message = "Họ và tên không được rỗng")
    String fullName;
    String address;
    @Min(value = 0, message = "Chỉ có 2 giá trị Kích hoạt hoặc tạm dừng")
    @Max(value = 1, message = "Chỉ có 2 giá trị Kích hoạt hoặc tạm dừng")
    Integer status;
    @NotBlank(message = "Quyền không được rỗng")
    String role;
    String date;
    Integer phone;
}