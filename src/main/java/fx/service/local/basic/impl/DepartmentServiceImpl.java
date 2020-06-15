package fx.service.local.basic.impl;

import fx.dao.mapper.basic.DepartmentMapper;
import fx.model.entity.basic.Department;
import fx.service.local.basic.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAllDepartment() {
        return departmentMapper.getAllDepartmentByParentId(-1);
    }
}
