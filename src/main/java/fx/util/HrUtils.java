package fx.util;

import fx.model.entity.hr.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

public class HrUtils {
    public static Hr getCurrentHr() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
