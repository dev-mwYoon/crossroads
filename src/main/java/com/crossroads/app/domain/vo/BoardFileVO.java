package com.crossroads.app.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardFileVO {
    private Long fileId;
    private String fileOriginalName;
    private String fileUuid;
    private String filePath;
    private String fileSize;
    private Long boardId;

}
