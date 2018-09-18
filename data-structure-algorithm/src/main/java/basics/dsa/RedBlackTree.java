package basics.dsa;

import java.util.ArrayList;

/**
 * Created by Gary Lei on 2018/9/14.
 */
public class RedBlackTree {
    private static final boolean RED = false;

    private static final boolean BLACK = true;

    private int size;

    private Node root;

    private Node root() {
        return this.root;
    }

    private Node parent(Node node) {
        return node == null ? null : node.parent;
    }

    private Node left(Node node) {
        return node == null ? null : node.left;
    }

    private Node right(Node node) {
        return node == null ? null : node.right;
    }

    private int value(Node node) {
        return (node == null) ? 0 : node.value;
    }

    private boolean color(Node node) {
        return (node == null) ? BLACK : node.color;
    }

    private void setRoot(Node root) {
        this.root = root;
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

    private void setColor(Node node, boolean color) {
        if (node != null) {
            node.color = color;
        }
    }

    /**
     * @param pivot 支点节点
     */
    private void rotateLeft(Node pivot) {
        if (pivot == null) {
            return;
        }

        /**
         * 寻找并判断是否有右节点
         */
        Node right = right(pivot);
        if (right == null) {
            return;
        }

        /**
         * 1.将右节点的左节点作为pivot的新右节点
         */
        setRight(pivot, left(right));
        setParent(left(right), pivot);

        /**
         * 2.使用右节点顶替pivot原位置
         */
        setParent(right, parent(pivot));
        if (parent(pivot) == null) {
            setRoot(right);
        } else if (pivot == left(parent(pivot))) {
            setLeft(parent(pivot), right);
        } else {
            setRight(parent(pivot), right);
        }

        /**
         * 3.将pivot作为右节点的新左节点
         */
        setLeft(right, pivot);
        setParent(pivot, right);
    }

    /**
     * @param pivot 支点节点
     */
    private void rotateRight(Node pivot) {
        if (pivot == null) {
            return;
        }

        Node left = left(pivot);
        if (left == null) {
            return;
        }

        /**
         * 1.将左节点的右节点作为pivot的新左节点
         */
        setLeft(pivot, right(left));
        setParent(right(left), pivot);

        /**
         * 2.使用左节点顶替pivot原位置
         */
        setParent(left, parent(pivot));
        if (parent(pivot) == null) {
            setRoot(left);
        } else if (pivot == right(parent(pivot))) {
            setRight(parent(pivot), left);
        } else {
            setLeft(parent(pivot), left);
        }

        /**
         * 3.将pivot作为左节点的新右节点
         */
        setParent(pivot, left);
        setRight(left, pivot);
    }

    private void fixupRemove() {

    }

    private void fixupInsert(Node fixupNode) {
        Node uncleNode;
        while (RED == color(parent(fixupNode))) {
            if (parent(fixupNode) == left(parent(parent(fixupNode)))) {
                uncleNode = right(parent(parent(fixupNode)));
            } else {
                uncleNode = left(parent(parent(fixupNode)));
            }

            if (RED == color(uncleNode)) {
                /**
                 * 叔叔节点为红
                 */
                setColor(parent(fixupNode), BLACK);
                setColor(uncleNode, BLACK);
                fixupNode = parent(parent(fixupNode));
            } else {
                if (fixupNode == right(parent(fixupNode))) {
                    /**
                     * 叔叔节点为黑，当前节点为其父节点的右节点
                     */
                    fixupNode = parent(fixupNode);
                    rotateLeft(fixupNode);
                }

                /**
                 * 叔叔节点为黑，当前节点为其父节点的左节点
                 */
                setColor(parent(fixupNode), BLACK);
                setColor(parent(parent(fixupNode)), RED);
                rotateRight(parent(parent(fixupNode)));
            }
        }
        setColor(root(), BLACK);
    }

    public void add(int value) {
        Node newParent = null;
        Node subRoot = this.root;
        final Node newNode = new Node(value);

        /**
         *1. 查找父节点
         */
        while (subRoot != null) {
            newParent = subRoot;
            if (value(newNode) < value(subRoot)) {
                subRoot = left(subRoot);
            } else {
                subRoot = right(subRoot);
            }
        }

        /**
         * 2. 设置新的节点为父节点的子节点
         */
        setParent(newNode, newParent);
        if (newParent == null) {
            setRoot(newNode);
        } else if (value(newNode) < value(newParent)) {
            setLeft(newParent, newNode);
        } else {
            setRight(newParent, newNode);
        }

        setColor(newNode, RED);
        fixupInsert(newNode);
    }

    public void remove(int value) {

    }

    public boolean contains(int value) {
        Node subRoot = root();
        Node targetNode = null;
        while (subRoot != null) {
            int subRootVal = value(subRoot);
            if (subRootVal == value) {
                targetNode = subRoot;
                break;
            } else if (value < subRootVal) {
                subRoot = left(subRoot);
            } else {
                subRoot = right(subRoot);
            }
        }
        return targetNode != null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size != 0;
    }

    public void print() {
        final int interval = 2;

        final int locOffset = Math.abs(traversalPreOrder(root(), interval));

        final ArrayList<Node> list = new ArrayList<>();
        list.add(root());

        String space = " ";
        do {
            int size = list.size();
            Node[] nodes = list.toArray(new Node[size]);
            list.clear();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < size; i++) {
                Node node = nodes[i];
                int startLoc = node.startLoc + locOffset;
                int length = builder.length();
                if (startLoc > length) {
                    int diff = startLoc - length;
                    for (int j = 0; j < diff; j++) {
                        builder.append(space);
                    }
                }

                builder.append(node.toString());
                Node child = left(node);
                if (child != null) {
                    list.add(child);
                }
                child = right(node);
                if (child != null) {
                    list.add(child);
                }
            }
            System.out.println(builder.toString());
        } while (!list.isEmpty());
    }

    private int traversalPreOrder(Node node, int interval) {
        if (node == null) {
            return 0;
        }

        int minStartLoc = 0;
        final int width = width(node);
        Node parent = parent(node);
        if (parent != null) {
            int parentWidth = width(parent);
            if (node == left(parent)) {
                int startLoc = (parent.startLoc + (parentWidth / 2)) - (interval / 2 + width);
                node.startLoc = startLoc;
                minStartLoc = startLoc;
            } else {
                int startLoc = (parent.startLoc + (parentWidth / 2)) + (interval / 2);
                node.startLoc = startLoc;
            }
        }

        if (left(node) != null) {
            int startLoc = traversalPreOrder(left(node), interval);
            minStartLoc = Math.min(startLoc, minStartLoc);
        }

        if (right(node) != null) {
            traversalPreOrder(right(node), interval);
        }

        return minStartLoc;
    }

    private int width(Node node) {
        return (node == null ? 0 : node.toString().length());
    }

    public static class Node {

        private int value;

        Node parent;

        Node left;

        Node right;

        boolean color = RED;

        private int startLoc;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("[%1$s-%2$s]", value, (RED == color ? "R" : "B"));
        }
    }
}
