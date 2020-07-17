package com.jlyk.service.impl;

import com.github.pagehelper.PageHelper;
import com.jlyk.dao.SysLogDao;
import com.jlyk.domian.SysLog;
import com.jlyk.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;
    @Override
    public void saveSysLog(SysLog sysLog) {
        sysLogDao.saveSysLog(sysLog);
    }

    @Override
    public List<SysLog> findAllSysLog(Integer page,Integer size) {
        PageHelper.startPage(page,size);
        return sysLogDao.findAllSysLog();
    }
}
