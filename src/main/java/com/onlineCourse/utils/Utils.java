package com.onlineCourse.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class Utils {
    private Utils() {
    }

    public static <T> boolean isSafe(Collection<T> list) {
        return !(list == null || list.isEmpty());
    }

    public static <T> Collection<T> safe(Collection<T> list) {
        return Optional.ofNullable(list).orElseGet(Collections::emptyList);
    }
}
