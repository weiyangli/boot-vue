package com.dubbo.demo.provider.proxy.Handle;

import com.dubbo.demo.provider.annotation.Demo;
import com.dubbo.demo.provider.proxy.GetSubject;
import com.dubbo.demo.provider.proxy.impl.GetSubjectImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ObjectHandle implements InvocationHandler {
    private Object object;
    // 构造方法实例化需要代理的类
    public ObjectHandle(Object object) {
        this.object  = object;
    }

    /*
    * 被代理类需要加强的业务逻辑
    * */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理对象之前");
        // method 方法执行目标对象的当前方法, object 必须为已经实例化的对象
        Object invokeResult = method.invoke(object, args);
        System.out.println("代理对象之后");
        return invokeResult;
    }

    /*
    * 给代理接口创建一个代理类
    * */
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
    }

    public static void main(String[] args) {
        GetSubject getSubject = new GetSubjectImpl();
        getSubject = (GetSubject)new ObjectHandle(getSubject).getProxyInstance();
        getSubject.sayHello();
        getSubject.pullMessage("fsdfs");
    }
}
