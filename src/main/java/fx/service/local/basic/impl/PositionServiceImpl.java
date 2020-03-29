package fx.service.local.basic.impl;

import fx.dao.mapper.basic.PositionMapper;
import fx.model.entity.hr.Position;
import fx.service.local.basic.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    PositionMapper positionMapper;

    @Override
    public List<Position> getAllPosition() {

        return positionMapper.getAllPositions();
    }
}
