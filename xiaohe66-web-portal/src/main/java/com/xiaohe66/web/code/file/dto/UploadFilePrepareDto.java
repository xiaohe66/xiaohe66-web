package com.xiaohe66.web.code.file.dto;

import com.xiaohe66.web.base.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * @author xiaohe
 * @time 2019.10.25 10:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UploadFilePrepareDto extends BaseDto {

    private Integer fileId;

    private Integer maxMbChunkPer;

    private Integer countChunk;

    private Set<Integer> missingChunk;

}
