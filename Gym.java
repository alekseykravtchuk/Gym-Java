
import java.util.Arrays;
import java.util.Scanner;

public class Gym {
    public static void main(String[] args) {
        while (true) {
        int [] nWeights = inputWeights();
        System.out.println(countMaxWeight(nWeights));
        }
    }

    private static int[] inputWeights() {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            String [] sWeights = line.split(",| ");
            int [] nWeights = new int[sWeights.length];
            try {
                for (int i = 0; i < sWeights.length; i++) {
                    int n = Integer.parseInt(sWeights[i]);
                    if (n > 20) {
                        System.out.println("Вес блина - " + n + " фунтов. Больше ограничения в 20 фунтов");
                        break;
                    }
                    nWeights[i] = Integer.parseInt(sWeights[i].replaceAll("\\D", ""));
                    if (i > 1000) {
                        System.out.println("В наборе может присутствовать от 1 до 1000 блинов. Повторите ввод.");
                        break;
                    }
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода");
            }
        Arrays.sort(nWeights);
        return nWeights;
    }

    private static int countMaxWeight (int[] weights0) {
        boolean equal = false;
        int offset = 0;
        int leftWeight = 0, rightWeight = 0;
        while (!equal) {
            int[] weights = weights0;
            leftWeight = 0; rightWeight = 0;
            int sum = 0;
            for (int i : weights)  sum += i;

            int maxWeight = sum;
            if (sum % 2 != 0) maxWeight = sum - 1;
            for (int i = weights.length - (offset + 1); i >= 0; i--) {
                leftWeight += weights[i];
                if (leftWeight > maxWeight / 2)
                    leftWeight -= weights[i];
                else weights[i] = 0;
            }
            int sumR = 0;
            for (int i : weights) sumR += i;

            rightWeight = sumR;
            int line = 0;
            while (!equal) {
                if (leftWeight == rightWeight) {
                    equal = true;
                } else {
                    rightWeight -= weights[line];
                    if (line < weights.length - 1) line++;
                }
                if (line == weights.length - 1) {
                    offset++;
                    break;}
            }
            if (offset > weights.length) break;
        }
        if (leftWeight == rightWeight) {
            return leftWeight + rightWeight;
        }
        else return 0;
    }
}
