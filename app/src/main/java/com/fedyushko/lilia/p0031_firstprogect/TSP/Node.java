package com.fedyushko.lilia.p0031_firstprogect.TSP;

/**
 * Created by Lilia on 29.05.2017.
 */

public class Node {

    public Node parent;
    private float parent_cost;

    private float[][] distances;
    private int[] active_set;//ножина всіх точок враховуючи цю що рахуються зараз

    public int index;

    /**
     * Constructs a new Node
     *
      parent This node's parent
      parent_cost The cost between these nodes якими цими між батьком і цим?
      distances The 2D array of distance between locations
      active_set The set of all points (including this node) that are being calculated
      index The location index of this node
     */
    public Node(Node parent, float parent_cost, float[][] distances, int[] active_set, int index) {
        this.parent = parent;
        this.parent_cost = parent_cost;
        this.distances = distances;
        this.active_set = active_set;
        this.index = index;
    }

    /**
     * Check if this node is terminal
     Whether or not the node is terminal
     */
    public boolean isTerminal() { //перевіряє чи вузол кінцевий
        return active_set.length == 1;//якщо залишилось порахувати тільки цю точку
    }

    /**
     * Generate and return this node's children
     *
     * @precondition Node is not terminal
     * @return Array of children
     */
    public Node[] generateChildren() {
        Node[] children = new Node[active_set.length - 1];//створюється масив вузлів-дітей розміром active_set.length - 1 ?
        int[] new_set = new int[active_set.length - 1];// і такий масив інт такого самого розміру
        int i = 0;
        for(int location : active_set) {// йде по всіх містах і якщо індекс вузла == поточному місту то переходить на
            if(location == index)// наступну ітерацію а якщо ні -- то вони записуються в new_set
                continue;		// тобто в new_set вписуються всі міста окрім поточного

            new_set[i] = location;
            i++;
        }
        // діти записуються а масив вузлів children їхнім parent є не даний ноут parent  не вони самі
        for(int j = 0; j < children.length; j++)// а хто? оцей об'єкт для якого викликається метод generateChildren()
            children[j] = new Node(this, distances[index][new_set[j]], distances, new_set, new_set[j]);

        return children;
    }

    /**
     * Get the path array up to this point
     *
     * @return The path
     */
    public int[] getPath() {
        int depth = distances.length - active_set.length + 1; //кількість міст - активна множина(кількість дітей )+1
        int[] path = new int[depth];
        getPathIndex(path, depth - 1);//запускаємо рекурсивний пошук шляху для цієї глибини ^  всі - ті що рахуються+1
        //	|
        return path;
    }

    /**
     * Recursive method to fill in a path array from this point
     *
     * @param path The path array
     * @param i The index of this node
     *///рекурсивний метод масиву що заповнює шлях з цієї точки
    public void getPathIndex(int[] path, int i) {
        path[i] = index; //поточний вузол стає ітим елеменом на шляху)
        if(parent != null)// якщо це не корінь то
            parent.getPathIndex(path, i - 1); //попереднім елементом на шляху стає його parent
    }

    /**
     * Get the lower bound cost of this node
     *
     * @return Lower bound cost
     */
    public float getLowerBound() {
        float value = 0;

        if(active_set.length == 2)//якщо залишилось 2 точки не опрацьовані то повертає цю відстань
            return getPathCost() + distances[active_set[0]][active_set[1]]; //  +відстань між останніми двома

        for(int location : active_set) {// де по всіх містах серед тих що залишились не калькульовані
            float low1 = Float.MAX_VALUE;
            float low2 = Float.MAX_VALUE;

            for(int other: active_set) {// ще раз по всіх наявних
                if(other == location)// якщо рівні пропускає
                    continue;

                float cost = distances[location][other];// інакше присвоюється відстань між 2 точками
                if(cost < low1) {// якщо вона менша, а вона менша
                    low2 = low1;//то другій -- перша, а першій --ця(відстань)
                    low1 = cost;
                }
                else if(cost < low2) {//і якщо після цього друга менша за кост
                    low2 = cost;// то їй теж присвоєюється кост
                }
            }

            value += low1 + low2;// value одаюсься 1 і 2
        }

        return getParentCost() + value / 2;// а потім діляться на 2 wtf
    }

    /**
     * Get the cost of the entire path up to this point
     *
     * @return Cost of path including return
     */
    public float getPathCost() {//відстань між нульовим і поточним + відстань для його батьківського вузла
        return distances[0][index] + getParentCost();
    }

    /**
     * Get the cost up to the parent at this point
     *
     * @return Cost of path
     */
    public float getParentCost() {
        if(parent == null)//якщо це корінь то 0
            return 0;

        return parent_cost + parent.getParentCost();//якщо ні -- то parent_cost(0)+ рекурсивно відстнь для parent
    }
}
