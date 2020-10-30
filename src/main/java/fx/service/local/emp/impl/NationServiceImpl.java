package fx.service.local.emp.impl;

import fx.dao.mapper.emp.NationMapper;
import fx.model.entity.emp.Nation;
import fx.service.local.emp.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationServiceImpl implements NationService {

    @Autowired
    NationMapper nationMapper;
    @Override
    public List<Nation> getAllNations() {
        return nationMapper.getAllNations();
    }
}
