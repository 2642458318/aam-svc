package fx.dao.mapper.emp;

import fx.model.entity.emp.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {
    List<Employee> getEmployeeByPage(@Param("page") Integer page, @Param("size") Integer size,@Param("keyword") String keyword);

    Long getTotal(String keyword);

    Integer insertSelective(Employee employee);
}
