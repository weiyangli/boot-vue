package com.dubbo.demo.provider.proxy.Handle;

import com.dubbo.demo.provider.proxy.impl.GetSubjectImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    public Object newInstall(Object object) {

        return Enhancer.create(object.getClass(), this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("先热身一会");
        Object o1 = methodProxy.invokeSuper(o,objects);
        System.out.println("打完了");
        return o1;
    }

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        GetSubjectImpl getSubject = (GetSubjectImpl)cglibProxy.newInstall(new GetSubjectImpl());
        getSubject.sayHello();
    }
}
