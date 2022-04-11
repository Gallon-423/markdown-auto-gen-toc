import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileIO {
    public static String getFileStringByPath(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        byte[] data = new byte[getFileBytesCount(path)];
        //unicode编码的文件中的换行是“\r\n”
        //一个中文在utf-8中由3个字节表示
        int len = fis.read(data);
        String str = new String(data,0,len,StandardCharsets.UTF_8);
        //"utf-8"可以不写，这是MarkText默认字符编码集
        //System.out.println(str);
        fis.close();
        return str;
    }
    public static int getFileBytesCount(String path){
        //使用相对路径
        File file = new File(path);
        //获取字节数
        //java int 的最大值Integer.MAX_VALUE 是2147483647,二十一亿多的一个整数
        //正常来说不会有这么大的文件，如需要可自己定制
        //System.out.println("文件大小（字节）："+length);
        return (int) file.length();
    }
    public static void writeFileNotAppending(String path,String content) throws IOException {
        FileOutputStream fos = new FileOutputStream(path,false);
        fos.write(content.getBytes(StandardCharsets.UTF_8));
        //System.out.println("完成");
        fos.close();
        System.out.println("Finished");
    }
    public static String repeatString(String base,int times){
        StringBuilder res= new StringBuilder();
        for (int i = 0; i < times; i++) {
            res.append(base);
        }
        return res.toString();
    }
}
