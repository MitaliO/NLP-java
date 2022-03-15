import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.*;

class DataFile{

  String fileName;
  List<String> fileContent;

  DataFile(String inputFileName){
    fileName = inputFileName;
    fileContent = readContent(fileName);
  }

  public List<String> readContent(String file){
    List<String> lines = Collections.emptyList();
    try
    {
      lines =
       Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
    }
 
    catch (IOException e)
    {
      // do something
      e.printStackTrace();
    }
    System.out.println(String.format("Successfully read file %s and loaded contents!",fileName));
    return lines;
    
  }

  
}