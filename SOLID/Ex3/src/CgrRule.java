public class CgrRule implements EligibilityRule {
    private final double minCgr;

    public CgrRule(double minCgr) {
        this.minCgr = minCgr;
    }

    @Override
    public boolean isViolated(StudentProfile profile) {
        return profile.cgr < minCgr;
    }

    @Override
    public String getReason() {
        return "CGR below " + String.format("%.1f", minCgr);
    }
}
