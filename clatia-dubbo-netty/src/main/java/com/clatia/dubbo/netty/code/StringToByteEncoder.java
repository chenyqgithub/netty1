package com.clatia.dubbo.netty.code;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by admin on 2018/1/25.
 */
public class StringToByteEncoder extends MessageToByteEncoder<String> {
    protected void encode(ChannelHandlerContext channelHandlerContext, String s, ByteBuf byteBuf) throws Exception {
        byte[] bytes = (s+"[end]").getBytes("utf-8");
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }
}
