package data.generator;

import data.config.GeneratorConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.Random;

/**
 * Created by amitkumar on 12/11/16.
 */
public class DataGenerator implements Runnable {

    private int id;
    private GeneratorConfiguration configuration;

    public DataGenerator(int id, GeneratorConfiguration configuration) {
        this.id = id;
        this.configuration = configuration;
    }

    public void run() {
        String[] dataFiles = configuration.getDataFiles();
        Random random = new Random();
        int fileIndex = random.nextInt(dataFiles.length);
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("datafiles/" + dataFiles[fileIndex]).getFile());
        try {
            byte[] byteArray = Files.readAllBytes(file.toPath());
            System.out.println("byte array size = " + byteArray.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("msg number : " + id + " :: Thread Name " + Thread.currentThread().getName());
    }
}
