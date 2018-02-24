package cn.myxinge.demo.ws;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @Auth:chenxinghua
 * @Date:2018\2\24 0024 15:47
 * @Description:
 */
@Controller
public class ThreadSendMsg {

    @RequestMapping("/push")
    @ResponseBody
    public String sendWsMsg() {
        System.out.println("准备推送数据至客户端....");
        if (null == WebSoketServerHandler.getSession()) {
            return "webSoket is closed..";
        }
        new Thread(new Runnable() {
            public void run() {
                WebSoketServerHandler webSoketServerHandler = new WebSoketServerHandler();

                int i = 0;
                while (true) {
                    try {
                        webSoketServerHandler.sendMessageTo("测试数据" + (i++), "");
                        //每秒一次
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        return "begin push data to client...";
    }
}
