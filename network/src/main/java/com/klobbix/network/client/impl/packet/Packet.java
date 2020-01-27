package com.klobbix.network.client.impl.packet;

import io.netty.buffer.ByteBuf;

public class Packet {

    private final ByteBuf byteBuf;

    public Packet(ByteBuf byteBuf) {
        this.byteBuf = byteBuf.readBytes(4);
    }

    public Packet(ByteBuf byteBuf, int count) {
        this.byteBuf = byteBuf.readBytes(count);
    }

    public ByteBuf getBytes() {
        return byteBuf;
    }
}
