package com.yxtl.business.constant;

import com.baomidou.mybatisplus.extension.api.IErrorCode;

/**
 * @author px
 * @date 2021/9/18 15:05
 */
public enum ResApiFailCode implements IErrorCode {

    //非预期内出错
    FAILED(-1L, "出错了"),
    //登录出错
    NULL_NAME(-2L, "账号不能为空"),
    ERROR_NAME(-3L, "账号错误"),
    NULL_PASSWORD(-4L, "密码不能为空"),
    ERROR_PASSWORD(-5L, "密码错误"),
    //发货失败
    ERROR_DELIVER(-6L, "发货失败"),
    //新增用户
    ERROR_USER(-7L, "该用户已存在"),
    ERROR_USERNAME(-16L, "该账号已存在"),

    OPERATE_FAILED(-8L, "操作失败"),

    RECHARGE_FAILED(-9L, "充值失败"),
    NULL_PICTURE(-10L, "充值凭证不能为空"),
    NULL_ACCOUNTID(-11L, "该账户不存在"),
    NULL_AMOUNT(-12L, "充值金额不能为空"),
    NULL_CODE(-13L, "充值代码不能为空"),
    ERROR_ACCOUNT(-14L,"信息错误，请确认充值信息"),
    RECHARGE_WARNING(-15L, "不可操作"),
    ERROR_COMPANY(-16L, "该公司名已存在，请重新输入"),
    ERROR_KIND(-17L, "该规格已存在，请重新输入"),
    ERROR_PRODUCT(-18L, "该商品已存在，请重新输入"),
    ERROR_SPU(-19L, "该型号已存在，请重新输入");

    private final long code;
    private final String msg;

    ResApiFailCode(final long code, final String msg) {
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
