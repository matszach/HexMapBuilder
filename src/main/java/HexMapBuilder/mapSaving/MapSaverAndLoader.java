package HexMapBuilder.mapSaving;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class MapSaverAndLoader {

    public static void save(MapSerializable ms, String fileName){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName + ".map"))){
           out.writeObject(ms);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static MapSerializable load(String fileName){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName + ".map"))){
            return (MapSerializable)in.readObject();
        } catch (IOException e){
            System.out.println(e.getMessage());
            return null;
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
            return null;
        }

    }
}






