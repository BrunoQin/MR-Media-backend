package com.mr.media.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;


/**
 * Created by tanjingru on 11/01/2017.
 */

@Component
public class FileHelper {

    @Value("${paths.uploadedFiles}")
    String uploadFilePath;

    public Boolean saveFile(String absolutePath, byte[] fileContent){
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

    public String generateFilePath(String filename, String prefix){
        UUID uuid = UUID.randomUUID();
        String generatedFilename = prefix + uuid.toString() +filename;
        return  Paths.get(uploadFilePath, generatedFilename).toString();

    }

}
