DEFAULT_GOAL := help

.PHONY: help
help: ## Show this help
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

create-infra-local: ## Run this command to create the infrastructure to upload the local project
	docker-compose -f local/docker-compose-local.yml build
	docker-compose -f local/docker-compose-local.yml up -d

create-infra-test: ## Run this command to create the infrastructure to run the integration tests
	docker-compose -f local/docker-compose-test.yml build
	docker-compose -f local/docker-compose-test.yml up -d

remove-infra: ## Run this command to delete the infrastructure
	docker stop postgres zookeeper kafka kafka-schema-registry zookeeper-secondary kafka-secondary kafka-schema-registry-secondary redpanda-primary redpanda-secondary redis
	docker rm postgres zookeeper kafka kafka-schema-registry zookeeper-secondary kafka-secondary kafka-schema-registry-secondary redpanda-primary redpanda-secondary redis
