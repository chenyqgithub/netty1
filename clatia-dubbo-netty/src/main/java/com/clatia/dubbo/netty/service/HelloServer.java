package com.clatia.dubbo.netty.service;

import com.clatia.dubbo.netty.code.ByteToStringDecoder;
import com.clatia.dubbo.netty.code.StringToByteEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.*;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by admin on 2018/1/25.
 */
public class HelloServer {
    private static final Logger logger = LoggerFactory.getLogger(HelloServer.class);

    public void start(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024 * 4)
                    .option(ChannelOption.SO_BACKLOG, 1024 * 2)// 配置TCP参数
                    .option(ChannelOption.SO_BACKLOG, 1024 * 2) // 设置tcp缓冲区
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024) // 设置发送缓冲大小
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024) // 这是接收缓冲大小
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            // 注册handler
                            ch.pipeline().addLast(
                                    new StringToByteEncoder(),
                                    new ByteToStringDecoder(),
                                    new HelloServerInHandler()
                            ).addLast(new FixedLengthFrameDecoder(5))
                            ;
                        }
                    });
            ChannelFuture f = b.bind(port).sync();
            logger.info("netty启动成功 port({})", port);
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {

        HelloServer server = new HelloServer();
        server.start(8086);

    }
}
