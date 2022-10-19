package com.yrk.core.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @BelongsProject: BIO_NIO_AIO_Demo
 * @Author: ruikun
 * @CreateTime: 2022-10-19  22:04
 * @Description: TODO
 * @Version: 1.0
 */
public class NIOServerDemo {

    public static void main(String[] args) throws IOException {
        //打开通道
        ServerSocketChannel channel = ServerSocketChannel.open();
        //绑定Socket的端口
        channel.socket().bind(new InetSocketAddress(8082));
        //设置不需要阻塞
        channel.configureBlocking(Boolean.FALSE);
        List<SocketChannel>listSocket=new ArrayList<>();
        while (true){
            //循环中获取链接
            SocketChannel accept = channel.accept();
            if (Objects.nonNull(accept)){
                System.out.println("获取到新的连接");
                //获取链接的socket对象 并设置非阻塞
                accept.configureBlocking(Boolean.FALSE);
                //缓存起来
                listSocket.add(accept);
            }

            Iterator<SocketChannel> iterator = listSocket.iterator();
            while (iterator.hasNext()){
                SocketChannel next = iterator.next();

                //向jvm申请内存空间
                ByteBuffer bytes = ByteBuffer.allocate(126);
                //读取请求中的数据
                //直接从通道中获取数据 如果流数据读取为空则返回-1
                Integer readLen = next.read(bytes);
                if (readLen>0){
                    System.out.println("获取到的请求数据为 "+new String(bytes.array(),0,readLen));
                }else if (readLen<0){
                    iterator.remove();;
                    System.out.println("断开连接 ");
                }
                //
            }

        }

    }
}
