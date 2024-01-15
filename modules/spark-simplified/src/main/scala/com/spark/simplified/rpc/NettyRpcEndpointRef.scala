package com.spark.simplified.rpc

import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.string.StringEncoder

import scala.concurrent.Future

// connecting to server, sending messages, then closing connection
class NettyRpcEndpointRef(name: String, env: NettyRpcEnv) extends RpcEndpointRef {
  private val group = new NioEventLoopGroup()
  private val bootstrap = new Bootstrap()
    .group(group)
    .channel(classOf[NioSocketChannel])
    .handler(new ChannelInitializer[NioSocketChannel] {
      override def initChannel(ch: NioSocketChannel): Unit = {
        ch.pipeline().addLast(new StringEncoder())
      }
    })

  // send message without waiting for response
  override def send(message: Any): Unit = {
    val channel = bootstrap.connect("localhost", 0).sync().channel()
    channel.writeAndFlush(s"$name:$message").sync()
    channel.closeFuture().sync()
    group.shutdownGracefully()
  }

  // send message and receive a response
  override def ask(message: Any): Future[Any] = ???
}
