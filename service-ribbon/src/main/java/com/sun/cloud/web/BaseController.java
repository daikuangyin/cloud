//package com.sun.cloud.web;
//
//import cn.hutool.core.io.FileUtil;
//import cn.hutool.extra.qrcode.QrCodeUtil;
//import cn.hutool.extra.qrcode.QrConfig;
//import com.sun.cloud.common.util.ServletUtil;
//import com.sun.cloud.domain.vo.APIResponse;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.subject.SimplePrincipalCollection;
//import org.apache.shiro.subject.Subject;
//
//import javax.imageio.ImageIO;
//import javax.servlet.http.HttpServletResponse;
//import java.awt.image.BufferedImage;
//import java.awt.image.RenderedImage;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.URLEncoder;
//import java.util.List;
//
//public abstract class BaseController {
//
//    public static Subject getSubject() {
//        return SecurityUtils.getSubject();
//    }
//
//    public static Session getSession() {
//        return SecurityUtils.getSubject().getSession();
//    }
//
//    public static Member getCurrentMember(boolean... catchException) {
//        return ShiroUtil.getCurrentAppUser(catchException);
//    }
//
//    public static void setCurrentMember(Member member) {
//        Subject subject = getSubject();
//        PrincipalCollection principalCollection = subject.getPrincipals();
//        String realmName = principalCollection.getRealmNames().iterator().next();
//        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(member, realmName);
//        // 重新加载Principal
//        subject.runAs(newPrincipalCollection);
//    }
//
//    public static SysUserVo getCurrentUser(boolean... catchException) {
//        return ShiroUtil.getCurrentSysUser(catchException);
//    }
//
//
//    /**
//     * 获取登录人的ID
//     *
//     * @return
//     */
//    public static Long getLoginId(boolean... catchException) {
//        return ShiroUtil.getLoginId(catchException);
//    }
//
//
//    /**
//     * 如果小程序,sessionKey
//     *
//     * @return
//     */
//    public static String getSessionKey() {
//        Object attribute = getSession().getAttribute(Global.SESSION_KEY);
//        return attribute == null ? null : String.valueOf(attribute);
//    }
//
//    /**
//     * 当前Appkey
//     *
//     * @return
//     */
//    public static String getAppKey() {
//        Object attribute = getSession().getAttribute(Global.APP_KEY);
//        return attribute == null ? null : String.valueOf(attribute);
//    }
//
//    /**
//     * 当前密匙
//     *
//     * @return
//     */
//    public static String getAppSercet() {
//        Object attribute = getSession().getAttribute(Global.APP_SERCET);
//        return attribute == null ? null : String.valueOf(attribute);
//    }
//
//    /**
//     * 返回成功
//     */
//    public APIResponse<Void> success() {
//        return APIResponseBuilder.successNoData();
//    }
//
//    /**
//     * 返回成功
//     */
//    public <T> APIResponse<T> successData(T data) {
//        return APIResponseBuilder.success(data);
//    }
//
//    /**
//     * 响应请求分页数据
//     */
//    protected <T> APIResponse<PageInfo<T>> successPage(List<T> list) {
//        return successData(new PageInfo<T>(list));
//    }
//
//    /**
//     * 返回成功
//     */
//    public APIResponse successMsg(String msg) {
//        return APIResponseBuilder.successNoDataWithMsg(msg);
//    }
//
//
//    /**
//     * 返回失败
//     */
//    public APIResponse fail() {
//        return APIResponseBuilder.fail();
//    }
//
//    /**
//     * 返回失败
//     */
//    public APIResponse failData(ResultCode code) {
//        return APIResponseBuilder.fail(code);
//    }
//
//    /**
//     * 返回失败
//     */
//    public APIResponse failMsg(String msg) {
//        return APIResponseBuilder.failWithMsg(msg);
//    }
//
//    /**
//     * 页面跳转
//     */
//    public String redirect(String url) {
//        return String.format("redirect:%s", url);
//    }
//
//    /**
//     * 下载
//     */
//    public void setDownloadResponse(String fileName) {
//        HttpServletResponse response = ServletUtil.getResponse();
//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-disposition",
//                "attachment; filename=" + setFileDownloadHeader(fileName));
//        response.setContentType("application/x-msdownload");
//    }
//
//    /**
//     * 下载文件名重新编码
//     *
//     * @param fileName 文件名
//     * @return 编码后的文件名
//     */
//    public String setFileDownloadHeader(String fileName) {
//        String filename = fileName;
//        try {
//            final String agent = ServletUtil.getRequest().getHeader("USER-AGENT");
//            if (agent.contains("MSIE")) {
//                // IE浏览器
//                filename = URLEncoder.encode(filename, "utf-8");
//                filename = filename.replace("+", " ");
//            } else if (agent.contains("Firefox")) {
//                // 火狐浏览器
//                filename = new String(fileName.getBytes(), "ISO8859-1");
//            } else if (agent.contains("Chrome")) {
//                // google浏览器
//                filename = URLEncoder.encode(filename, "utf-8");
//            } else {
//                // 其它浏览器
//                filename = URLEncoder.encode(filename, "utf-8");
//            }
//
//        } catch (Exception e) {
//
//        }
//        return filename;
//    }
//
//    /**
//     * 生成二维码
//     *
//     * @param content
//     * @param imgPath
//     * @throws IOException
//     */
//    public BufferedImage getQrCode(String content, String imgPath) {
//        QrConfig qrConfig = new QrConfig();
//        qrConfig.setWidth(200);
//        qrConfig.setHeight(200);
//        qrConfig.setImg(FileUtil.file(imgPath).getPath());
//        return QrCodeUtil.generate(content, qrConfig);
//    }
//
//    /**
//     * 响应结果
//     *
//     * @param response
//     * @param resultStr
//     * @throws IOException
//     */
//    public void respResult(HttpServletResponse response, Object resultStr, String fileType) throws IOException {
//        //禁止图像缓存
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Cache-Control", "no-cache");
//        response.setDateHeader("Expires", 0);
//        response.setContentType(fileType);
//        if ("jpg,jepg,gif,png".contains(fileType)) {
//            response.setContentType("image/" + fileType);
//            ImageIO.write((RenderedImage) resultStr, "jpg", response.getOutputStream());
//        } else {
//            PrintWriter out = response.getWriter();
//            out.write((String) resultStr);
//            out.flush();
//            out.close();
//        }
//
//    }
//}
