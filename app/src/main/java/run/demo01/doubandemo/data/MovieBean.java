package run.demo01.doubandemo.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//1
@Entity(tableName = "movie")
public class MovieBean {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "info_link")
    private String info_link;

    private String pic_link;
    private String cname;
    private String score;
    private String rated;
    private String instroducation;
    private String info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo_link() {
        return info_link;
    }

    public void setInfo_link(String info_link) {
        this.info_link = info_link;
    }

    public String getPic_link() {
        return pic_link;
    }

    public void setPic_link(String pic_link) {
        this.pic_link = pic_link;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getInstroducation() {
        return instroducation;
    }

    public void setInstroducation(String instroducation) {
        this.instroducation = instroducation;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
