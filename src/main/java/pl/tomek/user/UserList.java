package pl.tomek.user;

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
        int counter = 1;
        for (int i = 0; i < users.size(); i++) {
            if(!users.get(i).isActive()){
                System.out.println(counter +": " + users.get(i).showUser());
                counter++;
            }
        }
    }

    public void showAllActiveUsers()
    {
        long count = users.stream().filter(User::isActive).count();
        if(count==0)
            System.out.println("No active Users!");
        else
        {
            for (int i = 0; i < users.size(); i++) {
                if(users.get(i).isActive())
                    System.out.println("Id: "+ (i + 1) +": " + users.get(i).showUser());
            }
        }
    }

    public void setUserActive()
    {
        showAllNotActiveUsers();
        long count = users.stream().filter(user -> !user.isActive()).count();
        if(count!=0)
        {
            Scanner in = new Scanner(System.in);
            System.out.println("Give user ID to activate: ");
            int input = in.nextInt();
            findNotActiveUser(input);
        }else
            System.out.println("All users are active!");
    }

    private void findNotActiveUser(int index)
    {
        int counter = 0;
        for (int i = 0; i < users.size(); i++) {
            if(!users.get(i).isActive())
            {
                counter++;
                if(counter==index)
                    users.get(i).setActive(true);
            }

        }
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
