// LegacyRoster.java

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * LegacyRoster - A Student Information Management System
 * 
 * This class provides functionality to manage and analyze student academic records.
 * 
 * Key Features:
 * 
 * 1. Honor Roll Management (getHonorRoll)
 *    - Filters students based on minimum GPA and credit requirements
 *    - Returns students sorted by last name, then first name
 *    - Uses modern Comparator.comparing() for efficient sorting
 * 
 * 2. Email Management (getEmailsByMajor)
 *    - Retrieves email addresses for students in a specific major
 *    - Normalizes emails to lowercase
 *    - Returns sorted list for consistent presentation
 * 
 * 3. Academic Performance Tracking (getTopNByGpa)
 *    - Identifies top performing students by GPA
 *    - Uses credits as a tiebreaker
 *    - Implements efficient sorting with Comparator.comparing()
 * 
 * 4. GPA Analysis (getAverageGpa)
 *    - Calculates average GPA across student population
 *    - Handles null values and empty lists safely
 *    - Returns OptionalDouble for better null handling
 * 
 * 5. Student Lookup (findById)
 *    - Provides efficient student lookup by ID
 *    - Case-sensitive ID matching
 *    - Returns Optional for safer null handling
 * 
 * 6. Course Management (getDistinctCourseTitles)
 *    - Aggregates all unique course titles
 *    - Sorts titles case-insensitively
 *    - Eliminates duplicates automatically
 * 
 * Implementation Features:
 * - Uses Java Stream API for efficient data processing
 * - Implements functional programming patterns
 * - Provides null-safety through Optional types
 * - Uses modern Java comparison utilities
 * - Maintains immutable return types for thread safety
 */
public class LegacyRoster {
// --- Simple domain model ---

    static class Course {

        private final String code; // e.g., "CSC-101"
        private final String title; // e.g., "Intro to CS"

        public Course(String code, String title) {
            this.code = code;
            this.title = title;
        }

        public String getCode() {
            return code;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return code + " - " + title;
        }
    }

    static class Student {

        private final String id;
        private final String firstName;
        private final String lastName;
        private final String major; // e.g., "CS", "SE", "IT"
        private final double gpa; // 0.0 - 4.0
        private final int credits; // accumulated credits
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
            this.courses = new ArrayList<Course>(courses);
        }

        public String getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getMajor() {
            return major;
        }

        public double getGpa() {
            return gpa;
        }

        public int getCredits() {
            return credits;
        }

        public String getEmail() {
            return email;
        }

        public List<Course> getCourses() {
            return courses;
        }

        @Override
        public String toString() {
            return String.format("%s %s (ID:%s, %s) GPA: %.2f, Credits: %d",
                    firstName, lastName, id, major, gpa, credits);
        }
    }
// --- Legacy utility methods students will refactor ---

    /**
     * Return students with GPA >= minGpa AND credits >= minCredits, sorted by
     * last, then first name.
     */
    public static Optional<List<Student>> getHonorRoll(List<Student> students, double minGpa,
            int minCredits) {
        /*
        List<Student> result = new ArrayList<Student>();
               for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s != null) {
                if (s.getGpa() >= minGpa && s.getCredits() >= minCredits) {
                    result.add(s);
                }
            }
        }*/
        if (students == null || students.isEmpty()) {
            return Optional.empty();
        }

        List<Student> result = students.stream()
                .filter(s -> s != null)
                .filter(s -> s.getGpa() >= minGpa && s.getCredits() >= minCredits)
                .sorted(Comparator.comparing(Student::getLastName, String::compareToIgnoreCase)
                        .thenComparing(Student::getFirstName, String::compareToIgnoreCase))
                .collect(Collectors.toList());

// Manual sort using anonymous Comparator
/* 
 *         Collections.sort(result, new Comparator<Student>() {
            public int compare(Student a, Student b) {
                int lastCmp = a.getLastName().compareToIgnoreCase(b.getLastName());
                if (lastCmp != 0) {
                    return lastCmp;
                }
                return a.getFirstName().compareToIgnoreCase(b.getFirstName());
            }
        });
         */
        return result.isEmpty() ? Optional.empty() : Optional.of(result);
    }

    /**
     * Return emails of students in a given major, lowercased and sorted
     * alphabetically.
     */
    public static Optional<List<String>> getEmailsByMajor(List<Student> students, String major) {

        if (students == null || major == null) {
            return Optional.empty();
        }

        List<String> emails = students.stream()
                .filter(s -> {
                    if (s == null || s.getMajor() == null || s.getEmail() == null) {
                        return false;
                    }
                    return s.getMajor().equalsIgnoreCase(major);
                })
                .map(s -> s.getEmail().toLowerCase())
                .sorted()
                .collect(Collectors.toList());

        return emails.isEmpty() ? Optional.empty() : Optional.of(emails);


        /*        for (Student s : students) {
            if (s != null && s.getMajor() != null && s.getEmail() != null) {
                if (s.getMajor().equalsIgnoreCase(major)) {
                    emails.add(s.getEmail().toLowerCase());
                }
            }
        }
        Collections.sort(emails); */
        // natural String order
    }

    /**
     * Return top N students by GPA (descending). Ties broken by credits
     * (descending).
     */
    public static Optional<List<Student>> getTopNByGpa(List<Student> students, int n) {
        if (students == null || students.isEmpty()) {
            return Optional.empty();
        }
        List<Student> top = students.stream()
                .filter(s -> {
                    if (s == null) {
                        return false;
                    }
                    return true;
                })
                .sorted(Comparator.comparing(Student::getGpa).reversed()
                        .thenComparing(Student::getCredits, Comparator.reverseOrder()))
                .limit(n)
                .collect(Collectors.toList());


        /*
         *  for (Student s : students) {
            if (s != null) {
                copy.add(s);
            }
        }
        Collections.sort(copy, new Comparator<Student>() {
            public int compare(Student a, Student b) {
                if (b.getGpa() > a.getGpa()) {
                    return 1;
                }
                if (b.getGpa() < a.getGpa()) {
                    return -1;
                }
// tie-breaker by credits
                return b.getCredits() - a.getCredits();
            }
        });
        List<Student> top = new ArrayList<Student>();
        int limit = Math.min(n, copy.size());
        for (int i = 0; i < limit; i++) {
            top.add(copy.get(i));
        }
        return top;
         */
        return top.isEmpty() ? Optional.empty() : Optional.of(top);
    }

    /**
     * Compute average GPA across all students; returns 0.0 if list is empty.
     *
     */
    public static OptionalDouble getAverageGpa(List<Student> students) {
        /*
         *         if (students == null || students.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        int count = 0;
        for (Student s : students) {
            if (s != null) {
                total += s.getGpa();
                count++;
            }
        }
        if (count == 0) {
            return 0.0;
        }
        return total / count;
         */

        if (students == null || students.isEmpty()) {
            return OptionalDouble.empty();
        }

        return students.stream()
                .filter(s -> {
                    if (s == null) {
                        return false;
                    }
                    return true;
                })
                .mapToDouble(Student::getGpa)
                .average();
    }

    /**
     * Find first student with matching ID (case-sensitive). Returns null if not
     * found.
     *
     */
    public static Optional<Student> findById(List<Student> students, String id) {
        if (id == null) {
            return Optional.empty();
        }
        return students.stream()
                .filter(s -> {
                    if (s == null || s.getId() == null) {
                        return false;
                    }
                    return s.getId().equals(id);
                })
                .findFirst();
        /* 
         *         if (id == null) {
            return null;
        }
        for (Student s : students) {
            if (s != null && s.getId() != null) {
                if (s.getId().equals(id)) {
                    return s;
                }
            }
        }
        return null;
         */

    }

    /**
     * Return a sorted list of distinct course titles across all students.
     */
    public static Optional<List<String>> getDistinctCourseTitles(List<Student> students) {
        /*        HashSet<String> seen = new HashSet<String>();
        for (Student s : students) {
            if (s != null && s.getCourses() != null) {
                List<Course> cs = s.getCourses();
                for (int i = 0; i < cs.size(); i++) {
                    Course c = cs.get(i);
                    if (c != null && c.getTitle() != null) {
                        if (!seen.contains(c.getTitle())) {
                            seen.add(c.getTitle());
                        }
                    }
                }
            }
        }
        List<String> titles = new ArrayList<String>(seen);
        Collections.sort(titles, new Comparator<String>() {
            public int compare(String a, String b) {
                return a.compareToIgnoreCase(b);
            }
        });
        return titles; */
        if (students == null || students.isEmpty()) {
            return Optional.empty();
        }

        List<String> titles = students.stream()
                .filter(s -> {
                    if (s == null || s.getCourses() == null) {
                        return false;
                    }
                    return true;
                })
                .flatMap(s -> s.getCourses().stream())
                .filter(c -> {
                    if (c == null || c.getTitle() == null) {
                        return false;
                    }
                    return true;
                })
                .map(Course::getTitle)
                .distinct()
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.toList());

        return titles.isEmpty() ? Optional.empty() : Optional.of(titles);

    }
// --- Demo data and output ---

    public static void main(String[] args) {
        // Sample courses
        Course csc101 = new Course("CSC-101", "Intro to CS");
        Course csc225 = new Course("CSC-225", "Data Structures");
        Course csc372 = new Course("CSC-372", "Generative AI");
        Course mat250 = new Course("MAT-250", "Discrete Math");
        Course se310 = new Course("SE-310", "Software Engineering");

        // Sample students
        List<Student> roster = new ArrayList<>();

        List<Course> aCourses = Arrays.asList(csc101, csc225, mat250);
        List<Course> bCourses = Arrays.asList(csc225, se310);
        List<Course> cCourses = Arrays.asList(csc101, csc372, se310, csc225);

        roster.add(new Student("A001", "Emma", "Rowe", "CS", 3.82, 78,
                "emma.rowe@univ.edu", aCourses));
        roster.add(new Student("A002", "Noah", "Turner", "SE", 3.10, 45,
                "noah.turner@univ.edu", bCourses));
        roster.add(new Student("A003", "Xander", "Wood", "CS", 3.95, 90,
                "xander.wood@univ.edu", cCourses));
        roster.add(new Student("A004", "Ethan", "Sexton", "IT", 2.75, 30,
                "ethan.sexton@univ.edu", aCourses));
        roster.add(new Student("A005", "Jolie", "Barger", "CS", 3.35, 60,
                "jolie.barger@univ.edu", bCourses));
        roster.add(new Student("A006", "Sravani", "Kadiyala", "SE", 3.55, 72,
                "sravani.k@univ.edu", cCourses));

        // Honor Roll
        Optional<List<Student>> honor = getHonorRoll(roster, 3.5, 60);
        System.out.println("Honor Roll:");
        honor.ifPresent(list -> list.forEach(s -> System.out.println(" - " + s)));

        // Emails by major (lowercased, sorted)
        Optional<List<String>> csEmails = getEmailsByMajor(roster, "CS");
        System.out.println("\nCS Emails (sorted):");
        csEmails.ifPresent(list -> list.forEach(e -> System.out.println(" - " + e)));

        // Top 3 by GPA
        Optional<List<Student>> top3 = getTopNByGpa(roster, 3);
        System.out.println("\nTop 3 by GPA:");
        top3.ifPresent(list -> list.forEach(s -> System.out.println(" - " + s)));

        // Average GPA
        double avg = getAverageGpa(roster).orElse(0.0);
        System.out.println("\nAverage GPA: " + String.format("%.3f", avg));

        // Find by ID
        Optional<Student> found = findById(roster, "A003");
        System.out.println("\nfindById('A003'): "
                + found.map(Student::toString).orElse("not found"));

        // Distinct course titles across roster
        Optional<List<String>> titles = getDistinctCourseTitles(roster);
        System.out.println("\nDistinct course titles (sorted, case-insensitive):");
        titles.ifPresent(list -> list.forEach(t -> System.out.println(" - " + t)));
    }
}
