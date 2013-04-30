class UrlMappings {

	static mappings = {
  
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
                "/task/show/$id"(controller: 'task', action: 'show')
                "/task/list/$format?/$extension?"(controller: 'task', action :'list')
                "/customer/list/$format?/$extension?"(controller: 'customer', action: 'list')
                "/user/list/$format?/$extension?"(controller: 'user', action :'list')
                "/project/list/$format?/$extension?"(controller: 'project', action :'list')
                "/project/report/$id/$year?/$month?"( controller: 'project', action: 'report' )
                "/project/show/$id"(controller: 'project', action: 'show')
                "/report/week/$year?/$month?/$day?"(controller: 'report', action: 'week')
                "/report/create/$year?/$month?/$day?"(controller: 'report', action: 'create')
                "/report/month/$year?/$month?"(controller: 'report', action: 'month')
                "/report/export/"(controller: 'report', action: 'export')
                "/report/exportPDF/"(controller: 'report', action: 'exportPDF')
                "/project/reportPDF/$id?/$year?/$month?/$format?/$extension?"(controller:'project',action: 'reportPDF')
                "/taskinstance/create/$projectid/$userid/$taskid/$hours"
		"/"(controller: 'report', action: 'week')
		/*"500"(controller:'error',action:'error404')
                "404"(controller:'error',action:'error404')*/
	}
}
