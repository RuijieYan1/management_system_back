package com.yxtl.business.constant;

/**
 * @Author: px
 * @Date: 2021/09/19/13:51
 * @Description:
 */
public class Constant {

    //文件上传路径
    public static final String BINDEXCELPATH = "D:\\Project\\WebStormProjects\\admin-version-front\\public\\file\\excel\\bind\\";
    public static final String UNBINDEXCELPATH = "D:\\Project\\WebStormProjects\\admin-version-front\\public\\file\\excel\\unbind\\";
    public static final String IMAGEPATH = "D:\\Project\\WebStormProjects\\admin-version-front\\public\\file\\images\\";

    //绑定/解绑
    public static final Integer BIND = 0;
    public static final Integer UNBIND = 1;

    //常量
    public static final Integer ZEROR = 0;

    //订单状态
    public static final Integer WAITTING = 1;
    public static final Integer DELIVER = 2;
    public static final Integer CANCEL = 3;
    public static final Integer COMPLETE = 4;
    public static final String SHOWWAIT = "待发货";
    public static final String SHOWDELIVER = "已发货";
    public static final String SHOWCANCEL = "订单取消";
    public static final String SHOWCOMPLETE = "订单完成";

    //充值审核状态
    public static final Integer WAITTING_REVIEW = 0;
    public static final Integer REFUSE = 1;
    public static final Integer AGREE = 2;

    //账号身份
    public static final String ADMIN = "admin";
    public static final String OPERATOR = "operator";

    //默认信息
    public static final String DEFAULT_ACCOUNTPWD = "123456";

    //公归公订单状态
    public static final Integer PAYMENT_TYPE = 3;

    //mqtt消息状态
    public static final Integer DELIVER_GOODS = 0;
    public static final Integer RECHARGE = 1;
    public static final Integer UNREAD = 0;
    public static final Integer READ = 1;
    public static final Integer DELETE = 2;

    //上架、下架
    public static final Integer ON_SHELF = 1;
    public static final Integer OFF_SHELF = 0;
    public static final Integer IS_SUIT = 1;
    public static final Integer IS_NOT_SUIT = 0;

    //充值mqtt
    public static final Integer UNSAME = 0;
    public static final Integer SAME = 1;

    //线下充值
    public static final String OFFLINE = "2";

}
