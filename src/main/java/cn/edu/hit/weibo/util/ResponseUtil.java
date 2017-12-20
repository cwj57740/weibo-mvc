package cn.edu.hit.weibo.util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtil {
    public static String sendJsonResponse(HttpServletResponse response,Object ... objects) throws IOException {
        PrintWriter writer = response.getWriter();
        Gson gson = new Gson();
        String json = gson.toJson(objects);
        writer.write(json);
        return json;
    }
    public static void sendBooleanResponse(HttpServletResponse response,boolean b){
        try {
            PrintWriter writer = response.getWriter();
            if(b){
                writer.write("true");
            } else {
                writer.write("false");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
