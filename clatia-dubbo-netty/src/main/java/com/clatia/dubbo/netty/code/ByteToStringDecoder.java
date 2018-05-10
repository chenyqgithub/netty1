package com.clatia.dubbo.netty.code;

import com.clatia.dubbo.netty.service.HelloServer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by admin on 2018/1/25.
 */
public class ByteToStringDecoder extends ByteToMessageDecoder {
    private static final Logger logger = LoggerFactory.getLogger(ByteToStringDecoder.class);

    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println(byteBuf);
        int capacity = byteBuf.capacity();
        int i = byteBuf.readableBytes();
        if (capacity == i) {return;
//            byte[] req = new byte[byteBuf.readableBytes()];
//            byteBuf.readBytes(req);
//            String body = new String(req, "UTF-8");
//            System.out.println(body);
//            if(body.indexOf("[end]")==-1){
//                return;
//            }
        }
        float v = byteBuf.readFloat();
        System.out.println(v);
        if (byteBuf.readableBytes() >= 4) {  // Check if there are at least 4 bytes readable
            int anInt = byteBuf.readInt();
            byte[] req = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(req);
            String body = new String(req, "UTF-8");
            logger.info("解码 包头长度：" + anInt + " 消息体内容：" + body);
            list.add(body);      //Read integer from inbound ByteBuf, add to the List of decodec messages
        }
    }

}
