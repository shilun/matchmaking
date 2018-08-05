package com.lunshi.matchmaking.web;

import com.common.web.AbstractController;
import org.apache.log4j.Logger;

import javax.annotation.Resource;

/**
 * Created by shilun on 2017/5/12.
 */
public abstract class AbstractClientController extends AbstractController {

    @Resource
    private static final Logger LOGGER = Logger.getLogger(AbstractClientController.class);


}
