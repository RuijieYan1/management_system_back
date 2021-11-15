package com.yxtl.business.constant;

import com.baomidou.mybatisplus.extension.api.IErrorCode;

/**
 * @author px
 * @date 2021/9/18 15:05
 */
public enum ResApiSuccessCode implements IErrorCode {

    //执行成功
    SUCCESS(200L, "执行成功"),
    BIND_SUCCESS(200L, "设备入库成功"),
    RELIEVE_SUCCESS(200L, "设备解绑成功"),
    RECHARGE_SUCCESS(200L, "充值成功");

    private final long code;
    private final String msg;

    ResApiSuccessCode(final long code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public long getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public String toString() {
        return String.format(" ErrorCode:{code=%s, msg=%s} ", this.code, this.msg);
    }

}
