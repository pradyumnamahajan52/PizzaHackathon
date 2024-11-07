package com.pizzabite.entity;

import java.util.Objects;
import java.util.Scanner;

public class User {

    int id;
    String name;
    String password;
    String mobile;	
    String address;
    String email;
    boolean admin;

    public User() {

    }


	public User(int id, String name, String mobile, String address, String email, boolean admin) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.address = address;
		this.email = email;
		this.admin = admin;
	}


    public User(String name, String password, String mobile, String address, String email) {
        super();
        this.name = name;
        this.password = password;
        this.mobile = mobile;
        this.address = address;
        this.email = email;
    }

    public User(int id, String name, String password, String mobile, String address, String email, boolean admin) {

        this.id = id;
        this.name = name;
        this.password = password;
        this.mobile = mobile;
        this.address = address;
        this.email = email;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, admin, email, mobile, name, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        return Objects.equals(address, other.address) && admin == other.admin && Objects.equals(email, other.email)
                && Objects.equals(mobile, other.mobile) && Objects.equals(name, other.name)
                && Objects.equals(password, other.password);
    }

    @Override
    public String toString() {
        return "Customer [name=" + name + ", password=" + password + ", mobile=" + mobile + ", address=" + address
                + ", email=" + email + ", admin=" + admin + "]";
    }

    public void acceptforRegistration(Scanner scan) {
  
        System.out.print("Enter name -  ");
        name = scan.nextLine();

        System.out.print("Enter Password -  ");
        password = scan.nextLine();

        System.out.print("Enter Mobile number -  ");
        mobile = scan.nextLine();

        System.out.print("Enter Address-  ");
        address = scan.nextLine();

        System.out.print("Enter email -  ");
        email = scan.nextLine();

    }

    public void acceptforLogin(Scanner scan) {
        // scan.nextLine();

        System.out.print("Enter email -  ");
        email = scan.nextLine();

        System.out.print("Enter Password -  ");
        password = scan.nextLine();

    }

}
