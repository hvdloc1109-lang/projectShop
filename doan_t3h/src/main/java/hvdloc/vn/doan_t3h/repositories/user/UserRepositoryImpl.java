package hvdloc.vn.doan_t3h.repositories.user;

import hvdloc.vn.doan_t3h.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
// sử dụng 1 câu lệnh thuần sql phức tạp, dùng jdbc template
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<UserDto> findAllTest() {
        String sql = "SELECT * FROM user";
        List<UserDto> listUser2 = jdbcTemplate.query(sql, new RowMapper<UserDto>() {
            @Override
            public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserDto user = new UserDto();
                user.setId(rs.getLong("ID"));
                user.setFullName(rs.getString("FULL_NAME"));
                user.setAddress(rs.getString("ADDRESS"));
                return user;
            }
        });
        return listUser2;
    }
}
