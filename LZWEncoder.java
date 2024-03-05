  
package Compresspro;

import java.util.*;
public class LZWEncoder{
    


    private HashMap<String, Integer> dictionary;
    private int nextCode;

    public LZWEncoder(){
        dictionary = new HashMap<>();
        for(int i = 0; i < 256; i++){
            dictionary.put(Character.toString((char) i), i);
            //System.out.println(Character.toString((char) i));
        }
        nextCode = 256;
    }

    public List<Integer> compress(String input){
        List<Integer> compressed = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        
        for(char c : input.toCharArray()){
            current.append(c);
            if(!dictionary.containsKey(current.toString())){
                dictionary.put(current.toString(), nextCode++);
                current.deleteCharAt(current.length() - 1);
                compressed.add(dictionary.get(current.toString()));
                current = new StringBuilder(Character.toString(c));
            }
        }
        compressed.add(dictionary.get(current.toString()));
        return compressed;
    }
}


