package system.prolog;

import org.jpl7.Integer;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;
import org.jpl7.Atom;
import system.model.Appointment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrologUtil {

    private static final String VALID_USER_ID_FILE_PATH = "prolog rules/valid_userid.pl";
    private static final String VALID_PASSWORD_FILE_PATH = "prolog rules/valid_password.pl";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);

    private static Boolean connect(String filePath) {
        Query consultQuery = new Query("consult", new Term[] {new Atom(filePath)});
        return consultQuery.hasSolution();
    }

    public static List<Appointment> sortAppointmentByDate(List<Appointment> unsortedList) {
        Term unsortedPrologList = Term.termArrayToList(
                unsortedList.stream().map(obj -> new Integer((int) LocalDate.parse(obj.getDate(), formatter).toEpochDay())).toArray(Term[]::new)
        );

        // Create a variable for the sorted list
        Variable sortedPrologList = new Variable("Sorted");

        // Create the sorting query
        Query sortingQuery = new Query(
                "msort", // Use the msort/2 predicate to sort the list
                new Term[]{unsortedPrologList, sortedPrologList}
        );

        // Execute the query
        if (sortingQuery.hasSolution()) {
            // Extract the sorted list from the solution
            Term[] sortedListArray = sortingQuery.oneSolution().get("Sorted").listToTermArray();

            // Convert the sorted Prolog list back to a Java list
            List<Long> sortedEpochDayList = Arrays.stream(sortedListArray)
                    .map(Term::longValue)
                    .collect(Collectors.toList());

            // Create a LinkedHashMap of index to Appointment instances
            Map<Long, Appointment> indexToObjectMap = new LinkedHashMap<>();
            IntStream.range(0, unsortedList.size())
                    .forEach(index -> indexToObjectMap.put((long) index, unsortedList.get(index)));

            // Create a map of epoch days to the corresponding indexes in the original list
            Map<Long, Long> epochDayToIndexMap = IntStream.range(0, unsortedList.size())
                    .boxed()
                    .collect(Collectors.toMap(index -> LocalDate.parse(unsortedList.get(index).getDate(), formatter).toEpochDay(),
                            index -> (long) index,
                            (a, b) -> a));

            // Convert the sorted list of epoch days to a sorted list of indexes
            List<Long> sortedIndexList = sortedEpochDayList.stream()
                    .map(epochDayToIndexMap::get)
                    .collect(Collectors.toList());

            return sortedIndexList.stream().map(indexToObjectMap::get).collect(Collectors.toList());

        } else {
            throw new RuntimeException("Failed to sort the list using Prolog.");
        }
    }

    public static Boolean validateUserID(String userID) {
        if (connect(VALID_USER_ID_FILE_PATH)) {
            Query query = new Query("valid_malaysia_nric", new Term[] {new Atom(userID)});
            return query.hasSolution();
        }
        throw new RuntimeException("Could not load user id rules");
    }

    public static Boolean validatePassword(String password) {
        if (connect(VALID_PASSWORD_FILE_PATH)) {
            Query query = new Query("valid_password", new Term[] {new Atom(password)});
            return query.hasSolution();
        }
        throw new RuntimeException("Could not load password rules");
    }

}
