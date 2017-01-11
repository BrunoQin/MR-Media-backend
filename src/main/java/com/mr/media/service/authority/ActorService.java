package com.mr.media.service.authority;

import com.mr.media.model.User;
import com.mr.media.response.BaseResp;
import com.mr.media.service.UserService;
import com.mr.media.util.FileHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by tanjingru on 11/01/2017.
 */
@Service
public class ActorService {
    @Autowired
    Environment environment;

    @Autowired
    UserService userService;

    @Autowired
    FileHelper fileHelper;

    public int UploadAvatar(String token, MultipartFile uploadFile){
        User user = userService.findUserByToken(token);
        String filePath = fileHelper.generateFilePath(uploadFile.getOriginalFilename(), user.getUid());
        try {

            Boolean result = fileHelper.saveFile(filePath, uploadFile.getBytes());
            if(result){
                return BaseResp.SUCCESS;
            }
            else {
                return BaseResp.UNKNOWN;
            }
        } catch (IOException e) {
            return BaseResp.UNKNOWN;
        }
    }
}
