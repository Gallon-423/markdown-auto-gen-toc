import java.io.*;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SingleFile {
    public static void main(String[] args) throws IOException {
        if(args.length==0) throw new IllegalArgumentException("You are suggested to inspect the argument you input.");
        String funChoose=args[0];
        if (Objects.equals(funChoose, "gen") || Objects.equals(funChoose, "generate")){
            File file = new File( args[1]);   //分隔符
            //获取文件名(不包括文件路径)
            String name = file.getName();
            System.out.println("InputFile:"+name);

            String outputFileName="output.md";
            if(args.length>=3){
                outputFileName=args[2];
            }
            System.out.println("The file created:"+outputFileName);
            String input= FileIO.getFileStringByPath(name);
            //最初的匹配，一个井号
            Matcher matcher=getInitialMatcher(input);
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
            String toc=table.genTOC()+"\r\n\r\n\r\n";
            FileIO.writeFileNotAppending(outputFileName,toc+input);
        }else if(Objects.equals(funChoose, "mer") || Objects.equals(funChoose, "merge")){
            File file1 = new File( args[1]);   //
            File file2 = new File( args[2]);
            //获取文件名(不包括文件路径)
            String name1 = file1.getName();
            System.out.println("MergeFile1:"+name1);
            String name2 = file1.getName();
            System.out.println("MergeFile1:"+name2);

            String outputFileName="output.md";

            if(args.length>=4){
                outputFileName=args[3];
            }
            System.out.println("The file merged into:"+outputFileName);
            TableOfContent table1=addTitles(name1);
            TableOfContent table2=addTitles(name2);
            String toc=TableOfContent.mergeTable(table1,table2).genTOC()+"\r\n\r\n\r\n";
            String input1= FileIO.getFileStringByPath(name1);
            String input2=FileIO.getFileStringByPath(name2);
            String input=input1+"\r\n"+input2;
            FileIO.writeFileNotAppending(outputFileName,toc+input);
        }else{
            throw new IllegalArgumentException("You are suggested to choose a type of function.(generate/merge)");
        }



    }

    private static TableOfContent addTitles(String name) throws IOException {
        String input= FileIO.getFileStringByPath(name);
        Matcher matcher=getInitialMatcher(input);
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
        return table;
    }

    public static Matcher getInitialMatcher(String input){
    String regex=String.format(TableOfContent.titleFilter,1);
    input=TableOfContent.backToOrigin(input);
    Pattern pattern=Pattern.compile(regex);
    return pattern.matcher(input);
}

}
