package com.klobbix.network.server;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;

public abstract class AbstractServer implements Server {

    private final int port;
    private final String host;
    final EventLoopGroup bossGroup = new NioEventLoopGroup();
    final EventLoopGroup workerGroup = new NioEventLoopGroup();

    protected AbstractServer(int port) {
        this.host = "localhost";
        this.port = port;
    }

    protected AbstractServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public EventLoopGroup getBossGroup() {
        return bossGroup;
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
    public abstract void run() throws Exception;

    @Override
    public Future<?> shutdownBossGroup() {
        return bossGroup.shutdownGracefully();
    }

    @Override
    public Future<?> shutdownWorkerGroup() {
        return workerGroup.shutdownGracefully();
    }

    @Override
    public void shutdown() {
        shutdownBossGroup();
        shutdownWorkerGroup();
    }
}
