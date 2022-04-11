import java.io.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

        String regex=String.format(TableOfContent.titleFilter,1);
        String input= FileIO.getFileStringByPath(name);
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        TableOfContent table=new TableOfContent();
        while (matcher.find()) {
            String s0=matcher.group(0);
            String s1=matcher.group(1);
            int count=1;
            String regexOnGrade=String.format(TableOfContent.titleFilter,count+1);
            Pattern patternOnGrade=Pattern.compile(regexOnGrade);
            Matcher matcherOnGrade=patternOnGrade.matcher(s0);
            while(matcherOnGrade.matches()){
                count++;
                regexOnGrade=String.format(TableOfContent.titleFilter,count+1);
                patternOnGrade=Pattern.compile(regexOnGrade);
                matcherOnGrade=patternOnGrade.matcher(s0);
            }
            //System.out.println(s0+" grade:"+count);
            //System.out.println("the content:"+s1);
            Title title=new Title(count,s1,s0);
            table.add(title);
            String titlePreRaw="<a id=\"%s\">";
            String titlePre=String.format(titlePreRaw,TableOfContent.tagPrefix+title.getId());
            String titleSuf="</a>";
            String newTitle=titlePre+s1+titleSuf;
            input=input.replace(s1,newTitle);
        }
        String toc=table.genTOC();
        FileIO.writeFileNotAppending(outputFileName,toc+input);

    }


}
