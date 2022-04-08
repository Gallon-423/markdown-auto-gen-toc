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
    @Test
    public void testFileReader() throws IOException {
        FileInputStream fis = new FileInputStream("git-note.md");
        byte[] data = new byte[1891];
        //unicode编码的文件中的换行是“\r\n”
        //一个中文在utf-8中由3个字节表示
        int len = fis.read(data);
        String str = new String(data,0,len, StandardCharsets.UTF_8);
        //"utf-8"可以不写，这是MarkText默认字符编码集
        System.out.println(str);
        fis.close();
    }
    @Test
    public void testFileWriter() throws IOException {
        /*
         * FileOutputStream fos = new FileOutputStream("abc.txt");
         * 默认的构造方法是覆盖写操作，即：如果输出的文件已经存在，会将原文件中的内容清空，然后通过流，从头写入新数据
         */

        /*
         * 追加操作，该构造方法需要传入两个参数，后一个参数为boolean值，若该值为true,这追加写操作；该流写入到文件结尾
         */
        FileOutputStream fos = new FileOutputStream("abc.txt",true);
        fos.write("123".getBytes());
        System.out.println("完成");
        fos.write("你好".getBytes());
        //System.out.println("完成");
        fos.close();
    }
    @Test
    public void testRegex() throws IOException {
        FileInputStream fis = new FileInputStream("git-note.md");
        byte[] data = new byte[1891];
        //unicode编码的文件中的换行是“\r\n”
        //一个中文在utf-8中由3个字节表示
        int len = fis.read(data);
        String str = new String(data,0,len, StandardCharsets.UTF_8);




        String pattern = "(?m)^#{2}\\s(.+)";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);

        while(m.find()){

            String s=m.group(1);
            String res="<a href=#"+1+">"+s+"</a>\n";
            String formattedLocation="<a id=\""+1+"\">"+s+"</a>";
            str=str.replace(s,formattedLocation);
            System.out.println(res);

        }
        System.out.println(str);

    }

    public String testRecursionMethod(int grade, String toc, String str){
        String patternRaw = "(?m)^#{%d}\\s(.+)";
        String pattern=String.format(patternRaw,grade);
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);

        boolean find=false;
        while(m.find()){
            find=true;
            String s=m.group(1);
            String contentRaw="- <a href=#%d>"+s+"</a>\n";
            String content=String.format(contentRaw,s.hashCode());
            toc=toc+content;
            //替换标题文本
            String formattedTitleRaw="<a id=\"%d\">"+s+"</a>";
            String formattedTitle=String.format(formattedTitleRaw,s.hashCode());
            str=str.replace(s,formattedTitle);
        }
        if (!find){
            return testRecursionMethod(grade+1,toc,str);
        }
        else return toc+str;

    }
    @Test
    public void testRecursionCall() throws IOException {
        FileInputStream fis = new FileInputStream("git-note.md");
        byte[] data = new byte[1891];
        //unicode编码的文件中的换行是“\r\n”
        //一个中文在utf-8中由3个字节表示
        int len = fis.read(data);
        String str = new String(data,0,len);
        //"utf-8"可以不写，这是MarkText默认字符编码集
        fis.close();
        System.out.println(testRecursionMethod(2,"",str));
    }
    @Test
    public void testWriteTarget() throws IOException {
        FileInputStream fis = new FileInputStream("git-note.md");
        byte[] data = new byte[1891];
        //unicode编码的文件中的换行是“\r\n”
        //一个中文在utf-8中由3个字节表示
        int len = fis.read(data);
        String str = new String(data,0,len);
        //"utf-8"可以不写，这是MarkText默认字符编码集
        fis.close();
        System.out.println(testRecursionMethod(2,"",str));
        FileOutputStream fos = new FileOutputStream("new.md");
        fos.write(testRecursionMethod(1,"",str).getBytes(StandardCharsets.UTF_8));
        //System.out.println("完成");
        fos.close();
    }

}
