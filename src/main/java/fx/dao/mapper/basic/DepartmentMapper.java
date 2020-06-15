package fx.dao.mapper.basic;

import fx.model.entity.basic.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {
    List<Department> getAllDepartmentByParentId(Integer pid);
}
