package Task3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static void main(String[] args) {
        // Sample input strings
        String input1 = "app=edi_adapter_converter wingtipsTrace=8faeae6709355291 INFO  OrderCreateClient - action=EDIOrderSent originalFilename=Integration_test_Contract customerName=0005084863 orderUUID=d34149d8-88ab-4791-bb0a-46c96e034200 poNum=Test_TS5155079515 lineCount=3";
        String input2 = "app=edi_adapter_converter wingtipsTrace=8faeae6709355291 INFO  OrderCreateClient - action=EDIOrderSent originalFilename=Integration_test_Contract customerName=0005084863 orderUUID=d34149d8-88ab-4791-bb0a-46c96e034200 poNum=Test_TS5155079515 lineCount=3";
        String input3 = "test 2667843 (test_email@griddynamics.com) test 67483 some string";
        String input4 = "app=edi_adapter_splitter wingtipsTrace=225debfbe6e5fac7 poiFileName=Integration_test_Contract INFO  LogUtils - POI file name: [Integration_test_Contract], total number of orders successfully processed: [2]";

        // 1. Find and return true or false if String contains orderUUID
        boolean containsOrderUUID1 = containsOrderUUID(input1);
        System.out.println("Task 1 - Contains orderUUID: " + containsOrderUUID1);

        boolean containsOrderUUID2 = containsOrderUUID(input2);
        System.out.println("Task 1 - Contains orderUUID: " + containsOrderUUID2);

        // 2. Find and return orderUUID substring from string
        String orderUUID = findOrderUUID(input2);
        System.out.println("Task 2 - orderUUID: " + orderUUID);

        // 3. Find and return user email from string
        String userEmail = findUserEmail(input3);
        System.out.println("Task 3 - User email: " + userEmail);

        // 4. Find and return how many orders were created from this string
        int orderCount = findOrderCount(input4);
        System.out.println("Task 4 - Order count: " + orderCount);
    }

    // 1. Find and return true or false if String contains orderUUID
    public static boolean containsOrderUUID(String input) {
        return input.matches(".*\\borderUUID=[a-f0-9]{8}-([a-f0-9]{4}-){3}[a-f0-9]{12}\\b.*");
    }

    // 2. Find and return orderUUID substring from string
    public static String findOrderUUID(String input) {
        String orderUUID = null;
        Pattern pattern = Pattern.compile("orderUUID=([a-f0-9]{8}-([a-f0-9]{4}-){3}[a-f0-9]{12})");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            orderUUID = matcher.group(1);
        }
        return orderUUID;
    }

    // 3. Find and return user email from string
    public static String findUserEmail(String input) {
        String email = null;
        Pattern pattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            email = matcher.group();
        }
        return email;
    }

    // 4. Find and return how many orders were created from this string
    public static int findOrderCount(String input) {
        int orderCount = 0;
        Pattern pattern = Pattern.compile("total number of orders successfully processed: \\[(\\d+)]");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            orderCount = Integer.parseInt(matcher.group(1));
        }
        return orderCount;
    }
}
