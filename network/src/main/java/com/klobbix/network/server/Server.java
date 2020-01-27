package com.klobbix.network.server;

import io.netty.util.concurrent.Future;

public interface Server {

    String getHost();
    int getPort();
    void run() throws Exception;
    Future<?> shutdownBossGroup();
    Future<?> shutdownWorkerGroup();
    void shutdown();
}
