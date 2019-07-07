package geektime.spring.transactional.service.impl;

import geektime.spring.transactional.RoolBackException;
import geektime.spring.transactional.service.FooService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author:coder
 * @Date:2019/5/8/008
 * @Description:geektime.spring.transactional.service.impl
 */
@Component
public class FooServiceImpl implements FooService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void insertRecord() {
        jdbcTemplate.execute("insert into FOO (bar) values ('HHH')");
    }

    @Override
    @Transactional(rollbackFor = RoolBackException.class)
    public void insertThenRollback() throws RoolBackException{
        jdbcTemplate.execute("insert into FOO (bar) values ('JJJ')");
        throw new RoolBackException();
    }

    @Override
    public void invokeInsertThenRoollback() {
        ((FooService)AopContext.currentProxy()).insertThenRollback();
        //insertThenRollback();
    }
}
