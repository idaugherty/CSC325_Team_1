// LegacyRoster.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Assignment8Refactored {

    // --- Simple domain model ---

    public static class Course {
        private final String code;     // e.g., "CSC-101"
        private final String title;    // e.g., "Intro to CS"

        public Course(String code, String title) {
            this.code = code;
            this.title = title;
        }

        public String getCode() { return code; }
        public String getTitle() { return title; }

        @Override
        public String toString() {
            return code + " - " + title;
        }
    }

    public static class Student {
        private final String id;
        private final String firstName;
        private final String lastName;
        private final String major;    // e.g., "CS", "SE", "IT"
        private final double gpa;      // 0.0 - 4.0
        private final int credits;     // accumulated credits
        private final String email;
        private final List<Course> courses; // enrolled courses

        public Student(String id, String firstName, String lastName,
                       String major, double gpa, int credits, String email,
                       List<Course> courses) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.major = major;
            this.gpa = gpa;
            this.credits = credits;
            this.email = email;
            // Intentionally store a *mutable* list to highlight refactor goals
            this.courses = new ArrayList<>(courses);
        }

        public String getId() { return id; }
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public String getMajor() { return major; }
        public double getGpa() { return gpa; }
        public int getCredits() { return credits; }
        public String getEmail() { return email; }
        public List<Course> getCourses() { return courses; }

        @Override
        public String toString() {
            return String.format("%s %s (ID:%s, %s) GPA: %.2f, Credits: %d",
                    firstName, lastName, id, major, gpa, credits);
        }
    }

    // --- Legacy utility methods students will refactor ---

    /*
     * Advanced features used in this file (short explanation):
     *
     * Optional<T>
     * - Purpose: represents a value that may be present or absent, avoiding null returns.
     * - Here we provide `findByIdOptional` that returns Optional<Student> instead of null.
     *
     * reduce()
     * - Purpose: fold a stream into a single value using an accumulator. Useful for sums,
     *   concatenation, or custom aggregations.
     * - Here we use reduce to compute total credits across students in
     *   `getTotalCreditsUsingReduce`.
     *
     * Note: this file also already uses Comparator.comparing(), flatMap() and distinct()
     * elsewhere to exemplify common stream operations.
     */
    /** Return students with GPA >= minGpa AND credits >= minCredits, sorted by last, then first name. */
    public static List<Student> getHonorRoll(List<Student> students, double minGpa, int minCredits) {
        if (students == null) return Collections.emptyList();
        // Use stream to filter, sort and collect into an immutable-result list
        return students.stream()
                .filter(s -> s != null) // skip null entries
                .filter(s -> s.getGpa() >= minGpa && s.getCredits() >= minCredits) // selection
                // sort by lastName then firstName, case-insensitive
                .sorted(Comparator.comparing(Student::getLastName, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Student::getFirstName, String.CASE_INSENSITIVE_ORDER))
                .collect(java.util.stream.Collectors.toList());
        // Note: preserves original semantics but avoids in-place mutation
    }

    /** Return emails of students in a given major, lowercased and sorted alphabetically. */
    public static List<String> getEmailsByMajor(List<Student> students, String major) {
        if (students == null || major == null) return Collections.emptyList();
        // Stream: filter by major, map to lowercased emails, sort, collect
        return students.stream()
                .filter(s -> s != null && s.getMajor() != null && s.getEmail() != null)
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .map(s -> s.getEmail().toLowerCase())
                .sorted() // natural string order
                .collect(java.util.stream.Collectors.toList());
    }

    /** Return top N students by GPA (descending). Ties broken by credits (descending). */
    public static List<Student> getTopNByGpa(List<Student> students, int n) {
        if (students == null || n <= 0) return Collections.emptyList();
        // Stream: filter non-null, sort by GPA desc then credits desc, limit to n
        return students.stream()
                .filter(s -> s != null)
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed()
                        .thenComparing(Comparator.comparingInt(Student::getCredits).reversed()))
                .limit(n)
                .collect(java.util.stream.Collectors.toList());
        // Avoids explicit copy and manual index-based slicing
    }

    /** Compute average GPA across all students; returns 0.0 if list is empty. */
    public static double getAverageGpa(List<Student> students) {
        if (students == null) return 0.0;
        // Use DoubleStream to compute average while skipping nulls
        return students.stream()
                .filter(s -> s != null)
                .mapToDouble(Student::getGpa)
                .average()
                .orElse(0.0);
    }

    /** Find first student with matching ID (case-sensitive). Returns null if not found. */
    public static Student findById(List<Student> students, String id) {
        if (students == null || id == null) return null;
        // Find first matching id using stream
        return students.stream()
                .filter(s -> s != null && s.getId() != null)
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Optional-based finder: returns Optional<Student> instead of null.
     * Demonstrates use of Optional<> to avoid null returns.
     */
    public static Optional<Student> findByIdOptional(List<Student> students, String id) {
        if (students == null || id == null) return Optional.empty();
        return students.stream()
                .filter(s -> s != null && s.getId() != null)
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    /**
     * Compute total credits using reduce(). Returns 0 if no students.
     * Demonstrates reduce for aggregation.
     */
    public static int getTotalCreditsUsingReduce(List<Student> students) {
        if (students == null) return 0;
        return students.stream()
                .filter(Objects::nonNull)
                .map(Student::getCredits)
                .reduce(0, Integer::sum);
    }

    /** Return a sorted list of distinct course titles across all students. */
    public static List<String> getDistinctCourseTitles(List<Student> students) {
        if (students == null) return Collections.emptyList();
        // Flat-map all course titles, filter non-null, distinct, then sort case-insensitively
        return students.stream()
                .filter(s -> s != null && s.getCourses() != null)
                .flatMap(s -> s.getCourses().stream()) // stream of Course
                .filter(c -> c != null && c.getTitle() != null)
                .map(Course::getTitle)
                .distinct()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(java.util.stream.Collectors.toList());
    }

    // --- Demo data and output ---

    public static void main(String[] args) {
        // Sample courses
        Course csc101 = new Course("CSC-101", "Intro to CS");
        Course csc225 = new Course("CSC-225", "Data Structures");
        Course csc372 = new Course("CSC-372", "Generative AI");
        Course mat250 = new Course("MAT-250", "Discrete Math");
        Course se310  = new Course("SE-310",  "Software Engineering");

        // Sample students
    List<Student> roster = new ArrayList<>();
    List<Course> aCourses = new ArrayList<>();
        aCourses.add(csc101); aCourses.add(csc225); aCourses.add(mat250);

    List<Course> bCourses = new ArrayList<>();
        bCourses.add(csc225); bCourses.add(se310);

    List<Course> cCourses = new ArrayList<>();
        cCourses.add(csc101); cCourses.add(csc372); cCourses.add(se310); cCourses.add(csc225);

        roster.add(new Student("A001", "Emma",  "Rowe",        "CS", 3.82, 78, "emma.rowe@univ.edu", aCourses));
        roster.add(new Student("A002", "Noah",  "Turner",      "SE", 3.10, 45, "noah.turner@univ.edu", bCourses));
        roster.add(new Student("A003", "Xander","Wood",        "CS", 3.95, 90, "xander.wood@univ.edu", cCourses));
        roster.add(new Student("A004", "Ethan", "Sexton",      "IT", 2.75, 30, "ethan.sexton@univ.edu", aCourses));
        roster.add(new Student("A005", "Jolie", "Barger",      "CS", 3.35, 60, "jolie.barger@univ.edu", bCourses));
        roster.add(new Student("A006", "Sravani","Kadiyala",   "SE", 3.55, 72, "sravani.k@univ.edu",    cCourses));

        // Honor roll (GPA >= 3.5 and credits >= 60), sorted by last/first
        List<Student> honor = getHonorRoll(roster, 3.5, 60);
        System.out.println("Honor Roll:");
        // Use forEach with lambda instead of an imperative for-loop
        honor.forEach(s -> System.out.println(" - " + s));

        // Emails by major (lowercased, sorted)
        List<String> csEmails = getEmailsByMajor(roster, "CS");
        System.out.println("\nCS Emails (sorted):");
        // Stream-style printing
        csEmails.forEach(e -> System.out.println(" - " + e));

        // Top 3 by GPA
        List<Student> top3 = getTopNByGpa(roster, 3);
        System.out.println("\nTop 3 by GPA:");
        top3.forEach(s -> System.out.println(" - " + s));

        // Average GPA
        double avg = getAverageGpa(roster);
        System.out.println("\nAverage GPA: " + String.format("%.3f", avg));

        // Find by ID
        Student found = findById(roster, "A003");
        System.out.println("\nfindById('A003'): " + (found == null ? "not found" : found.toString()));

        // Distinct course titles across roster
        List<String> titles = getDistinctCourseTitles(roster);
        System.out.println("\nDistinct course titles (sorted, case-insensitive):");
        titles.forEach(t -> System.out.println(" - " + t));

    // Demonstrate Optional-based finder (avoid returning null)
    Optional<Student> maybe = findByIdOptional(roster, "A002");
    System.out.println("\nfindByIdOptional('A002'): " + maybe.map(Object::toString).orElse("not found"));

    // Demonstrate reduce() to compute total credits
    int totalCredits = getTotalCreditsUsingReduce(roster);
    System.out.println("\nTotal credits (using reduce): " + totalCredits);
    }
}
