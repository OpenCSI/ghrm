package com.opencsi.ghrm.services
import com.opencsi.ghrm.domain.TaskInstance
import com.opencsi.ghrm.domain.User

class TaskInstanceService {

    static transactional = true

    def findAllOpenByUser(User user) {

        def criteria = TaskInstance.createCriteria()
        return criteria.list {
            eq("user", user)
            eq("status", TaskInstance.STATUS_OPEN)
        }
    }
}
