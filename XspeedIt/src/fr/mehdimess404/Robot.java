package fr.mehdimess404;

import java.util.*;

/**
 * Created by Mehdimess404 on 7/10/2017.
 */
public class Robot {

   private ArrayList<Package> packages; //The list of packages used by the robot
   private ArrayList<Integer> chain;    //The list of products waiting to be packed

   private ArrayList<Package> bestPackagesContent; //The backup of the last optimal package solution with its content.
   private int bestPackages; //The number of packages used for the optimal solution
   private int currentPackages; //The current number of package used.

   public Robot(String chain){
       bestPackages = Integer.MAX_VALUE;
       currentPackages = 0;
       packages = new ArrayList<>();
       bestPackagesContent = new ArrayList<>();
       this.chain = new ArrayList<>();

       // We parse the String in order to convert it into an list of integers.
       for(char c : chain.toCharArray()){
            this.chain.add(Character.getNumericValue(c));
       }
   }

   public void treatChain(){
       treatProduct(0);
       // We'll iterate over the backup of the best configuration of packages in order to display the content of each package and the total number of them.
       for(int i=0; i<bestPackagesContent.size()-1; i++){
           bestPackagesContent.get(i).print();
           System.out.print("/");
       }
       bestPackagesContent.get(bestPackagesContent.size()-1).print();
       System.out.println(" => " + bestPackages + " packages used");
   }

   /*
    Here is the recursive method treating each product one after the other.
    */
    private void treatProduct(Integer i) {
        // The outgoing condition when we reached the last product of the list.
       if(i>=chain.size()){
           // If the number of packages used in this configuration is better than the previous best one, we save it.
           if(currentPackages<bestPackages){
               bestPackages = currentPackages;
               bestPackagesContent = cloneList(packages);
           }
       }
       else{
           int product = chain.get(i);
           /*
            * We parse all the packages of the list and add the product into them if it can fit inside according to the weight limit.
            * Then we recursively call the same method on the next product.
           */
           for(int j=0; j<packages.size(); j++){
               Package p = packages.get(j);
               if(p.canFit(product)){
                   p.addContent(product);
                   treatProduct(i+1);
                   p.removeContent(product);
               }
           }

           //The product doesn't fit anywhere so we create a new Package and we'll recursively call the method on the next one.
           Package newPackage = new Package();
           packages.add(newPackage);
           currentPackages++;
           newPackage.addContent(product);
           treatProduct(i+1);
           newPackage.removeContent(product);
           packages.remove(packages.indexOf(newPackage));
       }
    }

    /*
     * Method in charge of properly copying an ArrayList of Packages with its content.
     */
    private ArrayList<Package> cloneList(ArrayList<Package> packages) {
        List<Package> clone = new ArrayList<>();
        for(Package p : packages){
            clone.add(new Package(p));
        }
        return (ArrayList<Package>) clone;
    }

}
