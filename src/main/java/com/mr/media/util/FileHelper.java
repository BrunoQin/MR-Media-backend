package com.mr.media.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;


/**
 * Created by tanjingru on 11/01/2017.
 */
public class FileHelper {
    public static Boolean saveFile(String absolutePath, byte[] fileContent){
        try{
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(absolutePath)));
            stream.write(fileContent);
            stream.close();
        }
        catch (IOException e){
            return false;
        }
        return true;
    }

    public static String generateName(String filename, String prefix){
        UUID uuid = UUID.randomUUID();
        return prefix + uuid.toString() +filename;
    }

}
