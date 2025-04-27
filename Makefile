DEFAULT_GOAL := help

.PHONY: help
help: ## Show this help
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

create-infra: ## Run this command to create the infrastructure to upload the local project
	docker-compose -f local/docker-compose.yml build
	docker-compose -f local/docker-compose.yml up -d

remove-infra: ## Run this command to delete the infrastructure
	docker stop postgres
	docker rm postgres
