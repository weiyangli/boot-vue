package com.boot.bvserver.dao;

import com.boot.bvserver.bean.ChatGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageDao {

    /**
     * 创建小组
     *
     * @param chatGroup
     */
    void createdGroup(ChatGroup chatGroup);


    /**
     * 批量建立消息群和用户关系
     *
     * @param userIds    用户 id 数组
     * @param groupId    群组 id
     */
    void createdUserGroups(@Param("userIds") Long [] userIds, @Param("groupId") Long groupId);

    /**
     * 查询当前用户加入的所有群聊
     *
     * @param userId    用户 id
     * @return
     */
     List<ChatGroup> findGroupByUserId(Long userId);
}
