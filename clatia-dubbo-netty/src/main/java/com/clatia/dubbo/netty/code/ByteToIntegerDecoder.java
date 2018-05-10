package com.clatia.dubbo.netty.code;

/**
 * Created by admin on 2018/1/25.
 */
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 把字节转换为int
 * 继承抽象类ByteToMessageDecoder实现解码器
 */
public class ByteToIntegerDecoder extends ByteToMessageDecoder {

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in,
                       List<Object> out) throws Exception {
        if (in.readableBytes() >= 4) {  // Check if there are at least 4 bytes readable
            int n = in.readInt();
            System.err.println("ByteToIntegerDecoder decode msg is " + n);
            out.add(n);      //Read integer from inbound ByteBuf, add to the List of decodec messages
        }
    }
}