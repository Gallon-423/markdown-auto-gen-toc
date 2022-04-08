import java.io.*;


public class SingleFile {
    public static void main(String[] args) throws IOException {
        if(args.length==0) throw new IllegalArgumentException("参数添加错误，请检查重试。");
        File file = new File( args[0]);   //分隔符
        //获取文件名(不包括文件路径)
        String name = file.getName();
        System.out.println("源文件名："+name);


        String outputFileName="output.md";
        if(args.length>=2){
            outputFileName=args[1];
        }
        System.out.println("生成文件名："+outputFileName);
        String tocTitle="# <font color=LightBlue>目录</font>";
        if(args.length>=3){
            tocTitle=args[2];
        }
        tocTitle=tocTitle+"\r\n";
        System.out.println("原字节数为："+Common.getFileBytesCount(name));
        Common.writeFileNotAppending(outputFileName,
                Common.genContent(1,tocTitle,"",Common.getFileStringByPath(name)));
        System.out.println("输出文件字节数为:"+Common.getFileBytesCount(outputFileName));
    }


}
