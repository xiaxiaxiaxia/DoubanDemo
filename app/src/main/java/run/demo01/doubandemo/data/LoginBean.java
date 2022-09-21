package run.demo01.doubandemo.data;

public class LoginBean {
    private String name;
    private String nickName;
    private String headImage;

    public LoginBean(String name, String nickName, String headImage) {
        this.name = name;
        this.nickName = nickName;
        this.headImage = headImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }
}
