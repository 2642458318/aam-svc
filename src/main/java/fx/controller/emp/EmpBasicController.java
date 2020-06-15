package fx.controller.emp;

import fx.model.param.resp.RespPageBean;
import fx.service.local.emp.EmpBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp/basic/")
public class EmpBasicController {

    @Autowired
    EmpBasicService empBasicService;

    /**
     * 默认查询第一页，每页查询十个
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam (defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size){
        return empBasicService.getEmployeeByPage(page,size);
    }

}
