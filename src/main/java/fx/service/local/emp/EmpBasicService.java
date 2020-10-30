package fx.service.local.emp;

import fx.model.entity.emp.Employee;
import fx.model.param.resp.RespPageBean;

public interface EmpBasicService {
    RespPageBean getEmployeeByPage(Integer page, Integer size,String keyword);

    Integer addEmp(Employee employee);
}
