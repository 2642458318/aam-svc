package fx.service.local.hr;


import fx.model.entity.hr.Hr;

import java.util.List;

public interface HrService  {
    List<Hr> getAllHrsExceptCurrentHr();
}
