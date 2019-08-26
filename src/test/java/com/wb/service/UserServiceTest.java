package com.wb.service;

public class UserServiceTest {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//        UserService userService = (UserService) applicationContext.getBean("userService");
//        userService.login();
//        userService.reg();

        // ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans01.xml");
        UserService1 userService1 = UserServiceFactory.createUserSerivce2();
        userService1.addUser();
        userService1.deleteUser();
    }
}
