import java.util.*;

public class ConfirmationPrinter {
    public void printInput(String raw) {
        System.out.println("INPUT: " + raw);
    }

    public void printSuccess(String id, int totalCount) {
        System.out.println("OK: created student " + id);
        System.out.println("Saved. Total students: " + totalCount);
        System.out.println("CONFIRMATION:");
    }

    public void printRecord(StudentRecord record) {
        System.out.println(record);
    }

    public void printValidationErrors(List<String> errors) {
        System.out.println("ERROR: cannot register");
        for (String e : errors) {
            System.out.println("- " + e);
        }
    }
}
