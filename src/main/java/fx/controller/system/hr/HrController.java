package fx.controller.system.hr;


import fx.model.entity.hr.Hr;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/basic/hr")
public class HrController {


    @GetMapping("/")
    public List<Hr> getAllHr(){
return null;
    }
}
