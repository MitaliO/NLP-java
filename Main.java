import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");

    /*
try{    Files.walk(Paths.get("/home/runner/HilariousHotElements/data/"))
      .filter(Files::isRegularFile)
      .forEach(System.out::println);
    
  } catch(Exception e){
  System.out.println(e);
  }
    */

    Preprocessing test = new Preprocessing();
    System.out.println(test.data.get(0).fileContent);
}
}