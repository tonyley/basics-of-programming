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
 * Created by Lei Guoting on 2019/4/25.
 */
public class MergingInterval {

    public static int[][] merge(int[][] intervals) {
        int length = intervals.length;
        int[][] merged = new int[length][];
        int mergedIndex = 0;
        for (int i = 0; i < length; ) {
            int[] first = intervals[i];
            for (int j = i + 1; j < length; ) {
                int[] compared = intervals[j];
                if (first[1] > compared[0]) {
                    if (first[1] > compared[1]) {
                        j++;
                    } else {
                        merged[mergedIndex++] = new int[]{
                                first[0],
                                compared[1]
                        };
                        i = j + 1;
                        break;
                    }
                } else if (first[1] == compared[0]) {
                    merged[mergedIndex++] = new int[]{
                            first[0],
                            compared[1]
                    };
                    i = j + 1;
                    break;
                } else {
                    merged[mergedIndex++] = first;
                    i = j;
                    break;
                }
            }

            if (i == length - 1) {
                merged[mergedIndex++] = intervals[i];
                i++;
            }
        }

        int realLength = mergedIndex;
        if (realLength < length) {
            int[][] newArray = new int[realLength][];
            System.arraycopy(merged, 0, newArray, 0, realLength);
            merged = newArray;
        }

        return merged;
    }

    public static int [][] merge2(int[][] intervals){
        int length = intervals.length;
        int[][] merged = new int[length][];
        int mergedIndex = 0;
        for (int i = 0; i < length; ) {
            int[] start = intervals[i];
            for (int j = i + 1; j < length; ) {
                int[] next = intervals[j];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int source[][] = new int[][]{
                new int[]{1, 3},
                new int[]{2, 6},
                new int[]{8, 10},
                new int[]{15, 18}
        };
        int[][] result = merge(source);
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
    }
}
