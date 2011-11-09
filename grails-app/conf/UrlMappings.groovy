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
                "/report/create/$year?/$month?/$day?"(controller: 'report', action: 'create')
                "/report/month/$year?/$month?"(controller: 'report', action: 'month')
                "/taskinstance/create/$projectid/$userid/$taskid/$hours"
		"/"(controller: 'report', action: 'week')
		"500"(controller:'error',action:'error404')
                "404"(controller:'error',action:'error404')
	}
}
