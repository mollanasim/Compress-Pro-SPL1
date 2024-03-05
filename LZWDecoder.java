
package Compresspro;

import java.util.HashMap;
import java.util.List;


public class LZWDecoder{
     private HashMap<Integer, String> dictionary;
    private int nextCode;

    public LZWDecoder(){
        dictionary = new HashMap<>();
        for(int i = 0; i < 256; i++) {
            dictionary.put(i, Character.toString((char) i));
        }
        nextCode = 256;
    }

    public String decompress(List<Integer> compressed){
        StringBuilder decompressed = new StringBuilder();
        String prev = dictionary.get(compressed.get(0));
        decompressed.append(prev);

        for(int i = 1; i < compressed.size(); i++){
            int code = compressed.get(i);
            String entry;
            if(dictionary.containsKey(code)){
                entry = dictionary.get(code);
            } else if(code == nextCode){
                entry = prev + prev.charAt(0);
            } else{
                throw new IllegalArgumentException("Corrupted compressed data.");
            }
            
            decompressed.append(entry);
            dictionary.put(nextCode++, prev + entry.charAt(0));
            prev = entry;
        }
        
        return decompressed.toString();
        
    }
}
 