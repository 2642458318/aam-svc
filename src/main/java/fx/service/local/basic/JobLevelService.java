package fx.service.local.basic;

import fx.model.entity.basic.JobLevel;
import fx.model.param.resp.RespBean;

import java.util.List;

public interface JobLevelService {

    List<JobLevel> getAllJobLevels();

    Integer addJobLevel(JobLevel jobLevel);

    Integer updateJobLevel(JobLevel jobLevel);

    Integer deleteJobLevelById(Integer id);

    Integer deleteJobLevelByIds(Integer[] ids);
}
