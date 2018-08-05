package com.lunshi.matchmaking.domain.module;

import com.common.util.IGlossary;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 撮合类型
 */
@ApiModel(description = "撮合请求类型")
public enum ItemTypeEnum implements IGlossary {
    @ApiModelProperty(value = "卖")
    SELL("卖"),
    @ApiModelProperty(value = "买")
    BUY("买");

    ItemTypeEnum(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getValue() {
        return null;
    }
}
