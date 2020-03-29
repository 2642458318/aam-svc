package fx.dao.mapper.basic;


import fx.model.entity.hr.Position;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    List<Position> getAllPositions();
}