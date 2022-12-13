package com.nonso.week9restapi.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Api(value = "CommentDto", description = "Comment model information")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    @ApiModelProperty(value = "Comment content/body")
    private String comment;
}
