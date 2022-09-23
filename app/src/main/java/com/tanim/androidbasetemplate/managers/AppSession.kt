import com.tanim.androidbasetemplate.managers.PreferenceManager
import com.tanim.androidbasetemplate.managers.Session
import javax.inject.Singleton

/**
 * Created by Tanim on 5/7/2020.
 */
@Singleton
class AppSession(private val preferenceManager: PreferenceManager) : Session {
    /**
     * Created by Tanim on 5/5/2020.
     */
    //private Session session;
    private var authenticationListener: Session.AuthenticationListener? = null
    private var apiToken: String? = null
    private var user: String? = null


    //    public SessionManager() {
    //
    //    }
    //
    //    private static SessionManager sessionManager;
    //
    //    public static SessionManager getInstance() {
    //        if (sessionManager == null) {
    //            sessionManager = new SessionManager();
    //        }
    //        return sessionManager;
    //    }
    @Synchronized
    override fun isLoggedIn(): Boolean {
        // check if token exist or not
        // return true if exist otherwise false
        // assuming that token exists
        return getApiToken() != null
    }

    @Synchronized
    override fun getApiToken(): String? {

        // return the token that was saved earlier
        //apiToken = "a3cad3012058c00ecb46421198f3ba0b49f0400b";
        if (apiToken == null) {
            apiToken = preferenceManager.getApiToken()
        }
        return apiToken
    }

    override fun setApiToken(token: String?) {
        // save the token
        //SharedPreferenceManagerManager.getInstance().setApiToken(token);
        apiToken = token
        preferenceManager.setApiToken(token)
    }

    @Synchronized
    override fun getUser(): String? {
        if (user == null) {
            user = preferenceManager.getUser()
        }
        return user
    }

    override fun setUser(user: String?) {
        this.user = user
        preferenceManager.setUser(user)
    }

    override fun logOut() {
        if (authenticationListener != null && isLoggedIn()) {
            authenticationListener!!.onUserLoggedOut()
        }
        apiToken = null
        preferenceManager.setApiToken(null)
        preferenceManager.setUser(null)

    }

    override fun logIn() {
        if (authenticationListener != null) {
            authenticationListener!!.onUserLogIn()
        }
    }

    override fun setAuthenticationListener(listener: Session.AuthenticationListener) {
        authenticationListener = listener
    }

}