public interface EligibilityRule {
    boolean isViolated(StudentProfile profile);
    String getReason();
}
