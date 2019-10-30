package _12.server;

import _12.MessageType;
import _12.codec.pojo.Header;
import _12.codec.pojo.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author huang
 * @version v1.0
 * @date 2019-10-22 10:04 PM
 */
public class HeartBeatRespHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()) {
            System.out.println("receive client heart beat message : --->" + message);
            NettyMessage heartBeat = buildHeartBeat();
            System.out.println("send heart beat response message to client : --->" + heartBeat);
            ctx.writeAndFlush(heartBeat);

        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildHeartBeat() {

        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_RESP.value());
        message.setHeader(header);
        return message;
    }
}