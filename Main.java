
package Compresspro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        int continues=1;
        
        while(continues==1){
            int choose;
             System.out.println("Choose algorithm \n Press '1' for Huffman Coding \n Press '2' for LZW \n ");
             Scanner cs=new Scanner(System.in);
             choose=cs.nextInt();
             
      if(choose!=1 && choose !=2){
          System.out.println("Enter correct choose");
          choose=cs.nextInt();
        }
      
      //Huffman start
      if(choose==1){
          int nextChoose;
          System.out.println("Press '1' for encode \n Press '2' for decode \n");
          nextChoose=cs.nextInt();
          
          String input = null;
        try{
            File file=new File("D:/semister_5/OriginalFile.text");
            Scanner inputs=new Scanner(file);
            while(inputs.hasNext()){
                 input=inputs.nextLine();
            }
        }catch(Exception e){
            System.out.println("error");
        }
        
        Map<Character, Integer> frequencyMap = new HashMap<>();
                for(char c : input.toCharArray()){
                    frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
                     }

            HuffmanTree huffmanTree = new HuffmanTree(frequencyMap);
        
        //encode start
         if(nextChoose==1){
             
             
            Map<Character, String> encodingMap = huffmanTree.buildEncodingMap();

            String encoded = huffmanTree.encode(input, encodingMap);
            System.out.println("Encoded: " + encoded);
             
         }
         
         //huffman decode
         else
         {
             
             try{
            File file=new File("D:/semister_5/encodedfile.txt");
            Scanner inputs=new Scanner(file);
            while(inputs.hasNext()){
                 input=inputs.nextLine();
            }
        }catch(Exception e){
            System.out.println("error");
        }
             
             HuffmanTree huffmanTree2 = new HuffmanTree(frequencyMap);
             String decoded = huffmanTree.decode(input);
             System.out.println("Decoded: " + decoded);
             System.out.println();
              System.out.println("Choose algorithm \n Press '1' for Huffman Coding \n Press '2' for LZW \n ");
             
      }
     }
       

        
      
        
      //Lzw
       else
      {
          int nextChoose;
          System.out.println("Press '1' for encode \n Press '2' for decode \n");
          nextChoose=cs.nextInt();
          String inputs = null;
          if(nextChoose==1){
              try{
                    
                    File file=new File("D:/semister_5/OriginalFile.text");
                    Scanner input=new Scanner(file);
                    while(input.hasNext()){
                    inputs=input.nextLine();
                 }
                }catch(Exception e){
                    System.out.println("error");
                 }

       LZWEncoder encoder = new LZWEncoder();
       List<Integer> compressed = encoder.compress(inputs);
       System.out.println("Compressed: " + compressed);
     }
           
        else
          {

              String fileName = "D:/semister_5/spl _ 1/lzwEncoded.txt";
        List<Integer> inputList = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            if(line != null){
                String[] values = line.split(",\\s*"); // Split by comma and optional spaces

                for(String value : values){
                    int intValue = Integer.parseInt(value.trim());
                    inputList.add(intValue);
                }
            }

            reader.close();
              }
              catch(Exception e){
                    System.out.println("error");
                 }
                        
                        LZWDecoder decoder = new LZWDecoder();
                         String decompressed = decoder.decompress(inputList);
                         System.out.println("Decompressed: " + decompressed);
                  }
              }
                
        }
        
        System.out.println("Enter '1' to continue and Enter '0' for end");
        Scanner value =new Scanner(System.in);
         continues= value.nextInt();
         
         continues=0;
      }
          
      }
        
    
    

    

