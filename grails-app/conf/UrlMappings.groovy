class UrlMappings {

	static mappings = {
  
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
                "/project/report/$id/$year?/$month?"( controller: 'project', action: 'report' )
                "/report/week/$year?/$month?/$day?"(controller: 'report', action: 'week')
		"/"(view:"/index")
		"500"(view:'/error')
	}
}
