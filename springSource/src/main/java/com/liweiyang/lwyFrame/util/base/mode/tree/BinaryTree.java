package com.liweiyang.lwyFrame.util.base.mode.tree;

/**
 * 二叉搜索树
 * 1: 唯一根节点
 * 2：父节点左侧的值小于自己，右侧可大于等于自己
 *
 */
public class BinaryTree implements ModelBase<Node, Long> {

    private Node root;

    @Override
    public void insert() {

    }

    @Override
    public Node find(Long key) {
        Node current = root;
        while (current.getPerson().getId().equals(key)) {
            if (key < current.getPerson().getId()) {
                current = current.getLeftNode();
            } else {
                current = current.getRightNode();
            }
            if (current == null) {
                System.out.println("未找到数据");
                return null;
            }
        }
        return current;
    }

    @Override
    public Node delete(Long key) {
        return null;
    }
}
