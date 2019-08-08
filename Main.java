import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {

    private BigDecimal minNumber = BigDecimal.valueOf(Integer.MAX_VALUE);
    private BigDecimal maxNumber = BigDecimal.valueOf(Integer.MIN_VALUE);
    private BigDecimal avgNumber = BigDecimal.ZERO;
    private int count = 0;


    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("For exit enter '?'");
            System.out.print("Enter the number: ");
            try {
                String inputString = bufferedReader.readLine();
                if (handleExitKey(inputString)) {
                    System.out.println("==========================");
                    printInfo();
                    break;
                }
                afterInputNumber(inputString);
                printInfo();
            } catch (NumberFormatException e) {
                System.out.println("number is not parsed. Correct format: 55.66");
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private boolean handleExitKey(String input) {
        return "?".equals(input);
    }

    private void afterInputNumber(String inputString) {
        BigDecimal input = new BigDecimal(inputString);
        if (input.compareTo(minNumber) < 0) {
            minNumber = input;
        }
        if (input.compareTo(maxNumber) > 0) {
            maxNumber = input;
        }
        averageManipulation(input);

    }

    private void averageManipulation(BigDecimal input) {
        BigDecimal tmp = input.subtract(avgNumber);
        count++;
        tmp = tmp.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP);
        avgNumber = avgNumber.add(tmp);
    }

    private void printInfo(){
        System.out.println("min: " + minNumber);
        System.out.println("max: " + maxNumber);
        System.out.println("avg: " + avgNumber);
    }
}
