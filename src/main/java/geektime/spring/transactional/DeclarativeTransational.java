package geektime.spring.transactional;

import geektime.spring.transactional.service.FooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author:coder
 * @Date:2019/5/8/008
 * @Description:geektime.spring.transactional
 */
@SpringBootApplication
@EnableTransactionManagement
@Slf4j
public class DeclarativeTransational implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FooService fooService;

    public static void main(String[] args) {
        SpringApplication.run(DeclarativeTransational.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        fooService.insertRecord();
        log.info("HHH {}",
                jdbcTemplate.queryForObject("select count(*) from FOO where bar = 'HHH'",Long.class));

        try{
            fooService.insertThenRollback();
        }catch(Exception e){
            log.info("JJJ {}",
                    jdbcTemplate.queryForObject("select count(*) from FOO where bar = 'JJJ'",Long.class));
        }

        try{
            fooService.invokeInsertThenRoollback();
        }catch(Exception e){
            log.info("JJJ {}",
                    jdbcTemplate.queryForObject("select count(*) from FOO where bar = 'JJJ'",Long.class));
        }
    }
}
