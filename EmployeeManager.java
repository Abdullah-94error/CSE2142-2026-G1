
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Reads employee information from the data file.
    private static String readEmployeeData() throws IOException {
        BufferedReader employeeReader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream(Constants.FILE_NAME)
            )
        );

        String employeeData = employeeReader.readLine();
        employeeReader.close();

        return employeeData;
    }

    // Writes employee information to the data file.
    private static void writeEmployeeData(String content, boolean append)
            throws IOException {

        BufferedWriter employeeWriter = new BufferedWriter(
            new FileWriter(Constants.FILE_NAME, append)
        );

        employeeWriter.write(content);
        employeeWriter.close();
    }

    // Executes the requested employee management operation.
    public static void main(String[] args) {

        // Validate the number of command-line arguments.
        if (args.length != 1) {
            System.out.println(Constants.INVALID_ARGUMENT_MESSAGE);
            return;
        }

        String command = args[0];

        // Display all employees.
        if (command.equals("l")) {
            System.out.println(Constants.LOADING_MESSAGE);

            try {
                for (String employeeName :
                        readEmployeeData().split(Constants.COMMA)) {
                    System.out.println(employeeName);
                }

            } catch (Exception exception) {
                System.out.println("Error reading employee data.");
            }

            System.out.println(Constants.DATA_LOADED_MESSAGE);

        // Display a randomly selected employee.
        } else if (command.equals("s")) {
            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String employeeData = readEmployeeData();
                String[] employeeNames = employeeData.split(Constants.COMMA);

                System.out.println(employeeData);

                System.out.println(
                    employeeNames[
                        new Random().nextInt(employeeNames.length)
                    ]
                );

            } catch (Exception exception) {
                System.out.println("Error selecting employee.");
            }

            System.out.println(Constants.DATA_LOADED_MESSAGE);

        // Add a new employee.
        } else if (command.startsWith("+") && command.length() > 1) {
            System.out.println(Constants.LOADING_MESSAGE);

            try {
                writeEmployeeData(
                    Constants.COMMA_SPACE + command.substring(1), true
                );

            } catch (Exception exception) {
                System.out.println("Error adding employee.");
            }

            System.out.println(Constants.DATA_LOADED_MESSAGE);

        // Search for an employee.
        } else if (command.startsWith("?") && command.length() > 1) {
            System.out.println(Constants.LOADING_MESSAGE);

            try {
                for (String employeeName :
                        readEmployeeData().split(Constants.COMMA)) {

                    if (employeeName.equals(command.substring(1))) {
                        System.out.println(Constants.EMPLOYEE_FOUND_MESSAGE);
                        System.out.println(Constants.DATA_LOADED_MESSAGE);
                        return;
                    }
                }

                System.out.println("Employee not found!");

            } catch (Exception exception) {
                System.out.println("Error searching employee.");
            }

            System.out.println(Constants.DATA_LOADED_MESSAGE);

        // Count the words in employee data.
        } else if (command.equals("c")) {
            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String employeeData = readEmployeeData();
                int wordCount = employeeData.trim().split("\\s+").length;

                System.out.println(
                    wordCount + " word(s) found " + employeeData.length()
                );

            } catch (Exception exception) {
                System.out.println("Error counting words.");
            }

            System.out.println(Constants.DATA_LOADED_MESSAGE);

        // Update an employee's information.
        } else if (command.startsWith("u") && command.length() > 1) {
            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String[] employeeNames =
                    readEmployeeData().split(Constants.COMMA);

                for (int index = 0; index < employeeNames.length; index++) {
                    if (employeeNames[index].equals(command.substring(1))) {
                        employeeNames[index] = Constants.UPDATED_VALUE;
                    }
                }

                writeEmployeeData(
                    String.join(Constants.COMMA, employeeNames), false
                );

            } catch (Exception exception) {
                System.out.println("Error updating employee.");
            }

            System.out.println(Constants.DATA_UPDATED_MESSAGE);

        // Delete an employee from the data file.
        } else if (command.startsWith("d") && command.length() > 1) {
            System.out.println(Constants.LOADING_MESSAGE);

            try {
                List<String> employeeNames = new ArrayList<>(
                    Arrays.asList(
                        readEmployeeData().split(Constants.COMMA)
                    )
                );

                employeeNames.remove(command.substring(1));

                writeEmployeeData(
                    String.join(Constants.COMMA, employeeNames), false
                );

            } catch (Exception exception) {
                System.out.println("Error deleting employee.");
            }

            System.out.println(Constants.DATA_DELETED_MESSAGE);

        // Display a message for unsupported commands.
        } else {
            System.out.println("Invalid argument!");
        }
    }
}
