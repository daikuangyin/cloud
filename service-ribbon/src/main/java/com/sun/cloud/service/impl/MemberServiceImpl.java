package com.sun.cloud.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sun.cloud.entity.Member;
import com.sun.cloud.mapper.MemberMapper;
import com.sun.cloud.service.IMemberService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2020-09-28
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {


}
