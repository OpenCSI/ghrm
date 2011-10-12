package com.opencsi.security

import javax.naming.AuthenticationException
import javax.naming.Context
import javax.naming.NamingException
import javax.naming.directory.BasicAttribute
import javax.naming.directory.BasicAttributes
import javax.naming.directory.InitialDirContext

import org.apache.shiro.authc.AccountException
import org.apache.shiro.authc.CredentialsException
import org.apache.shiro.authc.IncorrectCredentialsException
import org.apache.shiro.authc.UnknownAccountException

/**
 * Simple realm that authenticates users against an LDAP server.
 */
class ShiroLdapRealm {
    static authTokenClass = org.apache.shiro.authc.UsernamePasswordToken

    def grailsApplication

    def authenticate(authToken) {
        def appConfig = grailsApplication.config
        // if the authenticate value is 'on', enable LDAP:
        if (appConfig.ldap.authentication.toString().equals('on'))
        {
            log.info "Attempting to authenticate ${authToken.username} in LDAP realm..."
            def username = authToken.username
            def password = new String(authToken.password)

            // Get LDAP config for application. Use defaults when no config
            // is provided.
            def ldapUrls = appConfig.ldap.server.url ?: [ "ldap://localhost:389/" ]
            def searchBase = appConfig.ldap.search.base ?: ""
            def searchUser = appConfig.ldap.search.user ?: ""
            def searchPass = appConfig.ldap.search.pass ?: ""
            def usernameAttribute = appConfig.ldap.username.attribute ?: "uid"
            def skipAuthc = appConfig.ldap.skip.authentication ?: false
            def skipCredChk = appConfig.ldap.skip.credentialsCheck ?: false
            def allowEmptyPass = appConfig.ldap.allowEmptyPasswords != [:] ? appConfig.ldap.allowEmptyPasswords : true

            // Skip authentication ?
            if (skipAuthc) {
              log.info "[LDAP] : Skipping authentication in development mode."
              return username
            }

            // Null username is invalid
            if (username == null) {
                throw new AccountException("[LDAP] : Null usernames are not allowed by this realm.")
            }

            // Empty username is invalid
            if (username == "") {
                throw new AccountException("[LDAP] : Empty usernames are not allowed by this realm.")
            }

            // Allow empty passwords ?
            if (!allowEmptyPass) {
                // Null password is invalid
                if (password == null) {
                    throw new CredentialsException("[LDAP] : Null password are not allowed by this realm.")
                }

                // empty password is invalid
                if (password == "") {
                    throw new CredentialsException("[LDAP] : Empty passwords are not allowed by this realm.")
                }
            }

            // Accept strings and GStrings for convenience, but convert to
            // a list.
            if (ldapUrls && !(ldapUrls instanceof Collection)) {
                ldapUrls = [ ldapUrls ]
            }

            // Set up the configuration for the LDAP search we are about
            // to do.
            def env = new Hashtable()
            env[Context.INITIAL_CONTEXT_FACTORY] = "com.sun.jndi.ldap.LdapCtxFactory"
            if (searchUser) {
                // Non-anonymous access for the search.
                env[Context.SECURITY_AUTHENTICATION] = "simple"
                env[Context.SECURITY_PRINCIPAL] = searchUser
                env[Context.SECURITY_CREDENTIALS] = searchPass
            }

            // Find an LDAP server that we can connect to.
            def ctx
            def urlUsed = ldapUrls.find { url ->
                log.info "[LDAP] : Trying LDAP server ${url} ..."
                env[Context.PROVIDER_URL] = url

                // If an exception occurs, log it.
                try {
                    ctx = new InitialDirContext(env)
                    return true
                }
                catch (NamingException e) {
                    println "[LDAP] : Could not connect to ${url}: ${e}"
                    return false
                }
            }

            if (!urlUsed) {
                def msg = '[LDAP] : No LDAP server available.'
                println msg
                throw new org.apache.shiro.authc.AuthenticationException(msg)
            }

            // Look up the DN for the LDAP entry that has a 'uid' value
            // matching the given username.
            def matchAttrs = new BasicAttributes(true)
            matchAttrs.put(new BasicAttribute(usernameAttribute, username))
            def result = ctx.search(searchBase, matchAttrs)
            if (!result.hasMore()) {
                println "[LDAP] : No account found for user [${username}]"
                throw new UnknownAccountException("[LDAP] : No account found for user [${username}]")
            }

            // Skip credentials check ?
            if (skipCredChk) {
              println "[LDAP] : Skipping credentials check in development mode."
              return username
            }

            // Now connect to the LDAP server again, but this time use
            // authentication with the principal associated with the given
            // username.
            def searchResult = result.next()
            env[Context.SECURITY_AUTHENTICATION] = "simple"
            env[Context.SECURITY_PRINCIPAL] = searchResult.nameInNamespace
            env[Context.SECURITY_CREDENTIALS] = password

            try {
                new InitialDirContext(env)
                return username
            }
            catch (AuthenticationException ex) {
                println "[LDAP] : Invalid password for user '${username}'"
                throw new IncorrectCredentialsException("[LDAP] : Invalid password for user '${username}'")
            }
        }
    }
}
