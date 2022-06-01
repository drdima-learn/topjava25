package ru.javawebinar.topjava;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

public class UtilTestData {

    private static Map<String, String[]> map = new HashMap<>();

    static {
        map.put("User", new String[]{"registered", "roles"});
        map.put("Meal", new String[]{"user"});
    }


    public static <T> void assertMatch(T actual, T expected) {
        assertThat((actual)).isEqualToIgnoringGivenFields(expected,
                getIgnoreFields(actual));
    }

    public static <T> void assertMatch(T actual, T... expected) {
        assertThat((actual)).isEqualToIgnoringGivenFields(Arrays.asList(expected),
                getIgnoreFields(actual));
    }

    public static <T> void assertMatch(Iterable<T> actual, T... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static <T> void assertMatch(Iterable<T> actual, Iterable<T> expected) {
//        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").
//                isEqualTo(expected);

        assertThat(actual).usingElementComparatorIgnoringFields(getIgnoreFields(actual)).
                isEqualTo(expected);
    }

    private static String[] getIgnoreFields(Object obj) {
        if (obj==null) return new String[]{""};
        String className = obj.getClass().getSimpleName();
        if (obj instanceof Iterable){
            Iterable iterable = (Iterable) obj;
            try {
                Object o = iterable.iterator().next();
                className = o.getClass().getSimpleName();
            } catch (NoSuchElementException e) {
                return new String[]{""};
            }
        }
        return map.get(className);
    }
}
