echo "Для запуска должен быть установлен maven и java(21)"
mvn clean package

java -jar target/hw_1_core-1.0-SNAPSHOT.jar
