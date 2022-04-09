import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
        String tocTitle="# <font color=LightBlue>目录</font>";
        if(args.length>=3){
            tocTitle=args[2];
        }
        tocTitle=tocTitle+"\r\n";
        String regexRaw="(?m)^#*#{%d}\\s(.+)";
        String regex=String.format(regexRaw,1);
        String input= FileIO.getFileStringByPath(name);
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        List<Title> titles=new ArrayList<>();
        while (matcher.find()) {
            String s0=matcher.group(0);
            String s1=matcher.group(1);
            int count=1;
            String regexOnGrade=String.format(regexRaw,count+1);
            Pattern patternOnGrade=Pattern.compile(regexOnGrade);
            Matcher matcherOnGrade=patternOnGrade.matcher(s0);
            while(matcherOnGrade.matches()){
                count++;
                regexOnGrade=String.format(regexRaw,count+1);
                patternOnGrade=Pattern.compile(regexOnGrade);
                matcherOnGrade=patternOnGrade.matcher(s0);
            }
            //System.out.println(s0+" grade:"+count);
            //System.out.println("the content:"+s1);
            Title title=new Title(count,s1,s0);
            titles.add(title);
        }
        TableOfContent tableOfContent=new TableOfContent(titles);

        //System.out.println(tableOfContent.genTOC());
        //System.out.println(tableOfContent.addTarget(input));
        FileIO.writeFileNotAppending(outputFileName,tocTitle+tableOfContent.genTOC()+"\r\n"+tableOfContent.addTarget(input));


    }


}
