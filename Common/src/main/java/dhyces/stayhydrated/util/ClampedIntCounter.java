package dhyces.stayhydrated.util;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;

public class ClampedIntCounter {
    protected int min;
    protected int max;
    protected int current;

    public ClampedIntCounter(int min, int max, int current) {
        this.min = min;
        this.max = max;
        this.current = current;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int get() {
        return current;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void set(int newVal) {
        if (newVal > max) {
            current = max;
        } else if (newVal < min) {
            current = min;
        } else {
            current = newVal;
        }
    }

    public int getAndSet(Int2IntFunction function) {
        int retCurr = current;
        set(function.get(current));
        return retCurr;
    }

    public boolean isMax() {
        return current == max;
    }

    public boolean isMin() {
        return current == min;
    }

    public boolean isExtreme() {
        return isMin() || isMax();
    }
}
