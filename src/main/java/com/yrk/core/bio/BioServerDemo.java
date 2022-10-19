package com.yrk.core.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * @BelongsProject: Bio学习demo
 * @Author: ruikun
 * @CreateTime: 2022-10-19  20:28
 * @Description: TODO
 * @Version: 1.0
 */
public class BioServerDemo {


    public static void main(String[] args) throws Exception {
        ServerSocket socket=new ServerSocket(8080);

        while (true){
            //获取BIO中的连接请求
            Socket accept = socket.accept();
            System.out.println("获取到请求");
            //向jvm申请内存空间
            byte[] bytes=new byte[126];
            //读取请求中的数据
            InputStream inputStream = accept.getInputStream();
            //阻塞读取流数据 如果流数据读取为空则返回-1
            int readLen = inputStream.read(bytes);
            int length = bytes.length;
            if (readLen!=-1){
                System.out.println("获取到的请求数据为 "+new String(bytes,0,length));
            }
            //关闭流 如果还需要输出数据则不需要关闭
//            inputStream.close();
            //这里可以继续创建输出流对象将数据返还给调用方  然后这个请求连接丢失不需要做处理 这一次的请求已经结束

        }



    }

}
