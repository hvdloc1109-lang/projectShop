package hvdloc.vn.doan_t3h.service.gmail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
        // Class data members
        private String recipient; // ng nhan
        private String msgBody; // noi dung gui
        private String subject; // tiu de
        private String attachment; // file
}
