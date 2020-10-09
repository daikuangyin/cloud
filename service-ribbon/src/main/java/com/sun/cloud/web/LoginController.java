//package com.sun.cloud.web;
//
//import com.sun.cloud.common.constant.Global;
//import com.sun.cloud.common.util.APIResponseBuilder;
//import com.sun.cloud.common.util.IpUtil;
//import com.sun.cloud.common.util.ServletUtil;
//import com.sun.cloud.common.util.ShiroUtil;
//import com.sun.cloud.entity.Member;
//import com.sun.cloud.entity.param.PCLoginParam;
//import com.sun.cloud.entity.vo.APIResponse;
//import com.sun.cloud.enums.DictEnum;
//import com.sun.cloud.enums.LoginMethodEnum;
//import com.sun.cloud.enums.LoginTypeEnum;
//import com.sun.cloud.framework.annotation.Log;
//import com.sun.cloud.framework.shiro.redis.MyWxH5Token;
//import com.sun.cloud.framework.shiro.redis.MyWxToken;
//import com.sun.cloud.framework.shiro.redis.MyWxWebToken;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.subject.Subject;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@Api(tags = "认证")
//@RestController
//@RequestMapping("/auth")
//public class LoginController {
//
//
//    @Value("${config-center.qiNiuYunConfig.systemMark}")
//    private String env;
//
//    @Value("${server.servlet.context-path}")
//    private String product;
//
//    @ApiOperation("用户登录")
//    @PostMapping(value = "/ajaxLogin")
//    public APIResponse submitLogin(
//            @RequestBody @Valid PCLoginParam loginVo
//    ) {
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = handleToken(loginVo);
//        subject.login(token);
//        Map<String, Object> map = handleResult(loginVo);
//        return APIResponseBuilder.success(map);
//    }
//
//
//    /**
//     * 退出登录
//     *
//     * @return
//     */
//    @Log()
//    @ApiOperation("退出登录")
//    @GetMapping("/logOut")
//    public APIResponse logOut() {
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
//        return APIResponseBuilder.successNoDataWithMsg("退出成功");
//
//    }
//
//    /**
//     * 处理登录方式
//     *
//     * @param loginParam 登录方式
//     */
//    private UsernamePasswordToken handleToken(PCLoginParam loginParam) {
//        // 登录方式确立
//        UsernamePasswordToken token = null;
//        LoginMethodEnum loginMethod = DictEnum.valueOfEnum(LoginMethodEnum.class, loginParam.getLoginMethod());
//        if (LoginTypeEnum.SYSTEM.getKey().equals(loginParam.getLoginType())) {
//            //系统用户
//            switch (loginMethod) {
//                case ACCOUNT_PWD:
//                    token = new UsernamePasswordToken(loginParam.getAccount(), loginParam.getPwd());
//                    break;
//            }
//
//        } else if (LoginTypeEnum.MEMBER.getKey().equals(loginParam.getLoginType())) {
//            //会员用户
//            switch (loginMethod) {
//                case SMALL_PROGRAM:
//                    token = new MyWxToken(loginParam.getPwd());
//                    break;
//                case WECHAT_AUTHORIZATION:
//                    token = new MyWxH5Token(loginParam.getPwd());
//                    break;
//                case TEXT_MESSAGES_WEIXIN:
//                    token = new MyWxWebToken(loginParam.getRemark(), loginParam.getAccount(), loginParam.getPwd());
//                    break;
//            }
//        }
//        return token;
//    }
//
//    /**
//     * 处理登录后返回的结果
//     *
//     * @param loginParam
//     * @return
//     */
//    private Map<String, Object> handleResult(PCLoginParam loginParam) {
//        ShiroUtil.getSession().setAttribute(Global.LOGIN_TYPE, loginParam.getLoginType());
//        Map<String, Object> result = new LinkedHashMap<>();
//        LoginMethodEnum loginMethod = DictEnum.valueOfEnum(LoginMethodEnum.class, loginParam.getLoginMethod());
//        if (LoginTypeEnum.SYSTEM.getKey().equals(loginParam.getLoginType())) {
//            //系统用户
//            switch (loginMethod) {
//                case ACCOUNT_PWD:
//                    result.put("user", ShiroUtil.getCurrentSysUser());
//                    result.put("sessionId", ShiroUtil.getSession().getId());
//                    result.put("env", env);
//                    result.put("product", product.replace("/", ""));
//
//                    result.put("menuList", ShiroUtil.getSubject().getPrincipals().oneByType(ArrayList.class));
//                    break;
//            }
//        } else if (LoginTypeEnum.MEMBER.getKey().equals(loginParam.getLoginType())) {
//            //会员用户
//            Member member = ShiroUtil.getCurrentAppUser();
//            member.setLoginIp(IpUtil.getIpAddr(ServletUtil.getRequest()));
//            member.setLoginDevice(loginParam.getDevice());
//            member.addUpdateField("loginIp").addUpdateField("loginDevice");
//            memberSV.updateById(member);
//            result.put("user", member);
//            result.put("sessionId", ShiroUtil.getSession().getId());
//            result.put("appKey", ShiroUtil.getSession().getAttribute(Global.APP_KEY));
//            switch (loginMethod) {
//                case SMALL_PROGRAM:
//
//                    break;
//                case WECHAT_AUTHORIZATION:
//
//                    break;
//            }
//        }
//        // 执行绑定用户token关系
//        ShiroTokenManager.bindUserToken();
//        return result;
//    }
//
//
//}
