/*
 * Date: April 1st - April 8th, 2021
 * Name: Kyle Chong & Angus Whitehead
 * Teacher: Mr. Ho
 * Description: Customer System Assignment
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Random;
// More packages may be imported in the space below

class CustomerTest{
    private static int numSum;
    private static String cardNumber;
    private static String fName = "";
    private static String lName = "";
    private static String userPostal = "";

    public static void main(String[] args) throws IOException{
        // Please do not edit any of these variables
        Scanner reader = new Scanner(System.in);
        String userInput, enterCustomerOption, generateCustomerOption, exitCondition;
        enterCustomerOption = "1";
        generateCustomerOption = "2";
        exitCondition = "9";

        // More variables for the main may be declared in the space below

        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu
            if (userInput.equals(enterCustomerOption)){
                // Only the line below may be editted based on the parameter list and how you design the method return
		        // Any necessary variables may be added to this if section, but nowhere else in the code
                enterCustomerInfo(reader);
            }
            else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you design the method return
                generateCustomerDataFile();
            }
            else if (userInput.equals(exitCondition)) {
                
            }
            else{
                System.out.println("Please type in a valid option (A number from 1-9)");
            }

        } while (!userInput.equals(exitCondition));         // Exits once the user types 
        
        reader.close();
        System.out.println("Program Terminated");
    }
    public static void printMenu(){
        System.out.println("Customer and Sales System\n"
        .concat("1. Enter Customer Information\n")
        .concat("2. Generate Customer data file\n")
        .concat("9. Quit\n")
        .concat("Enter menu option (1-9)\n")
        );
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void enterCustomerInfo(Scanner reader) throws IOException{
        boolean invalid = true;
        System.out.println("Please Enter Your First Name: ");
        fName = reader.nextLine();
        System.out.println("Please Enter Your Last Name: ");
        lName = reader.nextLine();
        do {
            System.out.println("Please Enter Your Postal Code (Must be longer than or equal to 3 digits): ");
            userPostal = reader.nextLine().toUpperCase().trim();
            if (userPostal.length() >= 3){
                userPostal = userPostal.substring(0, 3);
                invalid = validatePostalCode(invalid);
            }
            else {
                System.out.println("Invalid Postal Code. Too Short");
            }
        } while (invalid);
        invalid = true;
        do {
            System.out.println("Please Enter Your Credit Card (Must be longer than or equal to 9 digits): \n");
            cardNumber = reader.nextLine().trim();
            numSum = 0;
            if (cardNumber.length() >= 9){
                invalid = validateCreditCard(invalid);
            }
            else {
                System.out.println("Invalid Credit Card. Too Short");
            }
        } while (invalid);
    }
    public static boolean validatePostalCode(boolean invalid) throws IOException{
        Scanner fileReader;
        String filePath = "postal_codes.csv";
        boolean found = false;
        String postalCode = ""; String placeName = ""; String province = ""; String latitude = ""; String longitude = "";
        try {
            fileReader = new Scanner(new File(filePath));
            fileReader.useDelimiter("[|\n]");

            while (fileReader.hasNext() && !found){
                postalCode = fileReader.next();
                placeName = fileReader.next();
                province = fileReader.next();
                latitude = fileReader.next();
                longitude = fileReader.next();
                //System.out.println(fileReader.hasNext());
                if (postalCode.equals(userPostal)){
                    found = true;
                }
            }
            if (found){
                System.out.println("Postal Code: " + postalCode + " Place: " + placeName + " Province: " + province + " Latitude: " + latitude + " Longitude: " + longitude);
                invalid = false; 
            }
            else {
                System.out.println("Postal Code Not Found");
            }
        }
        catch (Exception e){
            System.out.println("Invalid Input");
        }
        return invalid;
    }
    public static boolean validateCreditCard(boolean invalid){
        String reverseCardNum = reverse(cardNumber);
        int numOfDigits = reverseCardNum.length();
        boolean evenDigit = false; 
        for (int i = 0; i <= numOfDigits - 1; i++) {
            int n = reverseCardNum.charAt(i) - '0';
            if (evenDigit == true){
                n = n*2;
            }
            numSum += n / 10;
            numSum += n % 10;
            evenDigit = !evenDigit;            
        }
        if (numSum % 10 == 0) {
            System.out.println(cardNumber + " is a valid Credit Card");
            invalid = false;
        }
        else {
            System.out.println(cardNumber + " is not a valid Credit Card");
        }    
        return invalid;
    }
	
    public static void generatIDNumber(){
      Random rand = new Random();
	    int randNum = rand.nextInt(10000);
	    System.out.println("Your ID Number is " + randNum);  
    }
	
    public static void generateCustomerDataFile(){
	     while(!invalid){
        System.out.println("Hello " + fName + lName + "from " + province + " Canada.");
        System.out.println("Credit Card Number: " + cardNumber);
        System.out.println("Postal Code: " + postalCode);
	System.out.println("Your ID Number is " + randNum);	     

      }
    }
    
    public static String reverse(String cardNumber){
        int len = cardNumber.length();
        String result = "";
        for (int i = len - 1; i >= 0; i--) {
            result = result + cardNumber.charAt(i);
        }
        return result;
    }
}
