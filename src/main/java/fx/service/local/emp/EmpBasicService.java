package fx.service.local.emp;

import fx.model.param.resp.RespPageBean;

public interface EmpBasicService {
    RespPageBean getEmployeeByPage(Integer page, Integer size);
}
