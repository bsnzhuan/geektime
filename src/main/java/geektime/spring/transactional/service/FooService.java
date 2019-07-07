package geektime.spring.transactional.service;

/**
 * @Author:coder
 * @Date:2019/5/8/008
 * @Description:geektime.spring.transactional.service
 */
public interface FooService {
    void insertRecord();

    void insertThenRollback();

    void invokeInsertThenRoollback();
}
