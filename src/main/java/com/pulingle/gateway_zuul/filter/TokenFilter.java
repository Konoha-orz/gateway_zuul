package com.pulingle.gateway_zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.pulingle.gateway_zuul.utils.FilterUrlUtil;
import com.pulingle.gateway_zuul.utils.RespondBuilder;
import com.pulingle.gateway_zuul.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by @杨健 on 2018/5/10 14:41
 *
 * @Des: 验证请求中的Token
 */

public class TokenFilter extends ZuulFilter{

    private static final Logger LOGGER= LoggerFactory.getLogger(TokenFilter.class);

    //过滤地址列表
    private static final List<String> urlList= FilterUrlUtil.getUrlList();

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx=RequestContext.getCurrentContext();
        HttpServletRequest request=ctx.getRequest();
        System.out.println(request.getRequestURI());
        //需要验证Token的地址列表
        if(urlList.contains(request.getRequestURI())) {
            //验证是否有Token
            if(request.getHeader("token")==null){//没有Token
                HttpServletResponse response = ctx.getResponse();
                //设置响应
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json");
                ctx.setSendZuulResponse(false);
                try {
                    response.getWriter().write(JSONObject.toJSONString(RespondBuilder.buildTokenErrorResponse("请先登录")));
                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.error("response io异常");
                }
                ctx.setResponse(response);
                return null;
            }else {//有Token
                //验证Token时效
                String token=request.getHeader("token");
                //解析token获取用户信息
                try{
                    Claims claims = TokenUtil.parseJWT(token);
                    //解析成功，时效成功，返回
                    return null;
                }catch (Exception e){
                    LOGGER.error(e.getMessage());
                    //解析不成功
                    HttpServletResponse response = ctx.getResponse();
                    //设置响应
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("application/json");
                    ctx.setSendZuulResponse(false);
                    try {
                        response.getWriter().write(JSONObject.toJSONString(RespondBuilder.buildTokenErrorResponse("请重新登录")));
                    } catch (IOException ioE) {
                        ioE.printStackTrace();
                        LOGGER.error("response io异常");
                    }
                    ctx.setResponse(response);
                }

            }
            return null;
        }else
            return null;

    }
}
