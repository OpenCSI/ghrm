# What is GHRM?

# Installation

    $ git clone https://github.com/OpenCSI/ghrm

Copy src/groovy/app-config.properties.sample to app-config.properties and edit the file to fit your configuration

## Create WAR file with the following command:

 	$ grails prod war

Deploy WAR on your favorite servlet container (like Apache Tomcat). You also need to add your JDBC driver
in the container classpath (like mysql-jdbc.)

# SSO

By default, GHRM will authenticate user w/o password when REMOTE_USER is defined (for example by a SSO agent).
If you use Tomcat (or Jboss) ensure to add the following paramter to your connector (HTTP or AJP)

	tomcatAuthentication="false"

You also need to create users in GHRM before they are able to authenticate.
