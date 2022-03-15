import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.StringUtils;

import java.util.*;

  
public class Preprocessing{

  //String dataPath = "/Users/mitali/eclipse-workspace/BDS-JAVA_Eclipse/src/data/test";
	String dataPath = "/Users/mitali/eclipse-workspace/BDS-JAVA_Eclipse/src/data/C1";
	HashSet<String> stopwords = loadStopwords();
  
	ArrayList<DataFile> data = removeStopWords(readAllData(dataPath));
	ArrayList<DataFile> lemmatizedData = lemmatizer(data);
   
  
  public HashSet<String> loadStopwords(){
    List<String> stopwords = new ArrayList<String>();
    HashSet<String> stopwordsSet = new HashSet<String>();
    try{
      stopwords = Files.readAllLines(Paths.get("/Users/mitali/eclipse-workspace/BDS-JAVA_Eclipse/src/English-stopwords.txt"));
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
  
  public static ArrayList<DataFile> lemmatizer(ArrayList<DataFile> originalData) {
	  
	  StanfordLemmatizer slem = new StanfordLemmatizer();
	  
	  //System.out.println(slem.lemmatize(text));
	  for(DataFile df : originalData){
		  List<String> builder = slem.lemmatize(StringUtils.join(df.fileContent," "));
		  df.fileContent = builder;
	  }
	  return originalData;
	  }
 
  
}