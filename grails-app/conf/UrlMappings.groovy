class UrlMappings {

	static mappings = {
  
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
                "/project/test/$id/$year?/$month?"( controller: 'project', action: 'test' )
                "/titi"(view:"/index")
		"/"(view:"/index")
		"500"(view:'/error')
	}
}
