package A6_Dijkstra;

public class EntryPair{
    public Vertex value;
    public long priority;

    public EntryPair(Vertex aValue, long aPriority) {
        value = aValue;
        priority = aPriority;
    }

    public Vertex getValue() {
        return value;
    }

    public long getPriority() {
        return priority;
    }
}