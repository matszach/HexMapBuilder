package HexMapBuilder.mapSaving;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class MapSaverAndLoader {

    public static void saveToDefaultDirectory(MapSerializable ms, String fileName){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("SavedMaps\\" + fileName + ".map"))){
           out.writeObject(ms);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void saveToFile(MapSerializable ms, File file){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){
            out.writeObject(ms);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static MapSerializable loadFromDefaultDirectory(String fileName){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("SavedMaps\\" + fileName + ".map"))){
            return (MapSerializable)in.readObject();
        } catch (IOException e){
            System.out.println(e.getMessage());
            return null;
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static MapSerializable loadFromFile(File file){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
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






