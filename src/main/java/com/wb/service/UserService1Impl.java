package com.wb.service;

public class UserService1Impl implements UserService1 {
    @Override
    public void addUser() {
        System.out.println("添加用户!");
    }

    @Override
    public void updateUser() {
        System.out.println("修改用户!");
    }

    @Override
    public void deleteUser() {
        System.out.println("删除用户!");
    }
}
