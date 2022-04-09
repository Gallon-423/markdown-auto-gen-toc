import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexApiTest {
    @Test
    //记录位置
    public void test1() {
        Pattern pattern=Pattern.compile("[a-z]+");
        Matcher matcher = pattern.matcher("2020 i love you! 0329");
        while (matcher.find()) {
            /*输出：
            start=5,end=6
            start=7,end=11
            start=12,end=15
            */
            System.out.println("start=" + matcher.start() + ",end=" + matcher.end());

        }

    }

    @Test
    public void test2() {
        Pattern pattern=Pattern.compile("(?m)^#*#{1}\\s(.+)");
        Matcher matcher = pattern.matcher("# 123\n## 345\n### 456\n");
        while (matcher.find()) {
            String s=matcher.group(1);
            System.out.println(s);

        }

    }
    public String repeatString(String base,int times){
        String target="";
        while(times--!=0){
            target=target+base;
        }
        return target;
    }

    @Test
    public void test3() {
        String regexRaw="(?m)^#*#{%d}\\s(.+)";
        String regex=String.format(regexRaw,1);
        String input="# 123\n## 345\n### 456\n";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        String content="";
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
            System.out.println(s0+" grade:"+count);
            System.out.println("the content:"+s1);
            content=content+repeatString(" ",(count-1)*2)+"- "+s1+"\r\n";
        }
        System.out.println(content);
    }

    @Test
    public void test4() {
        String regexRaw="(?m)^#*#{%d}\\s(.+)";
        String regex=String.format(regexRaw,1);
        String input="\n## 345\n### 456\n## 789\n#### JQK";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        String content="";
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
            System.out.println(s0+" grade:"+count);
            System.out.println("the content:"+s1);
            Title title=new Title(count,s1,s0);
            titles.add(title);
        }
        TableOfContent tableOfContent=new TableOfContent(titles);
        System.out.println(tableOfContent.genTOC());
        System.out.println(tableOfContent.addTarget(input));

    }


}
