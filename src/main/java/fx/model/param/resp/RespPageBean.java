package fx.model.param.resp;

import java.util.List;

public class RespPageBean {
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 差了多少页
     */
    private List<?> data;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
