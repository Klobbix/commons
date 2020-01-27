package com.klobbix.network.client.impl.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import java.util.List;

public class PacketDecoder extends ReplayingDecoder<Void> {

    private final int length;

    protected PacketDecoder(int length) {
        this.length = length;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) {
        out.add(new Packet(in.readBytes(length)));
    }
}
