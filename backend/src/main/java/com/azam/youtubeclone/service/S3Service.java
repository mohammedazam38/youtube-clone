package com.azam.youtubeclone.service;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;
@RequiredArgsConstructor
@Service
public class S3Service implements FileService{

    public static final String ZAMBUC = "zambuc";
    private final AmazonS3Client amazonS3Client;



    @Override
    public String uploadFile(MultipartFile file){
     //upload file to AWS S3

        //prepare a Key
        var filenameExtension= StringUtils.getFilenameExtension(file.getOriginalFilename()) ;
        var key= UUID.randomUUID().toString()+"."+filenameExtension;
        var metaData= new ObjectMetadata();
        metaData.setContentLength(file.getSize());
        metaData.setContentType(file.getContentType());
        try{
            amazonS3Client.putObject(ZAMBUC,key,file.getInputStream(),metaData);

        }
        catch (IOException ioException){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"An Exeception occured while uploading the video");
        }
        amazonS3Client.setObjectAcl(ZAMBUC,key, CannedAccessControlList.PublicRead);
        return amazonS3Client.getResourceUrl(ZAMBUC,key);

    }
}
