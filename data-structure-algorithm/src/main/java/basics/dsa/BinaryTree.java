package basics.dsa;

/**
 * Created by Lei Guoting on 2019/4/19.
 */
public class BinaryTree {

    private int size;

    private Node root;


    private Node root() {
        return this.root;
    }

    private Node parentOf(Node node) {
        return node == null ? null : node.parent;
    }

    private Node leftOf(Node node) {
        return node == null ? null : node.left;
    }

    private Node rightOf(Node node) {
        return node == null ? null : node.right;
    }

    private int valueOf(Node node) {
        return (node == null) ? 0 : node.value;
    }


    private void setParent(Node child, Node parent) {
        if (child != null) {
            child.parent = parent;
        }
    }

    private void setLeft(Node parent, Node left) {
        if (parent != null) {
            parent.left = left;
        }
    }

    private void setRight(Node parent, Node right) {
        if (parent != null) {
            parent.right = right;
        }
    }

    public static class Node {

        private int value;

        Node parent;

        Node left;

        Node right;

        private int startLoc;

        private int level;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return Integer.toString(value);
        }
    }
}
