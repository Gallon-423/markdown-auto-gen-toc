import sun.nio.cs.ext.GBK;

import java.io.*;


public class SingleFile {
    public static void main(String[] args) throws IOException {
        if(args.length==0) throw new IllegalArgumentException("You are suggested to inspect the argument you input.");
        File file = new File( args[0]);   //分隔符
        //获取文件名(不包括文件路径)
        String name = file.getName();
        System.out.println("InputFile:"+name);


        String outputFileName="output.md";
        if(args.length>=2){
            outputFileName=args[1];
        }
        System.out.println("The file created:"+outputFileName);
        String tocTitle="# <font color=LightBlue>Content</font>";
        if(args.length>=3){
            tocTitle=args[2];
        }
        tocTitle=tocTitle+"\r\n";
        Common.writeFileNotAppending(outputFileName,
                Common.genContent(1,tocTitle,"",Common.getFileStringByPath(name)));

    }


}
