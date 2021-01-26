package basics.test;

/**
 * Created by Lei Guoting on 2019-12-21.
 */
public class BinarySearchTree {

    public static class Node {
        private int value;

        private Node parent;

        private Node left;

        private Node right;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }
    }

    private Node root;

    private int size;

    public void add(int value) {
        Node root = root();

        //find parent
        Node current = root;
        Node parent = null;
        while (current != null) {
            parent = current;
            int nodeValue = valueOf(current);
            if (value == nodeValue) {
                return;
            } else if (value < nodeValue) {
                current = leftOf(current);
            } else {
                current = rightOf(current);
            }
        }

        Node newNode = new Node(value);
        newNode.setParent(parent);
        this.size++;
        if (parent == null) {
            this.root = newNode;
            return;
        }

        int parentValue = valueOf(parent);
        if (value < parentValue) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }
    }

    public boolean remove(int value) {
        Node target = find(value);
        if (target == null) {
            return false;
        }


        Node left = leftOf(target);
        Node right = rightOf(target);
        //查找继承节点
        Node successor = target;
        if (left != null && right != null) {
            //查找右子树中最小值作为继承节点
            Node rightMinNode = right;
            Node rightMinNodeLeft = leftOf(right);
            while (rightMinNodeLeft != null) {
                rightMinNode = rightMinNodeLeft;
                rightMinNodeLeft = leftOf(right);
            }
            successor = rightMinNode;
        }

        //删除继承节点
        Node parent = parentOf(successor);
        left = leftOf(successor);
        right = rightOf(successor);
        Node newChildNode;
        if (left != null) {
            newChildNode = left;
        } else {
            newChildNode = right;
        }
        if (newChildNode != null) {
            newChildNode.setParent(parent);
        }

        if (parent == null) {
            this.root = newChildNode;
        } else if (leftOf(parent) == successor) {
            parent.setLeft(newChildNode);
        } else {
            parent.setRight(newChildNode);
        }
        this.size--;
        return true;
    }

    public int size() {
        return this.size;
    }

    public boolean contains(int value) {
        return find(value) != null;
    }

    private Node find(int value) {
        Node current = root();
        while (current != null) {
            int nodeValue = valueOf(current);
            if (value == nodeValue) {
                break;
            } else if (value < nodeValue) {
                current = leftOf(current);
            } else {
                current = rightOf(current);
            }
        }
        return current;
    }

    private Node root() {
        return this.root;
    }

    private int valueOf(Node node) {
        return node.getValue();
    }

    private Node leftOf(Node node) {
        return node != null ? node.getLeft() : null;
    }

    private Node rightOf(Node node) {
        return node != null ? node.getRight() : null;
    }

    private Node parentOf(Node node) {
        return node != null ? node.getParent() : null;
    }
}
