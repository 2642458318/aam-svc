package fx.dao.mapper.emp;

import fx.model.entity.emp.Politicsstatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoliticsstatusMapper {
    List<Politicsstatus> getAllPoliticsstatus();
}
