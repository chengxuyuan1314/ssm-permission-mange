package com.jlyk.dao;

import com.jlyk.domian.Member;
import com.jlyk.domian.Product;
import org.apache.ibatis.annotations.Select;


public interface MemberDao {
    @Select("select * from member where id= #{id}")
    Member findById(String id);
}
