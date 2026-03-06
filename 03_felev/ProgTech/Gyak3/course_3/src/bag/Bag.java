package bag;

import java.util.ArrayList;

/**
 *
 * @author bli
 */
public class Bag {

    private final ArrayList<BagItem> data;

    public Bag() {
        this.data = new ArrayList<>();
    }

    public void add(String item, int num) {
        if (num <= 0) {
            throw new IllegalArgumentException();
        }
        BagItem bi = getItem(item);
        if (bi == null) {
            data.add(new BagItem(item, num));
        } else {
            bi.addNum(num);
        }
    }

    public boolean contains(String item) {
        BagItem bi = getItem(item);
        return (bi != null);
    }

    public Integer remove(String item) {
        BagItem bi = getItem(item);
        if (bi != null) {
            data.remove(bi);
            return bi.getNum();
        }
        return null;
    }

    public boolean remove(String item, int num) {
        if (num <= 0) {
            throw new IllegalArgumentException();
        }
        BagItem bi = getItem(item);
        if (bi == null) {
            return false;
        }
        int left = bi.getNum() - num;
        if (left > 0) {
            bi.setNum(left);
        } else {
            remove(item);
        }
        return true;
    }

    public int howMany(String item) {
        BagItem bi = getItem(item);
        if (bi != null) {
            return bi.getNum();
        }
        return 0;
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void clear() {
        data.clear();
    }

    public ArrayList<String> items() {
        ArrayList<String> items = new ArrayList<>();
        for (BagItem bi : data) {
            items.add(bi.getItem());
        }
        return items;
    }

    public Bag union(Bag bag) {
        Bag result = new Bag();
        for (BagItem bi : bag.data) {
            result.add(bi.getItem(), bi.getNum());
        }
        for (BagItem bi : data) {
            result.add(bi.getItem(), bi.getNum());
        }
        return result;
    }

    // Implement intersection and difference here
    public Bag intersection(Bag bag) {
        Bag result = new Bag();
        for (BagItem bi1 : bag.data) {
            for (BagItem bi2 : data) {
                if (bi1.getItem().equals(bi2.getItem())) {
                    if (bi1.getNum() > bi2.getNum()) {
                        result.add(bi2.getItem(), bi2.getNum());
                    }
                    else {
                        result.add(bi1.getItem(), bi1.getNum());
                    }
                }
            }
        }
        return result;
    }

    public Bag difference(Bag bag) {
        /*
        Bag intersection = this.intersection(bag);
        --
        so uh
        Bag intersection = new Bag();
        for (BagItem bi1 : bag.data) {
            for (BagItem bi2 : data) {
                if (bi1.getItem().equals(bi2.getItem())) {
                    intersection.add(bi1.getItem(), bi1.getNum());
                    intersection.add(bi2.getItem(), bi2.getNum());
                }
            }
        }
        --
        Bag result = new Bag();
        for (BagItem bi1 : bag.data) {
            if (intersection.getItem(bi1.getItem()) == null) {
                result.add(bi1.getItem(), bi1.getNum());
            }
        }
        for (BagItem bi2 : data) {
            if (intersection.getItem(bi2.getItem()) == null) {
                result.add(bi2.getItem(), bi2.getNum());
            }
        }
        return result;
        */
        Bag result = this;
        for (BagItem b : bag.data) {
            if (result.getItem(b.getItem()) != null) {
                result.remove(b.getItem());
            }
            else {
                result.add(b.getItem(), b.getNum());
            }
        }
        return result;
    }

    private BagItem getItem(String item) {
        for (BagItem bi : this.data) {
            if (item.equals(bi.getItem())) {
                return bi;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Bag{" + "data=" + data + '}';
    }
}