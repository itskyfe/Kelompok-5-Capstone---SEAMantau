/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author TUF
 */
public abstract class LoginLimiter {   

    protected final int MAX_LOGIN_ATTEMPTS = 3;
    
    protected int failedAttempts = 0;


    public boolean hasReachedLimit() {
        return failedAttempts >= MAX_LOGIN_ATTEMPTS;
    }
    public void resetAttempts() {
        this.failedAttempts = 0;
//        System.out.println("Percobaan Login Kembali di set menjadi 0.");
    }


    public void recordFailedLogin() {
        if (!hasReachedLimit()) {
            failedAttempts++;
            onFailedAttempt(); 
        }
        
        if (hasReachedLimit()) {
            onLimitReached(); 
        }
    }

    public abstract void onFailedAttempt();


    public abstract void onLimitReached();
}
