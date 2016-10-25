package rxjava.colorchen.com.fragment.tab4.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import rxjava.colorchen.com.App;
import rxjava.colorchen.com.model.Item;

/**
 * Created by color on 24/10/2016 12:14.
 */

public class ImageDataBase {
    private static String DATA_FILE_NAME = "data.db";
    private static ImageDataBase INSTANCE;

    File dataFile = new File(App.getInstance().getFilesDir(), DATA_FILE_NAME);
    Gson gson = new Gson();

    private ImageDataBase() {
    }

    public static ImageDataBase getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ImageDataBase();
        }
        return INSTANCE;
    }

    public List<Item> readItems() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Reader reader = new FileReader(dataFile);
            return gson.fromJson(reader, new TypeToken<List<Item>>() {
            }.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeItems(List<Item> items) {
        String json = gson.toJson(items);
        try {
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
            Writer writer = new FileWriter(dataFile);
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteData() {
        dataFile.delete();
    }
}
