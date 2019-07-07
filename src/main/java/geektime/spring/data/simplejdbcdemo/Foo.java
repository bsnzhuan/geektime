package geektime.spring.data.simplejdbcdemo;

import lombok.Builder;
import lombok.Data;

/**
 * @Author:coder
 * @Date:2019/5/7/007
 * @Description:geektime.spring.data.simplejdbcdemo
 */
@Data
@Builder
public class Foo {
    private Integer id;
    private String bar;
}
