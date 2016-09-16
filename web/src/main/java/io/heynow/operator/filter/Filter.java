package io.heynow.operator.filter;

import java.util.Map;

public interface Filter {
    boolean filter(Map<String, Object> payload);
}
