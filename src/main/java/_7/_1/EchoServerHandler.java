package _7._1;

import _7.UserInfo;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * 描述:
 *
 * @author huang
 * @since 2019-10-06 9:07 PM
 */
public class EchoServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server receive the msgpack message : " + msg + "");
        ctx.writeAndFlush(msg);
        // 在EchoClientHandler中向服务端发送一个pojo对象，经过MessagePack编解码后，
        // 在EchoServerHandler中的channelRead方法中打印的msg为pojo对象的toString方法内容，
        // 不可以直接将msg转换为User

         List<Object> userInfo = (List<Object>) msg;
         if (userInfo == null) {
             System.out.println("userInfo is null");
         } else {
             for (Object user : userInfo) {
                 // ClassCastException
//                 UserInfo user1 = (UserInfo) user;
                 System.out.println(user);
             }
         }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

}