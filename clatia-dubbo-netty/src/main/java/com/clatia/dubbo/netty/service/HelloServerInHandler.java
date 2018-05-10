package com.clatia.dubbo.netty.service;

/**
 * Created by admin on 2018/1/25.
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 该handler是InboundHandler类型
public class HelloServerInHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(HelloServerInHandler.class);
    @Override
    public boolean isSharable() {
        logger.info("==============handler-sharable==============");
        return super.isSharable();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("==============channel-register==============");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("==============channel-unregister==============");
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("==============channel-active==============");

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("==============channel-inactive==============");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("==============channel-read==============");
        logger.info("服务器端接收到的客户端的数据是：" + msg +" 来自于 ："+ctx.channel().id());
        ctx.writeAndFlush("服务器接收到数据为（"+msg+"）");
        ctx.flush();
        ctx.channel().pipeline().writeAndFlush("sssssssssssssss");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.info("==============channel-read-complete==============");
        ctx.flush();
    }
}