import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
  
class Preprocessing{

  //String dataPath = "/home/runner/HilariousHotElements/data/";
  String dataPath = "/home/runner/HilariousHotElements/data/test/";
  HashSet<String> stopwords = loadStopwords();
  
  ArrayList<DataFile> data = removeStopWords(readAllData(dataPath));
  
  public HashSet<String> loadStopwords(){
    List<String> stopwords = new ArrayList<String>();
    HashSet<String> stopwordsSet = new HashSet<String>();
    try{
      stopwords = Files.readAllLines(Paths.get("English-stopwords.txt"));
      stopwordsSet.addAll(stopwords);
    } catch(Exception e){
      System.out.println(e);
      }
    System.out.println(stopwordsSet);
    return stopwordsSet;
  }

  public ArrayList<DataFile> readAllData(String dataPath){ //ArrayList<DataFile>
    ArrayList<DataFile> dataFiles = new ArrayList<DataFile>();
    try{
      dataFiles = new ArrayList<DataFile>( Files.walk(Paths.get(dataPath))
                                  .filter(Files::isRegularFile)
                                  //.forEach(System.out::println));
                                  .map(s -> new DataFile(s.toAbsolutePath().toString()))
                                  .collect(Collectors.toList()));

      }catch(Exception e){
      System.out.println(e);
      }
    return dataFiles;
    }
  
  public ArrayList<DataFile> removeStopWords(ArrayList<DataFile> originalData){ //ArrayList<DataFile>
    
    for(DataFile df : originalData){
      List<String> builder = new ArrayList<String>();
      
      for(String line : df.fileContent){
        String[] allWords = line.toLowerCase().split(" ");

        for(String word : allWords) {
          String wordWithoutSymbol = removeSymbol(word);
          if(!stopwords.contains(wordWithoutSymbol)) {
              builder.add(wordWithoutSymbol);
              //builder.append(' ');
          }
        }
      }
    df.fileContent = builder;

    }
   return originalData;
  }

  private String removeSymbol(String word){
    StringBuilder result = new StringBuilder();
    
    //return word.chars().mapToObj(Character::toChars).filter(Character.isLetterOrDigit(character));

    
    for(char character:word.toCharArray()){
      if(Character.isLetterOrDigit(character)){
        result.append(character);
      }
    }
    return result.toString();
    
  }
}
  

