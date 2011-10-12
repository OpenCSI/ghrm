hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}

dataSource {
    pooled = true
    driverClassName = hibernate.jdbc.driver_class
    username = hibernate.jdbc.username
    password = hibernate.jdbc.password
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create"
            //url = "jdbc:mysql://url/DB"
            url = hibernate.jdbc.url
        }
    }

    hsql {
        dataSource {
            dbCreate = "create" // one of 'create', 'create-drop','update'
            url = "jdbc:hsqldb:mem:devDB"
            driverClassName = "org.hsqldb.jdbcDriver"
            username = "SA"
            password = ""
        }
    }

    shell {
        dataSource {
            dbCreate = "create" // one of 'create', 'create-drop','update'
            //url = "jdbc:hsqldb:mem:devDB"
           // url = "jdbc:mysql://proxy-local.opencsi.com/ghrm"
            url = hibernate.jdbc.url
        }
    }

    test {
        dataSource {
            dbCreate = "create"
            url = hibernate.jdbc.url
        }
    }
    production {
        dataSource {
            dbCreate = "create"
            url = hibernate.jdbc.url
        }
    }
}
