package com.mr.media.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tanjingru on 06/01/2017.
 */
@Component
public class UidHelper {

    private static int IDLENGTH = 5;

    public String generateUid(int count, Integer authority){
        String[] auth_map = {"SUPER", "R1", "S1", "T1"};
        String generatedId = auth_map[authority];
        String countString = count + "";
        for(int i =0; i<IDLENGTH - countString.length() ;i++){
            generatedId += "0";
        }
        return generatedId + countString;
    }
}
