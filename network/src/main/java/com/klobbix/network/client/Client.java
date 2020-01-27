package com.klobbix.network.client;

import io.netty.util.concurrent.Future;

public interface Client {

    String getHost();
    int getPort();
    void connect() throws Exception;
    Future<?> shutdown();
}
