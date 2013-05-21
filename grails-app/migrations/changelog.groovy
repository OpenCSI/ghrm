databaseChangeLog = {

	changeSet(author: "cedj (generated)", id: "1369123259844-1") {
		createTable(tableName: "customer") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "city", type: "VARCHAR(255)")

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "postal_code", type: "VARCHAR(255)")

			column(name: "street", type: "VARCHAR(255)")

			column(name: "picture", type: "BLOB") {
				constraints(nullable: "false")
			}

			column(name: "type_picture", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-2") {
		createTable(tableName: "message_recruitment") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "createat", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "message", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "recruitment_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "title", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "who", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-3") {
		createTable(tableName: "project") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "code", type: "VARCHAR(255)")

			column(name: "createat", type: "DATETIME")

			column(name: "customer_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "VARCHAR(255)")

			column(name: "label", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "updateat", type: "DATETIME")

			column(defaultValue: "FFFFFF", name: "color", type: "VARCHAR(25)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-4") {
		createTable(tableName: "recruitment") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "comment", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "createat", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "file", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "statut_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "title", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "updateat", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "who", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-5") {
		createTable(tableName: "shiro_role") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-6") {
		createTable(tableName: "shiro_role_permissions") {
			column(name: "shiro_role_id", type: "BIGINT")

			column(name: "permissions_string", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-7") {
		createTable(tableName: "shiro_user") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "password_hash", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-8") {
		createTable(tableName: "shiro_user_permissions") {
			column(name: "shiro_user_id", type: "BIGINT")

			column(name: "permissions_string", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-9") {
		createTable(tableName: "shiro_user_roles") {
			column(name: "shiro_user_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "shiro_role_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-10") {
		createTable(tableName: "statut_recruitment") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-11") {
		createTable(tableName: "task") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "createat", type: "DATETIME")

			column(name: "description", type: "VARCHAR(255)")

			column(name: "label", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "updateat", type: "DATETIME")
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-12") {
		createTable(tableName: "task_instance") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "createat", type: "DATETIME")

			column(name: "days", type: "FLOAT") {
				constraints(nullable: "false")
			}

			column(name: "project_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "task_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "updateat", type: "DATETIME")

			column(name: "user_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-13") {
		createTable(tableName: "task_report") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "days", type: "FLOAT") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "task_instance_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-14") {
		createTable(tableName: "test_object") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "createat", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "updateat", type: "DATETIME") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-15") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "firstname", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "initials", type: "VARCHAR(255)")

			column(name: "lastname", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)")

			column(name: "uid", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValueBoolean: "true", name: "showidle", type: "BIT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-16") {
		createTable(tableName: "user_projects_managed") {
			column(name: "project_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-17") {
		addPrimaryKey(columnNames: "shiro_user_id, shiro_role_id", tableName: "shiro_user_roles")
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-18") {
		addPrimaryKey(columnNames: "user_id, project_id", tableName: "user_projects_managed")
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-19") {
		createIndex(indexName: "FKBF7659644B411456", tableName: "message_recruitment", unique: "false") {
			column(name: "recruitment_id")
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-20") {
		createIndex(indexName: "FKED904B19C61A7A5E", tableName: "project", unique: "false") {
			column(name: "customer_id")
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-21") {
		createIndex(indexName: "label", tableName: "project", unique: "true") {
			column(name: "label")
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-22") {
		createIndex(indexName: "FK2E89CD9C344E4BAC", tableName: "recruitment", unique: "false") {
			column(name: "statut_id")
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-23") {
		createIndex(indexName: "FK2E89CD9C9388A93E", tableName: "recruitment", unique: "false") {
			column(name: "user_id")
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-24") {
		createIndex(indexName: "name", tableName: "shiro_role", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-25") {
		createIndex(indexName: "FK389B46C936F4835B", tableName: "shiro_role_permissions", unique: "false") {
			column(name: "shiro_role_id")
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-26") {
		createIndex(indexName: "FK34555A9EDC1F473B", tableName: "shiro_user_permissions", unique: "false") {
			column(name: "shiro_user_id")
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-27") {
		createIndex(indexName: "FKBA22105736F4835B", tableName: "shiro_user_roles", unique: "false") {
			column(name: "shiro_role_id")
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-28") {
		createIndex(indexName: "FKBA221057DC1F473B", tableName: "shiro_user_roles", unique: "false") {
			column(name: "shiro_user_id")
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-29") {
		createIndex(indexName: "label", tableName: "task", unique: "true") {
			column(name: "label")
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-30") {
		createIndex(indexName: "FK2198F60F40AC96FE", tableName: "task_instance", unique: "false") {
			column(name: "task_id")
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-31") {
		createIndex(indexName: "FK2198F60F9388A93E", tableName: "task_instance", unique: "false") {
			column(name: "user_id")
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-32") {
		createIndex(indexName: "FK2198F60FBA953536", tableName: "task_instance", unique: "false") {
			column(name: "project_id")
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-33") {
		createIndex(indexName: "FK770CCD2EEF8691E9", tableName: "task_report", unique: "false") {
			column(name: "task_instance_id")
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-34") {
		createIndex(indexName: "uid", tableName: "user", unique: "true") {
			column(name: "uid")
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-35") {
		createIndex(indexName: "FK78AADACE9388A93E", tableName: "user_projects_managed", unique: "false") {
			column(name: "user_id")
		}
	}

	changeSet(author: "cedj (generated)", id: "1369123259844-36") {
		createIndex(indexName: "FK78AADACEBA953536", tableName: "user_projects_managed", unique: "false") {
			column(name: "project_id")
		}
	}
}
