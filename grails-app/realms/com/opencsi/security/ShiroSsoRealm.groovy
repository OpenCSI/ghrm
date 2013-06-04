package com.opencsi.security

import org.apache.shiro.authc.AccountException
import org.apache.shiro.authc.UnknownAccountException
import org.apache.shiro.authc.SimpleAccount


//import org.apache.shiro.realm

class ShiroSsoRealm{
    static authTokenClass = org.apache.shiro.authc.UsernamePasswordToken
    private static boolean _bSSO

    static def SSO(def bSSO){
        _bSSO = bSSO
    }
    
    def authenticate(authToken){
        if(!_bSSO)
            throw new AccountException("No SSO request.")
        
        if(authToken == null)
            throw new AccountException("Null usernames are not allowed by this realm.")
        
        def user = ShiroUser.findByUsername(authToken.username)//request.getHeader("REMOTE_USER")
        if (!user) {
            throw new UnknownAccountException("No account found for user [${username}]")
        }
        
        return new SimpleAccount(user.username, "", "ShiroDbRealm")
        
    }

}