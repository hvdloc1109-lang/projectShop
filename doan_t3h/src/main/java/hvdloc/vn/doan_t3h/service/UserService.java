package hvdloc.vn.doan_t3h.service;

import hvdloc.vn.doan_t3h.dto.ResponseDto;
import hvdloc.vn.doan_t3h.dto.ResponseTableDto;
import hvdloc.vn.doan_t3h.dto.UserChangePassword;
import hvdloc.vn.doan_t3h.dto.UserDto;
import hvdloc.vn.doan_t3h.entities.UserEntity;
import hvdloc.vn.doan_t3h.repositories.user.UserRepository;
import hvdloc.vn.doan_t3h.service.gmail.EmailDetails;
import hvdloc.vn.doan_t3h.service.gmail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseDto save(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        if (userDto.getId() != null){
            userEntity = userRepository.findById(userDto.getId()).get();
        } else {
            userEntity.setUserName(userDto.getUserName());
            userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        userEntity.setFullName(userDto.getFullName());
        userEntity.setRole(userDto.getRole());
        userEntity.setStatus(userDto.getStatus());
        userEntity.setPhone(userDto.getPhone());
        userEntity.setDate(userDto.getDate());
        userEntity.setAddress(userDto.getAddress());
        userRepository.save(userEntity);
        return new ResponseDto("Lưu người dùng "+ userDto.getFullName() + " thành công! ");
    }

    public void list(ResponseTableDto tableDto){
        tableDto.list(userRepository);
    }


    public UserEntity detail(Long id){
        UserEntity detail = userRepository.findById(id).get();
        return detail;
    }

    public String delete(Long id){
        UserEntity detail = userRepository.findById(id).get();
        if(detail == null) return "Tài khoản không tồn tại! ";
        userRepository.deleteById(id);
        return "Xoá thành công!";
    }

    public ResponseDto register(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setFullName(userDto.getFullName());
        userEntity.setUserName(userDto.getUserName());
        userEntity.setRole("CUSTOMER");
        userEntity.setStatus(0);
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(userEntity);
        ResponseDto dto= save(userDto);
        // gui email xac nhan
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(userDto.getUserName());
        emailDetails.setSubject("[Bảo mật] XÁC NHẬN ĐĂNG KÝ TÀI KHOẢN");
        emailDetails.setMsgBody("Click vào đường link bên dưới để xác nhận đăng ký tài khoản! \n"+
                "http://localhost:8080/verify/" + userEntity.getId());
        // gửi mail xác nhận
        emailService.sendSimpleMail(emailDetails);
        return new ResponseDto("Vui lòng kiểm tra Email để xác nhận đăng ký tài khoản!");
    }
    public String verify(Long userId){
        UserEntity userEntity = userRepository.findById(userId).get();
        if (userEntity == null) return "Tài khoản không tồn tại!";
        if (userEntity.getStatus() != 0) return "Tài khoản đã được kích hoạt";
        userEntity.setStatus(1);
        userRepository.save(userEntity);
        return "Kích hoạt thành công!";
    }

    public String changePass(UserChangePassword userChangePassWord) {
        // lấy thông tin user đang đăng nhập
        Long userId = ((UserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        UserEntity userEntity = userRepository.findById(userId).get();
        // kiểm tra thông tin tài khoản
        if (userEntity == null)
            return "Tài khoản đã bị xóa";
        if (!passwordEncoder.matches(userChangePassWord.getOldPassword(), userEntity.getPassword())) {
            return "Mật khẩu không đúng";
        }
        userEntity.setPassword(passwordEncoder.encode(userChangePassWord.getPassword()));
        userRepository.save(userEntity);

        SecurityContextHolder.getContext().setAuthentication(null);
        return "Lưu mật khẩu tài khoản thành công";
    }
}
