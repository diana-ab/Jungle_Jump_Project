package util;

import java.util.ArrayList;

public class LimitedList<E> extends ArrayList<E> {
    private int limit;

    public LimitedList(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean add(E e) {
        if (this.size() < this.limit) {
            super.add(e);
        } else {
            this.remove(0);
            this.add(e);
        }
        return true;
    }
}
