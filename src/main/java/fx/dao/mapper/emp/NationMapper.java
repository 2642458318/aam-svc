package fx.dao.mapper.emp;

import fx.model.entity.emp.Nation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NationMapper {
    List<Nation> getAllNations();
}
