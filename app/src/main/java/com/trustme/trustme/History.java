package com.trustme.trustme;

import android.widget.Button;

public class History {

    private String id;
    private String userStory;
    private String dateStory;
    private String degree;
    private String category;
    private String titleStory;
    private String textHistory;
    private String commentsOneHistory;

    public History() {}

    public History(String degree, String category, String titleStory, String textHistory) {
        this.degree = degree;
        this.category = category;
        this.titleStory = titleStory;
        this.textHistory = textHistory;
    }

    public History(String id, String user1965, String s, String s1, String category_mistreatment,
                   String relationship_of_abuse, String s2) {}

    public History(String id, String userStory, String dateStory, String degree, String category,
                   String titleStory, String textHistory, String commentsOneHistory ) {
        this.id = id;
        this.userStory = userStory;
        this.dateStory = dateStory;
        this.degree = degree;
        this.category = category;
        this.titleStory = titleStory;
        this.textHistory = textHistory;
        this.commentsOneHistory = commentsOneHistory;
    }

    public String getId() {return  id;}
    public void setId(String id) {this.id = id;}

    public String getUserStory() {return userStory;}
    public void  setUserStory(String userStory) {this.userStory = userStory;}

    public String getDateStory() {return dateStory;}
    public void setDateStory(String dateStory) {this.dateStory= dateStory;}

    public String getDegree() {return degree;}
    public void setDegree(String degree) {this.degree = degree;}

    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}

    public String getTitleStory() {return titleStory;}
    public void setTitleStory(String titleStory) {this.titleStory = titleStory;}

    public String getTextHistory() {return textHistory;}
    public void setTextHistory(String textHistory) {this.textHistory = textHistory;}

    public String getCommentsOneHistory() {return commentsOneHistory;}
    public void setCommentsOneHistory(String commentsOneHistory) {this.commentsOneHistory =
            commentsOneHistory;}


}
