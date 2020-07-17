package com.jlyk.service;

import com.jlyk.domian.SysLog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SysLogService {

    void saveSysLog(SysLog sysLog);

    List<SysLog> findAllSysLog(Integer page,Integer size);
}
