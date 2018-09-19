package basics.dsa;

/**
 * Created by Gary Lei on 2018/9/13.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hi, algorithm");
        testRedBlackTree();
    }

    private static void testRedBlackTree() {
        RedBlackTree tree = new RedBlackTree();
        tree.add(5);
        tree.add(4);
        tree.add(2);

        tree.print();
        System.out.println("------");
        tree.add(1);
        tree.add(11);
        tree.add(6);
        tree.add(3);
        tree.print();
        System.out.println("------");

        tree.add(12);
        //tree.add(32);
        tree.print();
        System.out.println("------");

        tree.add(9);
        tree.print();
    }

    private static void testIf() {
        int index = 2;
        while (index != 0) {
            if (index > 0) {
                System.out.println(String.format("index[%s] > 0", index));
                index = -1;
            } else if (index < 0) {
                System.out.println(String.format("index[%s] < 0", index));
                index = 0;
            } else {
                System.out.println(String.format("index[%s] == 0", index));
            }
            System.out.println("-------");
        }
    }
}
