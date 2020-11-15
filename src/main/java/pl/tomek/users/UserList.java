package pl.tomek.users;

import java.util.ArrayList;
import java.util.Scanner;

public class UserList {
    private final ArrayList<User> users = new ArrayList<>();

    public void addUser(User user)
    {
        this.users.add(user);
    }

    public void showAllNotActiveUsers()
    {
        for (int i = 0; i < users.size(); i++) {
            if(!users.get(i).isActive())
                System.out.println("Id: "+ (i + 1) +": " + users.get(i).showUser());
        }
    }

    public void showAllActiveUsers()
    {
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).isActive())
                System.out.println("Id: "+ (i + 1) +": " + users.get(i).showUser());
        }
    }

    public void setUserActive()
    {
        showAllNotActiveUsers();
        Scanner in = new Scanner(System.in);
        System.out.println("Podaj numer osoby do aktywacji:");
        int input = in.nextInt();
        users.get(input-1).setActive(true);
    }

    public void addNewUser()
    {
        users.add(User.build());
    }

    public User getUser(int index)
    {
        return users.get(index-1);
    }

}
