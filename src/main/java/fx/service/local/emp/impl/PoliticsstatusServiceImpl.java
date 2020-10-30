package fx.service.local.emp.impl;

import fx.dao.mapper.emp.PoliticsstatusMapper;
import fx.model.entity.emp.Politicsstatus;
import fx.service.local.emp.PoliticsstatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoliticsstatusServiceImpl implements PoliticsstatusService {

    @Autowired
    PoliticsstatusMapper politicsstatusMapper;
    @Override
    public List<Politicsstatus> getAllPoliticsstatus() {
        return politicsstatusMapper.getAllPoliticsstatus();
    }
}
