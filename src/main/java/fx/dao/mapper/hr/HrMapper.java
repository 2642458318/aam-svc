package fx.dao.mapper.hr;

import fx.model.entity.hr.Hr;
import fx.model.entity.hr.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HrMapper {
    /*int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    Hr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);*/

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    Hr loadUserByUsername(@Param("username") String username);

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    List<Role> getHrRolesById(@Param("id") Integer id);

    List<Hr> getAllHrsExceptCurrentHr(Integer id);
}