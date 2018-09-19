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

    private boolean colorOf(Node node) {
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
        Node right = rightOf(pivot);
        if (right == null) {
            return;
        }

        /**
         * 1.将右节点的左节点作为pivot的新右节点
         */
        setRight(pivot, leftOf(right));
        setParent(leftOf(right), pivot);

        /**
         * 2.使用右节点顶替pivot原位置
         */
        setParent(right, parentOf(pivot));
        if (parentOf(pivot) == null) {
            setRoot(right);
        } else if (pivot == leftOf(parentOf(pivot))) {
            setLeft(parentOf(pivot), right);
        } else {
            setRight(parentOf(pivot), right);
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

        Node left = leftOf(pivot);
        if (left == null) {
            return;
        }

        /**
         * 1.将左节点的右节点作为pivot的新左节点
         */
        setLeft(pivot, rightOf(left));
        setParent(rightOf(left), pivot);

        /**
         * 2.使用左节点顶替pivot原位置
         */
        setParent(left, parentOf(pivot));
        if (parentOf(pivot) == null) {
            setRoot(left);
        } else if (pivot == rightOf(parentOf(pivot))) {
            setRight(parentOf(pivot), left);
        } else {
            setLeft(parentOf(pivot), left);
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
        while (RED == colorOf(parentOf(fixupNode))) {
            if (parentOf(fixupNode) == leftOf(parentOf(parentOf(fixupNode)))) {
                Node uncleNode = rightOf(parentOf(parentOf(fixupNode)));
                if (RED == colorOf(uncleNode)) {
                    /**
                     * 叔叔节点为红
                     */
                    setColor(parentOf(fixupNode), BLACK);
                    setColor(uncleNode, BLACK);
                    setColor(parentOf(parentOf(fixupNode)), RED);
                    fixupNode = parentOf(parentOf(fixupNode));
                } else {
                    if (fixupNode == rightOf(parentOf(fixupNode))) {
                        /**
                         * 叔叔节点为黑，当前节点为其父节点的右节点
                         */
                        fixupNode = parentOf(fixupNode);
                        rotateLeft(fixupNode);
                    }

                    /**
                     * 叔叔节点为黑，当前节点为其父节点的左节点
                     */
                    setColor(parentOf(fixupNode), BLACK);
                    setColor(parentOf(parentOf(fixupNode)), RED);
                    rotateRight(parentOf(parentOf(fixupNode)));
                }
            } else {
                Node uncleNode = leftOf(parentOf(parentOf(fixupNode)));
                if (RED == colorOf(uncleNode)) {
                    setColor(parentOf(fixupNode), BLACK);
                    setColor(uncleNode, BLACK);
                    setColor(parentOf(parentOf(fixupNode)), RED);
                    fixupNode = parentOf(parentOf(fixupNode));
                } else {
                    if (fixupNode == leftOf(parentOf(fixupNode))) {
                        fixupNode = parentOf(fixupNode);
                        rotateRight(fixupNode);
                    }
                    setColor(parentOf(fixupNode), BLACK);
                    setColor(parentOf(parentOf(fixupNode)), RED);
                    rotateLeft(parentOf(parentOf(fixupNode)));
                }
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
            if (valueOf(newNode) < valueOf(subRoot)) {
                subRoot = leftOf(subRoot);
            } else {
                subRoot = rightOf(subRoot);
            }
        }

        /**
         * 2. 设置新的节点为父节点的子节点
         */
        setParent(newNode, newParent);
        if (newParent == null) {
            setRoot(newNode);
        } else if (valueOf(newNode) < valueOf(newParent)) {
            setLeft(newParent, newNode);
        } else {
            setRight(newParent, newNode);
        }

        setColor(newNode, RED);
        fixupInsert(newNode);
        this.size++;
    }

    public void remove(int value) {

    }

    public boolean contains(int value) {
        Node subRoot = root();
        Node targetNode = null;
        while (subRoot != null) {
            int subRootVal = valueOf(subRoot);
            if (subRootVal == value) {
                targetNode = subRoot;
                break;
            } else if (value < subRootVal) {
                subRoot = leftOf(subRoot);
            } else {
                subRoot = rightOf(subRoot);
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
        final int interval = 4;

        final Node root = root();
        measureLevel(root);
        resetLevel(root);
        final int locOffset = Math.abs(traversePreOrder(root, interval));

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
                Node child = leftOf(node);
                if (child != null) {
                    list.add(child);
                }
                child = rightOf(node);
                if (child != null) {
                    list.add(child);
                }
            }
            System.out.println(builder.toString());
        } while (!list.isEmpty());
    }

    private int traversePreOrder(Node node, int interval) {
        if (node == null) {
            return 0;
        }

        int minStartLoc = 0;
        final int width = widthOf(node);
        Node parent = parentOf(node);
        if (parent != null) {
            int parentWidth = widthOf(parent);
            int intervalTemp = interval * node.level;
            if (node == leftOf(parent)) {
                int startLoc = (parent.startLoc + (parentWidth / 2)) - (intervalTemp / 2 + width);
                node.startLoc = startLoc;
                minStartLoc = startLoc;
            } else {
                int startLoc = (parent.startLoc + (parentWidth / 2)) + (intervalTemp / 2);
                node.startLoc = startLoc;
            }
        }

        if (leftOf(node) != null) {
            int startLoc = traversePreOrder(leftOf(node), interval);
            minStartLoc = Math.min(startLoc, minStartLoc);
        }

        if (rightOf(node) != null) {
            traversePreOrder(rightOf(node), interval);
        }

        return minStartLoc;
    }

    private int widthOf(Node node) {
        return (node == null ? 0 : node.toString().length());
    }

    private int measureLevel(Node node) {
        if (node == null) {
            return 0;
        }

        Node left = leftOf(node);
        Node right = rightOf(node);
        int leftLevel = 0;
        int rightLevel = 0;
        if (left != null) {
            leftLevel = measureLevel(left);
        }

        if (right != null) {
            rightLevel = measureLevel(right);
        }

        int level = Math.max(leftLevel, rightLevel) + 1;
        node.level = level;
        return level;
    }

    private void resetLevel(Node node) {
        if (node == null) {
            return;
        }

        Node parent = parentOf(node);
        if (parent != null) {
            int level = parent.level - 1;
            if (level < 1) {
                level = 1;
            }

            node.level = level;
        }

        Node left = leftOf(node);
        if (left != null) {
            resetLevel(left);
        }

        Node right = rightOf(node);
        if (right != null) {
            resetLevel(right);
        }
    }

    public static class Node {

        private int value;

        Node parent;

        Node left;

        Node right;

        boolean color = RED;

        private int startLoc;

        private int level;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("[%1$s-%2$s]", value, (RED == color ? "R" : "B"));
        }
    }
}
