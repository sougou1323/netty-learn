package _7._2;

import _7._1.EchoClientHandler;
import _7.MsgpackDecoder;
import _7.MsgpackEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * 描述:
 *
 * @author huang
 * @since 2019-10-11 2:42 PM
 */
public class EchoClientV2 {

    private final String host;
    private final int port;
    private final int sendNumber;

    public EchoClientV2(String host, int port, int sendNumber) {
        this.host = host;
        this.port = port;
        this.sendNumber = sendNumber;
    }

    public void run() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2))
                                    .addLast("msgpack decoder", new MsgpackDecoder())
                                    .addLast("frameEncoder", new LengthFieldPrepender(2))
                                    .addLast("msgpack encoder", new MsgpackEncoder())
                                    .addLast(new EchoClientHandler(sendNumber));
                        }
                    });
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoClientV2( "127.0.0.1",8080,  20).run();
    }

}