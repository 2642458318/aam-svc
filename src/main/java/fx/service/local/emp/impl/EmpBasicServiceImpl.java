package fx.service.local.emp.impl;

import fx.dao.mapper.emp.EmployeeMapper;
import fx.model.entity.emp.Employee;
import fx.model.param.resp.RespPageBean;
import fx.service.local.emp.EmpBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpBasicServiceImpl implements EmpBasicService {

    @Autowired
    EmployeeMapper employeeMapper;
    @Override
    public RespPageBean getEmployeeByPage(Integer page, Integer size,String keyword) {
        if (page != null && size != null) {
            page=(page-1)*size;
        }
        List<Employee> data=employeeMapper.getEmployeeByPage(page,size,keyword);
        Long total=employeeMapper.getTotal(keyword);
        RespPageBean bean=new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    @Override
    public Integer addEmp(Employee employee) {
        return employeeMapper.insertSelective(employee);
    }
}
