package Task3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static void main(String[] args) {
        String input1 = "app=edi_adapter_converter wingtipsTrace=8faeae6709355291 INFO  OrderCreateClient - action=EDIOrderSent originalFilename=Integration_test_Contract customerName=0005084863 orderUUID=d34149d8-88ab-4791-bb0a-46c96e034200 poNum=Test_TS5155079515 lineCount=3";
        String input2 = "app=edi_adapter_converter wingtipsTrace=8faeae6709355291 INFO  OrderCreateClient - action=EDIOrderSent originalFilename=Integration_test_Contract customerName=0005084863 orderUUID=d34149d8-88ab-4791-bb0a-46c96e034200 poNum=Test_TS5155079515 lineCount=3";
        String input3 = "test 2667843 (test_email@griddynamics.com) test 67483 some string";
        String input4 = "app=edi_adapter_splitter wingtipsTrace=225debfbe6e5fac7 poiFileName=Integration_test_Contract INFO  LogUtils - POI file name: [Integration_test_Contract], total number of orders successfully processed: [2]";

        boolean containsOrderUUID1 = containsOrderUUID(input1);
        System.out.println("Task 1 - Contains orderUUID: " + containsOrderUUID1);

        boolean containsOrderUUID2 = containsOrderUUID(input2);
        System.out.println("Task 1 - Contains orderUUID: " + containsOrderUUID2);

        String orderUUID = findOrderUUID(input2);
        System.out.println("Task 2 - orderUUID: " + orderUUID);

        String userEmail = findUserEmail(input3);
        System.out.println("Task 3 - User email: " + userEmail);

        int orderCount = findOrderCount(input4);
        System.out.println("Task 4 - Order count: " + orderCount);
    }

    public static boolean containsOrderUUID(String input) {
        Pattern pattern = Pattern.compile("\\borderUUID=[a-f0-9]{8}-([a-f0-9]{4}-){3}[a-f0-9]{12}\\b");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static String findOrderUUID(String input) {
        Pattern pattern = Pattern.compile("orderUUID=([a-f0-9]{8}-([a-f0-9]{4}-){3}[a-f0-9]{12})");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static String findUserEmail(String input) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public static int findOrderCount(String input) {
        Pattern pattern = Pattern.compile("total number of orders successfully processed: \\[(\\d+)]");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 0;
    }
}
