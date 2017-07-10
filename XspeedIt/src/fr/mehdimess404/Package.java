package fr.mehdimess404;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.util.ArrayList;

/**
 * Created by Mehdimess404 on 7/10/2017.
 *
 * The class representing the package. Each of them contains a list of content representing the products contained inside the package
 * and a weight corresponding to the total sum of all product's weight inside it.
 */
public class Package {

    private ArrayList<Integer> contents;
    private int weight;

    public Package(){
        contents = new ArrayList<>();
        weight = 0;
    }

    public Package(Package clone){
        this.contents = new ArrayList<>(clone.getContents());
        this.weight = clone.getWeight();
    }

    public void addContent(Integer content){
        contents.add(content);
        weight += content;
    }

    public int getWeight(){
        return weight;
    }

    public boolean canFit(int product){
        return product + weight <= Constants.WEIGHT_MAX_IN_PACKAGE;
    }

    public void removeContent(int product){
        contents.remove(contents.indexOf(product));
    }

    public void print(){
        for(int value : contents){
            System.out.print(value);
        }
    }

    public ArrayList<Integer> getContents() {
        return contents;
    }
}
