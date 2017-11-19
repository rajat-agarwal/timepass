package mi;
/**
 * Created by rajat.agarwal on 24/03/17.
 */
public class Document_Storage_On_Machines {
    public static void findServerLocation(int[] serverRules, int documentNum) {
        for (int i = 0; i < serverRules.length; i++) {
            if (serverRules[i] == 0) { //Ideally it should be last server but checking it always to avoid handling any specific interview tweak of the question.
                System.out.println("document stored on server " + (i + 1)); //Server starts with S1,S2,S3.. and so on and array index starts from 0,1,2 and so on
                break;
            }
            if (documentNum % serverRules[i] == 0) {
                System.out.println("document stored on server " + (i + 1)); //Server starts with S1,S2,S3.. and so on and array index starts from 0,1,2 and so on
                break;
            } else {
                documentNum -= documentNum / serverRules[i];
            }
        }
    }

    public static void main(String[] args) {
        //Assuming input document sequence starts from 1,2,3,4.. and go on.
        int[] serverRulesMapping = {2, 3, 4, 5, 6, 7, 8}; //there will be a mapping for each server here. i(th) location corresponds to rule of i(th) server. 0 denotes accept all.
        findServerLocation(serverRulesMapping, 9); //Give server location for document number 5
    }
}