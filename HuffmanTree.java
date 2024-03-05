
package Compresspro;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class HuffmanTree{
     private HuffmanNode root;

    public HuffmanTree(Map<Character, Integer> frequencyMap){
        buildTree(frequencyMap);
    }

    private void buildTree(Map<Character, Integer> frequencyMap){
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();

        for(Map.Entry<Character, Integer> entry : frequencyMap.entrySet()){
            priorityQueue.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while(priorityQueue.size() > 1){
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            HuffmanNode newNode = new HuffmanNode('\0', left.frequency + right.frequency);
            newNode.left = left;
            newNode.right = right;
            
            priorityQueue.offer(newNode);
        }

        root = priorityQueue.poll();
    }

    public Map<Character, String> buildEncodingMap(){
        Map<Character, String> encodingMap = new HashMap<>();
        buildEncodingMap(root, "", encodingMap);
        return encodingMap;
    }

    private void buildEncodingMap(HuffmanNode node, String path, Map<Character, String> encodingMap){
        if (node.isLeaf()){
            encodingMap.put(node.data, path);
        } else{
            buildEncodingMap(node.left, path + "0", encodingMap);
            buildEncodingMap(node.right, path + "1", encodingMap);
        }
    }

    public String encode(String input, Map<Character, String> encodingMap){
        StringBuilder encoded = new StringBuilder();
        for(char c : input.toCharArray()){
            encoded.append(encodingMap.get(c));
        }
        return encoded.toString();
    }
   
    //decode
    public String decode(String encoded){
        StringBuilder decoded = new StringBuilder();
        HuffmanNode current = root;
        for(char bit : encoded.toCharArray()){
            if(bit == '0'){
                current = current.left;
            } else 
            {
                current = current.right;
            }

            if(current.isLeaf()){
                decoded.append(current.data);
                current = root;
            }
        }
        return decoded.toString();
    }

}
