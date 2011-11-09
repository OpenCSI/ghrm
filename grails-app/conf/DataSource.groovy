hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}

dataSource {
    pooled = true
    /*driverClassName = hibernate.jdbc.driver_class
    username = hibernate.jdbc.username
    password = hibernate.jdbc.password*/
}
// environment specific settings
/*
"create-drop" - Drop and re-create the database when Grails is run
"create" - Create the database if it doesn't exist, but don't modify it if it does. Deletes existing data.
"update" - Create the database if it doesn't exist, and modify it if it does exist
*/
environments {
    development {
        dataSource {
            dbCreate = "" // one of 'create', 'create-drop','update'
            driverClassName = "" //com.mysql.jdbc.Driver
            url = "" //"jdbc:mysql://localhost/ghrm"
            username = "" //"asyd"
            password = "" //"asyd"
        }
    }

    production {
        dataSource {
            dbCreate = "" // one of 'create', 'create-drop','update'
            driverClassName = "" //com.mysql.jdbc.Driver
            url = "" //"jdbc:mysql://localhost/ghrm"
            username = "" //"asyd"
            password = "" //"asyd"
        }
    }
}