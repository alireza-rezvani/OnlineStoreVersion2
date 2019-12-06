package ir.maktab32.java.projects.onlinestoreversion2.userentrance;

import ir.maktab32.java.projects.onlinestoreversion2.daoimpl.UserDaoImpl;
import ir.maktab32.java.projects.onlinestoreversion2.model.Address;
import ir.maktab32.java.projects.onlinestoreversion2.model.User;
import ir.maktab32.java.projects.onlinestoreversion2.utilities.Utilities;

import java.util.List;
import java.util.Scanner;

public class UserEntrance{
    private Scanner scanner = new Scanner(System.in);
    private User resultUser = null;

    public User enter(){
        resultUser = null;
        System.out.println("\n\u29bf Select Entrance Method:\t(Or Press Any Key to Exit!)");
        System.out.println("\t1. Sign In\n\t2. Sign Up");
        String choice = scanner.next();
        if (choice.equals("1")){
            resultUser = signIn();
        }
        else if (choice.equals("2")){
            resultUser = signUp();
        }
        else {
            resultUser = null;
        }
        return resultUser;
    }

    public User signIn(){
        resultUser = null;
        String username;
        String password;

        do {
            System.out.print("\u29bf Enter Your Username: ");
            username = scanner.next();
            if (username.length() < 3)
                System.out.println("\t\u26a0 Username's Length Cant't Be Less Than 3 Characters!");
        }while (username.length() < 3);

        do {
            System.out.print("\u29bf Enter Your Password: ");
            password = scanner.next();
            if (password.length() < 6)
                System.out.println("\t\u26a0 Password's Length Cant't Be Less Than 6 Characters!");
        }while (password.length() < 6);

        resultUser = new UserDaoImpl().findUserByUsername(username);

        if (resultUser == null){
            System.out.println("\t\u26a0 There Isn't Such User!");
            enter();
        }
        else {
            if (resultUser.getPassword().equals(password)){
                System.out.println("\n\t\u2705 Welcome Dear " + resultUser.getFirstName() + "!\n");
            }
            else {
                System.out.println("\t\u26a0 Invalid Password!");
                enter();
            }
        }

        return resultUser;
    }

    public User signUp(){
        resultUser = null;
        String username;
        String password;
        String firstName;
        String lastName;
        String mobileNumber;
        String email;
        String province;
        String city;
        String street;
        String zipCode;

        List<User> usersList = new UserDaoImpl().findAllUsers();

        System.out.println("\n\u29bf\u29bf\u29bf Enter Your Information \u29bf\u29bf\u29bf\n");

        do {
            System.out.print("\u29bf Enter Your Username: ");
            username = scanner.next();
            if (username.length() < 3){
                System.out.println("\t\u26a0 Username's Length Cant't Be Less Than 3 Characters!");
            }
            else{
                for (User user : usersList){
                    if (user.getUsername().equals(username)){
                        System.out.println("\t\u26a0 This Username is Taken! Choose Another Username!");
                        username = "";
                    }
                }
            }
        }while (username.length() < 3);

        do {
            System.out.print("\u29bf Enter Your Password: ");
            password = scanner.next();
            if (password.length() < 6){
                System.out.println("\t\u26a0 Password's Length Cant't Be Less Than 6 Characters!");
            }
        }while (password.length() < 6);

        do {
            System.out.print("\u29bf Enter Your First Name: ");
            firstName = scanner.next();
            if (firstName.equals("") || firstName == null){
                System.out.println("\t\u26a0 First Name Cant't Be Empty!");
            }
        }while (firstName.equals("") || firstName == null);


        do {
            System.out.print("\u29bf Enter Your Last Name: ");
            lastName = scanner.next();
            if (lastName.equals("") || lastName == null){
                System.out.println("\t\u26a0 Last Name Cant't Be Empty!");
            }
        }while (lastName.equals("") || lastName == null);


        do {
            System.out.print("\u29bf Enter Your Mobile Number: ");
            mobileNumber = scanner.next();
            if (!Utilities.isNumeric(mobileNumber)){
                System.out.println("\t\u26a0 Invalid Mobile Number!");
            }
            if (mobileNumber.length() < 11){
                System.out.println("\t\u26a0 Mobile Number Cant Be Shorter Than 11 Characters!");
            }
            if (mobileNumber.length() > 15){
                System.out.println("\t\u26a0 Entered Mobile Number is Too Long!");
            }

        }while ((!Utilities.isNumeric(mobileNumber)) || mobileNumber.length() < 11 || mobileNumber.length() > 15);


        do {
            System.out.print("\u29bf Enter Your Email Address: ");
            email = scanner.next();

            if (!Utilities.isEmailValid(email)){
                System.out.println("\t\u26a0 Invalid Email Address!");
            }
        }while (!Utilities.isEmailValid(email));


        System.out.println("\n\u29bf Address Information \u29bf");
        do {
            System.out.print("\u29bf Enter Your Province: ");
            province = scanner.next();
            if (province.equals("") || province == null){
                System.out.println("\t\u26a0 Province Cant't Be Empty!");
            }
        }while (province.equals("") || province == null);

        do {
            System.out.print("\u29bf Enter Your City: ");
            city = scanner.next();
            if (city.equals("") || city == null){
                System.out.println("\t\u26a0 City Cant't Be Empty!");
            }
        }while (city.equals("") || city == null);

        do {
            System.out.print("\u29bf Enter Your Street: ");
            street = scanner.next();
            if (street.equals("") || street == null){
                System.out.println("\t\u26a0 Street Cant't Be Empty!");
            }
        }while (street.equals("") || street == null);

        do {
            System.out.print("\u29bf Enter Your ZipCode: ");
            zipCode = scanner.next();
            if ((!Utilities.isNumeric(zipCode)) || zipCode.length() != 10){
                System.out.println("\t\u26a0 Invalid ZipCode! Enter Your 10 Digits ZipCode!");
            }
        }while ((!Utilities.isNumeric(zipCode)) || zipCode.length() != 10);

        UserDaoImpl userDaoImpl = new UserDaoImpl();

        //this user still doesn't have cartId and addressId. so it is just for sending to database; not for return
        Address address = new Address(province,city,street,zipCode);
        resultUser = new User(firstName,lastName,mobileNumber,email,address,username,password);
        userDaoImpl.addUser(resultUser);

        System.out.println("\n\t\u2705 Welcome Dear " + firstName + "!\n");

        //we return user from database that contains cartId and addressId
        return userDaoImpl.findUserByUsername(username);
    }
}