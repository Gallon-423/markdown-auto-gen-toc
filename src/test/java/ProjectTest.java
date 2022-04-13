import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProjectTest {

//    @Test
//    public void multilevelTest() throws IOException {
//
//
//            File file = new File("demo_with_content.md");   //分隔符
//            //获取文件名(不包括文件路径)
//            String name = file.getName();
//            System.out.println("InputFile:"+name);
//
//
//            String outputFileName="demo_with_content_output.md";
//
//            String regex=String.format(TableOfContent.titleFilter,1);
//            String input= FileIO.getFileStringByPath(name);
//            Pattern pattern=Pattern.compile(regex);
//            Matcher matcher = pattern.matcher(input);
//            TableOfContent table=new TableOfContent();
//        while (matcher.find()) {
//            String s0=matcher.group(0);
//            String well=matcher.group(1);
//            String s2=matcher.group(2);
//            int count=1;
//            String regexOnGrade=String.format(TableOfContent.titleFilter,count+1);
//            Pattern patternOnGrade=Pattern.compile(regexOnGrade);
//            Matcher matcherOnGrade=patternOnGrade.matcher(s0);
//            while(matcherOnGrade.matches()){
//                count++;
//                regexOnGrade=String.format(TableOfContent.titleFilter,count+1);
//                patternOnGrade=Pattern.compile(regexOnGrade);
//                matcherOnGrade=patternOnGrade.matcher(s0);
//            }
//            //System.out.println(s0+" grade:"+count);
//            //System.out.println("the content:"+s2);
//            Title title=new Title(count,s2,s0);
//            table.add(title);
//            String titlePreRaw="<a id=\"%s\">";
//            String titlePre=String.format(titlePreRaw,TableOfContent.tagPrefix+title.getId());
//            String titleSuf="</a>";
//            String newTitle=titlePre+s2+titleSuf;
//            input=input.replace(s0,well+" "+newTitle);
//        }
//        String toc=table.genTOC()+"\r\n\r\n\r\n";
//        FileIO.writeFileNotAppending(outputFileName,toc+input);
//
//
//    }
//    @Test
//    public void backToOriginTest() throws IOException {
//        String input=FileIO.getFileStringByPath("test.md");
//        System.out.println(TableOfContent.backToOrigin(input));
//    }
    @Test
    public void test_1_1_0() throws IOException {
        File file = new File("demo_with_content.md");   //分隔符
        //获取文件名(不包括文件路径)
        String name = file.getName();
        System.out.println("InputFile:"+name);


        String outputFileName="demo_with_content_output.md";

        String regex=String.format(TableOfContent.titleFilter,1);
        String input= FileIO.getFileStringByPath(name);
        input=TableOfContent.backToOrigin(input);
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        TableOfContent table=new TableOfContent();
        while (matcher.find()) {
            String s0=matcher.group(0);
            String well=matcher.group(1);
            String s2=matcher.group(2);
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
            //System.out.println("the content:"+s2);
            Title title=new Title(count,s2,s0);
            table.add(title);
            String titlePreRaw="<a id=\"%s\">";
            String titlePre=String.format(titlePreRaw,TableOfContent.tagPrefix+title.getId());
            String titleSuf="</a>";
            String newTitle=titlePre+s2+titleSuf;
            input=input.replace(s0,well+" "+newTitle);
        }
        String toc=table.genTOC();
        FileIO.writeFileNotAppending(outputFileName,toc+input);
    }

}
