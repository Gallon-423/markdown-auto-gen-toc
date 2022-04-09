import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProjectTest {
//    @Test
//    public void main1() throws IOException {
//        String regexRaw="(?m)^#*#{%d}\\s(.+)";
//        String path="input_CN.md";
//        String regex=String.format(regexRaw,1);
//        String input= FileIO.getFileStringByPath(path);
//        Pattern pattern=Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(input);
//        List<Title> titles=new ArrayList<>();
//        while (matcher.find()) {
//            String s0=matcher.group(0);
//            String s1=matcher.group(1);
//            int count=1;
//            String regexOnGrade=String.format(regexRaw,count+1);
//            Pattern patternOnGrade=Pattern.compile(regexOnGrade);
//            Matcher matcherOnGrade=patternOnGrade.matcher(s0);
//            while(matcherOnGrade.matches()){
//                count++;
//                regexOnGrade=String.format(regexRaw,count+1);
//                patternOnGrade=Pattern.compile(regexOnGrade);
//                matcherOnGrade=patternOnGrade.matcher(s0);
//            }
//            System.out.println(s0+" grade:"+count);
//            System.out.println("the content:"+s1);
//            Title title=new Title(count,s1,s0);
//            titles.add(title);
//        }
//        TableOfContent tableOfContent=new TableOfContent(titles);
//
//        System.out.println(tableOfContent.genTOC());
//        System.out.println(tableOfContent.addTarget(input));
//        FileIO.writeFileNotAppending("output_CN.md",tableOfContent.genTOC()+"\r\n"+tableOfContent.addTarget(input));
//
//    }
}
