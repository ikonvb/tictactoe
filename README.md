# Tictactoe

**Описание**  
Для запуска приложения без участия Spring Boot нам потребуется класс **PlayGameXmlJson** из пакета **gameplayxmljson**.    
Через этот класс можно запустить:
1. Игру с записью результата в файл с расширением xml или json на выбор.
2. Воспроизведение игры из файла с расширением xml или json на выбор.    

Для запуска приложения с участием Spring Boot и REST API нам потребуется класс **TicTacToeApplication** из пакета **com.konstantinbulygin.tictactoexml**.    
Для работы с REST API рекомендуется использовать [**Postman**](https://www.postman.com/).
1. Для загрузки файла через REST API имеется endpoint "/upload" - это POST метод.    
   Возвращает строку и статус *HttpStatus.OK* если все прошло хорошо и *HttpStatus.NOT_ACCEPTABLE* если что-то пошло не так.    
   После успешной загрузки автоматически запускается воспроизведение игры из загруженного файла. Ход игры отображается в консоли.
2. Документацию для игры через REST API можно найти по этому адрессу:    
~~~
 {yourlocalhostaddress}/swagger-ui/index.html
 ~~~

