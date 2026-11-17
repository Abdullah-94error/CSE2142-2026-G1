
// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Read employee data from file
    private static String readEmployeeFile() throws IOException {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream("employees.txt")
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
            new FileWriter("employees.txt", append)
        );

        writer.write(content);
        writer.close();
    }

    public static void main(String[] args) {

        // Check arguments
        if (args.length != 1) {
            System.out.println("Invalid number of arguments!");
            return;
        }

        if (args[0].equals("l")) {
            System.out.println("Loading data ...");

            try {
                String line = readEmployeeFile();
                String employees[] = line.split(",");

                for (String employee : employees) {
                    System.out.println(employee);
                }

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");

            try {
                String line = readEmployeeFile();
                System.out.println(line);

                String employees[] = line.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);

                System.out.println(employees[randomIndex]);

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");

            try {
                String name = args[0].substring(1);
                writeEmployeeFile(", " + name, true);

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");

            try {
                String line = readEmployeeFile();
                String employees[] = line.split(",");
                boolean found = false;
                String searchName = args[0].substring(1);

                for (int i = 0; i < employees.length && !found; i++) {
                    if (employees[i].equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");

            try {
                String line = readEmployeeFile();
                char[] characters = line.toCharArray();
                boolean isInWord = false;
                int wordCount = 0;

                for (char character : characters) {
                    if (character == ' ') {
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

            System.out.println("Data Loaded.");

        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");

            try {
                String line = readEmployeeFile();
                String employees[] = line.split(",");
                String name = args[0].substring(1);

                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(name)) {
                        employees[i] = "Updated";
                    }
                }

                writeEmployeeFile(String.join(",", employees), false);

            } catch (Exception exception) {
            }

            System.out.println("Data Updated.");

        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");

            try {
                String line = readEmployeeFile();
                String employees[] = line.split(",");
                String name = args[0].substring(1);

                List<String> employeeList = new ArrayList<>(
                    Arrays.asList(employees)
                );

                employeeList.remove(name);

                writeEmployeeFile(String.join(",", employeeList), false);

            } catch (Exception exception) {
            }

            System.out.println("Data Deleted.");
        }
    }
}
