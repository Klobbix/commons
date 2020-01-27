package com.klobbix.network.client;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;

public abstract class AbstractClient implements Client {

    private final String host;
    private final int port;
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();

    protected AbstractClient(int port) {
        this.host = "localhost";
        this.port = port;
    }

    protected AbstractClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public EventLoopGroup getWorkerGroup() {
        return workerGroup;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public abstract void connect() throws Exception;

    @Override
    public Future<?> shutdown() {
        return workerGroup.shutdownGracefully();
    }
}
