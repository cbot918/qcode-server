init:

run:

db-cli:
	docker run -it --rm --network qcode-server_default mysql mysql -h db -u root -p

db-init:
	docker cp db.sql qcode-server-db-1:/
	docker exec -it qcode-server-db-1 bash -c 'mysql -u root -p < db.sql'

db-reset:
	docker run -it --rm --network $NETWORK mysql mysql -h db -u $DB_USER -p$"$DB_PASSWORD" $DB -e "drop database qcode"

db-dbs:
	docker run -it --rm --network qcode-server_default mysql mysql -h db -u root -p$'12345' -e show databases

db-tables:
	docker run -it --rm --network qcode-server_default mysql mysql -h db -u root -p$'12345' -e use qcode;show tables;


.PHONY: init run
.SILENT: init run