package Util;

//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;

public class Utility {

    public static String getRandomName() {
        String[] firstNames = {"Alice", "Bob", "Charlie", "David", "Emily", "Frank", "Grace", "Henry", "Isabella", "Jack", "Soka", "Boka"};
        Random random = new Random();
        int index = random.nextInt(firstNames.length);
        return firstNames[index];

    }

    public static String getRandomFirstName() {
        String[] firstNames = {"Alice", "Bob", "Charlie", "David", "Emily", "Frank", "Grace", "Henry", "Isabella", "Jack"};
        Random random = new Random();
        int index = random.nextInt(firstNames.length);
        return firstNames[index];

    }

    public static String getRandomLastName() {
        String[] lastNames = {"Alice", "Bob", "Charlie", "David", "Emily", "Frank", "Grace", "Henry", "Isabella", "Jack"};
        Random random = new Random();
        int index = random.nextInt(lastNames.length);
        return lastNames[index];

    }

    //generate complex password with 8 characters
    public static String generatePassword() {
        final String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String lowerLetters = capitalLetters.toLowerCase();
        final String specialCharacters = "!@#$%^&*()-+";
        final String allCharacters = capitalLetters + lowerLetters + specialCharacters;
        final int passwordLength = 8;

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        int charCategories = 3; // Consider at least one character from each category

        // Ensure at least one character from each category
        for (int i = 0; i < charCategories; i++) {
            int charGroupIndex = random.nextInt(charCategories);
            String charGroup;
            switch (charGroupIndex) {
                case 0:
                    charGroup = capitalLetters;
                    break;
                case 1:
                    charGroup = lowerLetters;
                    break;
                case 2:
                    charGroup = specialCharacters;
                    break;
                default:
                    throw new IllegalStateException("Unexpected character group index");
            }
            int charIndex = random.nextInt(charGroup.length());
            password.append(charGroup.charAt(charIndex));
        }

        // Fill remaining characters with any character
        for (int i = password.length(); i < passwordLength; i++) {
            int charIndex = random.nextInt(allCharacters.length());
            password.append(allCharacters.charAt(charIndex));
        }

        // Shuffle the characters for better randomness
        for (int i = 0; i < password.length(); i++) {
            int targetIndex = random.nextInt(password.length());
            char temp = password.charAt(i);
            password.setCharAt(i, password.charAt(targetIndex));
            password.setCharAt(targetIndex, temp);
        }

        return password.toString();
    }

    // generate random email with gmail domain only
    public static String generateRandomGmailEmail() {
        int usernameLength = 8; // Adjust username length as needed
        StringBuilder usernameBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < usernameLength; i++) {
            int charType = random.nextInt(3);
            if (charType == 0) {
                usernameBuilder.append((char) (random.nextInt(26) + 'a')); // Lowercase letter
            } else if (charType == 1) {
                usernameBuilder.append((char) (random.nextInt(26) + 'A')); // Uppercase letter
            } else {
                usernameBuilder.append((char) (random.nextInt(10) + '0')); // Number
            }
        }

        String username = usernameBuilder.toString();
        String domain = "gmail.com"; // Guaranteed Gmail domain
        return username + "@" + domain;
    }

    //generate uniqe number
    public static List<Integer> generateUniqueRandomNumbers(int count) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    //generate random integer
    public static Integer removeRandomInteger(List<Integer> list) {
        // Check if the list is empty
        if (list.isEmpty()) {
            throw new IllegalArgumentException("The list is empty.");
        }

        // Generate a random index within the bounds of the list
        Random random = new Random();
        int index = random.nextInt(list.size());

        // Remove the element at the random index
        return list.remove(index);
    }


    // parse float values
    public static float parsePriceFromString(String priceString) {
        if (priceString == null || priceString.isEmpty()) {
            throw new IllegalArgumentException("Price string cannot be null or empty");
        }

        // Remove leading and trailing whitespaces (optional)
        priceString = priceString.trim();

        // Check if the string starts with a dollar sign ($)
        if (!priceString.startsWith("$")) {
            throw new IllegalArgumentException("Price string must start with a dollar sign ($)");
        }

        // Extract the number part (everything after the dollar sign)
        String numberString = priceString.substring(1);

        // Parse the number string to a float
        try {
            return Float.parseFloat(numberString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid price format. Please provide a valid number after the dollar sign ($)", e);
        }
    }

    // read from json
    public static String getSingleJsonData(String jsonFilePath, String jsonField) throws IOException, ParseException, IOException {
        JSONParser jsonParser = new JSONParser();

        FileReader fileReader = new FileReader(jsonFilePath);
        Object obj = jsonParser.parse(fileReader);

        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject.get(jsonField).toString();
    }

    // read from excel
    public static String getExcelData(int RowNum, int ColNum, String SheetName) {
        XSSFWorkbook workBook;
        XSSFSheet sheet;
        String projectPath = System.getProperty("user.dir");
        String cellData = null;
        try {
            workBook = new XSSFWorkbook(projectPath + "/src/test/resources/test_data/data.xlsx");
            sheet = workBook.getSheet(SheetName);
            cellData = sheet.getRow(RowNum).getCell(ColNum).getStringCellValue();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        return cellData;
    }

    //read from json multiple items
    public static String[] readJson(String jsonFilePath, String jsonFieldArray, String field) throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();

        FileReader fileReader = new FileReader(jsonFilePath);
        Object obj = jsonParser.parse(fileReader);

        JSONObject jsonObject = (JSONObject) obj;
        JSONArray array = (JSONArray) jsonObject.get(jsonFieldArray);

        String[] arr = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            JSONObject users = (JSONObject) array.get(i);
            String fieldData = (String) users.get(field);

            arr[i] = fieldData;
        }
        return arr;


    }

}
