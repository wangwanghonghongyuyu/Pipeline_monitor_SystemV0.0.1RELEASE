package cn.enilu.material.admin.modular.system.transfer;

import cn.enilu.material.bean.constant.Const;
import lombok.Data;

@Data
public class ResponseBean<T> {
    private String rcode;
    private T data;

    public ResponseBean() {
    }

    public ResponseBean(T data) {
        this.rcode = Const.RESPONSE_RCODE.SUCCESS;
        this.data = data;
    }

    public ResponseBean(String rcode, T data) {
        this.rcode = rcode;
        this.data = data;
    }

    public ResponseBean(String rcode) {
        this.rcode = rcode;
    }
}
