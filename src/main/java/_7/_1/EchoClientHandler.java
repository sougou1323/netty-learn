package _7._1;

import _7.UserInfo;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 描述:
 *
 * @author huang
 * @since 2019-10-06 8:40 PM
 */
public class EchoClientHandler extends ChannelHandlerAdapter {

    private final int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserInfo[] infos = userInfo();

        for (UserInfo userinfo : infos) {
            ctx.write(userinfo);
        }
        ctx.flush();

//        ctx.writeAndFlush(Unpooled.copiedBuffer("hello".getBytes()));
        System.out.println("send over!");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client receive msgpack message : " + msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private UserInfo[] userInfo() {
        UserInfo[] userInfos = new UserInfo[sendNumber];
        UserInfo userInfo = null;
        for (int i = 0; i < sendNumber; i++) {
            userInfo = new UserInfo();
            userInfo.setUserId(i);
            userInfo.setUserName("ABCDEFG --->" + i);
            userInfos[i] = userInfo;
        }
        return userInfos;
    }


}