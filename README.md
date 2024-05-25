# wb_ozon

1. Cоздать папку на диске C:\ (назвать at)
2. Скопировать в эту папку java_jdk и maven
3. Создать в переменных окружения:
- JAVA_HOME = c:\at\jdk_11.0.2 (путь указан как пример)
- M2_HOME = c:\at\maven (путь указан как пример)
- MAVEN_HOME = c:\at\maven
4. В path добавить:
- %JAVA_HOME%\bin
- %M2_HOME%\bin

В проект добавить 2 папки (in_files / out_files):
- src/test/resources/in_files
- src/test/resources/out_files

В папку in_files добавить file data.txt (файл будет содержать входные данные для поиска)

Например:
- капсулы для стирки Ariel 15 капсул 
- зубная паста lacalut актив 75 мл 2 шт
