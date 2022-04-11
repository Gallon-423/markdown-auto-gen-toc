import java.util.ArrayList;
import java.util.List;

public class Title {
    //表示当前等级，即#号的个数
    private int grade;
    //表示除了井号外的文本内容
    private String contentWithoutWell;
    //表示包括了井号的内容
    private String contentWithWell;
    //对应id
    private String id;
    //父级title
    private Title parent;
    //孩子titles
    private List<Title> children;


    public Title(int grade, String contentWithoutWell, String contentWithWell) {
        this.grade = grade;
        this.contentWithoutWell = contentWithoutWell;
        this.contentWithWell = contentWithWell;
        children=new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getContentWithoutWell() {
        return contentWithoutWell;
    }

    public void setContentWithoutWell(String contentWithoutWell) {
        this.contentWithoutWell = contentWithoutWell;
    }

    public String getContentWithWell() {
        return contentWithWell;
    }

    public void setContentWithWell(String contentWithWell) {
        this.contentWithWell = contentWithWell;
    }

    public Title getParent() {
        return parent;
    }

    public void setParent(Title parent) {
        this.parent = parent;
    }

    public List<Title> getChildren() {
        return children;
    }

    public void addChildren(Title child) {
        this.children.add(child);
    }
}
