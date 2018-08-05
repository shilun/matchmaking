package com.lunshi.matchmaking.web.controller;

import com.common.util.RPCResult;
import com.common.util.model.YesOrNoEnum;
import com.common.web.IExecute;
import com.lunshi.matchmaking.domain.ItemInfo;
import com.lunshi.matchmaking.domain.module.ItemTypeEnum;
import com.lunshi.matchmaking.service.ReceiverService;
import com.lunshi.matchmaking.web.AbstractClientController;
import com.lunshi.matchmaking.web.controller.dto.ItemInfoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "撮合")
@RestController
@RequestMapping(name = "/receiver", method = {RequestMethod.POST})
public class ReceiverController extends AbstractClientController {


    @Resource
    private ReceiverService receiverService;


    /**
     * 发布撮合
     *
     * @param dto
     * @return
     */
    @ApiOperation(value = "推送撮合")
    @RequestMapping("push")
    public RPCResult<Boolean> push(@RequestBody ItemInfoDto dto) {
        return buildRPCMessage(new IExecute() {
            @Override
            public Object getData() {
                ItemInfo item = new ItemInfo();
                item.setRefId(dto.getRefId());
                item.setAmount(dto.getAmount());
                item.setPrice(dto.getPrice());
                item.setRefType(dto.getRefType());
                item.setType(dto.getType().name());
                item.setTotalAmount(dto.getAmount());
                item.setStatus(YesOrNoEnum.NO.getValue());
                receiverService.addItem(item);
                return null;
            }
        });
    }

    /**
     * 取消撮合
     *
     * @param refId
     * @param refType
     * @param type
     * @return
     */
    @ApiOperation(value = "取消撮合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "refId", value = "业务标识", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "refType", value = "货币对类型", required = true, dataType = "String", example = "USDT_BTC"),
            @ApiImplicitParam(name = "type", value = "货币操作类型", required = true, dataType = "com.lunshi.matchmaking.domain.module.ItemTypeEnum")
    })
    @RequestMapping("cancle")
    public RPCResult<Boolean> cancle(@RequestParam("refId") Long refId, @RequestParam("refType") String refType, @RequestParam("type") ItemTypeEnum type) {
        return buildRPCMessage(new IExecute() {
            @Override
            public Object getData() {
                receiverService.cancle(refId, refType, type);
                return null;
            }
        });
    }
}
