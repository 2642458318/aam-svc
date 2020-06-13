package fx.service.local.basic.impl;

import fx.dao.mapper.basic.JobLevelMapper;
import fx.model.entity.basic.JobLevel;
import fx.model.param.resp.RespBean;
import fx.service.local.basic.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobLevelServiceImpl implements JobLevelService {

    @Autowired
    JobLevelMapper jobLevelMapper;

    @Override
    public List<JobLevel> getAllJobLevels() {
        return jobLevelMapper.getAllJobLevels();
    }

    @Override
    public Integer addJobLevel(JobLevel jobLevel) {
        jobLevel.setCreateDate(new Date());
        jobLevel.setEnabled(true);
        return jobLevelMapper.insertSelective(jobLevel);
    }

    @Override
    public Integer updateJobLevel(JobLevel jobLevel) {
        return jobLevelMapper.updateByPrimaryKeySelective(jobLevel);
    }

    @Override
    public Integer deleteJobLevelById(Integer id) {
        return jobLevelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteJobLevelByIds(Integer[] ids) {
        return jobLevelMapper.deleteJobLevelsByIds(ids);
    }
}
