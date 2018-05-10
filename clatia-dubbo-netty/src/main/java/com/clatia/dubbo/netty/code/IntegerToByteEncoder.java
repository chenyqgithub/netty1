package com.clatia.dubbo.netty.code;

/**
 * Created by admin on 2018/1/25.
 */
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class IntegerToByteEncoder extends MessageToByteEncoder<Integer> {
    @Override
    public void encode(ChannelHandlerContext ctx, Integer msg, ByteBuf out)
            throws Exception {
        System.err.println("IntegerToByteEncoder encode msg is " + msg);
        out.writeInt(msg);
    }
}
