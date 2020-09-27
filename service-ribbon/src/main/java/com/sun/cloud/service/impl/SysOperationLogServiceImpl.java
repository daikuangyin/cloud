package com.sun.cloud.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sun.cloud.entity.SysOperationLog;
import com.sun.cloud.mapper.SysOperationLogMapper;
import com.sun.cloud.service.ISysOperationLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统操作日志表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2020-09-27
 */
@Service
public class SysOperationLogServiceImpl extends ServiceImpl<SysOperationLogMapper, SysOperationLog> implements ISysOperationLogService {

}
