public class AttendanceRule implements EligibilityRule {
    private final int minAttendancePct;

    public AttendanceRule(int minAttendancePct) {
        this.minAttendancePct = minAttendancePct;
    }

    @Override
    public boolean isViolated(StudentProfile profile) {
        return profile.attendancePct < minAttendancePct;
    }

    @Override
    public String getReason() {
        return "attendance below " + minAttendancePct;
    }
}
