package apractice.class11;

import java.util.ArrayList;
import java.util.List;

public class Code03_EncodeNarryTreeToBinary {

    private static class Node{
        private int value;
        private List<Node> children;

        Node(int value) {
            this.value = value;
        }

        Node(int value, List<Node> children) {
            this.children = children;
        }
    }

    private static class TreeNode{
        private int value;
        private TreeNode left;
        private TreeNode right;

        TreeNode(int value) {
            this.value = value;
        }
    }

    class CodeC{
        public TreeNode enCode(Node node) {
            if(node == null) {
                return null;
            }
            TreeNode treeNode = new TreeNode(node.value);
            treeNode.left = en(node);
            return treeNode;
        }

        public TreeNode en(Node node) {
            List<Node> children = node.children;
            if(children == null || children.size() < 1) {
                return null;
            }
            TreeNode head = null;
            TreeNode cur = null;
            for(Node child:children) {
                TreeNode tNode = new TreeNode(child.value);
                if(head == null) {
                    head = tNode;
                    cur = tNode;
                }else {
                    cur.right = tNode;
                    cur = tNode;
                }
                cur.left = en(child);
            }
            return head;
        }

        public Node deCode(TreeNode node) {
            if(node == null) {
                return null;
            }

            Node head = new Node(node.value);
            head.children = de(node);
            return head;
        }

        public List<Node> de(TreeNode head) {
            TreeNode cur = head.left;
            if(cur == null) {
                return null;
            }
            List<Node> list = new ArrayList<>();
            while(cur != null) {
                Node node = new Node(cur.value);
                node.children = de(cur);
                list.add(node);
                cur = cur.right;

            }

            return list;
        }
    }



}
