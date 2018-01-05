package mi;

import java.util.*;

public class Validate_Schema_Atlassian {
    private static boolean isValidChar(char c) {
        return c >= ' ' && c <= '~';
    }

    private static boolean isValidCharset(String field) {
        for (int i = 0; i < field.length(); i++) {

            //Check if the character is a valid character
            if (!isValidChar(field.charAt(i)))
                return false;

            //Check if the sequence is a valid escape sequence
            if (field.charAt(i) == '~') {
                //if it is the last character then it is the ~|
                //Check for ~~
                if (++i < field.length() && field.charAt(i) != '~') {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValidStartAndEnd(String s) {
        // check for proper start and end of the header
        return s != null && s.charAt(0) == '|' && s.charAt(s.length() - 1) == '|';
    }

    private static String[] parseFieldNames(String header) {
        // check for proper start and end of the header
        if (!isValidStartAndEnd(header))
            return null;

        String[] ftokens = header.split("\\|", -1);
        Set<String> set = new LinkedHashSet<>(ftokens.length);
        //Check for unique header names
        for (int i = 1; i < ftokens.length - 1; i++) {
            if (ftokens[i].length() == 0 || !isValidCharset(ftokens[i]) || set.contains(ftokens[i])) {
                return null;
            }
            set.add(ftokens[i]);
        }
        return set.toArray(new String[0]);
    }

    private static String makeReturnMessage(int a, int b, int c, String s) {
        StringBuilder sb = new StringBuilder();
        return sb.append(a).append(":").append(b).append(":").append(c).append(":").append(s).toString();
    }

    private static String validate(String s) {
        String errorMessage = "0:0:0:format_error";

        String[] records = s.split("~n", -1);
        if (records.length < 1)
            return errorMessage;

        //parse header
        String[] fieldNames = parseFieldNames(records[0]);
        if (fieldNames == null) {
            return errorMessage;
        }

        //variables to track metrics
        int numOfFieldNames = fieldNames.length;
        int numEmptyValues = 0;
        String lastFieldName = fieldNames[fieldNames.length - 1];
        String lastDynamicFieldname = lastFieldName; //Last field name formed for more fields in record than the header fields

        //parse input records now
        int recordNo = 0;
        while (++recordNo < records.length - 1) {
            if (!isValidStartAndEnd(records[recordNo]))
                return errorMessage;

            String[] rfields = records[recordNo].split("\\|", -1);
            int numOfRecordFields = rfields.length - 2; // ignore the null token before first '|' and after last '|'
            for (int i = 1; i <= numOfRecordFields; i++) {
                if (rfields[i].length() == 0) {
                    numEmptyValues++;
                    continue;
                }
                if (!isValidCharset(rfields[i]))
                    return errorMessage;
            }

            numEmptyValues += numOfFieldNames > numOfRecordFields ? numOfFieldNames - numOfRecordFields : 0;

            if (numOfRecordFields > numOfFieldNames) {
                lastDynamicFieldname = lastFieldName + "_" + recordNo;
            }

            numOfFieldNames = Math.max(numOfFieldNames, numOfRecordFields);
        }

        return makeReturnMessage(recordNo - 1, numOfFieldNames, numEmptyValues, lastDynamicFieldname);
    }

    public static void main(String[] args) {
        String input = "|name|address|~n|Patrick|patrick@test.com|pat@test.com|~n|Annie|annie@test.com|~n|Zoe|~n";
        System.out.println(validate(input));

        Random random = new Random();
        for (int k=0;k<100000;k++) {
            int size = random.nextInt(1000000);
            Set<Integer> set = new LinkedHashSet<>(size);
            for (int i = 0; i < size; i++)
                set.add(i);
            Integer[] ret = set.toArray(new Integer[0]);
            for (int i = 0; i < size; i++) {
                if (ret[i] != i) {
                    System.out.println("Panic ");
                    for (int e : ret) {
                        System.out.print(e + " ");
                    }
                    return;
                }
            }
        }
    }
}
