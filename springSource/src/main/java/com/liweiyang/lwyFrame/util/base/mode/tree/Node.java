package com.liweiyang.lwyFrame.util.base.mode.tree;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Node {

    private Person person;
    /**左侧子节点**/
    private Node leftNode;
    /**右侧子节点**/
    private Node rightNode;
}
