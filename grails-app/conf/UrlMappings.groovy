class UrlMappings {

	static mappings = {
  
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
                "/task/show/$id"(controller: 'task', action: 'show')
                "/project/report/$id/$year?/$month?"( controller: 'project', action: 'report' )
                "/report/week/$year?/$month?/$day?"(controller: 'report', action: 'week')
		"/"(controller: 'report', action: 'create')
		"500"(view:'/error')
	}
}
