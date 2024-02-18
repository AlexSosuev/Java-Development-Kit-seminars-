package homework_3;

public record RecordPair<T1, T2>(T1 t1, T2 t2) {
    @Override
    public String toString() {
        return "(" + t1 + ", " + t2 + ')';
    }
}
