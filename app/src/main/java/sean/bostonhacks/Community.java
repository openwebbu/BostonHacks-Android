package sean.bostonhacks;

import java.util.ArrayList;

/**
 * Created by Sean on 10/25/15.
 */
public class Community {
    private String teamID;
    private String name;
    private String role;
    private String twitterHandle;
    private String email;

    Community(String memberID, String memberRole, String person, String twitter, String emailName) {
        teamID = memberID;
        role = memberRole;
        name = person;
        twitterHandle = twitter;
        email = emailName;
    }

    public String getID(){return teamID;}

    public String getName() {
        return name;
    }

    public String getRole() {return role;}

    public String getTwitterHandle() {
        return twitterHandle;
    }

    public String getEmail() {
        return email;
    }
}