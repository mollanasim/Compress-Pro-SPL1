
package Compresspro;


public class HuffmanNode implements Comparable<HuffmanNode>{
    char data;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(char data, int frequency){
        this.data = data;
        this.frequency = frequency;
    }

    public boolean isLeaf(){
        return left == null && right == null;
    }

    @Override
    public int compareTo(HuffmanNode other){
        return this.frequency - other.frequency;
    }
}

