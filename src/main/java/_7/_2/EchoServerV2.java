package _7._2;

import _7._1.EchoServerHandler;
import _7.MsgpackDecoder;
import _7.MsgpackEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 描述:
 *
 * @author huang
 * @since 2019-10-11 2:50 PM
 */
public class EchoServerV2 {

    private int port;

    public EchoServerV2(int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {
        NioEventLoopGroup acceptorgroup = new NioEventLoopGroup();
        NioEventLoopGroup IOGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(acceptorgroup, IOGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2))
                                    .addLast("msgpack decoder", new MsgpackDecoder())
                                    .addLast("frameEncoder", new LengthFieldPrepender(2))
                                    .addLast("msgpack encoder", new MsgpackEncoder())
                                    .addLast(new EchoServerHandler());
                        }
                    });
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            acceptorgroup.shutdownGracefully();
            IOGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoServerV2(8080).run();
    }

}