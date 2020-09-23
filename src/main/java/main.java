import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.nio.file.*;
import java.nio.file.attribute.FileTime;

import static java.nio.file.StandardWatchEventKinds.*;


public class main {

    public static void main(String[] args) {
        System.out.println("Hello World!"); // Display the string.


        try {

            File save = new File ("save.txt");

            if (save.createNewFile()) {
                System.out.println("File created: " + save.getName());
            } else {
                System.out.println("File "+ save.getName() +" already exists.");
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try{
            WatchService watcher = FileSystems.getDefault().newWatchService();




        Path dir = Paths.get("./");

            dir.register(watcher,
                    ENTRY_CREATE,
                    ENTRY_DELETE,
                    ENTRY_MODIFY
                    );


            WatchKey key;

            while ((key = watcher.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    if(event.kind().equals(ENTRY_MODIFY)&&event.context().toString().equals("test.txt")){
                        System.out.println("Zmiana pliku");
                    }
                    System.out.println(
                            "Event kind:" + event.kind()
                                    + ". File affected: " + event.context() + ".");
                }
                key.reset();
            }
           /* for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                if (kind == OVERFLOW) {
                    continue;
                }
                if(kind.equals(ENTRY_MODIFY)){
                    System.out.println("ENTRY_MODIFY");
                }
                if(kind.equals(ENTRY_CREATE)){
                    System.out.println("ENTRY_CREATE");
                }
                if(kind.equals(ENTRY_DELETE)){
                    System.out.println("ENTRY_DELETE");
                }
            }/**/

        } catch (IOException e) {
            System.out.println("An watcher error occurred.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }





    }
}
