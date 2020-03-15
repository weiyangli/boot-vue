package com.dubbo.demo.zuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class FilterUser extends ZuulFilter {
    public FilterUser() {
        super();
    }

    /**
     * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下:
     *
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     * filterOrder：过滤的顺序
     * shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
     * run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 多个 filter 时用于指定 filter 的执行顺序，不过要基于 filterType 基础上。
     *
     * 1.按照filterType决定顺序
     *
     * Pre 优先 Post执行，此时filterOrder没有作用。
     *
     * 2.filterType相同
     *
     * filterOrder有作用，数字越小，越先执行。（负数也是这个规则，0和-1的话，-1先执行）
     *
     * 3.相同filterType,相同filterOrder，都执行，执行顺序不清楚。
     *
     * prefilter先执行了，post后执行了。
     *
     * 感觉不像是按照过滤请名称排序的样子。
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 当前过滤器需要执行的业务
     *
     * @return
     */
    @Override
    public Object run() {
        // RequestContext 类中将每次请求信息存储到了一个 TreadLocal 中
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
//        String token = request.getHeader("token");
        System.out.println("检验用户 jwt 中的信息");
        return null;
    }

    /**
     * 是否启动当前过滤器
     * 可以根据业务判断是否需要过滤，
     * 登录请求 false， 其他请求 true
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }
}
