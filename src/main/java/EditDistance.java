import java.io.FileWriter;
import java.io.IOException;

public class EditDistance {
    public int minDistance(String word1, String word2) {
        int l1 = word1.length();
        int l2 = word2.length();
        //System.out.println(l2);

        int[][] dp = new int[l1 + 1][l2 + 1];

        for (int i = 0; i <= l1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= l2; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                int insert = dp[i][j - 1] + 1;
                int delete = dp[i - 1][j] + 1;
                int replace = dp[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1);

                dp[i][j] = Math.min(Math.min(insert, delete), replace);
            }
        }

        try {
            FileWriter writer = new FileWriter("edit_distance.txt");
            for (int i = 0; i <= l1; i++) {
                for (int j = 0; j <= l2; j++) {
                    writer.write(dp[i][j] + " ");
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dp[l1][l2];
    }

    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();
        String word1 = "The quick brown fox jumps over the lazy dog.";
        String word2 = "The sun rises in the east and sets in the west.";
        int distance = editDistance.minDistance(word1, word2);
        System.out.println("Edit Distance: " + distance);
    }
}
