package _7;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * 描述:
 *
 * @author huang
 * @since 2019-10-06 8:23 PM
 */
public class MsgpackEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        MessagePack msgPack = new MessagePack();
        byte[] raw = msgPack.write(msg);
        out.writeBytes(raw);
        System.out.println("encode over!");
    }
}