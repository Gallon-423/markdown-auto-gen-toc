import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableOfContent {
    List<Title> titles;
    //后续用于打印
    List<Title> greatest;
    int greatestGrade;
    public static final String tagPrefix="gallon_toc_";
    public static final String titleFilter="(?m)^(#*#{%d})\\s(.+)";
    //表示%d级以上的title会被选中
    public TableOfContent() {
        this.titles = new ArrayList<>();
        this.greatest=new ArrayList<>();
        greatestGrade=6;
    }
    public static String backToOrigin(String text){
        String regex1="(?m).*<a href=#gallon_toc_.*>.+</a>\\s*";
        String regex2="(?m)<a id=\"gallon_toc_.*>(.+)</a>";
        Pattern pattern1=Pattern.compile(regex1);
        Pattern pattern2=Pattern.compile(regex2);
        Matcher matcher1=pattern1.matcher(text);
        Matcher matcher2=pattern2.matcher(text);
        while (matcher1.find()){
            String s0=matcher1.group(0);
            text=text.replace(s0,"");
        }
        while (matcher2.find()){
            String s0=matcher2.group(0);
            String s1=matcher2.group(1);
            text=text.replace(s0,s1);
        }
        return text;
    }
    public String genTOC(){
        StringBuilder toc= new StringBuilder();
        for (Title cur : titles) {
            String tag = tagPrefix + cur.getId();
            int diff = cur.getGrade() - greatestGrade;
            String spaces=FileIO.repeatString(" ", 2*diff);
            String contentPreRaw = spaces+"- " + "<a href=#%s>";
            String contentPre = String.format(contentPreRaw, tag);
            String contentSuf = "</a>\r\n";

            toc.append(contentPre).append(cur.getContentWithoutWell()).append(contentSuf);

        }
        return toc.toString();
    }

    public String addTarget(String input){
        String tag1Raw="<a id=\"%s\">";
        String tag2="</a>";
        for (Title title:titles
             ) {
            String tag1=String.format(tag1Raw,title.getId());
            input=input.replace(title.getContentWithoutWell(),tag1+title.getContentWithoutWell()+tag2);
        }
        return input;
    }

    public void add(Title title){
        //在添加进去的时候应该确定双方的父母和孩子

        for (int i = titles.size()-2; i >= 0; i--) {
            //-2是从自己前一个开始找
            //找到自己前面第一个等级小于自己的，就是自己的父，如果没有则没有父
            Title temp=titles.get(i);
            if(temp.getGrade()<title.getGrade()){
                title.setParent(temp);
                //指定父
                temp.addChildren(title);
                //增加孩子
                title.setId(title.getParent().getId()+"_"+(title.getParent().getChildren().size()-1));
                //为孩子定id
                break;
            }
        }
        //如果没有父，就是最高级目录
        if (title.getParent()==null){
            greatest.add(title);
            int id=greatest.size()-1;
            title.setId(String.valueOf(id));
        }
        titles.add(title);

        //将标题加入待打印列表
        if(title.getGrade()<greatestGrade) greatestGrade=title.getGrade();
    }
}
