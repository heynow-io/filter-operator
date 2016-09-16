import io.heynow.operator.filter.Filter

class Test implements Filter {

    @Override
    boolean filter(Map<String, Object> payload) {
        return false;
    }
}