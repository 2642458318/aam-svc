package fx.model.param.Menu;

public class Meta {
    /**
     * 当离开这个组件时，这个组件是不是要保活
     */
    private Boolean keepalive;
    /**
     * 进入这个组件要不要先登录
     */
    private Boolean requireauth;

    public Boolean getKeepalive() {
        return keepalive;
    }

    public void setKeepalive(Boolean keepalive) {
        this.keepalive = keepalive;
    }

    public Boolean getRequireauth() {
        return requireauth;
    }

    public void setRequireauth(Boolean requireauth) {
        this.requireauth = requireauth;
    }
}
