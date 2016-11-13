package data.generator;

import data.config.GeneratorConfiguration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by amitkumar on 12/11/16.
 */
public class GeneratorJob {

    public static void main(String[] args) throws Exception {
        int numberOfMessagesToGenerate = 100;
        if (args.length > 0) {
            numberOfMessagesToGenerate = Integer.parseInt(args[0]);
        }
        else {
            throw new Exception(" Please provid number of messages in command line");
        }
        try {
            GeneratorConfiguration configuration = new GeneratorConfiguration();
            final int threadPoolSize = configuration.getThreadpoolsize();
            System.out.println("data generator pool size " + threadPoolSize);
            ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
            for (int i = 0; i < numberOfMessagesToGenerate; i++) {
                executor.execute(new DataGenerator(i, configuration));
            }
            executor.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
