package test;

import classes.people;
import java.util.ArrayList;
import java.util.Scanner;

public class test {

    public static void main(String args[]){
        StringBuilder ax = new StringBuilder("asdasd");
        ax.setCharAt(0,'1');
        System.out.println(ax);
        System.out.println();

        // array can store any type
        ArrayList array = new ArrayList();
        array.add(1);
        array.add("bob");
        array.add(0, ax);
        System.out.println(array.get(0));
        System.out.println();

        // array can be designated to store a specific type
        ArrayList<people> apeople = new ArrayList<>();
        apeople.add(0,new people(1));
        apeople.add(new people(2));
        apeople.get(0).shout();
        for (int i = 0; i < apeople.size(); i++) {
            System.out.println(apeople.get(i));
        }
        System.out.println();

        //ArrayList<int> arrayint = new ArrayList<int>();  Type argument cannot be of primitive type
        ArrayList<Integer> arrayInt = new ArrayList<>();


        // iterating
        for(int i =0;i<array.size();i++){
            System.out.println(array.get(i));
        }

        //remove
        System.out.println(array);
        array.remove(0);   // return the removed item
        System.out.println(array);
        array.remove("bob");  //return true if remove successful
        System.out.println(array);
        System.out.println();

        //set
        array.set(0,"bojun");
        System.out.println(array);

    }
}
