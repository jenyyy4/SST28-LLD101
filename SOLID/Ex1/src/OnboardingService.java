import java.util.*;

public class OnboardingService {
    private final StudentRepository repository;
    private final InputParser parser;
    private final StudentValidator validator;
    private final ConfirmationPrinter printer;

    public OnboardingService(StudentRepository repository, InputParser parser, 
                            StudentValidator validator, ConfirmationPrinter printer) {
        this.repository = repository;
        this.parser = parser;
        this.validator = validator;
        this.printer = printer;
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        Map<String, String> kv = parser.parse(raw);

        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");

        ValidationResult validationResult = validator.validate(name, email, phone, program);

        if (!validationResult.isValid()) {
            printer.printValidationErrors(validationResult.getErrors());
            return;
        }

        String id = IdUtil.nextStudentId(repository.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        repository.save(rec);

        printer.printSuccess(id, repository.count());
        printer.printRecord(rec);
    }
}
