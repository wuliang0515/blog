package com.wu.blog.enums;

public enum NotificationTypeEnum {
    REPLY_QUESTION(1,"replied to the question"),
    REPLY_COMMENT(2,"replied to the comment");

    private int type;
    private String name;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    NotificationTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
    public static  String nameOfType(int type){
        for (NotificationTypeEnum notificationTypeEnum: NotificationTypeEnum.values()
             ) {
          if(notificationTypeEnum.getType() == type){
              return notificationTypeEnum.getName();
          }
        }
        return "";
    }
}
