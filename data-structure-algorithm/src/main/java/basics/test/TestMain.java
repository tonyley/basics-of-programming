package basics.test;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Created by Lei Guoting on 2019-12-21.
 */
public class TestMain {

    public static void main(String[] args) {
        /*
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(3);
        bst.add(8);
        bst.add(10);
        bst.add(2);
        bst.add(6);
        bst.add(1);
        bst.add(9);
        bst.add(50);

        System.out.println("==== END =====");
        */

        TestMain main = new TestMain();
        int result = main.compareString("10.0", "10");
        System.out.println(result);
    }

    /**
     * @return 0: left == right; 1: left > right; -1:left < right
     */
    private int compareString(String left, String right) {
        if (left == right) {
            return 0;
        }

        if (left == null) {
            return -1;
        } else if (right == null) {
            return -1;
        }

        if (isNumber(left) && isNumber(right)) {
            BigDecimal leftNum = new BigDecimal(left);
            BigDecimal rightNumb = new BigDecimal(right);
            return leftNum.compareTo(rightNumb);
        } else {
            return left.compareTo(right);
        }
    }

    private static final Pattern NUMBER_PATTERN = Pattern
            .compile("^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$");

    private boolean isNumber(String source) {
        return NUMBER_PATTERN.matcher(source).matches();
    }
}
