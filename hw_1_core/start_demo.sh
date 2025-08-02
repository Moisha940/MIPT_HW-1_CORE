echo "Для запуска должен быть установлен maven и java(21)"
mvn clean package

( echo "1"; # Создадим студента
  echo "Миша";

  echo "2"; # Добавить оценку
  echo "9";

  echo "2"; # Добавить оценку
  echo "8";

  echo "2"; # Добавить оценку
  echo "7";

  echo "4"; # Вывести студента

  echo "3"; # удалить оценку
  echo "7";

  echo "4"; # Вывести студента

  echo "3"; # удалить оценку
  echo "8"

  echo "5"; # Выход
) | java -jar target/hw_1_core-1.0-SNAPSHOT.jar

