package sean.bostonhacks;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;

/**
 * Created by Claire on 10/18/2015.
 */
public class MainApplication extends Application {
    public void onCreate(){
        Parse.enableLocalDatastore(getApplicationContext());
        Parse.initialize(this, "TpddRNEVg1gw0BJmle7yrRgiLYqAbLLJQN1mJTDC", "GKgjTZIeq6BsNKUFLAlqGUbpSwHs0RNPeWPoC6w5");
        ParseInstallation.getCurrentInstallation().saveInBackground();


    }
}

