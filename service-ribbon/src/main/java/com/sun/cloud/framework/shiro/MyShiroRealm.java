package com.sun.cloud.framework.shiro;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sun.cloud.entity.Member;
import com.sun.cloud.service.IMemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class MyShiroRealm extends AuthorizingRealm {


    //如果项目中用到了事物，@Autowired注解会使事物失效，可以自己用get方法获取值
    @Autowired
    private IMemberService memberService;


    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        log.info("---------------- 执行 Shiro 凭证认证 ----------------------");
        //获取用户的输入的账号.
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();

        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法

        // 从数据库获取对应用户名密码的用户
        EntityWrapper memberWrapper = new EntityWrapper();
        memberWrapper.eq("phoneNo", name).eq("deleted", "00");
        Member member = memberService.selectOne(memberWrapper);

        if (member != null) {
            // 用户为禁用状态
            if ("03".equals(member.getUserState())) {
                throw new DisabledAccountException();
            }
            log.info("---------------- Shiro 凭证认证成功 ----------------------");
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    member, //用户
                    member.getUserPwd(), //密码
                    ByteSource.Util.bytes(member.getSalt()),
                    getName()  //realm name
            );
            return authenticationInfo;
        }
        throw new UnknownAccountException();
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("---------------- 执行 Shiro 权限获取 ---------------------");
        Object principal = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        if (principal instanceof SysUser) {
//            SysUser userLogin = (SysUser) principal;
//            Set<String> roles = roleService.findRoleNameByUserId(userLogin.getId());
//            authorizationInfo.addRoles(roles);
//
//            Set<String> permissions = userService.findPermissionsByUserId(userLogin.getId());
//            authorizationInfo.addStringPermissions(permissions);
//        }
//        logger.info("---- 获取到以下权限 ----");
//        logger.info(authorizationInfo.getStringPermissions().toString());
//        logger.info("---------------- Shiro 权限获取成功 ----------------------");
        return authorizationInfo;
    }

}
