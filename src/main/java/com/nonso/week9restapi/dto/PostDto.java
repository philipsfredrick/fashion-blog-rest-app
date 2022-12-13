package com.nonso.week9restapi.dto;

import com.nonso.week9restapi.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "PostDto", description = "Post model information")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    @ApiModelProperty(value = "Blog post title")
    private String title;

    @ApiModelProperty(value = "Blog post content")
    private String description;

    @ApiModelProperty(value = "Blog post image")
    private String featuredImage;

    @ApiModelProperty(value = "Blog post author id")
    private Long user_id;

}
