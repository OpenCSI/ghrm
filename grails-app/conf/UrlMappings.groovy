class UrlMappings {

	static mappings = {
  
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
                "/project/report/$id/$year?/$month?"( controller: 'project', action: 'report' )
		"/"(view:"/index")
		"500"(view:'/error')
	}
}
