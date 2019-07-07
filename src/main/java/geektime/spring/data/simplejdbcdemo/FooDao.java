package geektime.spring.data.simplejdbcdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author:coder
 * @Date:2019/5/7/007
 * @Description:geektime.spring.data.simplejdbcdemo
 */
@Slf4j
@Repository
public class FooDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    public void insertData(){
        Arrays.asList('b','c').forEach(bar ->{
            jdbcTemplate.update("insert into FOO (BAR) VALUES (?)",bar);
        });
        HashMap<String,String> row = new HashMap<>();
        row.put("BAR","d");
        Number id = simpleJdbcInsert.executeAndReturnKey(row);
        log.info("ID of d:{}",id.longValue());
    }

    public void listData(){
        log.info("count: {}",
                jdbcTemplate.queryForObject("select count(*) from FOO",Integer.class));

        List<String> list = jdbcTemplate.queryForList("select bar from foo",String.class);
        list.forEach(s -> log.info("Bar:{}",s));

        List<Foo> fooList = jdbcTemplate.query("select * from foo", new RowMapper<Foo>() {
            @Override
            public Foo mapRow(ResultSet rs, int i) throws SQLException {
                return Foo.builder()
                        .id(rs.getInt(1))
                        .bar(rs.getString(2))
                        .build();
            }
        });
        fooList.forEach(f -> log.info("Foo : {}",f));
    }
}
