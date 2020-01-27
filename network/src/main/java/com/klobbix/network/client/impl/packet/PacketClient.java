package com.klobbix.network.client.impl.packet;

import com.klobbix.network.client.AbstractClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class PacketClient extends AbstractClient {

    private int length = 4;

    public PacketClient(int port) {
        super(port);
    }

    public PacketClient(String host, int port) {
        super(host, port);
    }

    public PacketClient(String host, int port, int length) {
        this(host, port);
        this.length = length;
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
                    ch.pipeline().addLast(new PacketDecoder(length), new PacketClientHandler());
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
