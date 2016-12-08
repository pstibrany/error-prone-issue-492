import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> set1 = ImmutableSet.of("hello", "error-prone");
        Set<String> set2 = ImmutableSet.of("hello", "world");

        for (String k: Sets.difference(set1, set2)) {
            System.out.printf(k);
        }
    }
}
