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

class ColabCustomerSystem{
    //Private Static Variables (For use outside and inside methods)
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

        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu
            if (userInput.equals(enterCustomerOption)){
                enterCustomerInfo(reader);
            }
            else if (userInput.equals(generateCustomerOption)) {
                generateCustomerDataFile();
            }
            else if (userInput.equals(exitCondition)) {
                System.out.println("You have exited the program");
            }
            else{
                System.out.println("Please type in a valid option (A number from 1-9)");
            }

        } while (!userInput.equals(exitCondition));         // Exits once the user types 
        
        reader.close();
        System.out.println("Program Terminated");
    }
    //Menu Method
    public static void printMenu(){
        System.out.println("Customer and Sales System\n"
        .concat("1. Enter Customer Information\n")
        .concat("2. Generate Customer data file\n")
        .concat("9. Quit\n")
        .concat("Enter menu option (1-9)\n")
        );
    }

    //Enter Customer Information Method
    public static void enterCustomerInfo(Scanner reader) throws IOException{
        boolean invalid = true;
        //Prompt User input for First Name and Last Name
        System.out.println("Please Enter Your First Name: ");
        fName = reader.nextLine();
        System.out.println("Please Enter Your Last Name: ");
        lName = reader.nextLine();

        //Do-while loop. Keeps looping until valid postal code is found in the CSV file
        do {
            System.out.println("Please Enter Your Postal Code (Must be longer than or equal to 3 digits): ");
            userPostal = reader.nextLine().toUpperCase().trim();
            //Checks for the length of the postal code, if it's less than 3 digits, code is invalid.
            if (userPostal.length() >= 3){
                userPostal = userPostal.substring(0, 3);
                //Calls the validatePostalCode method. Returns boolean invalid. If it returns false, loop ends.
                invalid = validatePostalCode(invalid);
            }
            //If the postal code is too short
            else {
                System.out.println("Invalid Postal Code. Too Short");
            }
        } while (invalid);

        //Do-while loop. Keeps looping until valid credit card passes Luhn Algorithm
        invalid = true;
        do {
            System.out.println("Please Enter Your Credit Card (Must be longer than or equal to 9 digits): \n");
            cardNumber = reader.nextLine().trim();
            numSum = 0;
            //Checks for length of credit card. If it's less than 9 digits, credit card is invalid
            if (cardNumber.length() >= 9){
                //Calls the validateCreditCard method. Returns boolean invalid. If it returns false, loop ends.
                invalid = validateCreditCard(invalid);
            }
            //If the credit card is too short
            else {
                System.out.println("Invalid Credit Card. Too Short");
            }
        } while (invalid);
    }

    //Validate Postal Code Method. Searches CSV file
    public static boolean validatePostalCode(boolean invalid) throws IOException{
        Scanner fileReader;
        String filePath = "postal_codes.csv";
        boolean found = false;
        String postalCode = ""; String placeName = ""; String province = ""; String latitude = ""; String longitude = "";
        try {
            fileReader = new Scanner(new File(filePath));   //Defines scanner and what file to read from
            fileReader.useDelimiter("[|\n]");   //Defines the delimiters

            while (fileReader.hasNext() && !found){ //As long as the file reader can find a next value and a postal code hasn't matched, it will keep going.
                postalCode = fileReader.next();
                placeName = fileReader.next();
                province = fileReader.next();
                latitude = fileReader.next();
                longitude = fileReader.next();
                if (postalCode.equals(userPostal)){ //If a postal code is found, it will stop searching.
                    found = true;
                }
            }
            //If a matching postal code is found, it will print out all the information of that postal code
            if (found){
                System.out.println("Postal Code: " + postalCode + " Place: " + placeName + " Province: " + province + " Latitude: " + latitude + " Longitude: " + longitude);
                invalid = false; 
            }
            //If a postal code does not match any in the file
            else {
                System.out.println("Postal Code Not Found");
            }
        }
        //Catches any invalid inputs or errors
        catch (Exception e){
            System.out.println("Invalid Input");
        }
        //Returns the boolean value invalid. Used to stop the for loop
        return invalid;
    }

    //Validate Credit Card Method. Luhn Algorithm
    public static boolean validateCreditCard(boolean invalid){    
        String reverseCardNum = reverse(cardNumber);    //Calls the reverse method to reverse the order of 'cardNumber'
        int numOfDigits = reverseCardNum.length();  //Takes the length of the card number
        boolean evenDigit = false; 
        for (int i = 0; i <= numOfDigits - 1; i++) {    //Goes through the digits/indexes one by one 
            int n = reverseCardNum.charAt(i) - '0';
            if (evenDigit == true){ //If its an even digit/every other digit ie. digit # 2,4,6,8
                n = n*2;
            }
            numSum += n / 10;
            numSum += n % 10;
            evenDigit = !evenDigit; //Swaps the boolean value. True becomes False and False becomes True.
        }
        //If the sum % 10 is = 0, its valid
        if (numSum % 10 == 0) {
            System.out.println(cardNumber + " is a valid Credit Card");
            invalid = false;
        }
        //Else it is not a valid credit card
        else {
            System.out.println(cardNumber + " is not a valid Credit Card");
        }    
        //Returns the boolean value invalid. Used to stop the for loop
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
    
    //Reverse Method. Reverse a string
    public static String reverse(String cardNumber){
        int len = cardNumber.length();
        String result = "";
        for (int i = len - 1; i >= 0; i--) {    //Goes through the digits/indexes one by one BACKWARDS
            result = result + cardNumber.charAt(i);
        }
        return result;
    }
}
