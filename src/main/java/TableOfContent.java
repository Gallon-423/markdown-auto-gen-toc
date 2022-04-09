import java.util.List;

public class TableOfContent {
    List<Title> titles;
    int greatestGrade;

    public TableOfContent(List<Title> titles) {
        this.titles = titles;
        int greatest=6;
        for (Title title:titles
             ) {
            if (title.getGrade()<greatest){
                greatest=title.getGrade();
            }
        }
        greatestGrade=greatest;
    }
    public String genTOC(){
        String toc="";
        for (int i = 0; i < titles.size(); i++) {
            String id="gallon"+i;
            titles.get(i).setId(id);
            int diff=titles.get(i).getGrade()-greatestGrade;
            String addedLine= FileIO.repeatString(" ",diff*2)+"- "+ "<a href=#%s>%s</a>";
            String line=String.format(addedLine,id,titles.get(i).getContentWithoutWell())+"\r\n";
            toc=toc+line;
        }
        return toc;
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
}
