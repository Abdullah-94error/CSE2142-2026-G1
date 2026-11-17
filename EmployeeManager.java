

import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Read employee data from file
    private static String readEmployeeFile() throws IOException {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream(Constants.FILE_NAME)
            )
        );

        String line = reader.readLine();
        reader.close();

        return line;
    }

    // Write employee data to file
    private static void writeEmployeeFile(String content, boolean append)
            throws IOException {

        BufferedWriter writer = new BufferedWriter(
            new FileWriter(Constants.FILE_NAME, append)
        );

        writer.write(content);
        writer.close();
    }

    public static void main(String[] args) {

        // Check arguments
        if (args.length != 1) {
            System.out.println(Constants.INVALID_ARGUMENT_MESSAGE);
            return;
        }

        if (args[0].equals("l")) {
            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String line = readEmployeeFile();
                String employees[] = line.split(Constants.COMMA);

                for (String employee : employees) {
                    System.out.println(employee);
                }

            } catch (Exception exception) {
            }

            System.out.println(Constants.DATA_LOADED_MESSAGE);

        } else if (args[0].equals("s")) {
            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String line = readEmployeeFile();
                System.out.println(line);

                String employees[] = line.split(Constants.COMMA);
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);

                System.out.println(employees[randomIndex]);

            } catch (Exception exception) {
            }

            System.out.println(Constants.DATA_LOADED_MESSAGE);

        } else if (args[0].contains("+")) {
            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String name = args[0].substring(1);
                writeEmployeeFile(Constants.COMMA_SPACE + name, true);

            } catch (Exception exception) {
            }

            System.out.println(Constants.DATA_LOADED_MESSAGE);

        } else if (args[0].contains("?")) {
            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String line = readEmployeeFile();
                String employees[] = line.split(Constants.COMMA);
                boolean found = false;
                String searchName = args[0].substring(1);

                for (int i = 0; i < employees.length && !found; i++) {
                    if (employees[i].equals(searchName)) {
                        System.out.println(Constants.EMPLOYEE_FOUND_MESSAGE);
                        found = true;
                    }
                }

            } catch (Exception exception) {
            }

            System.out.println(Constants.DATA_LOADED_MESSAGE);

        } else if (args[0].contains("c")) {
            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String line = readEmployeeFile();
                char[] characters = line.toCharArray();
                boolean isInWord = false;
                int wordCount = 0;

                for (char character : characters) {
                    if (character == Constants.SPACE.charAt(0)) {
                        if (!isInWord) {
                            wordCount++;
                            isInWord = true;
                        } else {
                            isInWord = false;
                        }
                    }
                }

                System.out.println(
                    wordCount + " word(s) found " + characters.length
                );

            } catch (Exception exception) {
            }

            System.out.println(Constants.DATA_LOADED_MESSAGE);

        } else if (args[0].contains("u")) {
            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String line = readEmployeeFile();
                String employees[] = line.split(Constants.COMMA);
                String name = args[0].substring(1);

                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(name)) {
                        employees[i] = Constants.UPDATED_VALUE;
                    }
                }

                writeEmployeeFile(String.join(Constants.COMMA, employees), false);

            } catch (Exception exception) {
            }

            System.out.println(Constants.DATA_UPDATED_MESSAGE);

        } else if (args[0].contains("d")) {
            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String line = readEmployeeFile();
                String employees[] = line.split(Constants.COMMA);
                String name = args[0].substring(1);

                List<String> employeeList = new ArrayList<>(
                    Arrays.asList(employees)
                );

                employeeList.remove(name);

                writeEmployeeFile(
                    String.join(Constants.COMMA, employeeList), false
                );

            } catch (Exception exception) {
            }

            System.out.println(Constants.DATA_DELETED_MESSAGE);
        }
    }
}
