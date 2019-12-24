package com.boot.bvserver.service.impl;

import com.boot.bvserver.bean.Message;
import com.boot.bvserver.service.MessageService;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service(value = "messageService")
public class MessageServiceImpl implements MessageService {

    /**
     * 用来存已连接的客户端
     */
    private static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();

    /**
     * socketIo的对象
     */
    @Autowired
    private SocketIOServer socketIOServer;

    /**
     * 功能描述：当前的service被初始化的时候执行以下的方法
     * @throws Exception
     */
    @PostConstruct
    private void autoStartUp() throws Exception {
        start();
    }

    /**
     * 功能描述：当我们的系统停止的时候关闭我们的 socketIo
     * @throws Exception
     */
    @PreDestroy
    private void autoStop() throws Exception {
        stop();
    }

    @Override
    public void start() throws Exception {
        // 监听客户端连接
        socketIOServer.addConnectListener( client -> {
            /**
             * 此处实现我们的socket的连接的用户的逻辑，此处我前端传的是 chatId 这个参数，大家可以根据自己的情况来定义入参
             */
            String loginUser = getParamsByClient(client).get("chatId").get(0);
            clientMap.put(loginUser, client);
        });
        // 监听客户端断开连接
        socketIOServer.addDisconnectListener(client -> {
            String loginUser = getParamsByClient(client).get("chatId").get(0);
            if (loginUser != null && !"".equals(loginUser)) {
                 clientMap.remove(loginUser);
                 client.disconnect();
            }
        });

        // 处理自定义的事件，与连接监听类似
        socketIOServer.addEventListener(PUSH_EVENT, Message.class, (client, data, ackSender) -> {
            // TODO do something
            client.sendEvent(PUSH_EVENT, data);
        });
        socketIOServer.start();

    }

    @Override
    public void stop() {
        if (socketIOServer != null) {
            socketIOServer.stop();
            socketIOServer = null;
        }
    }

    /**
     * 功能描述：发送消息到前端
     *
     * @param message 发送消息的实体
     */
    @Override
    public void pushMessageToUser(Message message) {
        SocketIOClient socketIOClient = clientMap.get(message.getChatId());
        if (socketIOClient != null) {
            socketIOClient.sendEvent(message.getChatId(), message);
        }
    }

    /**
     * 此方法为获取client连接中的参数，可根据需求更改
     *
     * @param client
     * @return
     */
    private Map<String, List<String>> getParamsByClient(SocketIOClient client) {
        // 从请求的连接中拿出参数
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        return params;
    }
}
