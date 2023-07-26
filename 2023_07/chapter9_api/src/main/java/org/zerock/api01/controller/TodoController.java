package org.zerock.api01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.api01.dto.PageRequestDTO;
import org.zerock.api01.dto.PageResponseDTO;
import org.zerock.api01.dto.TodoDTO;
import org.zerock.api01.service.TodoService;
import org.zerock.api01.util.LocalUploader;
import org.zerock.api01.util.S3Uploader;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
@Log4j2
public class TodoController {

    private final LocalUploader localUploader;
    private final S3Uploader s3Uploader;
    private final TodoService todoService;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> register(TodoDTO todoRegisterDTO){

        log.info("todo register todo....");
        log.info(todoRegisterDTO);

        MultipartFile[] files = todoRegisterDTO.getFiles();

        if(files != null && files.length > 0){

            List<String> uploadFilesPath = new ArrayList<>();

            //local upload alll
            for (MultipartFile file:files) {
                log.info(files);
                uploadFilesPath.addAll(localUploader.uploadLocal(file));
            }

            //s3 upload
            List<String> s3UploadPaths = uploadFilesPath.stream().map(filePath -> s3Uploader.upload(filePath)).collect(Collectors.toList());

            log.info(s3UploadPaths);

            todoRegisterDTO.setS3FilePath(s3UploadPaths);

        }//end if

        Long tno = todoService.register(todoRegisterDTO);


        return Map.of("tno", tno);
    }

    @GetMapping(value = "/{tno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TodoDTO read(@PathVariable("tno") Long tno ){

        TodoDTO todoDTO = todoService.read(tno);

        return todoDTO;
    }


    @DeleteMapping(value = "/{tno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> remove(@PathVariable("tno") Long tno){


        Optional<List<String>> fileLinks = todoService.getFileLinks(tno);

        //데이터베이스 먼저 삭제
        todoService.remove(tno);

        //S3내 파일들 삭제
        if(fileLinks.isPresent()){
            fileLinks.get().forEach(fileLink -> {

                try {
                    String utfName = URLDecoder.decode(fileLink,"UTF-8");
                    log.info(utfName);
                    s3Uploader.removeS3File(utfName);
                }catch(Exception e){

                }

            });
        }


        return null;
    }


    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO){

        log.info("------------------------------------");
        log.info(pageRequestDTO);

        return todoService.getList(pageRequestDTO);
    }

    @PutMapping(value ="/{tno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> modify(@PathVariable("tno") Long tno, TodoDTO todoDTO){

        log.info("modify.........tno: " + tno);

        todoDTO.setTno(tno);

        Optional<List<String>> fileLinks = todoService.getFileLinks(tno);

        //S3내 파일들 삭제
        if(fileLinks.isPresent()){
            fileLinks.get().forEach(fileLink -> {

                try {
                    String utfName = URLDecoder.decode(fileLink,"UTF-8");
                    log.info(utfName);
                    s3Uploader.removeS3File(utfName);
                }catch(Exception e){
                    log.error(e.getMessage());
                }
            });
        }

        //수정된 새로운 파일들 추가
        MultipartFile[] files = todoDTO.getFiles();

        if(files != null && files.length > 0){

            List<String> uploadFilesPath = new ArrayList<>();

            //local upload alll
            for (MultipartFile file:files) {
                log.info(files);
                uploadFilesPath.addAll(localUploader.uploadLocal(file));
            }

            //s3 upload
            List<String> s3UploadPaths = uploadFilesPath.stream().map(filePath -> s3Uploader.upload(filePath)).collect(Collectors.toList());

            log.info(s3UploadPaths);

            todoDTO.setS3FilePath(s3UploadPaths);

        }//end if


        todoService.modify(todoDTO);


        return null;
    }

}














