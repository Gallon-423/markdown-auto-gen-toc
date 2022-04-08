import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTest {
    @Test
    public void testFileDetail() {
        //使用相对路径
        File file = new File( "git-note.md");   //分隔符
        //获取文件名(不包括文件路径)
        String name = file.getName();
        System.out.println("文件名："+name);
        long length = file.length();
        System.out.println("文件大小（字节）："+length);

    }
    @Test
    public void testFileCreated() throws IOException {
        File file = new File("file-created-demo.txt");
        if(! file.exists()){
            file.createNewFile(); //没有判断也不会覆盖
            System.out.println("创建成功");
        }else{
            System.out.println("创建失败");
        }
    }


}
