package com.yy.ds.system.user.enums;

/*
 *@Description: 所有用户相关的枚举定义处
 *@ClassAuthor: tengYong
 *@Date: 2021-09-18 13:53:10
*/
public class UserEnums {

    public enum Status {

        STATUS_FORBID("禁用"), STATUS_NORMAL("正常");

        private final String message;

        Status(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public enum PermissionType {

        CATALOGUE("目录"), MENU("菜单"), BUTTON("按钮");

        private final String message;

        PermissionType(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}