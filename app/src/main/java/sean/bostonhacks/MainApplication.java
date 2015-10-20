package sean.bostonhacks;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by Claire on 10/18/2015.
 */
public class MainApplication extends Application {
    public void onCreate(){
        Parse.initialize(this, "EWm45pIZTDbnjwhUIl09dUM0aM9lygXaicaiHmvv", "fJSWol5CBlMNNw2pCWOjSKnYdx4jsi94R82lhDt7");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}

