/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

/**
 *
 * @author be Dunn
 * Simple task handler for making sure the user session is passed arround 
 * during the login, includes only three values, username, password and 
 * admin status
 */
public class Session {
    
    private String userName, passWord, failureStatusMessage;
    private boolean adminStatus, Success;
    private int _ID; 
    
    //successfully created session. 
    public Session (int _ID, String userName, String PassWord, boolean adminStatus) {
        this.userName = userName;
        this.passWord = passWord;
        this.adminStatus = adminStatus; 
        this.Success = true; 
        this._ID = _ID; //should not be gettable setable 
    }
    //failed session login, contains reason for failure. 
    
    public Session (String failureStatusMessage) {
        this.failureStatusMessage = failureStatusMessage;
        this.Success = false; 
    }

    
    
    public String getFailureStatusMessage() {
        return failureStatusMessage;
    }

    //auto generated getters, a session can only be pulled for information
    public boolean isSuccess() {
        return Success;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public boolean isAdminStatus() {
        return adminStatus;
    }
    
    
}
