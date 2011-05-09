class UrlMappings {

	static mappings = {
  
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
                "/task/show/$id"(controller: 'task', action: 'show')
                "/project/report/$id/$year?/$month?"( controller: 'project', action: 'report' )
                "/project/show/$id"(controller: 'project', action: 'show')
                "/report/week/$year?/$month?/$day?"(controller: 'report', action: 'week')
                "/taskinstance/create/$projectid/$userid/$taskid/$hours"
		"/"(controller: 'report', action: 'create')
		"500"(view:'/error')
	}
}
