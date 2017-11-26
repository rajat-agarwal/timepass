package mi;

import java.util.*;

public class Validate_Schema_Atlassian {
    private static boolean isValid(String field) {
        for (int i = 0; i < field.length(); i++) {

            //Check if the character is a valid character
            if (field.charAt(i) < ' ' || field.charAt(i) > '~')
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

    private static boolean validStartAndEnd(String[] tokens) {
        if (tokens[0].length() != 0 || tokens[tokens.length - 1].length() != 0) {
            return false;
        }
        return true;
    }

    private static boolean validHeader(String[] tokens) {
        if (!validStartAndEnd(tokens))
            return false;

        Set<String> set = new HashSet<>(tokens.length);
        //Check for unique header names
        for (int i = 1; i < tokens.length - 1; i++) {
            if (tokens[i].length() == 0 || !isValid(tokens[i]) || set.contains(tokens[i])) {
                return false;
            }
            set.add(tokens[i]);
        }
        return true;
    }

    private static String makeReturnMessage(int a, int b, int c, String s) {
        StringBuilder sb = new StringBuilder();
        return sb.append(a).append(":").append(b).append(":").append(c).append(":").append(s).toString();
    }

    private static String validate(String s) {
        String errorMessage = "0:0:0:format_error";

        String[] input = s.split("~n", -1);
        if (input.length < 1)
            return errorMessage;

        //parse header
        String[] htokens = input[0].split("\\|", -1);
        if (!validHeader(htokens)) {
            return errorMessage;
        }

        //variables to track metrics
        int numRecords = input.length - 2; //ignoring the header and last newline character
        int numFields = htokens.length - 2; //ignoring the start and end empty string
        int numEmptyValues = 0;
        String lastFieldName = htokens[htokens.length - 2];
        String lastDynamicFieldname = lastFieldName;

        //parse input records now
        int recordNo = 1;
        for (int i = 1; i < input.length - 1; i++) {
            String[] rfields = input[i].split("\\|", -1);
            if (!validStartAndEnd(rfields))
                return errorMessage;

            for (int l = 1; l < rfields.length - 1; l++) {
                if (rfields[l].length() == 0) {
                    numEmptyValues++;
                    continue;
                }
                if (!isValid(rfields[l]))
                    return errorMessage;
            }

            numEmptyValues += numFields > (rfields.length - 2) ? numFields - (rfields.length - 2) : 0;

            if (rfields.length - 2 > numFields) {
                lastDynamicFieldname = lastFieldName + "_" + recordNo;
            }

            numFields = Math.max(numFields, rfields.length - 2);
            recordNo++;
        }

        return makeReturnMessage(numRecords, numFields, numEmptyValues, lastDynamicFieldname);
    }

    public static void main(String[] args) {
        String input = "|name|address|~n|Patrick|patrick@test.com|pat@test.com|~n|Annie|annie@test.com|~n|Zoe|~n";
        System.out.println(validate(input));
    }
}
