package data.organiser;

import java.util.*;

public class Organiser<T> {
    private ArrayList<T> elem; // [T1, T2, ...]
    private ArrayList<Map.Entry<Integer, Integer>> swaps; // [(1,2), (5, ), ...]

    @SafeVarargs
    public Organiser(T... elems) { //varargs: előtte lehet más változó, utána nem
        elems = new ArrayList<>();
        for (T elem : elems) {
            elems.add(elem);
        }
        //elems = new ArrayList<>(Arrays.asList(elems));
        swaps = new ArrayList<>();
    } 

    /*
    new Organiser()
    new Organiser(5, 4, 3, 2, 7)
    new Organiser("alma")
    */

   public void addSwap(int from, int to) {
        swaps.add(Map.entry(from, to));
   }

   public ArrayList<T> get() {
        //return elems;
        return new ArrayList<>(elems);
   }

   public T get(int index) {
        return elems.get(index);
   }

   private void swap(int from, int to) {
        T tmp = elems.get(from);
        elems.set(from, elems.get(to));
        elems.set(to, tmp); 
   }

   public void runSwaps() {
        for ( Map.Entry<Integer, Integer> swap : swaps) {
            swap(swap.getKey(), swap.getValue);
        }
   }

    @Override //Felülírja a szülőben, de nem kötelező (java megoldja)
   public String toString() {
        //return super.toString();
        /*
        String result = "[";
        for (T elem : elems) {
            result += elem + " ";
        }
        result += "]";
        return result;
        */

        /*
        létrehoz stringet ami:
        "["
        "[5 "
        "[5 4 "
        "[5 4 ]"
        ez pazarlás 
        */

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (T elem : elems) {
            sb.append(elem);
            sb.append(" ");
        }
        sb.append("]");
        return sb.toString();
   }
}

//Organiser org = new Organiser(5, 8, 3, 4);
//System.out.println(org) => [5, 8, 3, 4]              itt org-ra meghívódik: toString() metódus (System.out.println(org.toString()))