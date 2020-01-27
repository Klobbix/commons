package com.klobbix.network.client.impl.time;

import com.klobbix.network.client.AbstractClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient extends AbstractClient {

    public TimeClient(int port) {
        super(port);
    }

    public TimeClient(String host, int port) {
        super(host, port);
    }

    @Override
    public void connect() throws Exception {
        try {
            Bootstrap b = new Bootstrap();
            b.group(getWorkerGroup());
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast(new TimeDecoder(), new TimeClientHandler());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(getHost(), getPort()).sync();

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
           shutdown();
        }
    }
}
