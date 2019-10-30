package _9;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 描述:
 *
 * @author huang
 * @since 2019-10-11 5:40 PM
 */
@ChannelHandler.Sharable
public class SubReqServerHandler extends ChannelHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReq req = (SubscribeReq) msg;
        if ("huang".equals(req.getUserName())) {
            System.out.println("accept req : " + req.toString());
            ctx.writeAndFlush(resp(req.getSubReqID()));
        }
    }

    private SubscribeResp resp(int subReqId) {
        SubscribeResp sub = new SubscribeResp();
        sub.setSubReqID(subReqId);
        sub.setRespCode(0);
        sub.setDesc("netty book order succeed, 3 days later, sent to the designated address");
        return sub;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}