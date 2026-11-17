
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
                for (String employee : readEmployeeFile().split(Constants.COMMA)) {
                    System.out.println(employee);
                }

            } catch (Exception exception) {
            }

            System.out.println(Constants.DATA_LOADED_MESSAGE);

        } else if (args[0].equals("s")) {
            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String employeeData = readEmployeeFile();
                System.out.println(employeeData);

                String[] employees = employeeData.split(Constants.COMMA);

                System.out.println(
                    employees[new Random().nextInt(employees.length)]
                );

            } catch (Exception exception) {
            }

            System.out.println(Constants.DATA_LOADED_MESSAGE);

        } else if (args[0].contains("+")) {
            System.out.println(Constants.LOADING_MESSAGE);

            try {
                writeEmployeeFile(
                    Constants.COMMA_SPACE + args[0].substring(1), true
                );

            } catch (Exception exception) {
            }

            System.out.println(Constants.DATA_LOADED_MESSAGE);

        } else if (args[0].contains("?")) {
            System.out.println(Constants.LOADING_MESSAGE);

            try {
                for (String employee : readEmployeeFile().split(Constants.COMMA)) {
                    if (employee.equals(args[0].substring(1))) {
                        System.out.println(Constants.EMPLOYEE_FOUND_MESSAGE);
                        System.out.println(Constants.DATA_LOADED_MESSAGE);
                        return;
                    }
                }

                System.out.println("Employee not found!");

            } catch (Exception exception) {
            }

            System.out.println(Constants.DATA_LOADED_MESSAGE);

        } else if (args[0].contains("c")) {
            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String line = readEmployeeFile();
                int wordCount = line.trim().split("\\s+").length;

                System.out.println(
                    wordCount + " word(s) found " + line.length()
                );

            } catch (Exception exception) {
            }

            System.out.println(Constants.DATA_LOADED_MESSAGE);

        } else if (args[0].contains("u")) {
            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String[] employees = readEmployeeFile().split(Constants.COMMA);

                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(args[0].substring(1))) {
                        employees[i] = Constants.UPDATED_VALUE;
                    }
                }

                writeEmployeeFile(
                    String.join(Constants.COMMA, employees), false
                );

            } catch (Exception exception) {
            }

            System.out.println(Constants.DATA_UPDATED_MESSAGE);

        } else if (args[0].contains("d")) {
            System.out.println(Constants.LOADING_MESSAGE);

            try {
                List<String> employeeList = new ArrayList<>(
                    Arrays.asList(readEmployeeFile().split(Constants.COMMA))
                );

                employeeList.remove(args[0].substring(1));

                writeEmployeeFile(
                    String.join(Constants.COMMA, employeeList), false
                );

            } catch (Exception exception) {
            }

            System.out.println(Constants.DATA_DELETED_MESSAGE);
        }
    }
}
