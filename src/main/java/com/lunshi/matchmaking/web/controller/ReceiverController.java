package com.lunshi.matchmaking.web.controller;

import com.common.util.RPCResult;
import com.common.web.IExecute;
import com.lunshi.matchmaking.domain.ItemInfo;
import com.lunshi.matchmaking.domain.module.ItemTypeEnum;
import com.lunshi.matchmaking.service.ReceiverService;
import com.lunshi.matchmaking.web.AbstractClientController;
import com.lunshi.matchmaking.web.controller.dto.ItemInfoDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/receiver")
public class ReceiverController extends AbstractClientController {


    @Resource
    private ReceiverService receiverService;


    /**
     * 发布撮合
     * @param dto
     * @return
     */
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
                item.setTotalAmount(dto.getTotalAmount());
                receiverService.addItem(item);
                return null;
            }
        });
    }

    /**
     * 取消撮合
     * @param refId
     * @param refType
     * @param type
     * @return
     */
    @RequestMapping("cancle")
    public RPCResult<Boolean>cancle(Long refId, String refType, ItemTypeEnum type){
        return buildRPCMessage(new IExecute() {
            @Override
            public Object getData() {
                receiverService.cancle(refId,refType,type);
                return null;
            }
        });
    }
}
