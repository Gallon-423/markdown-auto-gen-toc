import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileIO {
    public static String genContent(int grade,String tocTitle, String toc, String str){
        String patternRaw = "(?m)^#{%d}\\s(.+)";
        String pattern=String.format(patternRaw,grade);
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);


        int count=0;
        String origin=str;
        while(m.find()){
            count++;
            String s=m.group(1);
            String contentRaw="- <a href=#%d>"+s+"</a>\r\n";
            String content=String.format(contentRaw,count);
            toc=toc+content;
            //替换标题文本
            String formattedTitleRaw="<a id=\"%d\">"+s+"</a>";
            String formattedTitle=String.format(formattedTitleRaw,count);
            str=str.replace(s,formattedTitle);
        }
        String divider="\r\n------\r\n\r\n";
        if (count<=1){
            toc= "";
            str= origin;
            return genContent(grade+1,tocTitle,toc,str);
        }
        else return tocTitle+toc+divider+str;
    }
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
        String target="";
        while(times--!=0){
            target=target+base;
        }
        return target;
    }
}
