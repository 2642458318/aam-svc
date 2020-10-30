package fx.controller.emp;

import fx.dao.mapper.emp.NationMapper;
import fx.model.entity.emp.Employee;
import fx.model.param.resp.RespBean;
import fx.model.param.resp.RespPageBean;
import fx.service.local.basic.DepartmentService;
import fx.service.local.basic.JobLevelService;
import fx.service.local.basic.PositionService;
import fx.service.local.emp.EmpBasicService;
import fx.service.local.emp.PoliticsstatusService;
import fx.util.PoiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/emp/basic/")
public class EmpBasicController {

    @Autowired
    EmpBasicService empBasicService;

    @Autowired
    PoiUtils poiUtils;

    @Autowired
    NationMapper nationMapper;

    @Autowired
    PoliticsstatusService politicsstatusService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    PositionService positionService;

    @Autowired
    JobLevelService jobLevelService;

    /**
     * 默认查询第一页，每页查询十个
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          String keyword) {
        return empBasicService.getEmployeeByPage(page, size, keyword);
    }

    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee) {
        if (empBasicService.addEmp(employee) == 1) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    /**
     * 数据导出
     *
     * @return
     */
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData() {
        //获取所有数据
        List<Employee> list = (List<Employee>) empBasicService.getEmployeeByPage(null, null, null).getData();
        return poiUtils.employee2Excel(list);
    }

    @PostMapping("/import")
    public RespBean importData(MultipartFile file) {
        List<Employee> list = PoiUtils.excel2Employee(file, nationMapper.getAllNations(), politicsstatusService.getAllPoliticsstatus(), departmentService.getAllDepartment(), positionService.getAllPosition(), jobLevelService.getAllJobLevels());
        return RespBean.ok("文件上传成功");
    }

}
