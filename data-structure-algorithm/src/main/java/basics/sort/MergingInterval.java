package basics.sort;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 例1：
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 例2：
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * 例3:
 * 输入:[[2,3],[4,5],[6,7],[8,9],[1,10]]
 * 输出:[[1,10]]
 * Created by Lei Guoting on 2019/4/25.
 */
public class MergingInterval {

    public static int[][] merge(int[][] intervals) {
        int length = intervals.length;
        int[][] merged = new int[length][];
        int mergedIndex = 0;
        for (int i = 0; i < length; i++) {
            int[] merge = mergeInner(intervals, i);
            if (merge.length != 0) {
                merged[mergedIndex++] = merge;
            }
        }

        int realLength = mergedIndex;
        if (realLength < length) {
            int[][] newArray = new int[realLength][];
            System.arraycopy(merged, 0, newArray, 0, realLength);
            merged = newArray;
        }
        sort(merged);
        return merged;
    }

    protected static int[] mergeInner(int[][] intervals, int startIndex) {
        int length = intervals.length;
        int[] start = intervals[startIndex];
        if (startIndex == length - 1) {
            return start;
        }
        if (start.length == 0) {
            return start;
        }

        for (int i = startIndex + 1; i < length; i++) {
            int[] next = intervals[i];
            int nextLength = next.length;
            if (nextLength == 0) {
                continue;
            }

            if (compare(start, next) == 0) {
                start[0] = Math.min(start[0], next[0]);
                start[1] = Math.max(start[1], next[1]);
                intervals[i] = new int[0];
            }
        }
        return start;
    }

    private static int compare(int[] left, int[] right) {
        if (left[1] < right[0]) {
            return -1;
        } else if (left[0] > right[1]) {
            return 1;
        } else {
            return 0;
        }
    }

    private static void sort(int[][] intervals) {
        int length = intervals.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                int[] first = intervals[j];
                int[] second = intervals[j + 1];
                if (compare(first, second) > 0) {
                    intervals[j] = second;
                    intervals[j + 1] = first;
                }
            }
        }
    }

    public static void main(String[] args) {
        /**
         *
         * [[1,4],[0,4]]
         * [[1,4],[0,0]]
         * [[4,5],[1,4],[0,1]]
         */

        int source[][] = new int[][]{
                new int[]{8, 10},
                new int[]{1, 3},
                new int[]{2, 6},
                new int[]{15, 18}
        };

        int source2[][] = new int[][]{
                new int[]{1, 4},
                new int[]{0, 0}
        };

        int source3[][] = new int[][]{
                new int[]{4, 5},
                new int[]{1, 4},
                new int[]{0, 1},
        };
        int[][] result = merge(source3);
        int length = result.length;
        for (int i = 0; i < length; i++) {
            int[] second = result[i];
            if (second != null) {
                int size = second.length;
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < size; j++) {
                    builder.append(second[j]).append(",");
                }
                System.out.println(builder.toString());
            }
        }

        int compared = compare(new int[]{1, 5}, new int[]{0, 1});
        System.out.println(String.format("compared: %s", Integer.toString(compared)));
    }
}
