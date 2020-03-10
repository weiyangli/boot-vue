package com.boot.bvserver.util;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;


public class WebServer {

    /**
     *  <Service name="Catalina">
     *     <Connector port="8080" protocol="HTTP/1.1"
     *                connectionTimeout="20000"
     *                redirectPort="8443" />
     *
     *     <!-- Define an AJP 1.3 Connector on port 8009 -->
     *     <Connector port="8009" protocol="AJP/1.3" redirectPort="8443" />
     *     <Engine name="Catalina" defaultHost="localhost">
     *       <Realm className="org.apache.catalina.realm.LockOutRealm">
     *         <Realm className="org.apache.catalina.realm.UserDatabaseRealm"
     *                resourceName="UserDatabase"/>
     *       </Realm>
     *       <Host name="localhost"  appBase="webapps"
     *             unpackWARs="true" autoDeploy="true">
     *         <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
     *                prefix="localhost_access_log." suffix=".txt"
     *                pattern="%h %l %u %t &quot;%r&quot; %s %b" />
     *
     *       </Host>
     *     </Engine>
     *   </Service>
     * @param args
     */
    public static void main(String[] args) {
        Tomcat tomcatServer = new Tomcat();
        Connector connector = tomcatServer.getConnector();
        connector.setPort(9010);
        //是否设置自动部署
        tomcatServer.getHost().setAutoDeploy(false);
        //创建上下文
        StandardContext  standardContext =  new StandardContext();
        //Path上下文路径
        standardContext.setPath("/demo");
        //监听上下文
        standardContext.addLifecycleListener(new Tomcat.FixContextListener());
        //tomcat容器添加standardContext上下文
        tomcatServer.getHost().addChild(standardContext);
        //创建servlet
        tomcatServer.addServlet("/demo", "indexServlet",new DemoServlet());
        //servlet URL映射
        standardContext.addServletMappingDecoded("/index","indexServlet");
        try {
            tomcatServer.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
        //异步进行接收请求
        tomcatServer.getServer().await();
    }
}
