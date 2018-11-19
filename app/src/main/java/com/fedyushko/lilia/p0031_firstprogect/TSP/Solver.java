package com.fedyushko.lilia.p0031_firstprogect.TSP;


import android.util.Log;

import com.fedyushko.lilia.p0031_firstprogect.Place;

import java.util.ArrayList;
import java.util.HashSet;

public class Solver {

    float[][] distances; //масив відстаней між містами
    float best_cost;
    int[] best_path;
    private static final String TAG = "myLogs";
    /**
     * Constructs a new Solver and initializes distances array
     *
     * @param places An ArrayList of Place's
     */
    public Solver(ArrayList<Place> places) { // в конструктор передається список міст і відстані між ними присвоюються в масив distances
        distances = new float[places.size()][places.size()];
        for(int i = 0; i < places.size(); i++) {
            for(int ii = 0; ii < places.size(); ii++)
                // метод distance визначений в класі Point2D повертає double тому переобразування типу в (float)
                distances[i][ii] = (float) places.get(i).distance(places.get(ii)); //відстань distance від міста(і) до міста(іі)
        }
    }

    /**
     * Calculates the shortest (non-repeating) path between a series of nodes
     *
     * @return An array with the locations of the best path
     */
    public int[] calculate() { // створюється множина Integer розміром places.size() тобто кількість міст в списку
        HashSet<Integer> location_set = new HashSet<Integer>(distances.length);
        for(int i = 0; i < distances.length; i++){
            location_set.add(i); // множині присвоюються номери від 0 до кількість міст-1
            //System.out.println(i);
        }
        best_cost = findGreedyCost(0, location_set, distances);// починаємовід нульового і шукаємо жадібну відстань
        int[] active_set = new int[distances.length]; //довжина -- кількість міст
        for(int i = 0; i < active_set.length; i++)
            active_set[i] = i; // заповнюється номерами від 0 до кількість міст-1
        Node root = new Node(null, 0, distances, active_set, 0);
        // створює короневий вузол в якого немає parent, відстань до parent - 0 йому задається індекс 0
        traverse(root);
        return best_path;
    }

    /**
     * Get current path cost
     *
     * @return The cost
     */
    public float getCost() {
        return best_cost;
    }

    /**
     * Find the greedy cost for a set of locations
     *
     * @param i The current location
     * @param location_set Set of all remaining locations
     * @param distances The 2D array containing point distances
     * @return The greedy cost
     */
    //жадібна вартість
    private float findGreedyCost(int i, HashSet<Integer> location_set, float[][] distances) {
        if(location_set.isEmpty())//якщо множина пуста повертає і-тий елемент з першого рядка
            return distances[0][i];

        location_set.remove(i);// видаляється і-тий елемент з множини

        float lowest = Float.MAX_VALUE;
        int closest = 0;
        for(int location : location_set) {// для кожного номеру з множини змінній cost передається відстань між вказаним
            float cost = distances[i][location];// в аргументах елементом i і поточним, якщо вона менша за найбільшу, а вона менша
            if(cost < lowest) {// то вона присвоюється lowest а closest присвоюється номер міста з множини
                lowest = cost;//так для і-того міста знаходиться найближче зі всіх location з location_set
                closest = location;
            }
        }// повертає це найближче розташування (найменшу відстань серед усіх) + викликається знов і шкається відстань
        //від міста найближчого до і до інших міст , отже в кінці повертає загальну відстань
        return lowest + findGreedyCost(closest, location_set, distances);
    }

    /**
     * Recursive method to go through the tree finding and pruning paths
     *
     * @param parent The root/parent node
     */
    private void traverse(Node parent) {//перешкода, подолання
        Node[] children = parent.generateChildren();// генеруються вузли-діти
        for(Node child : children) {// перебираються всі діти
            if(child.isTerminal()) {
                float cost = child.getPathCost();
                if(cost < best_cost) {
                    best_cost = cost;
                    best_path = child.getPath();
                }
            }

            else if(child.getLowerBound() <= best_cost) {// якщо це не кінцева дитина то перевіряється чи
                traverse(child); // перевіряються діти цієї дитини
                Log.d(TAG,"якщо це не кінцева дитина то перевіряється чи перевіряються діти цієї дитини");
            }
        }
    }
}
