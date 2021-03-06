package fx.dao.mapper.hr;


import fx.model.entity.hr.Menu;
import fx.model.entity.hr.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> getMenuById(Integer hrId);

    List<Menu> getAllMenuWitchRole();

    List<Menu> getMenusByHrId(Integer Hrid);

    List<Menu> getAllMenu();

    List<Integer> getMidsByRid(Integer rid);

    Integer deleteByRid(Integer rid);

    Integer insertRecord(@Param("rid") Integer rid,@Param("mids") Integer[] mids);

}