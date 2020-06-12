package fx.service.local.basic;

import fx.model.entity.basic.Position;

import java.util.List;

public interface PositionService {

    List<Position> getAllPosition();

    Integer addPosition(Position position);

    Integer updatePosition(Position position);

    Integer deletePositionById(Integer id);

    Integer deletePositionByIds(Integer[] ids);
}
